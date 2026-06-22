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
    
    protected ArrayList<Objeto> inventario;
    protected Arma armaEquipada;
    protected Armadura armaduraEquipada;

    public Personajes(String nombre, int hp, int defensa, int recurso) {
        this.nombre = nombre;
        this.hp = hp;
        this.maxHp = hp;
        this.defensa = defensa;
        this.recurso = recurso;
        this.inventario = new ArrayList<>();
    }

    // 🚀 EL MÉTODO ABSTRACTO: Recibe al rival y el número de opción del menú
    public abstract void usarHabilidad(Personajes enemigo, int opcion);
    
    // Método abstracto para mostrar el menú personalizado de cada uno
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
    
    // Método común para procesar el daño (Ataque - Defensa)
    public void recibirDaño(int dañoAtaque) {
        if (dañoAtaque <= 0) return;
        
        int defensaActual = calcularDefensaTotal(); // Considera la armadura equipada
        int dañoReal = dañoAtaque - defensaActual;
        if (dañoReal < 0) {
            dañoReal = 0; // La defensa absorbió todo
        }

        this.hp = Math.max(0, this.hp - dañoReal);
        System.out.println( nombre + " (Defensa: " + this.defensa + ") mitiga el golpe.");
        System.out.println(" Daño real recibido: " + dañoReal + " | HP restante: " + this.hp + "/" + this.maxHp);
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
}