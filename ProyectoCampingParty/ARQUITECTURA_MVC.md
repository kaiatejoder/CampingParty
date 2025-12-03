# Diagrama de Clases - Proyecto Camping Party (MVC)

## MODELO - Gestión de Datos

```
                    ┌─────────────┐
                    │   Persona   │
                    │  (Abstracta)│
                    └──────┬──────┘
                           │
                ┌──────────┼──────────┐
                │          │          │
           ┌────▼────┐ ┌──▼────┐ ┌──▼──────────┐
           │ Cliente │ │ Staff │ │Acompanyante │
           └─────────┘ └───────┘ └─────────────┘
```

### Entidades Principales

```
┌──────────────────┐
│   Modelo.java    │  ← Gestor Central
│                  │
│ - getParcelas()  │
│ - getActividades()│
│ - getReservas()  │
│ - getClientes()  │
│ - crearReserva() │
└────────┬─────────┘
         │
    ┌────┴─────────────────────────────┐
    │                                  │
    v                                  v
┌────────────┐                    ┌─────────┐
│  DAO.java  │                    │ Entidades│
│            │                    │          │
│ - Conexión │                    │-Parcela  │
│ - Queries  │                    │-Actividad│
│ - Insert   │                    │-Reserva  │
│ - Update   │                    │-Tienda   │
└────────────┘                    └──────────┘
      │
      v
  ┌──────────┐
  │ MySQL BD │
  │   (cbd)  │
  └──────────┘
```

## VISTA - Interfaz Gráfica

```
                    ┌──────────────┐
                    │   IVista     │
                    │  (Interfaz)  │
                    └──────┬───────┘
                           │
            ┌──────────────┼──────────────┐
            │              │              │
     ┌──────▼────────┐ ┌──▼──────────┐ ┌─▼──────────┐
     │    Vista      │ │VistaCliente │ │VistaStaff  │
     │ Bienvenida    │ │  Login      │ │  Login     │
     └───────────────┘ └─────────────┘ └────────────┘
```

### Jerarquía de Vistas

```
                    JFrame
                      │
                      v
            ┌──────────────────┐
            │   IVista         │
            │ (Interfaz común) │
            └────────┬─────────┘
                     │
      ┌──────────────┼──────────────┐
      │              │              │
      v              v              v
┌─────────────┐ ┌──────────────┐ ┌──────────────┐
│ VistaBiene- │ │VistaCliente  │ │VistaStaff    │
│ venida      │ │Login         │ │Login         │
│             │ │              │ │              │
│ "Bienvenido"│ │ Login/Registro│ │ Login        │
└─────────────┘ └──────────────┘ └──────────────┘
```

## CONTROLADOR - Mediador

```
                ┌──────────────────────────┐
                │ ControladorPrincipal     │
                │ (Singleton)              │
                │                          │
                │ - instancia               │
                │ - modelo                 │
                │ - vistas                 │
                │ - controladores          │
                │ - iniciar()              │
                │ - abrirLoginCliente()    │
                │ - abrirLoginStaff()      │
                │ - autenticar*()          │
                └────┬──────────────────┬──┘
                     │                  │
        ┌────────────┘                  └───────────┐
        │                                           │
        v                                           v
┌──────────────────────┐            ┌──────────────────────┐
│ControladorReserva    │            │ControladorLogin     │
│                      │            │                      │
│ - validarFechas()    │            │ - autenticar()       │
│ - validarParcelas()  │            │ - registrar()        │
│ - addParcela()       │            │ - validar()          │
│ - addAcompanyante()  │            │                      │
│ - confirmarReserva() │            └──────────────────────┘
└──────────────────────┘
```

## Flujo de Comunicación MVC

