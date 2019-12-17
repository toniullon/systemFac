<%@page import="modelos.Medidas"%>
<%@page import="controladores.MedidasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_medida = request.getParameter("nombre_medida");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Medidas medida = new Medidas();
    medida.setNombre_medida(nombre_medida);

    MedidasControlador.buscarMedidas(medida);
    if (medida.getId_medida() == 0) {
        tipo = "success";
        mensaje = "Datos de Nombre repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("nombre_medida", medida.getNombre_medida());

    out.print(obj);
    out.flush();
%>
