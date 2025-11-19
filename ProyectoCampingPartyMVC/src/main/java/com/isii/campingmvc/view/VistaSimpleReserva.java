package com.isii.campingmvc.view;

import com.isii.campingmvc.controller.ReservaController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class VistaSimpleReserva extends JFrame {
    private final ReservaController controller;
    private final JCheckBox[] parcelaChecks = new JCheckBox[16];
    private final JTextField fechaInField = new JTextField(10);
    private final JTextField fechaOutField = new JTextField(10);
    private final JTextField nombreField = new JTextField(15);

    public VistaSimpleReserva(ReservaController controller) {
        super("Demo MVC - Reservas");
        this.controller = controller;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildUI();
        pack();
        setLocationRelativeTo(null);
    }

    private void buildUI() {
        JPanel p = new JPanel(new BorderLayout(8,8));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Fecha entrada (YYYY-MM-DD):")); top.add(fechaInField);
        top.add(new JLabel("Fecha salida (YYYY-MM-DD):")); top.add(fechaOutField);
        top.add(new JLabel("Cliente:")); top.add(nombreField);
        p.add(top, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(4,4,4,4));
        for (int i = 0; i < 16; i++) {
            parcelaChecks[i] = new JCheckBox("P" + (i+1));
            parcelaChecks[i].setEnabled(false);
            center.add(parcelaChecks[i]);
        }
        p.add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton checkBtn = new JButton("Comprobar disponibles");
        JButton reservarBtn = new JButton("Reservar seleccionado");

        checkBtn.addActionListener(e -> controller.actualizarVistaParcelas());

        reservarBtn.addActionListener(e -> onReservar());

        bottom.add(checkBtn);
        bottom.add(reservarBtn);
        p.add(bottom, BorderLayout.SOUTH);

        add(p);
    }

    private void onReservar() {
        int selected = -1;
        for (int i = 0; i < parcelaChecks.length; i++) if (parcelaChecks[i].isSelected()) { selected = i; break; }
        if (selected == -1) { showMessage("Selecciona una parcela"); return; }
        LocalDate in, out;
        try {
            in = LocalDate.parse(fechaInField.getText());
            out = LocalDate.parse(fechaOutField.getText());
        } catch (DateTimeParseException ex) {
            showMessage("Formato de fecha inválido"); return;
        }
        controller.crearReserva(in, out, nombreField.getText().trim(), selected);
    }

    public void updateParcelas(boolean[] libres) {
        for (int i = 0; i < Math.min(parcelaChecks.length, libres.length); i++) {
            parcelaChecks[i].setEnabled(libres[i]);
            parcelaChecks[i].setSelected(false);
        }
    }

    public void showMessage(String s) {
        JOptionPane.showMessageDialog(this, s);
    }
}