```
┌─────────────┐
│   USUARIO   │
└──────┬──────┘
       │ (evento)
       v
┌─────────────────────────┐
│      VISTA              │
│  (Captura evento)       │
│                         │
│  Usuario hace clic      │
└────────────┬────────────┘
             │
             │ (llamada)
             v
┌─────────────────────────────────┐
│      CONTROLADOR                │
│  (Procesa solicitud)            │
│                                 │
│ 1. Valida datos                 │
│ 2. Solicita al Modelo           │
└────────┬─────────────────────────┘
         │
         │ (solicita/actualiza)
         v
┌─────────────────────────┐
│      MODELO             │
│  (Gestiona datos)       │
│                         │
│  1. Valida reglas       │
│  2. Accede a BD         │
│  3. Retorna datos       │
└────────┬────────────────┘
         │
         │ (retorna)
         v
┌─────────────────────────────────┐
│      CONTROLADOR                │
│  (Procesa respuesta)            │
│                                 │
│ 1. Procesa resultado            │
│ 2. Actualiza Vista              │
└────────┬─────────────────────────┘
         │
         │ (actualiza componentes)
         v
┌─────────────────────────┐
│      VISTA              │
│  (Muestra resultado)    │
│                         │
│  - Actualiza campos     │
│  - Muestra mensaje      │
│  - Cambia pantalla      │
└────────┬────────────────┘
         │
         v
┌─────────────┐
│   USUARIO   │
│ (ve cambios)│
└─────────────┘
```

## Paquetes y Responsabilidades

```
com.campingparty
│
├─ main/
│  └─ ProyectoCampingParty.java
│     └─ Punto de entrada de la aplicación
│
├─ modelo/
│  ├─ Persona.java (clase base)
│  ├─ Cliente.java
│  ├─ Staff.java
│  ├─ Acompanyante.java
│  ├─ Reserva.java
│  ├─ Parcela.java
│  ├─ Tienda.java
│  ├─ Actividad.java
│  ├─ Modelo.java (GESTOR CENTRAL)
│  └─ DAO.java (ACCESO A BD)
│     └─ Responsabilidades: Datos, Lógica de Negocio, BD
│
├─ vista/
│  ├─ IVista.java (interfaz)
│  ├─ VistaBienvenida.java
│  ├─ VistaClienteLogin.java
│  ├─ VistaStaffLogin.java
│  └─ VistaClienteReserva.java
│     └─ Responsabilidades: Mostrar datos, Captar eventos
│
├─ controlador/
│  ├─ ControladorPrincipal.java (SINGLETON)
│  ├─ ControladorReserva.java
│  ├─ ControladorClienteLogin.java
│  └─ ControladorStaffLogin.java
│     └─ Responsabilidades: Procesar, Validar, Coordinar
│
└─ utilidades/
   └─ (futuras clases auxiliares)
```

## Ejemplo de Caso de Uso: "Cliente Inicia Sesión"

```
1. USUARIO INTERACTÚA CON VISTA
   └─> Usuario abre VistaClienteLogin

2. USUARIO INGRESA CREDENCIALES
   └─> Usuario escribe usuario y contraseña

3. USUARIO HACE CLIC EN "INICIAR SESIÓN"
   └─> Vista captura evento del botón

4. VISTA LLAMA AL CONTROLADOR
   └─> VistaClienteLogin.iniciarSesion()
       └─> ControladorPrincipal.autenticarCliente(usuario, pass)

5. CONTROLADOR PROCESA
   └─> Valida que campos no estén vacíos
   └─> Llama al Modelo

6. MODELO ACCEDE A BASE DE DATOS
   └─> Modelo.autenticarCliente()
       └─> DAO.getCliente(usuario, contraseña)
           └─> Ejecuta query SELECT en MySQL

7. MODELO RETORNA RESULTADO
   └─> Si existe: Cliente object
   └─> Si no existe: null

8. CONTROLADOR RECIBE RESPUESTA
   └─> Si Cliente != null:
       ├─> Muestra vista principal del cliente
       └─> Cierra login
   └─> Si Cliente == null:
       └─> Llama a Vista para mostrar error

9. VISTA MUESTRA RESULTADO
   └─> Actualiza pantalla o muestra mensaje de error

10. USUARIO VE RESULTADO
    └─> Acceso concedido o denegado
```

---

**Esta arquitectura asegura que cada componente tenga una responsabilidad clara y separada,
facilitando el mantenimiento, pruebas y expansión del proyecto.**
