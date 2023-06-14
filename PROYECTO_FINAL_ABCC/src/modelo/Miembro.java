package modelo;



public class Miembro {
    private int ID_Miembro, Calle;
    private byte Edad;
    private String Nombre;
    private String Apellido;
    private String Es_Actor;

    public Miembro(int ID_Miembro, String nombre, String apellido, byte edad, String es_Actor, int calle) {
        this.ID_Miembro = ID_Miembro;
        Nombre = nombre;
        Apellido = apellido;
        Es_Actor = es_Actor;
        Calle = calle;
        Edad = edad;
    }

    public Miembro() {
    }

    public int getID_Miembro() {
        return ID_Miembro;
    }

    public void setID_Miembro(int ID_Miembro) {
        this.ID_Miembro = ID_Miembro;
    }

    public byte getEdad() {
        return Edad;
    }

    public void setEdad(byte edad) {
        Edad = edad;
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

    public int getCalle() {
        return Calle;
    }

    public void setCalle(int calle) {
        Calle = calle;
    }






}
