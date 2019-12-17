<%@page import="controladores.PersonasControlador"%>
<%@page import="modelos.Personas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String correo_persona = request.getParameter("correo_persona");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Personas persona = new Personas();
    persona.setCorreo_persona(correo_persona);

    PersonasControlador.buscarCorreo(persona);
    if (persona.getId_persona() == 0) {
        tipo = "success";
        mensaje = "Datos de Correo repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("correo_persona", persona.getCorreo_persona());

    out.print(obj);
    out.flush();
%>
