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
        super(nombre, 200, 15, 150); 
    }

    @Override
    public void mostrarMenuHabilidades() {
        System.out.println("\n--- Habilidades de " + nombre + " (Mana: " + recurso + ") ---");
        System.out.println("1. Proyectil Magico [Danio: 50 | Costo: 0 Mana] - CD: " + cooldowns[0]);
        System.out.println("2. Rayo de Escarcha [Danio: 100 | Costo: 40 Mana] - CD: " + cooldowns[1]);
        System.out.println("3. Explosion Piroclástica [Danio: 180 | Costo: 80 Mana] - CD: " + cooldowns[2]);
        System.out.println("4. [ESPECIAL] Cataclismo Estelar [Danio: 250 | Costo: 120 Mana] - CD: " + cooldowns[3]);
    }

    @Override
    public void usarHabilidad(Personajes enemigo, int opcion) {
        String nombreHabilidad = "";
        int dañoBase = 0;
        int costo = 0;
        int indiceCD = opcion - 1;

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
                nombreHabilidad = "Explosion Piroclastica";
                dañoBase = 180;
                costo = 80;
                break;
            case 4:
                nombreHabilidad = "Cataclismo Estelar (Especial)";
                dañoBase = 250;
                costo = 120;
                break;
            default:
                System.out.println(" Opcion invalida. ¡Fallas el turno!");
                return;
        }

        if (this.cooldowns[indiceCD] > 0) {
            System.out.println(" ¡" + nombreHabilidad + " esta en Cooldown! Faltan " + this.cooldowns[indiceCD] + " turnos.");
            return;
        }

        try {
            verificarRecurso(costo);
            this.recurso -= costo;
            System.out.println("\n " + nombre + " lanza [" + nombreHabilidad + "] a " + enemigo.getNombre() + "!");
            
            enemigo.recibirDaño(this.calcularAtaque(dañoBase));

            if (opcion == 2) this.cooldowns[1] = 1;
            if (opcion == 3) this.cooldowns[2] = 2;
            if (opcion == 4) this.cooldowns[3] = 4;

        } catch (Exception e) {
            System.out.println(" ERROR DE MANA: " + e.getMessage());
        }
    }

    @Override
    public String getTipoRecurso() { return "Mana"; }
}