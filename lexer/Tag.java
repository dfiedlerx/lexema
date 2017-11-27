package lexer;

public enum Tag {
    
    // fim de arquivo
    EOF,
    
    //Operadores
    RELOP_LT,
    RELOP_LE,
    RELOP_GT,
    RELOP_GE,
    RELOP_EQ,
    RELOP_NE,
    RELOP_ASSIGN,
    RELOP_PLUS,
    RELOP_MINUS,
    RELOP_MULT,
    RELOP_DIV,
    
    //Simbolos
    SMB_OP,
    SMB_CP,
    SMB_SEMICOLON,
    
    //identificador
    ID,
    
    //numeros
    INTEGER,
    DOUBLE,
    
    //strings
    STRING,
    
    // palavra reservada
    KW;
}
