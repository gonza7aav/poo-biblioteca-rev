/** Se define una subclase llamada Docente, que hereda de Socio
  */
public class Docente extends Socio {
  private String departamento;
  
  public Docente (int pDNISocio, String pNombre, String pDepartamento) {
    super(pDNISocio,pNombre, 5);
    this.setDepartamento(pDepartamento);
  }

  /** Establece el valor del parametro departamento
    * 
    * @param  pDepartamento  Departamento del docente
    */
  private void setDepartamento (String pDepartamento) {
    this.departamento = pDepartamento;
  }

  /** Retorna el valor del parametro departamento
    * 
    * @return  Departamento del docente
    */
  public String getDepartamento () {
    return this.departamento; 
  }
 
  /** Nos indica si el docente tuvo prestamos que se vencieron
    */
  public boolean esResponsable () {
    for (Prestamo p : this.getPrestamos()) { 
      if (p.estaVencido ()) { 
        return false;
      }
    }
    return true;
  }
  
  /** Agrega una cantidad adicional de dias para tener los prestamos
    * 
    * @param  pDiasExtra  Dias ha agregar
    */
  public void agregarDiasDePrestamo (int pDiasExtra) {
    this.setDiasPrestamo(this.getDiasPrestamo() + pDiasExtra);
  }

  /** Retorna el tipo de socio que es
    * 
    * @return  "Docente"
    */
  public String getTipoSocio () {
    return "Docente";
  }
}
