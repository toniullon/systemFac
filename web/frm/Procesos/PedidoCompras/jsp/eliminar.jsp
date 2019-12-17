<%@page import="controladores.PedidosProveedoresControlador"%>
<%@page import="controladores.DetallesPedidoscControlador"%>
<%@page import="modelos.DetallesPedidosc"%>
<%@page import="modelos.PedidosProveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedidoproveedor = Integer.parseInt(request.getParameter("id_pedidoproveedor"));
    //System.out.println("EL STOCK" + id_ajuste_stock);
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    PedidosProveedores pedidoproveedor = new PedidosProveedores();
    pedidoproveedor.setId_pedidoproveedor(id_pedidoproveedor);

    DetallesPedidosc detallepedidoc = new DetallesPedidosc();
    detallepedidoc.setPedidoproveedor(pedidoproveedor);

    
    // PedidosClientes = new PedidosClientes();
    // pedidoproveedor.setId_pedidoproveedor(id_pedidoproveedor);
    //  Stocks stock = new Stocks();
    //  stock.setId_stock(id_ajuste_stock);
    //stock.setCantidad_stock(cantidad_ajuste_stock);
    //stock.setProducto(producto);
    // if (StocksControlador.sumar(stock)) {

    if (DetallesPedidoscControlador.eliminarPrueva(detallepedidoc)) {
        if (PedidosProveedoresControlador.eliminar(pedidoproveedor)) {
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