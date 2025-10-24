package Certificado_Eventos;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregarCertificadoEv {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;

        int id_evento = 12;
        int id_usuario = 18;
        Date fecha_entrega = Date.valueOf("2025-08-30"); // yyyy-MM-dd

        try {
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

            String sql = "INSERT INTO Certificado_Eventos (id_evento, id_usuario, fecha_entrega) VALUES (?, ?, ?)";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id_evento);
            ps.setInt(2, id_usuario);
            ps.setDate(3, fecha_entrega);

            ps.executeUpdate();
            System.out.println("Certificado insertado correctamente.");

        } catch (SQLException ex) {
            Logger.getLogger(agregarCertificadoEv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
