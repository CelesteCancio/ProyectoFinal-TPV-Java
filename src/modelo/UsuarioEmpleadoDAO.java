package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase para realizar las consultas a la base de datos recibidas de la clase
 * ControladorUsuarioEmpleado
 * @author M. Celeste Cancio Vitale
 */
public class UsuarioEmpleadoDAO extends ConexionBBDD{
    //Métodos para la extraccion de datos de la BBDD 
    ConexionBBDD conexionBBDD = new ConexionBBDD ();
    Connection miConexion;
    PreparedStatement ps;
    ResultSet rs;
    
    /**
     * Método que valida los datos ingresados por el cliente. Devuelve el nombre 
     * de la persona, el cual está inicializado vacío. Si es que la encontró en 
     * la base de datos, devuelve el String con el nombre, sino devuelve el 
     * String vacío.
     * @param telefono Teléfono del cliente, que es la contraseña para el ingreso
     * @param usuario Usuario del cliente, que es su nombre y primer apellido,
     * en minúsculas
     * @return nombre Nombre de la persona. Si encontró a la persona en la BBDD, 
     * el String contiene su nombre, de lo contrario continúa vacío tal como 
     * se inicializó
     */
    public String validarEmpleadoBBDD(String telefono, String usuario){   
       
        String nombre="";
        
        try{            
            miConexion=conexionBBDD.conectar();            
            ps=miConexion.prepareStatement("select * from empleadoscazuela where telefono = ? and usuario = ?");
            ps.setString(1,telefono);
            ps.setString(2,usuario);            
            rs = ps.executeQuery();            
            if(rs.next()){//Esto es true cuando encuentra a una persona en la tabla que cumple la condicion que le pasamos                                 
                nombre=rs.getString("nombre");
            }
        }catch(Exception e){            
            e.printStackTrace();//Para controlar si surgen excepciones, y donde            
        }                
        finally{ //El finally se ejecuta siempre. Asegura que cierre la conexión a BBDD
            try{
                miConexion.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        } 
        return nombre; 
    }    
}
