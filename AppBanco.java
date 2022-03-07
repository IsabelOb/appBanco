
package appbanco;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;


public class AppBanco {

    static Banco banco; //se declara fuera del main para que sea accesible en toda la clase
    
    public static void main(String[] args) throws ParseException {
        
        banco = new Banco("DAW Bank");
        
        Scanner sc = new Scanner(System.in);
        
        while(true){
            
            System.out.println("¿Qué acción quiere realizar?:");
            System.out.println("1 - Crear nueva cuenta");
            System.out.println("2 - Imprimir listado de cuentas");
            System.out.println("3 - Modificar nombre de una cuenta");
            System.out.println("4 - Realizar un ingreso");
            System.out.println("5 - Realizar un reintegro");
            System.out.println("6 - Eliminar una cuenta");

            String accion = sc.nextLine();
            
            switch(accion){
            
                case "1":
                    NuevaCuenta();
                    break;
                case "2":
                    ImprimirCuentas();
                    break;
                case "3":
                    ModificarNombre();
                    break;
                case "4":
                    Ingresar();
                    break;
                case "5":
                    SacarDinero();
                    break;
                case "6":
                    Eliminar();
                    break;                  
                default:
                    return;
            }
                
        }

        
       
    }
    
    private static void ImprimirCuentas(){
    
        List<Cuenta> cuentas = banco.LeerCuentas();
        
        for(Cuenta cuenta: cuentas){
            System.out.println(cuenta.getCuenta() + " " + cuenta.getNombre() + " " + cuenta.getSaldo() + " " + cuenta.getFecha());
        }
    }
    
    public static void NuevaCuenta(){
    
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Número de cuenta:");
        String numero = sc.nextLine();
        
        System.out.println("Nombre cliente:");
        String nombre = sc.nextLine();
        
        float saldo = 0;
        
        Cuenta cuenta = new Cuenta(numero, nombre, saldo);
        banco.InsertarCuenta(cuenta);
        
    }
    
    public static void ModificarNombre(){
    
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Número de cuenta:");
        String numero = sc.nextLine();
        
        System.out.println("Nuevo nombre cliente:");
        String nombre = sc.nextLine();
               
        banco.ModificarNombre(numero, nombre);        
    }
    
    public static void Ingresar(){
    
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Número de cuenta:");
        String numero = sc.nextLine();
        
        System.out.println("Importe:");
        float importe = sc.nextFloat();
        
        banco.RealizarIngreso(numero, importe);
    }
    
    public static void SacarDinero(){
    
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Número de cuenta:");
        String numero = sc.nextLine();
        
        System.out.println("Importe:");
        float importe = sc.nextFloat();
        
        banco.RealizarReintegro(numero, importe);
    }
    
    public static void Eliminar(){
    
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Número de cuenta:");
        String numero = sc.nextLine();
               
        banco.EliminarCuenta(numero);
    }
}

