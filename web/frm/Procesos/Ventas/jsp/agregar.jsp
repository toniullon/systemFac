<%@page import="controladores.PedidosClientesControlador"%>
<%@page import="modelos.PedidosClientes"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedidocliente= Integer.parseInt(request.getParameter("id_pedidocliente"));
   
    String sfecha_pedidov = request.getParameter("fecha_pedidov");
    java.sql.Date fecha_pedidov = Utiles.stringToSqlDate(sfecha_pedidov);
     // String estado_pedidov = request.getParameter("estado_pedidov");
    String tipo = "error";
    String mensaje = "Datos no agregados.";

    
    PedidosClientes pedido = new PedidosClientes();
    pedido.setId_pedidocliente(id_pedidocliente);
    pedido.setFecha_pedidov(fecha_pedidov);
    pedido.setEstado_pedidov("pendiente");
   
    if (PedidosClientesControlador.agregar(pedido)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_pedidocliente", String.valueOf(pedido.getId_pedidocliente()));
    out.print(obj);
    out.flush();

%>