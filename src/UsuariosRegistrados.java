import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class UsuariosRegistrados {
    private String mensaje = "";
    public String nuevouser(Connection conn, Users usr) {
        PreparedStatement pst = null;
        String sql = "INSERT INTO usuarios (USUARIO, NOMBRE, APELLIDO, TELEFONO, EMAIL, PASSWORD, CONFIRMPASSWORD) "
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            //*EL PST PREPARESTATEMENT OBTENDRA LOS PARAMETROS Y LO MANDARA A VALUES*//
            pst = conn.prepareStatement(sql);
            pst.setString(1, usr.getUSUARIO());
            pst.setString(2, usr.getNOMBRE());
            pst.setString(3, usr.getAPELLIDO());
            pst.setString(4, usr.getTELEFONO());
            pst.setString(5, usr.getEMAIl() + "");
            pst.setString(6, usr.getPASSWORD() + "");
            pst.setString(7, usr.getCONFIRMPASSWORD());
            mensaje = "GUARDADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "NO SE GUARDADO CORRECTAMENTE \n " + e.getMessage();
        }
        return mensaje;
    }
        public String modificaruser(Connection conn, Users usr){
            PreparedStatement pst = null;
            String sql = "UPDATE USUARIOS SET USUARIO = ?, NOMBRE = ?, APELLIDO = ?, TELEFONO = ?, EMAIL = ?, PASSWORD = ?,CONFIRMPASSWORD =?"
                    + "WHERE USUARIO = ?";
            try {
                //*EL PST PREPARESTATEMENT OBTENDRA LOS PARAMETROS Y LO MANDARA A VALUES*//
                pst = conn.prepareStatement(sql);
                pst.setString(1, usr.getUSUARIO());
                pst.setString(2, usr.getNOMBRE());
                pst.setString(3, usr.getAPELLIDO());
                pst.setString(4, usr.getTELEFONO()+"");
                pst.setString(5, usr.getEMAIl()+"");
                pst.setString(6, usr.getPASSWORD());
                pst.setString(7, usr.getCONFIRMPASSWORD());
                mensaje = "ACTUALIZADO CORRECTAMENTE";
                pst.execute();
                pst.close();
            } catch (SQLException e)
            {
                mensaje = "NO SE ACTUALIZO CORRECTAMENTE \n " + e.getMessage();
            }
            return mensaje;
        }

    public String eliminaruser(Connection con, String USUARIO){
        PreparedStatement pst = null;
        String sql = "DELETE FROM USUARIOS WHERE USUARIO= ?";
        try {
            //*EL PST PREPARESTATEMENT OBTENDRA LOS PARAMETROS Y LO MANDARA A VALUES*//
            pst = con.prepareStatement(sql);
            pst.setString(1, USUARIO);


            mensaje = "ELIMINADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e)
        {
            mensaje = "NO SE ELIMINO CORRECTAMENTE \n " + e.getMessage();
        }
        return mensaje;
    }
    public void listaruser( Connection conn, JTable Tabla1){
        DefaultTableModel model;
        String [] columnas = {"ID","NOMBRES","APELLIDOS","CEDULA","ESTADO CIVIL","GENERO","EDAD"};
        model = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM EMPLEADO ORDER BY IDEMPLEADO";

        String
                [] filas = new String[7];
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < 7; i++) {
                    filas[i] = rs.getString(i+1);
                }
                model.addRow(filas);

            }
            Tabla1.setModel(model);

        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla");
        }
    }

}
