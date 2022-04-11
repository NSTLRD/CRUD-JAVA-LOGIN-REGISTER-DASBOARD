import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    /* encasulamiento*/
    private static Connection conn = null;
    private static String login = "C##STARLIN";
    /*le ponemos static para que no sea llamada*/
    private static String password = "12345";
    private static String url = "jdbc:oracle:thin:@localhost:1521:orcl";

    public static Connection getConnection(){
        try {
            /*el try catch enviara a la base de datos la url, login y password para obtener los datos*/
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url,login,password);
            conn.setAutoCommit(false);
            if (conn != null) {
                System.out.println("Conexion Exitosa");
            } else {
                System.out.println("Conexion es erronea");
            }


        }  catch (SQLException e) {
            e.printStackTrace();


            JOptionPane.showMessageDialog(null,"Conexion Erronea" + e.getMessage());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void desconexion(){

        try {
            conn.close();
        } catch (Exception e)
        {
            System.out.println("Error al desconectar" + e.getMessage());
        }

    }

    public static void main(String[] args){
        Conexion c = new Conexion() ;
        c.getConnection();
    }}
