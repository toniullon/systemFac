<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="controladores.DetallesPedidosControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.PedidosClientes"%>
<%@page import="modelos.DetallesPedidos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_detallepedidoc = Integer.parseInt(request.getParameter("id_detallepedidoc"));
    int cantidad_detallepedidoc = Integer.parseInt(request.getParameter("cantidad_detallepedidoc"));
    int id_pedidocliente = Integer.parseInt(request.getParameter("id_pedidocliente"));
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    DetallesPedidos detallepedido = new DetallesPedidos();
    detallepedido.setId_detallepedidoc(id_detallepedidoc);
    detallepedido.setCantidad_detallepedidoc(cantidad_detallepedidoc);

    PedidosClientes pedidocliente = new PedidosClientes();
    pedidocliente.setId_pedidocliente(id_pedidocliente);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);

    detallepedido.setPedidocliente(pedidocliente);
    detallepedido.setProducto(producto);

    /*if (DetallesPedidosControlador.modificar(detallepedido)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }*/
    Stocks stock = new Stocks();
    stock.setCantidad_stock(cantidad_detallepedidoc);
    stock.setProducto(producto);
    StocksControlador.buscarId(stock);

    if (stock.getCantidad_stock() != -1) {
        if (DetallesPedidosControlador.modificar(detallepedido)) {
            tipo = "success";
            mensaje = "Datos agregados.";

        } else {
            tipo = "success";
            mensaje = "Datos duplicados.";
        }
    } else {
        tipo = "success";
        mensaje = "Stock insuficiente";
    }

        JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    System.out.println("tipo" + tipo);
    obj.put("mensaje", mensaje);
    System.out.println("mensaje" + mensaje);
    obj.put("cantidad_stock", stock.getCantidad_stock());
    out.print(obj);
    out.flush();

%>