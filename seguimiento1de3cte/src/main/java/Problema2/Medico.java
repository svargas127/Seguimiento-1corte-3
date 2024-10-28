package Problema2;// Medico.java
import java.util.concurrent.atomic.AtomicInteger;

public class Medico implements Runnable {
    private String nombre;
    private AtomicInteger pacientesAtendidos;
    private boolean enFuncionamiento;

    public Medico(String nombre) {
        this.nombre = nombre;
        this.pacientesAtendidos = new AtomicInteger(0);
        this.enFuncionamiento = true;
    }

    public void detener() {
        enFuncionamiento = false;
    }

    public int getPacientesAtendidos() {
        return pacientesAtendidos.get();
    }

    @Override
    public void run() {
        while (enFuncionamiento) {
            Paciente paciente = new Paciente();
            try {
                // Simulamos el tiempo de atención del paciente
                Thread.sleep(paciente.getTiempoAtencion());
            } catch (InterruptedException e) {
                System.out.println(nombre + ": Atención interrumpida.");
                Thread.currentThread().interrupt();
                break;
            }

            // Incrementamos el contador de pacientes atendidos
            pacientesAtendidos.incrementAndGet();
            System.out.println(nombre + ": Paciente atendido. Total atendidos: " + getPacientesAtendidos());
        }
        System.out.println(nombre + ": Simulación detenida.");
    }
}
