<%@page import="controladores.PedidosClientesControlador"%>
<%@page import="controladores.DetallesPedidosControlador"%>
<%@page import="modelos.DetallesPedidos"%>
<%@page import="modelos.PedidosClientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedidocliente = Integer.parseInt(request.getParameter("id_pedidocliente"));
    //System.out.println("EL STOCK" + id_ajuste_stock);
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    PedidosClientes pedidocliente = new PedidosClientes();
    pedidocliente.setId_pedidocliente(id_pedidocliente);

    DetallesPedidos detallepedidoc = new DetallesPedidos();
    detallepedidoc.setPedidocliente(pedidocliente);

    
    // PedidosClientes = new PedidosClientes();
    // pedidocliente.setId_pedidocliente(id_pedidocliente);
    //  Stocks stock = new Stocks();
    //  stock.setId_stock(id_ajuste_stock);
    //stock.setCantidad_stock(cantidad_ajuste_stock);
    //stock.setProducto(producto);
    // if (StocksControlador.sumar(stock)) {

    if (DetallesPedidosControlador.eliminarPrueva(detallepedidoc)) {
        if (PedidosClientesControlador.eliminar(pedidocliente)) {
            tipo = "success";
            mensaje = "Datos eliminados.";
        }
    } else {
        tipo = "success";
        mensaje = "Datos no eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>