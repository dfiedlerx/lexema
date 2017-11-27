package lexer;

public class Token {
    
	private String lexema;
   private Tag classe;
   private int linha;
   private int coluna;
	
   public Token(Tag classe, String lexema, int linha, int coluna) {

   	this.classe = classe;
      this.lexema = lexema;
      this.linha = linha;
      this.coluna = coluna;
   }
	
   public Tag getClasse() {
		
		return classe;
	}
	
   public void setClasse(Tag classe) {
		
		this.classe = classe;
   }
	
   public String getLexema() {
	
		return lexema;
   }
	
   public void setLexema(String lexema) {
		
		this.lexema = lexema;
   }
    
   public int getLinha() {
   	return linha;
   }

   public void setLinha(int linha) {
   	this.linha = linha;
   }

   public int getColuna() {
   	return coluna;
   }

   public void setColuna(int coluna) {
   	this.coluna = coluna;
   }
    
   @Override
   public String toString() {
   	return "<" + classe + ", \"" + lexema + "\">";
   }
}
