
<%@page import="controladores.MenusControlador"%>
<%@page import="modelos.Menus"%>
<<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_menu = Integer.parseInt(request.getParameter("id_menu"));
    String nombre_menu = request.getParameter("nombre_menu");
    String codigo_menu = request.getParameter("codigo_menu");

  
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Menus menu = new Menus();
    menu.setId_menu(id_menu);
    menu.setNombre_menu(nombre_menu);
    menu.setCodigo_menu(codigo_menu);
    
    if (MenusControlador.agregar(menu)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    //obj.put("id_menu", menu.getId_menu());
    out.print(obj);
    out.flush();
%>