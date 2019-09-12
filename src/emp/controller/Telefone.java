/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.controller;

/**
 *
 * @author L
 */
public class Telefone {
    private int ddd;
    private String numero;

    private int pk_telefone;
    private int fk;

    public Telefone(int ddd, String numero, int pk_telefone, int fk) {
        this.ddd = ddd;
        this.numero = numero;
        this.pk_telefone = pk_telefone;
        this.fk = fk;
    }

    public Telefone(int ddd, String numero, int fk) {
        this.ddd = ddd;
        this.numero = numero;
        this.fk = fk;
    }

    public Telefone(int ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    public Telefone() {
    }
    
    

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getPk_telefone() {
        return pk_telefone;
    }

    public void setPk_telefone(int pk_telefone) {
        this.pk_telefone = pk_telefone;
    }

    public int getFk() {
        return fk;
    }

    public void setFk(int fk) {
        this.fk = fk;
    }

    @Override
    public String toString() {
        return "Telefone{" + "ddd=" + ddd + ", numero=" + numero + ", pk_telefone=" + pk_telefone + ", fk=" + fk + '}';
    }

    public void print(){
        System.out.println(this);
    }
    
}
