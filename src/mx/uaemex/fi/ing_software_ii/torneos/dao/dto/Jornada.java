/*
 * @José Juan García Romero
 * @Luis Angel Rocha Ronquillo
 * 
 * */

package mx.uaemex.fi.ing_software_ii.torneos.dao.dto;

public class Jornada {

    private int id;
    private int local;
    private int visitante;
    private String fecha;
    private String hora;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getLocal() {
        return local;
    }
    public void setLocal(int local) {
        this.local = local;
    }
    public int getVisitante() {
        return visitante;
    }
    public void setVisitante(int visitante) {
        this.visitante = visitante;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Jornada [id=" + id + ", local=" + local + ", visitante=" + visitante + ", fecha=" + fecha + ", hora="
                + hora + "]";
    }
   
}
