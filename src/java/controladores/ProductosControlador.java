package controladores;

import modelos.Productos;
import modelos.Marcas;
import modelos.Medidas;
import modelos.Rubros;
import utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.PedidosClientes;
//import modelos.Ivas;
import utiles.Utiles;

public class ProductosControlador {

    public static boolean agregar(Productos producto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into productos(nombre_producto, codigo_producto, descripcion_producto, precioventa_producto, preciocompra_producto, stockmin_producto, stockmax_producto, id_marca, id_rubro, id_medida, iva_producto) "
                    + "values('" + producto.getNombre_producto() + "', '"
                    + producto.getCodigo_producto() + "', '"
                    + producto.getDescripcion_producto() + "', '"
                    + producto.getPrecioventa_producto() + "', '"
                    + producto.getPreciocompra_producto() + "', '"
                    + producto.getStockmin_producto() + "', '"
                    + producto.getStockmax_producto() + "', '"
                    + producto.getMarca().getId_marca() + "', '"
                    + producto.getRubro().getId_rubro() + "', '"
                    + producto.getMedida().getId_medida() + "', '"
                    + producto.getIva_producto() + "')";
            //+ producto.getIva().getId_iva() + "')";
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_producto = keyset.getInt(1);
                    producto.setId_producto(id_producto);

                }
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }
    
    public static Productos buscarCodigo(Productos producto) {

        if (Conexion.conectar()) {
            String sql = "select * from productos p, marcas m, medidas c, rubros r where p.id_marca=m.id_marca and p.id_medida=c.id_medida and p.id_rubro=r.id_rubro and to_number(codigo_producto,'999999999999D99') = '" + producto.getCodigo_producto()+ "'";
            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    producto.setId_producto(rs.getInt("id_producto"));
                    producto.setNombre_producto(rs.getString("nombre_producto"));
                    producto.setCodigo_producto(rs.getString("codigo_producto"));
                    producto.setDescripcion_producto(rs.getString("descripcion_producto"));
                    producto.setPrecioventa_producto(rs.getInt("precioventa_producto"));
                    producto.setPreciocompra_producto(rs.getInt("preciocompra_producto"));
                    producto.setStockmin_producto(rs.getInt("stockmin_producto"));
                    producto.setStockmax_producto(rs.getInt("stockmax_producto"));
                    Marcas marca = new Marcas();
                    marca.setId_marca(rs.getInt("id_marca"));
                    marca.setNombre_marca(rs.getString("nombre_marca"));
                    producto.setMarca(marca);

                    Rubros rubro = new Rubros();
                    rubro.setId_rubro(rs.getInt("id_rubro"));
                    rubro.setNombre_rubro(rs.getString("nombre_rubro"));
                    producto.setRubro(rubro);

                    Medidas medida = new Medidas();
                    medida.setId_medida(rs.getInt("id_medida"));
                    medida.setNombre_medida(rs.getString("nombre_medida"));
                    producto.setMedida(medida);

                    producto.setIva_producto(rs.getInt("iva_producto"));
                } else {
                    producto.setId_producto(0);
                    producto.setNombre_producto("");
                    producto.setCodigo_producto("");
                    producto.setDescripcion_producto("");
                    producto.setPreciocompra_producto(0);
                    producto.setStockmin_producto(0);
                    producto.setStockmax_producto(0);

                    Marcas marca = new Marcas();
                    marca.setId_marca(0);
                    marca.setNombre_marca("");

                    Rubros rubro = new Rubros();
                    rubro.setId_rubro(0);
                    rubro.setNombre_rubro("");

                    Medidas medida = new Medidas();
                    medida.setId_medida(0);
                    medida.setNombre_medida("");

                    producto.setIva_producto(0);
                    producto.setMarca(marca);
                    producto.setMedida(medida);
                    producto.setRubro(rubro);
                    //producto.setIva(iva);
                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return producto;
    }
    

    public static Productos buscarId(Productos producto) {

        if (Conexion.conectar()) {
            String sql = "select * from productos p, marcas m, medidas c, rubros r where p.id_marca=m.id_marca and p.id_medida=c.id_medida and p.id_rubro=r.id_rubro and p.id_producto ='" + producto.getId_producto() + "'";
            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    producto.setId_producto(rs.getInt("id_producto"));
                    producto.setNombre_producto(rs.getString("nombre_producto"));
                    producto.setCodigo_producto(rs.getString("codigo_producto"));
                    producto.setDescripcion_producto(rs.getString("descripcion_producto"));
                    producto.setPrecioventa_producto(rs.getInt("precioventa_producto"));
                    producto.setPreciocompra_producto(rs.getInt("preciocompra_producto"));
                    producto.setStockmin_producto(rs.getInt("stockmin_producto"));
                    producto.setStockmax_producto(rs.getInt("stockmax_producto"));
                    Marcas marca = new Marcas();
                    marca.setId_marca(rs.getInt("id_marca"));
                    marca.setNombre_marca(rs.getString("nombre_marca"));
                    producto.setMarca(marca);

                    Rubros rubro = new Rubros();
                    rubro.setId_rubro(rs.getInt("id_rubro"));
                    rubro.setNombre_rubro(rs.getString("nombre_rubro"));
                    producto.setRubro(rubro);

                    Medidas medida = new Medidas();
                    medida.setId_medida(rs.getInt("id_medida"));
                    medida.setNombre_medida(rs.getString("nombre_medida"));
                    producto.setMedida(medida);

                    producto.setIva_producto(rs.getInt("iva_producto"));
                } else {
                    producto.setId_producto(0);
                    producto.setNombre_producto("");
                    producto.setCodigo_producto("");
                    producto.setDescripcion_producto("");
                    producto.setPreciocompra_producto(0);
                    producto.setStockmin_producto(0);
                    producto.setStockmax_producto(0);

                    Marcas marca = new Marcas();
                    marca.setId_marca(0);
                    marca.setNombre_marca("");

                    Rubros rubro = new Rubros();
                    rubro.setId_rubro(0);
                    rubro.setNombre_rubro("");

                    Medidas medida = new Medidas();
                    medida.setId_medida(0);
                    medida.setNombre_medida("");

                    producto.setIva_producto(0);
                    producto.setMarca(marca);
                    producto.setMedida(medida);
                    producto.setRubro(rubro);
                    //producto.setIva(iva);
                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return producto;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from productos p left join marcas m on p.id_marca=m.id_marca left join medidas c on p.id_medida=c.id_medida left join rubros r on p.id_rubro=r.id_rubro where upper(nombre_producto) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by id_producto offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getString("descripcion_producto") + "</td>"
                                + "<td>" + rs.getString("codigo_producto") + "</td>"
                               /* + "<td>" + rs.getString("precioventa_producto") + "</td>"
                                + "<td>" + rs.getString("preciocompra_producto") + "</td>"
                                + "<td>" + rs.getString("stockmin_producto") + "</td>"
                                + "<td>" + rs.getString("stockmax_producto") + "</td>"
                                + "<td>" + rs.getString("id_marca") + "</td>"
                                + "<td>" + rs.getString("id_rubro") + "</td>"
                                + "<td>" + rs.getString("id_medida") + "</td>"*/
                                //+ "<td>" + rs.getString("id_iva") + "</td>"
                               // + "<td>" + rs.getString("iva_producto") + "</td>"
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

    public static boolean modificar(Productos producto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update productos set nombre_producto= '" + producto.getNombre_producto()
                    + "', codigo_producto= '" + producto.getCodigo_producto()
                    + "', descripcion_producto= '" + producto.getDescripcion_producto()
                    + "', precioventa_producto= '" + producto.getPrecioventa_producto()
                    + "', preciocompra_producto= '" + producto.getPreciocompra_producto()
                    + "', stockmin_producto= '" + producto.getStockmin_producto()
                    + "', stockmax_producto= '" + producto.getStockmax_producto()
                    + "', id_marca= '" + producto.getMarca().getId_marca()
                    + "', id_rubro= '" + producto.getRubro().getId_rubro()
                    + "', id_medida= '" + producto.getMedida().getId_medida()
                    + "', iva_producto= '" + producto.getIva_producto()
                    + "'" + " where id_producto=" + producto.getId_producto();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Productos producto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from productos where id_producto=" + producto.getId_producto();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }

    //Aqui la función buscarProducto buscara los nombres de los productos para ver si no se repiten
    public static Productos buscarProducto(Productos producto) {

        if (Conexion.conectar()) {
            String sql = "select * from productos where nombre_producto ='" + producto.getNombre_producto() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    producto.setId_producto(0);

                } else {

                    producto.setId_producto(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return producto;
    }
    
    public static Productos buscarMarca(Productos producto) {
         if (Conexion.conectar()) {
            String sql = "select * from productos where id_marca ='" + producto.getMarca() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    producto.setId_producto(0);

                } else {

                    producto.setId_producto(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return producto;
    }

    

    //buscar codigo
   /* public static Productos buscarCodigo(Productos producto) {

        if (Conexion.conectar()) {
            String sql = "select * from productos where codigo_producto ='" + producto.getCodigo_producto() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    producto.setId_producto(0);

                } else {

                    producto.setId_producto(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return producto;
    }*/
public static boolean modificaprecioc(Productos producto) {
        boolean valor = false;
        if (Conexion.conectar()) {
        String sql = "update productos set preciocompra_producto= '" + producto.getPreciocompra_producto()
                 + "'" + " where id_producto=" + producto.getId_producto();
            try {

                Conexion.getSt().executeUpdate(sql);

                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
