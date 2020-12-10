package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import modelo.UsuarioClienteDAO;
import modelo.UsuarioCliente;
import vista.VentanaIngreso;
import vista.VentanaProductos;

/**
 * Clase que procesa el ingreso de clientes, según evento de la VentanaIngreso, 
 * llamando a UsuarioClienteDAO para consulta a base de datos.
 * @author M. Celeste Cancio Vitale
 */
public class ControladorUsuarioCliente implements ActionListener{
    
    //Declaro todos los objetos con los que necesito interactuar
    private VentanaIngreso ventanaIngreso;
    private UsuarioCliente usuarioCliente = new UsuarioCliente();
    private UsuarioClienteDAO clienteDAO = new UsuarioClienteDAO();

    /**
     * Constructor de la clase ControladorUsuarioCliente
     * @param ventanaIngreso Ventana de Ingreso donde se validan los datos
     * @param usuarioCliente Usuario cliente
     * @param clienteDAO Conexión a BBDD
     */
    public ControladorUsuarioCliente(VentanaIngreso ventanaIngreso, UsuarioCliente usuarioCliente, UsuarioClienteDAO clienteDAO) {
        this.ventanaIngreso = ventanaIngreso;
        this.usuarioCliente = usuarioCliente;
        this.clienteDAO = clienteDAO;
    }
    /**
     * Constructor de la clase ControladorUsuarioCliente
     * @param ventanaIngreso Ventana de Ingreso donde se validan los datos
     */
    public ControladorUsuarioCliente(VentanaIngreso ventanaIngreso) {
        this.ventanaIngreso = ventanaIngreso;
    }
    
    /**
     * Método que inicializa título y ubicación de la ventana de ingreso
     */
    public void iniciarVentanaIngreso(){
        ventanaIngreso.setTitle("Ingreso Cazuela");
        ventanaIngreso.setLocationRelativeTo(null);
    }

    /**
     * @see #validarCliente() 
     * Método que procesa el evento del botón Ingresar del panel Cliente de la ventana 
     * VentanaIngreso, llamando al método validarCliente() para conexión a la 
     * clase UsuarioEmpleadoDAO.
     * 
     * @param ae ActionEvent 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == ventanaIngreso.btnIngresarCliente){
            validarCliente();
        }
            
    }
    
    /**
     * Método que valida el ingreso del usuario cliente. Verifica si las cajas 
     * de texto están vacías. De lo contrario, llama al método 
     * validarClienteBBDD(String telefono, String usuario) de la
     * clase UsuarioClienteDAO para consulta a base de datos.
     * @see modelo.UsuarioClienteDAO#validarClienteBBDD(java.lang.String, java.lang.String) 
     */
    public void validarCliente(){
        
        String usuario = ventanaIngreso.txtUsuarioCliente.getText();
        String telefono = ventanaIngreso.txtContrasenaCliente.getText();
        String nombre = "";
                
        if(ventanaIngreso.txtUsuarioCliente.getText().equals("") || ventanaIngreso.txtContrasenaCliente.getText().equals("")){//si dejó alguna vacía
            JOptionPane.showMessageDialog(ventanaIngreso, "Completá usuario y contraseña por favor!");
            ventanaIngreso.txtUsuarioCliente.requestFocus();//pongo el cursor en el txt
        }else{           
            
            nombre=clienteDAO.validarClienteBBDD(telefono, usuario);            
            
            if((nombre!="")){ //Si el método devolvió un nombre que tiene un valor distinto al inicializado vacío, entra al if
                
                JOptionPane.showMessageDialog(ventanaIngreso, new JLabel("Bienvenido/a "+nombre.toUpperCase()+"! A elegir el desayuno!",JLabel.CENTER), "Cazuela", JOptionPane.PLAIN_MESSAGE);
                VentanaProductos ventanaProductos = new VentanaProductos();//instancio la ventana a la cual pasa si ingresa los datos correctamente
                ventanaProductos.setVisible(true);//hago visible la ventana que instancié
                ventanaIngreso.dispose();//cierra la ventana de ingreso y libera recursos
            }
            else{
                JOptionPane.showMessageDialog(ventanaIngreso, "Usuario y/o contraseña no válidos");
                ventanaIngreso.txtUsuarioCliente.setText(null);
                ventanaIngreso.txtContrasenaCliente.setText(null);
                ventanaIngreso.txtUsuarioCliente.requestFocus();//pongo el cursor en el txt
            }
        }
    }
    
}
