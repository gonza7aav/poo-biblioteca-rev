import java.util.ArrayList;
import java.util.Calendar;

/** Clase responsable de la administracion de prestamos de libros a socios
  */
public class Biblioteca {
  private String nombre;
  private ArrayList<Libro> libros;
  private ArrayList<Socio> socios;

  /** Instanciacion con un nombre especifico, y sin ningun socio ni libro
    *
    * @param  pNombre  Nombre de la biblioteca
    */
  public Biblioteca (String pNombre) {
    this.setNombre(pNombre);
    this.setLibros(new ArrayList<Libro> ());
    this.setSocios(new ArrayList<Socio> ());
  }

  /** Establece el valor del parametro nombre
    * 
    * @param  pNombre  Nombre de la biblioteca
    */
  private void setNombre (String pNombre) {
    this.nombre = pNombre;
  }

  /** Establece el valor del parametro libros
    * 
    * @param  pLibros  Libros de la biblioteca
    */
  private void setLibros (ArrayList<Libro> pLibros) {
    this.libros = pLibros;
  }

  /** Establece el valor del parametro socios
    * 
    * @param  pSocios  Socios de la biblioteca
    */
  private void setSocios (ArrayList<Socio> pSocios) {
    this.socios = pSocios;
  }

  /** Retorna el valor del parametro nombre
    * 
    * @return  Nombre de la biblioteca
    */
  public String getNombre () {
    return this.nombre;
  }

  /** Retorna el valor del parametro libros
    * 
    * @return  Libros en la biblioteca
    */
  public ArrayList<Libro> getLibros () {
    return this.libros;
  }

  /** Retorna el valor del parametro socios
    *
    * @return  Socios de la biblioteca
    */
  public ArrayList<Socio> getSocios () {
    return this.socios;
  }

  /** Agrega un socio a la coleccion de socios
    * 
    * @param  pSocio  Nuevo socio de la biblioteca
    */
  public void agregarSocio (Socio pSocio) {
    this.getSocios().add(pSocio);
  }

  /** Quita a un socio especifico de la coleccion de socios.
    * En caso de que no se encuentre en ella, no hace nada
    * 
    * @param  pSocio  Socio ha remover
    */
  public void quitarSocio (Socio pSocio) {
    this.getSocios().remove(pSocio);
  }

  /** Agrega un libro a la coleccion de libros
    * 
    * @param  pLibro  Nuevo libro de la biblioteca
    */
  private void agregarLibro (Libro pLibro) {
    this.getLibros().add(pLibro);
  }

  /** Quita a un libro especifico de la coleccion de libros.
    * En caso de que no se encuentre en ella, no hace nada
    * 
    * @param  pLibro  Libro ha remover
    */
  public void quitarLibro (Libro pLibro) {
    this.getLibros().remove(pLibro);
  }

  /** Coordina la operacion de agregar un Libro.
    * Al Libro lo configura con los parametros recibidos
    *
    * @param  pTitulo  Titulo del libro
    * @param  pEdicion  Numero de edicion
    * @param  pAutor  Autor del libro
    * @param  pAnio  Anio de publicacion
    */
  public void agregarLibro (
    String pTitulo,
    int pEdicion,
    String pAutor,
    int pAnio
  ) {
    Libro libro = new Libro (pTitulo, pEdicion, pAutor, pAnio);
    this.agregarLibro(libro);
  }

  /** Coordina la operacion de agregar un Estudiante como Socio.
    * Al Estudiante lo instancia con los parametros recibidos
    *
    * @param  pDNISocio  DNI del estudiante
    * @param  pNombre  Nombre del estudiante
    * @param  pCarrera  Carrera que cursa
    */
  public void agregarSocioEstudiante (
    int pDNISocio,
    String pNombre,
    String pCarrera
  ) {
    Estudiante socioEstudiante = new Estudiante (pDNISocio, pNombre, pCarrera);
    this.agregarSocio(socioEstudiante);
  }

