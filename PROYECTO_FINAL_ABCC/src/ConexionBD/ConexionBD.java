package ConexionBD;

import java.sql.*;

public class ConexionBD {
    private Connection conexion;
    private Statement stm; //Lo mejor es utilizar PREPARE STATEMEN por seguridad, para evitar SQL INJECTION
    private ResultSet rs;

    public ConexionBD(){
        //buscar el driver para la conexion con mysql
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String URL = "jdbc:mysql://localhost:3306/Empresa_Teatro";

            conexion = DriverManager.getConnection(URL, "root", "Alfredocarlitos88");

            System.out.println("Conexion establecida");


        } catch (ClassNotFoundException e) {
           // throw new RuntimeException(e);
            System.out.println("Error en el controlador de conexion a MySQL");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error en la ruta de conexión");
        }

    }//constructor

    //metodo para ABC (Altas, Bajas, Cambios)
    public boolean ejecutarInstruccionDML(String instruccionDML){
        boolean result= false;
        try {
            stm = conexion.createStatement();
            if(stm.executeUpdate(instruccionDML)>=1)
                result=true;
        } catch (SQLException e) {
            System.out.println("Error en instruccion DMl");
        }
        return result;
    }

    public ResultSet ejecutarConsultaSQL(String consultaSQL){
        rs = null;
        try {
            stm = conexion.createStatement();
            rs = stm.executeQuery(consultaSQL);

        } catch (SQLException e) {
            System.out.println("Error en instruccion SQL");
        }
        return rs;
    }


    public void cerrarConexion(){
        try {
            conexion.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        new ConexionBD();
    }
}
