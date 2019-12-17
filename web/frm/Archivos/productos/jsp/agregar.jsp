
<%@page import="modelos.Ivas"%>
<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="controladores.ProductosControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.Marcas"%>
<%@page import="modelos.Medidas"%>
<%@page import="modelos.Rubros"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));

    int id_marca = Integer.parseInt(request.getParameter("id_marca"));
    int id_medida = Integer.parseInt(request.getParameter("id_medida"));
    int id_rubro = Integer.parseInt(request.getParameter("id_rubro"));
    String nombre_producto = request.getParameter("nombre_producto");
    String descripcion_producto = request.getParameter("descripcion_producto");
    int stockmin_producto = Integer.parseInt(request.getParameter("stockmin_producto"));
    int stockmax_producto = Integer.parseInt(request.getParameter("stockmax_producto"));
    String codigo_producto = request.getParameter("codigo_producto");
    String precioc = request.getParameter("preciocompra_producto").replaceAll("\\.", "");
    int preciocompra_producto = Integer.parseInt(precioc);
    String preciov = request.getParameter("precioventa_producto").replaceAll("\\.", "");
    int precioventa_producto = Integer.parseInt(preciov);
    int iva_producto = Integer.parseInt(request.getParameter("iva_producto"));
    int cantidad_stock = 0;

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Marcas marca = new Marcas();
    marca.setId_marca(id_marca);

    Rubros rubro = new Rubros();
    rubro.setId_rubro(id_rubro);
    
    Medidas medida = new Medidas();
    medida.setId_medida(id_medida);
    
    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    producto.setNombre_producto(nombre_producto);
    producto.setDescripcion_producto(descripcion_producto);
    producto.setStockmin_producto(stockmin_producto);
    producto.setStockmax_producto(stockmax_producto);
    producto.setCodigo_producto(codigo_producto);
    producto.setPreciocompra_producto(preciocompra_producto);
    producto.setPrecioventa_producto(precioventa_producto);
    producto.setIva_producto(iva_producto);
    producto.setMarca(marca);
    producto.setMedida(medida);
    producto.setRubro(rubro);

    if (ProductosControlador.agregar(producto)) {
        tipo = "success";
        mensaje = "Datos agregados.";
        Stocks stock = new Stocks();
        stock.setCantidad_stock(cantidad_stock);
        stock.setProducto(producto);
        StocksControlador.agregar(stock);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_producto", producto.getId_producto());
    out.print(obj);
    out.flush();
%>
