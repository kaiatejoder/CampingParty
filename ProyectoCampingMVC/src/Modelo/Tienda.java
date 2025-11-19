package Modelo;

/**
 *
 * @author Sergio Gimenez Gomez
 */
public class Tienda {
    private String nombre;
    private float m2;

    public Tienda(String nombre, float m2){
        this.nombre = nombre;
        this.m2 = m2;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public float getM2(){
        return m2;
    }
    
    public void setM2(float m2) {
        this.m2 = m2;
    }
    
    @Override
    public String toString() {
        return nombre + " (" + m2 + "m²)";
    }
}