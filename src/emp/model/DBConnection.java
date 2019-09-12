/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author L
 */
public class DBConnection {
    private static Connection conn;

    private DBConnection() {
    }
    
    public static Connection getConnection(){
        if (conn!=null){//se ja existe uma conexao instanciada, retorne-a
            return conn;
        }
        //caso nao, estabeleca uma conexao e retorne
        try {
            Class.forName(DBConfig.DRIVER);
            conn = DriverManager.getConnection(
                    DBConfig.URL, 
                    DBConfig.USER, 
                    DBConfig.PASSWORD);
            
            return conn;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);           
        }
        
        return null;
    }
    
    
    
    
}
