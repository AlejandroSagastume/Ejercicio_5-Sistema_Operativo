import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Planificador {
    
    private List<Proceso> procesos;
    private Proceso procesoActual;
    private static int contadorPID = 1;
    
    public Planificador() {
        this.procesos = new ArrayList<>();
        this.procesoActual = null;
    }
    
    public void registrarProceso(Proceso proceso) {
        if (proceso != null) {
            procesos.add(proceso);
        }
    }
    
    public void ejecutarTodos() {
        for (Proceso proceso : procesos) {
            ejecutarProceso(proceso);
        }
    }
    
    private void ejecutarProceso(Proceso proceso) {
        this.procesoActual = proceso;
        proceso.ejecutar();
        this.procesoActual = null;
    }
    
    public List<Proceso> obtenerProcesos() {
        return new ArrayList<>(procesos);
    }
    
    public Proceso obtenerProcesoPorPID(int pid) {
        for (Proceso proceso : procesos) {
            if (proceso.getPid() == pid) {
                return proceso;
            }
        }
        return null;
    }
    
    public Map<String, Integer> contarProcesosPorTipo() {
        Map<String, Integer> conteo = new HashMap<>();
        conteo.put("CPU", 0);
        conteo.put("I/O", 0);
        conteo.put("Daemon", 0);
        
        for (Proceso proceso : procesos) {
            if (proceso instanceof ProcesoCPU) {
                conteo.put("CPU", conteo.get("CPU") + 1);
            } else if (proceso instanceof ProcesoIO) {
                conteo.put("I/O", conteo.get("I/O") + 1);
            } else if (proceso instanceof ProcesoDaemon) {
                conteo.put("Daemon", conteo.get("Daemon") + 1);
            }
        }
        
        return conteo;
    }
    
    public static int generarPID() {
        return contadorPID++;
    }
    
    public boolean eliminarProceso(int pid) {
        Proceso proceso = obtenerProcesoPorPID(pid);
        if (proceso != null) {
            return procesos.remove(proceso);
        }
        return false;
    }
    
    public void limpiarProcesosTerminados() {
        procesos.removeIf(proceso -> proceso.getEstado() == EstadoProceso.TERMINADO);
    }
    
    public int obtenerTotalProcesos() {
        return procesos.size();
    }
}