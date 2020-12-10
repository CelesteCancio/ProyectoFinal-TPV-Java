package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.String.valueOf;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;

/**
 * Clase que procesa los eventos de la ventana VentanaProductos, llamando a 
 * ProductoDAO para consulta a base de datos
 * @author M. Celeste Cancio Vitale
 */
public class ControladorProducto implements ActionListener{
    
    //Declaro todos los objetos con los que necesito interactuar
    private VentanaProductos ventanaProductos; 
    private Producto producto = new Producto();
    private ProductoDAO productoDAO = new ProductoDAO();
    
    private double subtotal=0;
    
    private ArrayList <Producto> listaProductosSeleccionados = new ArrayList<>();

    /**
     * Constructor de la clase ControladorProducto
     * @param ventanaProductos ventana que se vincula con la clase ControladorProducto
     * @param producto objecto de la clase Producto 
     * @param productoDAO objeto de la clase productoDAO (consultas a base de datos)
     */
    public ControladorProducto(VentanaProductos ventanaProductos, Producto producto, ProductoDAO productoDAO) {
        this.ventanaProductos = ventanaProductos;
        this.producto = producto;
        this.productoDAO = productoDAO;
    }
    
    /**
     * Constructor de la clase ControladorProducto, sin parámetros
     */
    public ControladorProducto() {
    }
    
    /**
     * Constructor de la clase ControladorProducto
     * @param ventanaProductos ventana que se vincula con la clase ControladorProducto
     */
    public ControladorProducto(VentanaProductos ventanaProductos) {
        this.ventanaProductos = ventanaProductos;
    }

