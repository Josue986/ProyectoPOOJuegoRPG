/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejecutarjuegoderol;

/**
 *
 * @author SebastianCodena
 */
public class Mago extends Personajes {
   public Mago(String nombre) {
        super(nombre, 200, 15, 150); // HP, Defensa, Maná
    }

    @Override
    public void mostrarMenuHabilidades() {
        System.out.println("\n--- Habilidades de " + nombre + " (Maná: " + recurso + ") ---");
        System.out.println("1. Proyectil Magico [Daño: 50 | Costo: 0 Mana]");
        System.out.println("2. Rayo de Escarcha [Daño: 100 | Costo: 40 Mana]");
        System.out.println("3. Explosión Piroclástica [Daño: 180 | Costo: 80 Mana]");
    }

    @Override
    public void usarHabilidad(Personajes enemigo, int opcion) {
        String nombreHabilidad = "";
        int dañoBase = 0;
        int costo = 0;

        switch (opcion) {
            case 1:
                nombreHabilidad = "Proyectil Magico";
                dañoBase = 50;
                costo = 0;
                break;
            case 2:
                nombreHabilidad = "Rayo de Escarcha";
                dañoBase = 100;
                costo = 40;
                break;
            case 3:
                nombreHabilidad = "Explosión Piroclastica";
                dañoBase = 180;
                costo = 80;
                break;
            default:
                System.out.println(" Opción invalida. ¡Fallas el turno!");
                return;
        }

        if (this.recurso >= costo) {
            this.recurso -= costo;
            System.out.println("\n " + nombre + " lanza [" + nombreHabilidad + "] a " + enemigo.getNombre() + "!");
            enemigo.recibirDaño(dañoBase);
        } else {
            System.out.println(" ¡No tienes suficiente Mana para " + nombreHabilidad + "!");
        }
    }

    @Override
    public String getTipoRecurso() { return "Mana"; }
}