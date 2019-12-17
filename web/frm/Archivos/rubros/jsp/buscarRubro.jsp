<%@page import="modelos.Rubros"%>
<%@page import="controladores.RubrosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_rubro = request.getParameter("nombre_rubro");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Rubros rubro = new Rubros();
    rubro.setNombre_rubro(nombre_rubro);

    RubrosControlador.buscarRubros(rubro);
    if (rubro.getId_rubro() == 0) {
        tipo = "success";
        mensaje = "Datos de Nombre repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("nombre_rubro", rubro.getNombre_rubro());

    out.print(obj);
    out.flush();
%>
