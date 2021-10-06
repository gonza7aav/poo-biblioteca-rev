import java.util.Calendar;

/** Se define una clase llamada Prestamo
  */
public class Prestamo {
  private Calendar fechaRetiro;
  private Calendar fechaDevolucion;
  private Socio socio;
  private Libro libro;

  public Prestamo (
    Calendar pFechaRetiro,
    Socio pSocio,
    Libro pLibro
  ) {
    this.setFechaRetiro(pFechaRetiro);
    this.setFechaDevolucion(null);
    this.setSocio(pSocio);
    this.setLibro(pLibro);
  }

  /** Establece el valor del parametro fechaRetiro
    * 
    * @param  pFechaRetiro  Fecha cuando se ha realizado el prestamo
    */
  private void setFechaRetiro (Calendar pFechaRetiro) {
    this.fechaRetiro = pFechaRetiro;
  }

  /** Establece el valor del parametro fechaDevolucion
    * 
    * @param  pFechaDevolucion  Fecha cuando se ha cancelado el prestamo
    */
  private void setFechaDevolucion (Calendar pFechaDevolucion) {
    this.fechaDevolucion = pFechaDevolucion;
  }

  /** Establece el valor del parametro socio
    * 
    * @param  pSocio  Socio que pidio el libro
    */
  private void setSocio (Socio pSocio) {
    this.socio = pSocio;
  }

  /** Establece el valor del parametro libro
    * 
    * @param  pLibro  Libro que se ha prestado
    */
  private void setLibro (Libro pLibro) {
    this.libro = pLibro;
  }

  /** Retorna el valor del parametro fechaRetiro
    * 
    * @return  Fecha cuando se ha realizado el prestamo
    */
  public Calendar getFechaRetiro () {
    return this.fechaRetiro;
  }

  /** Retorna el valor del parametro fechaDevolucion
    * 
    * @return  Fecha cuando se ha realizado el prestamo
    */
  public Calendar getFechaDevolucion () {
    return this.fechaDevolucion;
  }

  /** Retorna el valor del parametro socio
    * 
    * @return  Socio que pidio el libro
    */
  public Socio getSocio () {
    return this.socio;
  }

  /** Retorna el valor del parametro libro
    * 
    * @return  Libro que se ha prestado
    */
  public Libro getLibro () {
    return this.libro;
  }

  /** Registra la fecha de la cancelacion del prestamo
    * 
    * @param  pFechaDevolucion  Fecha de la devolcion del libro
    */
  public void registrarFechaDevolucion (Calendar pFechaDevolucion) {
    this.setFechaDevolucion(pFechaDevolucion);
    this.getSocio().removePrestamo(this);
    this.getLibro().removePrestamo(this);
  }

  /** Retorna si el prestamo se encuentra vencido
    *
    * @return  Si se encuentra vencido
    */
  public boolean estaVencido () {
    Calendar hoy = Calendar.getInstance();

    // hacer una copia de la fecha de realizacion del prestamo
    Calendar FechaVencimiento = Calendar.getInstance();
    FechaVencimiento.set(
      this.getFechaRetiro().get(Calendar.YEAR),
      this.getFechaRetiro().get(Calendar.MONTH),
      this.getFechaRetiro().get(Calendar.DAY_OF_MONTH)
    );
    
    // agregar los dias que tiene el socio para sus prestamos
    FechaVencimiento.add(Calendar.DAY_OF_MONTH, this.getSocio().getDiasPrestamo());

    return FechaVencimiento.compareTo(hoy) < 0;
  }

  /** Retorna los datos del prestamo en una cadena formateada
    * 
    * @return  Informacion del prestamo
    */
  public String toString () {
    String info = "";
    
    String infoFechaRetiro = this.getFechaRetiro().get(Calendar.YEAR) + "/";
    infoFechaRetiro += this.getFechaRetiro().get(Calendar.MONTH) + "/";
    infoFechaRetiro += this.getFechaRetiro().get(Calendar.DAY_OF_MONTH);

    String infoFechaDevolucion = "----/--/--";
    if (this.getFechaDevolucion() != null) {
      infoFechaDevolucion = this.getFechaDevolucion().get(Calendar.YEAR) + "/";
      infoFechaDevolucion += this.getFechaDevolucion().get(Calendar.MONTH) + "/";
      infoFechaDevolucion += this.getFechaDevolucion().get(Calendar.DAY_OF_MONTH);
    }

    info += "Retiro: " + infoFechaRetiro + " | Devolucion: " + infoFechaDevolucion + "\n";
    info += "Libro: " + this.getLibro().toString() + "\n";
    info += "Socio: " + this.getSocio().getNombre() + "\n";

    return info;
  }
}
