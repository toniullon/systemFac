<%@page import="utiles.Utiles"%>
<%@page import="controladores.PedidosClientesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
   // String nombre_pedidocliente = request.getParameter("bnombre_pedidocliente");
   String DESDES = request.getParameter("bnombre_pedidocliente");
    java.sql.Date DESDE = Utiles.stringToSqlDate(DESDES);
    
    String HASTAS = request.getParameter("bnombre_pedidocliented");
    java.sql.Date HASTA = Utiles.stringToSqlDate(HASTAS);
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
   
    String mensaje = "Búsqueda exitosa.";
    String contenido = PedidosClientesControlador.buscarNombreInfor(DESDE,HASTA, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>