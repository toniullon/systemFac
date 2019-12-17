<%@page import="modelos.Personas"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.PedidosProveedoresControlador"%>
<%@page import="modelos.PedidosProveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedidoproveedor = Integer.parseInt(request.getParameter("id_pedidoproveedor"));
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));
    // String sfecha_pedidov = request.getParameter("fecha_pedidov");
    // java.sql.Date fecha_pedidov = Utiles.stringToSqlDate(sfecha_pedidov);

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Personas persona = new Personas();
    persona.setId_persona(id_persona);

    PedidosProveedores proveedor = new PedidosProveedores();
    proveedor.setId_pedidoproveedor(id_pedidoproveedor);
    proveedor.setPersona(persona);
    
    if (PedidosProveedoresControlador.agregarProveedor(proveedor)) {
        tipo = "success";
        mensaje = "Datos modificados.";

    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_pedidoproveedor", String.valueOf(proveedor.getId_pedidoproveedor()));
  //  obj.put("condicion_proveedor", String.valueOf(proveedor.getCondicion_proveedor()));
    
    System.out.println("numero factura" + proveedor.getId_pedidoproveedor());
    out.print(obj);
    out.flush();
%>