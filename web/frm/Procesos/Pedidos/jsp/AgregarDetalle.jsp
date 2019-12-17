<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="controladores.ProductosControlador"%>
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
    int preciototal_detallepedidoc = Integer.parseInt(request.getParameter("preciototal_detallepedidoc"));
    //int total_ajuste= con + 0; 

    //int con=total_detalleajuste;
    String tipo = "error";
    String mensaje = "Datos no agregados.";

    PedidosClientes pedidocliente = new PedidosClientes();
    pedidocliente.setId_pedidocliente(id_pedidocliente);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);

    DetallesPedidos detallepedido = new DetallesPedidos();
    detallepedido.setId_detallepedidoc(id_detallepedidoc);
    detallepedido.setCantidad_detallepedidoc(cantidad_detallepedidoc);
    detallepedido.setPreciototal_detallepedidoc(preciototal_detallepedidoc);
    detallepedido.setPedidocliente(pedidocliente);
    detallepedido.setProducto(producto);

    Stocks stock = new Stocks();
    stock.setCantidad_stock(cantidad_detallepedidoc);
    stock.setProducto(producto);
    StocksControlador.buscarId(stock);

    if (stock.getCantidad_stock() != -1) {
        if (DetallesPedidosControlador.buscarDuplicadosP(detallepedido)) {
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

    /*Stocks stock = new Stocks();
    stock.setCantidad_stock(cantidad_producto_venta);
    stock.setProducto(producto);
    StocksControlador.descontar(stock);
    
    if (stock.getCantidad_stock() != -1) {
        if (DetallesVentasControlador.agregar(detalleventa)) {
            tipo = "success";
            mensaje = "Datos agregados.";
        }
    } else {
        tipo = "success";
        mensaje = "Stock insuficiente";
    }*/
    //producto = new Productos();
    //producto.setId_producto(id_producto);
    /*producto = new Productos();
    producto.setId_producto(id_producto);*/
    //ProductosControlador.modificarc(producto);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    System.out.println("tipo" + tipo);
    obj.put("mensaje", mensaje);
    System.out.println("mensaje" + mensaje);
    obj.put("cantidad_stock", stock.getCantidad_stock());
    out.print(obj);
    out.flush();

%>