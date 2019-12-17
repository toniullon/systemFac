<%@page import="controladores.Estados_CivilesControlador"%>
<%@page import="modelos.Estados_Civiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));
    String nombre_estadocivil = request.getParameter("nombre_estadocivil");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Estados_Civiles estadocivil = new Estados_Civiles();
    estadocivil.setId_estadocivil(id_estadocivil);
    estadocivil.setNombre_estadocivil(nombre_estadocivil);

    if (Estados_CivilesControlador.agregar(estadocivil)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
