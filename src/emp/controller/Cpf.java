/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.controller;

import java.util.InputMismatchException;

/**
 *
 * @author L
 */
public class Cpf {

    private String num;

    public Cpf(String num) {
        this.setNum(num);
    }

    public String getNum() {
        return num;
    }
    /**
     * atribui a variavel de instancia o valor
     * passado por parametro. 
     * 
     * Um erro e lancado caso o cpf nao seja 
     * valido
     * 
     * O metodo e final para evitar que uma
     * subclasse o sobrescreva 'burlando' a 
     * validacao.
     * @param num o cpf
     */
    public final void setNum(String num) {
        if (this.validaCpf(num)){
           this.num = num;
        } else {
            throw 
            new RuntimeException("CPF Inválido");
        }
    }
    
    
    
    private boolean validaCpf(String numero) {

        // considera-se erro numero's formados por uma sequencia de numeros iguais
        if (numero.equals("00000000000")
                || numero.equals("11111111111")
                || numero.equals("22222222222") || numero.equals("33333333333")
                || numero.equals("44444444444") || numero.equals("55555555555")
                || numero.equals("66666666666") || numero.equals("77777777777")
                || numero.equals("88888888888") || numero.equals("99999999999")
                || (numero.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do numero em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0         
                // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (numero.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (numero.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == numero.charAt(9)) && (dig11 == numero.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    @Override
    public String toString() {
        return "Cpf{" + "numero=" + num + '}';
    }
    
    public void print(){
        System.out.println(this);
    }
}
