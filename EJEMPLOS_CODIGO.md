# EJEMPLOS DE USO - ARQUITECTURA MVC

Este documento contiene ejemplos prácticos de cómo usar la arquitectura MVC en diferentes escenarios.

---

## Ejemplo 1: Autenticación de Cliente

### Vista: VistaClienteLogin
```java
public class VistaClienteLogin extends JFrame implements IVista {
    private ControladorPrincipal controlador;
    private JTextField campoUsuario;
    private JPasswordField campoPass;
    
    private void botonEntrarActionPerformed(ActionEvent evt) {
        // 1. CAPTAR DATOS DEL FORMULARIO
        String usuario = campoUsuario.getText().trim();
        String contrasena = new String(campoPass.getPassword());
        
        // 2. DELEGAR AL CONTROLADOR (no hacer lógica aquí)
        controlador.autenticarCliente(usuario, contrasena);
    }
}
```

### Controlador: ControladorPrincipal
```java
public class ControladorPrincipal {
    public void autenticarCliente(String usuario, String contrasena) {
        // 1. VALIDAR ENTRADA
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            vistaClienteLogin.mostrarError("Campos requeridos");
            return;
        }
        
        // 2. SOLICITAR AL MODELO
        Cliente cliente = modelo.autenticarCliente(usuario, contrasena);
        
        // 3. MANEJAR RESULTADO
        if (cliente != null) {
            // Éxito
            abrirVistaPrincipalCliente(cliente);
            vistaClienteLogin.ocultar();
        } else {
            // Error
            vistaClienteLogin.mostrarError("Usuario o contraseña incorrectos");
        }
    }
}
```

### Modelo: Modelo.java
```java
public class Modelo {
    public Cliente autenticarCliente(String usuario, String contrasena) {
        // Delegar al DAO para acceso a BD
        return dao.getCliente(usuario, contrasena);
    }
}
```

### DAO: DAO.java
```java
public class DAO {
    public Cliente getCliente(String user, String pass) {
        try {
            String sql = "SELECT * FROM persona WHERE user = ? AND pass = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                // Crear objeto Cliente con datos de BD
                return new Cliente(...);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }
}
```

---

## Ejemplo 2: Crear una Reserva

### Vista: VistaClienteReserva
```java
public class VistaClienteReserva extends JFrame implements IVista {
    private ControladorReserva controlador;
    private Cliente clienteActual;
    private Date fechaIn, fechaOut;
    private List<Parcela> parcelasSeleccionadas;
    
    // Usuario selecciona fechas
    private void jDateChooserInPropertyChange(PropertyChangeEvent evt) {
        fechaIn = jDateChooserIn.getDate();
        
        // Delegar al controlador para obtener parcelas disponibles
        Parcela[] disponibles = controlador.getParcelasDisponibles(fechaIn, fechaOut);
        actualizarTablaParcelasDisponibles(disponibles);
    }
    
    // Usuario selecciona parcelas
    private void botonSeleccionarParcelaActionPerformed(ActionEvent evt) {
        Parcela p = (Parcela) tablaParcelas.getSelectedObject();
        parcelasSeleccionadas.add(p);
        actualizarCarrito();
    }
    
    // Usuario confirma reserva
    private void botonConfirmarActionPerformed(ActionEvent evt) {
        // Delegar al controlador
        boolean exito = controlador.confirmarReserva(
            clienteActual,
            fechaIn,
            fechaOut,
            parcelasSeleccionadas
        );
        
        if (exito) {
            mostrarMensaje("Reserva confirmada");
            limpiar();
        } else {
            mostrarError("Error al confirmar reserva");
        }
    }
}
```

### Controlador: ControladorReserva
```java
public class ControladorReserva {
    private Modelo modelo;
    
    // Obtener parcelas disponibles
    public Parcela[] getParcelasDisponibles(Date in, Date out) {
        // VALIDAR
        if (!validarFechas(in, out)) {
            return new Parcela[0];
        }
        
        // SOLICITAR AL MODELO
        return modelo.getParcelasDisponibles(in, out).toArray(new Parcela[0]);
    }
    
    // Confirmar reserva
    public boolean confirmarReserva(Cliente cliente, Date in, Date out, 
                                     List<Parcela> parcelas) {
        // VALIDAR
        if (!validarFechas(in, out) || parcelas.isEmpty()) {
            return false;
        }
        
        // CREAR RESERVA EN MODELO
        Reserva r = modelo.crearReserva(cliente, in, out);
        
        // AGREGAR PARCELAS
        for (Parcela p : parcelas) {
            r.addParcela(p);
        }
        
        // CONFIRMAR
        return modelo.confirmarReserva(r);
    }
    
    private boolean validarFechas(Date in, Date out) {
        return in != null && out != null && in.before(out);
    }
}
```

