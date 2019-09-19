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

/**
 *
 * @author Felipe Nário
 */
public class EnderecoDAO {
    
    public static int create(Endereco e) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                "INSERT INTO endereco(fk_cliente, logradouro, numero, complemento, bairro, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                 PreparedStatement.RETURN_GENERATED_KEYS);
        
        stm.setInt(1, e.getFk_cliente());
        stm.setString(2, e.getLogradouro());
        stm.setString(3, e.getNumero());
        stm.setString(4, e.getComplemento());
        stm.setString(5, e.getBairro());
        stm.setString(6, e.getCidade());
        stm.setString(7, e.getEstado());
        stm.setString(8, e.getCep());
        
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
              rs.getString("cep"),
              rs.getInt("pk_endereco"),
              rs.getInt("fk_cliente")
            );            
        } else{
            throw new RuntimeException("Endereço não existe");                  
        }
    }
    
    public static Endereco retreaveall(int fk_cliente) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
       ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TELEFONE WHERE FK_CLIENTE=" + fk_cliente);
        Endereco e = null;
        if (rs.next()) {
            e = new Endereco(
                    rs.getString("logradouro"),
                    rs.getString("numero"),
                    rs.getString("complemento"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_cliente")
            );
        }
        return e;
    }
    
     public static void delete(Endereco e) throws SQLException {
        Connection conn = DBConnection.getConnection();

        if (e.getPk_endereco() != 0) {
            conn.createStatement().
                    execute("DELETE FROM endereco WHERE PK_ENDERECO=" + e.getPk_endereco());
        } else {
            throw new RuntimeException("Endereco nao existe no BD");
        }

    }
    
     public static void update(Endereco e) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement stm = conn.prepareStatement(
                "UPDATE ENDERECO SET LOGRADOURO=?, NUMERO=?, COMPLEMENTO=?, BAIRRO=?, CIDADE=?, ESTADO=?, CEP=?,PK_ENDERECO=? WHERE PK_TELEFONE=?");

  //configura as interrogações pela ordem
        stm.setString(1, e.getLogradouro());
        stm.setString(2, e.getNumero());
        stm.setString(3, e.getComplemento());
        stm.setString(4, e.getBairro());
        stm.setString(5, e.getCidade());
        stm.setString(6, e.getEstado());
        stm.setString(7, e.getCep());
        stm.setInt(8, e.getPk_endereco());

        stm.execute();
    }
   
}
