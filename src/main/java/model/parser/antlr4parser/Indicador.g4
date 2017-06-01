grammar Indicador;

//Codigo propio que se incluira en los archivos generados

@parser::header{
package model.parser.antlr4parser;
import model.parser.Antlr4ParserStrategy;
}

@parser::members {
public IndicadorParser(TokenStream tokens,Antlr4ParserStrategy evaluador){
	super(tokens);
	this.evaluador = evaluador;
	_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
public Antlr4ParserStrategy evaluador;
}

@lexer::header{
package model.parser.antlr4parser;
}

//Sintaxis del parser

eval returns [double value]
    :    exp=additionExp {$value = $exp.value;} EOF
    ;

additionExp returns [double value]
    :    m1=multiplyExp       {$value =  $m1.value;} 
         ( '+' m2=multiplyExp {$value += $m2.value;} 
         | '-' m2=multiplyExp {$value -= $m2.value;}
         )* 
    ;

multiplyExp returns [double value]
    :    a1=atomExp       {$value =  $a1.value;}
         ( '*' a2=atomExp {$value *= $a2.value;} 
         | '/' a2=atomExp {$value /= $a2.value;}
         )* 
    ;

atomExp returns [double value]
    :    n=Number                {$value = Double.parseDouble($n.text);}
    |	 n=ID					 {$value = evaluador.obtenerValor($n.text);}
    |    '(' exp=additionExp ')' {$value = $exp.value;}
    ;

//Expresiones regulares que el lexer convertira en tokens

Number
    :    ('0'..'9')+ ('.' ('0'..'9')+)?
    ;

WS  
    :   (' ' | '\t' | '\r'| '\n') -> skip
    ;  
    
ID
	:	[a-zA-Z]+[a-zA-Z ]*[a-zA-Z]+
	;