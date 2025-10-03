public class ProcesoCPU extends Proceso {
    
    private int operacionesAritmeticas;
    private String tipoProcesamiento;
    private int usoCPU;
    
    public ProcesoCPU(int pid, String nombre, int operaciones, String tipo) {
        super(pid, nombre);
        this.operacionesAritmeticas = operaciones;
        this.tipoProcesamiento = tipo;
        this.usoCPU = 80 + (int)(Math.random() * 20);
    }
    
    public ProcesoCPU(int pid, String nombre, int operaciones) {
        this(pid, nombre, operaciones, "Calculo Generico");
    }
    
    @Override
    public void ejecutar() {
        setEstado(EstadoProceso.LISTO);
        
        try {
            setEstado(EstadoProceso.EJECUTANDO);
            realizarCalculo();
            setEstado(EstadoProceso.TERMINADO);
        } catch (Exception e) {
            setEstado(EstadoProceso.TERMINADO);
        }
    }
    
    private void realizarCalculo() {
        long resultado = 0;
        for (int i = 0; i < operacionesAritmeticas; i++) {
            resultado += Math.pow(i, 2) * Math.sin(i) * Math.cos(i);
            
            if (i % 10000 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    
    public int getOperacionesAritmeticas() {
        return operacionesAritmeticas;
    }
    
    public String getTipoProcesamiento() {
        return tipoProcesamiento;
    }
    
    public int getUsoCPU() {
        return usoCPU;
    }
    
    public void setUsoCPU(int usoCPU) {
        this.usoCPU = Math.max(0, Math.min(100, usoCPU));
    }
    
    @Override
    public String toString() {
        return String.format(
            "ProcesoCPU[PID=%d, nombre='%s', estado=%s, operaciones=%d, tipo='%s', usoCPU=%d%%]",
            pid, nombre, estado, operacionesAritmeticas, tipoProcesamiento, usoCPU
        );
    }
}