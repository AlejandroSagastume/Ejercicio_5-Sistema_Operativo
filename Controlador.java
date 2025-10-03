public class Controlador {
    
    private Planificador planificador;
    private Vista vista;
    
    public Controlador(Planificador planificador, Vista vista) {
        this.planificador = planificador;
        this.vista = vista;
    }
    
    public void iniciar() {
        int opcion;
        
        do {
            vista.mostrarMenu();
            opcion = vista.leerOpcion();
            
            procesarOpcion(opcion);
            
            if (opcion != 0) {
                vista.pausar();
            }
            
        } while (opcion != 0);
        
        salir();
    }
    
    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                crearProcesoCPU();
                break;
            case 2:
                crearProcesoIO();
                break;
            case 3:
                crearProcesoDaemon();
                break;
            case 4:
                ejecutarProcesos();
                break;
            case 5:
                listarProcesos();
                break;
            case 6:
                buscarProceso();
                break;
            case 7:
                eliminarProceso();
                break;
            case 8:
                mostrarEstadisticas();
                break;
            case 9:
                limpiarProcesosTerminados();
                break;
            case 0:
                break;
            default:
                vista.mostrarError("Opción no válida.");
        }
    }
    
    private void crearProcesoCPU() {
        vista.mostrarSeparador();
        vista.mostrarMensaje("CREAR PROCESO CPU");
        vista.mostrarSeparador();
        
        String nombre = vista.leerString("Nombre del proceso: ");
        int operaciones = vista.leerEntero("Cantidad de operaciones (ej: 100000): ");
        String tipo = vista.leerString("Tipo de procesamiento (ej: Compilación, Renderizado): ");
        
        int pid = Planificador.generarPID();
        ProcesoCPU proceso = new ProcesoCPU(pid, nombre, operaciones, tipo);
        planificador.registrarProceso(proceso);
        
        vista.mostrarExito("Proceso CPU creado exitosamente con PID: " + pid);
    }
    
    private void crearProcesoIO() {
        vista.mostrarSeparador();
        vista.mostrarMensaje("CREAR PROCESO I/O");
        vista.mostrarSeparador();
        
        String nombre = vista.leerString("Nombre del proceso: ");
        String dispositivo = vista.leerString("Dispositivo I/O (ej: Disco, Red, Teclado): ");
        int tiempoBloqueado = vista.leerEntero("Tiempo de bloqueo en ms (ej: 1000): ");
        
        int pid = Planificador.generarPID();
        ProcesoIO proceso = new ProcesoIO(pid, nombre, dispositivo, tiempoBloqueado);
        planificador.registrarProceso(proceso);
        
        vista.mostrarExito("Proceso I/O creado exitosamente con PID: " + pid);
    }
    
    private void crearProcesoDaemon() {
        vista.mostrarSeparador();
        vista.mostrarMensaje("CREAR PROCESO DAEMON");
        vista.mostrarSeparador();
        
        String nombre = vista.leerString("Nombre del daemon: ");
        String servicio = vista.leerString("Servicio que proporciona (ej: Logging, Monitoring): ");
        int intervalo = vista.leerEntero("Intervalo de ejecución en segundos (ej: 60): ");
        
        int pid = Planificador.generarPID();
        ProcesoDaemon proceso = new ProcesoDaemon(pid, nombre, servicio, intervalo);
        planificador.registrarProceso(proceso);
        
        vista.mostrarExito("Proceso Daemon creado exitosamente con PID: " + pid);
    }
    
    private void ejecutarProcesos() {
        vista.mostrarSeparador();
        vista.mostrarMensaje("EJECUTANDO TODOS LOS PROCESOS...");
        vista.mostrarSeparador();
        
        if (planificador.obtenerTotalProcesos() == 0) {
            vista.mostrarError("No hay procesos registrados para ejecutar.");
            return;
        }
        
        long inicio = System.currentTimeMillis();
        planificador.ejecutarTodos();
        long fin = System.currentTimeMillis();
        
        vista.mostrarExito("Todos los procesos han sido ejecutados.");
        vista.mostrarMensaje("Tiempo total de ejecución: " + (fin - inicio) + " ms");
    }
    
    private void listarProcesos() {
        vista.mostrarListaProcesos(planificador.obtenerProcesos());
    }
    
    private void buscarProceso() {
        vista.mostrarSeparador();
        int pid = vista.leerEntero("Ingrese el PID del proceso: ");
        
        Proceso proceso = planificador.obtenerProcesoPorPID(pid);
        vista.mostrarProceso(proceso);
    }
    
    private void eliminarProceso() {
        vista.mostrarSeparador();
        int pid = vista.leerEntero("Ingrese el PID del proceso a eliminar: ");
        
        boolean eliminado = planificador.eliminarProceso(pid);
        
        if (eliminado) {
            vista.mostrarExito("Proceso con PID " + pid + " eliminado exitosamente.");
        } else {
            vista.mostrarError("No se encontró un proceso con PID " + pid);
        }
    }
    
    private void mostrarEstadisticas() {
        vista.mostrarEstadisticas(planificador.contarProcesosPorTipo());
    }
    
    private void limpiarProcesosTerminados() {
        vista.mostrarSeparador();
        int totalAntes = planificador.obtenerTotalProcesos();
        planificador.limpiarProcesosTerminados();
        int totalDespues = planificador.obtenerTotalProcesos();
        
        int eliminados = totalAntes - totalDespues;
        vista.mostrarExito("Se eliminaron " + eliminados + " proceso(s) terminado(s).");
    }
    
    private void salir() {
        vista.mostrarDespedida();
        vista.cerrar();
    }
}