package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import modelo.ConsultaEmpleadosDAO;
import modelo.UsuarioCliente;
import vista.VentanaConsultaEmpleados;
import vista.VentanaIngreso;

/**
 * Clase que procesa los eventos de la ventana VentanaConsultaEmpleados, 
 * llamando a ConsultaEmpleadosDAO para consulta a base de datos.
 * @author M. Celeste Cancio Vitale
 */

public class ControladorConsultaEmpleados implements ActionListener{
    //Declaro los objetos con los que me voy a relacionar.
    private VentanaConsultaEmpleados ventanaConsultaEmpleados;
    private ConsultaEmpleadosDAO consultaEmpleadosDAO = new ConsultaEmpleadosDAO();
    
    /**
     * Constructor de la clase
     * @param ventanaConsultaEmpleados Ventana en la que el empleado realiza sus consultas
     * @param consultaEmpleadosDAO Clase de consulta a base de datos
     */
    public ControladorConsultaEmpleados(VentanaConsultaEmpleados ventanaConsultaEmpleados, ConsultaEmpleadosDAO consultaEmpleadosDAO) {
        this.ventanaConsultaEmpleados = ventanaConsultaEmpleados;
        this.consultaEmpleadosDAO = consultaEmpleadosDAO;
    }
    
    /**
     * Constructor de la clase
     * @param ventanaConsultaEmpleados Ventana en la que el empleado realiza sus consultas
     */
    public ControladorConsultaEmpleados(VentanaConsultaEmpleados ventanaConsultaEmpleados) {
        this.ventanaConsultaEmpleados = ventanaConsultaEmpleados;
    }

    /**
     * Constructor de la clase, sin parámetros
     */
    public ControladorConsultaEmpleados() {
    }

    /**
     * Método que procesa los eventos de menu de la Ventana ventanaConsultaEmpleados
     * @param ae ActionEvent 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource() == ventanaConsultaEmpleados.mnuIVentanaIngreso){
            VentanaIngreso ventanaIngreso = new VentanaIngreso();
            ventanaIngreso.setVisible(true);
            ventanaConsultaEmpleados.dispose();
        }
        
        if(ae.getSource() == ventanaConsultaEmpleados.mnuISalir){
            System.exit(0);
        }
        
        if(ae.getSource() == ventanaConsultaEmpleados.btnIngresarNuevoCliente){
            insertarCliente();
        }
        
        if(ae.getSource() == ventanaConsultaEmpleados.btnIngresarClienteEditar){
            editarCliente();
        }
        
        if(ae.getSource() == ventanaConsultaEmpleados.btnEliminarCliente){
            eliminarCliente();
        }
    }
    
    /**
     * Método que obtiene los datos ingresados en las cajas de texto y los envía 
     * a la clase ConsultaEmpleadoDAO para ingresar el cliente a la base de datos
     * @see modelo.ConsultaEmpleadosDAO#insertarClienteEnBBDD(modelo.UsuarioCliente) 
     */
    public void insertarCliente(){

        UsuarioCliente nuevoCliente = new UsuarioCliente();
        nuevoCliente.setNombre(ventanaConsultaEmpleados.txtNombreNuevoCliente.getText());
        nuevoCliente.setApellido1(ventanaConsultaEmpleados.txtApellido1NuevoCliente.getText());
        nuevoCliente.setApellido2(ventanaConsultaEmpleados.txtApellido2NuevoCliente.getText());
        nuevoCliente.setTelefono(ventanaConsultaEmpleados.txtTelefonoNuevoCliente.getText());
        nuevoCliente.setUsuario((ventanaConsultaEmpleados.txtNombreNuevoCliente.getText()+
                (ventanaConsultaEmpleados.txtApellido1NuevoCliente.getText()).toLowerCase()));
        
        if(consultaEmpleadosDAO.insertarClienteEnBBDD(nuevoCliente)){
            JOptionPane.showMessageDialog(ventanaConsultaEmpleados, "Cliente insertado correctamente");
         }
        else{
            JOptionPane.showMessageDialog(ventanaConsultaEmpleados, new JLabel("No se pudo ingresar el nuevo cliente",JLabel.CENTER), "Cazuela", JOptionPane.ERROR_MESSAGE);
        }
        
        ventanaConsultaEmpleados.txtNombreNuevoCliente.setText(null);
        ventanaConsultaEmpleados.txtApellido1NuevoCliente.setText(null);
        ventanaConsultaEmpleados.txtApellido2NuevoCliente.setText(null);
        ventanaConsultaEmpleados.txtTelefonoNuevoCliente.setText(null);
        ventanaConsultaEmpleados.txtUsuarioNuevoCliente.setText(null);
        ventanaConsultaEmpleados.txtNombreNuevoCliente.requestFocus();
        
    }
    
