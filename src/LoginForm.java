import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;


public class LoginForm extends JDialog{
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnIngresar;
    private JButton btnRegistrar;
    private JPanel loginPanel;

    //construtor//
    public LoginForm(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(500,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //BOTON INGRESAR//
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());
                DasboardUsers db = new DasboardUsers();
                db.show();
                setModal(true);
                users = getAuthenticatedUser(email, password);

                if (users != null) {

                } else {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Email o Password Incorrecta",
                            "Intente De Nuevo",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //BOTON REGISTRAR//
        btnRegistrar.addMouseListener(new MouseAdapter() {
            @Override
            //metodo para mostrar form de regsitro al presionar el boton registrar
            public void mouseClicked(MouseEvent e) {
                RegistrationForm rf = new RegistrationForm(parent);
                rf.show();

            }
        }); setVisible(true);
    }



    private boolean connectToDatabase() {

        boolean hasRegistredUsers = false;
        final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
        final String USERNAME = "C##STARLIN";
        final String PASSWORD = "12345";
        try {

            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABE IF NO EXISTS starlindba");
            statement.close();
            conn.close();

            //second connect to the databe and create the table users
          /*  conn = DriverManager.getConnection(DB_URL,USERNAME ,PASSWORD);
            statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS USUARIOS("
                    + "USUARIO VARCHAR2(2500) NOT NULL PRIMARY KEY,"
                    + "NOMBRE VARCHAR2(2500) NOT NULL,"
                    + "APELLIDO VARCHAR2(2500) NOT NULL,"
                    + "TELEFONO VARCHAR2(2500),"
                    + "EMAIL VARCHAR2(2500),"
                    + "PASSWORD VARCHAR2(2500)NOT NULL"
                    + "CONFIRMPASSWORD VARCHAR2(2500)NOT NULL"
                    +")";
            statement.executeUpdate(sql);*/
            //check if we have user in the table
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM USUARIOS");
            if (resultSet.next()){
                int numUsers = resultSet.getInt(1);
                if (numUsers>0){
                    hasRegistredUsers = true;
                }
            }
            statement.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return hasRegistredUsers;
    }


    //metodo para verificar usuarios//
    public Users users ;
    private Users getAuthenticatedUser(String email, String password) {
    Users users = null;
        final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
        final String USERNAME = "C##STARLIN";
        final String PASSWORD = "12345";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM usuarios WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                users = new Users();
                users.NOMBRE = resultSet.getString("nombre");
                users.APELLIDO = resultSet.getString("apellido");
                users.TELEFONO = resultSet.getString("telefono");
                users.EMAIl = resultSet.getString("email");
                users.PASSWORD = resultSet.getString("password");
                users.CONFIRMPASSWORD = resultSet.getString("confirmpassword");
            }
            stm.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return users;


    }

    //main metodo//
    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
        Users users = loginForm.users;
        DasboardUsers db = new DasboardUsers();
        db.show();
        if(users != null){

            System.out.println("Correcta autentificacion de: " + users.NOMBRE);
            System.out.printf("         Apellido: " + users.APELLIDO);
            System.out.println("        Telefono: " + users.TELEFONO);
            System.out.println("        Email: " +    users.EMAIl );
            System.out.println("        Password: " + users.PASSWORD);
            System.out.println("    ConfirmPassword: " + users.CONFIRMPASSWORD);
        }

   }
}
