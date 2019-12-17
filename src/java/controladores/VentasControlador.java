package controladores;

import java.math.BigDecimal;
import java.sql.Date;
import modelos.Ventas;
import modelos.Personas;
import utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import modelos.DetallesVentas;
import utiles.Utiles;

public class VentasControlador {

    public static Ventas buscarId(int id) {
        Ventas ventas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ventas v "
                        + "left join personas c on v.id_persona=c.id_persona "
                        + "where estado_venta !='COBRADO' and id_venta=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        ventas = new Ventas();
                        ventas.setId_venta(rs.getInt("id_venta"));
                        ventas.setFecha_venta(rs.getDate("fecha_venta"));
                        ventas.setEstado_venta(rs.getString("estado_venta"));

                        Personas persona = new Personas();
                        persona.setId_persona(rs.getInt("id_persona"));
                        persona.setNombre_persona(rs.getString("nombre_persona"));
                        persona.setRuc_persona(rs.getString("ruc_persona"));
                        ventas.setPersona(persona);

                        ventas.setNumero_venta(rs.getInt("numero_venta"));
                        ventas.setCondicion_venta(rs.getString("condicion_venta"));
                    } else {

                        try {

                            String sqli = "SELECT COUNT (*) AS Ultimo, COUNT(numero_venta) FROM ventas";
                            try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                                int num = 0;
                                ResultSet rsi = psi.executeQuery();
                                if (rsi.next()) {
                                    ventas = new Ventas();
                                    num = rsi.getInt("Ultimo");
                                    ventas.setId_venta(0);
                                    ventas.setNumero_venta(num + 1);

                                    ventas.setEstado_venta("PENDIENTE");
                                    java.sql.Date fecha_venta = new java.sql.Date(new java.util.Date().getTime());
                                    ventas.setFecha_venta(fecha_venta);

                                    Personas persona = new Personas();
                                    persona.setId_persona(0);
                                    persona.setNombre_persona("");
                                    persona.setRuc_persona("");
                                    ventas.setPersona(persona);

                                } else {
                                    num = 1;
                                }

                                psi.close();
                            }
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
        return ventas;
    }

    public static String buscarNombre(Date DESDE, Date HASTA, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from facturaventas v "
                        + "left join personas c on c.id_persona=v.id_persona "
                        + "WHERE fecha_venta >= ' "
                        + DESDE
                        + "%' "
                        +"and fecha_venta <= ' "
                        + HASTA
                        + "%' "
                        + "and estado_venta='COBRADO'  order by id_venta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_venta") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "<td>" + rs.getString("numero_venta") + "</td>"
                                + "<td>" + rs.getString("totalpagado_venta") + "</td>"
                                + "<td>" + rs.getString("estado_venta") + "</td>"
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
    public static String buscarNombreV(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from facturaventas v "
                        + "left join personas c on c.id_persona=v.id_persona "
                        + "where upper(estado_venta) like '%"
                        + nombre
                        + "%' "
                         + "and estado_venta='COBRADO' order by id_venta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_venta") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "<td>" + rs.getString("numero_venta") + "</td>"
                                + "<td>" + rs.getString("totalpagado_venta") + "</td>"
                                + "<td>" + rs.getString("estado_venta") + "</td>"
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

