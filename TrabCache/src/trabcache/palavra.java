/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabcache;

/**
 *
 * @author andrelise
 */
public class palavra {
    //endereçamento de 4 em 4 como é o PC 
    
    public String conteudo="0";
    public int endereco=0;
    
    public palavra(int endereco, String conteudo) {
      
       this.endereco=endereco;
       this.conteudo=conteudo;
        
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getEndereco() {
        return endereco;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }

    
    
    
}
