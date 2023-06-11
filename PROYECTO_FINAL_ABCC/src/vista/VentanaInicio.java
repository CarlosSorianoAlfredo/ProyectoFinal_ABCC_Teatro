package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class VentanaInicio extends JFrame  implements ActionListener, KeyListener {

    int cont_ID=0;
    Color btn_Vaciar_Color = new Color(246, 241, 241);
    JTable table;
    String edades[];
    JLabel calleM, lblEdad, coloniaM, lblesActor, lbl_ID_Miembro, lblConfirmarMiembro, lblEliminarMiembro;
    JCheckBox EresActor;
    JInternalFrame JAltasM, JBajasM, JCambiosM ,JConsultasM;
    JMenuBar menuBar;
    JMenu Miembros, Patronos;
    JMenuItem altasM,bajasM,cambiosM,consultasM;
    JMenuItem altasAf,bajasAf,cambiosAf,consultasAf;
    JTextField tfnombreM, tfApellidoMiembro, tfID;
    JComboBox<String> CBcallesM, CBcoloniasM, CBEdad;
    JButton btn_AgregarMiembro,btn_EliminarMiembro,btn_BuscarMiembro, btn_CambiarMiembro, btn_Vaciado, btn_BuscarEliminacion, btn_Buscarcambio;
    String[] columnNames = {"No. De Control", "Nombre", "Edad", "Apellido Paterno", "Apellido Materno", "Semestre", "Carrera"};
    DefaultTableModel model = new DefaultTableModel(columnNames,0);
    JScrollPane scrollPane;

    public VentanaInicio(){
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("BASE DE DATOS DEL TEATRO");
        setSize(1300,900);
        setLocationRelativeTo(null);
        setVisible(true);

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

        CBcoloniasM = new JComboBox<String>();
        CBcoloniasM.addItem("Deportivo");
        CBcoloniasM.addItem("Chisme");
        CBcoloniasM.addItem("Palmas");
        CBcoloniasM.setBounds(325,360, 200, 30);
        CBcoloniasM.setBackground(new Color(187, 178, 178));
        CBcoloniasM.addActionListener(this);
        JAltasM.add(CBcoloniasM);

        calleM = new JLabel("Calle: ");
        calleM.setFont(new Font("Arial", Font.BOLD, 20));
        calleM.setBounds(575, 320, 100, 30);
        JAltasM.add(calleM);

        //===============================SE LLENO EL COMBO BOX DE MANERA ESTATICA, PERO SU LOGICA REAL ES QUE SE LLENE DE LA TABLA DE COLONIAS
        CBcallesM= new JComboBox<String>();
        CBcallesM.addItem("Toluca");
        CBcallesM.addItem("Juan Escutia");
        CBcallesM.addItem("Lazaro Cardenas");
        CBcallesM.addItem("Reforma");
        CBcallesM.setBounds(575, 360, 200, 30);
        CBcallesM.setBackground(new Color(187, 178, 178));
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
        tfID.setForeground(new Color(72, 72, 33, 255));
        tfID.setFont(new Font("Arial", Font.BOLD, 14));
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
        if(c==altasM||c==bajasM|| c==cambiosM){
            JAltasM.setVisible(true);

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

                metodoMagicoParaDesabilitarComponentes(lblEliminarMiembro,btn_EliminarMiembro,btn_BuscarEliminacion,lbl_ID_Miembro,tfID,btn_Buscarcambio,btn_CambiarMiembro);
                btn_EliminarMiembro.setVisible(false);
                btn_BuscarEliminacion.setVisible(false);
                tfID.setVisible(false);
                btn_Buscarcambio.setVisible(false);
                btn_CambiarMiembro.setVisible(false);



            } else if (c==cambiosM){
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
                tfID.setEnabled(true);
                tfID.setVisible(true);
                tfID.setBounds(920,230, 70, 40);
                btn_Buscarcambio.setVisible(true);
                btn_Buscarcambio.setEnabled(false);
                btn_Buscarcambio.setBounds(1000,230, 50, 40);

                metodoMagicoParaDesabilitarComponentes(tfnombreM,tfApellidoMiembro,CBEdad,CBcallesM,CBcoloniasM,lblEliminarMiembro,
                        btn_EliminarMiembro,btn_BuscarEliminacion,btn_AgregarMiembro);
                btn_AgregarMiembro.setVisible(false);
                btn_EliminarMiembro.setVisible(false);
                btn_BuscarEliminacion.setVisible(false);


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

                lbl_ID_Miembro.setEnabled(true);
                lbl_ID_Miembro.setVisible(true);
                lbl_ID_Miembro.setBounds(325,230, 50, 40);
                tfID.setEnabled(true);
                tfID.setVisible(true);
                tfID.setBounds(385,230, 150, 40);

            }


            metodoMagicoParaRestablecerComponentes(tfnombreM,tfApellidoMiembro,CBEdad, CBcallesM, CBcoloniasM, EresActor, tfID);
        } else if (c==btn_Vaciado) {
            metodoMagicoParaRestablecerComponentes(tfnombreM,tfApellidoMiembro,CBEdad, CBcallesM, CBcoloniasM, EresActor, tfID);
            metodoMagicoParaDesabilitarComponentes(btn_AgregarMiembro,btn_BuscarEliminacion,btn_EliminarMiembro,btn_Buscarcambio,btn_CambiarMiembro);
            if (!btn_Buscarcambio.isEnabled() && btn_Buscarcambio.isVisible()){
                metodoMagicoParaDesabilitarComponentes(tfnombreM,tfApellidoMiembro,CBEdad,CBcallesM,CBcoloniasM,lblEliminarMiembro,
                        btn_EliminarMiembro,btn_BuscarEliminacion,btn_AgregarMiembro);
            }

        } else if (c==btn_AgregarMiembro) { //==========================AQUI SE HACE LA VALIDACION DE QUE TODOS LOS COMPONENTES ESTEN LLENOS======
            if(tfnombreM.getText().equals("")||tfApellidoMiembro.getText().equals("")){
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
                JOptionPane.showMessageDialog(getContentPane(), "MSJ DEL BTN AGREGAR");
                btn_AgregarMiembro.setEnabled(true);
                //========AQUI SE HABILITA EL BOTON CUANDO TODO ESTE LLENO
            }
        } else if (c==btn_BuscarEliminacion) { //==========================AQUI SE HACE LA VALIDACION DE QUE TODOS LOS COMPONENTES ESTEN LLENOS======
            if(btn_BuscarEliminacion.isEnabled()==false){
                btn_EliminarMiembro.setEnabled(false);

            } else { //AQUI VA EL METODO DEL BOTON DE ELIMINAR
                btn_EliminarMiembro.setEnabled(true);
            }

        }else if (c==btn_Buscarcambio) { //==========================AQUI SE HACE LA VALIDACION DE QUE TODOS LOS COMPONENTES ESTEN LLENOS======
            if(btn_Buscarcambio.isEnabled()==false){
                btn_CambiarMiembro.setEnabled(false);


            } else { //AQUI VA EL METODO DEL BOTON DE ELIMINAR
                metodoMagicoHabilitarComponentes(tfnombreM,tfApellidoMiembro,CBEdad, CBcallesM, CBcoloniasM);
                btn_CambiarMiembro.setEnabled(true);
            }

        }
    }//ActionPerformed


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
            }
        }
    }
    public void metodoMagicoDesabilitarComponentes(JComponent ... compo){
        for (JComponent x: compo) {
            if(x instanceof JTextField){
                ((JTextField)x).setEnabled(true);
            }else if(x instanceof JComboBox){
                ((JComboBox)x).setEnabled(true);
            }else if (x instanceof JCheckBox) {
                ((JCheckBox)x).setEnabled(true);
            }else if (x instanceof JButton) {
                ((JButton)x).setEnabled(true);
            }else if (x instanceof JLabel) {
                ((JLabel)x).setVisible(false);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        Component c=(Component) e.getSource();
        if (c== tfnombreM || c==tfApellidoMiembro){
            char car= e.getKeyChar();
            if(!(car>64&&car<91||car>96&&car<123||car==32)) {
                e.consume();
            }else {
                btn_AgregarMiembro.setEnabled(true);

            }
        } else if (c== tfID) {
            char car= e.getKeyChar();
            if(!(car>48&&car<58)) {
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
