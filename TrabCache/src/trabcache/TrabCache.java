/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabcache;

import java.util.Scanner;

/**
 *
 * @author andrelise
 */
public class TrabCache {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int endereco = 0;
        memory memoria = new memory();
        
        for(int i = 0;i<15;i++){
            System.out.println("Digite instrucao");
            String ins = ler.nextLine();
            memoria.inserir(endereco, ins);         
            endereco = endereco + 4;
            
        }
        
        /*
            Vamos ter um endereço de 32 bits
            4 palavras por bloco
            → 8 bytes por palavra:: bits para offset de byte = log2 8 =4
            → bits para offset de palavra = log2 4 = 2
            → bits para indice = log2 numCache
            → tag = 32 - 8 - 2 - indice
        */
        
        System.out.println("Qual o tamanho da cachhe?");
        int tamanho = ler.nextInt();
        System.out.println("E a associatividade?");
        int associatividade = ler.nextInt();
        
        
        cacheInstrucao cache1 = new cacheInstrucao(tamanho, associatividade);//int tamanho, int via
        System.out.println("Vamos executar....");
      
       // cache1.imprimir();
       cache1.inserir(0, memoria);
        cache1.inserir(16, memoria);
        cache1.especialImpressao();
        cache1.inserir(32, memoria);
        cache1.especialImpressao();
//        System.out.println("IMprimindo....");
//        cache1.imprimir();
        
       // String bloco1 = "00000000000000000000000000001100";
     //  int bloco1 = 12; 
     //  cache1.inserir(bloco1, memoria);
        
//        System.out.println("---2 ENDEREÇO---");
//        String bloco2 = "00000000000000000000000000010000";
//        cache1.inserir(bloco2, memoria);
//        
    }
    
}
