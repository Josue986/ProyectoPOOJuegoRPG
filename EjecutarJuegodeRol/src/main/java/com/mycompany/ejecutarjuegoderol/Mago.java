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

       
        System.out.println("\n--- Habilidades de " + nombre + " (Mana: " + recurso + ") ---");
        System.out.println("1. Proyectil Magico       [Danio: 50  | Costo: 0 Mana]");
        System.out.println("2. Rayo de Escarcha       [Danio: 100 | Costo: 40 Mana]  -> CD: " + cooldowns[1]);
        System.out.println("3. Explosion Piroclastica [Danio: 180 | Costo: 80 Mana]  -> CD: " + cooldowns[2]);
        System.out.println("4.  ¡JUICIO ARCANO SUPER! [Danio: 300 | Costo: 100 Mana] -> CD: " + cooldowns[3] + " (Turno 4+)");

        
    }
    @Override
    public void usarHabilidad(Personajes enemigo, int opcion) throws RequisitosInsuficientesException {
        String nombreHabilidad = "";
        int dañoBase = 0;
        int costo = 0;
        int tiempoEspera = 0;

        switch (opcion) {
            case 1:
                nombreHabilidad = "Proyectil Magico";
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
                nombreHabilidad = "Explosion Piroclastica";
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
                System.out.println(" Opcion invalida. ¡Fallas el turno!");
                return;
        }

        try {
            if (opcion >= 1 && opcion <= 4 && this.cooldowns[opcion - 1] > 0) {
                throw new RequisitosInsuficientesException(" [" + nombreHabilidad + "] esta en enfriamiento. Espera " + this.cooldowns[opcion - 1] + " turno(s).");
            }

            if (opcion == 4 && this.turnosTranscurridos < 4) {
                throw new RequisitosInsuficientesException(" No puedes usar la Super Habilidad aun. Requiere que pasen 4 turnos en combate (Llevas: " + this.turnosTranscurridos + ").");
            }

            if (this.recurso < costo) {
                throw new RequisitosInsuficientesException(" No tienes suficiente " + getTipoRecurso() + " para " + nombreHabilidad + ". Requiere: " + costo);
            }

            this.recurso -= costo;
            this.cooldowns[opcion - 1] = tiempoEspera; 
            
            System.out.println("\n " + nombre + " desata [" + nombreHabilidad + "] sobre " + enemigo.getNombre() + "!");
            enemigo.recibirDaño(this.calcularAtaque(dañoBase));

        } catch (RequisitosInsuficientesException e) {
            System.out.println(e.getMessage());
            System.out.println(" Intenta usar una habilidad basica (Opción 1) de costo 0 para no perder tu turno.");
        }
    }

    @Override
    public String getTipoRecurso() { return "Mana"; }
}