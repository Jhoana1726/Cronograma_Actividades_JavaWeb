package Certificado_Eventos;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultarCertificadoEv {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        try {
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo establecer conexión con la base de datos");
                return;
            }

            st = cn.createStatement();
            rs = st.executeQuery(
                "SELECT c.id_certificado, e.titulo AS nombre_evento, u.nombre AS nombre_usuario, c.fecha_entrega " +
                "FROM Certificado_Eventos c " +
                "JOIN Evento e ON c.id_evento = e.id_evento " +
                "JOIN Usuario u ON c.id_usuario = u.id_usuario"
            );

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id_certificado") + ": "
                    + rs.getString("nombre_evento") + " - "
                    + rs.getString("nombre_usuario") + " - "
                    + rs.getDate("fecha_entrega")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(consultarCertificadoEv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
