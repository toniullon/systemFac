<%@page import="utiles.Utiles"%>
<%@page import="controladores.PedidosClientesControlador"%>
<%@page import="modelos.PedidosClientes"%>
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedidocliente = Integer.parseInt(request.getParameter("id_pedidocliente"));
    String sfecha_pedidov = request.getParameter("fecha_pedidov");
    java.sql.Date fecha_pedidov = Utiles.stringToSqlDate(sfecha_pedidov);

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    /*Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);*/
    PedidosClientes pedidocliente = new PedidosClientes();
    pedidocliente.setId_pedidocliente(id_pedidocliente);
    //ajuste.setNumero_factura(numero_factura);
    //ajuste.setTimbrado_ajuste(timbrado_ajuste);

    //ajuste.setProveedor(proveedor);
    Ventas venta = new Ventas();

    venta.setFecha_venta(fecha_pedidov);
    venta.setCondicion_venta("CONTADO");
    venta.setEstado_venta("PENDIENTE");
    venta.setPedidocliente(pedidocliente);

    Ventas ventac = new Ventas();
    ventac.setPedidocliente(pedidocliente);

    if (PedidosClientesControlador.modificarestadopedido(pedidocliente)) {
        tipo = "success";
        mensaje = "Datos modificados.";
        if (VentasControlador.agregar(venta)) {
            VentasControlador.agregarDetalle(ventac);
        }

    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_venta", String.valueOf(venta.getId_venta()));
    obj.put("condicion_venta", String.valueOf(venta.getCondicion_venta()));
    System.out.println("numero factura" + venta.getId_venta());
    out.print(obj);
    out.flush();
%>