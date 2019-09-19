package emp.controller;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author L
 */
public class Cliente {
    private int pk_cliente;
    private String nome;
    private Date nascimento;
    private Endereco endereco;
    private ArrayList<Telefone> telefones;
    private Cpf cpf;

    public Cliente(String nome, 
                   Date nascimento, 
                   Endereco endereco, 
                   String numero) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.cpf = new Cpf(numero);
    }

    public Cliente(String nome, 
                   Date nascimento, 
                   Endereco endereco, 
                   ArrayList<Telefone> telefones, 
                   String numero) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.telefones = telefones;
        this.cpf = new Cpf(numero);
    }

    public Cliente(int pk_cliente, String nome, Date nascimento, String cpf) {
        this.pk_cliente = pk_cliente;
        this.nome = nome;
        this.nascimento = nascimento;
        this.cpf = new Cpf(cpf);
    }

  
    
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(ArrayList<Telefone> telefones) {
        this.telefones = telefones;
    }

    public String getCpf() {
        return this.cpf.getNum();
    }

    public void setCpf(String numero) {
        this.cpf = new Cpf(numero);
    }
    
     

    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome + ", nascimento=" + nascimento + ", endereco=" + endereco + ", telefones=" + telefones + ", cpf=" + cpf + '}';
    }
    
    public void print(){
        System.out.println(this);
        
        this.cpf.print();
        this.endereco.print();
              
        for(Telefone aux:telefones){
            aux.print();
        }
    }

    public int setPk_cliente() {
        return pk_cliente;
        
    }
    
    
    public void setPk_cliente(int pk_cliente) {
        this.pk_cliente = pk_cliente;
    }
    
    public int getPk_cliente() {
        return pk_cliente;
    }

}
