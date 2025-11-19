package Modelo;

/**
 *
 * @author Sergio Gimenez Gomez
 */
import java.util.ArrayList;
/**
 * Clase Cliente que representa un cliente del camping.
 * @author Carla Terol
 */
public class Cliente extends Persona {
    private int edad;
    private ArrayList<Tienda> tiendas;
    private Reservas reservas;

    public Cliente(String nombre, String dni, int edad, int telefono, String username, String password){
        super(username, password, nombre, dni, telefono, 1); // role = 1 para CLIENTE
        this.edad = edad;
        this.tiendas = new ArrayList<>();
        this.reservas = new Reservas();
    }
    
    public Cliente(String nombre, String dni, int edad){
        super(); // Constructor vacío de Persona
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.role = 1; // CLIENTE
        this.tiendas = new ArrayList<>();
        this.reservas = new Reservas();
    }

    public Cliente() {
        super(); // Constructor vacío sin excepción
        this.tiendas = new ArrayList<>();
        this.reservas = new Reservas();
        this.role = 1; // CLIENTE por defecto
    }

    public void setUser(String u){
        this.user = u;
    }
    
    public void setPass(String p){
        this.pass = p;
    }
    
    public void setTlf(int t){
         this.tlf = t;
    }
    
    public void setPassword(String p){
        this.pass = p; 
    }
    
    public void addReserva(Reserva r){
        this.reservas.addReserva(r);
    }
    
    public boolean tieneReserva(){
        return !this.reservas.isEmpty();
    }
    
    public void borrarReserva(int index){
        this.reservas.cancelarReserva(index); // Usar el método de Reservas
    }
    
    public Reserva getReserva(int index){
        return reservas.getReserva(index); // Usar el método de Reservas
    }
    
    public int getNumReservas(){
        return reservas.size(); // Usar el método size() de Reservas
    }
    
    public int getEdad(){
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public Reservas getReservas(){
        return reservas;
    }
    
    public ArrayList<Tienda> getTiendas(){
        return new ArrayList<>(tiendas); // Devolver copia
    }
    
    public void addTienda(Tienda t){
        this.tiendas.add(t);
    }
    
    public void removeTienda(Tienda t) {
        this.tiendas.remove(t);
    }
    
    public void setPhone(int s){
        this.tlf = s;
    }
    
    @Override
    public String toString(){
        return dni + ";" + nombre + ";" + edad + ";" + tlf + ";" + user + ";" + pass + "\n";
    }
}