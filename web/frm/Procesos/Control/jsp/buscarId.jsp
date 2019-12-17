
<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_stock = Integer.parseInt(request.getParameter("id_stock"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Stocks stock = new Stocks();
    stock.setId_stock(id_stock);

    StocksControlador.buscarIdP(stock);
    if (stock.getId_stock() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        stock = new Stocks();
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_stock", stock.getId_stock());
    obj.put("cantidad_stock", stock.getCantidad_stock());
    
    obj.put("nombre_producto", stock.getProducto().getNombre_producto());
    obj.put("codigo_producto", stock.getProducto().getCodigo_producto());
    obj.put("descripcion_producto", stock.getProducto().getDescripcion_producto());

    out.print(obj);
    out.flush();
%>