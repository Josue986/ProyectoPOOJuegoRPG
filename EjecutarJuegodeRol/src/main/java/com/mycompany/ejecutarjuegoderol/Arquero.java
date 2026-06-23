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
    private int tiempoEspera = 0;
    public Arquero(String nombre) {
        super(nombre, 240, 25, 120); // HP: 240, Defensa: 25, Energía: 120
    }

    @Override
    public void mostrarMenuHabilidades() {
        System.out.println("\n--- Habilidades de " + nombre + " (Energía: " + recurso + ") ---");
        System.out.println("1. Disparo Rápido          [Daño: 65  | Costo: 15 Energía]");
        System.out.println("2. Flecha Perforante       [Daño: 110 | Costo: 45 Energía] -> CD: " + cooldowns[1]);
        System.out.println("3. Lluvia de Flechas       [Daño: 160 | Costo: 75 Energía] -> CD: " + cooldowns[2]);
        System.out.println("4. 🌟 ¡TORMENTA DE SINO SUPER! [Daño: 290 | Costo: 100 Energía] -> CD: " + cooldowns[3] + " (Turno 4+)");
    
    }

    @Override
    public void usarHabilidad(Personajes enemigo, int opcion) throws RequisitosInsuficientesException {
        String nombreHabilidad = "";
        int dañoBase = 0;
        int costo = 0;
        int tiempoEspera = 0; // Aquí se guardará el cooldown del switch

        // Definimos las 4 habilidades del arquero directamente en el switch
        switch (opcion) {
            case 1:
                nombreHabilidad = "Disparo Rápido";
                dañoBase = 65;
                costo = 15;
                tiempoEspera = 0; 
                break;
            case 2:
                nombreHabilidad = "Flecha Perforante";
                dañoBase = 110;
                costo = 45;
                tiempoEspera = 2; 
                break;
            case 3:
                nombreHabilidad = "Lluvia de Flechas";
                dañoBase = 160;
                costo = 75;
                tiempoEspera = 2; 
                break;
            case 4:
                nombreHabilidad = "¡TORMENTA DE SINO SUPER!";
                dañoBase = 290;
                costo = 100;
                tiempoEspera = 4; 
                break;
            default:
                System.out.println("❌ Opción inválida. ¡Fallas el turno por indeciso!");
                return;
        }

        // UN SOLO BLOCKE TRY-CATCH QUE VALIDA Y EJECUTA
        try {
            // 1. Validación de Cooldown
            if (opcion >= 1 && opcion <= 4 && this.cooldowns[opcion - 1] > 0) {
                throw new RequisitosInsuficientesException("❌ [" + nombreHabilidad + "] está en enfriamiento. Espera " + this.cooldowns[opcion - 1] + " turno(s).");
            }
            
            // 2. Validación Especial de Súper Habilidad
            if (opcion == 4 && this.turnosTranscurridos < 4) {
                throw new RequisitosInsuficientesException("❌ No puedes usar la Súper Habilidad aún. Requiere que pasen 4 turnos en combate (Llevas: " + this.turnosTranscurridos + ").");
            }
            
            // 3. Validación de Recursos
            if (this.recurso < costo) {
                throw new RequisitosInsuficientesException("❌ No tienes suficiente " + getTipoRecurso() + " para " + nombreHabilidad + ". Requiere: " + costo);
            }

            // SI PASA TODO, SE EJECUTA UNA SOLA VEZ
            this.recurso -= costo;
            this.cooldowns[opcion - 1] = tiempoEspera; 
            
            System.out.println("\n🏹 " + nombre + " apunta fijamente y lanza [" + nombreHabilidad + "] contra " + enemigo.getNombre() + "!");
            enemigo.recibirDaño(dañoBase);

        } catch (RequisitosInsuficientesException e) {
            System.out.println(e.getMessage());
            System.out.println("💡 Intenta usar una habilidad básica (Opción 1) para no perder tu turno.");
        }
    }

    @Override
    public String getTipoRecurso() { 
        return "Energía"; 
    }
}