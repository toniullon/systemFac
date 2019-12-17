<%@page import="controladores.PersonasControlador"%>
<%@page import="modelos.Personas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String telefono_persona = request.getParameter("telefono_persona");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Personas persona = new Personas();
    persona.setTelefono_persona(telefono_persona);

    PersonasControlador.buscarTelefono(persona);
    if (persona.getId_persona() == 0) {
        tipo = "success";
        mensaje = "Datos de Personas repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("telefono_persona", persona.getTelefono_persona());

    out.print(obj);
    out.flush();
%>
