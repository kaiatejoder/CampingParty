package MODELO;

public class Staff {
    private final String usuario;
    private final String contrasena;
    private final String nombre;

    public Staff(String usuario, String contrasena, String nombre) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
    }

    public String getUsuario() { return usuario; }
    public String getContrasena() { return contrasena; }
    public String getNombre() { return nombre; }
}
