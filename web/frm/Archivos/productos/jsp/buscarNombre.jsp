<%@page  import="controladores.ProductosControlador"%>
<%@page  import="org.json.simple.JSONObject"%>
<%@page  import="java.sql.ResultSet"%>
<%
    String nombre_producto = request.getParameter("bnombre");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));

    String mensaje = "Busqueda exitosa";
    String contenido = ProductosControlador.buscarNombre(nombre_producto, pagina);

    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);

    out.print(obj);
    out.flush();
%>