<%@page import="controladores.ProductosControlador"%>
<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="controladores.DetallesComprasControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.Compras"%>
<%@page import="modelos.DetallesCompras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecompra = Integer.parseInt(request.getParameter("id_detallecompra"));
    int cantidad_detallecompra = Integer.parseInt(request.getParameter("cantidad_detallecompra"));
    int id_compra = Integer.parseInt(request.getParameter("id_compra"));
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    String precioc = request.getParameter("preciocompra_producto").replaceAll("\\.", "");
    int preciocompra_producto = Integer.parseInt(precioc);
    String preciot = request.getParameter("preciototal_compra").replaceAll("\\.", "");
    int preciototal_detallecompra = Integer.parseInt(preciot);
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    DetallesCompras detallecompra = new DetallesCompras();
    detallecompra.setId_detallecompra(id_detallecompra);
    detallecompra.setCantidad_detallecompra(cantidad_detallecompra);
    detallecompra.setPreciototal_detallecompra(preciototal_detallecompra);
    
    Compras compra = new Compras();
    compra.setId_compra(id_compra);
    
    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    producto.setPreciocompra_producto(preciocompra_producto);
    
    detallecompra.setCompra(compra);
    detallecompra.setProducto(producto);

    /*if (DetallesPedidosControlador.modificar(detallepedido)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }*/
    Stocks stock = new Stocks();
    stock.setCantidad_stock(cantidad_detallecompra);
    stock.setProducto(producto);
    StocksControlador.buscarId(stock);
    
    if (ProductosControlador.modificaprecioc(producto)) {
        if (DetallesComprasControlador.modificar(detallecompra)) {
            tipo = "success";
            mensaje = "Datos agregados.";
            
        } else {
            tipo = "success";
            mensaje = "Datos duplicados.";
        }
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