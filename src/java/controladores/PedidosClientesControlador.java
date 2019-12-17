package controladores;

import modelos.PedidosClientes;
import utiles.Conexion;
import utiles.Utiles;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidosClientesControlador {

    public static PedidosClientes buscarId(int id) throws SQLException {
        PedidosClientes pedidoclientes = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pedidoclientes "
                        + "where estado_pedidov='PENDIENTE' and id_pedidocliente=?";

                /*String sql = "select * from ajustes_stock c "
                        + "left join usuarios u on u.id_usuario=c.id_usuario "
                        + "where id_ajuste_stock=?";*/
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        pedidoclientes = new PedidosClientes();
                        pedidoclientes.setId_pedidocliente(rs.getInt("id_pedidocliente"));
                        pedidoclientes.setFecha_pedidov(rs.getDate("fecha_pedidov"));
                        pedidoclientes.setEstado_pedidov(rs.getString("estado_pedidov"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                Logger.getLogger(PedidosClientesControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return pedidoclientes;
    }

    public static PedidosClientes buscarIdFacturado(int id) throws SQLException {
        PedidosClientes pedidoclientes = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pedidoclientes "
                        + "where id_pedidocliente=?";

                /*String sql = "select * from ajustes_stock c "
                        + "left join usuarios u on u.id_usuario=c.id_usuario "
                        + "where id_ajuste_stock=?";*/
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        pedidoclientes = new PedidosClientes();
                        pedidoclientes.setId_pedidocliente(rs.getInt("id_pedidocliente"));
                        pedidoclientes.setFecha_pedidov(rs.getDate("fecha_pedidov"));
                        pedidoclientes.setEstado_pedidov(rs.getString("estado_pedidov"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                Logger.getLogger(PedidosClientesControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return pedidoclientes;
    }

    public static String buscarNombreP(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from productos p left join marcas m on p.id_marca=m.id_marca left join medidas c on p.id_medida=c.id_medida left join rubros r on p.id_rubro=r.id_rubro where upper(nombre_producto) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by id_producto offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("codigo_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                //      + "<td>" + rs.getString("descripcion_producto") + "</td>"
                                + "<td>" + rs.getString("precioventa_producto") + "</td>"
                                //+ "<td>" + rs.getString("preciocompra_producto") + "</td>"
                                //   + "<td>" + rs.getString("stockmin_producto") + "</td>"
                                //   + "<td>" + rs.getString("stockmax_producto") + "</td>"
                                //   + "<td>" + rs.getString("id_marca") + "</td>"
                                //       + "<td>" + rs.getString("nombre_marca") + "</td>"
                                // + "<td>" + rs.getString("id_rubro") + "</td>"
                                //     + "<td>" + rs.getString("id_medida") + "</td>"
                                //+ "<td>" + rs.getString("id_iva") + "</td>"
                                //   + "<td>" + rs.getString("iva_producto") + "</td>"
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

    public static String buscarNombreC(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from productos p left join marcas m on p.id_marca=m.id_marca left join medidas c on p.id_medida=c.id_medida left join rubros r on p.id_rubro=r.id_rubro where upper(codigo_producto) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by id_producto offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getString("codigo_producto") + "</td>"
                                + "<td>" + rs.getString("descripcion_producto") + "</td>"
                                + "<td>" + rs.getString("precioventa_producto") + "</td>"
                                //+ "<td>" + rs.getString("preciocompra_producto") + "</td>"
                                //   + "<td>" + rs.getString("stockmin_producto") + "</td>"
                                //   + "<td>" + rs.getString("stockmax_producto") + "</td>"
                                //   + "<td>" + rs.getString("id_marca") + "</td>"
                                + "<td>" + rs.getString("nombre_marca") + "</td>"
                                // + "<td>" + rs.getString("id_rubro") + "</td>"
                                //     + "<td>" + rs.getString("id_medida") + "</td>"
                                //+ "<td>" + rs.getString("id_iva") + "</td>"
                                + "<td>" + rs.getString("iva_producto") + "</td>"
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

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pedidoclientes "
                        + "where upper(estado_pedidov) like '%"
                        + nombre
                        + "%' "
                        + "and estado_pedidov='PENDIENTE' order by id_pedidocliente "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_pedidocliente") + "</td>"
                                + "<td>" + rs.getDate("fecha_pedidov") + "</td>"
                                + "<td>" + rs.getString("estado_pedidov") + "</td>"
                                //+ "<td>" + rs.getString("id_usuario") + "</td>"
                                //+ "<td>" + rs.getString("nombre_usuario") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static String buscarNombreFacturado(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pedidoclientes "
                        + "where upper(estado_pedidov) like '%"
                        + nombre
                        + "%' "
                        + "and estado_pedidov='FACTURADO' order by id_pedidocliente "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_pedidocliente") + "</td>"
                                + "<td>" + rs.getDate("fecha_pedidov") + "</td>"
                                + "<td>" + rs.getString("estado_pedidov") + "</td>"
                                //+ "<td>" + rs.getString("id_usuario") + "</td>"
                                //+ "<td>" + rs.getString("nombre_usuario") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static String buscarNombreInfor(Date DESDE, Date HASTA, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                /*String sql = "select * from pedidoclientes "
                        + "where upper(fecha_pedidov) like '%"
                        + fecha_pedidov
                        + "%' "
                        + "order by id_pedidocliente "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;*/
                String sql = "select * from pedidoclientes "
                        + "where fecha_pedidov >=' "
                        + DESDE
                        + "%' "
                        + "AND fecha_pedidov <=' "
                        + HASTA
                        + "%' "
                        + "and estado_pedidov='FACTURADO' order by id_pedidocliente "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_pedidocliente") + "</td>"
                                + "<td>" + rs.getDate("fecha_pedidov") + "</td>"
                                + "<td>" + rs.getString("estado_pedidov") + "</td>"
                                //+ "<td>" + rs.getString("id_usuario") + "</td>"
                                //+ "<td>" + rs.getString("nombre_usuario") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificarestadopedido(PedidosClientes pedidocliente) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update pedidoclientes set estado_pedidov='FACTURADO'"
                    + " where id_pedidocliente=" + pedidocliente.getId_pedidocliente();
            try {

                Conexion.getSt().executeUpdate(sql);

                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(PedidosClientes pedidocliente) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into pedidoclientes(fecha_pedidov, estado_pedidov) "
                    + "values('" + pedidocliente.getFecha_pedidov() + "','"
                    + pedidocliente.getEstado_pedidov() + "')";

            /*String sql = "insert into ajustes(id_usuario,fecha_ajuste,motivo_ajuste) "
                    + "values('" + ajuste.getUsuario().getId_usuario() + "','"
                    + ajuste.getFecha_ajuste() + "','"
                    + ajuste.getMotivo_ajuste() + "')";*/
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_pedidocliente = keyset.getInt(1);
                    pedidocliente.setId_pedidocliente(id_pedidocliente);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(PedidosClientesControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
                //System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    /*  public static boolean modificar(PedidosClientes pedidos) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update pedidoclientes set id_cliente=? "
                    + "where id_pedidocliente=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, pedidos.getCliente().getId_cliente());
                ps.setInt(2, pedidos.getId_pedidocliente());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }*/
    public static boolean eliminar(PedidosClientes pedidocliente) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from pedidoclientes where id_pedidocliente=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, pedidocliente.getId_pedidocliente());
                System.out.println("sqlPUTO " + ps);
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
