package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase para conexión a base de datos
 * @author M. Celeste Cancio Vitale
 */
public class ConexionBBDD {
    
    Connection conexion;
    String url = "jdbc:mysql://localhost:3306/Cazuela";
    String user = "root"; 
    String password = "";
    
    /**
     * Método para conxión a base de datos
     * @return conexion Conexión a BBDD
     */
    public Connection conectar(){
        try{
            conexion = DriverManager.getConnection(url,user,password);
        }catch(Exception e){            
            System.out.println("No conecta");//Para control interno
        }
        System.out.println("Conectado a BBDD");//Para control interno
        return conexion;
    }
    
        
        
        
    }

