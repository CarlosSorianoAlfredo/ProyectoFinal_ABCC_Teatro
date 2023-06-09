package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VentanaInicio extends JFrame  implements ActionListener {

    JInternalFrame JAltasM, JBajasM, JCambiosM ,JConsultasM;
    JMenuBar menuBar;
    JMenu Miembros, Patronos;
    JMenuItem altasM,bajasM,cambiosM,consultasM;
    JMenuItem altasAf,bajasAf,cambiosAf,consultasAf;
    JTextField tfnombreM, tfApellidoMiembro;
    JComboBox<String> CBcallesM, CBcoloniasM;
    JButton btn_BuscarMiembro;
    public VentanaInicio(){
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("BASE DE DATOS DEL TEATRO");
        setSize(1300,900);
        setLocationRelativeTo(null);
        setVisible(true);

        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(225, 111, 14, 216));
        Miembros = new JMenu("MIEMBROS");
        Patronos = new JMenu("AFICIONADOS");

        //-------------MIEMBROS MENU BAR---------------
        altasM= new JMenuItem("Altas");
        altasM.setIcon(new ImageIcon("./imagenes/add.png"));
        altasM.addActionListener(this);
        bajasM= new JMenuItem("Bajas");
        bajasM.setIcon(new ImageIcon("./imagenes/Remove.png"));
        cambiosM= new JMenuItem("Cambios");
        cambiosM.setIcon(new ImageIcon("./imagenes/cambio.png"));
        consultasM= new JMenuItem("Consultas");
        consultasM.setIcon(new ImageIcon("./imagenes/buscar.png"));
        Miembros.add(altasM);
        Miembros.add(bajasM);
        Miembros.add(cambiosM);
        Miembros.add(consultasM);

        //------------AFICIONADOS MENU BAR------------------
        altasAf= new JMenuItem("Altas");
        altasAf.setIcon(new ImageIcon("./imagenes/add.png"));
        altasAf.addActionListener(this);
        bajasAf= new JMenuItem("Bajas");
        bajasAf.setIcon(new ImageIcon("./imagenes/Remove.png"));
        cambiosAf= new JMenuItem("Cambios");
        cambiosAf.setIcon(new ImageIcon("./imagenes/cambio.png"));
        consultasAf= new JMenuItem("Consultas");
        consultasAf.setIcon(new ImageIcon("./imagenes/buscar.png"));
        Patronos.add(altasAf);
        Patronos.add(bajasAf);
        Patronos.add(cambiosAf);
        Patronos.add(consultasAf);

        menuBar.add(Miembros); //AQUI SE AGREGAN TODAS LAS OPCIONES QUE ESTARAN EN EL MENUBAR
        menuBar.add(Patronos);
        setJMenuBar(menuBar);// AQUI YA SE AGREGA EL JMENU AL JSFRAME


        //==========================ALTAS_MIEMBROS=============================
        JDesktopPane desktopPane = new JDesktopPane();
        JAltasM = new JInternalFrame();
        JAltasM.getContentPane().setLayout(null);
        JAltasM.setDefaultCloseOperation(HIDE_ON_CLOSE);
        JAltasM.setTitle("DAR DE ALTA MIEMBRO");
        JAltasM.setSize(1284, 830);
        JAltasM.setClosable(true);
        JAltasM.setVisible(false);
        JAltasM.setResizable(false);
        JAltasM.setBackground(new Color(209, 246, 146, 155));
        //AltasM.setFrameIcon(new ImageIcon("./imagenes/add.png")); --------------CODIGO PARA PONER UN ICONO EN CADA FRAME


        //============COMPONENTES DENTRO DEL JFRAME=======================================
        JLabel Icono = new JLabel(new ImageIcon("./imagenes/Miembross.png"));
        Icono.setBounds(5,50, 300, 300);
        JAltasM.add(Icono);

        JLabel NombreMiembro = new JLabel("Nombre: ");
        NombreMiembro.setFont(new Font("Arial", Font.BOLD, 20));
        NombreMiembro.setBounds(325, 50, 100, 30);
        JAltasM.add(NombreMiembro);

        tfnombreM=new JTextField();
        tfnombreM.setBounds(325,90, 400, 40);
        tfnombreM.setBackground(new Color(191, 178, 246, 255));
        tfnombreM.setForeground(new Color(72, 72, 33, 255));
        tfnombreM.setFont(new Font("Arial", Font.BOLD, 14));
        JAltasM.add(tfnombreM);


        JLabel ApellidoMiembro = new JLabel("Apellido: ");
        ApellidoMiembro.setFont(new Font("Arial", Font.BOLD, 20));
        ApellidoMiembro.setBounds(325, 140, 100, 30);
        JAltasM.add(ApellidoMiembro);

        tfApellidoMiembro= new JTextField();
        tfApellidoMiembro.setBounds(325,180, 400, 40);
        tfApellidoMiembro.setBackground(new Color(191, 178, 246, 255));
        tfApellidoMiembro.setForeground(new Color(72, 72, 33, 255));
        tfApellidoMiembro.setFont(new Font("Arial", Font.BOLD, 14));
        JAltasM.add(tfApellidoMiembro);


        JLabel coloniaM = new JLabel("Colonia: ");
        coloniaM.setFont(new Font("Arial", Font.BOLD, 20));
        coloniaM.setBounds(325, 230, 100, 30);
        JAltasM.add(coloniaM);

        CBcoloniasM = new JComboBox<String>();
        CBcoloniasM.addItem("Deportivo");
        CBcoloniasM.addItem("Chisme");
        CBcoloniasM.addItem("Palmas");
        CBcoloniasM.setBounds(325,270, 400, 30);
        CBcoloniasM.setBackground(new Color(187, 178, 178));
        JAltasM.add(CBcoloniasM);

        JLabel calleM = new JLabel("Calle: ");
        calleM.setFont(new Font("Arial", Font.BOLD, 20));
        calleM.setBounds(325, 320, 100, 30);
        JAltasM.add(calleM);

        //===============================SE LLENO EL COMBO BOX DE MANERA ESTATICA, PERO SU LOGICA REAL ES QUE SE LLENE DE LA TABLA DE COLONIAS
        CBcallesM= new JComboBox<String>();
        CBcallesM.addItem("Selecciona tu calle");
        CBcallesM.addItem("Toluca");
        CBcallesM.addItem("Juan Escutia");
        CBcallesM.addItem("Lazaro Cardenas");
        CBcallesM.addItem("Reforma");
        CBcallesM.setBounds(325, 360, 400, 30);
        CBcallesM.setBackground(new Color(187, 178, 178));
        JAltasM.add(CBcallesM);

        JLabel lblConfirmarMiembro = new JLabel("CONFIRMAR");
        lblConfirmarMiembro.setFont(new Font("Arial", Font.BOLD, 13));
        lblConfirmarMiembro.setBounds(860, 90, 110, 10);
        JAltasM.add(lblConfirmarMiembro);

        btn_BuscarMiembro = new JButton(new ImageIcon("./imagenes/check.png"));
        btn_BuscarMiembro.setBackground(new Color(209, 246, 146, 155));
        btn_BuscarMiembro.setBorderPainted(false);
        btn_BuscarMiembro.setBounds(850, 110, 110, 100);
        JAltasM.add(btn_BuscarMiembro);




        desktopPane.add(JAltasM);// AQUI SE AGREGA EL JInternalFrame al Destokpane




        add(desktopPane);//AQUI SE AGREGAN TODOS LOS INTERNALFRAMES A LA VENTANA INICIO
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Login();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }//main

    @Override
    public void actionPerformed(ActionEvent e) {
        Component c=(Component) e.getSource();
        if(c==altasM){
            JAltasM.setVisible(true);
        }
    }
}//claseVentanaInicio
