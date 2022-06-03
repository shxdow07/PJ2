/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasaplicacio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DAM
 */
public class Conexio_bd {
    public boolean Connectat;
    
    public void getCon(){
        this.Connectat=true;
    }

    public Connection connectarBD() throws SQLException {
            //Connexió a la base de dades
    if(Connectat=true){
        Connection conn = null;   
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gimnas","root", "");
                
            }
            catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getMessage());

            }
            return conn;
        } return null;
    }
    
    
    public void desconnexioBD() throws SQLException {
        Connectat = false;
        Connection conn = null;
        System.out.println("Desconnectat de la BD");
        
    }
}
