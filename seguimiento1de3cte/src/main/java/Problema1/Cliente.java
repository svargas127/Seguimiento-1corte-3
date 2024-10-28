package Problema1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cliente {
    private List<Articulo> cesta;
    private int tiempoProcesamiento;

    public Cliente() {
        cesta = new ArrayList<>();
        Random random = new Random();


        int cantidadArticulos = 1 + random.nextInt(5);
        for (int i = 0; i < cantidadArticulos; i++) {
            Articulo articulo = Articulo.values()[random.nextInt(Articulo.values().length)];
            cesta.add(articulo);
        }


        tiempoProcesamiento = 1000 + random.nextInt(4000);
    }

    public List<Articulo> getCesta() {
        return cesta;
    }

    public int getTiempoProcesamiento() {
        return tiempoProcesamiento;
    }

    // Calcula el total de la compra del cliente
    public double calcularTotal() {
        return cesta.stream().mapToDouble(Articulo::getPrecio).sum();
    }
}
