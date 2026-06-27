package com.mycompany.ejecutarjuegoderol;

public class Arquero extends Personajes {

    public Arquero(String nombre) {
        super(nombre, 240, 25, 120);
    }

    @Override
    public void mostrarMenuHabilidades() {
        System.out.println("\n--- Arquero ---");
        System.out.println("1. Disparo Preciso");
        System.out.println("2. Flecha Envenenada");
    }

    @Override
    public void usarHabilidad(Personajes enemigo, int opcion) {

        switch (opcion) {

            case 1:
                System.out.println(getNombre() + " usa Disparo Preciso");
                enemigo.recibirDaño(getAtaque() + 15);
                break;

            case 2:
                System.out.println(getNombre() + " lanza Flecha Envenenada");
                enemigo.agregarEstado(new Envenenado(3));
                break;

            default:
                System.out.println("Opción inválida");
        }
    }

    @Override
    public String getTipoRecurso() {
        return "Energía";
    }
}