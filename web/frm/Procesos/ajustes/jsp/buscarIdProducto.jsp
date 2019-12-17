
<%@page import="controladores.ProductosControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Productos producto = new Productos();
    producto.setId_producto(id_producto);

    ProductosControlador.buscarId(producto);
    if (producto.getId_producto() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_producto", producto.getId_producto());
    obj.put("nombre_producto", producto.getNombre_producto());
    obj.put("id_marca", producto.getMarca().getId_marca());
    obj.put("nombre_marca", producto.getMarca().getNombre_marca());
  //  obj.put("id_categoria", producto.getCategoria().getId_categoria());
  //  obj.put("nombre_categoria", producto.getCategoria().getNombre_categoria());
    obj.put("id_rubro", producto.getRubro().getId_rubro());
    obj.put("nombre_rubro", producto.getRubro().getNombre_rubro());
    obj.put("stockmin_producto", producto.getStockmin_producto());
    obj.put("stockmax_producto", producto.getStockmax_producto());
    obj.put("codigo_producto", producto.getCodigo_producto());
    obj.put("preciocompra_producto", producto.getPreciocompra_producto());
    obj.put("precioventa_producto", producto.getPrecioventa_producto());
    obj.put("iva_producto", producto.getIva_producto());

    out.print(obj);
    out.flush();
%>