### Modelo: Modelo.java
```java
public class Modelo {
    public ArrayList<Parcela> getParcelasDisponibles(Date in, Date out) {
        ArrayList<Parcela> disponibles = new ArrayList<>();
        
        for (Parcela p : parcelas) {
            if (p.isLibre()) {
                disponibles.add(p);
            }
        }
        
        return disponibles;
    }
    
    public Reserva crearReserva(Cliente c, Date in, Date out) {
        Reserva r = new Reserva(in, out, c);
        reservas.add(r);
        return r;
    }
    
    public boolean confirmarReserva(Reserva r) {
        // Guardar en BD
        return dao.guardarReserva(r);
    }
}
```

---

## Ejemplo 3: Agregar un Acompañante a una Reserva

### Vista: VistaClienteReserva
```java
public class VistaClienteReserva extends JFrame implements IVista {
    private List<Acompanyante> acompanyantesTemporal = new ArrayList<>();
    
    // Usuario hace clic en "Agregar acompañante"
    private void botonAgregarAcompActionPerformed(ActionEvent evt) {
        String nombre = campoNombre.getText();
        int edad = Integer.parseInt(campoEdad.getText());
        
        // Delegar al controlador
        boolean exito = controlador.agregarAcompanyante(nombre, edad);
        
        if (exito) {
            acompanyantesTemporal.add(new Acompanyante(nombre, edad));
            actualizarTablaAcompanyantes();
            limpiarFormulario();
        } else {
            mostrarError("Datos inválidos");
        }
    }
}
```

### Controlador: ControladorReserva
```java
public class ControladorReserva {
    public boolean agregarAcompanyante(String nombre, int edad) {
        // VALIDAR
        if (!Validador.esNombreValido(nombre)) {
            return false;
        }
        if (!Validador.esEdadValida(edad)) {
            return false;
        }
        
        // CREAR Y AGREGAR A RESERVA
        Acompanyante a = new Acompanyante(nombre, edad);
        reservaActual.addAcompanyante(a);
        
        return true;
    }
}
```

### Modelo: Reserva.java
```java
public class Reserva {
    private ArrayList<Acompanyante> acompanyantes;
    
    public void addAcompanyante(Acompanyante a) {
        if (!acompanyantes.contains(a)) {
            acompanyantes.add(a);
        }
    }
}
```

---

## Ejemplo 4: Crear Actividad (Staff)

### Vista: VistaStaffActividad
```java
public class VistaStaffActividad extends JFrame implements IVista {
    private ControladorActividades controlador;
    
    private void botonCrearActionPerformed(ActionEvent evt) {
        String titulo = campoTitulo.getText();
        String descripcion = campoDesc.getText();
        int maxParticipantes = Integer.parseInt(campoMaxPart.getText());
        Date fecha = jDateChooser.getDate();
        
        // Delegar al controlador
        boolean exito = controlador.crearActividad(
            titulo, descripcion, maxParticipantes, fecha
        );
        
        if (exito) {
            mostrarMensaje("Actividad creada");
            limpiar();
        }
    }
}
```

### Controlador: ControladorActividades
```java
public class ControladorActividades {
    private Modelo modelo;
    
    public boolean crearActividad(String titulo, String descripcion,
                                   int maxPart, Date fecha) {
        // VALIDAR
        if (!Validador.esNombreValido(titulo) || maxPart <= 0) {
            return false;
        }
        
        // CREAR EN MODELO
        Actividad a = new Actividad(0, 0, fecha, maxPart, titulo, descripcion, "");
        modelo.addActividad(a);
        
        return true;
    }
}
```

---

## Ejemplo 5: Validación Usando Utilidades

```java
public class ControladorClienteLogin {
    public boolean autenticar(String usuario, String contrasena) {
        // VALIDAR usando Validador
        if (!Validador.esEmailValido(usuario)) {
            vista.mostrarError("Email inválido");
            return false;
        }
        
        if (!Validador.esContraseñaValida(contrasena)) {
            vista.mostrarError("Contraseña muy corta (mínimo 4 caracteres)");
            return false;
        }
        
        // Continuar con autenticación
        return modelo.autenticarCliente(usuario, contrasena) != null;
    }
}
```

```java
public class ControladorReserva {
    public boolean validarReserva(Reserva r) {
        // VALIDAR usando Validador
        if (!Validador.sonFechasValidas(r.getFechaInicio(), r.getFechaFin())) {
            return false;
        }
        
        // FORMATEAR usando Utilidades
        String fechas = Utilidades.formatearFecha(r.getFechaInicio()) +
                        " a " +
                        Utilidades.formatearFecha(r.getFechaFin());
        
        Utilidades.logExito("Reserva válida: " + fechas);
        
        return true;
    }
}
```

---

## Ejemplo 6: Flujo Completo de Autenticación y Reserva

