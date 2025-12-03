
# Proyecto Camping Party - Arquitectura MVC

## Descripción General

**Camping Party** es una aplicación de gestión de reservas para un camping, desarrollada en **Java** siguiendo la **arquitectura Modelo-Vista-Controlador (MVC)**.

La aplicación se conecta a una **base de datos MySQL** y proporciona interfaces para:
- **Clientes**: Realizar reservas, ver actividades, gestionar tiendas
- **Staff**: Gestionar reservas, actividades y participantes

## Arquitectura MVC

### 🗄️ MODELO (`com.campingparty.modelo`)

El **Modelo** se encarga de **TODOS los datos** del sistema:

- **Entidades principales**:
  - `Cliente`: Representa un cliente del camping
  - `Staff`: Representa un miembro del personal
  - `Reserva`: Representa una reserva de cliente
  - `Parcela`: Representa una parcela disponible
  - `Actividad`: Representa una actividad del camping
  - `Tienda`: Representa una tienda de campaña
  - `Acompanyante`: Representa a una persona que acompaña al cliente
  - `Persona`: Clase base para clientes, staff y acompañantes

- **Gestión Central**:
  - `Modelo.java`: Clase principal que gestiona todas las entidades
  - `DAO.java`: Acceso a la base de datos MySQL

**Responsabilidades**:
✅ Gestionar datos (parcelas, actividades, reservas, clientes)
✅ Validar reglas de negocio
✅ Acceder a la base de datos
✅ **NO** contiene lógica de interfaz gráfica
✅ **NO** conoce la existencia de las Vistas

### 🎨 VISTA (`com.campingparty.vista`)

La **Vista** se encarga de **PRESENTAR datos al usuario**:

- **Clases principales**:
  - `VistaBienvenida`: Primera pantalla de la aplicación
  - `VistaClienteLogin`: Login de clientes (con opción de registro)
  - `VistaStaffLogin`: Login de personal del camping
  - `VistaClienteReserva`: Interfaz para crear/editar reservas
  - `IVista`: Interfaz que define los métodos comunes

**Responsabilidades**:
✅ Mostrar datos al usuario (componentes Swing de NetBeans)
✅ Captar eventos del usuario (clics, escritura en campos)
✅ Llamar al Controlador cuando el usuario realiza acciones
✅ Mostrar mensajes y errores
✅ **NO** contiene lógica de negocio
✅ **NO** accede directamente al Modelo

### 🔄 CONTROLADOR (`com.campingparty.controlador`)

El **Controlador** es el **MEDIADOR** entre Modelo y Vista:

- **Clases principales**:
  - `ControladorPrincipal`: Controla toda la aplicación (Singleton)
  - `ControladorReserva`: Gestiona la lógica de reservas
  - `ControladorClienteLogin`: Gestiona la autenticación de clientes
  - `ControladorStaffLogin`: Gestiona la autenticación de staff

**Responsabilidades**:
✅ Recibir eventos de la Vista
✅ Validar y procesar datos
✅ Solicitar datos al Modelo
✅ Actualizar la Vista con datos del Modelo
✅ Implementar la lógica de flujo de la aplicación

## Flujo de Datos

```
     Usuario
       |
       v
    [VISTA]  ← Muestra datos, captura eventos
       |
       | (evento del usuario)
       |
       v
[CONTROLADOR]  ← Procesa la solicitud, valida
       |
       | (solicita/actualiza datos)
       |
       v
   [MODELO]   ← Gestiona datos, accede a BD
       |
       | (retorna datos)
       |
       v
[CONTROLADOR]  ← Procesa respuesta
       |
       | (actualiza vista)
       |
       v
    [VISTA]   ← Muestra nuevos datos
       |
       v
     Usuario
```

## Estructura de Directorios

```
src/main/java/com/campingparty/
├── main/
│   └── ProyectoCampingParty.java      ← Punto de entrada (main)
├── modelo/
│   ├── Persona.java                    ← Clase base
│   ├── Cliente.java
│   ├── Staff.java
│   ├── Acompanyante.java
│   ├── Reserva.java
│   ├── Parcela.java
│   ├── Tienda.java
│   ├── Actividad.java
│   ├── Modelo.java                     ← Gestor central
│   └── DAO.java                        ← Acceso a BD
├── vista/
│   ├── IVista.java                     ← Interfaz común
│   ├── VistaBienvenida.java
│   ├── VistaClienteLogin.java
│   ├── VistaStaffLogin.java
│   └── VistaClienteReserva.java
├── controlador/
│   ├── ControladorPrincipal.java       ← Controlador central
│   ├── ControladorReserva.java
│   ├── ControladorClienteLogin.java
│   └── ControladorStaffLogin.java
└── utilidades/
    └── (futuras utilidades)
```

## Cómo Ejecutar

1. **Compilar**:
   ```bash
   mvn clean compile
   ```

2. **Ejecutar**:
   ```bash
   mvn exec:java -Dexec.mainClass="com.campingparty.main.ProyectoCampingParty"
   ```

## Configuración de Base de Datos

La aplicación se conecta a **MySQL** con las siguientes credenciales:
- **Host**: localhost
- **Usuario**: root
- **Contraseña**: root
- **Base de datos**: cbd

**Modificar en**: `com.campingparty.modelo.DAO.java`

## Características Principales

### Para Clientes:
✅ Iniciar sesión o registrarse
✅ Crear nuevas reservas
✅ Seleccionar parcelas
✅ Añadir acompañantes
✅ Contratar tiendas
✅ Ver precio total
✅ Confirmar reserva

### Para Staff:
✅ Iniciar sesión
✅ Gestionar reservas
✅ Ver actividades
✅ Gestionar participantes

## Notas Técnicas

- **Vistas**: Diseñadas originalmente en **NetBeans Designer** y migradas a la estructura MVC
- **Look and Feel**: **FlatLightLaf** para una interfaz moderna
- **Patrón de Diseño**: **Singleton** para el Controlador Principal
- **Base de Datos**: **JDBC** con **MySQL**

## Ventajas de la Arquitectura MVC

✨ **Separación de responsabilidades**: Cada capa tiene un propósito específico
✨ **Reutilización**: El Modelo puede usarse con diferentes Vistas
✨ **Mantenibilidad**: Cambios en una capa no afectan a las otras
✨ **Testabilidad**: Cada componente se puede testear independientemente
✨ **Escalabilidad**: Fácil agregar nuevas funcionalidades

## Autores

👤 Carla Terol

## Licencia

Este proyecto es educativo.

---

**Última actualización**: Diciembre 2025
