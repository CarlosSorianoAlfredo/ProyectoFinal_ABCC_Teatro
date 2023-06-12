package modelo;

public class Calle {
    private int ID_Calle;
    private String Nombre_Calle;
    private int ID_Colonia;

    public Calle() {}
    public Calle(int ID_Calle, String nombre_Calle, int ID_Colonia) {
        this.ID_Calle = ID_Calle;
        Nombre_Calle = nombre_Calle;
        this.ID_Colonia = ID_Colonia;
    }

    public int getID_Calle() {
        return ID_Calle;
    }

    public void setID_Calle(int ID_Calle) {
        this.ID_Calle = ID_Calle;
    }

    public String getNombre_Calle() {
        return Nombre_Calle;
    }

    public void setNombre_Calle(String nombre_Calle) {
        Nombre_Calle = nombre_Calle;
    }

    public int getID_Colonia() {
        return ID_Colonia;
    }

    public void setID_Colonia(int ID_Colonia) {
        this.ID_Colonia = ID_Colonia;
    }
}