    /**
    * Método que procesa los eventos de botón: selección de productos y 
    * ejecución de compra, de la ventana VentanaProductos
    * @param ae ActionEvent, corresponde al botón seleccionado en VentanaProductos
    */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == ventanaProductos.btnCafeConLeche){                              
            String aliasProducto = "cafeConLeche";           
            seleccionProducto(aliasProducto);
        }
        if(ae.getSource() == ventanaProductos.btnTe){                              
            String aliasProducto = "te";
            seleccionProducto(aliasProducto);
        }
        if(ae.getSource() == ventanaProductos.btnMate){                              
            String aliasProducto = "mate";
            seleccionProducto(aliasProducto);
        }
        if(ae.getSource() == ventanaProductos.btnLicuadoFrutal){                              
            String aliasProducto = "licuadoFrutal";
            seleccionProducto(aliasProducto);
        }
        if(ae.getSource() == ventanaProductos.btnJugoDeNaranja){                              
            String aliasProducto = "jugoDeNaranja";
            seleccionProducto(aliasProducto);
        }
        if(ae.getSource() == ventanaProductos.btnYogurConGranola){                              
            String aliasProducto = "yogurConGranola";
            seleccionProducto(aliasProducto);
        }
        if(ae.getSource() == ventanaProductos.btnMedialunas){                              
            String aliasProducto = "medialunas";
            seleccionProducto(aliasProducto);            
        }
        if(ae.getSource() == ventanaProductos.btnTostadasFrancesas){                              
            String aliasProducto = "tostadasFrancesas";
            seleccionProducto(aliasProducto);
        }
        if(ae.getSource() == ventanaProductos.btnBizcochitos){                              
            String aliasProducto = "bizcochitos";
            seleccionProducto(aliasProducto);
        }
        if(ae.getSource() == ventanaProductos.btnPlatoDeFruta){                              
            String aliasProducto = "platoDeFruta";
            seleccionProducto(aliasProducto);
        }
        if(ae.getSource() == ventanaProductos.btnTortaDelDia){                              
            String aliasProducto = "tortaDelDia";
            seleccionProducto(aliasProducto);
        }
        if(ae.getSource() == ventanaProductos.btnTostado){                              
            String aliasProducto = "tostado";
            seleccionProducto(aliasProducto);
        }
        if(ae.getSource() == ventanaProductos.btnComprar){                  
            double precioTotal=sumarPrecioTotal(listaProductosSeleccionados);            
            if(productoDAO.insertar(listaProductosSeleccionados)){
                JOptionPane.showMessageDialog(ventanaProductos, "El total de tu compra es de " +subtotal+" euros.\n\nPasá por caja para pagar tu pedido.\n\nGracias por confiar en Cazuela!");                            
                ventanaProductos.dispose();
                VentanaIngreso ventanaIngreso = new VentanaIngreso();//vuelve al ingreso, para que haga el log in otra persona
                ventanaIngreso.setVisible(true);
            }
            else{                   
                JOptionPane.showMessageDialog(ventanaProductos, "Error al procesar la compra, intentá nuevamente", "Ups!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Método que recibe el alias del producto seleccionado y llama a ProductoDAO
     * para realizar la consulta a base de datos.
     * @see modelo.ProductoDAO#buscarProducto(modelo.Producto)
     * Si el método buscarProducto de ProductoDAO, devuelve que el producto existe, 
     * se agrega el item a la lista de compras, y se suma el precio subtotal. Si 
     * devuelve que no existe, se avisa al usuario con un mensaje.
     * @param aliasProducto alias del producto
     * 
     */
    public void seleccionProducto(String aliasProducto){
        producto.setAliasProducto(aliasProducto);
            if(productoDAO.buscarProducto(producto)){                
                mostrarEnTablaProductos(producto);
                agregarAlArrayListProductos(producto);              
                subtotal=obtenerSubtotalPrecio(producto,subtotal);
                imprimirSubtotalPrecio(subtotal);                
            }
            else{
                 JOptionPane.showMessageDialog(ventanaProductos, "Producto no encontrado, lo sentimos");
            }        
    }
    
    /**
     * Método para sumar el precio subtotal de productos que se fueron seleccionando
     * @param producto Producto recibido para sumar su precio
     * @param subtotalPrecio Precio subtotal hasta el momento
     * @return subtotalPrecio Nuevo precio subtotal hasta el momento
     */
    public double obtenerSubtotalPrecio(Producto producto, double subtotalPrecio){
        subtotalPrecio += producto.getPrecioProducto();
        return subtotalPrecio;
    }
    
    /**
     * Método para mostrar el precio subtotal en la VentanaProductos
     * @param subtotalPrecio Precio subtotal hasta el momento
     */
    public void imprimirSubtotalPrecio(double subtotalPrecio){
        ventanaProductos.txtSubtotal.setText(valueOf(subtotalPrecio));
    }
    /**
     * Método para mostrar los productos que se van seleccionando en la 
     * VentanaProductos
     * @param producto Producto seleccionado
     */
    public void mostrarEnTablaProductos (Producto producto){               
        ventanaProductos.txtAreaProductosSeleccionados.append(producto.getNombreProducto()+"\n");
        ventanaProductos.txtAreaPrecioProductosSeleccionados.append(producto.getPrecioProducto()+"\n");  
     }   
    
    /**
     * Método que guarda los productos que se van seleccionando en un ArrayList
     * @param producto Producto seleccionado
     */
    public void agregarAlArrayListProductos(Producto producto){
        //listaProductosSeleccionados.add(producto);//Asi esta mal, no funciona. (Entiendo que x referencia a puntero)
        listaProductosSeleccionados.add(new Producto(producto.getNombreProducto(),producto.getPrecioProducto(),producto.getAliasProducto()));
    }
    
    /**
     * Método que imprime en pantalla el ArrayList de productos seleccionados.
     * Su utilidad es permitir controlar que se estén agregando correctamente los 
     * productos a la lista, que son los que luego se insertarán en la base de datos
     */
    public void imprimirArrayListProductos(){
        System.out.println("\nlista productos seleccionados: ");
        ListIterator <Producto> it = listaProductosSeleccionados.listIterator();
        while(it.hasNext()){           
            System.out.println(it.next().getNombreProducto());
        }
        System.out.println("\n");
    }
    
    /**
     * Método que suma el precio total de la compra. No se utiliza, ya que se 
     * trabaja con el subtotal que va sumando producto a producto, pero queda
     * por si es necesario realizar un control adicional del monto total.
     * @param listaProductosSeleccionados ArrayList de productos seleccionados
     * @return precioTotal Precio total, obtenido de sumar los precios de los 
     * productos de la lista
     */
    public double sumarPrecioTotal(ArrayList <Producto> listaProductosSeleccionados){
        double precioTotal=0;
        for (int i = 0; i < listaProductosSeleccionados.size(); i++) {
            precioTotal+=listaProductosSeleccionados.get(i).getPrecioProducto();            
        }
        return precioTotal;
    }
    
}
