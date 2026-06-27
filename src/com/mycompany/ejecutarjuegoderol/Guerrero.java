package com.mycompany.ejecutarjuegoderol;

public class Guerrero extends Personajes {

    public Guerrero(String nombre) {
        super(nombre, 300, 35, 100);
    }

    @Override
    public void mostrarMenuHabilidades() {
        System.out.println("\n--- Guerrero ---");
        System.out.println("1. Golpe Fuerte");
        System.out.println("2. Grito de Guerra (+20 ataque)");
    }

    @Override
    public void usarHabilidad(Personajes enemigo, int opcion) {

        switch (opcion) {

            case 1:
                System.out.println(getNombre() + " usa Golpe Fuerte");
                enemigo.recibirDaño(getAtaque() + 20);
                break;

            case 2:
                System.out.println(getNombre() + " usa Grito de Guerra");
                agregarEstado(new AumentarFuerza(2));
                break;

            default:
                System.out.println("Opción inválida");
        }
    }

    @Override
    public String getTipoRecurso() {
        return "Furia";
    }
}