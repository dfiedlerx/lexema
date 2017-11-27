package lexer;

import java.io.*;

public class LexerAluno {
    
    private static final int END_OF_FILE = -1; // constante para fim do arquivo
    private static int lookahead = 0; // armazena o último caractere lido do arquivo	
    public static int n_line = 1; // contador de linhas
    public static int n_column = 1; // contador de colunas
    private RandomAccessFile instance_file; // referencia para o arquivo
    private static TS tabelaSimbolos; // tabela de simbolos
    
    // construtora >> nao eh necessario mexer aqui
    public LexerAluno(String input_data) {
		
   	// Abre instance_file de input_data
		try {
   		instance_file = new RandomAccessFile(input_data, "r");
		}
		catch(IOException e) {
   		System.out.println("Erro de abertura do arquivo " + input_data + "\n" + e);
   		System.exit(1);
		}
		catch(Exception e) {
   		System.out.println("Erro do programa ou falha da tabela de simbolos\n" + e);
   		System.exit(2);
		}
    }
    
    // Fecha instance_file de input_data >> nao eh necessario mexer aqui
    public void fechaArquivo() {

    	try {
      	instance_file.close();
      }
		catch(IOException errorFile) {
      	System.out.println("Erro ao fechar arquivo\n" + errorFile);
         System.exit(3);
		}
    }
    
    // Reporta erro para o usuário >> nao eh necessario mexer aqui
    public void sinalizaErro(String mensagem) {

        System.out.println("[Erro Lexico]: " + mensagem + "\n");
    }
    
    // Volta uma posição do buffer de leitura >> nao eh necessario mexer aqui
    public void retornaPonteiro(){

    	try {
      	// Não é necessário retornar o ponteiro em caso de Fim de Arquivo
         if(lookahead != END_OF_FILE) {
         	instance_file.seek(instance_file.getFilePointer() - 1);
           	n_column -= 1; // decrementar a coluna
         }    
      }
      catch(IOException e) {
      	System.out.println("Falha ao retornar a leitura\n" + e);
         System.exit(4);
      }
    }
    
    /* TODO:
    //[1]   Voce devera se preocupar quando incremetar as linhas e colunas,
    //      assim como quando decrementar ou reseta-las.
    //[2]   Toda vez que voce encontrar um lexema completo, voce deve retornar
    //      um objeto new Token(Tag, "lexema", linha, coluna). Cuidado com as
    //      palavras reservadas que ja sao codastradas na TS. Essa consulta
    //      voce devera fazer somente quando encontrar um Identificador.
    //[3]   Se o caractere lido nao casar com nenhum caractere esperado,
    //      apresentar a mensagem de erro na linha e coluna correspondente.
    //[4]   Dica: Para saber se 'c' eh uma letra, use Character.isLetter(c).
    //[5]   Dica: a variavel 'lexema' eh responsavel por montar o lexema pelo 
    //      metodo lexema.append(c).
    //[6]   Atencao com as strings. Assim que aparacer o segundo '"' (aspas duplas) 
    //      retornar a string. Cuidado, strings devem ser fechadas antes do 
    //      fim do arquivo ou de quebra de linha.
    //[7]   Nao eh necessario criar nenhuma outra variavel para finalizar seu AFD.
    */
    
