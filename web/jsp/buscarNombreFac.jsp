<%@page import="controladores.VentasControlador"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.PedidosClientesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    // String nombre_pedidocliente = request.getParameter("bnombre_pedidocliente");
    String FECHA = request.getParameter("bnombre_venta");
    java.sql.Date DESDE = Utiles.stringToSqlDate(FECHA);
    String FECHAD = request.getParameter("bnombre_ventad");
    java.sql.Date HASTA = Utiles.stringToSqlDate(FECHAD);
    int pagina = Integer.parseInt(request.getParameter("bpagina"));

    String mensaje = "Búsqueda exitosa.";
    String contenido = VentasControlador.buscarNombre(DESDE, HASTA, pagina);

    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);

    out.print(obj);
    out.flush();
%>