package modelo;

import static java.lang.Float.parseFloat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Clase para realizar las consultas a la base de datos recibidas de la clase
 * ControladorProductos
 * @author M. Celeste Cancio Vitale
 */
public class ProductoDAO extends ConexionBBDD{
    
    ConexionBBDD conexionBBDD = new ConexionBBDD ();
    Connection miConexion;
    
    PreparedStatement ps;
    ResultSet rs;
    
    /**
     * Constructor sin parámetros
     */
    public ProductoDAO() {
    }
    
    /**
     * Método que recibe el producto para buscar en la base de datos y devuelve 
     * true o false
     * @param producto Producto para buscar en la base de datos
     * @return true si encontró el producto en la base de datos
     */
    public boolean buscarProducto (Producto producto){
         
        try{
            miConexion=conexionBBDD.conectar();            
            ps=miConexion.prepareStatement("select * from productoscazuela where AliasProducto = ?");            
            
            ps.setString(1, producto.getAliasProducto());
            rs=ps.executeQuery();
            if(rs.next()){
                producto.setNombreProducto(rs.getString("NombreProducto"));
                producto.setPrecioProducto(parseFloat(rs.getString("PrecioProducto")));                
                
                return true;
            }
            else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{ //El finally se ejecuta siempre. Asegura que cierre la conexión a BBDD
            try{
                miConexion.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }       
    } 
    
    /**
     * Método que inserta en la base de datos la lista de productos seleccionados,
     * una vez pulsado el botón comprar
     * @param listaProductosSeleccionados ArrayList de productos seleccionados
     *  
     */
    public boolean insertar (ArrayList <Producto> listaProductosSeleccionados){
        try{            
            miConexion=conexionBBDD.conectar();

            ListIterator <Producto> it = listaProductosSeleccionados.listIterator();

            int resultado=0;
            
            while(it.hasNext()){ 
                
                ps=miConexion.prepareStatement("insert into ventascazuela (NombreProducto,"
                    + "PrecioProducto,AliasProducto) values (?,?,?)");
                                
                Producto auxiliar = it.next();
                
                ps.setString(1,auxiliar.getNombreProducto());
                ps.setDouble(2, (double)auxiliar.getPrecioProducto());          
                ps.setString(3, auxiliar.getAliasProducto());                
                resultado = ps.executeUpdate();                
               
            }                        
            if(resultado>0){ //Si insertó correctamente en la BBDD
                return true;
            }
            else{
                return false;
            }           
            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{ //El finally se ejecuta siempre. Asegura que cierre la conexión a BBDD
            try{
                miConexion.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }        
    }
}
