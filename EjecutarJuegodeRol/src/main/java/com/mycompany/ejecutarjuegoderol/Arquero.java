/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejecutarjuegoderol;

/**
 *
 * @author SebastianCodena
 */
public class Arquero extends Personajes {
    public Arquero(String nombre) {
        super(nombre, 240, 25, 120); // HP: 240, Defensa: 25, Energía: 120
    }

    @Override
    public void mostrarMenuHabilidades() {
        System.out.println("\n--- Habilidades de " + nombre + " (Energia: " + recurso + ") ---");
        System.out.println("1. Disparo Rapido [Danno: 65 | Costo: 15 Energia]");
        System.out.println("2. Flecha Perforante [Danno: 110 | Costo: 45 Energia]");
        System.out.println("3. Lluvia de Flechas [Danno: 160 | Costo: 75 Energia]");
    }

    @Override
    public void usarHabilidad(Personajes enemigo, int opcion) {
        String nombreHabilidad = "";
        int dañoBase = 0;
        int costo = 0;

        // Definimos las habilidades del arquero directamente en el switch
        switch (opcion) {
            case 1:
                nombreHabilidad = "Disparo Rapido";
                dañoBase = 65;
                costo = 15;
                break;
            case 2:
                nombreHabilidad = "Flecha Perforante";
                dañoBase = 110;
                costo = 45;
                break;
            case 3:
                nombreHabilidad = "Lluvia de Flechas";
                dañoBase = 160;
                costo = 75;
                break;
            default:
                System.out.println("❌ Opcion inválida. ¡Fallas el turno por indeciso!");
                return;
        }

        // Validar si tiene suficiente Energía para atacar
        if (this.recurso >= costo) {
            this.recurso -= costo; // Consume la energía
            System.out.println("\n🏹 " + nombre + " apunta fijamente y lanza [" + nombreHabilidad + "] contra " + enemigo.getNombre() + "!");
            enemigo.recibirDaño(this.calcularAtaque(dañoBase));
        } else {
            System.out.println("❌ ¡No tienes suficiente Energía para usar " + nombreHabilidad + "!");
        }
    }

    @Override
    public String getTipoRecurso() { 
        return "Energía"; 
    }
}