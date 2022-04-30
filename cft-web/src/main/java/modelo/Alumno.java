package modelo;

public class Alumno {
	private int id;
	private String nombre;
	private String carrera;
	
	public Alumno() {
		
	}
	
	public Alumno(String nombre, String carrera) {
		//CONSTRUTOR QUE SOLO ADMITE NOMBRE Y CARRERA
		this.nombre = nombre;
		this.carrera = carrera;
	}

	public Alumno(int id, String nombre, String carrera) {
	
		this.id = id;
		this.nombre = nombre;
		this.carrera = carrera;
	}
	//GETTER AND SETTER
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
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	
	

}
