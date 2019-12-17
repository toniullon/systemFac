<%@page import="modelos.Tipopersonas"%>
<%@page import="controladores.TipopersonasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_tipopersona = request.getParameter("nombre_tipopersona");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Tipopersonas tipopersona = new Tipopersonas();
    tipopersona.setNombre_tipopersona(nombre_tipopersona);

    TipopersonasControlador.buscarTipopersona(tipopersona);
    if (tipopersona.getId_tipopersona() == 0) {
        tipo = "success";
        mensaje = "Datos de Nombre repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("nombre_tipopersona", tipopersona.getNombre_tipopersona());

    out.print(obj);
    out.flush();
%>