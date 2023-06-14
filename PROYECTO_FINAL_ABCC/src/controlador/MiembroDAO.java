package controlador;

import ConexionBD.ConexionBD;
import modelo.Calle;
import modelo.Colonia;
import modelo.Miembro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MiembroDAO {


    public ArrayList<Calle> buscarCalle(String filtro){
        ArrayList<Calle> listaCalle= new ArrayList<>();
        String sql="SELECT * FROM Calles";

        ResultSet rs = ConexionBD.BuscarMiembro(sql);
        try {
            rs.next();

            do{
                int ID= rs.getInt(1);
                String nom_Calle=rs.getString(2);
                int ID_Col= rs.getInt(3);

                listaCalle.add(new Calle(ID,nom_Calle,ID_Col));

            }while(rs.next());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCalle;
}

    public ArrayList<Colonia> buscarColonia(String filtro){
        ArrayList<Colonia> listaColonia= new ArrayList<>();
        String sql="SELECT * FROM Colonias";

        ResultSet rs = ConexionBD.BuscarMiembro(sql);
        try {
            rs.next();

            do{
                int ID_Col= rs.getInt(1);
                String nom_Col=rs.getString(2);


                listaColonia.add(new Colonia(ID_Col,nom_Col));

            }while(rs.next());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaColonia;
    }


    //=============Metodos ABCC (CRUD)================

    //================Altas=====================
    public  boolean agregarMiembro(Miembro a){
        boolean res= false;

        res= ConexionBD.AgregarMiembro(a);
        return res;
    }


    //================Bajas=====================
    public boolean eliminarMiembro(String IDMiembro){
        boolean res = false;
        String sql= "DELETE from Miembros where ID_Miembro = '"+IDMiembro+"'";

        res = ConexionBD.EliminarMiembro(sql);

        return res;
    }

    //================Cambios===================
    public boolean actualizarMiembro(Miembro a){
        boolean res = false;

        //String sql="UPDATE Miembros SET  Nombre='"+a.getNombre() + "', Apellido='"+a.getApellido()+ "', Edad="+a.getEdad() +", Es_Actor='"+a.getEs_Actor() + "', ID_Calle="+a.getCalle() + " WHERE ID_Miembro = "+a.getID_Miembro()+""; //Esta linea es para poder seleccionar el objeto a modificar por medio de su numero del ID
        res=ConexionBD.ActualizarMiembro(a);
        return res;
    }

    //================Consultas=================


    public ArrayList<Miembro> buscarMiembros(String filtro){
        ArrayList<Miembro> listaMiembros= new ArrayList<>();
        String sql = "SELECT * FROM Miembros";

            ResultSet rs = ConexionBD.BuscarMiembro(sql);

            try {
                rs.next();

                do {
                    int id = rs.getInt(1);//Esta columna es el ID
                    String n = rs.getString(2);
                    String Ap = rs.getString(3);
                    byte e = rs.getByte(4);
                    String AC = rs.getString(5);
                    int ca = rs.getInt(6);


                    listaMiembros.add(new Miembro(id, n, Ap, e, AC,ca));


                } while (rs.next());

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        return listaMiembros;
    }

}//clase alumnoDAO
