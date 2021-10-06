import java.util.ArrayList;

/**
  * Se define una clase abstracta llamada Socio
  */
public abstract class Socio {
  private int dni;
  private String nombre;
  private int diasPrestamo;
  private ArrayList<Prestamo> prestamos;

  public Socio (
    int pDNI,
    String pNombre,
    int pDiasPrestamo
  ) {
    this.setDNI(pDNI);
    this.setNombre(pNombre);
    this.setDiasPrestamo(pDiasPrestamo);
    this.setPrestamos(new ArrayList<Prestamo>());
  }

  /** Establece el valor del parametro dni
  * 
  * @param  pDNI  DNI del socio
  */
  private void setDNI (int pDNI) {
    this.dni = pDNI;
  }

  /** Establece el valor del parametro nombre
  * 
  * @param  pNombre  Nombre del socio
  */
  private void setNombre (String pNombre) {
    this.nombre = pNombre;
  }

  /** Establece el valor del parametro diasPrestamo
    * 
    * @param  pDiasPrestamo  Dias para poseer un libro
    */
  public void setDiasPrestamo (int pDiasPrestamo) {
    this.diasPrestamo = pDiasPrestamo;
  }

  /** Establece el valor del parametro prestamos
    * 
    * @param  pPrestamos  Prestamos realizados por el socio
    */
  private void setPrestamos (ArrayList<Prestamo> pPrestamos) {
    this.prestamos = pPrestamos;
  }

  /** Retorna el valor del parametro dni
    * 
    * @return  DNI del socio
    */
  public int getDNI () {
    return this.dni;
  }

  /** Retorna el valor del parametro nombre
    * 
    * @return  Nombre del socio
    */
  public String getNombre () {
    return this.nombre;
  }

  /** Retorna el valor del parametro diasPrestamo
  * 
  * @return  Dias para poseer un libro
  */
  public int getDiasPrestamo () {
    return this.diasPrestamo;
  }

  /** Retorna el valor del parametro prestamos
    * 
    * @return  Prestamos realizados por el socio
    */
  public ArrayList<Prestamo> getPrestamos () {
    return this.prestamos;
  }

  /** Asocia un nuevo prestamo al socio
    * 
    * @return  Exito de la operacion
    */
  public boolean addPrestamo (Prestamo pPrestamo) {
    return this.getPrestamos().add(pPrestamo);
  }

  /** Desasocia un prestamo del socio
    * 
    * @return  Exito de la operacion
    */
  public boolean removePrestamo (Prestamo pPrestamo) {
    return this.getPrestamos().remove(pPrestamo);
  }

  /** Retorna la cantidad de libros que tiene a modo de prestamos
    * 
    * @return  Cantidad de libros que posee
    */
  public int contarLibrosPrestados () {
    return this.getPrestamos().size();
  }

  /** Retorna los datos personales en una cadena formateada
    * 
    * @return  Informacion del socio
    */
  public String toString () {
    return "DNI: " + this.getDNI() +
    " | " + this.getNombre() + " (" + this.getTipoSocio() + ")" +
    " | Libros Prestados: " + this.contarLibrosPrestados();
  }

  /** Retorna si el socio no tiene prestamos vencidos
    * 
    * @return  Puede realizar nuevos prestamos
    */
  public boolean puedePedir () {
    for (Prestamo prestamo : this.getPrestamos()) {
      if (prestamo.estaVencido()) {
        return false;
      }
    }
    return true;
  }

  /** Retorna el tipo de socio que es
    * 
    * @return  Tipo de socio
    */
  public abstract String getTipoSocio ();
  
}
