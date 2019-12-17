<%@page import="utiles.Utiles"%>
<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.Compras"%>
<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.Compras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_compra = Integer.parseInt(request.getParameter("id_compra"));
    int total = Integer.parseInt(request.getParameter("total"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    /*Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);*/
    Compras compra = new Compras();
    compra.setId_compra(id_compra);
    compra.setTotal(total);

    if (ComprasControlador.modificartotal(compra)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);

    out.print(obj);
    out.flush();
%>