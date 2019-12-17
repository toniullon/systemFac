/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author ALUMNO1
 */
public class Productos {

    private int id_producto;
    private String nombre_producto;
    private String descripcion_producto;
    private Marcas marca;
    private Medidas medida;
    private Rubros rubro;
    private Ubicaciones ubicacion;
    private int stockmin_producto;
    private int stockmax_producto;
    private String codigo_producto;
    private int preciocompra_producto;
    private int precioventa_producto;
    private int iva_producto;

    public Productos() {
    }

    public Productos(int id_producto, String nombre_producto, String descripcion_producto, Marcas marca, Medidas medida, Rubros rubro, Ubicaciones ubicacion, int stockmin_producto, int stockmax_producto, String codigo_producto, int preciocompra_producto, int precioventa_producto, int iva_producto) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.descripcion_producto = descripcion_producto;
        this.marca = marca;
        this.medida = medida;
        this.rubro = rubro;
        this.ubicacion = ubicacion;
        this.stockmin_producto = stockmin_producto;
        this.stockmax_producto = stockmax_producto;
        this.codigo_producto = codigo_producto;
        this.preciocompra_producto = preciocompra_producto;
        this.precioventa_producto = precioventa_producto;
        this.iva_producto = iva_producto;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    public Medidas getMedida() {
        return medida;
    }

    public void setMedida(Medidas medida) {
        this.medida = medida;
    }

    public Rubros getRubro() {
        return rubro;
    }

    public void setRubro(Rubros rubro) {
        this.rubro = rubro;
    }

    public Ubicaciones getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicaciones ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getStockmin_producto() {
        return stockmin_producto;
    }

    public void setStockmin_producto(int stockmin_producto) {
        this.stockmin_producto = stockmin_producto;
    }

    public int getStockmax_producto() {
        return stockmax_producto;
    }

    public void setStockmax_producto(int stockmax_producto) {
        this.stockmax_producto = stockmax_producto;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public int getPreciocompra_producto() {
        return preciocompra_producto;
    }

    public void setPreciocompra_producto(int preciocompra_producto) {
        this.preciocompra_producto = preciocompra_producto;
    }

    public int getPrecioventa_producto() {
        return precioventa_producto;
    }

    public void setPrecioventa_producto(int precioventa_producto) {
        this.precioventa_producto = precioventa_producto;
    }

    public int getIva_producto() {
        return iva_producto;
    }

    public void setIva_producto(int iva_producto) {
        this.iva_producto = iva_producto;
    }

    
    
}
