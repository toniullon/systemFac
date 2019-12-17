<%@page import="modelos.PedidosProveedores"%>
<%@page import="controladores.DetallesPedidoscControlador"%>
<%@page import="modelos.DetallesPedidosc"%>
<%@page import="modelos.Personas"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.Compras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedidoproveedor = Integer.parseInt(request.getParameter("id_pedidoproveedor"));
   // int id_persona = Integer.parseInt(request.getParameter("id_persona"));
    // String sfecha_pedidov = request.getParameter("fecha_pedidov");
    // java.sql.Date fecha_pedidov = Utiles.stringToSqlDate(sfecha_pedidov);

    String tipo = "error";
    String mensaje = "Datos no modificados.";

   /* Personas persona = new Personas();
    persona.setId_persona(id_persona);*/

    PedidosProveedores pedidoproveedor = new PedidosProveedores();
    pedidoproveedor.setId_pedidoproveedor(id_pedidoproveedor);
    
    DetallesPedidosc detallepedidoc = new DetallesPedidosc();
    detallepedidoc.setPedidoproveedor(pedidoproveedor);
   // compra.setPersona(persona);
    
    if (DetallesPedidoscControlador.modificarfacturado(detallepedidoc)) {
        tipo = "success";
        mensaje = "Datos modificados.";

    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
   // obj.put("id_c", String.valueOf(compra.getId_compra()));
   // obj.put("condicion_compra", String.valueOf(compra.getCondicion_compra()));
   // System.out.println("numero factura" + compra.getId_compra());
    out.print(obj);
    out.flush();
%>