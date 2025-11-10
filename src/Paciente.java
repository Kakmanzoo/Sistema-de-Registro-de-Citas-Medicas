import javax.swing.JOptionPane;

public class Paciente extends Datos implements GestionCita{
    private String cita;
    private String fecha; 
    public void setCita(String cita) { this.cita = cita; }
    public String getCita() {
        return cita;
    }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getFecha() {
        return fecha;
    }
    
    public Paciente (String nombre, int documento){
        super(nombre, documento);
    }
    
    @Override
    public void solicitarCita(){
        cita = JOptionPane.showInputDialog("Tipo de cita: ");
        fecha = JOptionPane.showInputDialog("Fecha de la cita: ");
    }
    
    @Override
    public void cancelarCita(){
        this.cita = "Cita no agregada";
        this.fecha = "Fecha no agregada";
        JOptionPane.showMessageDialog(null, "Su cita ha sido cancelada");
    }
    
    @Override
    public void MostrarDatos(){
    JOptionPane.showMessageDialog(null,"\n----- Datos de Cita -----" +
        "\nNombre: " + nombre + 
        "\nDocumento: " + documento + 
        "\nCita: " + cita + 
        "\nFecha: " + fecha);
    }
}
