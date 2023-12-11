/*
 * @José Juan García Romero
 * @Luis Angel Rocha Ronquillo
 * @Jesús Alberto Sanchez Mendieta
 * @Isaac Misael Vazquez Albor
 * @Francisco Gamaliel Alvaro Portillo
 * 
 * */

package mx.uaemex.fi.ing_software_ii.torneos.dao.dto;

public class Torneo {

    private int id;
    private String nombre;
    private String fecha_inicio;
    private String fecha_fin;
    private String disciplina;
    private int numero_equipos;
    private int numero_grupos;
    
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
	public String getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public String getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public int getNumero_equipos() {
		return numero_equipos;
	}
	public void setNumero_equipos(int numero_equipos) {
		this.numero_equipos = numero_equipos;
	}
	public int getNumero_grupos() {
		return numero_grupos;
	}
	public void setNumero_grupos(int numero_grupos) {
		this.numero_grupos = numero_grupos;
	}
	
	@Override
	public String toString() {
		return "Torneo [id=" + id + ", nombre=" + nombre + ", fecha_inico=" + fecha_inicio + ", fecha_fin=" + fecha_fin
				+ ", disciplina=" + disciplina + ", numero_equipos=" + numero_equipos + ", numero_grupos="
				+ numero_grupos + "]";
	}
    
	
   
}
