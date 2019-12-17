<%@page import="controladores.DetallesPedidosControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.PedidosClientes"%>
<%@page import="modelos.DetallesPedidos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallepedidoc = Integer.parseInt(request.getParameter("id_detallepedidoc"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesPedidos detallepedidoc = DetallesPedidosControlador.buscarId(id_detallepedidoc);
    if (detallepedidoc != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallepedidoc = new DetallesPedidos();
        detallepedidoc.setId_detallepedidoc(0);
        detallepedidoc.setCantidad_detallepedidoc(0);

        PedidosClientes pedidocliente = new PedidosClientes();
        pedidocliente.setId_pedidocliente(0);
        detallepedidoc.setPedidocliente(pedidocliente);

        Productos producto = new Productos();
        producto.setId_producto(0);
        producto.setNombre_producto("");
        producto.setPrecioventa_producto(0);
        detallepedidoc.setProducto(producto);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_detallepedidoc", detallepedidoc.getId_detallepedidoc());
    obj.put("id_pedidocliente", detallepedidoc.getPedidocliente().getId_pedidocliente());
    obj.put("id_producto", detallepedidoc.getProducto().getId_producto());
    obj.put("codigo_producto", detallepedidoc.getProducto().getCodigo_producto());
    obj.put("nombre_producto", detallepedidoc.getProducto().getNombre_producto());
    obj.put("precioventa_producto", detallepedidoc.getProducto().getPrecioventa_producto());
    obj.put("cantidad_detallepedidoc", detallepedidoc.getCantidad_detallepedidoc());
    out.print(obj);
    out.flush();
%>