package modelo;

/**
 * Clase que contiene los atributos y métodos del producto
 * 
 * @author M. Celeste Cancio Vitale
 */
public class Producto {
    
    private int codigoProducto; 
    private String nombreProducto;
    private double precioProducto;
    private String aliasProducto;
    
    /**
     * Constructor de la clase  
     * @param aliasProducto Alias del producto. Ese el nombre completo en minúscula, 
     * con la letra inicial de cada palabra en mayúscula
     */
    public Producto(String aliasProducto) {
        this.aliasProducto = aliasProducto;
    } 
    
    /**
     * 
     * @param nombreProducto Nombre del producto
     * @param precioProducto Precio del producto
     */
    public Producto(String nombreProducto, double precioProducto) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
    }
    /**
     * Constructor de la clase, sin parámetros
     */
    public Producto() {
    }
    
    /**
     * 
     * @param nombreProducto Nombre del producto
     * @param precioProducto Precio del producto
     * @param aliasProducto Alias del producto. Ese el nombre completo en minúscula, 
     * con la letra inicial de cada palabra en mayúscula
     */
    public Producto(String nombreProducto, double precioProducto, String aliasProducto) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.aliasProducto = aliasProducto;
    }
    
    /**
     * Setters y getters
     * 
     */
    
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }
    
    public String getAliasProducto() {
        return aliasProducto;
    }

    public void setAliasProducto(String aliasProducto) {
        this.aliasProducto = aliasProducto;
    }
}
