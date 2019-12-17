<%@page import="modelos.Depositos"%>
<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="controladores.ProductosControlador"%>
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
    int id_deposito = Integer.parseInt(request.getParameter("id_deposito")); 
//int total_ajuste= con + 0; 

    //int con=total_detalleajuste;
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Depositos deposito =new Depositos();
    deposito.setId_deposito(id_deposito);
    
    PedidosProveedores pedidoproveedor = new PedidosProveedores();
    pedidoproveedor.setId_pedidoproveedor(id_pedidoproveedor);
    
    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    
    DetallesPedidosc detallepedido = new DetallesPedidosc();
    detallepedido.setId_detallepedidop(id_detallepedidop);
    detallepedido.setCantidad_detallepedidop(cantidad_detallepedidop);
    detallepedido.setPreciototal_detallepedidop(preciototal_detallepedidop);
    detallepedido.setEstado_pedidop("PENDIENTE");
    detallepedido.setPedidoproveedor(pedidoproveedor);
    detallepedido.setProducto(producto);
    
    Stocks stock = new Stocks();
    stock.setCantidad_stock(cantidad_detallepedidop);
    stock.setProducto(producto);
    stock.setDeposito(deposito);
    
    if (DetallesPedidoscControlador.buscarDuplicadosP(detallepedido)) {
        controladores.StocksControlador.modificarD(stock);
        tipo = "success";
        mensaje = "Datos agregados.";
        
    } else {
        tipo = "success";
        mensaje = "Datos duplicados.";
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