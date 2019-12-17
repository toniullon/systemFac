<%@page import="controladores.DetallesPedidoscControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.PedidosProveedores"%>
<%@page import="modelos.DetallesPedidosc"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallepedidop = Integer.parseInt(request.getParameter("id_detallepedidop"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesPedidosc detallepedidop = DetallesPedidoscControlador.buscarId(id_detallepedidop);
    if (detallepedidop != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallepedidop = new DetallesPedidosc();
        detallepedidop.setId_detallepedidop(0);
        detallepedidop.setCantidad_detallepedidop(0);

        PedidosProveedores pedidoproveedor = new PedidosProveedores();
        pedidoproveedor.setId_pedidoproveedor(0);
        detallepedidop.setPedidoproveedor(pedidoproveedor);

        Productos producto = new Productos();
        producto.setId_producto(0);
        producto.setNombre_producto("");
        producto.setPrecioventa_producto(0);
        detallepedidop.setProducto(producto);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_detallepedidop", detallepedidop.getId_detallepedidop());
    obj.put("id_pedidoproveedor", detallepedidop.getPedidoproveedor().getId_pedidoproveedor());
    obj.put("id_producto", detallepedidop.getProducto().getId_producto());
    obj.put("codigo_producto", detallepedidop.getProducto().getCodigo_producto());
    obj.put("nombre_producto", detallepedidop.getProducto().getNombre_producto());
    obj.put("preciocompra_producto", detallepedidop.getProducto().getPreciocompra_producto());
    obj.put("cantidad_detallepedidop", detallepedidop.getCantidad_detallepedidop());
    out.print(obj);
    out.flush();
%>