  /** Coordina la operacion de agregar un Docente como Socio.
    * Al Docente lo instancia con los parametros recibidos
    *
    * @param  pDNISocio  DNI del docente
    * @param  pNombre  Nombre del docente
    * @param  pArea  Area de trabajo
    */
  public void agregarSocioDocente (int pDNISocio, String pNombre, String pArea) {
    Docente socioDocente = new Docente (pDNISocio, pNombre, pArea);
    this.agregarSocio(socioDocente);
  }

  /** Retorna la cantidad de un cierto tipo de Socio
    *
    * @param  pTipoSocio  tipo de socio a contar
    *
    * @return  Numero de socios contados.
    */
  public int contarSocios (String pTipoSocio) {
    int cantidad = 0;
    for (Socio socio : this.getSocios()) {
      if (socio.getTipoSocio().equals(pTipoSocio)) {
        cantidad++;
      }
    }
    return cantidad;
  }

  /** Realiza el prestamo de un Libro a un Socio
    *
    * @param  pFechaPrestamo  fecha en que fue concretado el prestamo
    * @param  pSocio  socio involucrado
    * @param  pLibro  libro prestado
    *
    * @return  true solo si el socio tiene permitido sacar un libro
    */
  public boolean realizarPrestamo (
    Calendar pFechaPrestamo,
    Socio pSocio,
    Libro pLibro
  ) {
    if (pSocio.puedePedir() && !pLibro.estaPrestado()) {
      Prestamo nuevoPrestamo = new Prestamo (pFechaPrestamo, pSocio, pLibro);
      pSocio.addPrestamo(nuevoPrestamo);
      pLibro.addPrestamo(nuevoPrestamo);
      return true;
    }
    return false;
  }

  /** Realiza la devolucion de un Libro.
    * Se registrara la fecha actual como la de la devolucion
    *
    * @param  pLibro  libro que fue prestado
    */
  public void cancelarPrestamo (Libro pLibro) {
    Calendar hoy = Calendar.getInstance();
    if (pLibro.estaPrestado()) {
      pLibro.getUltimoPrestamo().registrarFechaDevolucion(hoy);
    }
  }

  /** Retorna los prestamos vencidos al dia de la fecha
    *
    * @return  Coleccion de prestamos vencidos
    */
  public ArrayList<Prestamo> obtenerPrestamosVencidos() {
    ArrayList<Prestamo> vencidos = new ArrayList<Prestamo> ();

    for (Libro libro : this.getLibros()) {
      if (libro.estaPrestado()) {
        if (libro.getUltimoPrestamo().estaVencido()) {
          vencidos.add(libro.getUltimoPrestamo());
        }
      }
    }
    return vencidos;
   }

  /** Devuelve una coleccion con los docentes responsables
    * 
    * @return  Coleccion de docentes responsables
    */
  public ArrayList<Docente> obtenerDocentesResponsables () {
    ArrayList<Docente> responsables = new ArrayList<Docente> ();

    for (Socio socio : this.getSocios()) {
      if (socio.getTipoSocio().equals("Docente")) {
        if (((Docente) socio).esResponsable()) {
          responsables.add((Docente) socio);
        }
      }
    }
    return responsables;
  }

  /** Devuelve el socio que tiene el Libro
    *
    * @param  pLibro  libro en cuestion
    *
    * @return  Socio que tiene el libro
    */
    public Socio buscarQuienTieneElLibro (Libro pLibro) {
      if (pLibro.estaPrestado()) {
        return pLibro.getUltimoPrestamo().getSocio();
      } 
      return null;
    }

  /** Retorna el socio que posee el dni recibido.
    *
    * @param  pDNI  dni del socio buscado
    * @return  En caso de encontrarlo, devuelve un Socio; en caso
    *          contrario retorna null.
    */
  public Socio buscarSocio (int pDNI) {
    for (Socio socio : this.getSocios()) {
      if (socio.getDNI() == pDNI) {
        return socio;
      }
    }
    return null;
  }
}
