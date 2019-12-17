<%@page import="controladores.ComprasControlador"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    // String nombre_pedidocliente = request.getParameter("bnombre_pedidocliente");
    String DESDES = request.getParameter("bnombre_compra");
    java.sql.Date DESDE = Utiles.stringToSqlDate(DESDES);

    String HASTAS = request.getParameter("bnombre_comprad");
    java.sql.Date HASTA = Utiles.stringToSqlDate(HASTAS);
    int pagina = Integer.parseInt(request.getParameter("bpagina"));

    String mensaje = "Búsqueda exitosa.";
    String contenido = ComprasControlador.buscarNombre(DESDE, HASTA, pagina);

    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);

    out.print(obj);
    out.flush();
%>