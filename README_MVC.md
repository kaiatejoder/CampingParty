# Arquitectura MVC - Camping Party

## Visión General

El proyecto **Camping Party** sigue una arquitectura **Modelo-Vista-Controlador (MVC)** clara y bien estructurada.

```
┌─────────────────────────────────────────────────┐
│          USUARIO INTERACTÚA CON VISTA            │
└──────────────────┬──────────────────────────────┘
                   │
        ┌──────────▼──────────┐
        │    VISTA (GUI)      │ ← Solo interfaz gráfica
        │  - Muestra datos    │ ← Sin lógica de negocio
        │  - Captar eventos   │
        └──────────┬──────────┘
                   │ Envía eventos
        ┌──────────▼──────────┐
        │    CONTROLADOR      │ ← Orquesta la lógica
        │  - Valida entrada   │ ← Comunica Vista-Modelo
        │  - Llama al Modelo  │
        └──────────┬──────────┘
                   │ Solicita/actualiza datos
        ┌──────────▼──────────┐
        │     MODELO          │ ← Gestiona datos y negocio
        │  - Parcelas         │ ← Accede a BD a través de DAO
        │  - Reservas         │ ← Sin conocimiento de GUI
        │  - Clientes         │
        └─────────────────────┘
```

---

## Estructura de Carpetas

```
src/main/java/com/campingparty/
├── modelo/                      # Datos y lógica de negocio
│   ├── Persona.java            # Clase base
│   ├── Cliente.java            # Gestiona clientes
│   ├── Staff.java              # Personal del camping
│   ├── Acompanyante.java       # Acompañantes en reservas
│   ├── Parcela.java            # Parcelas del camping
│   ├── Tienda.java             # Tiendas de campaña
│   ├── Actividad.java          # Actividades
│   ├── Reserva.java            # Reservas
│   ├── Modelo.java             # Orquesta toda la lógica
│   └── DAO.java                # Acceso a Base de Datos
│
├── vista/                       # Interfaces gráficas
│   ├── IVista.java             # Interfaz que deben cumplir todas las vistas
│   ├── VistaBienvenida.java    # Primera pantalla
│   ├── VistaClienteLogin.java  # Login de clientes
│   ├── VistaStaffLogin.java    # Login de personal
│   ├── VistaClienteReserva.java # Crear reservas
│   └── [Otras vistas de NetBeans]
│
├── controlador/                 # Lógica de aplicación
│   ├── ControladorPrincipal.java   # Orquesta toda la app
│   ├── ControladorClienteLogin.java # Login de clientes
│   ├── ControladorStaffLogin.java   # Login de staff
│   ├── ControladorReserva.java      # Gestión de reservas
│   └── [Otros controladores]
│
├── utilidades/                  # Clases de apoyo
│   ├── Validador.java          # Validación de datos
│   └── Utilidades.java         # Métodos útiles generales
│
└── main/
    └── ProyectoCampingParty.java # Punto de entrada
```

---

## Responsabilidades de Cada Capa

### 1. MODELO (Datos y Lógica de Negocio)

**Responsabilidades:**
- Gestionar datos de parcelas, clientes, reservas, actividades
- Implementar reglas de negocio
- Interactuar con la base de datos (a través de DAO)
- NO CONTIENE código de interfaz gráfica

**Ejemplo de uso:**
```java
Modelo modelo = new Modelo();

// Crear una reserva
Reserva reserva = modelo.crearReserva(cliente, fechaIn, fechaOut);
reserva.addParcela(parcela1);
reserva.addAcompanyante(acompanyante1);

// Confirmar
boolean exito = modelo.confirmarReserva(reserva);
```

**Clases principales:**
- `Modelo`: Gestor central
- `DAO`: Acceso a BD
- Entidades: `Cliente`, `Reserva`, `Parcela`, etc.

---

### 2. VISTA (Interfaz Gráfica)

**Responsabilidades:**
- Mostrar datos al usuario
- Captar eventos del usuario (clicks, escritura, etc.)
- Llamar al controlador con los datos
- NO CONTIENE lógica de negocio

**Características:**
- Todas las vistas implementan `IVista`
- Heredan de `JFrame` o `JPanel`
- Compatible con NetBeans Designer (archivos `.form`)
- Comunican únicamente con el controlador

**Ejemplo de estructura:**
```java
public class VistaCliente extends JFrame implements IVista {
    private ControladorPrincipal controlador;
    
    public VistaCliente(ControladorPrincipal controlador) {
        this.controlador = controlador;
        initComponents(); // Generado por NetBeans Designer
    }
    
    private void onBotonClick() {
        // Captar dato de GUI
        String nombre = campoNombre.getText();
        
        // DELEGAR al controlador (no hacer lógica aquí)
        controlador.procesarDato(nombre);
    }
    
    @Override
    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
```

---

### 3. CONTROLADOR (Orquestación y Lógica de Flujo)

**Responsabilidades:**
- Recibir eventos de la vista
- Validar entrada del usuario
- Solicitar datos al modelo
- Actualizar la vista con resultados
- Gestionar el flujo de la aplicación

**Características:**
- NO contiene código de GUI
- NO contiene lógica de datos complejos
- Actúa como "intermediario" entre Vista y Modelo
- Implementa validaciones específicas del dominio

**Ejemplo de controlador:**
```java
public class ControladorReserva {
    private Modelo modelo;
    
    public ControladorReserva(Modelo modelo) {
        this.modelo = modelo;
    }
    
    public void crearReserva(Cliente cliente, Date in, Date out) {
        // Validar
        if (!validarFechas(in, out)) {
            // Informar error a vista
            return;
        }
        
        // Llamar al modelo
        Reserva r = modelo.crearReserva(cliente, in, out);
        
        // Retornar resultado a la vista
        return r;
    }
}
```

