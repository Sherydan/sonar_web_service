/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Rastreador;

import javax.jws.WebService;
import java.io.*;
import java.sql.*;
import javax.jws.WebMethod;
import javax.swing.JOptionPane;

/**
 *
 * @author Jacen
 */
@WebService()
public class servicio_gps {

    private String latitud;
    private String longitud;
    private String estado;
 
         //---------------comienzo procedimiento para insertar datos----------------
    @WebMethod
  public boolean insertarDatos(String lat,String longi,String est){

      String conexionBD="jdbc:mysql://127.0.0.1/coordenadas_gps";
      Connection conexion=null;
      boolean funciono=false;
      String con;
          try{
       // JOptionPane.showMessageDialog(null,"entro");
        Class.forName("com.mysql.jdbc.Driver");//el driver de mysql
        conexion=DriverManager.getConnection(conexionBD, "root","12345");//conexion a la base de datos
        Statement s = conexion.createStatement();
        // JOptionPane.showMessageDialog(null,no+" "+ap);
       con= "INSERT INTO DATOS VALUES (NULL,'"+lat+"','"+longi+"','" + est +"')";
        s.executeUpdate(con);

        funciono=true;
      }
      catch(Exception e){
        System.out.println("No se ha completado la peticiÃ³n...");
        }

        return funciono;
    }

    //----------------------

          //---------------comienzo procedimiento para consultar datos----------------
    @WebMethod
  public boolean consultarDatos(){
      String conexionBD="jdbc:mysql://127.0.0.1/coordenadas_gps";
      Connection conexion=null;
      boolean funciono=false;
      String con;
      String idregistro;
      idregistro="";
      ResultSet rs;
          try{
       // JOptionPane.showMessageDialog(null,"entro");
        Class.forName("com.mysql.jdbc.Driver");//el driver de mysql
        conexion=DriverManager.getConnection(conexionBD, "root","12345");//conexion a la base de datos
        Statement s = conexion.createStatement();
        // JOptionPane.showMessageDialog(null,no+" "+ap);
              con="SELECT max(id) as id from datos" ;
              rs = s.executeQuery (con); {
              while (rs.next()) {
             idregistro=rs.getString("id");
             //JOptionPane.showMessageDialog(null, idregistro);
            }
        }
        //ahora si traigo los datos de ubicacion
         con="SELECT * from datos where id='"+idregistro+"'" ;
         rs = s.executeQuery (con); {
         while (rs.next()) {
         latitud=rs.getString("latitud");
         longitud=rs.getString("longitud");
           //JOptionPane.showMessageDialog(null, "si");
            }
        }
        funciono=true;
        MostrarLatitud();
        MostrarLongitud();
      }
      catch(Exception e){
        System.out.println("No se ha completado la peticiÃ³n...");
        }

        return funciono;
    }
   @WebMethod
  public String MostrarLatitud(){
      String lati;
      lati="";
      lati=latitud;
      return lati;
    }
    @WebMethod
  public String MostrarLongitud(){
      String longi;
      longi="";
      longi=longitud;
      return longi;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "operation")
    public String operation() {
        //TODO write your implementation code here:
        return null;
    }

}
