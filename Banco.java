
package appbanco;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Banco {

    private String nombreBanco;


    public Banco(String nombre){

        this.nombreBanco=nombre;

    }

    public String getNombreBanco(){

        return nombreBanco;
    }
    
    public List<Cuenta> LeerCuentas(){

        List<Cuenta> listaCuentas = new ArrayList<>();

        try
        {
            Class.forName("org.sqlite.JDBC");

            Connection cnx = DriverManager.getConnection("jdbc:sqlite:C://sqlite//cuentas.db");

            Statement stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM cuenta");

            while(rs.next()){

                String numero = rs.getString("numero");
                String nombre = rs.getString("nombre");
                float saldo = rs.getFloat("saldo");
                LocalDate fecha = rs.getDate("fechaCreacion").toLocalDate();
                
                Cuenta cuenta = new Cuenta(numero, nombre, saldo, fecha);
                listaCuentas.add(cuenta);
            }

            rs.close();
            stm.close();
            cnx.close();
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Error");
        }
        catch(SQLException ex)
        {
            System.out.println("Error");        
        }
        
        return listaCuentas;
    }

    public void InsertarCuenta(Cuenta cuenta){

        try
        {
            Class.forName("org.sqlite.JDBC");

            Connection conexion = DriverManager.getConnection("jdbc:sqlite:C://sqlite//cuentas.db");

            PreparedStatement stm = conexion.prepareStatement("INSERT INTO cuenta (numero, nombre, saldo, fechaCreacion) VALUES (?, ?, ?, ?)");

            java.sql.Date fecha = java.sql.Date.valueOf(cuenta.getFecha());
            
            stm.setString(1, cuenta.getCuenta());
            stm.setString(2, cuenta.getNombre());
            stm.setFloat(3, cuenta.getSaldo());
            stm.setDate(4, fecha);

            stm.executeUpdate();

            stm.close();
            conexion.close();
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println(ex);
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
    }
    
    public void ActualizarCuenta(Cuenta cuenta){

        try
        {
            Class.forName("org.sqlite.JDBC");

            Connection conexion = DriverManager.getConnection("jdbc:sqlite:C://sqlite//cuentas.db");

            PreparedStatement stm = conexion.prepareStatement("UPDATE cuenta SET nombre = ?, saldo = ? WHERE numero = ?");

            
            stm.setString(1, cuenta.getNombre());
            stm.setFloat(2, cuenta.getSaldo());
            stm.setString(3, cuenta.getCuenta());

            stm.executeUpdate();

            stm.close();
            conexion.close();
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println(ex);
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
    }
    
    public Cuenta ObtenerCuenta(String numero){
        
        Cuenta cuenta = null; //Declaramos la cuenta pero es null a no ser que la obtengamos correctamente
        try
        {
            Class.forName("org.sqlite.JDBC");

            Connection conexion = DriverManager.getConnection("jdbc:sqlite:C://sqlite//cuentas.db");

            PreparedStatement stm = conexion.prepareStatement("SELECT * FROM cuenta WHERE numero = ?");

            stm.setString(1, numero);  //define el parametro que le pasamos a numero
            
            ResultSet rs = stm.executeQuery();

            if(rs.next()){ //Si encuentra una linea crea la cuenta (sin añadirla a la lista) y nos la devuelve. Usamos if en lugar de while pq es un solo valor
                

                String nombre = rs.getString("nombre");
                float saldo = rs.getFloat("saldo");
                
                cuenta = new Cuenta(numero, nombre, saldo);  
                //No añade la cuenta a la lista, solo queremos verlo   
            }
            else
                System.out.println("No se ha encontrado la cuenta.");

            rs.close();
            stm.close();
            conexion.close();
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println(ex);
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        
        return cuenta;  //si encuentra la cuenta nos la devuelve, si no, es null como declaramos al principio
    }

    public void EliminarCuenta(String numero){
        
        try
        {
            Class.forName("org.sqlite.JDBC");

            Connection conexion = DriverManager.getConnection("jdbc:sqlite:C://sqlite//cuentas.db");

            PreparedStatement stm = conexion.prepareStatement("DELETE FROM cuenta WHERE numero = ?");

            stm.setString(1, numero);  //define el parametro que le pasamos a numero
            
            stm.executeUpdate();

            stm.close();
            conexion.close();
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println(ex);
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        
        
    }
    
    public void ModificarNombre(String numero, String nuevoNombre){
    
        Cuenta cuenta = ObtenerCuenta(numero);
        
        if(cuenta != null){

            cuenta.setNombre(nuevoNombre);
            ActualizarCuenta(cuenta);
        }
    }
    
    public void RealizarIngreso(String numero, float importe){
    
        Cuenta cuenta = ObtenerCuenta(numero);
        
        if(cuenta != null){

            float saldo = cuenta.Ingreso(importe);
            cuenta.setSaldo(saldo);
            ActualizarCuenta(cuenta);
        }
    }
    
    public void RealizarReintegro(String numero, float importe){
    
        Cuenta cuenta = ObtenerCuenta(numero);
        
        if(cuenta != null){

            float saldo = cuenta.Reintegro(importe);
            cuenta.setSaldo(saldo);
            ActualizarCuenta(cuenta);
        }
    }
}
