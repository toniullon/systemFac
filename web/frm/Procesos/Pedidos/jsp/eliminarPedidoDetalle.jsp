<%@page import="modelos.PedidosClientes"%>
<%@page import="controladores.DetallesPedidosControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.DetallesPedidos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    // int id_producto = Integer.parseInt(request.getParameter("id_producto"));//esto es para la suma de stock
    int id_detallepedidoc = Integer.parseInt(request.getParameter("id_detallepedidoc"));
    //   int cantidad_ajuste_stock = Integer.parseInt(request.getParameter("cantidad_ajuste_stock"));//este tambien
    //  int id_ajuste_stock = Integer.parseInt(request.getParameter("id_ajuste_stock"));//y este

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

   

  /*  PedidosClientes pedidocliente = new PedidosClientes();
    pedidocliente.setId_pedidocliente(id_pedidocliente); */
    
    
    DetallesPedidos detallepedido = new DetallesPedidos();
    detallepedido.setId_detallepedidoc(id_detallepedidoc);
//desde aqui mas o menos
    /*  Productos producto = new Productos();
    producto.setId_producto(id_producto);

    Stocks stock = new Stocks();
    stock.setId_stock(id_ajuste_stock);
    stock.setCantidad_stock(cantidad_ajuste_stock);
    stock.setProducto(producto);
    
    
  //voy a ver si es asi  */
    if (DetallesPedidosControlador.eliminar(detallepedido)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
        // StocksControlador.sumar(stock);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>