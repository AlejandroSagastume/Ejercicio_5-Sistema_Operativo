import java.util.Objects;
public abstract class Proceso {
    
    protected int pid;
    protected String nombre;
    protected EstadoProceso estado;
    protected long tiempoCreacion;
    
    public Proceso(int pid, String nombre) {
        this.pid = pid;
        this.nombre = nombre;
        this.estado = EstadoProceso.NUEVO;
        this.tiempoCreacion = System.currentTimeMillis();
    }
    
    public abstract void ejecutar();
    
    public int getPid() {
        return pid;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public EstadoProceso getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoProceso estado) {
        this.estado = estado;
    }
    
    public long getTiempoCreacion() {
        return tiempoCreacion;
    }
    
    public String mostrarInformacion() {
        return String.format("PID: %d | Nombre: %s | Estado: %s", 
            pid, nombre, estado);
    }
    
    @Override
    public String toString() {
        return String.format("Proceso[PID=%d, nombre='%s', estado=%s]", 
            pid, nombre, estado);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Proceso proceso = (Proceso) obj;
        return pid == proceso.pid;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }
}