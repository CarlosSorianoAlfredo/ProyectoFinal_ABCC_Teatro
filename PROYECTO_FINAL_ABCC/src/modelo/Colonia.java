package modelo;

public class Colonia {

    private int ID_Colonia;
    private String Nombre_Colonia;

    public Colonia(int ID_Colonia, String nombre_Colonia) {
        this.ID_Colonia = ID_Colonia;
        Nombre_Colonia = nombre_Colonia;
    }

    public int getID_Colonia() {
        return ID_Colonia;
    }

    public void setID_Colonia(int ID_Colonia) {
        this.ID_Colonia = ID_Colonia;
    }

    public String getNombre_Colonia() {
        return Nombre_Colonia;
    }

    public void setNombre_Colonia(String nombre_Colonia) {
        Nombre_Colonia = nombre_Colonia;
    }
}
