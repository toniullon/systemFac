<%@page import="groovy.sql.Sql"%>
<%@page import="controladores.DetallesComprasControlador"%>
<%@page import="modelos.Personas"%>
<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.Compras"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<% 
    int id_compra = 0;
    if (request.getParameter("id_compra") != "") {
        id_compra = Integer.parseInt(request.getParameter("id_compra"));

    }
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Compras compras = ComprasControlador.buscartotal(id_compra);
    if (compras != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        compras = new Compras();
        compras.setId_compra(0);
    }
    

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    //obj.put("id_compra", compras.getId_compra());
    //  obj.put("numero_compra", compras.getNumero_compra());
    obj.put("total", compras.getTotal());
    obj.put("liq10", compras.getIva10());
    System.out.println("total " + compras.getTotal());
    System.out.println("liq10 " + compras.getIva10());

    out.print(obj);
    out.flush();
%>