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

    public Personajes(String nombre, int hp, int defensa, int recurso) {
        this.nombre = nombre;
        this.hp = hp;
        this.maxHp = hp;
        this.defensa = defensa;
        this.recurso = recurso;
    }

    // 🚀 EL MÉTODO ABSTRACTO: Recibe al rival y el número de opción del menú
    public abstract void usarHabilidad(Personajes enemigo, int opcion);
    
    // Método abstracto para mostrar el menú personalizado de cada uno
    public abstract void mostrarMenuHabilidades();
    public abstract String getTipoRecurso();

    // Método común para procesar el daño (Ataque - Defensa)
    public void recibirDaño(int dañoAtaque) {
        if (dañoAtaque <= 0) return;

        int dañoReal = dañoAtaque - this.defensa;
        if (dañoReal < 0) {
            dañoReal = 0; // La defensa absorbió todo
        }

        this.hp = Math.max(0, this.hp - dañoReal);
        System.out.println( nombre + " (Defensa: " + this.defensa + ") mitiga el golpe.");
        System.out.println(" Daño real recibido: " + dañoReal + " | HP restante: " + this.hp + "/" + this.maxHp);
    }

    public boolean estaVivo() { return this.hp > 0; }
    public String getNombre() { return nombre; }
    public int getHp() { return hp; }
    public int getRecurso() { return recurso; }
}