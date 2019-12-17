<%@page import="controladores.PersonasControlador"%>
<%@page import="modelos.Personas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String ruc_persona = request.getParameter("ruc_persona");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Personas persona = new Personas();
    persona.setRuc_persona(ruc_persona);

    PersonasControlador.buscarRuc(persona);
    if (persona.getId_persona() == 0) {
        tipo = "success";
        mensaje = "Datos de Ruc repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("ruc_persona", persona.getRuc_persona());

    out.print(obj);
    out.flush();
%>
