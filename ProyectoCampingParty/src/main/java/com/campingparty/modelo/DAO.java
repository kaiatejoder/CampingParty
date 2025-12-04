package com.campingparty.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 * DAO (Data Access Object) - Acceso a la base de datos.
 * 
 * Responsabilidades:
 * - Conectar con la BD
 * - Ejecutar consultas SQL (CRUD)
 * - Retornar datos al Modelo
 * - NO contiene lógica de negocio
 * 
 * @author Carla Terol
 */
public class DAO {
    private Connection conexionBD;

    public DAO() {
        conectarBD();
    }

    /**
     * Conecta con la base de datos
     */
    private void conectarBD() {
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
     * Verifica si la conexión está activa
     */
    public boolean estaConectado() {
        try {
            return conexionBD != null && !conexionBD.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    // ==================== CLIENTE ====================

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
                int id = resultados.getInt("id");
                String nombre = resultados.getString("name");
                String apellido1 = resultados.getString("surn1");
                String apellido2 = resultados.getString("surn2");
                String dni = resultados.getString("DNI");
                int edad = resultados.getInt("EDAD");
                int telefono = resultados.getInt("tlf");
                String nombreCompleto = nombre + " " + apellido1 + (apellido2 != null ? " " + apellido2 : "");

                c = new Cliente(nombreCompleto, dni, id, edad, telefono, user, pass);
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al obtener cliente: " + e.getMessage());
        }
        return c;
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
    // ==================== RESERVAS =============================
    public ArrayList<Reserva> getAllReservas(){
        ArrayList<Reserva> reservas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reserva WHERE dateout = null";
            Statement sent = conexionBD.createStatement();
            ResultSet res = sent.executeQuery(sql);

            while (res.next()) {
                int id = res.getInt("id");
                int c = res.getInt("cliente");
                Timestamp timeresin = res.getTimestamp("dateresin");
                java.util.Date dateresin = timeresin != null ? new java.util.Date(timeresin.getTime()) : null;
                Timestamp timestamp = res.getTimestamp("dateresout");
                java.util.Date dateresout = timestamp != null ? new java.util.Date(timestamp.getTime()) : null;
                Timestamp timein = res.getTimestamp("datein");
                java.util.Date datein = timein != null ? new java.util.Date(timein.getTime()) : null;
                String ac = res.getString("acts");
                String ti = res.getString("tiendas");
                String acom = res.getString("plus");
                String p = res.getString("parcelas");
                reservas.add(new Reserva(id, c, dateresin, dateresout,datein,));
            }
            System.out.println("✓ Se cargaron " + reservas.size() + " reservas");
        } catch (SQLException e) {
            System.err.println("✗ Error consultando actividades: " + e.getMessage());
            throw e;
        }
        return reservas;
    }
    // ==================== ACTIVIDADES ====================

    /**
     * Obtiene todas las actividades de la BD
     */
    public ArrayList<Actividad> getActividades() throws SQLException {
        ArrayList<Actividad> actividades = new ArrayList<>();
        try {
            String sql = "SELECT idact, type, date, max, tit, desc, loc FROM act";
            Statement sent = conexionBD.createStatement();
            ResultSet res = sent.executeQuery(sql);

            while (res.next()) {
                int idact = res.getInt("idact");
                int type = res.getInt("type");
                Timestamp timestamp = res.getTimestamp("date");
                java.util.Date date = timestamp != null ? new java.util.Date(timestamp.getTime()) : null;
                int max = res.getInt("max");
                String tit = res.getString("tit");
                String desc = res.getString("desc");
                String loc = res.getString("loc");
                actividades.add(new Actividad(idact, type, date, max, tit, desc, loc));
            }
            System.out.println("✓ Se cargaron " + actividades.size() + " actividades");
        } catch (SQLException e) {
            System.err.println("✗ Error consultando actividades: " + e.getMessage());
            throw e;
        }
        return actividades;
    }

    /**
     * Obtiene las plantillas de actividades (tipo 3)
     */
    public ArrayList<Actividad> getPlantillasActividades() throws SQLException {
        ArrayList<Actividad> plantillas = new ArrayList<>();
        try {
            String sql = "SELECT idact, type, date, max, tit, desc, loc FROM act WHERE type = 3";
            Statement sent = conexionBD.createStatement();
            ResultSet res = sent.executeQuery(sql);

            while (res.next()) {
                int idact = res.getInt("idact");
                int type = res.getInt("type");
                Timestamp timestamp = res.getTimestamp("date");
                java.util.Date date = timestamp != null ? new java.util.Date(timestamp.getTime()) : null;
                int max = res.getInt("max");
                String tit = res.getString("tit");
                String desc = res.getString("desc");
                String loc = res.getString("loc");
                plantillas.add(new Actividad(idact, type, date, max, tit, desc, loc));
            }
            System.out.println("✓ Se cargaron " + plantillas.size() + " plantillas");
        } catch (SQLException e) {
            System.err.println("✗ Error consultando plantillas: " + e.getMessage());
            throw e;
        }
        return plantillas;
    }
    // ============= STAFF ==================
    /**
     * Devuelve un Staff
     * @param user
     * @param pass
     * @return 
     */
    public Staff getStaff(String user, String pass) {
        Staff c = null;
        try {
            String sql = "SELECT ID, name, surn1, surn2, DNI, user,"
                    + "ROLE, pass FROM persona WHERE user = ? AND pass = ? AND role = 2";
            PreparedStatement ps = conexionBD.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet resultados = ps.executeQuery();

            if (resultados.next()) {
                int id = resultados.getInt("id");
                String nombre = resultados.getString("name");
                String apellido1 = resultados.getString("surn1");
                String apellido2 = resultados.getString("surn2");
                String dni = resultados.getString("DNI");
                String nombreCompleto = nombre + " " + apellido1 + (apellido2 != null ? " " + apellido2 : "");

                c = new Staff(nombreCompleto, dni, id , user, pass);
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al obtener staff: " + e.getMessage());
        }
        return c;
    }
    /**
     * getAllStaff
     */
    public ArrayList<Staff> getStaffAll(){
        ArrayList<Staff> res = new ArrayList();
        Staff s = null;
        try {
            String sql = "SELECT * FROM persona WHERE role = 2";
            PreparedStatement ps = conexionBD.prepareStatement(sql);
            ResultSet resultados = ps.executeQuery();
            if (resultados.next()) {
                int id = resultados.getInt("id");
                String nombre = resultados.getString("name");
                String apellido1 = resultados.getString("surn1");
                String apellido2 = resultados.getString("surn2");
                String dni = resultados.getString("DNI");
                String user = resultados.getString("user");
                String pass = resultados.getString("pass");
                String nombreCompleto = nombre + " " + apellido1 + (apellido2 != null ? " " + apellido2 : "");

                res.add(new Staff(nombreCompleto, dni, id , user, pass));
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al obtener staff: " + e.getMessage());
        }
        return res;
    }
    // ==================== CIERRE DE CONEXIÓN ====================

    /**
     * Cierra la conexión con la BD
     */
    public void cerrar() {
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


