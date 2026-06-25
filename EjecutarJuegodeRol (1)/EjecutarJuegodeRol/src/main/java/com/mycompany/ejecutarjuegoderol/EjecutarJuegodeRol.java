/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejecutarjuegoderol;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author SebastianCodena
 */
public class EjecutarJuegodeRol {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Personajes> coliseo = new ArrayList<>();

        coliseo.add(new Guerrero("Marcus el Protector"));
        coliseo.add(new Mago("Veigar el Arcano"));
        coliseo.add(new Arquero("Robin de los Bosques"));

        Personajes jugador1 = null;
        Personajes jugador2 = null;
        boolean seleccionValida = false;

        while (!seleccionValida) {
            System.out.println("=============================================");
            System.out.println("    ️ BIENVENIDO AL COLISEO DE COMBATE POO ️");
            System.out.println("=============================================");
            
            System.out.println("Personajes disponibles para luchar:");
            for (int i = 0; i < coliseo.size(); i++) {
                System.out.println((i + 1) + ". " + coliseo.get(i).getNombre() + " (" + coliseo.get(i).getClass().getSimpleName() + ")");
            }

            System.out.print("\nSelecciona el numero del Jugador 1 (Atacante): ");
            int p1Index = scanner.nextInt() - 1;

            System.out.print("Selecciona el numero del Jugador 2 (Defensor): ");
            int p2Index = scanner.nextInt() - 1;

            if (p1Index < 0 || p1Index >= coliseo.size() || p2Index < 0 || p2Index >= coliseo.size()) {
                System.out.println("\n Error: Seleccion fuera de rango. ¡Intentalo de nuevo!\n");
                continue; 
            }

            if (p1Index == p2Index) {
                System.out.println("\n Error: Un personaje no puede pelear contra si mismo. Elige dos distintos.\n");
                continue; 
            }

            jugador1 = coliseo.get(p1Index);
            jugador2 = coliseo.get(p2Index);
            seleccionValida = true; 
        }

        System.out.println("\n ¡EL DUELO HA SIDO PACTADO! ");
        System.out.println(jugador1.getNombre() + " VS " + jugador2.getNombre());
        System.out.println("---------------------------------------------");

        int turno = 1;

        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            System.out.println("\n=================== TURNO " + turno + " ===================");

            System.out.println(" TURNO DE: " + jugador1.getNombre() + " (HP: " + jugador1.getHp() + " | " + jugador1.getTipoRecurso() + ": " + jugador1.getRecurso() + ")");
            jugador1.mostrarMenuHabilidades();
            System.out.print("Selecciona tu accion (1-4): ");
            int opcion1 = scanner.nextInt();
            jugador1.usarHabilidad(jugador2, opcion1);

            if (jugador2.estaVivo()) {
                System.out.println("\n---------------------------------------------");
                System.out.println(" TURNO DE: " + jugador2.getNombre() + " (HP: " + jugador2.getHp() + " | " + jugador2.getTipoRecurso() + ": " + jugador2.getRecurso() + ")");
                jugador2.mostrarMenuHabilidades();
                System.out.print("Selecciona tu acción (1-4): ");
                int opcion2 = scanner.nextInt();
                jugador2.usarHabilidad(jugador1, opcion2);
            }

            jugador1.actualizarTurnosCooldown();
            jugador2.actualizarTurnosCooldown();

            turno++;
            try { Thread.sleep(1000); } catch (InterruptedException e) { }
        }

        System.out.println("\n=============================================");
        System.out.println(" ¡EL COMBATE HA TERMINADO EN EL TURNO " + (turno - 1) + "! ");
        System.out.println("=============================================");
        
        if (jugador1.estaVivo()) {
            System.out.println(" ¡EL GANADOR INDISCUTIBLE ES: " + jugador1.getNombre().toUpperCase() + "! ");
        } else {
            System.out.println(" ¡EL GANADOR INDISCUTIBLE ES: " + jugador2.getNombre().toUpperCase() + "! ");
        }
        System.out.println("=============================================");
        
        scanner.close();
    }
}