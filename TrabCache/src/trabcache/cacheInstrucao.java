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
public class cacheInstrucao {
    
    /*Vamos ter um endereço de 32 bits
        4 palavras por bloco
        → 8 bytes por palavra:: bits para offset de byte = log2 8 =4
        → bits para offset de palavra = log2 4 = 2
        → bits para indice = log2 numCache
        → tag = 32 - 8 - 2 - indice
    
      int val = (int) (Math.log(64)/Math.log(2));
    */
    
    public bloco [][]instrucao;
    public int offsetByte;
    public int offsetPalavra;
    public int tamIndice;
    public int tamanhoCache;
    public int numVias;
    
    
    public cacheInstrucao(int tamanho, int via) {
        
        bloco[][] aux = new bloco[tamanho][via];
        
        for(int i = 0; i<tamanho; i++){
            for(int j = 0; j<via; j++){
                aux[i][j] = new bloco();
               aux[i][j].id=j;
                aux[i][j].validade=0;
            }
        }
        
        this.tamanhoCache = tamanho;
        this.numVias=via;
        this.offsetByte =(int)  (Math.log(8)/Math.log(2));//  int val = (int) (Math.log(64)/Math.log(2));
        System.out.println("Offset de byte="+getOffsetByte());
        this.offsetPalavra =(int)  (Math.log(4)/Math.log(2));//  int val = (int) (Math.log(64)/Math.log(2));
        System.out.println("Offset de palavra="+getOffsetPalavra());
        this.tamIndice = (int) (Math.log(tamanho)/Math.log(2));
        System.out.println("Índice="+getTamIndice());
        this.instrucao = aux;
    }

    
    public int getOffsetByte() {
        return offsetByte;
    }

    public int getOffsetPalavra() {
        return offsetPalavra;
    }

    public int getTamIndice() {
        return tamIndice;
    }

    public int getTamanhoCache() {
        return tamanhoCache;
    }

    public void setTamanhoCache(int tamanhoCache) {
        this.tamanhoCache = tamanhoCache;
    }

    public int getNumVias() {
        return numVias;
    }

    
    public void inserir(int endereco, memory mem){
        
        
        int num = getOffsetByte()+getOffsetPalavra()+getTamIndice();
        int tamTag = 32 - num;
        System.out.println("Valor da tag="+tamTag);
        int auxEnd=0;
        auxEnd = endereco;
        int condicao = 0;
        
        String tag = verificaTam(conversorDecimalBinario(endereco));
        
        tag = tag.substring(0, tamTag);
        
        
        int conjunto = restoDivisao(endereco, getTamanhoCache());//vai retornar o conj aonde tenho que salvar o bloco
        System.out.println("Valor do conj="+conjunto);
        int via = encontrarVia(endereco);
        bloco aux = this.instrucao[conjunto][via];
        
            aux.setP1(mem.procurar(auxEnd));
            auxEnd = auxEnd + 4;
            aux.setP2(mem.procurar(auxEnd));
            auxEnd = auxEnd + 4;
            aux.setP3(mem.procurar(auxEnd));
            auxEnd = auxEnd + 4;
            aux.setP4(mem.procurar(auxEnd));
            aux.validade=1;
            aux.tag=Integer.parseInt(tag, 2);
            aux.via=1;
            
    }
    
    
    public void especialImpressao(){
       int conjunto = 0;
        System.out.println("via=0");
        bloco b1 = instrucao[0][0];
        palavra p1;
        palavra p2;
        palavra p3;
        palavra p4;
        
        p1=b1.getP1();
        System.out.println("Valores de p1="+p1.getEndereco()+";"+p1.getConteudo());
        p2=b1.getP2();
        System.out.println("Valores de p2="+p2.getEndereco()+";"+p2.getConteudo());
        p3=b1.getP3();
        System.out.println("Valores de p3="+p3.getEndereco()+";"+p3.getConteudo());
        p4=b1.getP4();
        System.out.println("Valores de p4="+p4.getEndereco()+";"+p4.getConteudo());
        System.out.println("Tag="+b1.getTag());
        bloco b2=instrucao[0][1];
        
        p1=b2.getP1();
        System.out.println("Valores de p1="+p1.getEndereco()+";"+p1.getConteudo());
        p2=b2.getP2();
        System.out.println("Valores de p2="+p2.getEndereco()+";"+p2.getConteudo());
        p3=b2.getP3();
        System.out.println("Valores de p3="+p3.getEndereco()+";"+p3.getConteudo());
        p4=b2.getP4();
        System.out.println("Valores de p4="+p4.getEndereco()+";"+p4.getConteudo());
        System.out.println("Tag="+b2.getTag());
        
    }
    
    public int restoDivisao(int x, int y){
        int resultado = x % y;
        
        return resultado;
    }
    
      public static int conversorDecimalBinario(int n){ 
     
        int d = n;
        String binario = ""; // guarda os dados
        while (d > 0) {
                int b = d % 2; 
                binario = binario + b;
                d = d >> 1; 
        }
        while(binario.length() < 32)
        {
            binario = binario + 0;
        }
        String invertida = new StringBuilder(binario).reverse().toString();
        return Integer.parseInt(invertida);
        //return invertida;
    }
    
    public String verificaTam(int n){
        String number = Integer.toString(n);
        
        int tamanho = number.length();
        int inc = 32 - tamanho;
            if(tamanho <32){
                for(int i = 0; i< inc; i++)
                {
                    number = "0" + "" + number;//inserindo zeros na frente
                }
            }
        return number;
    }
    
    public int encontrarVia(int endereco){
        int conjunto = restoDivisao(endereco, getTamanhoCache());
        int cheio = 0;
        int val = 0;
            for(int i = 0; i<getNumVias(); i++){
                if(instrucao[conjunto][i].getVia()==0){//via livre
                    cheio = 0;
                    val = i;
                //    return i;
                }else{
                    cheio = 1;
                }
            }
     
            //tá cheio preciso remover
            if(cheio == 1){
                   for(int i = 0; i<getNumVias(); i++){
                       if(instrucao[conjunto][i].getId()==0){//é o primeiro né
                          instrucao[conjunto][i].setId(getNumVias()+1); 
                          val = i;
                       }else{
                           int aux = instrucao[conjunto][i].getId();
                           instrucao[conjunto][i].setId(aux--);
                       }
                   } 
            } 
            
        return val;    
    }
    
    public int encontrarElemento(int endereco){
        int num = getOffsetByte()+getOffsetPalavra()+getTamIndice();
        int tamTag = 32 - num;
        
        int auxEnd=0;
        auxEnd = endereco;
        
        String tag = verificaTam(conversorDecimalBinario(endereco));
        
        tag = tag.substring(0, tamTag);
        
        
        int conjunto = restoDivisao(endereco, getTamanhoCache());//vai retornar o conj aonde tenho que salvar o bloco
        System.out.println("Valor do conj="+conjunto);
        int via = encontrarVia(endereco);
        bloco aux = this.instrucao[conjunto][via];
        
        
        return 0;
    }
    
}


