import java.awt.*;

//Paleta de colores y tipografías compartidas para el sistema.
public final class UITheme {
    // Colores principales
    public static final Color PURPLE = new Color(102, 0, 102);
    public static final Color PURPLE_DARK = new Color(78, 0, 78);
    public static final Color TEXT_DARK = new Color(30, 30, 30);
    public static final Color TEXT_MUTED = new Color(120, 120, 120);
    public static final Color BG_LIGHT = Color.WHITE;
    public static final Color FIELD_LINE = new Color(190, 190, 190);

    // Tipografías
    public static final Font H1           = new Font("SansSerif", Font.BOLD, 28); // Título grande
    public static final Font H2           = new Font("SansSerif", Font.BOLD, 18); // Subtítulos
    public static final Font FORM_LABEL   = new Font("Lucida Fax", Font.BOLD, 14); // "USUARIO" / "CONTRASEÑA"
    public static final Font INPUT        = new Font("SansSerif", Font.PLAIN, 15); // campos
    public static final Font BUTTON       = new Font("SansSerif", Font.BOLD, 14); // botón
    public static final Font PLACEHOLDER  = new Font("SansSerif", Font.ITALIC, 13);
    public static final Font SMALL        = new Font("SansSerif", Font.PLAIN, 12);

    private UITheme() {}
}
