package Problema1;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CajaRegistradora caja = new CajaRegistradora();
        BaseDatos baseDatos = new BaseDatos();

        Thread hiloCaja = new Thread(caja);
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("==== Supermercado ====");
            System.out.println("1. Iniciar Simulación");
            System.out.println("2. Detener Simulación y Guardar Resultados");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    if (!hiloCaja.isAlive()) {
                        hiloCaja = new Thread(caja);
                        hiloCaja.start();
                        System.out.println("Simulación iniciada...");
                    } else {
                        System.out.println("La simulación ya está en ejecución.");
                    }
                    break;
                case 2:
                    caja.detener();
                    try {
                        hiloCaja.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    baseDatos.guardarRegistro(caja.getClientesAtendidos(), caja.getTotalVentas());
                    System.out.println("Simulación detenida y resultados guardados.");
                    break;
                case 3:
                    ejecutando = false;
                    caja.detener();
                    baseDatos.cerrar();
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
