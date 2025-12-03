# Guía de Migración: Vistas de NetBeans a Arquitectura MVC

## Introducción

Este documento explica cómo integrar las vistas existentes creadas con NetBeans Designer en la nueva arquitectura MVC del proyecto Camping Party.

## Situación Actual

Las vistas antiguas están en carpetas como:
- `VISTA/`
- `CONTROLADOR/`
- `MODELO/`

Y usan paquetes como `VISTA`, `CONTROLADOR`, `MODELO` (en mayúsculas).

## Objetivo

Migrar a:
- `com.campingparty.vista`
- `com.campingparty.controlador`
- `com.campingparty.modelo`

---

## Paso 1: Entender la Estructura Actual de una Vista NetBeans

Una vista típica generada por NetBeans tiene esta estructura:

```java
public class VistaClienteReserva extends javax.swing.JFrame {
    
    // Componentes generados por NetBeans
    private javax.swing.JButton botonAceptar;
    private javax.swing.JTextField campoNombre;
    // ... más componentes ...
    
    public VistaClienteReserva() {
        initComponents(); // Generado por NetBeans
    }
    
    private void initComponents() {
        // TODO Todos estos eventos
    }
    
    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    
    public static void main(String args[]) {
        // TODO for Application support
    }
}
```

---

## Paso 2: Refactorizar una Vista Existente

### ANTES (Arquitectura antigua)
```java
package VISTA;

public class VistaClienteReserva extends javax.swing.JFrame {
    private Modelo modelo;
    
    public VistaClienteReserva(Modelo m) {
        this.modelo = m;
        initComponents();
    }
    
    private void botonConfirmarActionPerformed(ActionEvent evt) {
        // PROBLEMA: Lógica de negocio aquí
        String nombre = campoNombre.getText();
        Reserva r = new Reserva(nombre);
        modelo.guardarReserva(r);
        JOptionPane.showMessageDialog(this, "Guardado");
    }
}
```

### DESPUÉS (Nueva arquitectura MVC)

```java
package com.campingparty.vista;

import com.campingparty.controlador.ControladorReserva;
import com.campingparty.modelo.Modelo;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class VistaClienteReserva extends javax.swing.JFrame implements IVista {
    
    // Nuevo: Inyectar controlador
    private ControladorReserva controlador;
    private Modelo modelo;
    
    // Componentes (sin cambios, generados por NetBeans)
    private javax.swing.JButton botonConfirmar;
    private javax.swing.JTextField campoNombre;
    // ... más componentes ...
    
    // Nuevo: Constructor que acepta controlador
    public VistaClienteReserva(Modelo modelo, ControladorReserva controlador) {
        this.modelo = modelo;
        this.controlador = controlador;
        initComponents();
    }
    
    // Mantener igual: initComponents() sigue siendo generado por NetBeans
    @SuppressWarnings("unchecked")
    private void initComponents() {
        // ... generado por NetBeans, NO MODIFICAR ...
    }
    
    // REFACTORIZADO: Delegar al controlador
    private void botonConfirmarActionPerformed(ActionEvent evt) {
        String nombre = campoNombre.getText();
        
        // ANTES: Hacía la lógica aquí
        // AHORA: Solo captar dato y delegar
        boolean exito = controlador.crearReserva(nombre);
        
        if (exito) {
            mostrarMensaje("Reserva creada exitosamente");
            limpiar();
        } else {
            mostrarError("Error al crear reserva");
        }
    }
    
    // Nuevo: Implementar interfaz IVista
    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", 
                                      JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void mostrarError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", 
                                      JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void limpiar() {
        campoNombre.setText("");
        // ... limpiar otros campos ...
    }
    
    @Override
    public void mostrar() {
        setVisible(true);
    }
    
    @Override
    public void ocultar() {
        setVisible(false);
    }
}
```

---

## Paso 3: Crear el Controlador Correspondiente

```java
package com.campingparty.controlador;

import com.campingparty.modelo.Modelo;
import com.campingparty.modelo.Reserva;
import com.campingparty.utilidades.Validador;

public class ControladorReserva {
    private Modelo modelo;
    
    public ControladorReserva(Modelo modelo) {
        this.modelo = modelo;
    }
    
    /**
     * Crea una nueva reserva
     * LÓGICA: Aquí va toda la validación y lógica de negocio
     */
    public boolean crearReserva(String nombre) {
        // Validar entrada
        if (!Validador.esNombreValido(nombre)) {
            return false;
        }
        
        // Lógica de negocio
        Reserva r = new Reserva(nombre);
        
        // Guardar en modelo
        return modelo.guardarReserva(r);
    }
}
```

---

## Paso 4: Usar la Vista Refactorizada

### Desde el Controlador Principal

```java
public class ControladorPrincipal {
    
    public void abrirReserva(Cliente cliente) {
        // Crear controlador específico
        ControladorReserva ctrlReserva = new ControladorReserva(modelo);
        
        // Crear vista con inyección de controlador
        VistaClienteReserva vista = new VistaClienteReserva(modelo, ctrlReserva);
        
        // Mostrar
        vista.setVisible(true);
    }
}
```

---

## Paso 5: Checklist para Migrar cada Vista

Para cada vista que migres, verifica:

