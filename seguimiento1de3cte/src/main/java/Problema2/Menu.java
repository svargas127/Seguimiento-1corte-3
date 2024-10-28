package Problema2;

// Menu.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Thread> hilosMedicos = new ArrayList<>();
        List<Medico> medicos = new ArrayList<>();

        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("==== Hospital - Área de Atención Médica ====");
            System.out.println("1. Iniciar Simulación de Atención");
            System.out.println("2. Detener Simulación");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    if (hilosMedicos.isEmpty()) {
                        System.out.print("Ingrese el número de médicos: ");
                        int numMedicos = scanner.nextInt();
                        for (int i = 1; i <= numMedicos; i++) {
                            Medico medico = new Medico("Médico " + i);
                            medicos.add(medico);
                            Thread hiloMedico = new Thread(medico);
                            hilosMedicos.add(hiloMedico);
                            hiloMedico.start();
                        }
                        System.out.println("Simulación iniciada con " + numMedicos + " médicos.");
                    } else {
                        System.out.println("La simulación ya está en ejecución.");
                    }
                    break;
                case 2:
                    System.out.println("Deteniendo simulación...");
                    for (Medico medico : medicos) {
                        medico.detener();
                    }
                    for (Thread hilo : hilosMedicos) {
                        try {
                            hilo.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Simulación detenida. Resultados:");
                    for (Medico medico : medicos) {
                        System.out.println(medico + " - Pacientes atendidos: " + medico.getPacientesAtendidos());
                    }
                    hilosMedicos.clear();
                    medicos.clear();
                    break;
                case 3:
                    ejecutando = false;
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
