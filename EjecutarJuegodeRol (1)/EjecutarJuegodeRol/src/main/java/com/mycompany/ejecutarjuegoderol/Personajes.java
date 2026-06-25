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
    protected ArrayList<Objeto> inventario;
    protected Arma armaEquipada;
    protected Armadura armaduraEquipada;
    
    public Personajes(String nombre, int hp, int defensa, int recurso) {
        this.nombre = nombre;
        this.hp = hp;
        this.maxHp = hp;
        this.defensa = defensa;
        this.recurso = recurso;
        this.cooldowns[3] = 4; 
        this.inventario = new ArrayList<>();
    }

    public abstract void usarHabilidad(Personajes enemigo, int opcion);
    public abstract void mostrarMenuHabilidades();
    public abstract String getTipoRecurso();
    
    public void recogerObjeto(Objeto objeto) {
        this.inventario.add(objeto);
        System.out.println(nombre + " guardo en el inventario: " + objeto.getNombre());
    }
    
    public void equiparObjeto(Objeto objeto) {
        if(!inventario.contains(objeto)){
            System.out.println(nombre + " no tiene " + objeto.getNombre() + " en su inventario.");
            return;
        }

        if(objeto instanceof Arma){
            this.armaEquipada = (Arma) objeto;
            System.out.println(nombre + " ha equipado el arma: " + objeto.getNombre() 
                    + " (+" + armaEquipada.getBonificadorAtaque() + " de Dano)");
        } else if(objeto instanceof Armadura){
            this.armaduraEquipada = (Armadura) objeto;
            System.out.println(nombre + " se ha equipado la armadura: " 
                    + objeto.getNombre() + " (+" + armaduraEquipada.getBonificadorDefensa() 
                    + " de Defensa)");
        }
    }
    
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

        int defensaActual = calcularDefensaTotal(); 
        int dañoReal = dañoAtaque - defensaActual;
        if (dañoReal < 0) {
            dañoReal = 0; 
        }

        this.hp = Math.max(0, this.hp - dañoReal);
        System.out.println(nombre + " (Defensa: " + this.defensa + ") mitiga el golpe.");
        System.out.println(" Danio real recibido: " + dañoReal + " | HP restante: " + this.hp + "/" + this.maxHp);
    }
    
    public int calcularAtaque(int dañoHabilidad) {
        int dañoTotal = dañoHabilidad;
        if (this.armaEquipada != null) {
            dañoTotal += this.armaEquipada.getBonificadorAtaque();
        }
        return dañoTotal;
    }

    public int calcularDefensaTotal() {
        int defensaTotal = this.defensa;
        if (this.armaduraEquipada != null) {
            defensaTotal += this.armaduraEquipada.getBonificadorDefensa();
        }
        return defensaTotal;
    }
    
    public boolean estaVivo() { return this.hp > 0; }
    public String getNombre() { return nombre; }
    public int getHp() { return hp; }
    public int getRecurso() { return recurso; }
    public int getCooldownHabilidad(int indice) { return cooldowns[indice]; }
}