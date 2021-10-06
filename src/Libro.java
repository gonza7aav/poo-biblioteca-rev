import java.util.ArrayList;

/** Clase que describe las caracteristicas mas relevantes de un libro
  */
public class Libro {
  private String titulo;
  private int edicion;
  private String autor;
  private int anio;
  private ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();

  public Libro (String pTitulo, int pEdicion, String pAutor, int pAnio) {
    this.setTitulo(pTitulo);
    this.setEdicion(pEdicion);
    this.setAutor(pAutor);
    this.setAnio(pAnio);
  }

  /** Establece el valor del parametro titulo
    * 
    * @param  pTitulo  Titulo del libro
    */
  private void setTitulo (String pTitulo) {
    this.titulo = pTitulo;
  }

  /** Establece el valor del parametro edicion
    * 
    * @param  pEdicion  Numero de edicion
    */
  private void setEdicion (int pEdicion) {
    this.edicion = pEdicion;
  }

  /** Establece el valor del parametro autor
    * 
    * @param  pAutor  Autor del libro
    */
  private void setAutor (String pAutor) { 
    this.autor = pAutor;
  }

  /** Establece el valor del parametro anio
    * 
    * @param  pAnio  Anio de publicacionel libro
    */
  private void setAnio (int pAnio) {
    this.anio = pAnio;
  }

  /** Retorna el valor del parametro titulo
    * 
    * @return  Titulo del libro
    */
  public String getTitulo () {
    return this.titulo;
  }

  /** Retorna el valor del parametro edicion
    * 
    * @return  Numero de edicion
    */
  public int getEdicion () {
    return this.edicion;
  }

  /** Retorna el valor del parametro autor
    * 
    * @return  Autor del libro
    */
  public String getAutor () {
    return this.autor;
  }

  /** Retorna el valor del parametro anio
    * 
    * @return  Anio de publicacionel libro
    */
  public int getAnio () {
    return this.anio;
  }

  /** Retorna el valor del parametro prestamos
    * 
    * @return  Prestamos del libro
    */
  public ArrayList<Prestamo> getPrestamos () {
    return this.prestamos;
  }

  /** Retorna el ultimo prestamo del libro
    * 
    * @return  Ultimo prestamo
    */
  public Prestamo getUltimoPrestamo () {        
    int longitud = this.getPrestamos().size();
    
    if(longitud > 0) { 
      //obtenemos el ultimo elemento
      return this.getPrestamos().get(longitud - 1);
    }
    
    return null;
  }

  /** Agrega un prestamo a la coleccion
    * 
    * @param  pPrestamo  Nuevo prestamo del libro
    *
    * @return  Exito de la operacion
    */
  public boolean addPrestamo (Prestamo pPrestamo) {
    return this.getPrestamos().add(pPrestamo);
  }

  /** Quita un prestamo de la coleccion
    * 
    * @param  pPrestamo  Prestamo del libro a quitar
    *
    * @return  Exito de la operacion
    */
  public boolean removePrestamo (Prestamo pPrestamo) {
    return this.getPrestamos().remove(pPrestamo);
  }

  /** Retorna si el libro se encuentra en prestamo
    * 
    * @return  Se encuentra en prestamo
    */
  public boolean estaPrestado () {
    Prestamo ultimo = this.getUltimoPrestamo();

    if (ultimo != null) {
      if (ultimo.getFechaDevolucion() == null) {
        return true;
      }
    }

    return false;
  }

  /** Retorna los datos del libro en una cadena formateada
    * 
    * @return  Informacion del libro
    */
  public String toString () {
    return this.getTitulo() + " (" + String.valueOf(this.getEdicion()) +
      "° Edicion) - " + this.getAutor();
  }

}