---

## Flujo de Comunicación

### Ejemplo: Autenticación de Cliente

```
1. USUARIO hace click en botón "Entrar"
   ↓
2. VISTA captura el evento en onEntrarClick()
   ↓
3. VISTA obtiene usuario/contraseña del formulario
   ↓
4. VISTA llama: controlador.autenticarCliente(usuario, pass)
   ↓
5. CONTROLADOR recibe los datos
   - Valida que no estén vacíos
   - Llama: modelo.autenticarCliente(usuario, pass)
   ↓
6. MODELO busca en DAO:
   - DAO.getCliente(usuario, pass)
   - BD retorna datos de cliente
   ↓
7. MODELO retorna Cliente al CONTROLADOR
   ↓
8. CONTROLADOR retorna al CONTROLADOR PRINCIPAL
   ↓
9. CONTROLADOR PRINCIPAL abre nueva vista (VistaClientePrincipal)
   ↓
10. USUARIO ve la interfaz de cliente
```

---

## Cómo Agregar una Nueva Funcionalidad

### Paso 1: Crear/modificar Modelo

```java
// En Modelo.java, agregar método:
public List<Actividad> getActividadesPorFecha(Date fecha) {
    // Lógica de búsqueda
}
```

### Paso 2: Crear Controlador

```java
public class ControladorActividades {
    private Modelo modelo;
    
    public ControladorActividades(Modelo modelo) {
        this.modelo = modelo;
    }
    
    public List<Actividad> obtenerActividades(Date fecha) {
        // Validar fecha
        if (fecha == null || fecha.before(new Date())) {
            return null;
        }
        // Llamar modelo
        return modelo.getActividadesPorFecha(fecha);
    }
}
```

### Paso 3: Crear Vista

```java
public class VistaActividades extends JFrame implements IVista {
    private ControladorActividades controlador;
    
    public VistaActividades(ControladorPrincipal controlador) {
        this.controlador = new ControladorActividades(controlador.getModelo());
        initComponents();
    }
    
    private void mostrarActividades() {
        Date fecha = jDateChooser.getDate();
        List<Actividad> actividades = controlador.obtenerActividades(fecha);
        
        if (actividades != null) {
            actualizarTabla(actividades);
        }
    }
}
```

---

## Base de Datos

La aplicación se conecta a **MySQL** a través de la clase **DAO**:

```java
// Credenciales en DAO.java:
BD: cbd
Usuario: root
Contraseña: root
Puerto: 3306

// Tablas principales:
- persona (clientes, staff)
- reservas
- parcelas
- actividades
```

---

## Utilidades

### Validador.java
```java
// Validaciones comunes
Validador.esEmailValido(email);
Validador.esDNIValido(dni);
Validador.esContraseñaValida(pass);
Validador.sonFechasValidas(in, out);
```

### Utilidades.java
```java
// Formateo y conversiones
Utilidades.formatearFecha(date);
Utilidades.formatearPrecio(123.45); // "123.45€"
Utilidades.calcularDias(in, out);
```

---

## Integración con NetBeans Designer

Si deseas usar NetBeans Designer para las vistas:

1. Abre NetBeans
2. Crea un nuevo `JFrame Form`
3. Asegúrate de que esté en `com.campingparty.vista`
4. Implementa `IVista` en la clase generada
5. En el constructor, inyecta el controlador
6. En los event handlers, delega al controlador

**Ejemplo:**
```java
// Generado por NetBeans
public class VistaCliente extends javax.swing.JFrame {
    // ... código generado ...
    
    private ControladorCliente controlador; // AGREGAR ESTO
    
    public VistaCliente(ControladorPrincipal controlador) {
        this.controlador = new ControladorCliente(controlador.getModelo());
        initComponents();
    }
    
    private void bottonClickActionPerformed(ActionEvent evt) {
        // DELEGAR al controlador
        controlador.procesarDato(campoTexto.getText());
    }
}
```

---

## Patrones Utilizados

1. **MVC**: Separación clara de responsabilidades
2. **Singleton**: `ControladorPrincipal` es singleton
3. **DAO**: Abstracción de acceso a datos
4. **Inyección de Dependencias**: Controllers reciben Modelo en constructor
5. **Observer** (implícito): Eventos de GUI disparan acciones en Controlador

---

## Mejores Prácticas

### HACER ✅
- Toda lógica de negocio en **Modelo**
- Toda interfaz gráfica en **Vista**
- Toda comunicación Vista-Modelo en **Controlador**
- Validar entrada en **Controlador**
- Usar **DAO** para acceso a BD
- Implementar **IVista** en todas las vistas

### NO HACER ❌
- Lógica de negocio en vistas
- Acceso directo a BD desde vista o controlador
- Vista accediendo directamente al Modelo
- Queries SQL en la vista
- Componentes GUI en el Modelo

---

## Ejecución

```bash
# Compilar
mvn clean compile

# Ejecutar
mvn exec:java

# O ejecutar directamente desde IDE:
Run → ProyectoCampingParty.java
```

---

## Conclusión

Esta arquitectura **MVC** proporciona:
- **Separación clara** de responsabilidades
- **Fácil mantenimiento** y testing
- **Reutilización** de código
- **Escalabilidad** para agregar nuevas funcionalidades
- **Integración** con herramientas como NetBeans Designer

¡Cualquier duda, consulta el código de ejemplo en los controladores y vistas!
