package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;

/**
 * Clase que procesa el ingreso de empleados, según evento de la VentanaIngreso, 
 * llamando a UsuarioEmpleadoDAO para consulta a base de datos.
 * @author M. Celeste Cancio Vitale
 */
public class ControladorUsuarioEmpleado implements ActionListener{
    
    //Declaro todos los objetos con los que necesito interactuar
    private VentanaIngreso ventanaIngreso;
    private UsuarioEmpleado usuarioEmpleado = new UsuarioEmpleado();
    private UsuarioEmpleadoDAO empleadoDAO = new UsuarioEmpleadoDAO();

    /**
     * Constructor de la clase ControladorUsuarioEmpleado, sin parámetros
     */
    public ControladorUsuarioEmpleado() {
    }
    
    /**
     * Constructor de la clase ControladorUsuarioEmpleado
     * @param ventanaIngreso Ventana de Ingreso donde se validan los datos
     * @param usuarioEmpleado Usuario empleado
     * @param empleadoDAO Conexión a BBDD
     */
    public ControladorUsuarioEmpleado (VentanaIngreso ventanaIngreso, UsuarioEmpleado usuarioEmpleado, UsuarioEmpleadoDAO empleadoDAO){
        this.ventanaIngreso = ventanaIngreso;
        this.usuarioEmpleado = usuarioEmpleado;
        this.empleadoDAO = empleadoDAO;
    }
    
    /**
     * Constructor de la clase ControladorUsuarioEmpleado
     * @param ventanaIngreso Ventana de Ingreso donde se validan los datos
     */
    public ControladorUsuarioEmpleado(VentanaIngreso ventanaIngreso) {
        this.ventanaIngreso = ventanaIngreso;
    }
    
    /**
     * @see #validarEmpleado()
     * Método que procesa el evento del botón Ingresar del panel Empleado de la ventana 
     * VentanaIngreso, llamando al método validarEmpleado() para conexión a la 
     * clase UsuarioClienteDAO.  
     * @param ae ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == ventanaIngreso.btnIngresarEmpleado){
            validarEmpleado();
        }
    }
    
    /**
     * Método que valida el ingreso del usuario empleado. Verifica si las cajas 
     * de texto están vacías. De lo contrario, llama al método 
     * validarEmpleadoeBBDD(String telefono, String usuario) de la
     * clase UsuarioEmpleadoDAO para consulta a base de datos.
     * @see modelo.UsuarioEmpleadoDAO#validarEmpleadoBBDD(java.lang.String, java.lang.String) 
     */
    public void validarEmpleado(){
        
        String usuario = ventanaIngreso.txtUsuarioEmpleado.getText();
        String telefono = ventanaIngreso.txtContrasenaEmpleado.getText();
        String nombre="";        
        
        if(ventanaIngreso.txtUsuarioEmpleado.getText().equals("") || ventanaIngreso.txtContrasenaEmpleado.getText().equals("")){//si dejó alguna vacía
            JOptionPane.showMessageDialog(ventanaIngreso, "Completá usuario y contraseña por favor!");
            ventanaIngreso.txtUsuarioEmpleado.requestFocus();//pongo el cursor en el txt
        }else{           
            nombre= empleadoDAO.validarEmpleadoBBDD(telefono, usuario);            
            if((nombre!="")){ //Si el método devolvió un nombre que tiene un valor distinto al inicializado vacío, entra al if            
                JOptionPane.showMessageDialog(ventanaIngreso, new JLabel("Bienvenido/a "+nombre.toUpperCase()+"! Que tengas un lindo día de trabajo!",JLabel.CENTER), "Cazuela", JOptionPane.PLAIN_MESSAGE);
                VentanaConsultaEmpleados ventanaGestionEmpleados = new VentanaConsultaEmpleados();//instancio la ventana a la cual pasa si ingresa los datos correctamente
                ventanaGestionEmpleados.setVisible(true);//hago visible la ventana que instancié
                ventanaIngreso.dispose();//cierra la ventana de ingreso y libera recursos
            }
            else{
                JOptionPane.showMessageDialog(ventanaIngreso, "Usuario y/o contraseña no válidos");
                ventanaIngreso.txtUsuarioEmpleado.setText(null);
                ventanaIngreso.txtContrasenaEmpleado.setText(null);
                ventanaIngreso.txtUsuarioEmpleado.requestFocus();//pongo el cursor en el txt
            }
        }
    }
}
