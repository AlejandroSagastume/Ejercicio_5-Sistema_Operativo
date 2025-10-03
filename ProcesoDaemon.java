public class ProcesoDaemon extends Proceso {
    
    private String servicio;
    private int intervaloEjecucion;
    private boolean persistente;
    
    public ProcesoDaemon(int pid, String nombre, String servicio, int intervalo) {
        super(pid, nombre);
        this.servicio = servicio;
        this.intervaloEjecucion = intervalo;
        this.persistente = true;
    }
    
    public ProcesoDaemon(int pid, String nombre, String servicio) {
        this(pid, nombre, servicio, 60);
    }
    
    @Override
    public void ejecutar() {
        setEstado(EstadoProceso.LISTO);
        setEstado(EstadoProceso.EJECUTANDO);
        
        try {
            ejecutarServicio();
            setEstado(EstadoProceso.TERMINADO);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            setEstado(EstadoProceso.TERMINADO);
        }
    }
    
    private void ejecutarServicio() throws InterruptedException {
        Thread.sleep(intervaloEjecucion * 100);
    }
    
    public void detener() {
        this.persistente = false;
        setEstado(EstadoProceso.TERMINADO);
    }
    
    public boolean isPersistente() {
        return persistente;
    }
    
    public String getServicio() {
        return servicio;
    }
    
    public int getIntervaloEjecucion() {
        return intervaloEjecucion;
    }
    
    public void setPersistente(boolean persistente) {
        this.persistente = persistente;
    }
    
    @Override
    public String toString() {
        return String.format(
            "ProcesoDaemon[PID=%d, nombre='%s', estado=%s, servicio='%s', intervalo=%ds, persistente=%s]",
            pid, nombre, estado, servicio, intervaloEjecucion, persistente
        );
    }
}