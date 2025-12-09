package estadnumeros4;
import java.util.Scanner;
public class EstadNumeros4 {
   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        byte num;
        int valor, suma, mayor, menor;
        int positivos, negativos;
        float promedio;
        
        suma = 0;
        mayor = 0;
        menor = 0;
        positivos = 0;
        negativos = 0;
        promedio = 0;
      
        
        System.out.print("¿Cuántos números desea ingresar? ");
        num = sc.nextByte();
        
        for (int i = 1; i <= num; i++) {
            System.out.print("Ingrese el valor " + i + ":");
            valor = sc.nextInt();
            
            if (i == 1){
                mayor = valor;
                menor = valor;
            }
            if (valor > mayor) {
                mayor = valor;
            }
            
            if (valor < menor){
                mayor = valor;
            }
            
            if (valor > 0){
                positivos++;
            }
            else if (valor < 0){
                negativos++;
            }
            suma = suma + valor;
        }
        promedio = (float) suma / num;
        
        System.out.println("\nRESULTADOS\n");
        System.out.println("La media aritmética es: " + promedio);
        System.out.println("El número mayor es: " + mayor);
        System.out.println("El número menor es: " + menor);
        System.out.println("La cantidad de números positivos: " + positivos);
        System.out.println("La cantidad de números negativos: " + negativos);
        
    }
    
}
