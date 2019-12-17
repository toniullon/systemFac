<%@page import="utiles.Utiles"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="utiles.Conexion"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page contentType="application/pdf" %>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.InitialContext"%>
<%
    String dirPath = "/rpt";
    String realPath = this.getServletContext().getRealPath(dirPath);
    //String listado = request.getParameter("l");
    String sfecha_pedidov = request.getParameter("d");
    java.sql.Date fecha = Utiles.stringToSqlDate(sfecha_pedidov);
  
    String fechad = request.getParameter("c");
    java.sql.Date fechac = Utiles.stringToSqlDate(fechad);
    //   int fecha = Integer.parseInt(request.getParameter("d"));
    String jasperReport = "ImformeVentas.jasper";
    JasperPrint print = null;
    Connection conn = null;

    try {

        Conexion.conectar();
        conn = Conexion.getConn();
        Map parameters = new HashMap();
        parameters.put("fecha", fecha);
        parameters.put("fechad", fechac);
        print = JasperFillManager.fillReport(realPath + "//" + jasperReport, parameters, conn);
        response.setContentType("application/pdf");
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println(ex.getMessage());
    } finally {
        if (conn != null) {
            conn.close();
        }
    }
%>