    // Obtém próximo token >> aqui sim vc vai tter que completar
    // esse metodo eh o AFD
    public Token proxToken() {

      // essa variavel armaeza o lexema (palavra) construido
		StringBuilder lexema = new StringBuilder();
        
      // variavel que representa o estado atual
		int estado = 0;
        
      // armazena o char corrente
		char c;
		
		// sai desse loop somente qndo retornar um token
		while(true) {
      	c = '\u0000'; // null char
            
      	// avanca caractere
      	try {
        		lookahead = instance_file.read(); 
				if(lookahead != END_OF_FILE) {
            	c = (char) lookahead;

					// Dica: lemos um caractere...temos que controlar a posicao
					// desse caractere por <linha, coluna>...o que fazer??

            }
         }
         catch(IOException e) {
         	System.out.println("Erro na leitura do arquivo");
            System.exit(3);
         }
            
         // movimentacao do automato
         switch(estado) {
         	// estado = 0 representa o estado inicial
         	// temos que ter uma qtde de cases para cobrir as movimentacoes
          	// do nosso AFD.
                
				case 0:
            	if(lookahead == END_OF_FILE) // fim de arquivo. hora de parar
               	return new Token(Tag.EOF, "EOF", n_line, n_column);
               else if(c == ' ' || c == '\t' || c == '\n' || c == '\r') {
               	// Permance no estado = 0
                  // Dica: alterar o valor da linha e coluna caso necessario
               }
               else if(c == '+') {
               	// repare: isso indica que reconheci o + e vou movimentar
                  // do estado 0 para o estado 1. 
                  // MAS eu nao vou criar o estado 1, pois pra gente eh 
                  // desnecessario fazer outra consulta a toa. Irei
                  // apenas retornar o token. Toda vez que retornarmos um
                  // token, saimos (return) do AFD (metodo proxToken()).
               	return new Token(Tag.RELOP_PLUS, "+", n_line, n_column);
               }
               else if(c == '=') {
               	// iniciamos o reconhecimento de um operador
               	lexema.append(c);
               	estado = 11;
               }
               else if ....
               ....
               ...
               else if(Character.isLetter(c)) {
               	// significa que comecamos a construir um ID
                  // vamos movimentar de estado
                  lexema.append(c);
                  estado = 16;
               }
               else if(Character.isDigit(c)) {
               	lexema.append(c); // comecamos a construir um numero
                  estado = 18; // vamos para o estado 18
               }
               else if(c == '!') {
               	// comecamos o reconhecimento do operador !=
                  lexema.append(c);
                  estado = 14;
               }
               else if ...
               ...
               ...
					else if(c == '"') {
						// opa: temos o comeco de uma string
						// Dica: altere o estado
						// comece a construir a string
					}
               else {
						// se cair nesse ULTIMO else, significa que temos um simbolo
						// que nao sabemos reconhecer.
						//
               	// Dica: sinalizaErro(string); string eh uma mensagem informando
               	// qual o simbolo encontrado e onde esta esse simbolo.
               	return null;
               }
               break;
				case 11:
            	if(c == '=') {
              		// Dica: retornar novo objeto Token(Tag.RELOP_EQ, "==", n_line, n_column);
               }
               else {
               	// Dica: usar retornaPonteiro() para voltar uma posicao no arquivo
                  // Dica: retornar novo objeto Token(Tag.RELOP_ASSIGN, "=", n_line, n_column);
               } 
            case ...:
            ...
				...
            case 14:
            	if(c == "=") {
              		// Dica: retornar novo objeto Token(Tag.RELOP_NQ, "!=", n_line, n_column);
               }
               else {
               	// estavamos esperando o caractere =, mas veio outra coisa
                  // entao temos um erro:
                  //Dica: retornar poneito
                  //Dica: sinalizar Erro
                	//Dica: retornar null
               }
               break;
				case 16:
            	if(Character.isLetterOrDigit(c) || c == '_') {
               	// significa que ainda estamos construindo um ID
                  // continuamos no mesmo estado e construindo o ID
                  lexema.append(c);
               }
               else {
               	// se nao cai no if acima, entao terminamos de reconhecer
                  // um ID. Vamos retornar o ponteiro do arquivo para ler
                  // outra vez o caractere e retornar o token reconhecido.
                  // MAS CUIDADO, o ID pode ser uma palavra reservada!
                  retornaPonteiro();
                        
                  // De uma olhada no metodo retornaToken() da classe TS
                  Token token = tabelaSimbolos.retornaToken(lexema.toString());
                        
                  // Dica: se o token == null entao temos que retornar um 
                  // token ID, caso contrario retornamos o token ja cadastrado,
                  // ou seja, uma palavra reservada. Ache essa logica para
                  // vc fazer o retorno correto.
                        
               } 
               break;
				case 18:
           		if(Character.isDigit(c)) {
               	// Permanece no estado 18 e continua construindo o numero 
                  lexema.append(c);
               }
               else if(c == '.') {
               	// significa que o numero eh um double
                  // vamos deslocar para o estado 20
                  lexema.append(c);
                  estado = 20;
               }
               else {
               	// significa que nao eh um numero nem um ponto,
                  // ou seja, entao construimos um numero inteiro e
                  // precisamos retornar na leitura do arquivo para ler
                  // outra vez o caractere lido e retornar o token
                  // que reconhecemos. Novamente, nao vou criar o estado 19.
                	// Vou retornar o inteiro que acabei de construir.
                  retornaPonteiro();						
						return new Token(Tag.INTEGER, lexema.toString(), n_line, n_column);
               }
               break;
				case 20:
            	// nesse estado o unico simbolo que reconhecemos eh um digito.
               // Prestem atencao na logica que acontece aqui:
               if(Character.isDigit(c)) {
              		estado = 21; // movimento para o estado 21
               }
               else {
               	// temos um erro. Estavamos esperando um digito, mas veio
               	// outra coisa. Voltamos o ponteiro e damos uma mensagem
                  // de erro. Vamos usar sinalizaErro(string): string eh 
                  // uma mensagem informando qual o simbolo encontrado e 
                  // onde esta esse simbolo.
						// CUIDADO com a linha e coluna do erro!
                  retornaPonteiro();
                  sinalizaErro("Simbolo " + c + " invalido na linha " + n_line + 
                                " e coluna " + n_column);
                  return null;
               }
               break;
				case ...:
            ...
            ...
				case 23:
           		if(c == '"') {
						// opa: temos o fim da string
						// dica: retorne o novo token que corresponde a string
               }
					else if(c == '\n' || c == '\r') {
						// situacao de erro: string não fechada antes de quebra de linha
						// o que fazer??
					}
               else if(lookahead == END_OF_FILE) {
						// situacao de erro: string não fechada antes de fim de arquivo
               	sinalizaErro("String deve ser fechada com \" antes do fim de arquivo");
						return null;
               }
               else { // Se vier outro, permanece no estado 23
                	lexema.append(c);
               }
               break;
				case ...:
				...
				...
			} // fim switch
		} // fim while
	} // fim metodo


	public static void main(String[] args) {
		Lexer lexer = new Lexer("HelloJavinha.jvn"); // parametro eh um programa em Javinha
		Token token;
        
      // cria o objeto Tabela de Simbolos para inserir todas as palavras
      // reservadas do Javinha.
      tabelaSimbolos = new TS();

		// Enquanto não houver erros ou não for fim de arquivo:
		do {
            
			// o metodo proxToken() simula o AFD. Eh esse metodo que nos retorna
         // o token reconhecido. Iremos executar esse metodo ate o token
         // retornado for igual a null ou estivermos em fim de arquivo.
         token = lexer.proxToken();
            
         // Imprime token
			if(token != null) {
         	System.out.println("Token: " + token.toString() + "\t Linha: " +
                        n_line + "\t Coluna: " + n_column);
         }
	     
		} while(token != null && token.getClasse() != Tag.EOF);
		lexer.fechaArquivo();
	}
}
