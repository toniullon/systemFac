/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Ivas;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrador
 */
public class IvasControlador {
    
    public static boolean agregar(Ivas iva){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into ivas (nombre_iva, porcentaje_iva)" 
                    + "values ('"+ iva.getNombre_iva() + "', '" + iva.getPorcentaje_iva() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(IvasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(Ivas iva){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update ivas set nombre_iva ='" + iva.getNombre_iva() + "', porcentaje_iva='" + iva.getPorcentaje_iva() + "'"
                    + " where id_iva=" + iva.getId_iva();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(IvasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Ivas iva){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from ivas where id_iva=" + iva.getId_iva();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(IvasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Ivas buscarId(Ivas iva) {
        if (Conexion.conectar()){
            String sql = "select * from ivas where id_iva ='"+iva.getId_iva()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    iva.setId_iva(rs.getInt("id_iva"));
                    iva.setNombre_iva(rs.getString("Nombre_iva"));
                    iva.setPorcentaje_iva(rs.getInt("porcentaje_iva"));
                } else {
                    iva.setId_iva(0);
                    iva.setNombre_iva("");
                    iva.setPorcentaje_iva(0);
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return iva;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from ivas where upper(nombre_iva) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_iva offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_iva") + "</td>"
                                + "<td>" + rs.getString("nombre_iva") + "</td>"
                                + "<td>" + rs.getString("porcentaje_iva") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    /*public static String buscarPorcentaje(String porcentaje, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(porcentaje);
                String sql = "select * from ivas where (porcentaje_iva) like '%" +
                        porcentaje + "%'"
                        + "order by id_iva offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_iva") + "</td>"
                                + "<td>" + rs.getString("nombre_iva") + "</td>"
                                + "<td>" + rs.getString("porcentaje_iva") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                    System.out.println("lo siento");
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }*/
    
    public static Ivas buscarIva(Ivas iva) {
        if (Conexion.conectar()){
            String sql = "select * from ivas where porcentaje_iva ='"+iva.getPorcentaje_iva()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    iva.setId_iva(0);
                    
                } else {
                    iva.setId_iva(1);
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return iva;
    }
}
