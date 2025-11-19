package Modelo;

/**
 *
 * @author Sergio Gimenez Gomez
 */
public class Acompanyante extends Persona {
    private int edad;
    
    public Acompanyante(String nombre, int edad){
        super(); // Llamada al constructor de Persona
        this.nombre = nombre;
        this.edad = edad;
        this.role = 2; // ACOMPANYANTE
    }
    
    public int getEdad(){
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    @Override
    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}