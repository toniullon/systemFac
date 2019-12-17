/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.PedidosProveedores;
import modelos.Personas;
import modelos.Ventas;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author usuario
 */
public class PedidosProveedoresControlador {
    
    public static PedidosProveedores buscarId(int id) throws SQLException {
        PedidosProveedores pedidoproveedores = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pedidoproveedores p, personas per "
                        + "where estado_pedidop='PENDIENTE' and p.id_persona=per.id_persona and id_pedidoproveedor=?";

                /*String sql = "select * from ajustes_stock c "
                        + "left join usuarios u on u.id_usuario=c.id_usuario "
                        + "where id_ajuste_stock=?";*/
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        pedidoproveedores = new PedidosProveedores();
                        pedidoproveedores.setId_pedidoproveedor(rs.getInt("id_pedidoproveedor"));
                        pedidoproveedores.setFecha_pedidop(rs.getDate("fecha_pedidop"));
                        pedidoproveedores.setEstado_pedidop(rs.getString("estado_pedidop"));
                        
                        Personas persona = new Personas();
                        persona.setId_persona(rs.getInt("id_persona"));
                        persona.setNombre_persona(rs.getString("nombre_persona"));
                        persona.setApellido_persona(rs.getString("apellido_persona"));
                        persona.setDireccion_persona(rs.getString("direccion_persona"));
                        persona.setTelefono_persona(rs.getString("telefono_persona"));
                        persona.setCorreo_persona(rs.getString("correo_persona"));
                        persona.setRuc_persona(rs.getString("ruc_persona"));
                        pedidoproveedores.setPersona(persona);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                Logger.getLogger(PedidosProveedoresControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return pedidoproveedores;
    }
    
    public static PedidosProveedores buscarIdFacturado(int id) throws SQLException {
          PedidosProveedores pedidoproveedores = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pedidoproveedores p, personas per "
                        + "where estado_pedidop='PENDIENTE' and p.id_persona=per.id_persona and id_pedidoproveedor=?";

                /*String sql = "select * from ajustes_stock c "
                        + "left join usuarios u on u.id_usuario=c.id_usuario "
                        + "where id_ajuste_stock=?";*/
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        pedidoproveedores = new PedidosProveedores();
                        pedidoproveedores.setId_pedidoproveedor(rs.getInt("id_pedidoproveedor"));
                        pedidoproveedores.setFecha_pedidop(rs.getDate("fecha_pedidop"));
                        pedidoproveedores.setEstado_pedidop(rs.getString("estado_pedidop"));
                        
                        Personas persona = new Personas();
                        persona.setId_persona(rs.getInt("id_persona"));
                        persona.setNombre_persona(rs.getString("nombre_persona"));
                        persona.setApellido_persona(rs.getString("apellido_persona"));
                        persona.setDireccion_persona(rs.getString("direccion_persona"));
                        persona.setTelefono_persona(rs.getString("telefono_persona"));
                        persona.setCorreo_persona(rs.getString("correo_persona"));
                        persona.setRuc_persona(rs.getString("ruc_persona"));
                        pedidoproveedores.setPersona(persona);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                Logger.getLogger(PedidosProveedoresControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return pedidoproveedores;
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
                String sql = "select * from pedidoproveedores "
                        + "where upper(estado_pedidop) like '%"
                        + nombre
                        + "%' "
                        + "and estado_pedidop='PENDIENTE' order by id_pedidoproveedor "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                /*String sql = "select * from ajustes c "
                        + "left join usuarios p on c.id_usuario=p.id_usuario "
                        + "where upper(nombre_usuario) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_ajuste "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;*/
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_pedidoproveedor") + "</td>"
                                + "<td>" + rs.getDate("fecha_pedidop") + "</td>"
                                + "<td>" + rs.getString("estado_pedidop") + "</td>"
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
     public static String buscarNombreCo(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pedidoproveedores "
                        + "where upper(estado_pedidop) like '%%' "
                        + "and estado_pedidop='FACTURADO' order by id_pedidoproveedor "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                /*String sql = "select * from ajustes c "
                        + "left join usuarios p on c.id_usuario=p.id_usuario "
                        + "where upper(nombre_usuario) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_ajuste "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;*/
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_pedidoproveedor") + "</td>"
                                + "<td>" + rs.getDate("fecha_pedidop") + "</td>"
                                + "<td>" + rs.getString("estado_pedidop") + "</td>"
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
    
    public static String buscarNombreInf(Date DESDE, Date HASTA, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
               String sql = "select * from pedidoproveedores "
                        + "where fecha_pedidop >=' "
                        + DESDE
                        + "%' "
                        + "AND fecha_pedidop <=' "
                        + HASTA
                        + "%' "
                        +  "and estado_pedidop='FACTURADO' order by id_pedidoproveedor "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                /*String sql = "select * from ajustes c "
                        + "left join usuarios p on c.id_usuario=p.id_usuario "
                        + "where upper(nombre_usuario) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_ajuste "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;*/
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_pedidoproveedor") + "</td>"
                                + "<td>" + rs.getDate("fecha_pedidop") + "</td>"
                                + "<td>" + rs.getString("estado_pedidop") + "</td>"
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
    
    public static boolean modificarestadopedido(PedidosProveedores pedidoproveedor) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update pedidoproveedores set estado_pedidop='FACTURADO'"
                    + " where id_pedidoproveedor=" + pedidoproveedor.getId_pedidoproveedor();
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
    
    public static boolean agregar(PedidosProveedores pedidoproveedor) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into pedidoproveedores(fecha_pedidop, estado_pedidop, id_persona) "
                    + "values('" + pedidoproveedor.getFecha_pedidop() + "','"
                    + pedidoproveedor.getEstado_pedidop() + "','"
                    + pedidoproveedor.getPersona().getId_persona()+ "')";

            /*String sql = "insert into ajustes(id_usuario,fecha_ajuste,motivo_ajuste) "
                    + "values('" + ajuste.getUsuario().getId_usuario() + "','"
                    + ajuste.getFecha_ajuste() + "','"
                    + ajuste.getMotivo_ajuste() + "')";*/
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_pedidoproveedor = keyset.getInt(1);
                    pedidoproveedor.setId_pedidoproveedor(id_pedidoproveedor);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(PedidosProveedoresControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
                //System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }
        
        return valor;
    }

    /*  public static boolean modificar(PedidosProveedores pedidos) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update pedidoproveedores set id_cliente=? "
                    + "where id_pedidoproveedor=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, pedidos.getCliente().getId_cliente());
                ps.setInt(2, pedidos.getId_pedidoproveedor());
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
    public static boolean eliminar(PedidosProveedores pedidoproveedor) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from pedidoproveedores where id_pedidoproveedor=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, pedidoproveedor.getId_pedidoproveedor());
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
    
    public static boolean agregarProveedor(PedidosProveedores pedidoproveedor) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update pedidoproveedores set id_persona = " + pedidoproveedor.getPersona().getId_persona() + " where id_pedidoproveedor=" + pedidoproveedor.getId_pedidoproveedor();
            try {
                
                Conexion.getSt().executeUpdate(sql);
                
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> Grabado ToooooooooNi");
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
