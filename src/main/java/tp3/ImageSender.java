import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.*;
import java.io.*;
import java.util.logging.*;


public class ImageSender extends JFrame {
    private JPanel imagePanel;
    private BufferedImage loadedImage = null;
    private static final Logger logger = Logger.getLogger(ImageSender.class.getName());

    static {
        try {
            String logDirectory = "logs";
            File directory = new File(logDirectory);

            if (!directory.exists()) {
                directory.mkdir();
            }

            FileHandler fileHandler = new FileHandler(logDirectory + "/logs.log", true);
            logger.addHandler(fileHandler);

            logger.setLevel(Level.INFO);

            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al guardar el archivo de los logs", e);
        }
    }

    public ImageSender() {
        setTitle("Receptor y Enviador de Imagenes 4000");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (loadedImage != null) {
                    g.drawImage(loadedImage, 0, 0, this);
                }
            }
        };

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        JMenuItem loadItem = new JMenuItem("Cargar Imagen");
        JMenuItem sendItem = new JMenuItem("Enviar Imagen");
        JMenuItem serverItem = new JMenuItem("Actuar como Servidor");
        JMenuItem exitItem = new JMenuItem("Salir");

        loadItem.addActionListener(this::loadImage);
        sendItem.addActionListener(this::sendImage);
        serverItem.addActionListener(this::actAsServer);
        exitItem.addActionListener(e -> System.exit(0));

        menu.add(loadItem);
        menu.add(sendItem);
        menu.add(serverItem);
        menu.add(exitItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        add(imagePanel);

        setVisible(true);
    }

    private void loadImage(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                loadedImage = ImageIO.read(fileChooser.getSelectedFile());
                imagePanel.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void sendImage(ActionEvent e) {
        if (loadedImage == null) {
            JOptionPane.showMessageDialog(this, "No hay imagen cargada");
            return;
        }

        String serverIP = JOptionPane.showInputDialog(this, "Ingrese la dirección IP del servidor:", "localhost");
        String serverPort = JOptionPane.showInputDialog(this, "Ingrese el puerto del servidor:", "4646");
        if (serverIP != null && serverPort != null) {
            int port = Integer.parseInt(serverPort);

            try (Socket socket = new Socket(serverIP, port);
                 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                 ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

                ImageIO.write(loadedImage, "png", baos);
                byte[] imageBytes = baos.toByteArray();
                String hexData = bytesToHex(imageBytes);
                for (int i = 0; i < hexData.length(); i += 6) {
                    String part = hexData.substring(i, Math.min(i + 6, hexData.length()));
                    logger.info(part);
                }
                oos.writeObject(imageBytes);
                logger.log(Level.INFO, "Imagen enviada al servidor en {0}:{1}", new Object[]{serverIP, port});
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Error al enviar la imagen: {0}", ex.getMessage());
                ex.printStackTrace();
            }
        }
    }



    private void actAsServer(ActionEvent e) {
        String portString = JOptionPane.showInputDialog(this, "Indique el puerto:", "4646");
        if (portString != null) {
            int port = Integer.parseInt(portString);

            new Thread(() -> {
                try (ServerSocket serverSocket = new ServerSocket(port)) {
                    logger.log(Level.INFO, "Servidor en el puerto {0}", port);

                    while (true) {
                        try (Socket clientSocket = serverSocket.accept();
                             ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {

                            byte[] imageBytes = (byte[]) ois.readObject();
                            BufferedImage receivedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
                            SwingUtilities.invokeLater(() -> {
                                loadedImage = receivedImage;
                                imagePanel.repaint();
                            });
                            logger.log(Level.INFO, "Imagen recibida de {0}", clientSocket.getRemoteSocketAddress());
                        } catch (IOException | ClassNotFoundException ex) {
                            logger.log(Level.SEVERE, "Fallo con el envio de la imagen: {0}", ex.getMessage());
                            ex.printStackTrace();
                        }
                    }
                } catch (IOException ex) {
                    logger.log(Level.SEVERE, "Error para empezar el servidor: {0}", ex.getMessage());
                    ex.printStackTrace();
                }
            }).start();
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImageSender::new);
    }
}
