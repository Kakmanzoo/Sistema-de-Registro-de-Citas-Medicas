import javax.swing.JOptionPane;

abstract class Datos {
    protected String nombre;
    protected int documento;
    
public Datos(String nombre, int documento){
    this.nombre = nombre;
    this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDocumento() {
        return documento;
    }

    public void MostrarDatos(){
        JOptionPane.showMessageDialog(null,"Nombre: " + nombre + "\nDocumento: " + documento);
    }
}
