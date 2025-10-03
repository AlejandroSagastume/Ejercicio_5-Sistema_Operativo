public class ProcesoIO extends Proceso {
    
    private String dispositivoIO;
    private int tiempoBloqueado;
    private long bytesTransferidos;
    
    public ProcesoIO(int pid, String nombre, String dispositivo, int tiempoBloqueado) {
        super(pid, nombre);
        this.dispositivoIO = dispositivo;
        this.tiempoBloqueado = tiempoBloqueado;
        this.bytesTransferidos = 0;
    }
    
    public ProcesoIO(int pid, String nombre, String dispositivo) {
        this(pid, nombre, dispositivo, 1000);
    }
    
    @Override
    public void ejecutar() {
        setEstado(EstadoProceso.LISTO);
        
        try {
            setEstado(EstadoProceso.EJECUTANDO);
            esperarDispositivo();
            transferirDatos();
            setEstado(EstadoProceso.TERMINADO);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            setEstado(EstadoProceso.TERMINADO);
        }
    }
    
    private void esperarDispositivo() throws InterruptedException {
        setEstado(EstadoProceso.BLOQUEADO);
        Thread.sleep(tiempoBloqueado);
        setEstado(EstadoProceso.EJECUTANDO);
    }
    
    private void transferirDatos() {
        this.bytesTransferidos = 1024 + (long)(Math.random() * 10 * 1024 * 1024);
    }
    
    public String getDispositivoIO() {
        return dispositivoIO;
    }
    
    public int getTiempoBloqueado() {
        return tiempoBloqueado;
    }
    
    public long getBytesTransferidos() {
        return bytesTransferidos;
    }
    
    @Override
    public String toString() {
        return String.format(
            "ProcesoIO[PID=%d, nombre='%s', estado=%s, dispositivo='%s', tiempoBloqueado=%dms, bytes=%d]",
            pid, nombre, estado, dispositivoIO, tiempoBloqueado, bytesTransferidos
        );
    }
}