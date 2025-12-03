package MODELO;

import java.sql.*;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 * Clase DAO para acceso a datos con base de datos MySQL.
 * Gestiona todas las operaciones CRUD para Camping Riu Rau.
 * BD: cbd (root/root)
 *
 * @author Carla Terol
 */
public class DAO {

    private Connection conexionBD;

    public DAO() {
        // Conectar a la BD "cbd" con credentials root/root
        String bd = "jdbc:mysql://localhost/cbd?serverTimezone=" + TimeZone.getDefault().getID();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexionBD = DriverManager.getConnection(bd, "root", "root");
            System.out.println("✓ Conexión a BD 'cbd' exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("✗ Error de conexión: " + e.getMessage());
        }
    }

    /**
     * Obtiene un cliente por usuario y contraseña
     */
    public Cliente getCliente(String user, String pass) {
        Cliente c = null;
        try {
            String sql = "SELECT ID, name, surn1, surn2, DNI, EDAD, tlf, user, pass FROM persona WHERE user = ? AND pass = ?";
            PreparedStatement ps = conexionBD.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet resultados = ps.executeQuery();
            
            if (resultados.next()) {
                String nombre = resultados.getString("name");
                String apellido1 = resultados.getString("surn1");
                String apellido2 = resultados.getString("surn2");
                String dni = resultados.getString("DNI");
                int edad = resultados.getInt("EDAD");
                int telefono = resultados.getInt("tlf");
                String nombreCompleto = nombre + " " + apellido1 + (apellido2 != null ? " " + apellido2 : "");
                
                c = new Cliente(nombreCompleto, dni, edad, telefono, user, pass);
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al obtener cliente: " + e.getMessage());
        }
        return c;
    }

    /**
     * Obtiene todas las actividades de la BD
     */
    public ArrayList<Actividad> getActividades() throws SQLException {
        ArrayList<Actividad> actividades = new ArrayList<>();
        try {
            String sql = "SELECT idact, type, date, max FROM act";
            Statement sent = conexionBD.createStatement();
            ResultSet res = sent.executeQuery(sql);
            
            while (res.next()) {
                int idact = res.getInt("idact");
                String type = res.getString("type");
                Timestamp timestamp = res.getTimestamp("date");
                java.util.Date date = timestamp != null ? new java.util.Date(timestamp.getTime()) : null;
                int max = res.getInt("max");
                
                actividades.add(new Actividad(idact, type, date, max));
            }
            System.out.println("✓ Se cargaron " + actividades.size() + " actividades");
        } catch (SQLException e) {
            System.err.println("✗ Error consultando actividades: " + e.getMessage());
            throw e;
        }
        return actividades;
    }

    /**
     * Agrega un nuevo cliente a la BD
     */
    public boolean agregarCliente(Cliente cliente) {
        try {
            String sql = "INSERT INTO persona (name, surn1, surn2, DNI, EDAD, tlf, user, pass, ban) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0)";
            PreparedStatement ps = conexionBD.prepareStatement(sql);
            
            String[] nombres = cliente.getNombre().split(" ");
            ps.setString(1, nombres[0]); // name
            ps.setString(2, nombres.length > 1 ? nombres[1] : ""); // surn1
            ps.setString(3, nombres.length > 2 ? nombres[2] : null); // surn2
            ps.setString(4, cliente.getDni());
            ps.setInt(5, cliente.getEdad());
            ps.setInt(6, cliente.getTlf());
            ps.setString(7, cliente.getUser());
            ps.setString(8, cliente.getPass());
            
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                System.out.println("✓ Cliente registrado: " + cliente.getNombre());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al agregar cliente: " + e.getMessage());
        }
        return false;
    }

    /**
     * Agrega una nueva actividad a la BD
     */
    public boolean agregarActividad(Actividad actividad) {
        try {
            String sql = "INSERT INTO act (idact, type, date, max) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexionBD.prepareStatement(sql);
            
            ps.setInt(1, actividad.getIdActividad());
            ps.setString(2, actividad.getTipo());
            ps.setTimestamp(3, new java.sql.Timestamp(actividad.getFechaHora().getTime()));
            ps.setInt(4, actividad.getMaxParticipantes());
            
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                System.out.println("✓ Actividad agregada: " + actividad.getTipo());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al agregar actividad: " + e.getMessage());
        }
        return false;
    }

    /**
     * Agrega una nueva reserva a la BD
     */
    public boolean agregarReserva(Reserva reserva, int idCliente) {
        try {
            String sql = "INSERT INTO reserva (id, cliente, dateresin, dateresout, datein, dateout, tot, day) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexionBD.prepareStatement(sql);
            
            ps.setInt(1, idCliente);
            ps.setString(2, reserva.getCliente().getNombre());
            ps.setTimestamp(3, new java.sql.Timestamp(reserva.getInicioReserva().getTime()));
            ps.setTimestamp(4, new java.sql.Timestamp(reserva.getFinReserva().getTime()));
            ps.setTimestamp(5, new java.sql.Timestamp(reserva.getInicioEstancia().getTime()));
            ps.setTimestamp(6, new java.sql.Timestamp(reserva.getFinEstancia().getTime()));
            ps.setFloat(7, reserva.getPrecioTotal());
            ps.setFloat(8, reserva.getPrecioDia());
            
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                System.out.println("✓ Reserva agregada para: " + reserva.getCliente().getNombre());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al agregar reserva: " + e.getMessage());
        }
        return false;
    }

    /**
     * Obtiene todas las parcelas disponibles
     */
    public ArrayList<Parcela> getParcelasDisponibles() {
        ArrayList<Parcela> parcelas = new ArrayList<>();
        try {
            String sql = "SELECT idparcela, PRICE, M2, L FROM parcela WHERE OC = 0 AND RES = 0";
            Statement sent = conexionBD.createStatement();
            ResultSet res = sent.executeQuery(sql);
            
            while (res.next()) {
                int id = res.getInt("idparcela");
                float precio = res.getFloat("PRICE");
                float m2 = res.getFloat("M2");
                boolean luz = res.getBoolean("L");
                
                parcelas.add(new Parcela(id, m2, luz, precio));
            }
            System.out.println("✓ Se cargaron " + parcelas.size() + " parcelas disponibles");
        } catch (SQLException e) {
            System.err.println("✗ Error obteniendo parcelas: " + e.getMessage());
        }
        return parcelas;
    }

    /**
     * Actualiza el estado de una parcela (ocupada/reservada)
     */
    public boolean actualizarParcela(int idParcela, boolean ocupada, boolean reservada, java.util.Date fechaOcupacion, java.util.Date fechaFuera) {
        try {
            String sql = "UPDATE parcela SET OC = ?, RES = ?, DATEOC = ?, DATEOUT = ? WHERE idparcela = ?";
            PreparedStatement ps = conexionBD.prepareStatement(sql);
            
            ps.setInt(1, ocupada ? 1 : 0);
            ps.setInt(2, reservada ? 1 : 0);
            ps.setTimestamp(3, fechaOcupacion != null ? new java.sql.Timestamp(fechaOcupacion.getTime()) : null);
            ps.setTimestamp(4, fechaFuera != null ? new java.sql.Timestamp(fechaFuera.getTime()) : null);
            ps.setInt(5, idParcela);
            
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                System.out.println("✓ Parcela " + idParcela + " actualizada");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al actualizar parcela: " + e.getMessage());
        }
        return false;
    }

    /**
     * Obtiene las reservas de un cliente específico
     */
    public ArrayList<Reserva> getReservasCliente(int idCliente) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        try {
            String sql = "SELECT id, dateresin, dateresout, datein, dateout, tot, day FROM reserva WHERE id = ?";
            PreparedStatement ps = conexionBD.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ResultSet res = ps.executeQuery();
            
            while (res.next()) {
                java.util.Date inicioReserva = new java.util.Date(res.getTimestamp("dateresin").getTime());
                java.util.Date finReserva = new java.util.Date(res.getTimestamp("dateresout").getTime());
                Reserva r = new Reserva(inicioReserva, finReserva, null);
                reservas.add(r);
            }
            System.out.println("✓ Se cargaron " + reservas.size() + " reservas para cliente " + idCliente);
        } catch (SQLException e) {
            System.err.println("✗ Error obteniendo reservas: " + e.getMessage());
        }
        return reservas;
    }

    /**
     * Agrega una participación en una actividad
     */
    public boolean agregarParticipacion(int idCliente, int idActividad) {
        try {
            String sql = "INSERT INTO part (pers, act, is) VALUES (?, ?, 1)";
            PreparedStatement ps = conexionBD.prepareStatement(sql);
            
            ps.setInt(1, idCliente);
            ps.setInt(2, idActividad);
            
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                System.out.println("✓ Participación registrada: Cliente " + idCliente + " en Actividad " + idActividad);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al agregar participación: " + e.getMessage());
        }
        return false;
    }

    /**
     * Obtiene el ID del cliente por su DNI
     */
    public int obtenerIdClientePorDNI(String dni) {
        try {
            String sql = "SELECT ID FROM persona WHERE DNI = ? LIMIT 1";
            PreparedStatement ps = conexionBD.prepareStatement(sql);
            ps.setString(1, dni);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al obtener ID del cliente: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Cierra la conexión a la BD
     */
    public void cerrarConexion() {
        try {
            if (conexionBD != null && !conexionBD.isClosed()) {
                conexionBD.close();
                System.out.println("✓ Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al cerrar conexión: " + e.getMessage());
        }
    }
}
