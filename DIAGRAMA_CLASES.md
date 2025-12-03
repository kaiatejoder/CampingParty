# DIAGRAMA DE CLASES Y RELACIONES

## Diagrama UML Simplificado

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                            CAPA DE MODELO                                   │
└─────────────────────────────────────────────────────────────────────────────┘

                                  Modelo
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
                  DAO          Parcelas         Clientes
                  │               │               │
            ┌─────┴─────┐    ┌────┴────┐   ┌────┴────┐
            │           │    │         │   │         │
         Insert    Select  Parcela  Staff  Cliente   Acompanyante
         Update    Update
         Delete


┌─────────────────────────────────────────────────────────────────────────────┐
│                          CAPA DE CONTROLADOR                                │
└─────────────────────────────────────────────────────────────────────────────┘

                         ControladorPrincipal
                                │
                ┌───────────────┼───────────────┐
                │               │               │
       ControladorClienteLogin  ControladorStaffLogin   ControladorReserva
                │                      │                    │
           autenticar()            autenticar()        confirmar()
           registrar()             validar()           agregarParcela()
           validar()                                   agregarAcompanyante()


┌─────────────────────────────────────────────────────────────────────────────┐
│                            CAPA DE VISTA                                    │
└─────────────────────────────────────────────────────────────────────────────┘

                          IVista (Interface)
                                │
                ┌───────────────┼───────────────┐
                │               │               │
        VistaBienvenida   VistaClienteLogin   VistaStaffLogin
                │                 │               │
                │                 │               │
        VistaClienteReserva   VistaStaffMain   [Otras vistas]
```

---

## Relaciones de Clases (Modelo)

### Jerarquía de Persona

```
        ┌──────────────┐
        │   Persona    │
        ├──────────────┤
        │ - user       │
        │ - pass       │
        │ - nombre     │
        │ - dni        │
        │ - tlf        │
        │ - role       │
        └──────┬───────┘
               │
       ┌───────┼───────┐
       │       │       │
    Cliente  Staff  Acompanyante
    │         │         │
    ├─        ├─        ├─
    │ edad    │ init()  │ edad
    │ tiendas │ autent()│
    │ reservas│         │
    └─        └─        └─
```

### Relaciones Principales

```
Cliente
  ├─ 1..* Reserva
  │         ├─ 1..* Parcela
  │         ├─ 0..* Tienda
  │         └─ 0..* Acompanyante
  │
  ├─ 0..* Tienda
  │
  └─ 0..* Actividad (participación)


Actividad
  ├─ 1..* Cliente (participantes)
  └─ 1 Cliente (ganador, opcional)


Parcela
  ├─ 1 Camping
  └─ 0..1 Reserva
