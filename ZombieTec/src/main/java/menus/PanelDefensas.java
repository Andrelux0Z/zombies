package menus;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.util.function.BiConsumer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Panel que contiene los iconos de defensas arrastrables.
 */
public class PanelDefensas {
    private String[] nombresDefensas = { "bomba", "gatoglobo", "lanzallamas", "metralleta", "Torreta" };
    private BiConsumer<String, Image> onDefensaArrastrada;

    public PanelDefensas(BiConsumer<String, Image> onDefensaArrastrada) {
        this.onDefensaArrastrada = onDefensaArrastrada;
    }

    /* Configura el panel con las defensas arrastrables */
    public void configurar(JPanel panelDestino) {
        panelDestino.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        for (String nombreDefensa : nombresDefensas) {
            JLabel labelDefensa = crearLabelDefensa(nombreDefensa);
            panelDestino.add(labelDefensa);
        }

        panelDestino.revalidate();
        panelDestino.repaint();
    }

    /* Crea un JLabel con la imagen de una defensa que se puede arrastrar */
    private JLabel crearLabelDefensa(String nombreDefensa) {
        String rutaImagen = resolverRutaDefensa(nombreDefensa);
        Image imagen = new ImageIcon(rutaImagen).getImage();
        Image imagenEscalada = imagen.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        JLabel label = new JLabel(new ImageIcon(imagenEscalada));
        label.setToolTipText(nombreDefensa);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        configurarDragParaDefensa(label, nombreDefensa, imagen);
        return label;
    }

    // Resuelve la ruta de imagen para una defensa usando la nueva estructura
    private String resolverRutaDefensa(String nombreDefensa) {
        String base = "src/main/java/Resourses/";
        String nombre;
        if (nombreDefensa == null) {
            nombre = "";
        } else {
            nombreDefensa = nombreDefensa.trim().toLowerCase();
            nombre = nombreDefensa;
        }
        return base + "defensas/" + nombre + ".png";
    }

    /* Configura el arrastre para una defensa */
    private void configurarDragParaDefensa(JLabel label, String nombreDefensa, Image imagen) {
        DragSource dragSource = DragSource.getDefaultDragSource();

        DragGestureListener dragGestureListener = new DragGestureListener() {
            public void dragGestureRecognized(DragGestureEvent dge) {
                // Notificar que se inició el arrastre
                if (onDefensaArrastrada != null) {
                    onDefensaArrastrada.accept(nombreDefensa, imagen);
                }

                Transferable transferable = new Transferable() {
                    public DataFlavor[] getTransferDataFlavors() {
                        return new DataFlavor[] { DataFlavor.stringFlavor };
                    }

                    public boolean isDataFlavorSupported(DataFlavor flavor) {
                        return flavor.equals(DataFlavor.stringFlavor);
                    }

                    public Object getTransferData(DataFlavor flavor) {
                        return nombreDefensa;
                    }
                };

                dge.startDrag(DragSource.DefaultMoveDrop, transferable);
            }
        };

        dragSource.createDefaultDragGestureRecognizer(label, DnDConstants.ACTION_COPY, dragGestureListener);
    }
}
