package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.ConexionBBDD;


/**
 * Clase para realizar las consultas a la base de datos recibidas de 
 * ControladorConsultaEmpleados.
 * @author M. Celeste Cancio Vitale
 */
public class ConsultaEmpleadosDAO extends ConexionBBDD{
    ConexionBBDD conexionBBDD = new ConexionBBDD ();
    Connection miConexion;
    
    PreparedStatement ps=null;
    ResultSet rs=null;
          
    /**
     * Constructor sin parámetros
     */
    public ConsultaEmpleadosDAO() {
    }
    
    /**
     * Método que recibe los datos del nuevo cliente y lo ingresa a la base de datos
     * @see controlador.ControladorConsultaEmpleados#insertarCliente() 
     * @param nuevoCliente Cliente para ingresar a la base de datos
     * @return true si lo ingresó correctamente a la base de datos
     */
    
    public boolean insertarClienteEnBBDD (UsuarioCliente nuevoCliente){
        
        try{
            miConexion=conexionBBDD.conectar();
            ps=miConexion.prepareStatement("insert into clientescazuela (nombre,apellido1,"
                    + "apellido2,telefono,usuario) values (?,?,?,?,?)");
            
            ps.setString(1,nuevoCliente.getNombre());
            ps.setString(2,nuevoCliente.getApellido1());
            ps.setString(3, nuevoCliente.getApellido2());
            ps.setString(4, nuevoCliente.getTelefono());
            ps.setString(5, nuevoCliente.getUsuario());

            int resultado = ps.executeUpdate();
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
   
    /**
     * Método que recibe los datos del cliente a editar y lo actualiza en la 
     * base de datos
     * @param clienteEditado Cliente para actualizar a la base de datos
     * @return true si lo actualizó correctamente en la base de datos
     * @see controlador.ControladorConsultaEmpleados#editarCliente() 
     */
    public boolean editarClienteEnBBDD (UsuarioCliente clienteEditado){
        
        try{
            miConexion=conexionBBDD.conectar();
            ps=miConexion.prepareStatement("update clientescazuela set nombre=?,"
                    + "apellido1=?,apellido2=?,telefono=? where usuario=?");

            ps.setString(1,clienteEditado.getNombre());
            ps.setString(2,clienteEditado.getApellido1());
            ps.setString(3, clienteEditado.getApellido2());
            ps.setString(4, clienteEditado.getTelefono());
            ps.setString(5, clienteEditado.getUsuario());

            int resultado = ps.executeUpdate();
            if(resultado>0){ //Si modificó correctamente en la BBDD
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
     * Método que recibe los datos del cliente cuyo usuario tiene que editar,
     * y lo actualiza en la base de datos
     * @param clienteEditado Cliente cuyo usuario va a actualizar a la base de datos
     * @return true si lo actualizó correctamente en la base de datos
     */
    public boolean editarUsuarioBBDD (UsuarioCliente clienteEditado){
        
        String usuarioActualizado = ((clienteEditado.getNombre())+
                (clienteEditado.getApellido1()).toLowerCase());
        
        try{
            miConexion=conexionBBDD.conectar();
            ps=miConexion.prepareStatement("update clientescazuela set usuario=? where telefono=?");
            
            ps.setString(1,usuarioActualizado);
            ps.setString(2, clienteEditado.getTelefono());

            int resultado = ps.executeUpdate();
            if(resultado>0){ //Si modificó correctamente en la BBDD
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
     * Método que recibe los datos del cliente a eliminar y lo elimina de la 
     * base de datos
     * @param clienteEliminar Cliente para eliminar de la base de datos
     * @return true si lo eliminó correctamente de la base de datos
     * @see controlador.ControladorConsultaEmpleados#eliminarCliente() 
     */
    public boolean eliminarClienteDeBBDD (UsuarioCliente clienteEliminar){
        
        try{
            miConexion=conexionBBDD.conectar();
            ps=miConexion.prepareStatement("delete from clientescazuela where "
                    + "(nombre=? and apellido1=? and apellido2=? and telefono=? "
                    + "and usuario=?)");
            
            ps.setString(1,clienteEliminar.getNombre());
            ps.setString(2,clienteEliminar.getApellido1());
            ps.setString(3, clienteEliminar.getApellido2());
            ps.setString(4, clienteEliminar.getTelefono());
            ps.setString(5, clienteEliminar.getUsuario());

            int resultado = ps.executeUpdate();
            if(resultado>0){ //Si eliminó correctamente en la BBDD
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
