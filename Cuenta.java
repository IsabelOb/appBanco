
package appbanco;

import java.time.LocalDate;


public class Cuenta {
     
    //Declara las propiedades de la clase Cuenta:
    
    private String nombreCliente;
    private String numCuenta;
    private float saldo;
    private LocalDate fechaCreacion;

    
    //Método constructor:
    
    public Cuenta(String numero, String cliente, float saldo){
        this.nombreCliente=cliente;
        this.numCuenta=numero;
        this.saldo=saldo;    
        fechaCreacion=LocalDate.now();
    }
    
    public Cuenta(String numero, String cliente, float saldo, LocalDate fechaCreacion){
        this.nombreCliente=cliente;
        this.numCuenta=numero;
        this.saldo=saldo;    
        this.fechaCreacion=fechaCreacion;
    }
    
    //Setters y getters:
    
    public LocalDate getFecha(){
        return fechaCreacion;
    }
    
    public void setFecha(LocalDate fecha){
        this.fechaCreacion=fecha;
    }
            
    public String getNombre(){
        return nombreCliente;
    }
    
    public void setNombre(String nombre){
        this.nombreCliente=nombre;
    }
    
    public String getCuenta(){
        return numCuenta;
    }
    
    public void setCuenta(String dni){
        this.numCuenta=dni;
    }
    
    public float getSaldo(){
        return saldo;
    }
    
    void setSaldo(float saldo){
        this.saldo=saldo;
    }
    
    //Métodos operaciones bancarias:
    
    public float Ingreso(float ingreso){
        
        if(ingreso>0){
            this.saldo = saldo + ingreso;
            return saldo;
        }
        
        return saldo; 
    }
    
    public float Reintegro(float reintegro){
        if(reintegro<=this.saldo && reintegro>0){
            this.saldo = saldo - reintegro;
            return saldo;
        }
        else if(reintegro>this.saldo){
            return saldo;
        }
        else{
            return saldo;
        }
    }
    
}

