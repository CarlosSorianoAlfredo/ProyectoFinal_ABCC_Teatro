package vista;

import controlador.UsuarioDAO;
import modelo.Usuario;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import ConexionBD.ConexionBD;

public class Login extends JFrame {

    JLabel lblUsuario, lblContraseña;
    JTextField jtfUsuario;
    JPasswordField jpfContraseña;
    JButton btnIngresar;
    BufferedImage imagen;
    JLabel imagen1;

    Color rojo = new Color(218, 4, 4);
    Color grisClaro = new Color(212, 212, 212);
    UsuarioDAO uDAO = new UsuarioDAO();
    boolean mostrarUsuarios = false;
    public static boolean bandera;

    public Login() throws IOException {
        getContentPane().setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 470);
        getContentPane().setBackground(new Color(104, 220, 220));
        setLocationRelativeTo(null);
        setTitle("Ingresar");
        setVisible(true);

        imagen = ImageIO.read(new File("./imagenes/login.png"));
        imagen1 = new JLabel(new ImageIcon(imagen));
        imagen1.setBounds(70, 35, 150, 150);
        add(imagen1);

        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 180, 100, 25);
        lblUsuario.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        add(lblUsuario);

        jtfUsuario = new JTextField();
        jtfUsuario.setBounds(50, 210, 185, 25);
        add(jtfUsuario);

        lblContraseña = new JLabel("Contraseña");
        lblContraseña.setBounds(50, 250, 100, 25);
        lblContraseña.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        add(lblContraseña);

        jpfContraseña = new JPasswordField();
        jpfContraseña.setBounds(50, 280, 185, 25);
        add(jpfContraseña);


        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(95, 340, 100, 35);
        btnIngresar.setBackground(rojo);
        btnIngresar.setForeground(grisClaro);
        btnIngresar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        btnIngresar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Connection a = ConexionBD.getConexion();
                if (verificar()) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if (mostrarUsuarios) {
                                new VentanaInicio();
                            }else {
                                new VentanaInicio();
                            }
                        }
                    });
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre de usuario o la contraseña son incorrectos");
                }
            }
        });
        add(btnIngresar);
    }

    public boolean verificar() {
        try {
            ArrayList<Usuario> listaUsuarios = uDAO.buscarUsuario("SELECT * FROM usuario");

            uDAO.setFiltro("SELECT * FROM usuario WHERE Usuario = '"+jtfUsuario.getText()+"'");
            Thread hilo = new Thread(uDAO);
            hilo.start();
            if (listaUsuarios.size() != 0 && bandera) {
                Usuario usuario = listaUsuarios.get(0);

                return usuario.getContraseña().equals(jpfContraseña.getText());

            } else {
                return false;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return true;
    }
}

