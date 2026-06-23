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
private int tiempoEspera = 0;
    public Guerrero(String nombre) {
        super(nombre, 300, 40, 100); 
    }

    @Override
    public void mostrarMenuHabilidades() {
        System.out.println("\n--- Habilidades de " + nombre + " (Furia: " + recurso + ") ---");
        System.out.println("1. Golpe de Escudo         [Daño: 60  | Costo: 10 Furia]");
        System.out.println("2. Torbellino de Acero     [Daño: 95  | Costo: 30 Furia]  -> CD: " + cooldowns[1]);
        System.out.println("3. Ejecución Devastadora   [Daño: 150 | Costo: 60 Furia]  -> CD: " + cooldowns[2]);
        System.out.println("4. 🌟 ¡IRA DEL TITÁN SUPER!  [Daño: 280 | Costo: 100 Furia] -> CD: " + cooldowns[3] + " (Turno 4+)");
    }

   @Override
    public void usarHabilidad(Personajes enemigo, int opcion) throws RequisitosInsuficientesException {
        String nombreHabilidad = "";
        int dañoBase = 0;
        int costo = 0;
        int tiempoEspera = 0;

        switch (opcion) {
            case 1:
                nombreHabilidad = "Golpe de Escudo";
                dañoBase = 60;
                costo = 10;
                tiempoEspera = 0; 
                break;
            case 2:
                nombreHabilidad = "Torbellino de Acero";
                dañoBase = 95;
                costo = 30;
                tiempoEspera = 2; 
                break;
            case 3:
                nombreHabilidad = "Ejecución Devastadora";
                dañoBase = 150;
                costo = 60;
                tiempoEspera = 2; 
                break;
            case 4:
                nombreHabilidad = "¡IRA DEL TITÁN SUPER!";
                dañoBase = 280;
                costo = 100;
                tiempoEspera = 4; 
                break;
            default:
                System.out.println("❌ Opción inválida. ¡Fallas el turno!");
                return;
        }

        // UN SOLO BLOQUE TRY-CATCH QUE CONTROLA TODO
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

            // EJECUCIÓN ÚNICA DEL ATAQUE
            this.recurso -= costo;
            this.cooldowns[opcion - 1] = tiempoEspera; 
            
            System.out.println("\n⚔️ " + nombre + " usa [" + nombreHabilidad + "] contra " + enemigo.getNombre() + "!");
            enemigo.recibirDaño(dañoBase);

        } catch (RequisitosInsuficientesException e) {
            System.out.println(e.getMessage());
            System.out.println("💡 Intenta usar una habilidad básica (Opción 1) para no perder tu turno.");
        }
    }
    

    @Override
    public String getTipoRecurso() { return "Furia"; }
}