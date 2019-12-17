<%@page import="modelos.Stocks"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.PedidosProveedoresControlador"%>
<%@page import="modelos.PedidosProveedores"%>
<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.Compras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedidoproveedor = Integer.parseInt(request.getParameter("id_pedidoproveedor"));
    String sfecha_pedidop = request.getParameter("fecha_pedidop");
    java.sql.Date fecha_pedidop = Utiles.stringToSqlDate(sfecha_pedidop);

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    PedidosProveedores pedidoproveedor = new PedidosProveedores();
    pedidoproveedor.setId_pedidoproveedor(id_pedidoproveedor);
  
    Compras compra = new Compras();

    compra.setFecha_compra(fecha_pedidop);
    compra.setCondicion_compra("CONTADO");
    compra.setEstado_compra("PENDIENTE");
    compra.setPedidoproveedor(pedidoproveedor);

    Compras comprac = new Compras();
    comprac.setPedidoproveedor(pedidoproveedor);
   if (ComprasControlador.agregar(compra)) {
        ComprasControlador.agregarDetalle(comprac);
   }
   
   
   
    //if (PedidosProveedoresControlador.modificarestadopedido(pedidoproveedor)) {
      //  tipo = "success";
      //  mensaje = "Datos modificados.";
       
 
        
//}
//   ComprasControlador.buscarId(comprac);
//  if (ComprasControlador.agregardetalle(compra)) {
    
 // }
    

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_compra", String.valueOf(compra.getId_compra()));
  //  obj.put("condicion_compra", String.valueOf(compra.getCondicion_compra()));
  System.out.println("numero factura2" + comprac.getId_compra());
    System.out.println("numero factura" + compra.getId_compra());
    out.print(obj);
    out.flush();
%>