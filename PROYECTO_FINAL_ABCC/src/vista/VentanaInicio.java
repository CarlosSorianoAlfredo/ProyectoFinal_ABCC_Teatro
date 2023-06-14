package vista;

import controlador.MiembroDAO;
import modelo.Calle;
import modelo.Miembro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentanaInicio extends JFrame  implements ActionListener, KeyListener {

    MiembroDAO m1 = new MiembroDAO();
    Miembro M2;


    int contMiembros=m1.buscarMiembros("todos").size();
    int cont_ID=m1.buscarMiembros("todos").get(contMiembros-1).getID_Miembro();
    int cont_ID_Cambio;
    int num;

    int callesID;
    String[]  Catalogo_Colonias;
    Color btn_Vaciar_Color = new Color(246, 241, 241);
    JTable table;
    ButtonGroup grupo;
    JRadioButton SiEsActor, NoEsActor, TodosMiembros;
    String edades[];
    JLabel calleM, lblEdad, coloniaM, lblesActor, lbl_ID_Miembro, lblConfirmarMiembro, lblEliminarMiembro,lblBuscarPorAutor;
    JCheckBox EresActor;
    JInternalFrame JAltasM;
    JMenuBar menuBar;
    JMenu Miembros, Patronos;
    JMenuItem altasM,bajasM,cambiosM,consultasM;
    JMenuItem altasAf,bajasAf,cambiosAf,consultasAf;
    JTextField tfnombreM, tfApellidoMiembro, tfID,tfCons;
    JComboBox<String> CBcallesM, CBcoloniasM, CBEdad;
    JButton btn_AgregarMiembro,btn_EliminarMiembro,btn_BuscarMiembro, btn_CambiarMiembro, btn_Vaciado, btn_BuscarEliminacion, btn_Buscarcambio,btnPrim,btnAntes,btnDespues,btnUlt;
    String[] columnNames = {"ID_Miembro", "Nombre", "Apellido", "Edad", "Es_Actor", "Calle"};
    DefaultTableModel model = new DefaultTableModel(columnNames,0);
    JScrollPane scrollPane;

    public VentanaInicio(){
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("BASE DE DATOS DEL TEATRO");
        setSize(1300,900);
        setLocationRelativeTo(null);
        setVisible(true);
        LLenarCB_Colonias();


        //======LLENADO DE DATOS DE EDAD AL SPINER===============
        edades= new String[100];

        for (int i=0;i<edades.length;i++){
            edades[i]= String.valueOf(i+1);
        }


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
        bajasM.addActionListener(this);

        cambiosM= new JMenuItem("Cambios");
        cambiosM.setIcon(new ImageIcon("./imagenes/cambio.png"));
        cambiosM.addActionListener(this);

        consultasM= new JMenuItem("Consultas");
        consultasM.setIcon(new ImageIcon("./imagenes/buscar.png"));
        consultasM.addActionListener(this);


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
        JAltasM.setSize(1284, 830);
        JAltasM.setClosable(true);
        JAltasM.setVisible(false);
        JAltasM.setResizable(false);

        //AltasM.setFrameIcon(new ImageIcon("./imagenes/add.png")); --------------CODIGO PARA PONER UN ICONO EN CADA FRAME


        //============COMPONENTES DENTRO DEL JFRAME=======================================
        JLabel Icono = new JLabel(new ImageIcon("./imagenes/Miembross.png"));
        Icono.setBounds(5,50, 300, 300);
        JAltasM.add(Icono);

        JLabel tituloM = new JLabel("MIEMBROS");
        tituloM.setFont(new Font("Arial", Font.BOLD, 30));
        tituloM.setBounds(70, 30, 200, 40);
        JAltasM.add(tituloM);

        JLabel NombreMiembro = new JLabel("Nombre: ");
        NombreMiembro.setFont(new Font("Arial", Font.BOLD, 20));
        NombreMiembro.setBounds(325, 50, 100, 30);
        JAltasM.add(NombreMiembro);

        tfnombreM=new JTextField();
        tfnombreM.setBounds(325,90, 400, 40);
        tfnombreM.setBackground(new Color(191, 178, 246, 255));
        tfnombreM.setForeground(new Color(72, 72, 33, 255));
        tfnombreM.setFont(new Font("Arial", Font.BOLD, 14));
        tfnombreM.addKeyListener(this);
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
        tfApellidoMiembro.addKeyListener(this);
        JAltasM.add(tfApellidoMiembro);


        lblEdad= new JLabel("Edad: ");
        lblEdad.setFont(new Font("Arial", Font.BOLD, 20));
        lblEdad.setBounds(325, 230, 100, 30);
        JAltasM.add(lblEdad);

        CBEdad= new JComboBox<>(edades);
        CBEdad.setBounds(325,270, 400, 30);
        JAltasM.add(CBEdad);

        coloniaM = new JLabel("Colonia: ");
        coloniaM.setFont(new Font("Arial", Font.BOLD, 20));
        coloniaM.setBounds(325, 320, 100, 30);
        JAltasM.add(coloniaM);

        CBcoloniasM = new JComboBox<>(Catalogo_Colonias);
        CBcoloniasM.setBounds(325,360, 200, 30);
        CBcoloniasM.setBackground(new Color(187, 178, 178));
        CBcoloniasM.addActionListener(this);
        JAltasM.add(CBcoloniasM);

        calleM = new JLabel("Calle: ");
        calleM.setFont(new Font("Arial", Font.BOLD, 20));
        calleM.setBounds(575, 320, 100, 30);
        JAltasM.add(calleM);

        //===============================SE LLENO EL COMBO BOX DE MANERA ESTATICA, PERO SU LOGICA REAL ES QUE SE LLENE DE LA TABLA DE COLONIAS
        CBcallesM= new JComboBox<>();
        CBcallesM.addItem("Selecciona tu calle");
        CBcallesM.setBounds(575, 360, 200, 30);
        CBcallesM.setBackground(new Color(187, 178, 178));
        CBcallesM.addActionListener(this);
        JAltasM.add(CBcallesM);


        //==========================================     BOTONES (AGREGAR, ELIMINAR, BUSCAR, CAMBIAR)      ====================================

        //------------------     BOTON CONFIRMAR MIEMBRO    ----------------------
        lblConfirmarMiembro = new JLabel("CONFIRMAR");
        lblConfirmarMiembro.setFont(new Font("Arial", Font.BOLD, 13));
        lblConfirmarMiembro.setBounds(860, 90, 110, 10);
        lblConfirmarMiembro.setVisible(false);
        JAltasM.add(lblConfirmarMiembro);

        btn_AgregarMiembro = new JButton(new ImageIcon("./imagenes/check.png"));
        btn_AgregarMiembro.setBackground(new Color(209, 246, 146, 255));
        btn_AgregarMiembro.setBorderPainted(false);
        btn_AgregarMiembro.setBounds(850, 110, 110, 100);
        btn_AgregarMiembro.addActionListener(this);
        btn_AgregarMiembro.setEnabled(false);
        btn_AgregarMiembro.setVisible(false);
        JAltasM.add(btn_AgregarMiembro);

        //--------------------      BOTON ELIMINAR MIEMBRO      ----------------------------

        lblEliminarMiembro = new JLabel("ELIMINAR");
        lblEliminarMiembro.setFont(new Font("Arial", Font.BOLD, 13));
        lblEliminarMiembro.setBounds(860, 90, 110, 10);
        lblEliminarMiembro.setVisible(false);
        JAltasM.add(lblEliminarMiembro);

        btn_EliminarMiembro = new JButton(new ImageIcon("./imagenes/borrarMiembro.png"));
        btn_EliminarMiembro.setBackground(new Color(253, 58, 58, 155));
        btn_EliminarMiembro.setBorderPainted(false);
        btn_EliminarMiembro.setBounds(850, 110, 110, 100);
        btn_EliminarMiembro.addActionListener(this);
        btn_EliminarMiembro.setEnabled(false);
        btn_EliminarMiembro.setVisible(false);
        JAltasM.add(btn_EliminarMiembro);



        //--------------------      BOTON BUSCAR MIEMBRO     ----------------------------

        btn_BuscarMiembro = new JButton(new ImageIcon("./imagenes/lupaConsultas.png"));
        btn_BuscarMiembro.setBackground(new Color(120, 218, 190, 253));
        btn_BuscarMiembro.setBorderPainted(false);
        btn_BuscarMiembro.setBounds(850, 110, 110, 100);
        btn_BuscarMiembro.addActionListener(this);
        btn_BuscarMiembro.setEnabled(false);
        btn_BuscarMiembro.setVisible(false);
        JAltasM.add(btn_BuscarMiembro);


        lblBuscarPorAutor = new JLabel("BUSCAR POR:");
        lblBuscarPorAutor.setBounds(850, 30, 150, 10);
        JAltasM.add(lblBuscarPorAutor);

        grupo= new ButtonGroup();

        TodosMiembros = new JRadioButton("Todos");
        TodosMiembros.setBackground(new Color(120, 218, 190, 253));
        TodosMiembros.addActionListener(this);

        SiEsActor = new JRadioButton("Actores");
        SiEsActor.setBackground(new Color(120, 218, 190, 253));
        SiEsActor.addActionListener(this);

        NoEsActor = new JRadioButton("No Actores");
        NoEsActor.setBackground(new Color(120, 218, 190, 253));
        NoEsActor.addActionListener(this);

        grupo.add(TodosMiembros);
        grupo.add(SiEsActor);
        grupo.add(NoEsActor);
        TodosMiembros.setBounds(850, 45, 150, 15);
        SiEsActor.setBounds(850, 65, 150, 20);
        NoEsActor.setBounds(850, 90, 150, 15);
        JAltasM.add(NoEsActor);
        JAltasM.add(SiEsActor);
        JAltasM.add(TodosMiembros);
        //--------------------      BOTON CAMBIAR MIEMBRO     ----------------------------

        btn_CambiarMiembro = new JButton(new ImageIcon("./imagenes/lapiz_cambio.png"));
        btn_CambiarMiembro.setBackground(new Color(245, 190, 87, 250));
        btn_CambiarMiembro.setBorderPainted(false);
        btn_CambiarMiembro.setBounds(850, 110, 110, 100);
        btn_CambiarMiembro.addActionListener(this);
        btn_CambiarMiembro.setEnabled(false);
        btn_CambiarMiembro.setVisible(false);
        JAltasM.add(btn_CambiarMiembro);



        JLabel lblVaciarMiembro = new JLabel("LIMPIAR CAMPOS");
        lblVaciarMiembro.setFont(new Font("Arial", Font.BOLD, 13));
        lblVaciarMiembro.setBounds(1000, 90, 150, 10);
        JAltasM.add(lblVaciarMiembro);

        btn_Vaciado = new JButton(new ImageIcon("./imagenes/vaciar.png"));
        btn_Vaciado.setBorderPainted(false);
        btn_Vaciado.setBackground(btn_Vaciar_Color);
        btn_Vaciado.setBounds(1000, 110, 110, 115);
        btn_Vaciado.addActionListener(this);
        JAltasM.add(btn_Vaciado);

        lblesActor = new JLabel("Deseas ser actor?");
        lblesActor.setBounds(360,423,170,25);
        lblesActor.setFont(new Font("Arial", Font.BOLD, 18));
        JAltasM.add(lblesActor);

        EresActor = new JCheckBox();
        EresActor.setBounds(325, 420,30,30);
        EresActor.setBackground(null);
        JAltasM.add(EresActor);

        lbl_ID_Miembro = new JLabel("ID: ");
        lbl_ID_Miembro.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_ID_Miembro.setBounds(325,230, 50, 40);
        lbl_ID_Miembro.setEnabled(false);
        lbl_ID_Miembro.setVisible(false);
        JAltasM.add(lbl_ID_Miembro);

        tfID = new JTextField();
        tfID.setBounds(385,230, 150, 40);
        tfID.setBackground(new Color(191, 178, 246, 255));
        tfID.setForeground(new Color(4, 79, 218));
        tfID.setFont(new Font("Arial", Font.BOLD, 20));
        tfID.addKeyListener(this);
        JAltasM.add(tfID);

        btn_BuscarEliminacion = new JButton();
        btn_BuscarEliminacion.setIcon(new ImageIcon("./imagenes/lupa.png"));
        btn_BuscarEliminacion.setBounds(550,230, 100, 30);
        btn_BuscarEliminacion.addActionListener(this);
        JAltasM.add(btn_BuscarEliminacion);

        btn_Buscarcambio = new JButton();
        btn_Buscarcambio.setIcon(new ImageIcon("./imagenes/lupa.png"));
        btn_Buscarcambio.addActionListener(this);
        JAltasM.add(btn_Buscarcambio);


        btnPrim= new JButton("<<");
        btnPrim.setBounds(850, 230, 70, 40);
        btnPrim.addActionListener(this);
        JAltasM.add(btnPrim);

        btnAntes= new JButton("<");
        btnAntes.setBounds(925, 230, 70, 40);
        btnAntes.addActionListener(this);
        JAltasM.add(btnAntes);

        tfCons= new JTextField();
        tfCons.setBounds(1000, 230, 50, 40);
        tfCons.setText("0");
        JAltasM.add(tfCons);

        btnDespues= new JButton(">");
        btnDespues.setBounds(1055, 230, 70, 40);
        btnDespues.addActionListener(this);
        JAltasM.add(btnDespues);

        btnUlt= new JButton(">>");
        btnUlt.setBounds(1130,230,70,40);
        JAltasM.add(btnUlt);
        btnUlt.addActionListener(this);

        table = new JTable(model);
        scrollPane=  new JScrollPane(table);
        scrollPane.setBounds(75,470,1100,200);
        JAltasM.add(scrollPane);


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
        if(c==altasM||c==bajasM|| c==cambiosM||c==consultasM){
            JAltasM.setVisible(true);
            actualizarTablas(table);

            if(c==altasM){

                JAltasM.setTitle("DAR DE ALTA MIEMBRO");
                JAltasM.setBackground(new Color(209, 246, 146, 155));
                btn_Vaciar_Color=new Color(209, 246, 146, 155);
                btn_Vaciado.setBackground(btn_Vaciar_Color);
                metodoMagicoHabilitarComponentes(lblConfirmarMiembro,tfnombreM,tfApellidoMiembro,lblEdad,CBEdad,coloniaM,CBcoloniasM,calleM, CBcallesM,lblesActor,EresActor);
                btn_AgregarMiembro.setVisible(true);
                CBEdad.setVisible(true);
                CBcoloniasM.setVisible(true);

                CBcallesM.setVisible(true);
                EresActor.setVisible(true);

                tfnombreM.setEditable(true);
                tfApellidoMiembro.setEditable(true);

                lbl_ID_Miembro.setVisible(true);
                lbl_ID_Miembro.setEnabled(true);
                lbl_ID_Miembro.setBounds(860, 230, 50, 40);
                tfID.setEnabled(true);
                tfID.setForeground(Color.red);
                tfID.setVisible(true);

                tfID.setBackground(new Color(231, 241, 156, 255));
                tfID.setBounds(920,230, 70, 40);
                tfID.setEditable(false);



                metodoMagicoParaDesabilitarComponentes(btnPrim,btnUlt,btnAntes,tfCons,btnDespues,lblBuscarPorAutor,TodosMiembros,SiEsActor,NoEsActor,lblEliminarMiembro,btn_EliminarMiembro,btn_BuscarEliminacion,btn_Buscarcambio,btn_CambiarMiembro);

                btnPrim.setVisible(false);
                btnUlt.setVisible(false);
                btnAntes.setVisible(false);
                tfCons.setVisible(false);
                btnDespues.setVisible(false);

                btn_EliminarMiembro.setVisible(false);
                TodosMiembros.setVisible(false);
                SiEsActor.setVisible(false);
                NoEsActor.setVisible(false);
                btn_BuscarEliminacion.setVisible(false);
                btn_Buscarcambio.setVisible(false);
                btn_CambiarMiembro.setVisible(false);



            } else if(c==consultasM){
                JAltasM.setTitle("CONSULTAR MIEMBROS");
                JAltasM.setBackground(new Color(120, 218, 190, 253));
                btn_Vaciar_Color=new Color(120, 218, 190, 253);
                btn_Vaciado.setBackground(btn_Vaciar_Color);

                metodoMagicoHabilitarComponentes(btnPrim,btnUlt,btnAntes,btnDespues,lblBuscarPorAutor,TodosMiembros,SiEsActor,NoEsActor,lblEdad,CBEdad,coloniaM,CBcoloniasM,calleM,CBcallesM);
                btnPrim.setVisible(true);
                btnUlt.setVisible(true);
                btnAntes.setVisible(true);
                tfCons.setVisible(true);
                tfCons.setEnabled(true);
                btnDespues.setVisible(true);
                SiEsActor.setVisible(true);
                NoEsActor.setVisible(true);
                TodosMiembros.setVisible(true);
                TodosMiembros.setSelected(true);
                btn_BuscarMiembro.setVisible(true);
                btn_BuscarMiembro.setEnabled(true);
                CBEdad.setVisible(true);
                CBcoloniasM.setVisible(true);

                CBcallesM.setVisible(true);
                EresActor.setVisible(false);



                metodoMagicoParaDesabilitarComponentes(lbl_ID_Miembro,tfID,btn_Buscarcambio,lblesActor,EresActor,lblConfirmarMiembro,tfnombreM,tfApellidoMiembro,CBEdad,CBcallesM,CBcoloniasM,lblEliminarMiembro,
                        btn_EliminarMiembro,btn_BuscarEliminacion,btn_AgregarMiembro,btn_CambiarMiembro);
                btn_AgregarMiembro.setVisible(false);
                tfID.setVisible(false);
                btn_Buscarcambio.setVisible(false);
                btn_EliminarMiembro.setVisible(false);
                btn_BuscarEliminacion.setVisible(false);
                btn_CambiarMiembro.setVisible(false);

            }else if (c==cambiosM){
                JAltasM.setTitle("EDITAR MIEMBRO");
                JAltasM.setBackground(new Color(245, 190, 87, 250));
                btn_Vaciar_Color=new Color(245, 190, 87, 250);
                btn_Vaciado.setBackground(btn_Vaciar_Color);

                metodoMagicoHabilitarComponentes(lblConfirmarMiembro,lblEdad,CBEdad,coloniaM,CBcoloniasM,calleM,CBcallesM,lblesActor,EresActor);
                btn_CambiarMiembro.setVisible(true);
                CBEdad.setVisible(true);

                CBcoloniasM.setVisible(true);
                CBcallesM.setVisible(true);
                EresActor.setVisible(true);

                lbl_ID_Miembro.setVisible(true);
                lbl_ID_Miembro.setBounds(860, 230, 50, 40);
                tfnombreM.setEditable(true);
                tfApellidoMiembro.setEditable(true);
                tfID.setEnabled(true);
                tfID.setBackground(new Color(245, 190, 87, 250));
                tfID.setVisible(true);
                tfID.setEditable(true);
                tfID.setForeground(new Color(4, 79, 218));
                tfID.setBounds(920,230, 70, 40);
                btn_Buscarcambio.setVisible(true);
                btn_Buscarcambio.setEnabled(false);
                btn_Buscarcambio.setBounds(1000,230, 50, 40);

                metodoMagicoParaDesabilitarComponentes(btnPrim,btnUlt,btnAntes,tfCons,btnDespues,btn_BuscarMiembro,lblBuscarPorAutor,TodosMiembros,SiEsActor,NoEsActor,tfnombreM,tfApellidoMiembro,CBEdad,CBcallesM,CBcoloniasM,lblEliminarMiembro,
                        btn_EliminarMiembro,btn_BuscarEliminacion,btn_AgregarMiembro);
                btnPrim.setVisible(false);
                btnUlt.setVisible(false);
                btnAntes.setVisible(false);
                tfCons.setVisible(false);
                btnDespues.setVisible(false);
                btn_AgregarMiembro.setVisible(false);
                btn_EliminarMiembro.setVisible(false);
                btn_BuscarEliminacion.setVisible(false);
                btn_BuscarMiembro.setVisible(false);
                SiEsActor.setVisible(false);
                TodosMiembros.setVisible(false);
                NoEsActor.setVisible(false);


            } else if (c==bajasM) {
                JAltasM.setTitle("ELIMINAR MIEMBRO");
                JAltasM.setBackground(new Color(253, 58, 58, 155));
                btn_Vaciar_Color=new Color(253, 58, 58, 155);
                btn_Vaciado.setBackground(btn_Vaciar_Color);
                btn_AgregarMiembro.setVisible(false);
                btn_AgregarMiembro.setEnabled(false);
                lblConfirmarMiembro.setVisible(false);
                lblEliminarMiembro.setVisible(true);
                btn_EliminarMiembro.setVisible(true);

                btn_Buscarcambio.setVisible(false);
                btn_Buscarcambio.setEnabled(false);

                btn_BuscarEliminacion.setVisible(true);
                btn_BuscarEliminacion.setEnabled(false);

                tfnombreM.setEnabled(false);
                tfnombreM.setBackground(new Color(199, 196, 220, 156));
                tfApellidoMiembro.setEnabled(false);
                tfApellidoMiembro.setBackground(new Color(196, 191, 217, 156));


                btnPrim.setVisible(false);
                btnPrim.setEnabled(false);
                btnUlt.setVisible(false);
                btnUlt.setEnabled(false);
                btnAntes.setVisible(false);
                btnAntes.setEnabled(false);
                tfCons.setVisible(false);
                tfCons.setEnabled(false);
                btnDespues.setVisible(false);
                btnDespues.setEnabled(false);

                lblEdad.setVisible(false);
                CBEdad.setVisible(false);
                CBEdad.setEnabled(false);
                coloniaM.setVisible(false);
                coloniaM.setEnabled(false);
                CBcoloniasM.setVisible(false);
                CBcoloniasM.setEnabled(false);
                calleM.setVisible(false);
                calleM.setEnabled(false);
                CBcallesM.setVisible(false);
                CBcallesM.setEnabled(false);
                lblesActor.setVisible(false);
                lblesActor.setEnabled(false);
                EresActor.setVisible(false);
                EresActor.setEnabled(false);

                lblBuscarPorAutor.setVisible(false);
                SiEsActor.setVisible(false);
                TodosMiembros.setEnabled(false);
                TodosMiembros.setVisible(false);
                SiEsActor.setEnabled(false);
                NoEsActor.setVisible(false);
                NoEsActor.setEnabled(false);

                lbl_ID_Miembro.setBounds(325,230, 50, 40);
                lbl_ID_Miembro.setEnabled(true);
                lbl_ID_Miembro.setVisible(true);
                tfID.setBounds(385,230, 150, 40);
                tfID.setEditable(true);
                tfID.setEnabled(true);
                tfID.setVisible(true);
                tfID.setForeground(new Color(4, 79, 218));
                tfID.setBackground(new Color(227, 253, 58, 255));


            }


            metodoMagicoParaRestablecerComponentes(tfnombreM,tfApellidoMiembro,CBEdad, CBcallesM,CBcoloniasM, EresActor, tfID);
        } else if (c==btn_Vaciado) {
            //TodosMiembros.setSelected(true);
            metodoMagicoParaRestablecerComponentes(tfnombreM,tfApellidoMiembro,CBEdad, CBcallesM, CBcoloniasM, EresActor, tfID);
            metodoMagicoParaDesabilitarComponentes(btn_AgregarMiembro,btn_BuscarEliminacion,btn_EliminarMiembro,btn_Buscarcambio,btn_CambiarMiembro,btn_BuscarMiembro);
            if (!btn_Buscarcambio.isEnabled() && btn_Buscarcambio.isVisible()){
                metodoMagicoParaDesabilitarComponentes(tfnombreM,tfApellidoMiembro,CBEdad,CBcallesM,CBcoloniasM,lblEliminarMiembro,
                        btn_EliminarMiembro,btn_BuscarEliminacion,btn_AgregarMiembro);
            }

        } else if (c==btn_AgregarMiembro ) {
            //==========================AQUI SE HACE LA VALIDACION DE QUE TODOS LOS COMPONENTES ESTEN LLENOS======
            if(tfnombreM.getText().equals("")&&tfApellidoMiembro.getText().equals("")||tfnombreM.getText().equals("")||tfApellidoMiembro.getText().equals("")){
                btn_AgregarMiembro.setEnabled(false);
                JOptionPane.showMessageDialog(getContentPane(), "VERIFICA QUE LOS CAMPOS ESTEN LLENOS");
            } else if (tfnombreM.getText().startsWith(" ") ||tfApellidoMiembro.getText().startsWith(" ")) {
                if(tfnombreM.getText().startsWith(" ")){
                    JOptionPane.showMessageDialog(getContentPane(), "El NOMBRE no debe comenzar con espacios");
                    metodoMagicoParaRestablecerComponentes(tfnombreM);
                } else if (tfApellidoMiembro.getText().startsWith(" ")) {
                    JOptionPane.showMessageDialog(getContentPane(), "El APELLIDO no debe comenzar con espacios");
                    metodoMagicoParaRestablecerComponentes(tfApellidoMiembro);
                }

                btn_AgregarMiembro.setEnabled(false);
            } else{
                tfID.setText(String.valueOf(cont_ID+1));
                cont_ID++;
                contMiembros++;

                if (EresActor.isSelected()){
                    M2= new Miembro(cont_ID,tfnombreM.getText(),tfApellidoMiembro.getText(),(byte)(CBEdad.getSelectedIndex()+1),"si",callesID);
                    m1.agregarMiembro(M2);
                    actualizarTablas(table);
                } else if (!EresActor.isSelected()) {
                    M2= new Miembro(cont_ID,tfnombreM.getText(),tfApellidoMiembro.getText(),(byte)(CBEdad.getSelectedIndex()+1),"no",callesID);
                    m1.agregarMiembro(M2);
                    actualizarTablas(table);

                }

                //========AQUI SE HABILITA EL BOTON CUANDO TODO ESTE LLENO
            }
        } else if (c==btn_BuscarEliminacion) { //==========================AQUI SE HACE LA VALIDACION DE QUE TODOS LOS COMPONENTES ESTEN LLENOS======
            if(btn_BuscarEliminacion.isEnabled()==false){
                btn_EliminarMiembro.setEnabled(false);

            } else { //AQUI VA EL METODO DEL BOTON DE ELIMINAR
                for (int i=0; i<contMiembros; i++){
                    if(Integer.parseInt(tfID.getText())==m1.buscarMiembros("todos").get(i).getID_Miembro()){
                        tfnombreM.setBackground(new Color(196, 248, 120, 255));
                        tfApellidoMiembro.setBackground(new Color(196, 248, 120, 255));
                        tfnombreM.setEnabled(true);
                        tfApellidoMiembro.setEnabled(true);
                        tfnombreM.setEditable(false);
                        tfApellidoMiembro.setEditable(false);
                        tfnombreM.setForeground(new Color(4, 79, 218));
                        tfApellidoMiembro.setForeground(new Color(4, 79, 218));
                        tfnombreM.setFont(new Font("Arial", Font.BOLD, 20));
                        tfApellidoMiembro.setFont(new Font("Arial", Font.BOLD, 20));
                        tfnombreM.setText(m1.buscarMiembros("todos").get(i).getNombre());
                        tfApellidoMiembro.setText(m1.buscarMiembros("todos").get(i).getApellido());

                        break;
                    }

                }

                btn_EliminarMiembro.setEnabled(true);
                btn_BuscarEliminacion.setEnabled(false);
            }

        } else if (c==btn_EliminarMiembro) {

            if (contMiembros==1) {
                JOptionPane.showMessageDialog(getContentPane(), "UPS!, DEBES CONTAR CON ALMENOS 1 MIEMBRO ");
                metodoMagicoParaRestablecerComponentes(tfnombreM, tfID, tfApellidoMiembro, btn_BuscarEliminacion);
            } else if (Integer.parseInt(tfID.getText()) <= m1.buscarMiembros("todos").get(contMiembros - 1).getID_Miembro() && Integer.parseInt(tfID.getText()) > 0 && contMiembros > 1) {

                m1.eliminarMiembro(tfID.getText());
                JOptionPane.showMessageDialog(getContentPane(), "Miembro ELIMINADO ");
                metodoMagicoParaRestablecerComponentes(tfnombreM, tfID, tfApellidoMiembro, btn_BuscarEliminacion);
                contMiembros--;
                actualizarTablas(table);

            } else {
                JOptionPane.showMessageDialog(getContentPane(), "El ID ingresado no existe, verificalo nuevamente");
                btn_BuscarEliminacion.setEnabled(false);
                metodoMagicoParaRestablecerComponentes(tfnombreM, tfID, tfApellidoMiembro, btn_BuscarEliminacion);
            }

        } else if (c==btn_Buscarcambio) { //==========================AQUI SE HACE LA VALIDACION DE QUE TODOS LOS COMPONENTES ESTEN LLENOS======
            if(btn_Buscarcambio.isEnabled()==false){
                btn_CambiarMiembro.setEnabled(false);


            } else if (Integer.parseInt(tfID.getText()) <= m1.buscarMiembros("").get(m1.buscarMiembros("").size()-1).getID_Miembro()) {
                for (int k=0; k<m1.buscarMiembros("").size(); k++){
                    if(Integer.parseInt(tfID.getText()) == m1.buscarMiembros("").get(k).getID_Miembro()){
                        metodoMagicoHabilitarComponentes(tfnombreM,tfApellidoMiembro,CBEdad, CBcallesM, CBcoloniasM);
                        for (int i=0; i<contMiembros; i++){
                            if (i==contMiembros){
                                JOptionPane.showMessageDialog(getContentPane(),"ESE MIEMBRO NO EXISTE");
                            }else if(Integer.parseInt(tfID.getText())==m1.buscarMiembros("todos").get(i).getID_Miembro()){
                                cont_ID_Cambio=Integer.parseInt(tfID.getText());
                                tfnombreM.setText(m1.buscarMiembros("todos").get(i).getNombre());
                                tfApellidoMiembro.setText(m1.buscarMiembros("todos").get(i).getApellido());
                                CBEdad.setSelectedIndex(m1.buscarMiembros("todos").get(i).getEdad()-1);

                                if(m1.buscarMiembros("todos").get(i).getEs_Actor().equals("si")){
                                    EresActor.setSelected(true);


                                }else {
                                    EresActor.setSelected(false);
                                }
                                for (int j =0; j<m1.buscarCalle("").size(); j++){
                                    if(m1.buscarCalle("").get(j).getID_Calle()==m1.buscarMiembros("todos").get(i).getCalle()){
                                        CBcoloniasM.setSelectedIndex(m1.buscarCalle("").get(j).getID_Colonia());
                                        JOptionPane.showMessageDialog(getContentPane(),"COLONIA A INGRESAR EN LA CB"+m1.buscarCalle("").get(j).getID_Colonia());
                                        break;
                                    }
                                }//for

                                for(int n=0; n<CBcallesM.getItemCount(); n++){
                                    int m=m1.buscarMiembros("todos").get(i).getCalle();
                                    JOptionPane.showMessageDialog(getContentPane(),"VALOR DE (M) DEL id CALLE"+ m);
                                    String compa=m1.buscarCalle("").get(m-1).getNombre_Calle();
                                    JOptionPane.showMessageDialog(getContentPane(),"NOMBRE DE LACALLE"+ compa);

                                    if(compa.equals(CBcallesM.getItemAt(n))){
                                        CBcallesM.setSelectedIndex(n);
                                        break;
                                    }
                                } break;

                            }
                        }
                        JOptionPane.showMessageDialog(getContentPane(), cont_ID_Cambio );
                        btn_CambiarMiembro.setEnabled(true);
                        metodoMagicoParaDesabilitarComponentes(btn_AgregarMiembro,btn_EliminarMiembro, btn_BuscarMiembro);

                    }//if
                }//for
            } else {
                JOptionPane.showMessageDialog(getContentPane(), "Usuario no Existente" );
                metodoMagicoParaRestablecerComponentes(tfnombreM,tfApellidoMiembro,CBEdad, CBcallesM, CBcoloniasM, EresActor, tfID);
            }

        } else if (c==btn_CambiarMiembro) {
            metodoMagicoParaDesabilitarComponentes(btn_AgregarMiembro,btn_EliminarMiembro, btn_BuscarMiembro);

            if (EresActor.isSelected()){
                M2= new Miembro(cont_ID_Cambio,tfnombreM.getText(),tfApellidoMiembro.getText(),(byte)(CBEdad.getSelectedIndex()+1),"si",callesID);
                if (!tfnombreM.getText().equals("") && !tfApellidoMiembro.getText().equals("") ){
                    m1.actualizarMiembro(M2);
                }else{
                    JOptionPane.showMessageDialog(getContentPane(), "NOmbre vacio" );
                }
                actualizarTablas(table);

            } else if (!EresActor.isSelected()) {
                M2= new Miembro(cont_ID_Cambio,tfnombreM.getText(),tfApellidoMiembro.getText(),(byte)(CBEdad.getSelectedIndex()+1),"no",callesID);
                if (!tfnombreM.getText().equals("") && !tfApellidoMiembro.getText().equals("") ){
                    m1.actualizarMiembro(M2);
                }else{
                    JOptionPane.showMessageDialog(getContentPane(), "NOmbre vacio" );
                }

                actualizarTablas(table);

            }
        } else if (CBcoloniasM.getSelectedIndex()>=0 && c==CBcoloniasM) {
            int pos=CBcoloniasM.getSelectedIndex();
            CBcallesM.removeAllItems();
            LlenarCB_Calles(pos);
        } else if (CBcallesM.getSelectedIndex()>0) {
            if(!tfnombreM.getText().equals("") && !tfApellidoMiembro.getText().equals("")&& CBcoloniasM.getSelectedIndex()!=0){
                btn_AgregarMiembro.setEnabled(true);

            }

            for (int i=0;i<m1.buscarCalle("").size(); i++){
                if(CBcallesM.getSelectedItem().equals(m1.buscarCalle("").get(i).getNombre_Calle())){
                    callesID= m1.buscarCalle("").get(i).getID_Calle();
                    break;
                }
            }
        }else if (btn_BuscarMiembro == c) {
            metodoMagicoHabilitarComponentes(tfnombreM,tfApellidoMiembro,CBEdad,CBcoloniasM,CBcallesM);
            tfnombreM.setText(m1.buscarMiembros("todos").get(0).getNombre());
            tfApellidoMiembro.setText(m1.buscarMiembros("todos").get(0).getApellido());
            CBEdad.setSelectedIndex(m1.buscarMiembros("todos").get(0).getEdad()-1);
            //=============

            for (int j = 0; j < m1.buscarCalle("").size(); j++) {
                if (m1.buscarCalle("").get(j).getID_Calle() == m1.buscarMiembros("todos").get(num).getCalle()) {
                    CBcoloniasM.setSelectedIndex(m1.buscarCalle("").get(j).getID_Colonia());

                }
            }//for

            for (int n = 0; n < CBcallesM.getItemCount(); n++) {
                int m = m1.buscarMiembros("todos").get(num).getCalle();
                String compa = m1.buscarCalle("").get(m - 1).getNombre_Calle();

                if (compa.equals(CBcallesM.getItemAt(n))) {
                    CBcallesM.setSelectedIndex(n);

                }
            }

            tfCons.setText("1");

        }else if (c==btnPrim) {
            metodoMagicoParaRestablecerComponentes(tfnombreM,tfApellidoMiembro,CBEdad, CBcallesM, CBcoloniasM);
            tfnombreM.setText(m1.buscarMiembros("todos").get(0).getNombre());
            tfApellidoMiembro.setText(m1.buscarMiembros("todos").get(0).getApellido());
            CBEdad.setSelectedIndex(m1.buscarMiembros("todos").get(0).getEdad()-1);

            for (int j = 0; j < m1.buscarCalle("").size(); j++) {
                if (m1.buscarCalle("").get(j).getID_Calle() == m1.buscarMiembros("todos").get(num).getCalle()) {
                    CBcoloniasM.setSelectedIndex(m1.buscarCalle("").get(j).getID_Colonia());

                }
            }//for

            for (int n = 0; n < CBcallesM.getItemCount(); n++) {
                int m = m1.buscarMiembros("todos").get(num).getCalle();
                String compa = m1.buscarCalle("").get(m - 1).getNombre_Calle();

                if (compa.equals(CBcallesM.getItemAt(n))) {
                    CBcallesM.setSelectedIndex(n);

                }
            }
            tfCons.setText("1");
        }else if(c==btnDespues){
            metodoMagicoParaRestablecerComponentes(tfnombreM,tfApellidoMiembro,CBEdad, CBcallesM, CBcoloniasM);
            if(Integer.parseInt(tfCons.getText())>=m1.buscarMiembros("todos").size()){

            }else {
                tfnombreM.setText(m1.buscarMiembros("todos").get((Integer.parseInt(tfCons.getText()))).getNombre());
                tfApellidoMiembro.setText(m1.buscarMiembros("todos").get((Integer.parseInt(tfCons.getText()))).getApellido());
                CBEdad.setSelectedIndex(m1.buscarMiembros("todos").get((Integer.parseInt(tfCons.getText()))).getEdad()-1);
                tfCons.setText(String.valueOf((Integer.parseInt(tfCons.getText()))+1));

                for (int j = 0; j < m1.buscarCalle("").size(); j++) {
                    if (m1.buscarCalle("").get(j).getID_Calle() == m1.buscarMiembros("todos").get(num).getCalle()) {
                        CBcoloniasM.setSelectedIndex(m1.buscarCalle("").get(j).getID_Colonia());

                    }
                }//for

                for (int n = 0; n < CBcallesM.getItemCount(); n++) {
                    int m = m1.buscarMiembros("todos").get(num).getCalle();
                    String compa = m1.buscarCalle("").get(m-1).getNombre_Calle();

                    if (compa.equals(CBcallesM.getItemAt(n))) {
                        CBcallesM.setSelectedIndex(n);

                    }
                }

            }
        } else if (c==btnAntes) {
            metodoMagicoParaRestablecerComponentes(tfnombreM,tfApellidoMiembro,CBEdad, CBcallesM, CBcoloniasM);
            if(Integer.parseInt(tfCons.getText())<=1){
            }else {
                tfCons.setText(String.valueOf(Integer.parseInt(tfCons.getText())-1));

                tfnombreM.setText(m1.buscarMiembros("todos").get(((Integer.parseInt((tfCons.getText()))))-1).getNombre());
                tfApellidoMiembro.setText(m1.buscarMiembros("todos").get(((Integer.parseInt((tfCons.getText()))))-1).getApellido());
                CBEdad.setSelectedIndex(m1.buscarMiembros("todos").get(((Integer.parseInt((tfCons.getText()))))-1).getEdad()-1);

                for (int j = 0; j < m1.buscarCalle("").size(); j++) {
                    if (m1.buscarCalle("").get(j).getID_Calle() == m1.buscarMiembros("todos").get(num).getCalle()) {
                        CBcoloniasM.setSelectedIndex(m1.buscarCalle("").get(j).getID_Colonia());

                    }
                }//for
                for (int n = 0; n < CBcallesM.getItemCount(); n++) {
                    int m = m1.buscarMiembros("todos").get(num).getCalle();
                    String compa = m1.buscarCalle("").get(m - 1).getNombre_Calle();
                    if (compa.equals(CBcallesM.getItemAt(n))) {
                        CBcallesM.setSelectedIndex(n);

                    }
                }

            }


        } else if (c==btnUlt) {
            metodoMagicoParaRestablecerComponentes(tfnombreM,tfApellidoMiembro,CBEdad, CBcallesM, CBcoloniasM);
            tfnombreM.setText(m1.buscarMiembros("todos").get(m1.buscarMiembros("todos").size()-1).getNombre());
            tfApellidoMiembro.setText(m1.buscarMiembros("todos").get(m1.buscarMiembros("todos").size()-1).getApellido());
            CBEdad.setSelectedIndex(m1.buscarMiembros("todos").get(m1.buscarMiembros("todos").size()-1).getEdad()-1);

            for (int j = 0; j < m1.buscarCalle("").size(); j++) {
                if (m1.buscarCalle("").get(j).getID_Calle() == m1.buscarMiembros("todos").get(num).getCalle()) {
                    CBcoloniasM.setSelectedIndex(m1.buscarCalle("").get(j).getID_Colonia());

                }
            }//for
            for (int n = 0; n < CBcallesM.getItemCount(); n++) {
                int m = m1.buscarMiembros("todos").get(num).getCalle();
                String compa = m1.buscarCalle("").get(m - 1).getNombre_Calle();
                if (compa.equals(CBcallesM.getItemAt(n))) {
                    CBcallesM.setSelectedIndex(n);

                }
            }

            //==============
            tfCons.setText(String.valueOf(m1.buscarMiembros("todos").size()));
        }

    }//ActionPerformed






    public void actualizarTablas(JTable tabla){
        String controlador="com.mysql.cj.jdbc.Driver";
        String URL= "jdbc:mysql://localhost:3306/Empresa_Teatro";
        String consulta="SELECT * FROM Miembros";
        ResultSetTableModel modeloTabla=null;
        try {
            modeloTabla = new ResultSetTableModel(controlador,URL,consulta);
        }  catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tabla.setModel(modeloTabla);
    }
    public void metodoMagicoParaRestablecerComponentes(JComponent ... compo){
        for (JComponent x: compo) {
            if(x instanceof JTextField){
                ((JTextField)x).setText("");
            }else if(x instanceof JComboBox){
                ((JComboBox)x).setSelectedIndex(0);
            }else if(x instanceof SpinnerModel){
                ((SpinnerModel)x).setValue(0);
            } else if (x instanceof JCheckBox) {
                ((JCheckBox)x).setSelected(false);
            }
        }
    }//metodoParaVaciado

    public void metodoMagicoParaDesabilitarComponentes(JComponent ... compo){
        for (JComponent x: compo) {
            if(x instanceof JTextField){
                ((JTextField)x).setEnabled(false);
            }else if(x instanceof JComboBox){
                ((JComboBox)x).setEnabled(false);
            }else if (x instanceof JCheckBox) {
                ((JCheckBox)x).setEnabled(false);
            }else if (x instanceof JButton) {
                ((JButton)x).setEnabled(false);
            }else if (x instanceof JLabel) {
                ((JLabel)x).setVisible(false);
            }else if (x instanceof JRadioButton) {
                ((JRadioButton)x).setEnabled(false);
            }
        }
    }

    public void metodoMagicoHabilitarComponentes(JComponent ... compo){
        for (JComponent x: compo) {
            if(x instanceof JTextField){
                ((JTextField)x).setEnabled(true);
            }else if(x instanceof JComboBox){
                ((JComboBox)x).setEnabled(true);
            }else if (x instanceof JCheckBox) {
                ((JCheckBox)x).setEnabled(true);
            }else if (x instanceof JButton) {
                ((JButton)x).setEnabled(true);
            } else if (x instanceof JLabel) {
                ((JLabel)x).setVisible(true);
            }else if (x instanceof JRadioButton) {
                ((JRadioButton)x).setEnabled(true);
            }
        }
    }



    public void LLenarCB_Colonias(){
        int tamaño=m1.buscarColonia("").size();
        Catalogo_Colonias = new String[tamaño+1];
        Catalogo_Colonias[0]="Seleccione su colonia";
        for (int i=1; i<tamaño+1; i++){
            Catalogo_Colonias[i]=m1.buscarColonia("").get(i-1).getNombre_Colonia();
        }

    }

    public void LlenarCB_Calles(int pos) {
        CBcallesM.addItem("Selecciona tu calle");
        if (pos == 0) {

        } else {
            int tamaño1 = m1.buscarCalle("").size();
            for (int i = 0; i < tamaño1; i++) {
                if (CBcoloniasM.getSelectedIndex() == m1.buscarCalle("").get(i).getID_Colonia()) {
                    CBcallesM.addItem(m1.buscarCalle("").get(i).getNombre_Calle());
                }

            }//for

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        Component c=(Component) e.getSource();
        if (c== tfnombreM || c==tfApellidoMiembro){
            char car= e.getKeyChar();
            if(!(car>64&&car<91||car>96&&car<123||car==32)) {
                e.consume();
            }/*else if(CBcoloniasM.getSelectedIndex()>0&&CBcallesM.getSelectedIndex()>0){
                btn_AgregarMiembro.setEnabled(true);

            }*/
        } else if (c== tfID) {
            char car= e.getKeyChar();
            if(!(car>47&&car<58)) {
                e.consume();
            }else {
                btn_BuscarEliminacion.setEnabled(true);
                btn_Buscarcambio.setEnabled(true);

            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Component c=(Component) e.getSource();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}//claseVentanaInicio
