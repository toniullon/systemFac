<%@page import="controladores.PersonasControlador"%>
<%@page import="modelos.Personas"%>
<%@page import="modelos.Ciudades"%>
<%@page import="modelos.Tipopersonas"%>
<%@page import="modelos.Estados_Civiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
    int id_tipopersona = Integer.parseInt(request.getParameter("id_tipopersona"));
    int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));
    String nombre_persona = request.getParameter("nombre_persona");
    String apellido_persona = request.getParameter("apellido_persona");
    String direccion_persona = request.getParameter("direccion_persona");
    String telefono_persona = request.getParameter("telefono_persona");
    String correo_persona = request.getParameter("correo_persona");
    String ruc_persona = request.getParameter("ruc_persona");

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);

    Tipopersonas tipopersona = new Tipopersonas();
    tipopersona.setId_tipopersona(id_tipopersona);

    Estados_Civiles estadocivil = new Estados_Civiles();
    estadocivil.setId_estadocivil(id_estadocivil);

    Personas persona = new Personas();
    persona.setId_persona(id_persona);
    persona.setNombre_persona(nombre_persona);
    persona.setApellido_persona(apellido_persona);
    persona.setDireccion_persona(direccion_persona);
    persona.setTelefono_persona(telefono_persona);
    persona.setCorreo_persona(correo_persona);
    persona.setRuc_persona(ruc_persona);
   
    persona.setCiudad(ciudad);
    persona.setTipopersona(tipopersona);
    persona.setEstadocivil(estadocivil);

    if (PersonasControlador.modificar(persona)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
