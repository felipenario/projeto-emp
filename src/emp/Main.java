/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp;

import emp.controller.Cliente;
import emp.controller.Endereco;
import emp.controller.Telefone;
import emp.model.ClienteDAO;
import emp.model.TelefoneDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author L
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       
       /*  ArrayList<Telefone> t1;
        try {
            t1 = TelefoneDAO.retreaveall(3);
         System.out.println(t1);            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            */
        
        

        
        Cliente c = new Cliente(
                "Robersvaldo", 
                new Date(2002, 5, 21), 
                new Endereco(
                "Rua das Bolinhas",
                "456A",
                "Qd 75, Lt 10",
                "St Bela Vista",
                "Goiânia",
                "GO",
                "74500-000"),
               "92141730104");
        
        ArrayList<Telefone> tels = new ArrayList<>();
        tels.add(new Telefone(64, "99999-8888"));
        tels.add(new Telefone(64, "99999-7777"));
        tels.add(new Telefone(64, "99999-5555"));
        tels.add(new Telefone(64, "99999-4444"));
        tels.add(new Telefone(64, "99999-3333"));
        
        c.setTelefones(tels);
        
        try {
            ClienteDAO.create(c);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
