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
import modelos.Compras;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrador
 */
public class ComprasControlador {

    public static boolean agregar(Compras compra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            Date v1 = compra.getFecha_compra();
            String v2 = compra.getEstado_compra();
            String v3 = compra.getCondicion_compra();
            int v4 = compra.getPedidoproveedor().getId_pedidoproveedor();

            String sql = "insert into facturacompras(fecha_compra ,estado_compra, condicion_compra ,id_pedidoproveedor) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "','" + v4 + "')";

            //   String cangrejo = "insert into detallecompras (cantidad_detallecompra, id_producto) select cantidad_detallepedidoc, id_producto from detallepedidoproveedores, facturacompras where detallepedidoproveedores.id_pedidoproveedor = facturacompras.id_pedidoproveedor and detallepedidoproveedores.id_pedidoproveedor = 14";            
            //     System.out.println("cangrejo"+cangrejo);
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                //     Conexion.getSt().executeUpdate(cangrejo, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_compra = keyset.getInt(1);
                    compra.setId_compra(id_compra);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean agregarDetalle(Compras compra) {
        boolean valor = false;
        if (Conexion.conectar()) {

            String cangrejo = "insert into detallecompras (cantidad_detallecompra,"
                    + " id_producto, id_compra, preciototal_detallecompra)"
                    + " select cantidad_entrante, id_producto, id_compra, "
                    + "preciototal_detallepedidop from detallepedidoproveedores,"
                    + " facturacompras where  "
                    + "detallepedidoproveedores.id_pedidoproveedor "
                    + "= facturacompras.id_pedidoproveedor and detallepedidoproveedores.estado_pedidop='PENDIENTE' and facturacompras.estado_compra!='FINALIZADA' and detallepedidoproveedores.id_pedidoproveedor = " + compra.getPedidoproveedor().getId_pedidoproveedor() + "";

            System.out.println("cangrejo" + cangrejo);

            try {
                Conexion.getSt().executeUpdate(cangrejo, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_compra = keyset.getInt(1);
                    compra.setId_compra(id_compra);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean agregardetalle(Compras compra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "select * from facturacompras "
                    + "where id_pedidoproveedor= " + compra.getPedidoproveedor().getId_pedidoproveedor();
            System.out.println("sql " + sql);

            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    String cangrejo = "insert into detallecompras (cantidad_detallecompra,"
                            + " id_producto, id_compra, preciototal_detallecompra)"
                            + " select cantidad_entrante, id_producto, id_compra, "
                            + "preciototal_detallepedidop from detallepedidoproveedores,"
                            + " facturacompras where  "
                            + "detallepedidoproveedores.id_pedidoproveedor "
                            + "= facturacompras.id_pedidoproveedor and detallepedidoproveedores.cantidad_entrante > 0 and detallepedidoproveedores.estado_pedidop='PENDIENTE' and  detallepedidoproveedores.id_pedidoproveedor = " + compra.getPedidoproveedor().getId_pedidoproveedor() + "";

                    System.out.println("cangrejo" + cangrejo);

                    try {
                        Conexion.getSt().executeUpdate(cangrejo, Statement.RETURN_GENERATED_KEYS);
                        ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                        if (keyset.next()) { 
                            int id_compra = keyset.getInt(1);
                            compra.setId_compra(id_compra);
                            
                            Conexion.getConn().setAutoCommit(false);
                        }
                        valor = true;
                    } catch (SQLException ex) {
                        System.out.println("--> " + ex.getLocalizedMessage());
                    }

                } else {

                    Date v1 = compra.getFecha_compra();
                    String v2 = compra.getEstado_compra();
                    String v3 = compra.getCondicion_compra();
                    int v4 = compra.getPedidoproveedor().getId_pedidoproveedor();

                    String sql1 = "insert into facturacompras(fecha_compra ,estado_compra, condicion_compra ,id_pedidoproveedor) "
                            + "values('" + v1 + "','" + v2 + "','" + v3 + "','" + v4 + "')";

                    String cangrejo = "insert into detallecompras (cantidad_detallecompra,"
                            + " id_producto, id_compra, preciototal_detallecompra)"
                            + " select cantidad_entrante, id_producto, id_compra, "
                            + "preciototal_detallepedidop from detallepedidoproveedores,"
                            + " facturacompras where  "
                            + "detallepedidoproveedores.id_pedidoproveedor "
                            + "= facturacompras.id_pedidoproveedor and detallepedidoproveedores.cantidad_entrante > 0 and detallepedidoproveedores.estado_pedidop='PENDIENTE' and  detallepedidoproveedores.id_pedidoproveedor = " + compra.getPedidoproveedor().getId_pedidoproveedor() + "";

                    //   String cangrejo = "insert into detallecompras (cantidad_detallecompra, id_producto) select cantidad_detallepedidoc, id_producto from detallepedidoproveedores, facturacompras where detallepedidoproveedores.id_pedidoproveedor = facturacompras.id_pedidoproveedor and detallepedidoproveedores.id_pedidoproveedor = 14";            
                    //     System.out.println("cangrejo"+cangrejo);
                    System.out.println("--> " + sql1);
                    try {
                        Conexion.getSt().executeUpdate(sql1, Statement.RETURN_GENERATED_KEYS);
                            Conexion.getSt().executeUpdate(cangrejo, Statement.RETURN_GENERATED_KEYS);
                        ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                        if (keyset.next()) {
                            int id_compra = keyset.getInt(1);
                            compra.setId_compra(id_compra);
                            Conexion.getConn().setAutoCommit(false);
                        }
                        valor = true;
                    } catch (SQLException ex) {
                        System.out.println("--> " + ex.getLocalizedMessage());
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
    
    public static boolean NumeroFactura(Compras compra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturacompras set numero_factura = " + compra.getNumero_compra() + " where id_compra=" + compra.getId_compra();
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

    public static boolean modificaranular(Compras compra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturacompras set estado_compra='ANULADO'  "
                    + " where id_compra=" + compra.getId_compra();
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

    public static boolean modificarcobrar(Compras compra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturacompras set estado_compra='FINALIZADA'  "
                    + " where id_compra=" + compra.getId_compra();
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

    public static Compras buscartotal(int id) {
        Compras compras = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallecompras dv "
                        + "left join facturacompras v on v.id_compra=dv.id_compra "
                        + "left join productos p on p.id_producto=dv.id_producto "
                        + "where dv.id_compra=" + id + " "
                        + "order by id_detallecompra";
                System.out.println("sqltotal " + sql + id);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        int factura = rs.getInt("numero_factura");
                        int totales = 0;
                        int totale = 0;
                        int total5 = 0;
                        int total10 = 0;
                        int iva = 0;
                        int totalexentas = 0;
                        int totaliva5 = 0;
                        int totaliva10 = 0;

                        int liqiva5 = 0;
                        int liqiva10 = 0;

                        while (rs.next()) {
                            iva = rs.getInt("iva_producto");
                            int cantidad = rs.getInt("cantidad_detallecompra");
                            int compra = rs.getInt("preciocompra_producto");
                            if (iva == 0) {

                                totalexentas = compra * cantidad;
                                totaliva5 = 0;
                                totaliva10 = 0;
                                totale = totale + totalexentas;

                            } else if (iva == 5) {

                                totalexentas = 0;
                                totaliva10 = 0;

                                totaliva5 = compra * cantidad;
                                total5 = total5 + totaliva5;

                            } else {

                                totalexentas = 0;
                                totaliva5 = 0;
                                totaliva10 = compra * cantidad;
                                total10 = total10 + totaliva10;
                            }

                            liqiva5 = total5 * 5 / 100;
                            liqiva10 = total10 * 5 / 100;

                            totales = liqiva5 + liqiva10;

                        }
                        String sql2 = "select v.id_compra, v.numero_factura, sum(preciocompra_producto * to_number(cantidad_detallecompra,'999999999999D99')) as TotalaPagar from detallecompras dv left join facturacompras v on v.id_compra=dv.id_compra left join productos p on p.id_producto=dv.id_producto where dv.id_compra=" + id + " group by v.id_compra";
                        System.out.println("total " + sql2);
                        try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql2)) {

                            ResultSet rs1 = ps1.executeQuery();
                            if (rs1.next()) {
                                int total_pagar = rs1.getInt("TotalaPagar");
                                int resul;
                                resul = total_pagar / 11;

                                //int factura = rs1.getInt("numero_factura");
                                compras = new Compras();
                                compras.setId_compra(id);
                                compras.setNumero_compra(factura);
                                compras.setTotal(total_pagar);
                                compras.setIva10(resul);

                            }
                            System.out.println("Total a Pagar " + sql2);
                            System.out.println(rs1.getString("TotalaPagar"));
                            ps1.close();
                        } catch (SQLException ex) {
                            System.out.println("--> " + ex.getLocalizedMessage());

                        }
                    }
                    ps.close();
                }

            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return compras;
    }

    public static boolean modificartotal(Compras compra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturacompras set totalpagado_compra = " + compra.getTotal() + " where id_compra=" + compra.getId_compra();
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

    public static String buscarNombre(Date DESDE, Date HASTA, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {

                String sql = "select * from facturacompras v, pedidoproveedores c, personas p where v.id_pedidoproveedor=c.id_pedidoproveedor and c.id_persona=p.id_persona "
                        + "and fecha_compra >= ' "
                        + DESDE
                        + "%' "
                        + "and fecha_compra <= ' "
                        + HASTA
                        + "%' "
                        + "and estado_compra='FINALIZADA' order by id_compra "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                //  + "<td>" + rs.getString("id_venta") + "</td>"
                                + "<td>" + rs.getString("numero_factura") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "<td>" + rs.getString("totalpagado_compra") + "</td>"
                                + "<td>" + rs.getString("estado_compra") + "</td>"
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

    public static String buscarNombreC(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {

                String sql = "select * from facturacompras v, pedidoproveedores c, personas p where v.id_pedidoproveedor=c.id_pedidoproveedor and c.id_persona=p.id_persona "
                        + "and upper(estado_compra) like '%%' "
                        + "and estado_compra='FINALIZADA' order by id_compra "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                //  + "<td>" + rs.getString("id_venta") + "</td>"
                                + "<td>" + rs.getString("numero_factura") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "<td>" + rs.getString("totalpagado_compra") + "</td>"
                                + "<td>" + rs.getString("estado_compra") + "</td>"
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
    
     public static Compras buscarId(Compras comprac) {
        Compras compras = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from facturacompras "
                        + "where  id_pedidoproveedor=?";

                /*String sql = "select * from ajustes_stock c "
                        + "left join usuarios u on u.id_usuario=c.id_usuario "
                        + "where id_ajuste_stock=?";*/
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, comprac.getPedidoproveedor().getId_pedidoproveedor());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        compras = new Compras();
                        compras.setId_compra(rs.getInt("id_compra"));
                        System.out.println("EL ID" +compras);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                Logger.getLogger(ComprasControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return compras;
    }
     public static String AumentarStock(Compras compra) {

        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallecompras where id_compra ="+ compra.getId_compra();
                     

                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                   
                    while (rs.next()) {
                        
                        String cangrejo = "update stocks set cantidad_stock= cantidad_stock + " + rs.getString("cantidad_detallecompra") + " "
                    + " where id_producto=" + rs.getString("id_producto") ;
                        
                        try (PreparedStatement sp = Conexion.getConn().prepareStatement(cangrejo)) {
                    ResultSet sr = sp.executeQuery();
                    }catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                   
                   }
            }
                }
                
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
