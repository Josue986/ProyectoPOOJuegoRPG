package menuopciones3;
import java.util.Scanner;
public class MenuOpciones3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int num, valorAbs;
        byte seleccion;
        
        System.out.print("Ingrese un número entero: ");
        num = sc.nextInt();
        
        do {
            System.out.println("\nOPERACIÓN A REALIZAR\n");
            System.out.println("1. Determinar si es par");
            System.out.println("2. Determinar si es múltiplo de 3");
            System.out.println("3. Calcular valor absoluto");
            System.out.print("Elija e inngrese una opción (1-3): ");
            seleccion = sc.nextByte();
          
            
            if (seleccion < 1 || seleccion > 3) {
                System.out.println("Error opción no válida. Intente  nuevamente");
             }    
            }
           
            while(seleccion < 1 && seleccion > 3);
            switch (seleccion) {
                case 1:
                    if (num % 2 == 0) {
                        System.out.println("El número ingresado " + num + " si es un número par");
                        
                    }
                    else {
                        System.out.println("El número ingresado " + num + " no es un número par");
                    }
                    
                case 2:
                    if (num % 3 == 0) {
                        System.out.println("El número ingresado " + num + " si es múltiplo de 3");
                        
                    }
                    else {
                        System.out.println("El número ingresado " + num + " no es múltiplo de 3");
                    }
                    break;
                    
                case 3:
                    valorAbs = num;
                    if ( num < 0) {
                        valorAbs = num * -1;
                    }
                    System.out.println("El valor absoluto de " + num + " es: " + valorAbs);
                    break;               
                    
            }
    }
}
