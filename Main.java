/**
 * Clase principal que inicia la aplicación de planificación de procesos.
 * 
 * Esta clase implementa el patrón de diseño MVC (Modelo-Vista-Controlador)
 * para separar las responsabilidades de la aplicación:
 * - Modelo: Gestiona los datos y la lógica de negocio (Planificador)
 * - Vista: Maneja la interfaz de usuario y la interacción (Vista)
 * - Controlador: Coordina el Modelo y la Vista (Controlador)
 * 
 * @since 2024-06-15
 */
public class Main {
    public static void main(String[] args) {
        Planificador planificador = new Planificador();  // MODELO - Lógica de negocio
        Vista vista = new Vista();                        // VISTA - Interfaz de usuario
        Controlador controlador = new Controlador(planificador, vista); // CONTROLADOR
        controlador.iniciar();
    }
}