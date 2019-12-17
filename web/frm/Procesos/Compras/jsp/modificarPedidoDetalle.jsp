<%@page import="controladores.ProductosControlador"%>
<%@page import="modelos.Depositos"%>
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
    int cantidad_entrante = Integer.parseInt(request.getParameter("cantidad_entrante"));
    int cantidad_faltante = Integer.parseInt(request.getParameter("cantidad_faltante"));
    //int id_pedidoproveedor = Integer.parseInt(request.getParameter("id_pedidoproveedor"));
    int id_producto = Integer.parseInt(request.getParameter("id_productos"));
    int id_deposito = Integer.parseInt(request.getParameter("id_deposito"));
    String precioc = request.getParameter("preciocompra_producto").replaceAll("\\.", "");
    int preciocompra_producto = Integer.parseInt(precioc);

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    DetallesPedidosc detallepedido = new DetallesPedidosc();
    detallepedido.setId_detallepedidop(id_detallepedidop);
    detallepedido.setCantidad_detallepedidop(cantidad_detallepedidop);
    detallepedido.setCantidad_entrante(cantidad_entrante);
    detallepedido.setCantidad_faltante(cantidad_faltante);

    PedidosProveedores pedidoproveedor = new PedidosProveedores();
    //  pedidoproveedor.setId_pedidoproveedor(id_pedidoproveedor);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    producto.setPreciocompra_producto(preciocompra_producto);

    Depositos deposito = new Depositos();
    deposito.setId_deposito(id_deposito);

    detallepedido.setPedidoproveedor(pedidoproveedor);
    detallepedido.setProducto(producto);

    /*if (DetallesPedidosControlador.modificar(detallepedido)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }*/
    Stocks stock = new Stocks();
    stock.setCantidad_stock(cantidad_detallepedidop);
    stock.setProducto(producto);
    stock.setDeposito(deposito);

    StocksControlador.modificarD(stock);
    ProductosControlador.modificaprecioc(producto);

    if (DetallesPedidoscControlador.modificarFaltante(detallepedido)) {
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