<%@page import="modelos.Marcas"%>
<%@page import="controladores.MarcasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_marca = request.getParameter("nombre_marca");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Marcas marca = new Marcas();
    marca.setNombre_marca(nombre_marca);

    MarcasControlador.buscarMarcas(marca);
    if (marca.getId_marca() == 0) {
        tipo = "success";
        mensaje = "Datos de Nombre repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("nombre_marca", marca.getNombre_marca());

    out.print(obj);
    out.flush();
%>
