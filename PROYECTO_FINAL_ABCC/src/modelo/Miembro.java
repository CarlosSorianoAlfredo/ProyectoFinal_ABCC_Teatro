package modelo;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class Miembro {
    private int ID_Miembro;
    private String Nombre;
    private String Apellido;
    private String Es_Actor;
    private String Calle, Colonia;

    public Miembro(int ID_Miembro, String nombre, String apellido, String es_Actor, String calle, String colonia) {
        this.ID_Miembro = ID_Miembro;
        Nombre = nombre;
        Apellido = apellido;
        Es_Actor = es_Actor;
        Calle = calle;
        Colonia = colonia;
    }
    public Miembro(){}

    public int getID_Miembro() {
        return ID_Miembro;
    }

    public void setID_Miembro(int ID_Miembro) {
        this.ID_Miembro = ID_Miembro;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getEs_Actor() {
        return Es_Actor;
    }

    public void setEs_Actor(String es_Actor) {
        Es_Actor = es_Actor;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String calle) {
        Calle = calle;
    }

    public String getColonia() {
        return Colonia;
    }

    public void setColonia(String colonia) {
        Colonia = colonia;
    }



}
