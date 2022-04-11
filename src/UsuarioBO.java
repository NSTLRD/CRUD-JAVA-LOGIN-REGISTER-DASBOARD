import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class UsuarioBO {
    private String mensaje = "";
    private UsuariosRegistrados udao = new UsuariosRegistrados();

    public String nuevouser( Users usr){
        Connection conn = Conexion.getConnection();
        try {
            mensaje = udao.nuevouser(conn, usr);

        } catch (Exception e)
        {
            mensaje = mensaje + " " + e.getMessage();
        } finally
        { try {
            if (conn != null ){
                conn.close();
            }
        } catch (Exception e) {
            mensaje = mensaje + " " + e.getMessage();
        }

        }
        return mensaje;
    }

    public String modificaruser( Users usr){
        Connection conn = Conexion.getConnection();
        try {
            mensaje = udao.modificaruser(conn, usr);

        } catch (Exception e)
        {
            mensaje = mensaje + " " + e.getMessage();
        } finally
        { try {
            if (conn != null ){
                conn.close();
            }
        } catch (Exception e) {
            mensaje = mensaje + " " + e.getMessage();
        }

        }
        return mensaje;
    }

    public String eliminaruser( String USUARIO){
        Connection conn = Conexion.getConnection();
        try {
            mensaje = udao.eliminaruser(conn,USUARIO);


        } catch (Exception e)
        {
            mensaje = mensaje + " " + e.getMessage();
        } finally
        { try {
            if (conn != null ){
                conn.close();
            }
        } catch (Exception e) {
            mensaje = mensaje + " " + e.getMessage();
        }

        }
        return mensaje;
    }

   public void listaruser(JTable tabla1){
        Connection conn = Conexion.getConnection();

        udao.listaruser(conn, tabla1);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
