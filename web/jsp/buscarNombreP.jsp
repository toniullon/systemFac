<%@page import="controladores.PedidosProveedoresControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre = request.getParameter("bnombre_compra");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
   
    String mensaje = "B�squeda exitosa.";
    String contenido = PedidosProveedoresControlador.buscarNombreCo(nombre, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>