<%@page import="controladores.DetallesPedidosControlador"%>
<%@page import="controladores.PedidosClientesControlador"%>
<%@page import="modelos.PedidosClientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedidocliente = Integer.parseInt(request.getParameter("id_pedidocliente"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    PedidosClientes pedidos = PedidosClientesControlador.buscarId(id_pedidocliente);
    if (pedidos != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {

        pedidos = new PedidosClientes();
        pedidos.setId_pedidocliente(0);

        java.sql.Date fecha_pedidov = new java.sql.Date(new java.util.Date().getTime());
        pedidos.setFecha_pedidov(fecha_pedidov);
        pedidos.setEstado_pedidov("pendiente");
    }

    String contenido_detalle = DetallesPedidosControlador.buscarIdPedidoClienteFactura(id_pedidocliente);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_pedidocliente", pedidos.getId_pedidocliente());
    obj.put("fecha_pedidov", String.valueOf(pedidos.getFecha_pedidov()));
    obj.put("estado_pedidov", pedidos.getEstado_pedidov());
    obj.put("contenido_detalle", contenido_detalle);
    out.print(obj);
    out.flush();
%>