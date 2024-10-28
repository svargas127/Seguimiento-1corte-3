package Problema1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CajaRegistradora implements Runnable {
    private AtomicInteger clientesAtendidos;
    private AtomicReference<Double> totalVentas;
    private boolean enFuncionamiento;

    public CajaRegistradora() {
        clientesAtendidos = new AtomicInteger(0);
        totalVentas = new AtomicReference<>(0.0);
        enFuncionamiento = true;
    }

    public void detener() {
        enFuncionamiento = false;
    }

    public int getClientesAtendidos() {
        return clientesAtendidos.get();
    }

    public double getTotalVentas() {
        return totalVentas.get();
    }

    @Override
    public void run() {
        while (enFuncionamiento) {
            Cliente cliente = new Cliente();
            try {

                Thread.sleep(cliente.getTiempoProcesamiento());
            } catch (InterruptedException e) {
                System.out.println("Caja: Interrumpida durante el procesamiento.");
                Thread.currentThread().interrupt();
            }


            double totalCliente = cliente.calcularTotal();
            totalVentas.updateAndGet(v -> v + totalCliente);
            clientesAtendidos.incrementAndGet();

            System.out.println("Caja: Problema1.Cliente atendido - Total de compra: $" + totalCliente);
        }
        System.out.println("Caja: Simulación detenida.");
    }
}
