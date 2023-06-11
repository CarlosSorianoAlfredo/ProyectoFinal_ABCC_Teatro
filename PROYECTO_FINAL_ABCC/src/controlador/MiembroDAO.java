package controlador;

import ConexionBD.ConexionBD;
import modelo.Miembro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MiembroDAO {
    private int contadorAlumnos=0;

    public int getContadorAlumnos() {
        return contadorAlumnos;
    }

    public void setContadorAlumnos(int contadorAlumnos) {
        this.contadorAlumnos = contadorAlumnos;
    }

    ConexionBD conexion = new ConexionBD();
    //=============Metodos ABCC (CRUD)================

    //================Altas=====================
    public boolean agregarAlumno(Miembro a){
        boolean res= false;
        /*
        PROCESO PARA ALTAS EN MySQL

            insert into Alumnos values('01',"Dominic", "Toreto", 'Garcia', 43, 8, "ISC");

         */
        //==============================  CREAR LA TABLA EN SQL PARA PODER ACOMODAR LOS DATOS DE ESTA INSTRUCCION    =============
        String sql= "insert into Miembros values('"+a.getNumControl()+"','"+a.getNombre()+"', '"+a.getPrimerAp()+"', '"+a.getSegundoAp()+"','"+a.getEdad()+"', '"+a.getSemestre()+"', '"+a.getCarrera()+"')";
        res= conexion.ejecutarInstruccionDML(sql);
        return res;
    }


    //================Bajas=====================
    public boolean eliminarAlumno(String numControl){
        boolean res = false;

        //   DELETE from Alumnos where NumControl = '01';
        String sql= "DELETE from Alumnos where NumControl = '"+numControl+"'";

        res = conexion.ejecutarInstruccionDML(sql);

        return res;
    }

    //================Cambios===================
    public boolean actualizarAlumno(Miembro a){
        boolean res = false;
        //UPDATE Alumnos SET Nombre='x', PrimerAp='x', SegundoAp='x', Edad=10, Semestre=2, Carrera='x', WHERE NumControl = '"a.getNumcontrol"'";
        String sql="UPDATE Alumnos SET Nombre='"+a.getNombre() +
                "', PrimerAp='"+a.getPrimerAp() +
                "', SegundoAp='"+a.getSegundoAp() +
                "', Edad="+a.getEdad() +
                ", Semestre="+a.getSemestre() +
                ", Carrera='"+a.getCarrera() +
                "' WHERE NumControl = '"+a.getNumControl()+"'"; //Esta linea es para poder seleccionar el objeto a modificar por medio de su numero de control
        res=conexion.ejecutarInstruccionDML(sql);
        return res;
    }

    //================Consultas=================
    public Miembro buscarAlumno(String filtro){

        return null;
    }

    public ArrayList<Miembro> buscarAlumnos(String filtro){
        ArrayList<Miembro> listaAlumnos= new ArrayList<>();
        contadorAlumnos=0;
        String sql = "SELECT * FROM Alumnos";

        ResultSet rs =conexion.ejecutarConsultaSQL(sql);

        try {
            rs.next();

            do {
                String nc = rs.getString(1);//Esta columna es la primera
                String n = rs.getString("Nombre");
                String pa = rs.getString(3);
                String sa = rs.getString(4);
                byte e = rs.getByte(5);
                byte s = rs.getByte(6);
                String c = rs.getString(7);

                listaAlumnos.add(new Miembro(nc, n, pa, sa, e, s, c));
                contadorAlumnos++;

            }while(rs.next());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaAlumnos;
    }

}//clase alumnoDAO
