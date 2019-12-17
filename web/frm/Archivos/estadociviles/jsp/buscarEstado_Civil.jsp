<%@page import="modelos.Estados_Civiles"%>
<%@page import="controladores.Estados_CivilesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_estadocivil = request.getParameter("nombre_estadocivil");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Estados_Civiles estadocivil = new Estados_Civiles();
    estadocivil.setNombre_estadocivil(nombre_estadocivil);

    Estados_CivilesControlador.buscarEstados_Civiles(estadocivil);
    if (estadocivil.getId_estadocivil() == 0) {
        tipo = "success";
        mensaje = "Datos de Nombre repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("nombre_estadocivil", estadocivil.getNombre_estadocivil());

    out.print(obj);
    out.flush();
%>