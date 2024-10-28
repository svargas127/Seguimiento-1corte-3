package Problema2;

import java.util.Random;

public class Paciente {
    private int tiempoAtencion;

    public Paciente() {
        Random random = new Random();
        // Tiempo de atención aleatorio entre 2 y 6 segundos (2000 a 6000 ms)
        this.tiempoAtencion = 2000 + random.nextInt(4000);
    }

    public int getTiempoAtencion(){
    return tiempoAtencion;
    }
}
