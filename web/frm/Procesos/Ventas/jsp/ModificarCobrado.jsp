<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="modelos.DetallesCajas"%>
<%@page import="modelos.Personas"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    int cobrado_caja = Integer.parseInt(request.getParameter("total"));
    // String sfecha_pedidov = request.getParameter("fecha_pedidov");
    // java.sql.Date fecha_pedidov = Utiles.stringToSqlDate(sfecha_pedidov);

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    // venta.setPersona(persona);
    DetallesCajas detallecaja = new DetallesCajas();
    detallecaja.setCobrado_caja(cobrado_caja);
    detallecaja.setVenta(venta);

    if (VentasControlador.modificarcobrado(venta)) {

        DetallesCajasControlador.modificard(detallecaja);
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_venta", String.valueOf(venta.getId_venta()));
    obj.put("condicion_venta", String.valueOf(venta.getCondicion_venta()));
    System.out.println("numero factura" + venta.getId_venta());
    out.print(obj);
    out.flush();
%>