
<%@page import="controladores.MedidasControlador"%>
<%@page import="modelos.Medidas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_medida = Integer.parseInt(request.getParameter("id_medida"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Medidas medida = new Medidas();
    medida.setId_medida(id_medida);

    MedidasControlador.buscarId(medida);
    if (medida.getId_medida() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_medida", medida.getId_medida());
    obj.put("nombre_medida", medida.getNombre_medida());

    out.print(obj);
    out.flush();
%>