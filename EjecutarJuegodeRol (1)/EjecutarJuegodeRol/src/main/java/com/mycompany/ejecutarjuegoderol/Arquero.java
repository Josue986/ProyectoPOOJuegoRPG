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
        super(nombre, 240, 25, 120); 
    }

    @Override
    public void mostrarMenuHabilidades() {
        System.out.println("\n--- Habilidades de " + nombre + " (Energia: " + recurso + ") ---");
        System.out.println("1. Disparo Rápido [Danio: 65 | Costo: 15 Energia] - CD: " + cooldowns[0]);
        System.out.println("2. Flecha Perforante [Danio: 110 | Costo: 45 Energia] - CD: " + cooldowns[1]);
        System.out.println("3. Lluvia de Flechas [Danio: 160 | Costo: 75 Energia] - CD: " + cooldowns[2]);
        System.out.println("4. [ESPECIAL] Flecha del Juicio [Danio: 230 | Costo: 100 Energia] - CD: " + cooldowns[3]);
    }
    
    @Override
    public void usarHabilidad(Personajes enemigo, int opcion) {
        String nombreHabilidad = "";
        int dañoBase = 0;
        int costo = 0;
        int indiceCD = opcion - 1;

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
            case 4:
                nombreHabilidad = "Flecha del Juicio (Especial)";
                dañoBase = 230;
                costo = 100;
                break;
            default:
                System.out.println(" Opcion invalida. ¡Fallas el turno por indeciso!");
                return;
        }

        if (this.cooldowns[indiceCD] > 0) {
            System.out.println(" ¡" + nombreHabilidad + " esta en Cooldown! Faltan " + this.cooldowns[indiceCD] + " turnos.");
            return;
        }

        try {
            verificarRecurso(costo);
            this.recurso -= costo; 
            System.out.println("\n🏹 " + nombre + " apunta fijamente y lanza [" + nombreHabilidad + "] contra " + enemigo.getNombre() + "!");
            
            enemigo.recibirDaño(this.calcularAtaque(dañoBase));

            if (opcion == 2) this.cooldowns[1] = 1;
            if (opcion == 3) this.cooldowns[2] = 2;
            if (opcion == 4) this.cooldowns[3] = 4;

        } catch (Exception e) {
            System.out.println(" ERROR DE ENERGIA: " + e.getMessage());
        }
    }

    @Override
    public String getTipoRecurso() { 
        return "Energía"; 
    }
}