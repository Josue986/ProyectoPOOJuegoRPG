package com.mycompany.ejecutarjuegoderol;

import java.util.ArrayList;
import java.util.Scanner;

public class EjecutarJuegodeRol {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Personajes> coliseo = new ArrayList<>();

        coliseo.add(new Guerrero("Marcus"));
        coliseo.add(new Mago("Veigar"));
        coliseo.add(new Arquero("Robin"));

        System.out.println("=== COLISEO DE COMBATE ===");

        for (int i = 0; i < coliseo.size(); i++) {
            System.out.println((i + 1) + ". " + coliseo.get(i).getNombre());
        }

        System.out.print("Jugador 1: ");
        int p1 = scanner.nextInt() - 1;

        System.out.print("Jugador 2: ");
        int p2 = scanner.nextInt() - 1;

        if (p1 == p2) {
            System.out.println("No puedes seleccionar el mismo personaje.");
            return;
        }

        Personajes j1 = coliseo.get(p1);
        Personajes j2 = coliseo.get(p2);

        int turno = 1;

        while (j1.estaVivo() && j2.estaVivo()) {

            System.out.println("\n===== TURNO " + turno + " =====");

            // Turno jugador 1
            turnoJugador(scanner, j1, j2);

            if (!j2.estaVivo()) {
                break;
            }

            turnoJugador(scanner, j2, j1);

            System.out.println("\n----- VIDAS -----");
            System.out.println(j1.getNombre() + ": " + j1.getVida());
            System.out.println(j2.getNombre() + ": " + j2.getVida());

            turno++;
        }

        System.out.println("\n===== FIN DEL COMBATE =====");

        if (j1.estaVivo()) {
            System.out.println("GANADOR: " + j1.getNombre());
        } else {
            System.out.println("GANADOR: " + j2.getNombre());
        }

        scanner.close();
    }

    public static void turnoJugador(Scanner scanner,
                                    Personajes atacante,
                                    Personajes enemigo) {

        System.out.println("\nTurno de " + atacante.getNombre());

        atacante.procesarEstados();

        if (!atacante.estaVivo()) {
            return;
        }

        if (!atacante.puedeAtacar()) {
            System.out.println(atacante.getNombre()
                    + " está congelado y pierde el turno.");
            return;
        }

        atacante.mostrarMenuHabilidades();

        System.out.print("Seleccione habilidad: ");
        int opcion = scanner.nextInt();

        atacante.usarHabilidad(enemigo, opcion);
    }
}