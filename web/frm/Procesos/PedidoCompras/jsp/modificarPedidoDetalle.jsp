<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="controladores.DetallesPedidoscControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.PedidosProveedores"%>
<%@page import="modelos.DetallesPedidosc"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_detallepedidop = Integer.parseInt(request.getParameter("id_detallepedidop"));
    int cantidad_detallepedidop = Integer.parseInt(request.getParameter("cantidad_detallepedidop"));
    int id_pedidoproveedor = Integer.parseInt(request.getParameter("id_pedidoproveedor"));
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int preciototal_detallepedidop = Integer.parseInt(request.getParameter("preciototal_detallepedidop"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    DetallesPedidosc detallepedido = new DetallesPedidosc();
    detallepedido.setId_detallepedidop(id_detallepedidop);
    detallepedido.setCantidad_detallepedidop(cantidad_detallepedidop);
    detallepedido.setPreciototal_detallepedidop(preciototal_detallepedidop);

    PedidosProveedores pedidoproveedor = new PedidosProveedores();
    pedidoproveedor.setId_pedidoproveedor(id_pedidoproveedor);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);

    detallepedido.setPedidoproveedor(pedidoproveedor);
    detallepedido.setProducto(producto);

    /*if (DetallesPedidosControlador.modificar(detallepedido)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }*/
    Stocks stock = new Stocks();
    stock.setCantidad_stock(cantidad_detallepedidop);
    stock.setProducto(producto);


        if (DetallesPedidoscControlador.modificar(detallepedido)) {
            tipo = "success";
            mensaje = "Datos agregados.";

        } else {
            tipo = "success";
            mensaje = "Datos duplicados.";
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