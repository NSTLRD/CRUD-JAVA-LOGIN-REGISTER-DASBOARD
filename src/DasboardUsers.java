import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DasboardUsers extends JFrame {
    private JPanel rootpanel;
    private JButton btnAgregar;
    private JButton btnModificar;
    private JTable table1;
    private JPasswordField txtpassword;
    private JPasswordField txtconfirmpass;
    private JTextField textUSUARIO;
    private JTextField txtEMAIL;
    private JTextField textTELEFONO;
    private JTextField textAPELLIDO;
    private JTextField textNOMBRE;
    private JButton ELIMINARButton;
    private JButton CERRARSESIONButton;
    private JScrollPane tb2;
    private UsuarioBO ebo = new UsuarioBO();

    private String USUARIO;
    private String NOMBRE;
    private String APELLIDO;
    private String TELEFONO;
    private String EMAIL;
    private String PASSWORD;
    private String CONFIRMPASSWORD;

    public DasboardUsers() {
        setTitle("DASBOARDUSERS");
        setContentPane(rootpanel);
        setMinimumSize(new Dimension(500, 500));
        setSize(900, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        boolean hasRegistredUsers = connectTODatabase();
        if (hasRegistredUsers) {
            //show login form//
            LoginForm loginForm = new LoginForm(this);
            Users users = loginForm.users;

        }


        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textUSUARIO.getText().isEmpty() || textNOMBRE.getText().isEmpty() || textAPELLIDO.getText().isEmpty()
                        || textTELEFONO.getText().isEmpty() || txtEMAIL.getText().isEmpty()
                        || txtpassword.getText().isEmpty() || txtconfirmpass.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Llene todos los campos");
                }
                Users usr = new Users();
                usr.setUSUARIO(textUSUARIO.getText());
                usr.setNOMBRE(textNOMBRE.getText());
                usr.setAPELLIDO(textAPELLIDO.getText());
                usr.setTELEFONO(textTELEFONO.getText());
                usr.setEMAIl(txtEMAIL.getText());
                usr.setPASSWORD(txtpassword.getText());
                usr.setCONFIRMPASSWORD(txtconfirmpass.getText());
                String mensaje = ebo.nuevouser(usr);
                JOptionPane.showMessageDialog(null, mensaje);
                limpiar();

            }
        });
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textUSUARIO.getText().isEmpty() || textNOMBRE.getText().isEmpty() || textAPELLIDO.getText().isEmpty()
                        || textTELEFONO.getText().isEmpty() || txtEMAIL.getText().isEmpty()
                        || txtpassword.getText().isEmpty() || txtconfirmpass.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Llene todos los campos");
                }
                Users usr = new Users();
                usr.setUSUARIO(textUSUARIO.getText());
                usr.setNOMBRE(textNOMBRE.getText());
                usr.setAPELLIDO(textAPELLIDO.getText());
                usr.setTELEFONO(textTELEFONO.getText());
                usr.setEMAIl(txtEMAIL.getText());
                usr.setPASSWORD(txtpassword.getText());
                usr.setCONFIRMPASSWORD(txtconfirmpass.getText());
                String mensaje = ebo.nuevouser(usr);
                JOptionPane.showMessageDialog(null, mensaje);
                limpiar();

            }
        });
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textUSUARIO.getText().isEmpty() || textNOMBRE.getText().isEmpty() || textAPELLIDO.getText().isEmpty()
                        || textTELEFONO.getText().isEmpty() || txtEMAIL.getText().isEmpty()
                        || txtpassword.getText().isEmpty() || txtconfirmpass.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Llene todos los campos");
                } else {
                    String mensaje = ebo.eliminaruser(new String(textUSUARIO.getText()));
                    JOptionPane.showMessageDialog(null, mensaje);
                    limpiar();

                }
            }
        });

    }

   /* private void listaruser(Connection conn, JTable table1) {
        DefaultTableModel tableModel = new DefaultTableModel(7, 7);
        table1.setModel(tableModel);
        String[] columnas = {"USUARIO", "NOMBRE", "APELLIDO", "TELEFONO", "EMAIL", "PASSWORD", "CONFIRMPASSWORD"};
        tableModel = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM USUARIOS ORDER BY USUARIO";
        String
                [] filas = new String[7];
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < 7; i++) {
                    filas[i] = rs.getString(i + 1);
                }
                tableModel.addRow(filas);
            }
            table1.setModel(tableModel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede mostrar la table");
        }
        setVisible(true);
    }*/

    public void DasboardUsers(String USUARIO, String NOMBRE, String APELLIDO, String TELEFONO, String EMAIL, String
            PASSWORD, String CONFIRMPASSWORD) {
        this.USUARIO = USUARIO;
        this.NOMBRE = NOMBRE;
        this.APELLIDO = APELLIDO;
        this.TELEFONO = TELEFONO;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.CONFIRMPASSWORD = CONFIRMPASSWORD;
        //to save or insert user
    }


    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPELLIDO() {
        return APELLIDO;
    }

    public void setAPELLIDO(String APELLIDO) {
        this.APELLIDO = APELLIDO;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getCONFIRMPASSWORD() {
        return CONFIRMPASSWORD;
    }

    public void setCONFIRMPASSWORD(String CONFIRMPASSWORD) {
        this.CONFIRMPASSWORD = CONFIRMPASSWORD;
    }


    private void limpiar() {
        textUSUARIO.setText("");
        textNOMBRE.setText("");
        textAPELLIDO.setText("");
        textTELEFONO.setText("");
        txtEMAIL.setText("");
        txtpassword.setText("");
        txtconfirmpass.setText("");
    }

    private boolean connectTODatabase() {
        boolean hasRegistredUsers = false;
        final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
        final String USERNAME = "C##STARLIN";
        final String PASSWORD = "12345";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("select * from usuarios");
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hasRegistredUsers;
    }


    public static void main(String[] args) {
        getOracleConnection();
        JFrame f = new JFrame("USERS REGISTER");
        f.setSize(600, 600);
        f.add(new DasboardUsers().rootpanel);
        f.setVisible(true);




    }


    public static Connection getOracleConnection() {
        Connection conn = null;
        try {
            /*el try catch enviara a la base de datos la url, login y password para obtener los datos*/
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "C##STARLIN", "12345");
            conn.setAutoCommit(false);
            if (conn != null) {
                System.out.println("Conexion Exitosa");
            } else {
                System.out.println("Conexion es erronea");
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexion Erronea" + e.getMessage());

        }
        return conn;

    }

    String header[] = {"USUARIO", "NOMBRE", "APELLIDO", "TELEFONO", "EMAIL", "PASSWORD", "CONFIRMPASSWORD"};

    private void createUIComponents() throws SQLException {
        // TODO: place custom component creation code here
        DefaultTableModel model = new DefaultTableModel(0, 7);
        model.setColumnIdentifiers(header);
        try {
            table1 = new JTable(model);
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "C##STARLIN", "12345");
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from usuarios");
while (rs.next()){
    Object[] row={rs.getString("USUARIO"),rs.getString("NOMBRE"),rs.getString("APELLIDO"),rs.getString("TELEFONO"),rs.getString("EMAIL"),rs.getString("PASSWORD"),rs.getString("CONFIRMPASSWORD")};
    model.addRow(row);
}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