- [ ] **Paquete**: Cambiar de `VISTA` a `com.campingparty.vista`
- [ ] **Implementa `IVista`**: Implementar todos los métodos de la interfaz
- [ ] **Constructor**: Aceptar controlador o modelo en constructor
- [ ] **Inyección de dependencias**: Recibir controlador en constructor
- [ ] **Event handlers**: Delegar lógica al controlador, no hacer lógica en la vista
- [ ] **initComponents()**: Dejar como está (generado por NetBeans)
- [ ] **mostrarMensaje()**: Implementado correctamente
- [ ] **mostrarError()**: Implementado correctamente
- [ ] **limpiar()**: Limpiar campos correctamente
- [ ] **Imports**: Cambiar imports de paquetes antiguos a nuevos

---

## Ejemplo Completo: VistaClienteLogin

### ANTES
```java
package VISTA;

public class ClientLogin extends javax.swing.JFrame {
    private Modelo modelo;
    
    public void botonEntrarActionPerformed(ActionEvent evt) {
        String user = campoUsuario.getText();
        String pass = new String(campoPass.getPassword());
        
        // Lógica de negocio AQUÍ (MALO)
        Cliente c = modelo.getDAO().getCliente(user, pass);
        if (c != null) {
            // Abrir otra vista
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}
```

### DESPUÉS
```java
package com.campingparty.vista;

public class VistaClienteLogin extends javax.swing.JFrame implements IVista {
    private ControladorClienteLogin controlador;
    
    public VistaClienteLogin(ControladorPrincipal controladorPrincipal) {
        this.controlador = new ControladorClienteLogin(controladorPrincipal.getModelo());
        this.controladorPrincipal = controladorPrincipal;
        initComponents();
    }
    
    private void botonEntrarActionPerformed(ActionEvent evt) {
        String user = campoUsuario.getText();
        String pass = new String(campoPass.getPassword());
        
        // Delegar al controlador (BIEN)
        controladorPrincipal.autenticarCliente(user, pass);
    }
    
    @Override
    public void mostrarError(String error) {
        etiquetaError.setText(error);
    }
}
```

---

## Notas Importantes

### 1. NetBeans Designer y archivos .form

Si el archivo tiene un `.form` asociado:
- El archivo `.form` es la definición visual (NO editar manualmente)
- El archivo `.java` es el código (SI editar)
- Solo modifica el método `initComponents()` si es necesario
- Preferiblemente, modifica desde el Designer de NetBeans

### 2. Preservar comportamiento visual

- Los components, layouts y diseño visuales NO cambian
- Solo cambia dónde está la lógica (del evento handler al controlador)
- Las vistas siguen viéndose exactamente igual

### 3. Acceso a datos

ANTES:
```java
modelo.getDAO().getCliente(user, pass); // DIRECTO A DAO
```

DESPUÉS:
```java
controlador.autenticar(user, pass); // Controlador hace la llamada
```

### 4. Orden de migraciones recomendado

1. Crear estructura de paquetes (`com.campingparty.*`)
2. Migrar clases de **Modelo** (Cliente, Reserva, Parcela, etc.)
3. Migrar **DAO**
4. Migrar **ControladorPrincipal** y controladores específicos
5. Migrar **Vistas** (empezar por las simples como VistaBienvenida)
6. Probar cada vista migrada
7. Ajustar según sea necesario

---

## Errores Comunes

### ❌ Error 1: Lógica en la Vista
```java
// NO HACER ESTO
private void botonClick() {
    Cliente c = new Cliente();
    modelo.save(c); // ← Lógica en vista
}
```

### ✅ Corrección
```java
// HACER ESTO
private void botonClick() {
    controlador.guardarCliente(nombre, dni); // ← Delegar
}
```

### ❌ Error 2: Crear componentes sin GenForms
```java
// Si NetBeans generó el .form, NO hagas esto:
botonEntrar = new JButton("Entrar");
// Usa el Designer en su lugar
```

### ❌ Error 3: Vista accediendo a DAO directamente
```java
// NO
private DAO dao = new DAO();
dao.getCliente(user, pass);

// SÍ
controlador.autenticar(user, pass);
```

---

## Preguntas Frecuentes

**P: ¿Debo crear el .form en NetBeans?**
R: Si ya existe el `.form`, mantenlo. Si es nueva, puedes crearla desde NetBeans Designer o hacerla programáticamente.

**P: ¿Cómo manejo eventos complejos?**
R: Los event handlers simples (onClick, onChange) van en la vista, que delegan al controlador. La lógica compleja va en el controlador.

**P: ¿Qué pasa con la seguridad (validación)?**
R: La validación va en el **Controlador**, no en la vista.

**P: ¿Puedo tener múltiples controladores?**
R: Sí, es lo ideal. Un controlador por cada grupo de funcionalidades.

---

## Resumen

| Aspecto | Antes | Después |
|--------|-------|---------|
| Paquetes | `VISTA`, `MODELO`, `CONTROLADOR` | `com.campingparty.vista`, `.modelo`, `.controlador` |
| Lógica en vista | Sí (MALO) | No (BIEN) |
| Acceso a DAO | Directo desde vista | A través de Controlador |
| Validación | En vista | En Controlador |
| Comunicación | Vista↔Modelo directo | Vista→Controlador→Modelo |

---

¡Ya estás listo para migrar las vistas! 🚀
