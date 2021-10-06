/** Se define una sublcase llamada Estudiante, que hereda de Socio
  */
public class Estudiante extends Socio {
  private String carrera;
  
  public Estudiante (int pDNI, String pNombre, String pCarrera) {
    super(pDNI, pNombre, 20);
    this.setCarrera(pCarrera);
  } 
  
  /** Establece el valor del parametro carrera
    * 
    * @param  pCarrera  Carrera del estudiante
    */
  private void setCarrera (String pCarrera) {
    this.carrera = pCarrera;
  }

  /** Retorna el valor del parametro carrera
    * 
    * @return  Carrera del estudiante
    */
  public String getCarrera () {
    return this.carrera; 
  }

  /** Retorna si el estudiante puede realizar nuevos prestamos
    * Condiciones: No poseer ningun prestamo vencido y no tener mas de 2 libros
    * 
    * @return  Puede realizar nuevos prestamos
    */
  public boolean puedePedir () {
    boolean tienePrestamosVencidos = false;

    // buscamos si tiene prestamos vencidos
    for (Prestamo p : this.getPrestamos()) {
      if (p.estaVencido()){
        tienePrestamosVencidos = true;
      }
    }
    
    if (!tienePrestamosVencidos && this.contarLibrosPrestados() < 3) { 
      return true;
    }

    return false;      
  }
  
  /** Retorna el tipo de socio que es
    * 
    * @return  "Estudiante"
    */
  public String getTipoSocio () {
    return "Estudiante";
  }
}
