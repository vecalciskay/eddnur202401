import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.logging.*;

public class VentanaPrincipal extends JFrame {
    private JButton botonOrdenar;
    private JPanel panelGrupos;
    private static final String[] VOCALS = {"A", "E", "I", "O", "U"};
    private JTextArea[] areasTexto;
    private static final Logger logger = Logger.getLogger(VentanaPrincipal.class.getName());

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


    public VentanaPrincipal() {
        super("Ordenamiento de Palabras");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        logger.info("Se abrió la aplicación");

        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemLeer = new JMenuItem("Leer");
        itemLeer.addActionListener(e -> leerArchivo());
        menuArchivo.add(itemLeer);
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> salirDeLaAplicacion());
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelBotonOrdenar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonOrdenar = new JButton("Ordenar");
        botonOrdenar.addActionListener(e -> ordenarPalabras());
        panelBotonOrdenar.add(botonOrdenar);
        panelPrincipal.add(panelBotonOrdenar, BorderLayout.NORTH);

        panelGrupos = new JPanel();
        panelGrupos.setLayout(new GridLayout(1, VOCALS.length));
        areasTexto = new JTextArea[VOCALS.length];
        for (int i = 0; i < VOCALS.length; i++) {
            JPanel panelVocal = new JPanel(new BorderLayout());
            areasTexto[i] = new JTextArea(5, 15);
            areasTexto[i].setEditable(false);
            JScrollPane scrollPane = new JScrollPane(areasTexto[i]);
            panelVocal.add(new JLabel(VOCALS[i], SwingConstants.CENTER), BorderLayout.NORTH);
            panelVocal.add(scrollPane, BorderLayout.CENTER);
            panelGrupos.add(panelVocal);
        }
        panelPrincipal.add(panelGrupos, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private void leerArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
        fileChooser.setFileFilter(filter);

        logger.info("Se abrió el selector de archivos");

        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    procesarLinea(linea);
                }
                botonOrdenar.setEnabled(true);
                logger.info("Se importaron correctamente las palabras del archivo");
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error al leer el archivo", e);
                JOptionPane.showMessageDialog(this, "Error al leer el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void procesarLinea(String linea) {
        String[] palabras = linea.split("\n");

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                agregarPalabraALetraVocal(palabra);
            }
        }
    }
    private void agregarPalabraALetraVocal(String palabra) {
        char primeraLetra = palabra.toUpperCase().charAt(0);
        for (int i = 0; i < VOCALS.length; i++) {
            if (VOCALS[i].charAt(0) == primeraLetra) {
                areasTexto[i].append(palabra + "\n");
                break;
            }
        }
    }

    private void ordenarPalabras() {
        botonOrdenar.setEnabled(false);
        int contador = 0;

        // Itero sobre cada vocal
        for (int i = 0; i < VOCALS.length; i++) {
            JTextArea areaTexto = areasTexto[i]; // Obtengo el JTextArea correspondiente a la vocal actual
            String[] palabras = areaTexto.getText().split("\n"); // Divido el texto del JTextArea en un arreglo de palabras
            // Validacion si no hay nada que ordenar, por que nos han retornado un array vacio
            if (palabras.length == 1) {
                contador += 1;
                if (contador >= 5) {
                    logger.warning("No hubo palabras que ordenar, probablemente por que las palabras aun no se han importado...");
                    botonOrdenar.setEnabled(true);
                    return;
                }
            }

            // Algoritmo de ordenamiento de burbuja
            for (int j = 0; j < palabras.length - 1; j++) {
                for (int k = 0; k < palabras.length - j - 1; k++) {
                    // Comparo las palabras y las intercambio si es necesario para ordenarlas alfabéticamente
                    if (compararPalabras(palabras[k], palabras[k + 1]) > 0) {
                        String temp = palabras[k];
                        palabras[k] = palabras[k + 1];
                        palabras[k + 1] = temp;
                    }
                }
            }

            // Actualizo el JTextArea con las palabras ordenadas
            areaTexto.setText(String.join("\n", palabras));
        }
        logger.info("Se han ordenado las palabras correctamente");
    }

    private int compararPalabras(String word1, String word2) {
        // Obtengo la longitud minima de las dos palabras
        int minLength = Math.min(word1.length(), word2.length());
        // Itero sobre cada carácter de ambas palabras
        for (int i = 0; i < minLength; i++) {
            // Conviertyo los caracteres a mayusculas para realizar una comparacion insensible a mayusculas y minusculas
            char char1 = Character.toUpperCase(word1.charAt(i));
            char char2 = Character.toUpperCase(word2.charAt(i));
            // Comparo los caracteres
            if (char1 != char2) {
                // Si los caracteres son diferentes, devuelve la diferencia de sus valores en unicode
                return char1 - char2;
            }
        }
        // Si las palabras son iguales hasta el final de la mas corta, devuelve la diferencia de longitudes
        return word1.length() - word2.length();
    }


    private void salirDeLaAplicacion() {
        logger.info("Se cerró la aplicación");
        System.exit(0);
    }
}
