<%@page import="utiles.Utiles"%>
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    int total = Integer.parseInt(request.getParameter("total"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    /*Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);*/
    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    venta.setTotal(total);

    if (VentasControlador.modificartotal(venta)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);

    out.print(obj);
    out.flush();
%>