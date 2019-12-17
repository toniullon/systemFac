<%@page import="controladores.PersonasControlador"%>
<%@page import="modelos.Personas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_persona = request.getParameter("nombre_persona");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Personas persona = new Personas();
    persona.setNombre_persona(nombre_persona);

    PersonasControlador.buscarPersona(persona);
    if (persona.getId_persona() == 0) {
        tipo = "success";
        mensaje = "Datos de Personas repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("nombre_persona", persona.getNombre_persona());

    out.print(obj);
    out.flush();
%>