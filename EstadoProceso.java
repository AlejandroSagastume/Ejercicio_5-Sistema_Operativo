/**
 * Enumeración que define los posibles estados de un proceso.
 * 
 * @author AlejandroSagastume
 * @date 2025-01-03
 */
public enum EstadoProceso {
    NUEVO("Nuevo"),
    LISTO("Listo"),
    EJECUTANDO("Ejecutando"),
    BLOQUEADO("Bloqueado"),
    TERMINADO("Terminado");
    
    private final String descripcion;
    
    EstadoProceso(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
}