package controladores;


import utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Ciudades;
import modelos.Estados_Civiles;
import modelos.Personas;
import modelos.Tipopersonas;
import utiles.Utiles;

public class PersonasControlador {

       public static boolean agregar(Personas persona) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into personas(nombre_persona, apellido_persona, direccion_persona, telefono_persona, correo_persona, ruc_persona, id_ciudad, id_tipopersona, id_estadocivil) "
                    + "values('" + persona.getNombre_persona() + "', '"
                    + persona.getApellido_persona() + "', '"
                    + persona.getDireccion_persona() + "', '"
                    + persona.getTelefono_persona() + "', '"
                    + persona.getCorreo_persona() + "', '"
                    + persona.getRuc_persona() + "', '"
                    + persona.getCiudad().getId_ciudad() + "', '"
                    + persona.getTipopersona().getId_tipopersona() + "', '"
                    + persona.getEstadocivil().getId_estadocivil() + "')";
            //+ persona.getIva().getId_iva() + "')";
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_persona = keyset.getInt(1);
                    persona.setId_persona(id_persona);

                }
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Personas buscarId(Personas persona) {

        if (Conexion.conectar()) {
            String sql = "select * from personas p, ciudades m, tipopersonas c, estados_civiles r where p.id_ciudad=m.id_ciudad and p.id_tipopersona=c.id_tipopersona and p.id_estadocivil=r.id_estadocivil and p.id_persona ='" + persona.getId_persona() + "'";
            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    persona.setId_persona(rs.getInt("id_persona"));
                    persona.setNombre_persona(rs.getString("nombre_persona"));
                    persona.setApellido_persona(rs.getString("apellido_persona"));
                    persona.setDireccion_persona(rs.getString("direccion_persona"));
                    persona.setTelefono_persona(rs.getString("telefono_persona"));
                    persona.setCorreo_persona(rs.getString("correo_persona"));
                    persona.setRuc_persona(rs.getString("ruc_persona"));
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                    persona.setCiudad(ciudad);

                    Tipopersonas tipopersona = new Tipopersonas();
                    tipopersona.setId_tipopersona(rs.getInt("id_tipopersona"));
                    tipopersona.setNombre_tipopersona(rs.getString("nombre_tipopersona"));
                    persona.setTipopersona(tipopersona);
                    Estados_Civiles estadocivil = new Estados_Civiles();
                    estadocivil.setId_estadocivil(rs.getInt("id_estadocivil"));
                    estadocivil.setNombre_estadocivil(rs.getString("nombre_estadocivil"));
                    persona.setEstadocivil(estadocivil);

                } else {
                    persona.setId_persona(0);
                    persona.setNombre_persona("");
                    persona.setApellido_persona("");
                    persona.setDireccion_persona("");
                    persona.setTelefono_persona("");
                    persona.setCorreo_persona("EjemploCorreo@gmail.com");
                    persona.setRuc_persona("");

                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");

                    Tipopersonas tipopersona = new Tipopersonas();
                    tipopersona.setId_tipopersona(0);
                    tipopersona.setNombre_tipopersona("");

                    Estados_Civiles estadocivil = new Estados_Civiles();
                    estadocivil.setId_estadocivil(0);
                    estadocivil.setNombre_estadocivil("");

                    persona.setCiudad(ciudad);
                    persona.setEstadocivil(estadocivil);
                    persona.setTipopersona(tipopersona);
                    //persona.setIva(iva);
                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return persona;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from personas p left join ciudades m on p.id_ciudad=m.id_ciudad left join estados_civiles c on p.id_estadocivil=c.id_estadocivil left join tipopersonas r on p.id_tipopersona=r.id_tipopersona where upper(nombre_persona) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by id_persona offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_persona") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "<td>" + rs.getString("apellido_persona") + "</td>"
                              //  + "<td>" + rs.getString("direccion_persona") + "</td>"
                                + "<td>" + rs.getString("telefono_persona") + "</td>"
                              //  + "<td>" + rs.getString("correo_persona") + "</td>"
                                + "<td>" + rs.getString("ruc_persona") + "</td>"
                              //  + "<td>" + rs.getString("id_ciudad") + "</td>"
                              //  + "<td>" + rs.getString("id_tipopersona") + "</td>"
                              //  + "<td>" + rs.getString("id_estadocivil") + "</td>"
                                + "<td>" + rs.getString("nombre_tipopersona") + "</td>"
                                //+ "<td>" + rs.getString("id_iva") + "</td>"
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

    public static String buscarNombreCliente(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from personas p left join ciudades m on p.id_ciudad=m.id_ciudad left join estados_civiles c on p.id_estadocivil=c.id_estadocivil left join tipopersonas r on p.id_tipopersona=r.id_tipopersona where upper(nombre_persona) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "and p.id_tipopersona ='2' order by id_persona offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_persona") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "<td>" + rs.getString("apellido_persona") + "</td>"
                              //  + "<td>" + rs.getString("direccion_persona") + "</td>"
                                + "<td>" + rs.getString("telefono_persona") + "</td>"
                              //  + "<td>" + rs.getString("correo_persona") + "</td>"
                                + "<td>" + rs.getString("ruc_persona") + "</td>"
                              //  + "<td>" + rs.getString("id_ciudad") + "</td>"
                              //  + "<td>" + rs.getString("id_tipopersona") + "</td>"
                              //  + "<td>" + rs.getString("id_estadocivil") + "</td>"
                                + "<td>" + rs.getString("nombre_tipopersona") + "</td>"
                                //+ "<td>" + rs.getString("id_iva") + "</td>"
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
    
    public static String buscarNombreProveedor(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from personas p left join ciudades m on p.id_ciudad=m.id_ciudad left join estados_civiles c on p.id_estadocivil=c.id_estadocivil left join tipopersonas r on p.id_tipopersona=r.id_tipopersona where upper(nombre_persona) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "and p.id_tipopersona ='3' order by id_persona offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_persona") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "<td>" + rs.getString("apellido_persona") + "</td>"
                              //  + "<td>" + rs.getString("direccion_persona") + "</td>"
                                + "<td>" + rs.getString("telefono_persona") + "</td>"
                              //  + "<td>" + rs.getString("correo_persona") + "</td>"
                                + "<td>" + rs.getString("ruc_persona") + "</td>"
                              //  + "<td>" + rs.getString("id_ciudad") + "</td>"
                              //  + "<td>" + rs.getString("id_tipopersona") + "</td>"
                              //  + "<td>" + rs.getString("id_estadocivil") + "</td>"
                                + "<td>" + rs.getString("nombre_tipopersona") + "</td>"
                                //+ "<td>" + rs.getString("id_iva") + "</td>"
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
    
    public static boolean modificar(Personas persona) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update personas set nombre_persona= ' " + persona.getNombre_persona()
                    + " ', apellido_persona= '" + persona.getApellido_persona()
                    + "', direccion_persona= '" + persona.getDireccion_persona()
                    + "', telefono_persona= '" + persona.getTelefono_persona()
                    + "', correo_persona= '" + persona.getCorreo_persona()
                    + "', ruc_persona= '" + persona.getRuc_persona()
                    + "', id_ciudad= '" + persona.getCiudad().getId_ciudad()
                    + "', id_tipopersona= '" + persona.getTipopersona().getId_tipopersona()
                    + "', id_estadocivil= '" + persona.getEstadocivil().getId_estadocivil()
                    + " ' " + " where id_persona=" + persona.getId_persona();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Personas persona) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from personas where id_persona=" + persona.getId_persona();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }

    //Aqui la función buscarProducto buscara los nombres de los personas para ver si no se repiten
    public static Personas buscarPersona(Personas persona) {

        if (Conexion.conectar()) {
            String sql = "select * from personas where nombre_persona ='" + persona.getNombre_persona() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    persona.setId_persona(0);

                } else {

                    persona.setId_persona(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return persona;
    }
    
    

    //buscar codigo
  public static Personas buscarTelefono(Personas persona) {

        if (Conexion.conectar()) {
            String sql = "select * from personas where telefono_persona ='" + persona.getTelefono_persona() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    persona.setId_persona(0);

                } else {

                    persona.setId_persona(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return persona;
    }
  
   public static Personas buscarCorreo(Personas persona) {

        if (Conexion.conectar()) {
            String sql = "select * from personas where correo_persona ='" + persona.getCorreo_persona() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    persona.setId_persona(0);

                } else {

                    persona.setId_persona(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return persona;
    }
   
   public static Personas buscarRuc(Personas persona) {

        if (Conexion.conectar()) {
            String sql = "select * from personas where ruc_persona ='" + persona.getRuc_persona() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    persona.setId_persona(0);

                } else {

                    persona.setId_persona(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return persona;
    }

}