    public static boolean agregar(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            Date v1 = venta.getFecha_venta();
            String v2 = venta.getEstado_venta();
            String v3 = venta.getCondicion_venta();
            int v4 = venta.getPedidocliente().getId_pedidocliente();

            String sql = "insert into facturaventas(fecha_venta ,estado_venta, condicion_venta ,id_pedidocliente) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "','" + v4 + "')";

            //   String cangrejo = "insert into detalleventas (cantidad_detalleventa, id_producto) select cantidad_detallepedidoc, id_producto from detallepedidoclientes, facturaventas where detallepedidoclientes.id_pedidocliente = facturaventas.id_pedidocliente and detallepedidoclientes.id_pedidocliente = 14";            
            //     System.out.println("cangrejo"+cangrejo);
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                //     Conexion.getSt().executeUpdate(cangrejo, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_venta = keyset.getInt(1);
                    venta.setId_venta(id_venta);
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

    public static boolean agregarDetalle(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {

            String cangrejo = "insert into detalleventas (cantidad_detalleventa, id_producto, id_venta,preciototal_detalleventa) select cantidad_detallepedidoc, id_producto, id_venta, preciototal_detallepedidoc from detallepedidoclientes, facturaventas where detallepedidoclientes.id_pedidocliente = facturaventas.id_pedidocliente and detallepedidoclientes.id_pedidocliente = " + venta.getPedidocliente().getId_pedidocliente() + "";

            System.out.println("cangrejo" + cangrejo);

            try {
                Conexion.getSt().executeUpdate(cangrejo, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_venta = keyset.getInt(1);
                    venta.setId_venta(id_venta);
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

    public static boolean modificar(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ventas set id_persona=?,"
                    + "numero_venta=?,"
                    + "condicion_venta=?,"
                    + "where id_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, venta.getPersona().getId_persona());
                ps.setInt(3, venta.getNumero_venta());
                //ps.setString(4, venta.getEstado_venta());
                ps.setInt(6, venta.getId_venta());
                ps.setString(7, venta.getCondicion_venta());
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
    }

    public static boolean eliminar(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ventas where id_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, venta.getId_venta());
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

    public static boolean modificarestado(Ventas venta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ventas set estado_venta='ANULADO'  "
                    + " where id_venta=" + venta.getId_venta();
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

    public static boolean modificarventascliente(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturaventas set id_persona = " + venta.getPersona().getId_persona() + " where id_venta=" + venta.getId_venta();
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
    
     public static boolean modificartotal(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturaventas set totalpagado_venta = " + venta.getTotal()+ " where id_venta=" + venta.getId_venta();
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

    public static boolean modificaranular(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturaventas set estado_venta='ANULADO'  "
                    + " where id_venta=" + venta.getId_venta();
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

    public static boolean modificarcobrado(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturaventas set estado_venta='COBRADO'  "
                    + " where id_venta=" + venta.getId_venta();
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

    public static Ventas buscarNumFactura(int id) throws SQLException {
        Ventas ventas = null;

        if (Conexion.conectar()) {
            try {
                String sqli = "SELECT COUNT(*) AS Ultimo, COUNT(numero_venta) FROM ventas ";

                System.out.println("sss" + sqli);
                try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                    ResultSet rsi = psi.executeQuery();
                    if (rsi.next()) {
                        ventas = new Ventas();

                        ventas.setNumero_venta(rsi.getInt("Ultimo"));

                    }
                    psi.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }

    /*public static Ventas buscarTotalfactura(int id) throws SQLException {
        Ventas ventas = null;

        if (Conexion.conectar()) {
            try {
                String sqli = "select v.numero_venta,v.id_venta,SUM(total) as total from ventas v "
                        + "left join detallesventas dv on dv.id_venta=v.id_venta "
                        + "where v.id_venta=? "
                        + "group by v.id_venta";

                System.out.println("sss" + id);

                try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                    psi.setInt(1, id);
                    ResultSet rsi = psi.executeQuery();
                    if (rsi.next()) {
                        ventas = new Ventas();
                        ventas.setId_venta(rsi.getInt("id_venta"));
                        ventas.setNumero_venta(rsi.getInt("numero_venta"));
                        ventas.setTotal(rsi.getInt("total"));

                    }
                    psi.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }*/
    public static Ventas buscarId3(int id) {
        Ventas ventas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesventas dv "
                        + "left join ventas v on v.id_venta=dv.id_venta "
                        + "left join productos p on p.id_producto=dv.id_producto "
                        + "where dv.id_venta=" + id + " "
                        + "order by id_detalle_venta";
                System.out.println("sqltotal " + sql + id);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    System.out.println("sqltotal " + ps);
                    ResultSet rs = ps.executeQuery();

                    //BigDecimal iva = BigDecimal.ZERO;
                    if (rs.next()) {
                        int cantidad = rs.getInt("cantidad_producto_venta");
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
                            int cantidad1 = rs.getInt("cantidad_producto_venta");
                            int venta = rs.getInt("precio_venta_producto");
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

                            liqiva5 = total5 / 21;
                            liqiva10 = total10 / 11;

                            totales = liqiva5 + liqiva10;

                            totalf = total5 + totale + total10;
                            totalt = total5 + totale + total10 + totales;

                            // System.out.println("total"+total);
                            //iva
                            //BigDecimal subiva = rs.getBigDecimal("iva_producto");
                            //iva = iva.add(subiva);
                            //subtotaliva
                        }

                        String sql2 = "select v.id_venta, v.numero_venta, sum(precio_venta_producto * " + cantidad + ") +" + totales + "as TotalaPagar from detallesventas dv left join ventas v on v.id_venta=dv.id_venta left join productos p on p.id_producto=dv.id_producto where dv.id_venta=" + id + " group by v.id_venta, v.numero_venta";
                        System.out.println("total " + sql2);
                        try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql2)) {

                            ResultSet rs1 = ps1.executeQuery();
                            if (rs1.next()) {
                                int total_pagar = rs1.getInt("TotalaPagar");
                                ventas = new Ventas();
                                ventas.setId_venta(id);
                                ventas.setNumero_venta(rs.getInt("numero_venta"));
                                ventas.setTotal(total_pagar);

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
        return ventas;
    }

    public static Ventas buscartotal(int id) {
        Ventas ventas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalleventas dv "
                        + "left join facturaventas v on v.id_venta=dv.id_venta "
                        + "left join productos p on p.id_producto=dv.id_producto "
                        + "where dv.id_venta=" + id + " "
                        + "order by id_detalleventa";
                System.out.println("sqltotal " + sql + id);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        int factura = rs.getInt("numero_venta");
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
                            int cantidad = rs.getInt("cantidad_detalleventa");
                            int venta = rs.getInt("precioventa_producto");
                            if (iva == 0) {

                                totalexentas = venta * cantidad;
                                totaliva5 = 0;
                                totaliva10 = 0;
                                totale = totale + totalexentas;

                            } else if (iva == 5) {

                                totalexentas = 0;
                                totaliva10 = 0;

                                totaliva5 = venta * cantidad;
                                total5 = total5 + totaliva5;

                            } else {

                                totalexentas = 0;
                                totaliva5 = 0;
                                totaliva10 = venta * cantidad;
                                total10 = total10 + totaliva10;
                            }

                            liqiva5 = total5 * 5 / 100;
                            liqiva10 = total10 * 5 / 100;

                            totales = liqiva5 + liqiva10;

                        }
                        String sql2 = "select v.id_venta, v.numero_venta, sum(precioventa_producto * to_number(cantidad_detalleventa,'999999999999D99')) as TotalaPagar from detalleventas dv left join facturaventas v on v.id_venta=dv.id_venta left join productos p on p.id_producto=dv.id_producto where dv.id_venta=" + id + " group by v.id_venta";
                        System.out.println("total " + sql2);
                        try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql2)) {

                            ResultSet rs1 = ps1.executeQuery();
                            if (rs1.next()) {
                                int total_pagar = rs1.getInt("TotalaPagar");
                                int resul;
                                resul = total_pagar/11;
                                
                                //int factura = rs1.getInt("numero_factura");
                                ventas = new Ventas();
                                ventas.setId_venta(id);
                                ventas.setNumero_venta(factura);
                                ventas.setTotal(total_pagar);
                                ventas.setIva10(resul);

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
        return ventas;
    }

    public static Ventas buscartotalbien(int id) {
        Ventas ventas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesventas dv "
                        + "left join ventas v on v.id_venta=dv.id_venta "
                        + "left join productos p on p.id_producto=dv.id_producto "
                        + "where dv.id_venta=" + id + " "
                        + "order by id_detalle_venta";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        DecimalFormat df = new DecimalFormat("#,###");
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
                            int cantidad1 = rs.getInt("cantidad_producto_venta");
                            int venta = rs.getInt("precio_venta_producto");
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

                        }
                        String sqlbien = "select v.id_venta, v.numero_venta, sum(precio_venta_producto * to_number(cantidad_producto_venta,'999999999999D99')) + " + totales + " as TotalaPagar from detallesventas dv left join ventas v on v.id_venta=dv.id_venta left join productos p on p.id_producto=dv.id_producto where dv.id_venta=" + id + " group by v.id_venta";
                        System.out.println("total " + sqlbien);

                        try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sqlbien)) {

                            ResultSet rs1 = ps1.executeQuery();
                            if (rs1.next()) {
                                int total_pagar = rs1.getInt("TotalaPagar");
                                //int factura = rs1.getInt("numero_venta");
                                ventas = new Ventas();
                                ventas.setId_venta(id);
                                ventas.setNumero_venta(rs.getInt("numero_venta"));
                                ventas.setTotal(total_pagar);

                            }
                            System.out.println("Total a Pagar " + sqlbien);
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
        return ventas;
    }

    public static boolean modificarestadocobro(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturaventas set estado_venta='COBRADO'"
                    + " where id_venta=" + venta.getId_venta();
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
    /* public static boolean AgregarDetalleVenta(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "Select * from stocks where  "
                    + "id_producto='" + stock.getProducto().getId_producto() + "'";
            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (!(rs.next())) {
                    String sql2 = "insert into stocks(id_producto, cantidad_stock)"
                            + "values('" + stock.getProducto().getId_producto() + "',"
                            + "'" + stock.getCantidad_stock() + "')";
                    System.out.println("sql" + sql2);
                    try {
                        Conexion.getSt().executeUpdate(sql2);
                        valor = true;
                    } catch (SQLException ex2) {
                        System.err.println("Error:" + ex2);
                    }

                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex);
            }
        }
        return valor;
    }*/
 /* public static boolean AgregarDetalleVenta(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {

            try {

                String sql = "select cantidad_detallepedidoc, id_producto from detallepedidoclientes, facturaventas where detallepedidoclientes.id_pedidocliente = facturaventas.id_pedidocliente"
                        + "and detallepedidoclientes.id_pedidocliente =" + venta.getPedidocliente().getId_pedidocliente() + "";

                //   System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        String sqldos = "insert into detalleventas (cantidad_detalleventa, id_producto) select cantidad_detallepedidoc, id_producto from detallepedidoclientes, facturaventas where detallepedidoclientes.id_pedidocliente = facturaventas.id_pedidocliente"
                                + "and detallepedidoclientes.id_pedidocliente = 14";
                    }
                    valor = true;
                    ps.close();

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
    }*/
    
    public static String DescontarVentas(Ventas venta) throws SQLException {

        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalleventas where id_venta ="+ venta.getId_venta();
                     

                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                   
                    while (rs.next()) {
                        
                        String cangrejo = "update stocks set cantidad_stock= cantidad_stock - " + rs.getString("cantidad_detalleventa") + " "
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
