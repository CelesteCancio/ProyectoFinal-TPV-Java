package principal;

import modelo.ConsultaEmpleadosDAO;
import controlador.*;
import modelo.*;
import vista.*;

/**
 * Clase principal, que contiene el main para lanzar la aplicación
 * @author M. Celeste Cancio Vitale
 */
public class Principal {
    public static void main(String[] args) {
        
        VentanaIngreso ventanaIngreso = new VentanaIngreso();
        
        UsuarioCliente usuarioCliente = new UsuarioCliente();
        UsuarioClienteDAO usuarioClienteDAO = new UsuarioClienteDAO();
        ControladorUsuarioCliente controladorUsuarioCliente = new ControladorUsuarioCliente (ventanaIngreso,usuarioCliente,usuarioClienteDAO);
        
        UsuarioEmpleado usuarioEmpleado = new UsuarioEmpleado();
        UsuarioEmpleadoDAO usuarioEmpleadoDAO = new UsuarioEmpleadoDAO();
        ControladorUsuarioEmpleado controladorUsuarioEmpleado = new ControladorUsuarioEmpleado (ventanaIngreso, usuarioEmpleado,usuarioEmpleadoDAO);
        
        controladorUsuarioCliente.iniciarVentanaIngreso();
        ventanaIngreso.setVisible(true);       
      
        VentanaProductos ventanaProductos = new VentanaProductos();
        Producto producto = new Producto();
        ProductoDAO productoDAO = new ProductoDAO();
        ControladorProducto controladorProducto = new ControladorProducto (ventanaProductos,producto,productoDAO);
        
        VentanaConsultaEmpleados ventanaConsultaEmpleados = new VentanaConsultaEmpleados();
        ConsultaEmpleadosDAO consultaEmpleadosDAO = new ConsultaEmpleadosDAO();
        ControladorConsultaEmpleados controladorConsultaEmpleados = new ControladorConsultaEmpleados(ventanaConsultaEmpleados,consultaEmpleadosDAO);
    
    
    }
}
