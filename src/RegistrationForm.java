import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegistrationForm extends JDialog {
    private static   JFrame parent;
    private JTextField tfNameUser;
    private JTextField tfNombre;
    private JTextField tfApellido;
    private JTextField tfTelefono;
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JButton btnRegistrar;
    private JButton btnCancelar;
    public JPanel registerpanel;
    private JPanel JPanelRegister;


    //metodo de registrar usuarios//
    public RegistrationForm(JFrame parent) {
            super(parent);
            setTitle("Crear Una Nueva Cuenta");
            setContentPane(JPanelRegister);
            setMinimumSize(new Dimension(450, 474));
            setModal(true);
            setLocationRelativeTo(parent);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            //este metodo va registrar nuevos usuarios cuando ellos presionen el boton registrar//
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        //metodo cancelar usuario para no registrar ningun user y cerrar la app//
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }


    //metodo registra usuarios//
    private void registerUser() {
       // int IDUSUARIO = tfIdusuario.getComponent(tfIdusuario);//
        String USUARIO = tfNameUser.getText();
        String NOMBRE = tfNombre.getText();
        String APELLIDO = tfApellido.getText();
        String TELEFONO = tfTelefono.getText();
        String EMAIL = tfEmail.getText();
        String PASSWORD = String.valueOf(pfPassword.getPassword());
        String CONFIRMPASSWORD = String.valueOf(pfConfirmPassword);
        if (USUARIO.isEmpty() || NOMBRE.isEmpty() || APELLIDO.isEmpty() || TELEFONO.isEmpty() || EMAIL.isEmpty() || PASSWORD.isEmpty() || CONFIRMPASSWORD.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "POR FAVOR LLENAR TODOS LOS CAMPOS",
                    "INTENTE DE NUEVO",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        //ESTE METODO NOS PERMITIRA AGREGAR USUARIOS A LA BASE DE DATOS
    users = addUserToDatabase(USUARIO, NOMBRE, APELLIDO, TELEFONO, EMAIL, PASSWORD, CONFIRMPASSWORD);
            if (users != null){
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this,
                        "fallo al registrar usuario",
                        "INTENTE DE NUEVO",
                        JOptionPane.ERROR_MESSAGE);

            }
        }

        //este metodo nos devolvera un objeto a la base de datos un usuario registrado//

    public Users users;
    public Users addUserToDatabase(String usuario, String nombre, String apellido, String telefono, String email, String password, String confirmpassword) {
        Users users = null;
        final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
        final String USERNAME = "C##STARLIN";
        final String PASSWORD = "12345";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //conexion a la base de datos//
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO USUARIOS (usuario, nombre, apellido, telefono, email, password, confirmpassword) "
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setString(4, telefono);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, confirmpassword);


            //INSERT COLUMNAS EN LA BASE DE DATOS TABLAS//
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                users = new Users();
                users.USUARIO = usuario;
                users.NOMBRE = nombre;
                users.APELLIDO = apellido;
                users.TELEFONO = telefono;
                users.EMAIl = email;
                users.PASSWORD = password;
                users.CONFIRMPASSWORD = confirmpassword;


            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    //pondremos ahora para test la app el metodo main//
        public static void main (String[]args){
            RegistrationForm myform = new RegistrationForm(null);
            Users users = myform.users;
           //creacion del objeto para llamar a este form en la ventana del boton registro
            LoginForm lf = new LoginForm(parent);
            myform.setSize(680,750);
           lf.show();
            if (users != null){
                System.out.println("usuario registrado correctamente: " + users.NOMBRE);
            }
            else {
                System.out.println("cancelar registro");
            }
        }

    }
