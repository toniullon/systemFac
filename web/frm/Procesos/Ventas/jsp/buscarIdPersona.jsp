
<%@page import="controladores.PersonasControlador"%>
<%@page import="modelos.Personas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
  
    Personas persona = new Personas();
    persona.setId_persona(id_persona);

    PersonasControlador.buscarId(persona);
    if (persona.getId_persona() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_persona", persona.getId_persona());
    obj.put("nombre_persona", persona.getNombre_persona());
  //  obj.put("apellido_persona", persona.getApellido_persona());
    obj.put("ruc_persona", persona.getRuc_persona());

    out.print(obj);
    out.flush();
%>