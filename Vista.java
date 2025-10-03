import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Vista {
    
    private Scanner scanner;
    
    public Vista() {
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        mostrarSeparador();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║   SIMULADOR DE PROCESOS DE SISTEMA OPERATIVO      ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("  [1] Crear Proceso CPU");
        System.out.println("  [2] Crear Proceso I/O");
        System.out.println("  [3] Crear Proceso Daemon");
        System.out.println("  [4] Ejecutar todos los procesos");
        System.out.println("  [5] Listar procesos registrados");
        System.out.println("  [6] Buscar proceso por PID");
        System.out.println("  [7] Eliminar proceso");
        System.out.println("  [8] Mostrar estadísticas");
        System.out.println("  [9] Limpiar procesos terminados");
        System.out.println("  [0] Salir");
        System.out.println();
        mostrarSeparador();
    }
    
    public int leerOpcion() {
        int opcion = -1;
        boolean valido = false;
        
        do {
            try {
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                if (opcion >= 0 && opcion <= 9) {
                    valido = true;
                } else {
                    mostrarError("Opción inválida. Ingrese un número entre 0 y 9.");
                }
            } catch (InputMismatchException e) {
                mostrarError("Entrada inválida. Ingrese un número.");
                scanner.nextLine();
            }
        } while (!valido);
        
        return opcion;
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println("ℹ " + mensaje);
    }
    
    public void mostrarError(String mensaje) {
        System.out.println("✗ ERROR: " + mensaje);
    }
    
    public void mostrarExito(String mensaje) {
        System.out.println("✓ " + mensaje);
    }
    
    public String leerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    public int leerEntero(String prompt) {
        int numero = 0;
        boolean valido = false;
        
        do {
            try {
                System.out.print(prompt);
                numero = scanner.nextInt();
                scanner.nextLine();
                valido = true;
            } catch (InputMismatchException e) {
                mostrarError("Debe ingresar un número entero.");
                scanner.nextLine();
            }
        } while (!valido);
        
        return numero;
    }
    
    public void mostrarProceso(Proceso proceso) {
        if (proceso == null) {
            mostrarError("Proceso no encontrado.");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║              INFORMACIÓN DEL PROCESO               ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println(proceso.toString());
        System.out.println();
    }
    
    public void mostrarListaProcesos(List<Proceso> procesos) {
        if (procesos == null || procesos.isEmpty()) {
            mostrarMensaje("No hay procesos registrados en el sistema.");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                        PROCESOS REGISTRADOS                                ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-5s │ %-25s │ %-15s │ %-10s ║%n", "PID", "NOMBRE", "TIPO", "ESTADO");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        
        for (Proceso proceso : procesos) {
            String tipo = proceso.getClass().getSimpleName();
            System.out.printf("║ %-5d │ %-25s │ %-15s │ %-10s ║%n",
                proceso.getPid(),
                truncar(proceso.getNombre(), 25),
                tipo,
                proceso.getEstado()
            );
        }
        
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("Total de procesos: " + procesos.size());
        System.out.println();
    }
    
    public void mostrarEstadisticas(Map<String, Integer> estadisticas) {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║              ESTADÍSTICAS DEL SISTEMA              ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.printf("║  Procesos CPU:    %-10d                     ║%n", estadisticas.get("CPU"));
        System.out.printf("║  Procesos I/O:    %-10d                     ║%n", estadisticas.get("I/O"));
        System.out.printf("║  Procesos Daemon: %-10d                     ║%n", estadisticas.get("Daemon"));
        System.out.println("╠════════════════════════════════════════════════════╣");
        int total = estadisticas.values().stream().mapToInt(Integer::intValue).sum();
        System.out.printf("║  TOTAL:           %-10d                     ║%n", total);
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    public void mostrarSeparador() {
        System.out.println("────────────────────────────────────────────────────────");
    }
    
    public void mostrarDespedida() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║    Gracias por usar el Simulador de Procesos      ║");
        System.out.println("║         Sistema finalizado correctamente           ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
    }
    
    public void cerrar() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    private String truncar(String texto, int longitud) {
        if (texto == null) return "";
        if (texto.length() <= longitud) return texto;
        return texto.substring(0, longitud - 3) + "...";
    }
    
    public void pausar() {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}