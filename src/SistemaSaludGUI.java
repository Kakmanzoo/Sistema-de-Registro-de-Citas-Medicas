import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SistemaSaludGUI extends JFrame {
    private JTextField nombreField, documentoField, tipoCitaField, fechaCitaField;
    private JButton agendarBtn, cancelarBtn, mostrarBtn;
    private JTextArea areaDatos;
    private ArrayList<Paciente> pacientes;

    public SistemaSaludGUI() {
        setTitle("Sistema de Gestión de Citas de Salud");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        pacientes = new ArrayList<>();

        // Barra de menu morada (izquierda)
        JPanel sidebar = new JPanel();
        sidebar.setBackground(UITheme.PURPLE);
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(24, 18, 24, 18));

        JLabel brand = new JLabel("NexusPrime");
        brand.setAlignmentX(Component.CENTER_ALIGNMENT);
        brand.setForeground(Color.WHITE);
        brand.setFont(UITheme.H2);

        // Boton informacion del programa
        JButton btnInfo = new JButton("Información del programa");
        styleSidebarButton(btnInfo);
        btnInfo.addActionListener(e -> mostrarInfoPrograma());

        JButton btnSalir = new JButton("Salir");
        styleSidebarButton(btnSalir);
        btnSalir.addActionListener(e -> dispose());

        sidebar.add(brand);
        sidebar.add(Box.createVerticalStrut(16));
        sidebar.add(btnInfo);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnSalir);

        // Logo y titulo superior del programa
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UITheme.BG_LIGHT);
        header.setBorder(new EmptyBorder(12, 16, 12, 16));
        JLabel smallLogo = new JLabel(loadLogo("src/logo-nexusPrime.png", 40, 40));
        JLabel titulo = new JLabel("Gestión de Citas");
        titulo.setFont(UITheme.H2);
        titulo.setForeground(UITheme.TEXT_DARK);
        JPanel leftHead = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        leftHead.setOpaque(false);
        leftHead.add(smallLogo);
        leftHead.add(titulo);
        header.add(leftHead, BorderLayout.WEST);

        // Formulario
        JPanel panelTop = new JPanel(new GridLayout(2, 4, 10, 8));
        panelTop.setBorder(new EmptyBorder(6, 16, 6, 16));
        panelTop.setBackground(UITheme.BG_LIGHT);

        panelTop.add(styledLabel("Nombre:"));
        nombreField = new JTextField();
        panelTop.add(stylizeField(nombreField));

        panelTop.add(styledLabel("Documento:"));
        documentoField = new JTextField();
        panelTop.add(stylizeField(documentoField));

        panelTop.add(styledLabel("Tipo de Cita:"));
        tipoCitaField = new JTextField();
        panelTop.add(stylizeField(tipoCitaField));

        panelTop.add(styledLabel("Fecha de Cita:"));
        fechaCitaField = new JTextField();
        panelTop.add(stylizeField(fechaCitaField));

        // Botones
        JPanel panelBarra = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        panelBarra.setBorder(new EmptyBorder(6, 16, 12, 16));
        panelBarra.setBackground(UITheme.BG_LIGHT);
        agendarBtn = new JButton("Agendar Cita");
        cancelarBtn = new JButton("Cancelar Cita");
        mostrarBtn = new JButton("Mostrar Citas");
        stylePrimaryButton(agendarBtn);
        styleSecondaryButton(cancelarBtn);
        styleSecondaryButton(mostrarBtn);
        panelBarra.add(agendarBtn);
        panelBarra.add(cancelarBtn);
        panelBarra.add(mostrarBtn);

        // Área de datos en el centro del programa. Acá se ubica los datos del programa
        // que el usuario ponga
        areaDatos = new JTextArea();
        areaDatos.setEditable(false);
        areaDatos.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBackground(UITheme.BG_LIGHT);

        content.add(panelTop, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(0, 10));
        centerPanel.setOpaque(false);
        centerPanel.add(panelBarra, BorderLayout.NORTH);

        JPanel areaWrapper = new JPanel(new GridBagLayout());
        areaWrapper.setOpaque(false);
        JScrollPane scrollArea = new JScrollPane(areaDatos);
        scrollArea.setPreferredSize(new Dimension(600, 200)); // altura visible centrada
        scrollArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.FIELD_LINE),
                new EmptyBorder(10, 10, 10, 10)));
        areaWrapper.add(scrollArea, new GridBagConstraints());
        centerPanel.add(areaWrapper, BorderLayout.CENTER);

        content.add(centerPanel, BorderLayout.CENTER);
        content.setBorder(new EmptyBorder(0, 16, 16, 16));

        add(sidebar, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

        // Acciones
        agendarBtn.addActionListener(e -> agendarCita());
        cancelarBtn.addActionListener(e -> cancelarCita());
        mostrarBtn.addActionListener(e -> mostrarCitas());
    }

    // Ventana "Información del programa"
    private void mostrarInfoPrograma() {
        JDialog dlg = new JDialog(this, "Información del programa", true);
        dlg.setSize(520, 360);
        dlg.setLocationRelativeTo(this);
        dlg.setLayout(new BorderLayout());
        dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Encabezado
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(UITheme.PURPLE);
        top.setBorder(new EmptyBorder(12, 16, 12, 16));
        JLabel title = new JLabel("Información del programa");
        title.setForeground(Color.WHITE);
        title.setFont(UITheme.H2);
        top.add(title, BorderLayout.WEST);

        // Contenido
        JPanel body = new JPanel(new BorderLayout(16, 16));
        body.setBorder(new EmptyBorder(16, 16, 16, 16));
        body.setBackground(UITheme.BG_LIGHT);

        JLabel logo = new JLabel(loadLogo("src/logo-nexusPrime.png", 120, 120));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        body.add(logo, BorderLayout.WEST);

        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.setOpaque(false);
        info.setFont(UITheme.INPUT);
        info.setText(
                "Sistema de Gestión de Citas de Salud\n" +
                        "Versión:  1.0.0\n" +
                        "Creador:    Kakmanzoo\n" +
                        "Descripción:\n" +
                        "Aplicación de escritorio para agendar, cancelar y consultar\n" +
                        "citas médicas.\n" +
                        "Tecnologías:\n" +
                        "- Java 17\n" +
                        "- Programación orientada a Objetos\n" +
                        "Muchas gracias por usar el programa :)");
        body.add(info, BorderLayout.CENTER);

        // Pie con botón cerrar
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(UITheme.BG_LIGHT);
        JButton cerrar = new JButton("Cerrar");
        styleSecondaryButton(cerrar);
        cerrar.addActionListener(e -> dlg.dispose());
        footer.add(cerrar);

        dlg.add(top, BorderLayout.NORTH);
        dlg.add(body, BorderLayout.CENTER);
        dlg.add(footer, BorderLayout.SOUTH);

        dlg.setVisible(true);
    }

    // Utilidades de estilo
    private JLabel styledLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(UITheme.FORM_LABEL);
        l.setForeground(UITheme.TEXT_DARK);
        return l;
    }

    private JTextField stylizeField(JTextField field) {
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.FIELD_LINE),
                new EmptyBorder(6, 8, 6, 8)));
        field.setFont(UITheme.INPUT);
        field.setForeground(UITheme.TEXT_DARK);
        return field;
    }

    private void stylePrimaryButton(JButton b) {
        b.setFont(UITheme.BUTTON);
        b.setForeground(Color.WHITE);
        b.setBackground(UITheme.PURPLE);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(8, 16, 8, 16));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b.setBackground(UITheme.PURPLE_DARK);
            }

            public void mouseExited(MouseEvent e) {
                b.setBackground(UITheme.PURPLE);
            }
        });
    }

    private void styleSecondaryButton(JButton b) {
        b.setFont(UITheme.BUTTON);
        b.setForeground(UITheme.PURPLE);
        b.setBackground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.PURPLE),
                new EmptyBorder(8, 16, 8, 16)));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b.setBackground(new Color(245, 245, 245));
            }

            public void mouseExited(MouseEvent e) {
                b.setBackground(Color.WHITE);
            }
        });
    }

    private void styleSidebarButton(JButton b) {
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setFont(UITheme.BUTTON);
        b.setForeground(Color.WHITE);
        b.setBackground(UITheme.PURPLE_DARK);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(8, 12, 8, 12));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b.setBackground(UITheme.PURPLE);
            }

            public void mouseExited(MouseEvent e) {
                b.setBackground(UITheme.PURPLE_DARK);
            }
        });
    }

    // Imagen de la ventana de informacipon y logo superior
    private Icon loadImage(String path, int w, int h) {
        try {
            ImageIcon icon = new ImageIcon(path);
            if (icon.getIconWidth() > 0) {
                Image scaled = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            }
        } catch (Exception ignored) {
        }
        // Fallback si no se encuentra la imagen
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(w, h,
                java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        g.setColor(UITheme.PURPLE_DARK);
        g.drawRect(1, 1, w - 3, h - 3);
        g.setFont(UITheme.FORM_LABEL);
        g.drawString("IMG", 8, h / 2);
        g.dispose();
        return new ImageIcon(img);
    }

    // Carga y escala un logo, comportamiento similar a loadImage pero con texto de
    // fallback "LOGO"
    private Icon loadLogo(String path, int w, int h) {
        try {
            ImageIcon icon = new ImageIcon(path);
            if (icon.getIconWidth() > 0) {
                Image scaled = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            }
        } catch (Exception ignored) {
        }
        // Fallback si no se encuentra la imagen
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(w, h,
                java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        // Dibujar fondo circular para logo
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(UITheme.PURPLE);
        g.fillOval(0, 0, w, h);
        g.setColor(Color.WHITE);
        g.setFont(UITheme.FORM_LABEL.deriveFont(Math.max(10f, w / 6f)));
        FontMetrics fm = g.getFontMetrics();
        String text = "LOGO";
        int tx = (w - fm.stringWidth(text)) / 2;
        int ty = (h - fm.getHeight()) / 2 + fm.getAscent();
        g.drawString(text, tx, ty);
        g.dispose();
        return new ImageIcon(img);
    }

    // Parte de la logica del codigo
    private void agendarCita() {
        String nombre = nombreField.getText().trim();
        String docStr = documentoField.getText().trim();
        String tipoCita = tipoCitaField.getText().trim();
        String fechaCita = fechaCitaField.getText().trim();

        if (nombre.isEmpty() || docStr.isEmpty() || tipoCita.isEmpty() || fechaCita.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos para agendar la cita.");
            return;
        }
        int documento;
        try {
            documento = Integer.parseInt(docStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Documento debe ser numérico.");
            return;
        }

        for (Paciente p : pacientes) {
            if (p.getDocumento() == documento) {
                p.setCita(tipoCita);
                p.setFecha(fechaCita);
                areaDatos
                        .setText("Cita actualizada para el usuario existente.\nUse 'Mostrar Citas' para ver detalles.");
                limpiarCampos();
                return;
            }
        }
        Paciente paciente = new Paciente(nombre, documento);
        paciente.setCita(tipoCita);
        paciente.setFecha(fechaCita);
        pacientes.add(paciente);
        areaDatos.setText("Cita agendada correctamente.\nUse 'Mostrar Citas' para ver detalles.");
        limpiarCampos();
    }

    private void cancelarCita() {
        String docStr = documentoField.getText().trim();
        if (docStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el documento del paciente a cancelar.");
            return;
        }
        int documento;
        try {
            documento = Integer.parseInt(docStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Documento debe ser numérico.");
            return;
        }
        for (Paciente p : pacientes) {
            if (p.getDocumento() == documento) {
                p.cancelarCita();
                areaDatos.setText("Cita cancelada para: " + p.getNombre());
                limpiarCampos();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "No se encontró paciente con ese documento.");
    }

    private void mostrarCitas() {
        if (pacientes.isEmpty()) {
            areaDatos.setText("No hay citas registradas.");
            return;
        }
        StringBuilder sb = new StringBuilder("----- Citas Registradas -----\n");
        for (Paciente p : pacientes) {
            sb.append("Nombre: ").append(p.getNombre()).append("\n");
            sb.append("Documento: ").append(p.getDocumento()).append("\n");
            sb.append("Cita: ").append(p.getCita()).append("\n");
            sb.append("Fecha: ").append(p.getFecha()).append("\n");
            sb.append("-------------------------\n");
        }
        areaDatos.setText(sb.toString());
    }

    private void limpiarCampos() {
        nombreField.setText("");
        documentoField.setText("");
        tipoCitaField.setText("");
        fechaCitaField.setText("");
    }
}
