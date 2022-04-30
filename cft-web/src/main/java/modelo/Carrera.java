package modelo;

public class Carrera {
	private int id;
	private String nombre;
	private String codigo;
	
	//CONSTRUCTOR VACIO
	public Carrera() {
		
	}
	//CONSTRUCTOR QUE PERMITA SOLO DOS ATRIBUTOS
	public Carrera(String nombre, String codigo) {
		this.nombre = nombre;
		this.codigo = codigo;
	}
	//CONTROLADOR COMPLETO
	public Carrera(int id, String nombre, String codigo) {		
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
	}
	//GETTER Y SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
