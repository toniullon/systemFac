<%@page import="modelos.Depositos"%>
<%@page import="controladores.DepositosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_deposito = request.getParameter("nombre_deposito");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Depositos deposito = new Depositos();
    deposito.setNombre_deposito(nombre_deposito);

    DepositosControlador.buscarDepositos(deposito);
    if (deposito.getId_deposito() == 0) {
        tipo = "success";
        mensaje = "Datos de Nombre repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("nombre_deposito", deposito.getNombre_deposito());

    out.print(obj);
    out.flush();
%>
