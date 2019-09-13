/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.model;

import emp.controller.Telefone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * a funcao desse Data Access Object e fazer o mapeamento
 * objeto-relacional do bean Telefone
 * 
 * essa e uma classe utilitaria, e nao tem a pretensao de
 * ser instaciada
 * @author L
 */
public class TelefoneDAO {

    private TelefoneDAO() {
    }
    /**
     * inserir um telefone no banco de dados
     * @param t
     * @return retorna a chave primaria gerada
     */
    public static int create(Telefone t) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "INSERT INTO telefone(fk_cliente, ddd, numero) VALUES (?, ?, ?)",
                 PreparedStatement.RETURN_GENERATED_KEYS);
        
        stm.setInt(1, t.getFk());
        stm.setInt(2, t.getDdd());
        stm.setString(3, t.getNumero());
        
        stm.execute();
        
        ResultSet pkset = stm.getGeneratedKeys();
        pkset.next();
        
        //configura a chave primaria gerada no objeto telefone
        t.setPk_telefone(pkset.getInt(1));
       
        return pkset.getInt(1);
    }
    
    public static Telefone retreave(int pk_telefone) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "SELECT * FROM TELEFONE WHERE PK_TELEFONE=?"
                );
        
        stm.setInt(1, pk_telefone);
        
        stm.executeQuery();
                     
        ResultSet rs = stm.getResultSet();
        
        if (rs.next()){
            return new Telefone(                   
              rs.getInt("ddd"),
              rs.getString("numero"),
              rs.getInt("pk_telefone"),
              rs.getInt("fk_cliente")
            );            
        } else{
            throw new RuntimeException("Telefone não existe");                  
        }
    }
    
    public static ArrayList<Telefone> retreaveall(int fk_cliente) throws SQLException{
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
    
    
    
    
    
    
}
