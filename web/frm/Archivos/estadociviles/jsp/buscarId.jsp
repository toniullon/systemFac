<%@page import="controladores.Estados_CivilesControlador"%>
<%@page import="modelos.Estados_Civiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));  
  String tipo = "error";
  String mensaje = "Datos no encontrados";
  String nuevo = "true";
Estados_Civiles estadocivil = new Estados_Civiles();
estadocivil.setId_estadocivil(id_estadocivil);

Estados_CivilesControlador.buscarId(estadocivil);
if(estadocivil.getId_estadocivil()!=0) {
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
}
  
JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_estadocivil", estadocivil.getId_estadocivil());
    System.out.println("id_estadocivil"+estadocivil.getId_estadocivil());
   obj.put("nombre_estadocivil", estadocivil.getNombre_estadocivil());
    System.out.println("nombre_estadocivil"+ estadocivil.getNombre_estadocivil());
    
    out.print(obj);
    out.flush();
%>