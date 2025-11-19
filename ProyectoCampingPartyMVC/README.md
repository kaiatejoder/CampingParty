# ProyectoCampingPartyMVC

Proyecto mínimo creado para demostrar un diseño Modelo-Vista-Controlador (MVC) derivado del proyecto original.

Cómo usar (NetBeans):

1. File → Open Project → seleccionar la carpeta `ProyectoCampingPartyMVC`.
2. Clean and Build.
3. Run → Run Project, o desde Maven: `mvn -f ProyectoCampingPartyMVC/pom.xml exec:java`.

Qué contiene:
- `com.isii.campingmvc.model` - clases de dominio: `Modelo`, `Parcela`, `Reserva`, `Cliente`.
- `com.isii.campingmvc.controller` - `ReservaController` que implementa la lógica.
- `com.isii.campingmvc.view` - `VistaSimpleReserva` (Swing) como vista simple.

Objetivo: dejar el código original intacto y proporcionar un ejemplo autónomo y ejecutable en una carpeta separada.
