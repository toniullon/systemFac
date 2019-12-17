<%@page import="modelos.DetallesPedidos"%>
<%@page import="controladores.DetallesPedidosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String id_producto = request.getParameter("id_producto");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    DetallesPedidos detallepedido = new DetallesPedidos();
    detallepedido.setProducto(id_producto);

    DetallesPedidosControlador.buscarDetallePedido(detallepedido);
    if (detallepedido.getId_detallepedidoc()== 0) {
        tipo = "success";
        mensaje = "Datos de Nombre repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_producto", detallepedido.getProducto());

    out.print(obj);
    out.flush();
%>