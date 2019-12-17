<%@page import="modelos.Ivas"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.Compras"%>
<%@page import="controladores.DetallesComprasControlador"%>
<%@page import="modelos.DetallesCompras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecompra = Integer.parseInt(request.getParameter("id_detallecompra"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesCompras detallecompra = DetallesComprasControlador.buscarId(id_detallecompra);
    if (detallecompra != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallecompra = new DetallesCompras();
        detallecompra.setId_detallecompra(0);
        detallecompra.setPreciototal_detallecompra(0);

        Compras compra = new Compras();
        compra.setId_compra(0);
        detallecompra.setCompra(compra);

        Productos producto = new Productos();
        producto.setId_producto(0);
        producto.setNombre_producto("");
        producto.setCodigo_producto("");
        producto.setIva_producto(0);
        //producto.setPrecio_compra_producto(0);
        detallecompra.setProducto(producto);
        
        /*Ivas iva = new Ivas();
        iva.setPorcentaje_iva(0);
        producto.setIva(iva);*/
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detallecompra", String.valueOf(detallecompra.getId_detallecompra()));
    obj.put("id_compra", String.valueOf(detallecompra.getCompra().getId_compra()));
    obj.put("id_producto", String.valueOf(detallecompra.getProducto().getId_producto()));
    obj.put("nombre_producto", detallecompra.getProducto().getNombre_producto());
    obj.put("codigo_producto", detallecompra.getProducto().getCodigo_producto());
    obj.put("preciocompra_producto", detallecompra.getProducto().getPreciocompra_producto());
    obj.put("iva_producto", detallecompra.getProducto().getIva_producto());
    /*obj.put("nombre_iva", detallecompra.getProducto().getIva().getNombre_iva());
    obj.put("porcentaje_iva", detallecompra.getProducto().getIva().getPorcentaje_iva());
    obj.put("nombre_marca", String.valueOf(detallecompra.getProducto().getMarca().getNombre_marca()));*/
    obj.put("cantidad_detallecompra", String.valueOf(detallecompra.getCantidad_detallecompra()));
    obj.put("preciototal_compra", String.valueOf(detallecompra.getPreciototal_detallecompra()));

    out.print(obj);
    out.flush();
%>