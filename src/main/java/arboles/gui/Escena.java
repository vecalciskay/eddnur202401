package arboles.gui;

import arboles.Arbol;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Escena {
    private Arbol<String> arbol;
    private String mensaje;

    private PropertyChangeSupport observado;

    public Escena(Arbol<String> a) {
        arbol = a;
        mensaje = "";
        observado = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener observador) {
        observado.addPropertyChangeListener(observador);
    }

    public Arbol<String> getArbol() {
        return arbol;
    }

    public void setArbol(Arbol<String> arbol) {
        this.arbol = arbol;
        observado.firePropertyChange("ESCENA", true, false);
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
        observado.firePropertyChange("ESCENA", true, false);
    }
}
