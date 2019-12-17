package controladores;

import modelos.Medidas;
import utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utiles.Utiles;

public class MedidasControlador {

    public static boolean agregar(Medidas medida) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into medidas(nombre_medida) " + "values('" + medida.getNombre_medida() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Medidas buscarId(Medidas medida) {

        if (Conexion.conectar()) {
            String sql = "select * from medidas where id_medida ='" + medida.getId_medida() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    medida.setId_medida(rs.getInt("id_medida"));
                    medida.setNombre_medida(rs.getString("nombre_medida"));
                } else {
                    medida.setId_medida(0);
                    medida.setNombre_medida("");
                    //return null;
                    //return medida;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return medida;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from medidas where upper(nombre_medida) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by id_medida offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_medida") + "</td>"
                                + "<td>" + rs.getString("nombre_medida") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(Medidas medida) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update medidas set nombre_medida= '" + medida.getNombre_medida() + "'" + " where id_medida=" + medida.getId_medida();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Medidas medida) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from medidas where id_medida=" + medida.getId_medida();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }
     public static Medidas buscarMedidas(Medidas medida) {

        if (Conexion.conectar()) {
            String sql = "select * from medidas where nombre_medida='" + medida.getNombre_medida() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    medida.setId_medida(0);

                } else {

                    medida.setId_medida(-1);

                    //return null;
                    //return medida;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return medida;
    }
}
