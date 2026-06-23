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
    private int tiempoEspera = 0;
   public Mago(String nombre) {
        super(nombre, 200, 15, 150); // HP, Defensa, Maná
    }

   @Override
    public void mostrarMenuHabilidades() {
       
        System.out.println("\n--- Habilidades de " + nombre + " (Maná: " + recurso + ") ---");
        System.out.println("1. Proyectil Mágico       [Daño: 50  | Costo: 0 Mana]");
        System.out.println("2. Rayo de Escarcha       [Daño: 100 | Costo: 40 Mana]  -> CD: " + cooldowns[1]);
        System.out.println("3. Explosión Piroclástica [Daño: 180 | Costo: 80 Mana]  -> CD: " + cooldowns[2]);
        System.out.println("4. 🌟 ¡JUICIO ARCANO SUPER! [Daño: 300 | Costo: 100 Mana] -> CD: " + cooldowns[3] + " (Turno 4+)");
    }
    @Override
    public void usarHabilidad(Personajes enemigo, int opcion) throws RequisitosInsuficientesException {
        String nombreHabilidad = "";
        int dañoBase = 0;
        int costo = 0;
        int tiempoEspera = 0;

        switch (opcion) {
            case 1:
                nombreHabilidad = "Proyectil Mágico";
                dañoBase = 50;
                costo = 0;
                tiempoEspera = 0; 
                break;
            case 2:
                nombreHabilidad = "Rayo de Escarcha";
                dañoBase = 100;
                costo = 40;
                tiempoEspera = 2; 
                break;
            case 3:
                nombreHabilidad = "Explosión Piroclástica";
                dañoBase = 180;
                costo = 80;
                tiempoEspera = 2; 
                break;
            case 4:
                nombreHabilidad = "¡JUICIO ARCANO SUPER!";
                dañoBase = 300;
                costo = 100;
                tiempoEspera = 4; 
                break;
            default:
                System.out.println("❌ Opción inválida. ¡Fallas el turno!");
                return;
        }

        // UN SOLO BLOQUE TRY-CATCH QUE CONTROLA TODO
        try {
            // 1. Validación de Cooldown General
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

            // EJECUCIÓN ÚNICA DEL ATAQUE
            this.recurso -= costo;
            this.cooldowns[opcion - 1] = tiempoEspera; 
            
            System.out.println("\n✨ " + nombre + " desata [" + nombreHabilidad + "] sobre " + enemigo.getNombre() + "!");
            enemigo.recibirDaño(dañoBase);

        } catch (RequisitosInsuficientesException e) {
            System.out.println(e.getMessage());
            System.out.println("💡 Intenta usar una habilidad básica (Opción 1) de costo 0 para no perder tu turno.");
        }
    }

    @Override
    public String getTipoRecurso() { return "Mana"; }
}