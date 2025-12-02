package Controlador;

import Modelo.Modelo;
import Vista.ClienteLogin;
import Vista.TrabajadorLogin;
import Vista.VistaBienvenida;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controla la pantalla de bienvenida:
 *  - Botón "Soy Cliente"
 *  - Botón "Soy Staff"
 */
public class ControladorBienvenida {

    private final Modelo modelo;
    private final VistaBienvenida vista;
    private final ClienteLogin vistaClienteLogin;
    private final TrabajadorLogin vistaTrabajadorLogin;

    public ControladorBienvenida(Modelo modelo,
                                 VistaBienvenida vista,
                                 ClienteLogin vistaClienteLogin,
                                 TrabajadorLogin vistaTrabajadorLogin) {
        this.modelo = modelo;
        this.vista = vista;
        this.vistaClienteLogin = vistaClienteLogin;
        this.vistaTrabajadorLogin = vistaTrabajadorLogin;

        inicializar();
    }

    private void inicializar() {
        // Botón "Soy Cliente"
        vista.getBotonCliente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClienteSeleccionado();
            }
        });

        // Botón "Soy Staff"
        vista.getBotonTrabajador().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onStaffSeleccionado();
            }
        });
    }

    private void onClienteSeleccionado() {
        vista.setVisible(false);
        vistaClienteLogin.setLocationRelativeTo(vista);
        vistaClienteLogin.setVisible(true);
    }

    private void onStaffSeleccionado() {
        vista.setVisible(false);
        vistaTrabajadorLogin.setLocationRelativeTo(vista);
        vistaTrabajadorLogin.setVisible(true);
    }
}
