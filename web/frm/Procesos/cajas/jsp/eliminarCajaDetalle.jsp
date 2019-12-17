<%@page import="modelos.Cajas"%>
<%@page import="controladores.StockControlador"%>
<%@page import="modelos.Stock"%>
<%@page import="modelos.FacturaVentas"%>
<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="modelos.DetallesCajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));
    int id_factura_venta = Integer.parseInt(request.getParameter("id_factura_venta"));
    int id_detallecaja = Integer.parseInt(request.getParameter("id_caja"));
    int importe = Integer.parseInt(request.getParameter("importe"));
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);
    
    DetallesCajas detallecaja = new DetallesCajas();
    detallecaja.setId_detallecaja(id_detallecaja);
    FacturaVentas venta = new FacturaVentas();
    venta.setId_factura_venta(id_factura_venta);



    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>