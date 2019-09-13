/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Felipe Nário
 */
public class EnderecoDAO {
    
    public static int create(Endereco e) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "INSERT INTO endereco(logradouro, numero, complemento, bairro, cidade, estado, cep, fk_cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                 PreparedStatement.RETURN_GENERATED_KEYS);
        
        
        stm.setString(1, e.getLogradouro());
        stm.setString(2, e.getNumero());
        stm.setString(3, e.getComplemento());
        stm.setString(4, e.getBairro());
        stm.setString(5, e.getCidade());
        stm.setString(6, e.getEstado());
        stm.setString(7, e.getCep());
        stm.setInt(8, e.getFk_cliente());
        
        stm.execute();
        
        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();
        
        
        //configura a chave primaria gerada no objeto telefone
        e.setPk_endereco(pkset.getInt(1));
       
        return pkset.getInt(1);
    }
    
    public static Endereco retreave(int pk_endereco) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "SELECT * FROM ENDERECO WHERE PK_ENDERECO=?"
                );
        
        stm.setInt(1, pk_endereco);
        
        stm.executeQuery();
                     
        ResultSet rs = stm.getResultSet();
        
        if (rs.next()){
            return new Endereco(                   
              rs.getString("logradouro"),
              rs.getString("numero"),
              rs.getString("complemento"),
              rs.getString("bairro"),
              rs.getString("cidade"),
              rs.getString("estado"),
              rs.getString("cep")      
            );            
        } else{
            throw new RuntimeException("Endereço não existe");                  
        }
    }
    
    /*public static ArrayList<Telefone> retreaveall(int fk_cliente) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        ResultSet rs = conn.createStatement().
                executeQuery("SELECT * FROM TELEFONE WHERE FK_CLIENTE ="+fk_cliente);
        
        ArrayList<Telefone> telefones = new ArrayList<>();
        
        while(rs.next()){
            telefones.add(new Telefone(rs.getInt("ddd"), 
                    rs.getString("numero"), 
                    rs.getInt("pk_telefone"), 
                    fk_cliente));
        }

       return telefones;
    }
    
    public static void delete(Telefone t) throws SQLException{
        
         Connection conn = DBConnection.getConnection();
         if(t.getPk_telefone() != 0){
             conn.createStatement().execute("DELETE FROM TELEFONE WHERE PK_TELEFONE =" + t.getPk_telefone());
             
             
         }else throw new RuntimeException("Telefone não existe");
        
        
    }
    
    public static void update (Telefone t) throws SQLException{
        
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "UPDATE telefone SET DDD = ? WHERE PK_TELEFONE = ?");
        
        stm.setInt(1, t.getDdd());
        stm.setString(2, t.getNumero());
        stm.setInt(3, t.getPk_telefone());
        
        stm.execute();
    }
    */
}
