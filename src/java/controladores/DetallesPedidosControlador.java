/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Productos;
import modelos.PedidosClientes;
import modelos.DetallesPedidos;
import utiles.Conexion;
import utiles.Utiles;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DetallesPedidosControlador {

    public static DetallesPedidos buscarId(int id) {
        DetallesPedidos detallepedido = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallepedidoclientes d, pedidoclientes a, productos p where d.id_pedidocliente=a.id_pedidocliente and d.id_producto=p.id_producto and d.id_detallepedidoc=?";

                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detallepedido = new DetallesPedidos();
                        detallepedido.setId_detallepedidoc(rs.getInt("id_detallepedidoc"));
                        detallepedido.setCantidad_detallepedidoc(rs.getInt("cantidad_detallepedidoc"));

                        Productos producto = new Productos();
                        producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setPrecioventa_producto(rs.getInt("precioventa_producto"));
                        producto.setCodigo_producto(rs.getString("codigo_producto"));
                        detallepedido.setProducto(producto);

                        PedidosClientes pedidocliente = new PedidosClientes();
                        pedidocliente.setId_pedidocliente(rs.getInt("id_pedidocliente"));
                        detallepedido.setPedidocliente(pedidocliente);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallepedido;
    }

    public static String buscarIdPedidoCliente(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallepedidoclientes  dp "
                        + "left join pedidoclientes p on p.id_pedidocliente=dp.id_pedidocliente "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where dp.id_pedidocliente=" + id + " "
                        + "order by id_detallepedidoc";
                // System.out.println("acaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {

                        BigDecimal cantidad = rs.getBigDecimal("cantidad_detallepedidoc");
                        total = total.add(cantidad);

                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallepedidoc") + "</td>"
                                + "<td>" + rs.getString("codigo_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button id='lapiz' onclick='editarLinea(" + rs.getString("id_detallepedidoc") + ")'"
                                + " type='button' class='btn bordo bordo1 btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button><button id='menos' onclick='P(" + rs.getString("id_detallepedidoc") + ")''  type='button' class='btn bordo bordo1 btn-sm'><span class='glyphicon glyphicon-trash'> </span></button> </td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                Logger.getLogger(DetallesPedidosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static String buscarIdPedidoClienteFactura(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallepedidoclientes  dp "
                        + "left join pedidoclientes p on p.id_pedidocliente=dp.id_pedidocliente "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where dp.id_pedidocliente=" + id + " "
                        + "order by id_detallepedidoc";
                // System.out.println("acaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {

                        BigDecimal cantidad = rs.getBigDecimal("cantidad_detallepedidoc");
                        total = total.add(cantidad);

                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallepedidoc") + "</td>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                Logger.getLogger(DetallesPedidosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static String buscarIdPedidoFactura(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallepedidoclientes  dp "
                        + "left join pedidoclientes p on p.id_pedidocliente=dp.id_pedidocliente "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where dp.id_pedidocliente=" + id + " "
                        + "order by id_detallepedidoc";
                System.out.println("--> " + sql);
                //YONI PRUEBA DE CODIGO SI FUNCIONA
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    BigDecimal total1 = BigDecimal.ZERO;
                    BigDecimal stotal = BigDecimal.ZERO;
                    //BigDecimal iva = BigDecimal.ZERO;
                    int totales = 0;
                    //int cant = Integer.parseInt(rs.getString("cantidad_producto_venta"));
                    int totale = 0;
                    int total5 = 0;
                    int total10 = 0;
                    int totalf = 0;
                    int totalt = 0;
                    //BigDecimal total10 = BigDecimal.ZERO;
                    int iva = 0;
                    int totalexentas = 0;
                    int totaliva5 = 0;
                    int totaliva10 = 0;

                    int liqiva5 = 0;
                    int liqiva10 = 0;

                    while (rs.next()) {
                        iva = rs.getInt("iva_producto");
                        int cantidad1 = rs.getInt("cantidad_detallepedidoc");
                        int venta = rs.getInt("precioventa_producto");
                        if (iva == 0) {

                            totalexentas = venta * cantidad1;
                            totaliva5 = 0;
                            totaliva10 = 0;
                            totale = totale + totalexentas;

                        } else if (iva == 5) {

                            totalexentas = 0;
                            totaliva10 = 0;

                            totaliva5 = venta * cantidad1;
                            total5 = total5 + totaliva5;

                        } else {

                            totalexentas = 0;
                            totaliva5 = 0;
                            totaliva10 = venta * cantidad1;
                            total10 = total10 + totaliva10;
                        }

                        liqiva5 = total5 * 5 / 100;
                        liqiva10 = total10 / 11;

                        totales = liqiva5 + liqiva10;

                        totalf = total5 + totale + total10;
                        totalt = total5 + totale + total10 + totales;

                        BigDecimal cantidad = rs.getBigDecimal("cantidad_detallepedidoc");

                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        BigDecimal subtotal = rs.getBigDecimal("precioventa_producto");
                        total1 = total1.add(subtotal);
                        //para el subtotal
                        BigDecimal subtotalp = subtotal.multiply(cantidad);
                        stotal = stotal.add(subtotalp);
                        //iva
                        //BigDecimal subiva = rs.getBigDecimal("iva_producto");
                        //iva = iva.add(subiva);
                        //subtotaliva

                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallepedidoc") + "</td>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getString("codigo_producto") + "</td>"
                                + "<td>" + rs.getString("precioventa_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>" + df.format(subtotalp) + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=7>No existen registros ...</td></tr>";
                    } else {
                        tabla += "<tr><td colspan=5>TOTAL</td><td class='centrado'>" + df.format(total) + " <td colspan=6 class='centrado'><b>" + df.format(totalf) + "</b></td></td>";

                        //YONI IMPORTANTE PRUEBA DE CODIGO
                        //  tabla += "<tr><td colspan=6><b>SUB-TOTAL:</b></td><td class='left'><b>" + df.format(totalf) + "</b></tr>";
                        /*tabla += "<tr><td colspan=6><b>LIQUIDACION DEL IVA:</b>&nbsp;&nbsp;&nbsp; <b>(5%):</b> " + "" + df.format(liqiva5) + "  &nbsp;&nbsp;&nbsp;   <b>(10%):</b>&nbsp;" + df.format(liqiva10) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> TOTAL I.V.A:</b> &nbsp; " + df.format(total) + " </td> </tr>";
                        tabla += "<tr><td colspan=7 ><b><P ALIGN=right>TOTAL A PAGAR:</b></td><td class='left'><b>" + df.format(totalf) + "</b></td></tr>";  //+ df.format(totald) +"</td></tr>";*/
                        tabla += "<tr><td colspan=9><b>Liquidacion de IVA:</b>&nbsp;&nbsp;&nbsp; <b>(5%):</b> " + "" + df.format(liqiva5) + "  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   <b>(10%):</b>&nbsp;" + df.format(liqiva10) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> TOTAL I.V.A:</b> &nbsp; " + df.format(totales) + " </td> </tr>";

                        //tabla += "<tr><td colspan=9 ><b><P ALIGN=left>Total Grabada: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + df.format(totalf) + " Gs." + "</b></td></tr>";  //+ df.format(totald) +"</td></tr>";
                        tabla += "<tr><td colspan=9 ><b><P ALIGN=left>Total a Pagar: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + df.format(totalf) + " Gs." + "</b></td></tr>";  //+ df.format(totald) +"</td></tr>";

                        //String sql2 = "select sum(" + cant + " * precio_venta_producto) + " + total + " as TotalaPagar from detallesventas dv left join ventas v on v.id_venta=dv.id_venta left join productos p on p.id_producto=dv.id_producto where dv.id_venta="+ id + "";
                        //System.out.println("total " + sql2);
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

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallepedidoclientes dp "
                        + "left join pedidoclientes p on p.id_pedidocliente=dp.id_pedidocliente "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        //  + "left join stock s on s.id_producto=p.id_producto "
                        + "where upper(a.nombre_producto) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detallepedidocliente "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getInt("id_detallepedidoc") + "</td>"
                                + "<td>" + rs.getInt("id_pedidocliente") + "</td>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getInt("precioventa_producto") + "</td>"
                                //+ "<td>" + rs.getInt("iva_producto") + "</td>"
                                // + "<td>" + rs.getInt("cantidad_exi") + "</td>"
                                + "<td>" + rs.getInt("cantidad_detallepedidoc") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
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

    public static boolean agregar(DetallesPedidos detallepedido) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallepedidoclientes (id_pedidocliente, id_producto, cantidad_detallepedidoc) "
                    + "values (?,?,?)";

            /*String sql = "insert into detallesajustes "
                    + "(id_ajuste,id_producto,cantidad_ajuste) "
                    + "values (?,?,?)";*/
            System.out.println(sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallepedido.getPedidocliente().getId_pedidocliente());
                ps.setInt(2, detallepedido.getProducto().getId_producto());
                ps.setInt(3, detallepedido.getCantidad_detallepedidoc());

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
    
     public static boolean buscarDuplicadosM(DetallesPedidos detallepedidoc) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "select * from detallepedidoclientes where id_pedidocliente = " + detallepedidoc.getPedidocliente().getId_pedidocliente()
                    + " and id_producto = " + detallepedidoc.getProducto().getId_producto() + "";
            System.out.println("sql " + sql);

            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    detallepedidoc.setId_detallepedidoc(0);
                    System.out.println("DUPLICADO");

                } else {
                  String sql1 = "update detallepedidoclientes set "
                    + "id_pedidocliente=?,"
                    + "id_producto=?,"
                    + "cantidad_detallepedidoc=? "
                    + "where id_detallepedidoc=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql1)) {
                ps.setInt(1, detallepedidoc.getPedidocliente().getId_pedidocliente());
                ps.setInt(2, detallepedidoc.getProducto().getId_producto());
                ps.setInt(3, detallepedidoc.getCantidad_detallepedidoc());
                ps.setInt(4, detallepedidoc.getId_detallepedidoc());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> Grabado");
                valor = true;
                        System.out.println("NO DUPLICADO");
                    } catch (SQLException ex) {
                        System.out.println("--> " + ex.getLocalizedMessage());
                        try {
                            Conexion.getConn().rollback();
                        } catch (SQLException ex1) {
                            System.out.println("--> " + ex1.getLocalizedMessage());
                        }
                    }

                    //return null;
                    //return ciudad;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static boolean modificar(DetallesPedidos detallepedidoc) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallepedidoclientes set "
                    + "id_pedidocliente=?,"
                    + "id_producto=?,"
                    + "cantidad_detallepedidoc=? "
                    + "where id_detallepedidoc=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallepedidoc.getPedidocliente().getId_pedidocliente());
                ps.setInt(2, detallepedidoc.getProducto().getId_producto());
                ps.setInt(3, detallepedidoc.getCantidad_detallepedidoc());
                ps.setInt(4, detallepedidoc.getId_detallepedidoc());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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

    public static boolean eliminar(DetallesPedidos detallepedidoc) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallepedidoclientes where id_detallepedidoc=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallepedidoc.getId_detallepedidoc());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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

    public static boolean eliminarProductoPedido(DetallesPedidos detallepedidoc) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallepedidoclientes where id_pedidocliente=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallepedidoc.getId_detallepedidoc());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + detallepedidoc.getId_detallepedidoc());
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
    
     public static boolean eliminarPrueva (DetallesPedidos detallepedidoc) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from detallepedidoclientes where id_pedidocliente=" + detallepedidoc.getPedidocliente().getId_pedidocliente();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static DetallesPedidos buscarDetallePedido(DetallesPedidos detallepedido) {

        if (Conexion.conectar()) {
            String sql = "select * from detallepedidoclientes where id_producto='" + detallepedido.getProducto() + "' ";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    detallepedido.setId_detallepedidoc(0);

                } else {

                    detallepedido.setId_detallepedidoc(-1);

                    //return null;
                    //return ciudad;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return detallepedido;
    }

    public static boolean buscarDuplicadosP(DetallesPedidos detallepedido) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "select * from detallepedidoclientes where id_pedidocliente = " + detallepedido.getPedidocliente().getId_pedidocliente()
                    + " and id_producto = " + detallepedido.getProducto().getId_producto() + "";
            System.out.println("sql " + sql);

            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    detallepedido.setId_detallepedidoc(0);
                    System.out.println("DUPLICADO");

                } else {
                    String sql1 = "insert into detallepedidoclientes (id_pedidocliente, id_producto, cantidad_detallepedidoc,preciototal_detallepedidoc) "
                            + "values (?,?,?,?)";

                    /*String sql = "insert into detallesajustes "
                    + "(id_ajuste,id_producto,cantidad_ajuste) "
                    + "values (?,?,?)";*/
                    System.out.println(sql1);
                    try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql1)) {
                        ps1.setInt(1, detallepedido.getPedidocliente().getId_pedidocliente());
                        ps1.setInt(2, detallepedido.getProducto().getId_producto());
                        ps1.setInt(3, detallepedido.getCantidad_detallepedidoc());
                        ps1.setInt(4, detallepedido.getPreciototal_detallepedidoc());

                        ps1.executeUpdate();
                        ps1.close();
                        Conexion.getConn().setAutoCommit(false);
                        valor = true;
                        System.out.println("NO DUPLICADO");
                    } catch (SQLException ex) {
                        System.out.println("--> " + ex.getLocalizedMessage());
                        try {
                            Conexion.getConn().rollback();
                        } catch (SQLException ex1) {
                            System.out.println("--> " + ex1.getLocalizedMessage());
                        }
                    }

                    //return null;
                    //return ciudad;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }
}
