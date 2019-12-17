<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="controladores.CajasControlador"%>
<%@page import="modelos.Cajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Cajas caja = CajasControlador.buscarId(id_caja);
    if (caja != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {

        caja = new Cajas();
        caja.setId_caja(0);

        java.sql.Date fecha_pedidov = new java.sql.Date(new java.util.Date().getTime());
        caja.setFecha_apertura(fecha_pedidov);
        //caja.setEstado_caja("PENDIENTE");
    }

    String contenido_detalle = DetallesCajasControlador.buscarIdCaja(id_caja);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_caja", caja.getId_caja());
    obj.put("fecha_apertura", String.valueOf(caja.getFecha_apertura()));
    obj.put("monto_inicial", caja.getMonto_inicial());
    obj.put("estado_caja", caja.getEstado_caja());
    obj.put("contenido_detalle", contenido_detalle);
    out.print(obj);
    out.flush();
%>