```

---

## Diagrama de Secuencia: Autenticación de Cliente

```
Actor      Vista              Controlador        Modelo          DAO          BD
│          │                      │                 │             │           │
│─login──→ │                      │                 │             │           │
│          │─autenticar()─────────→                │             │           │
│          │                      │─autenticar()───→             │           │
│          │                      │                 │─getCliente()→          │
│          │                      │                 │             │─SELECT───→│
│          │                      │                 │             │←─ row ───│
│          │                      │                 │←─Cliente ───│           │
│          │                      │←─ Cliente ──────│             │           │
│          │←─ mostrarMensaje ────│                 │             │           │
│          │                      │                 │             │           │
│←─vista ──│                      │                 │             │           │
```

---

## Diagrama de Secuencia: Crear Reserva

```
Usuario    Vista            Controlador         Modelo         DAO          BD
│          │                    │                 │             │           │
│─fechas──→│                    │                 │             │           │
│          │─getFechasDisp()───→│                 │             │           │
│          │                    │─getParcelas()──→│             │           │
│          │                    │                 │─query()─────→          │
│          │                    │                 │             │─SELECT───→│
│          │                    │                 │             │←─list ───│
│          │                    │                 │←─lista ─────│           │
│          │                    │←─lista ────────│             │           │
│          │←─mostrar parcelas ─│                 │             │           │
│          │                    │                 │             │           │
│─parcelas→│                    │                 │             │           │
│─acomps──→│                    │                 │             │           │
│─confirmar→─confirmar()────────→                │             │           │
│          │                    │─crear()────────→│             │           │
│          │                    │                 │─insert()────→          │
│          │                    │                 │             │─INSERT───→│
│          │                    │                 │             │←─OK ─────│
│          │                    │                 │←─OK ────────│           │
│          │                    │←─true ─────────│             │           │
│          │←─éxito ────────────│                 │             │           │
│←─confirmación─               │                 │             │           │
```

---

## Diagrama de Paquetes

```
com.campingparty
│
├── modelo/                          [Datos]
│   ├── Persona.java
│   ├── Cliente.java
│   ├── Staff.java
│   ├── Acompanyante.java
│   ├── Parcela.java
│   ├── Tienda.java
│   ├── Actividad.java
│   ├── Reserva.java
│   ├── Modelo.java
│   └── DAO.java
│
├── vista/                           [Interfaz Gráfica]
│   ├── IVista.java
│   ├── VistaBienvenida.java
│   ├── VistaClienteLogin.java
│   ├── VistaStaffLogin.java
│   ├── VistaClienteReserva.java
│   └── [Otras vistas]
│
├── controlador/                     [Lógica de Aplicación]
│   ├── ControladorPrincipal.java
│   ├── ControladorClienteLogin.java
│   ├── ControladorStaffLogin.java
│   └── ControladorReserva.java
│
├── utilidades/                      [Clases de Apoyo]
│   ├── Validador.java
│   └── Utilidades.java
│
└── main/                            [Punto de Entrada]
    └── ProyectoCampingParty.java
```

---

## Tabla de Relaciones Clase-a-Clase

### Modelo contiene

| Contenedor | Contiene | Multiplicidad | Ejemplo |
|-----------|----------|---------------|---------|
| `Modelo` | `ArrayList<Parcela>` | 1..* | 16 parcelas |
| `Modelo` | `ArrayList<Reserva>` | 0..* | Varias reservas |
| `Modelo` | `ArrayList<Actividad>` | 0..* | Varias actividades |
| `Modelo` | `DAO` | 1 | Una conexión |

### Cliente contiene

| Contenedor | Contiene | Multiplicidad | Ejemplo |
|-----------|----------|---------------|---------|
| `Cliente` | `ArrayList<Reserva>` | 0..* | Cliente sin reservas o con varias |
| `Cliente` | `ArrayList<Tienda>` | 0..* | Tiendas del cliente |

### Reserva contiene

| Contenedor | Contiene | Multiplicidad | Ejemplo |
|-----------|----------|---------------|---------|
| `Reserva` | `ArrayList<Parcela>` | 1..* | Al menos 1 parcela |
| `Reserva` | `ArrayList<Tienda>` | 0..* | Con o sin tiendas |
| `Reserva` | `ArrayList<Acompanyante>` | 0..* | Con o sin acompañantes |
| `Reserva` | `Cliente` | 1 | Un cliente por reserva |

### Actividad contiene

| Contenedor | Contiene | Multiplicidad | Ejemplo |
|-----------|----------|---------------|---------|
| `Actividad` | `ArrayList<Cliente>` | 0..* | Participantes |
| `Actividad` | `Cliente` (ganador) | 0..1 | Un ganador opcional |

---

## Estado de los Objetos

### Estados de Parcela

```
           ┌─────────────┐
           │   LIBRE     │
           └──────┬──────┘
                  │ reservarParcela()
           ┌──────▼──────────┐
           │   RESERVADA     │
           └──────┬──────────┘
                  │ ocuparParcela()
           ┌──────▼──────────┐
           │    OCUPADA      │
           └──────┬──────────┘
                  │ liberarParcela()
           ┌──────▼──────┐
           │   LIBRE     │
           └─────────────┘
```

### Estados de Reserva

```
           ┌────────────────────┐
           │   CREADA/PENDIENTE │
           └──────────┬─────────┘
                      │ addParcela(), addAcompanyante()
           ┌──────────▼──────────┐
           │    CONFIRMADA       │
           └──────────┬──────────┘
                      │ cancelarReserva()
           ┌──────────▼──────────┐
           │     CANCELADA       │
           └─────────────────────┘
