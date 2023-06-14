package ConexionBD;

import modelo.Miembro;
import modelo.*;

import java.sql.*;

public class ConexionBD {
    private static Connection conexion= null;
    //PreparedStatement stm = conexion.prepareStatement(sql);
    private static PreparedStatement pstm; //Lo mejor es utilizar PREPARE STATEMEN por seguridad, para evitar SQL INJECTION
    private static ResultSet rs;

    private ConexionBD(){
        //buscar el driver para la conexion con mysql
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String URL = "jdbc:mysql://localhost:3306/Empresa_Teatro";

            conexion = DriverManager.getConnection(URL, "root", "Alfredocarlitos88");

        } catch (ClassNotFoundException e) {
           // throw new RuntimeException(e);
            System.out.println("Error en el controlador de conexion a MySQL");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error en la ruta de conexión");
        }
    }//constructor

    public static Connection getConexion(){
        if (conexion == null){
            new ConexionBD();
        }
        return conexion;
    }

    static void cerrarConexion(){
        try {
            pstm.close();
            conexion.close();
        }catch (SQLException e){
            System.out.println("Error al cerrar la conexion");
            e.printStackTrace();
        }
    }


    //metodo para ABC (Altas, Bajas, Cambios)
    public static boolean AgregarMiembro(Miembro a){
        try {
            pstm = conexion.prepareStatement("insert into miembros values(?,?,?,?,?,?)");
            System.out.println("METODO DE AGREGAR MIEMBRO CONEXIONBD "+a.getID_Miembro() + a.getNombre() + a.getApellido() + a.getEdad() + a.getEs_Actor() + a.getCalle());
            pstm.setInt(1,a.getID_Miembro());
            System.out.println(a.getID_Miembro());
            pstm.setString(2, a.getNombre());
            pstm.setString(3,a.getApellido());
            pstm.setByte(4,a.getEdad());
            pstm.setString(5,a.getEs_Actor());
            pstm.setInt(6,a.getCalle());
            pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en AgregarPacienete CB.java");
        }
        return false;
    }

    public static boolean EliminarMiembro(String instruccion){
        try {
            String consulta = instruccion;
            pstm = conexion.prepareStatement(consulta);
            pstm.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("ERROR");
        }
        return false;
    }

    public static boolean ActualizarMiembro(Miembro a){

        try {
            pstm = conexion.prepareStatement("UPDATE Miembros SET Nombre=?,Apellido=?,Edad=?,Es_Actor=?,ID_Calle=? where ID_Miembro="+a.getID_Miembro()+"");
            pstm.setString(1,a.getNombre());
            pstm.setString(2,a.getApellido());
            pstm.setByte(3,a.getEdad());
            pstm.setString(4,a.getEs_Actor());
            pstm.setInt(5,a.getCalle());

            pstm.executeUpdate();

            return true;

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return false;
    }


    public static ResultSet BuscarMiembro(String consulta){

        try {
                pstm = conexion.prepareStatement(consulta);
            return pstm.executeQuery();

        } catch (SQLException e) {
            System.out.println("Error en instruccion SQL");
        }
        return null;
    }





    public static void main(String[] args) {
        new ConexionBD();
    }
}
