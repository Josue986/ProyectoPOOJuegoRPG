package com.mycompany.ejecutarjuegoderol;

import java.util.ArrayList;

/**
 *
 * @author SebastianCodena
 */
    public abstract class Personajes {
   protected String nombre;
    protected int hp;
    protected int maxHp;
    protected int defensa;
    protected int recurso;
    protected int[] cooldowns = new int[4]; 

    public Personajes(String nombre, int hp, int defensa, int recurso) {
        this.nombre = nombre;
        this.hp = hp;
        this.maxHp = hp;
        this.defensa = defensa;
        this.recurso = recurso;
        this.cooldowns[3] = 4; 
    }

    public abstract void usarHabilidad(Personajes enemigo, int opcion);
    public abstract void mostrarMenuHabilidades();
    public abstract String getTipoRecurso();

    public void actualizarTurnosCooldown() {
        for (int i = 0; i < cooldowns.length; i++) {
            if (cooldowns[i] > 0) {
                cooldowns[i]--;
            }
        }
    }

    protected void verificarRecurso(int costo) throws Exception {
        if (this.recurso < costo) {
            throw new Exception("¡No tienes suficiente " + getTipoRecurso() + " para usar esta habilidad!");
        }
    }

    public void recibirDaño(int dañoAtaque) {
        if (dañoAtaque <= 0) return;

        int dañoReal = dañoAtaque - this.defensa;
        if (dañoReal < 0) {
            dañoReal = 0; 
        }

        this.hp = Math.max(0, this.hp - dañoReal);
        System.out.println(nombre + " (Defensa: " + this.defensa + ") mitiga el golpe.");
        System.out.println(" Danio real recibido: " + dañoReal + " | HP restante: " + this.hp + "/" + this.maxHp);
    }

    public boolean estaVivo() { 
        return this.hp > 0; 
    }
    public String getNombre() { 
        return nombre; 
    }
    public int getHp() { 
        return hp;
    }
    public int getRecurso() { 
        return recurso; 
    }
    public int getCooldownHabilidad(int indice) {
        return cooldowns[indice];
    }
}