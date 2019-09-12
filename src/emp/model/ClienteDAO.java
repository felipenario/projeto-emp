/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Felipe Nário
 */
public class ClienteDAO {
     public static int create(Cliente c) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "INSERT INTO cliente(nascimento, endereco, telefones) VALUES (?, ?, ?)",
                 PreparedStatement.RETURN_GENERATED_KEYS);
        
        stm.setString(1, c.getNascimento().toString());
        stm.setString(2, c.getEndereco().toString());
        stm.setString(3, c.getTelefones().toString());
        
        stm.execute();
        
        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();
        
        //configura a chave primaria gerada no objeto telefone
        c.setPk_cliente(pkset.getInt(1));
       
        return pkset.getInt(1);
    }
    
    public static Cliente retreave(int pk_cliente) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "SELECT * FROM CLIENTE WHERE PK_CLIENTE=?"
                );
        
        stm.setInt(1, pk_cliente);
        
        stm.executeQuery();
                     
        ResultSet rs = stm.getResultSet();
        
        if (rs.next()){
            return new Cliente(                   
              rs.getString("nascimento"),
              rs.getString("endereco"),
              rs.getString("telefones")
            );            
        } else{
            throw new RuntimeException("Cliente não existe");                  
        }
    }
    
}
