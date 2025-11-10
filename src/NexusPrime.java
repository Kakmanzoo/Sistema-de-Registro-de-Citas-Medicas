import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/** Login estilizado y compatible con VS Code con validación de credenciales. */
public class NexusPrime extends JFrame {
    // Campos con placeholderf para el usuario y la contraseña
    private PlaceholderTextField txtUsuario;
    private PlaceholderPasswordField txtContrasena;

    private JButton btnIngresar;
    private JCheckBox chkMostrar;
    private JLabel logoLabel;

    // Credenciales en memoria (edítalas en seedUsers) 
    private final Map<String, String> users = new HashMap<>();

    public NexusPrime() {
        super("NexusPrime - Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 560);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        seedUsers(); // Opción para cargar a los usuarios válidos

        JPanel left = buildLeftPane();
        JPanel right = buildRightPane();

        add(left, BorderLayout.CENTER);
        add(right, BorderLayout.EAST);
    }

    private void seedUsers() {
        // Agrega aquí los usuarios y contraseñas válidos
        users.put("admin", "admin123");
        users.put("Doctor", "Doctor2024");
        users.put("Enfermero", "Hospital1234"); // 🤝 No hagamos la guerra mejor la paz.
    }

    private JPanel buildLeftPane() {
        JPanel left = new JPanel(new BorderLayout());
        left.setBackground(UITheme.BG_LIGHT);
        left.setBorder(new EmptyBorder(20, 24, 20, 36)); // margen lateral derecho un poco mayor

        // TOP: logo pequeño + texto "LOGO"
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        top.setOpaque(false);
        JLabel miniLogo = new JLabel(loadLogo(18, 18));
        JLabel miniText = new JLabel("LOGO");
        miniText.setFont(UITheme.SMALL);
        miniText.setForeground(UITheme.TEXT_MUTED);
        top.add(miniLogo);
        top.add(miniText);

        // CENTER: formulario con GridBag para controlar spacing fino
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0; gc.gridy = 0;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);

        JLabel h1 = new JLabel("INICIAR SESIÓN");
        h1.setFont(UITheme.H1);               // grande y en negrita
        h1.setForeground(UITheme.TEXT_DARK);

        // Usuario
        JLabel lUser = new JLabel("USUARIO");
        lUser.setFont(UITheme.FORM_LABEL);
        lUser.setForeground(UITheme.TEXT_DARK);

        txtUsuario = new PlaceholderTextField("Digite tu usuario");
        stylizeUnderlineField(txtUsuario);

        // Contraseña
        JLabel lPass = new JLabel("CONTRASEÑA");
        lPass.setFont(UITheme.FORM_LABEL);
        lPass.setForeground(UITheme.TEXT_DARK);

        txtContrasena = new PlaceholderPasswordField("Digite tu contraseña");
        stylizeUnderlineField(txtContrasena);

        // Mostrar contraseña
        chkMostrar = new JCheckBox("Mostrar contraseña");
        chkMostrar.setOpaque(false);
        chkMostrar.setFont(UITheme.SMALL);
        chkMostrar.setForeground(UITheme.TEXT_MUTED);
        chkMostrar.addActionListener(e -> {
            if (chkMostrar.isSelected()) {
                txtContrasena.setEchoChar((char) 0);
            } else {
                txtContrasena.setEchoChar('\u2022');
            }
        });

        // Botón iniciar
        btnIngresar = new JButton("INICIAR");
        stylePrimaryButton(btnIngresar);
        btnIngresar.addActionListener(this::onLogin);
        getRootPane().setDefaultButton(btnIngresar);

        // Colocación (labels cerca del campo; aire entre bloques)
        // Título
        gc.gridwidth = 2; gc.weightx = 1; gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(4, 0, 24, 0);
        form.add(h1, gc);

        // Label USUARIO
        gc.gridy++; gc.gridwidth = 2;
        gc.insets = new Insets(0, 0, 6, 0);
        form.add(lUser, gc);

        // Campo USUARIO
        gc.gridy++; gc.gridwidth = 2; gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(0, 0, 16, 0);
        setFieldHeight(txtUsuario, 34);
        form.add(txtUsuario, gc);

        // Label CONTRASEÑA
        gc.gridy++; gc.gridwidth = 2; gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(0, 0, 6, 0);
        form.add(lPass, gc);

        // Campo CONTRASEÑA
        gc.gridy++; gc.gridwidth = 2; gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(0, 0, 6, 0);
        setFieldHeight(txtContrasena, 34);
        form.add(txtContrasena, gc);

        // Checkbox mostrar
        gc.gridy++; gc.gridwidth = 2; gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(0, 0, 22, 0);
        form.add(chkMostrar, gc);

        // Botón
        gc.gridy++; gc.gridwidth = 1; gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(0, 0, 0, 0);
        form.add(btnIngresar, gc);

        left.add(top, BorderLayout.NORTH);
        left.add(form, BorderLayout.CENTER);
        return left;
    }