```

---

## Flujo de Datos

### De la Vista al Modelo

```
Usuario
  │ Evento (click, texto)
  ▼
Vista
  │ Captura datos, llama controlador
  ▼
Controlador
  │ Valida entrada, prepara datos
  ▼
Modelo
  │ Procesa lógica de negocio
  ▼
DAO
  │ Ejecuta operación en BD
  ▼
Base de Datos MySQL
  │ Almacena/retorna datos
  ▼
```

### De la Respuesta al Usuario

```
Base de Datos MySQL
  │ Retorna datos
  ▼
DAO
  │ Convierte ResultSet a objetos
  ▼
Modelo
  │ Actualiza estado interno
  ▼
Controlador
  │ Prepara respuesta para vista
  ▼
Vista
  │ Muestra datos/mensajes
  ▼
Usuario
```

---

## Matriz de Responsabilidades

| Responsabilidad | Modelo | Vista | Controlador |
|-----------------|--------|-------|-------------|
| GUI | ❌ | ✅ | ❌ |
| Datos | ✅ | ❌ | ❌ |
| BD | ✅ (vía DAO) | ❌ | ❌ |
| Validación | ❌ | ❌ | ✅ |
| Lógica negocio | ✅ | ❌ | ❌ |
| Flujo aplicación | ❌ | ❌ | ✅ |
| Eventos usuario | ❌ | ✅ | ❌ |
| Llamadas entre capas | ❌ | ❌ | ✅ |

---

## Dependencias Entre Clases

```
Vista
  └─ depende de → Controlador
                    │
                    ├─ depende de → Modelo
                    │                 │
                    │                 ├─ depende de → DAO
                    │                 │                │
                    │                 │                └─ accede → MySQL
                    │                 │
                    │                 └─ contiene → [Cliente, Reserva, Parcela...]
                    │
                    └─ depende de → Validador (en utilidades)

Modelo
  └─ depende de → [Entidades Cliente, Reserva, Parcela...]
                    └─ depende de → DAO
```

---

## Ciclo de Vida de Objetos Principales

### Cliente

```
1. Creación: Cliente c = new Cliente(nombre, dni, edad)
2. Inicialización: c.setUser(email), c.setPassword(pass)
3. Persistencia: modelo.registrarCliente(c) → DAO.agregarCliente(c) → BD
4. Recuperación: modelo.autenticarCliente(user, pass) → DAO.getCliente()
5. Uso: c.addReserva(r), c.getTiendas()
6. Fin: Logout/sesión termina
```

### Reserva

```
1. Creación: Reserva r = new Reserva(fechaIn, fechaOut, cliente)
2. Construcción: r.addParcela(p), r.addAcompanyante(a), r.addTienda(t)
3. Validación: controlador.validarReserva(r)
4. Persistencia: modelo.confirmarReserva(r) → DAO.guardarReserva(r)
5. Recuperación: modelo.getReserva(id) → DAO.getReserva()
6. Actualización: r.removeParcela(p)
7. Fin: modelo.removeReserva(r) → liberarParcelas()
```

### Actividad

```
1. Creación: Actividad a = new Actividad(id, tipo, fecha, maxPart, titulo, desc)
2. Participantes: a.agregarParticipante(cliente)
3. Ganador: a.setGanador(cliente)
4. Persistencia: modelo.addActividad(a)
5. Consulta: modelo.getActividades()
6. Fin: a.eliminarParticipante(cliente)
```

---

## Resumen

Esta estructura de clases sigue el patrón MVC:
- **Modelo**: Totalmente independiente de la presentación
- **Vista**: Solo presenta datos, capta eventos
- **Controlador**: Coordina la comunicación

La separación clara permite:
- ✅ Testing unitario del Modelo
- ✅ Reutilización de Controlador con diferentes Vistas
- ✅ Cambios en BD sin afectar Vista
- ✅ Cambios en GUI sin afectar Modelo

---
