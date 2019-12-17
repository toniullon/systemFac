<%@page import="utiles.numero"%>
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
    int DESDE = Integer.parseInt(request.getParameter("d"));
    int iva = Integer.parseInt(request.getParameter("iva10"));
    int totalpagar = Integer.parseInt(request.getParameter("totalpagar"));
    int nume = Integer.parseInt(request.getParameter("totalpagar"));

    numero n = new numero();
    String letras = n.convertirLetras(nume) + "-------";

    String jasperReport = "facturaventas.jasper";
    JasperPrint print = null;
    Connection conn = null;

    try {

        Conexion.conectar();
        conn = Conexion.getConn();
        Map parameters = new HashMap();
        parameters.put("DESDE", DESDE);
        parameters.put("iva10", iva);
        parameters.put("totalpagar", totalpagar);
        parameters.put("letras", letras);

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

