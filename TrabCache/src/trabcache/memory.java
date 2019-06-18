
package trabcache;

import java.util.ArrayList;
import java.util.List;

public class memory {
    
      List<palavra> m = new ArrayList<palavra>();   //List<segmentos> ocupados = new ArrayList<segmentos>();
      int dadoLido=0;
      
      
       public int getDadoLido() {
            return dadoLido;
       }

       public void setDadoLido(int dadoLido) {
            this.dadoLido = dadoLido;
       }

      
      
      public palavra procurar(int endereco)
      {
          //pc mandou endereço
          int tamanho = m.size();
          
          for(int i = 0; i<tamanho; i++){
              palavra auxiliar = m.get(i);
                  if(auxiliar.endereco == endereco)
                  {
                      //encontrei a palavra solicitada pelo CONTROLE do MIPS para executar alguma coisa
                      return auxiliar;
                  }
          }
         
          palavra erro = new palavra(0, "0");
          return erro;//vai dar um print de programa invalido
          
      }
      
      public void inserir(int endereco, String conteudo){
          palavra novaPalavra = new palavra(endereco, conteudo);
          m.add(novaPalavra);
      }
      
      public void imprimir(){
          int tamanho = m.size();
          
          for(int i = 0; i< tamanho; i++){
              palavra aux = m.get(i);
              System.out.println("Minha memoria tem o seguinte conteudo na posição="+i+"="+aux.getEndereco()+";"+aux.getConteudo());
          }
      }
    
}
