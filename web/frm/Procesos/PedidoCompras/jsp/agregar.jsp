<%@page import="modelos.Personas"%>
<%@page import="controladores.PedidosProveedoresControlador"%>
<%@page import="modelos.PedidosProveedores"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedidoproveedor = Integer.parseInt(request.getParameter("id_pedidoproveedor"));
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));
    String sfecha_pedidop = request.getParameter("fecha_pedidop");
    java.sql.Date fecha_pedidop = Utiles.stringToSqlDate(sfecha_pedidop);
    // String estado_pedidop = request.getParameter("estado_pedidop");
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Personas persona = new Personas();
    persona.setId_persona(id_persona);

    PedidosProveedores pedido = new PedidosProveedores();
    pedido.setId_pedidoproveedor(id_pedidoproveedor);
    pedido.setFecha_pedidop(fecha_pedidop);
    pedido.setEstado_pedidop("PENDIENTE");
    pedido.setPersona(persona);

    if (PedidosProveedoresControlador.agregar(pedido)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_pedidoproveedor", String.valueOf(pedido.getId_pedidoproveedor()));
    out.print(obj);
    out.flush();

%>