    /**
     * Método que obtiene los datos ingresados en las cajas de texto y los envía 
     * a la clase ConsultaEmpleadoDAO para editar el cliente en la base de datos
     * @see modelo.ConsultaEmpleadosDAO#editarClienteEnBBDD(modelo.UsuarioCliente) 
     * @see modelo.ConsultaEmpleadosDAO#editarUsuarioBBDD(modelo.UsuarioCliente) 
     */
    public void editarCliente(){
        UsuarioCliente clienteEditado = new UsuarioCliente();
        clienteEditado.setUsuario(ventanaConsultaEmpleados.txtUsuarioClienteEditar.getText());
        clienteEditado.setNombre(ventanaConsultaEmpleados.txtNombreClienteEditar.getText());
        clienteEditado.setApellido1(ventanaConsultaEmpleados.txtApellido1ClienteEditar.getText());
        clienteEditado.setApellido2(ventanaConsultaEmpleados.txtApellido2ClienteEditar.getText());
        clienteEditado.setTelefono(ventanaConsultaEmpleados.txtTelefonoClienteEditar.getText());
        
        if(consultaEmpleadosDAO.editarClienteEnBBDD(clienteEditado)){
            JOptionPane.showMessageDialog(ventanaConsultaEmpleados, "Cliente editado correctamente");
         }
        else{
            JOptionPane.showMessageDialog(ventanaConsultaEmpleados, new JLabel("No se pudo editar el cliente",JLabel.CENTER), "Cazuela", JOptionPane.ERROR_MESSAGE);
        }
        
        if(consultaEmpleadosDAO.editarUsuarioBBDD(clienteEditado)){
            String usuarioActualizado = (ventanaConsultaEmpleados.txtNombreClienteEditar.getText()+
                (ventanaConsultaEmpleados.txtApellido1ClienteEditar.getText()).toLowerCase());
            JOptionPane.showMessageDialog(ventanaConsultaEmpleados, "Usuario editado correctamente: "+usuarioActualizado);
        }
        else{
            JOptionPane.showMessageDialog(ventanaConsultaEmpleados, new JLabel("No se pudo actualizar el usuario",JLabel.CENTER), "Cazuela", JOptionPane.ERROR_MESSAGE);
        }

        ventanaConsultaEmpleados.txtUsuarioClienteEditar.setText(null);
        ventanaConsultaEmpleados.txtNombreClienteEditar.setText(null);
        ventanaConsultaEmpleados.txtApellido1ClienteEditar.setText(null);
        ventanaConsultaEmpleados.txtApellido2ClienteEditar.setText(null);
        ventanaConsultaEmpleados.txtTelefonoClienteEditar.setText(null);
        ventanaConsultaEmpleados.txtUsuarioClienteEditar.requestFocus();

    }
    
    /**
     * Método que obtiene los datos ingresados en las cajas de texto y los envía 
     * a la clase ConsultaEmpleadoDAO para eliminar al cliente de la base de datos
     * @see modelo.ConsultaEmpleadosDAO#eliminarClienteDeBBDD(modelo.UsuarioCliente) 
     */
    public void eliminarCliente(){
        UsuarioCliente clienteEliminar = new UsuarioCliente();
        clienteEliminar.setUsuario(ventanaConsultaEmpleados.txtUsuarioClienteEliminar.getText());
        clienteEliminar.setNombre(ventanaConsultaEmpleados.txtNombreClienteEliminar.getText());
        clienteEliminar.setApellido1(ventanaConsultaEmpleados.txtApellido1ClienteEliminar.getText());
        clienteEliminar.setApellido2(ventanaConsultaEmpleados.txtApellido2ClienteEliminar.getText());
        clienteEliminar.setTelefono(ventanaConsultaEmpleados.txtTelefonoClienteEliminar.getText());
        
        if(consultaEmpleadosDAO.eliminarClienteDeBBDD(clienteEliminar)){
            JOptionPane.showMessageDialog(ventanaConsultaEmpleados, "Cliente eliminado correctamente");
         }
        else{
            JOptionPane.showMessageDialog(ventanaConsultaEmpleados, new JLabel("No se pudo eliminar el cliente",JLabel.CENTER), "Cazuela", JOptionPane.ERROR_MESSAGE);
        }
        
        ventanaConsultaEmpleados.txtUsuarioClienteEliminar.setText(null);
        ventanaConsultaEmpleados.txtNombreClienteEliminar.setText(null);
        ventanaConsultaEmpleados.txtApellido1ClienteEliminar.setText(null);
        ventanaConsultaEmpleados.txtApellido2ClienteEliminar.setText(null);
        ventanaConsultaEmpleados.txtTelefonoClienteEliminar.setText(null);
        ventanaConsultaEmpleados.txtUsuarioClienteEliminar.requestFocus();
    }
    
}
