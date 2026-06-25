/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejecutarjuegoderol;

/**
 *
 * @author SebastianCodena
 */
public class Guerrero extends Personajes{

    public Guerrero(String nombre) {
        super(nombre, 300, 40, 100); 
    }

    @Override
    public void mostrarMenuHabilidades() {
        System.out.println("\n--- Habilidades de " + nombre + " (Furia: " + recurso + ") ---");
        System.out.println("1. Golpe de Escudo [Danio: 60 | Costo: 10 Furia] - CD: " + cooldowns[0]);
        System.out.println("2. Torbellino de Acero [Danio: 95 | Costo: 30 Furia] - CD: " + cooldowns[1]);
        System.out.println("3. Ejecucion Devastadora [Danio: 150 | Costo: 60 Furia] - CD: " + cooldowns[2]);
        System.out.println("4. [ESPECIAL] Ira del Coloso [Danio: 220 | Costo: 90 Furia] - CD: " + cooldowns[3]);
    }

    @Override
    public void usarHabilidad(Personajes enemigo, int opcion) {
        String nombreHabilidad = "";
        int dañoBase = 0;
        int costo = 0;
        int indiceCD = opcion - 1;

        switch (opcion) {
            case 1:
                nombreHabilidad = "Golpe de Escudo";
                dañoBase = 60;
                costo = 10;
                break;
            case 2:
                nombreHabilidad = "Torbellino de Acero";
                dañoBase = 95;
                costo = 30;
                break;
            case 3:
                nombreHabilidad = "Ejecucion Devastadora";
                dañoBase = 150;
                costo = 60;
                break;
            case 4:
                nombreHabilidad = "Ira del Coloso (Especial)";
                dañoBase = 220;
                costo = 90;
                break;
            default:
                System.out.println(" Opción invalida. ¡Fallas el turno por indeciso!");
                return;
        }

        if (this.cooldowns[indiceCD] > 0) {
            System.out.println(" ¡" + nombreHabilidad + " esta en Cooldown! Faltan " + this.cooldowns[indiceCD] + " turnos.");
            return;
        }

        try {
            verificarRecurso(costo);
            this.recurso -= costo;
            System.out.println("\n️ " + nombre + " usa [" + nombreHabilidad + "] contra " + enemigo.getNombre() + "!");
            enemigo.recibirDaño(dañoBase);

            if (opcion == 2) this.cooldowns[1] = 1;
            if (opcion == 3) this.cooldowns[2] = 2;
            if (opcion == 4) this.cooldowns[3] = 4;

        } catch (Exception e) {
            System.out.println(" ERROR DE FURIA: " + e.getMessage());
        }
    }

    @Override
    public String getTipoRecurso() { return "Furia"; }
}