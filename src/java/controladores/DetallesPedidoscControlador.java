/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.DetallesPedidos;
import modelos.DetallesPedidosc;
import modelos.PedidosProveedores;
import modelos.Productos;
import utiles.Conexion;

/**
 *
 * @author usuario
 */
public class DetallesPedidoscControlador {

    public static DetallesPedidosc buscarId(int id) {
        DetallesPedidosc detallepedido = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallepedidoproveedores d, pedidoproveedores a, productos p where d.id_pedidoproveedor=a.id_pedidoproveedor and d.id_producto=p.id_producto and d.id_detallepedidop=?";

                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detallepedido = new DetallesPedidosc();
                        detallepedido.setId_detallepedidop(rs.getInt("id_detallepedidop"));
                        detallepedido.setCantidad_detallepedidop(rs.getInt("cantidad_detallepedidop"));
                         detallepedido.setCantidad_faltante(rs.getInt("cantidad_faltante"));

                        Productos producto = new Productos();
                        producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setDescripcion_producto(rs.getString("descripcion_producto"));
                        producto.setPreciocompra_producto(rs.getInt("preciocompra_producto"));
                        producto.setCodigo_producto(rs.getString("codigo_producto"));
                        detallepedido.setProducto(producto);

                        PedidosProveedores pedidoproveedor = new PedidosProveedores();
                        pedidoproveedor.setId_pedidoproveedor(rs.getInt("id_pedidoproveedor"));
                        detallepedido.setPedidoproveedor(pedidoproveedor);

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
     public static DetallesPedidosc buscarIdFal(int id) {
        DetallesPedidosc detallepedido = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallepedidoproveedores d, pedidoproveedores a, productos p where d.id_pedidoproveedor=a.id_pedidoproveedor and d.id_producto=p.id_producto and d.id_detallepedidop=?";

                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detallepedido = new DetallesPedidosc();
                        detallepedido.setId_detallepedidop(rs.getInt("id_detallepedidop"));
                        detallepedido.setCantidad_detallepedidop(rs.getInt("cantidad_detallepedidop"));
                        if(rs.getInt("cantidad_faltante")>0){
                            detallepedido.setCantidad_faltante(rs.getInt("cantidad_faltante")); 
                        }else{
                            detallepedido.setCantidad_faltante(rs.getInt("cantidad_detallepedidop")); 
                        }
                       

                        Productos producto = new Productos();
                        producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setDescripcion_producto(rs.getString("descripcion_producto"));
                        producto.setPreciocompra_producto(rs.getInt("preciocompra_producto"));
                        producto.setCodigo_producto(rs.getString("codigo_producto"));
                        detallepedido.setProducto(producto);

                        PedidosProveedores pedidoproveedor = new PedidosProveedores();
                        pedidoproveedor.setId_pedidoproveedor(rs.getInt("id_pedidoproveedor"));
                        detallepedido.setPedidoproveedor(pedidoproveedor);

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
    public static boolean modificarfacturado(DetallesPedidosc detallepedidoc) {
        
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallepedidoproveedores set estado_pedidop='FACTURADO'  "
                    + " where cantidad_faltante < '1' and id_pedidoproveedor=" + detallepedidoc.getPedidoproveedor().getId_pedidoproveedor();
            
          //   String sql = "update detallepedidoproveedores set estado_pedidop='FACTURADO'  "
           //         + " where cantidad_faltante > '0' and id_pedidoproveedor=" + detallepedidoc.getPedidoproveedor().getId_pedidoproveedor();
         System.out.println("sql " + sql);
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
    public static boolean modificarpendiente(DetallesPedidosc detallepedidoc) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallepedidoproveedores set estado_pedidop='PENDIENTE'  "
                    + " where id_detallepedidop=" + detallepedidoc.getId_detallepedidop();
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

    public static String buscarIdPedidoProveedor(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallepedidoproveedores  dp "
                        + "left join pedidoproveedores p on p.id_pedidoproveedor=dp.id_pedidoproveedor "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where dp.id_pedidoproveedor=" + id + " "
                        + "order by id_detallepedidop";
                // System.out.println("acaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {

                        BigDecimal cantidad = rs.getBigDecimal("cantidad_detallepedidop");
                        total = total.add(cantidad);

                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallepedidop") + "</td>"
                                + "<td>" + rs.getString("codigo_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button id='lapiz' onclick='editarLinea(" + rs.getString("id_detallepedidop") + ")'"
                                + " type='button' class='btn bordo bordo1 btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button><button id='menos' onclick='P(" + rs.getString("id_detallepedidop") + ")''  type='button' class='btn bordo bordo1 btn-sm'><span class='glyphicon glyphicon-trash'> </span></button> </td>"
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

    public static String buscarIdPedidoProveedorFac(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallepedidoproveedores  dp "
                        + "left join pedidoproveedores p on p.id_pedidoproveedor=dp.id_pedidoproveedor "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where dp.estado_pedidop='PENDIENTE' and  dp.id_pedidoproveedor=" + id + ""
                        + "order by id_detallepedidop";
                // System.out.println("acaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {

                        BigDecimal cantidad = rs.getBigDecimal("cantidad_detallepedidop");
                        total = total.add(cantidad);
                        BigDecimal cantidade = rs.getBigDecimal("cantidad_entrante");
                        total = total.add(cantidade);
                        BigDecimal cantidadf = rs.getBigDecimal("cantidad_faltante");
                        total = total.add(cantidadf);

                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallepedidop") + "</td>"
                                + "<td>" + rs.getString("codigo_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>" + df.format(cantidade) + "</td>"
                                + "<td class='centrado'>" + df.format(cantidadf) + "</td>"
                                + "<td class='centrado'>"
                                + "<button id='lapiz' onclick='editarDepo(" + rs.getString("id_detallepedidop") + ")'"
                                + " type='button' class='btn bordo bordo1 btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
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

    public static boolean buscarDuplicadosP(DetallesPedidosc detallepedido) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "select * from detallepedidoproveedores where id_pedidoproveedor = " + detallepedido.getPedidoproveedor().getId_pedidoproveedor()
                    + " and id_producto = " + detallepedido.getProducto().getId_producto() + "";
            System.out.println("sql " + sql);

            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    detallepedido.setId_detallepedidop(0);
                    System.out.println("DUPLICADO");

                } else {
                    int d = 0;
                    String sql1 = "insert into detallepedidoproveedores (id_pedidoproveedor, id_producto, cantidad_detallepedidop,preciototal_detallepedidop,cantidad_entrante,cantidad_faltante,estado_pedidop) "
                            + "values (?,?,?,?,?," + d + ",?)";

                    /*String sql = "insert into detallesajustes "
                    + "(id_ajuste,id_producto,cantidad_ajuste) "
                    + "values (?,?,?)";*/
                    System.out.println(sql1);
                    try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql1)) {
                        ps1.setInt(1, detallepedido.getPedidoproveedor().getId_pedidoproveedor());
                        ps1.setInt(2, detallepedido.getProducto().getId_producto());
                        ps1.setInt(3, detallepedido.getCantidad_detallepedidop());
                        ps1.setInt(4, detallepedido.getPreciototal_detallepedidop());
                        ps1.setInt(5, detallepedido.getCantidad_detallepedidop());
                        ps1.setString(6, detallepedido.getEstado_pedidop());

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

    public static boolean modificar(DetallesPedidosc detallepedidop) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int d = 0;
            String sql = "update detallepedidoproveedores set "
                    + "id_producto=?,"
                    + "cantidad_faltante=" + d + ","
                    + "cantidad_detallepedidop=?, "
                    + "cantidad_entrante=?, "
                    + "preciototal_detallepedidop=? "
                    + "where id_detallepedidop=?";
             System.out.println("--> Grabado" +sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallepedidop.getProducto().getId_producto());
                ps.setInt(2, detallepedidop.getCantidad_detallepedidop());
                ps.setInt(3, detallepedidop.getCantidad_detallepedidop());
                ps.setInt(4, detallepedidop.getPreciototal_detallepedidop());
                ps.setInt(5, detallepedidop.getId_detallepedidop());
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
    
    public static boolean modificarFaltante(DetallesPedidosc detallepedidop) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallepedidoproveedores set "
                    + "cantidad_entrante=?,"
                    + "cantidad_faltante=?,"
                    + "cantidad_detallepedidop=? "
                    + "where id_detallepedidop=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallepedidop.getCantidad_entrante());
                ps.setInt(2, detallepedidop.getCantidad_faltante());
                ps.setInt(3, detallepedidop.getCantidad_detallepedidop());
                ps.setInt(4, detallepedidop.getId_detallepedidop());
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

    public static boolean eliminar(DetallesPedidosc detallepedidop) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallepedidoproveedores where id_detallepedidop=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallepedidop.getId_detallepedidop());
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

    public static boolean eliminarPrueva(DetallesPedidosc detallepedidoc) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallepedidoproveedores where id_pedidoproveedor=" + detallepedidoc.getPedidoproveedor().getId_pedidoproveedor();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static String buscarIdPedidoFactura(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallepedidoproveedores  dp "
                        + "left join pedidoproveedores p on p.id_pedidoproveedor=dp.id_pedidoproveedor "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where dp.id_pedidoproveedor=" + id + " "
                        + "order by id_detallepedidop";
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
                        int cantidad1 = rs.getInt("cantidad_detallepedidop");
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
                        liqiva10 = total10 * 10 / 100;

                        totales = liqiva5 + liqiva10;

                        totalf = total5 + totale + total10;
                        totalt = total5 + totale + total10 + totales;

                        BigDecimal cantidad = rs.getBigDecimal("cantidad_detallepedidop");

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
                                + "<td>" + rs.getString("id_detallepedidop") + "</td>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getString("codigo_producto") + "</td>"
                                + "<td>" + rs.getString("precioventa_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>" + df.format(subtotalp) + "</td>"
                                + "<td class='centrado'>"
                                + "<button id='lapiz' onclick='editarLinea(" + rs.getString("id_detallepedidop") + ")'"
                                + " type='button' class='btn bordo bordo1 btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button><button id='menos' onclick='''  type='button' class='btn bordo bordo1 btn-sm'><span class='glyphicon glyphicon-trash'> </span></button> </td>"
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

    public static String buscarIdPedidoFactu(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallecompras  dp "
                        + "left join facturacompras p on p.id_compra=dp.id_compra "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where dp.id_compra=" + id + " "
                        + "order by id_detallecompra";
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
                        int cantidad1 = rs.getInt("cantidad_detallecompra");
                        int venta = rs.getInt("preciocompra_producto");
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

                        BigDecimal cantidad = rs.getBigDecimal("cantidad_detallecompra");

                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        BigDecimal subtotal = rs.getBigDecimal("preciocompra_producto");
                        total1 = total1.add(subtotal);
                        //para el subtotal
                        BigDecimal subtotalp = subtotal.multiply(cantidad);
                        stotal = stotal.add(subtotalp);
                        //iva
                        //BigDecimal subiva = rs.getBigDecimal("iva_producto");
                        //iva = iva.add(subiva);
                        //subtotaliva

                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallecompra") + "</td>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getString("codigo_producto") + "</td>"
                                + "<td>" + rs.getString("preciocompra_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>" + df.format(subtotalp) + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=7>No existen registros ...</td></tr>";
                    } else {
                        tabla += "<tr><td colspan=5>TOTAL</td><td class='centrado'>" + df.format(total) + " <td colspan=5 class='centrado'><b>" + df.format(totalf) + "</b></td></td>";

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
}
