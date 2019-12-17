<%@page import="controladores.DetallesPedidoscControlador"%>
<%@page import="controladores.PedidosProveedoresControlador"%>
<%@page import="modelos.PedidosProveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_compra = Integer.parseInt(request.getParameter("id_compra"));
    int id_pedidoproveedor = Integer.parseInt(request.getParameter("id_pedidoproveedor"));
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    PedidosProveedores pedidos = PedidosProveedoresControlador.buscarIdFacturado(id_pedidoproveedor);
    if (pedidos != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {

        pedidos = new PedidosProveedores();
        pedidos.setId_pedidoproveedor(0);

        java.sql.Date fecha_pedidop = new java.sql.Date(new java.util.Date().getTime());
        pedidos.setFecha_pedidop(fecha_pedidop);
        pedidos.setEstado_pedidop("pendiente");
    }

    String contenido_detallep = DetallesPedidoscControlador.buscarIdPedidoFactu(id_compra);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_pedidoproveedor", pedidos.getId_pedidoproveedor());
    obj.put("fecha_pedidop", String.valueOf(pedidos.getFecha_pedidop()));
    //  obj.put("estado_pedidop", pedidos.getEstado_pedidop());
    obj.put("contenido_detallep", contenido_detallep);
    obj.put("id_persona", pedidos.getPersona().getId_persona());
    obj.put("correo_persona", pedidos.getPersona().getCorreo_persona());
    obj.put("nombre_persona", pedidos.getPersona().getNombre_persona());
    obj.put("ruc_persona", pedidos.getPersona().getRuc_persona());
    out.print(obj);
    out.flush();
%>