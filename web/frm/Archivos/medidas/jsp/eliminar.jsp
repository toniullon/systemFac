<%@page import="controladores.MedidasControlador"%>
<%@page import="modelos.Medidas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_medida = Integer.parseInt(request.getParameter("id_medida"));
    String nombre_medida = request.getParameter("nombre_medida");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Medidas medida = new Medidas();
    medida.setId_medida(id_medida);
    medida.setNombre_medida(nombre_medida);

    if (MedidasControlador.eliminar(medida)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
