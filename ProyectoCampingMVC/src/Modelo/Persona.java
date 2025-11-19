package Modelo;

/**
 *
 * @author Sergio Gimenez Gomez
 */
public class Persona {
    protected String user;
    protected String pass;
    protected String nombre;
    protected String dni;
    protected int tlf;
    protected int role;
    public String[] TipoRole = {
        "STAFF",
        "CLIENTE",
        "ACOMPANYANTE",
    };

    public Persona(String usuario, String contrasena, String nombre, String dni, int tlf, int role) {
        this.user = usuario;
        this.pass = contrasena;
        this.nombre = nombre;
        this.tlf = tlf;
        this.dni = dni;
        this.role = role;
    }
    
    public Persona(String usuario, String contrasena, String nombre, String dni, int tlf) {
        this.user = usuario;
        this.pass = contrasena;
        this.nombre = nombre;
        this.tlf = tlf;
        this.dni = dni;
    }
    
    public Persona() {
        // Constructor vacío sin excepción
    }

    public String getUser() { return user; }
    public String getPass() { return pass; }
    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public String getRole() { return TipoRole[role]; }
    public int getTlf() { return tlf; }
    public void setPass(String pass) { this.pass = pass; }  
    
    public void setUser(String user) { this.user = user; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDni(String dni) { this.dni = dni; }
    public void setTlf(int tlf) { this.tlf = tlf; }
    public void setRole(int role) { this.role = role; }
}