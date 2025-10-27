package menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menú de pausa que se muestra cuando el juego está pausado.
 * Permite reanudar, guardar o salir del juego.
 */
public class MenuPausa extends JDialog {
    private JButton btnReanudar;
    private JButton btnGuardar;
    private JButton btnSalir;
    private InterfazJuego interfazJuego;

    public MenuPausa(InterfazJuego parent) {
        super(parent, "Pausa", true);
        this.interfazJuego = parent;
        initComponents();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel lblTitulo = new JLabel("Juego Pausado");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(lblTitulo, gbc);

        // Botón Reanudar
        btnReanudar = new JButton("Reanudar");
        btnReanudar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interfazJuego.reanudarJuego();
                setVisible(false);
            }
        });
        gbc.gridy = 1;
        add(btnReanudar, gbc);

        // Botón Guardar
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interfazJuego.guardarJuego();
                // No cerrar el menú, permitir reanudar después de guardar
            }
        });
        gbc.gridy = 2;
        add(btnGuardar, gbc);

        // Botón Salir
        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(MenuPausa.this,
                        "¿Estás seguro de que quieres salir? El progreso no guardado se perderá.",
                        "Confirmar Salida", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        gbc.gridy = 3;
        add(btnSalir, gbc);

        pack();
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
}