```
╔══════════════════════════════════════════════════════════════════════════════╗
║                    FLUJO COMPLETO DE USUARIO                                ║
╚══════════════════════════════════════════════════════════════════════════════╝

INICIO
  │
  ├─→ VistaBienvenida → Usuario hace clic en "Login Cliente"
  │
  ├─→ VistaClienteLogin
  │    │
  │    ├─→ Usuario ingresa: usuario="juan@email.com", pass="1234"
  │    │
  │    ├─→ Vista llama: ControladorPrincipal.autenticarCliente("juan@...", "1234")
  │    │
  │    ├─→ ControladorPrincipal:
  │    │    ├─ Valida entrada
  │    │    ├─ Llama: Modelo.autenticarCliente(...)
  │    │    ├─ Modelo llama: DAO.getCliente(...) [BD: SELECT...]
  │    │    └─ Retorna Cliente Juan
  │    │
  │    └─→ ControladorPrincipal abre VistaClientePrincipal para Juan
  │
  ├─→ VistaClientePrincipal (Juan logueado)
  │    │
  │    ├─→ Juan hace clic en "Nueva Reserva"
  │    │
  │    └─→ ControladorPrincipal.abrirReserva(Juan)
  │
  ├─→ VistaClienteReserva
  │    │
  │    ├─→ Juan selecciona fechas: 15/12/2025 - 20/12/2025
  │    │
  │    ├─→ Vista llama: ControladorReserva.getParcelasDisponibles(15/12, 20/12)
  │    │
  │    ├─→ ControladorReserva:
  │    │    ├─ Valida fechas
  │    │    └─ Llama: Modelo.getParcelasDisponibles(...)
  │    │    └─ Modelo retorna lista de parcelas libres
  │    │
  │    ├─→ Vista muestra parcelas disponibles: [Parcela 1, 3, 5, 7, ...]
  │    │
  │    ├─→ Juan selecciona: Parcela 1 y Parcela 3
  │    │
  │    ├─→ Juan agrega acompañante: "María" (25 años)
  │    │
  │    ├─→ Vista llama: ControladorReserva.agregarAcompanyante("María", 25)
  │    │
  │    ├─→ ControladorReserva:
  │    │    ├─ Valida nombre y edad
  │    │    ├─ Crea Acompanyante
  │    │    └─ Agrega a Reserva
  │    │
  │    ├─→ Juan hace clic en "Confirmar Reserva"
  │    │
  │    ├─→ Vista llama: ControladorReserva.confirmarReserva(...)
  │    │
  │    ├─→ ControladorReserva:
  │    │    ├─ Valida reserva completa
  │    │    ├─ Llama: Modelo.confirmarReserva(reserva)
  │    │    ├─ Modelo llama: DAO.guardarReserva(...) [BD: INSERT...]
  │    │    └─ Retorna true (éxito)
  │    │
  │    └─→ Vista muestra: "Reserva confirmada"
  │
  └─→ FIN

```

---

## Ejemplo 7: Error Handling

```java
// Controlador
public boolean registrarCliente(String nombre, String dni, int edad, 
                                 String email, String pass) {
    // VALIDAR CON VALIDADOR
    if (!Validador.esRegistroClienteValido(nombre, dni, edad, email, pass)) {
        vista.mostrarError("Datos inválidos");
        return false;
    }
    
    try {
        // CREAR CLIENTE EN MODELO
        Cliente c = new Cliente(nombre, dni, edad);
        c.setUser(email);
        c.setPassword(pass);
        
        boolean exito = modelo.registrarCliente(c);
        
        if (exito) {
            vista.mostrarMensaje("Cliente registrado correctamente");
            Utilidades.logExito("Cliente " + nombre + " registrado");
        } else {
            vista.mostrarError("Error al registrar cliente");
            Utilidades.logError("Error al registrar cliente: " + nombre);
        }
        
        return exito;
        
    } catch (Exception e) {
        vista.mostrarError("Error inesperado: " + e.getMessage());
        Utilidades.logError("Error en registrarCliente: " + e.getMessage());
        return false;
    }
}
```

---

## Resumen de Patrones

| Patrón | Ejemplo |
|--------|---------|
| **Captar evento** | Vista: `private void botonClick(ActionEvent evt)` |
| **Delegar** | Vista: `controlador.procesarDato(dato)` |
| **Validar** | Controlador: `if (!Validador.esValido(dato))` |
| **Llamar modelo** | Controlador: `modelo.operacion(...)` |
| **Acceso BD** | Modelo: `dao.query(...)` |
| **Mostrar resultado** | Controlador: `vista.mostrar...()` |
| **Manejar error** | Vista: `mostrarError(msg)` |

---

¡Estos ejemplos muestran cómo usar la arquitectura MVC en práctica! 🚀
