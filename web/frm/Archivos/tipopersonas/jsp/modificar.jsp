<%@page import="controladores.TipopersonasControlador"%>
<%@page import="modelos.Tipopersonas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_tipopersona = Integer.parseInt(request.getParameter("id_tipopersona"));
    String nombre_tipopersona = request.getParameter("nombre_tipopersona");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Tipopersonas tipopersona = new Tipopersonas();
    tipopersona.setId_tipopersona(id_tipopersona);
    tipopersona.setNombre_tipopersona(nombre_tipopersona);

    if (TipopersonasControlador.modificar(tipopersona)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