    private JPanel buildRightPane() {
        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(360, 560));
        right.setBackground(UITheme.PURPLE);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(new EmptyBorder(40, 20, 40, 20));

        // Logo grande
        logoLabel = new JLabel(loadLogo(260, 180));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel brand = new JLabel("NexusPrime");
        brand.setForeground(Color.WHITE);
        brand.setFont(UITheme.H1);
        brand.setAlignmentX(Component.CENTER_ALIGNMENT);

        right.add(Box.createVerticalGlue());
        right.add(logoLabel);
        right.add(Box.createVerticalStrut(30));
        right.add(brand);
        right.add(Box.createVerticalGlue());

        return right;
    }

    private Icon loadLogo(int w, int h) {
        try {
            ImageIcon icon = new ImageIcon("./src/logo-nexusPrime.png"); // Ubicacion del logo
            if (icon.getIconWidth() > 0) {
                Image scaled = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            }
        } catch (Exception ignored) {}
        // Fallback: rectángulo blanco con borde morado
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.WHITE); g.fillRect(0, 0, w, h);
        g.setColor(UITheme.PURPLE_DARK);
        g.drawRect(1, 1, w-3, h-3);
        g.setFont(UITheme.FORM_LABEL);
        g.drawString("LOGO", 8, h/2);
        g.dispose();
        return new ImageIcon(img);
    }

    // Estilos de la pagina
    private void stylizeUnderlineField(JTextField field) {
        field.setBorder(new MatteBorder(0, 0, 2, 0, UITheme.FIELD_LINE)); // underline fino
        field.setFont(UITheme.INPUT);
        field.setForeground(UITheme.TEXT_DARK);
        field.setCaretColor(UITheme.TEXT_DARK);
        field.setOpaque(false);
        field.setMargin(new Insets(4, 2, 4, 2)); // más cerca del label visualmente
    }

    private void stylePrimaryButton(JButton b) {
        b.setFont(UITheme.BUTTON);
        b.setForeground(Color.WHITE);
        b.setBackground(UITheme.PURPLE);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(10, 22, 10, 22));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { b.setBackground(UITheme.PURPLE_DARK); }
            public void mouseExited(MouseEvent e) { b.setBackground(UITheme.PURPLE); }
        });
    }

    private void setFieldHeight(JTextField field, int h) {
        Dimension d = field.getPreferredSize();
        d.height = h;
        field.setPreferredSize(d);
        field.setMinimumSize(d);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, h));
    }

    // Manejo del evento de login
    private void onLogin(ActionEvent e) {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtContrasena.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa usuario y contraseña.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validación del login
        String stored = users.get(user);
        if (stored == null) {
            JOptionPane.showMessageDialog(this, "Usuario no existe.", "Credenciales inválidas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!stored.equals(pass)) {
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta.", "Credenciales inválidas", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Si todo se cumple, se va para la otra ventana
        new SistemaSaludGUI().setVisible(true);
        dispose();
    }

    // Para pruebas independientes
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignore) {}
        SwingUtilities.invokeLater(() -> new NexusPrime().setVisible(true));
    }
}

// Clase para el campo de texto con placeholder cuando está vacío (Swing no lo trae por defecto).
class PlaceholderTextField extends JTextField {
    private String placeholder;

    public PlaceholderTextField(String placeholder) {
        super();
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty() && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setColor(UITheme.TEXT_MUTED);
            g2.setFont(UITheme.PLACEHOLDER);
            Insets in = getInsets();
            g2.drawString(placeholder, in.left + 2, getHeight()/2 + getFont().getSize()/2 - 2);
            g2.dispose();
        }
    }
}

// Campo de contraseña con placeholder cuando está vacío. 
class PlaceholderPasswordField extends JPasswordField {
    private String placeholder;

    public PlaceholderPasswordField(String placeholder) {
        super();
        this.placeholder = placeholder;
        setEchoChar('\u2022'); // •
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getPassword().length == 0 && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setColor(UITheme.TEXT_MUTED);
            g2.setFont(UITheme.PLACEHOLDER);
            Insets in = getInsets();
            g2.drawString(placeholder, in.left + 2, getHeight()/2 + getFont().getSize()/2 - 2);
            g2.dispose();
        }
    }
}
