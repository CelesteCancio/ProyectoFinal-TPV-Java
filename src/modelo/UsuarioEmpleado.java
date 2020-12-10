package modelo;

/**
 * Clase que contiene los atributos y métodos del UsuarioEmpleado
 * @author M. Celeste Cancio Vitale
 */
public class UsuarioEmpleado {
    //Declaro las variables que son los mismos campos que la tabla empleados en la base de datos

    int id;
    String nombre;
    String apellido1;
    String apellido2;
    String telefono;
    String usuario;

    /**
     * Constructor de la clase, sin parámetros
     */    
    public UsuarioEmpleado() {
    }
    
    /**
     * Constructor de la clase
     * @param id Número de identificación de empleado (la base de datos lo asigna automáticamente)
     * @param nombre Nombre del empleado
     * @param apellido1 Apellido1 del empleado
     * @param apellido2 Apellido2 del empleado
     * @param telefono Teléfono del empleado: es la contraseña para el ingreso
     * @param usuario Usuario del empleado: es nombre+apellido1 en minúsculas
     */
    public UsuarioEmpleado(int id, String nombre, String apellido1, String apellido2, String telefono, String usuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.usuario = usuario;
    }
    
    /**
     * Setters y getters 
     */
    
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
