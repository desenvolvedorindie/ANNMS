/*
 *  ANNDL - Artificial Neural Networks Definition Language
 *  ANNML - Artificial Neural Network Manipulation Language
 *  ANNQL - Artificial Neural Networks Query Language
 */
grammar SQLANN;

@header {
/*
 * Copyright (c) 2013, Welsiton Ferreira (wfcreations@gmail.com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *  
 *  Redistributions in binary form must reproduce the above copyright notice, this
 *  list of conditions and the following disclaimer in the documentation and/or
 *  other materials provided with the distribution.
 *
 *  Neither the name of the WFCreation nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package br.com.wfcreations.annms.core.sqlann;
}

options
{
    language = Java;
}

/*
 * Lexer
 */
fragment A_  :   ('a'|'A');
fragment B_  :   ('b'|'B');
fragment C_  :   ('c'|'C');
fragment D_  :   ('d'|'D');
fragment E_  :   ('e'|'E');
fragment F_  :   ('f'|'F');
fragment G_  :   ('g'|'G');
fragment H_  :   ('h'|'H');
fragment I_  :   ('i'|'I');
fragment J_  :   ('j'|'J');
fragment K_  :   ('k'|'K');
fragment L_  :   ('l'|'L');
fragment M_  :   ('m'|'M');
fragment N_  :   ('n'|'N');
fragment O_  :   ('o'|'O');
fragment P_  :   ('p'|'P');
fragment Q_  :   ('q'|'Q');
fragment R_  :   ('r'|'R');
fragment S_  :   ('s'|'S');
fragment T_  :   ('t'|'T');
fragment U_  :   ('u'|'U');
fragment V_  :   ('v'|'V');
fragment W_  :   ('w'|'W');
fragment X_  :   ('x'|'X');
fragment Y_  :   ('y'|'Y');
fragment Z_  :   ('z'|'Z');

fragment DIGIT
    :   [0-9];

fragment EscapeSequence
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\');

OPEN_PARENTHESIS    :   '(';
CLOSE_PARENTHESIS   :   ')';
OPEN_BRACKETS       :   '{';
CLOSE_BRACKETS      :   '}';
COMMA               :   ',';
CLAUSE_END          :   ';';
EQUALS              :   '=';

INTEGER             :	I_ N_ T_ E_ G_ E_ R_;
REAL                :   R_ E_ A_ L_;
BOOLEAN             :   B_ O_ O_ L_ E_ A_ N_;
STRING              :   S_ T_ R_ I_ N_ G_;
DATE                :   D_ A_ T_ E_;

CREATE              :   C_ R_ E_ A_ T_ E_;
DATA                :   D_ A_ T_ A_;
DROP                :   D_ R_ O_ P_;
MODEL               :   M_ O_ D_ E_ L_;
INSERT              :   I_ N_ S_ E_ R_ T_;
INTO                :   I_ N_ T_ O_;
NEURALNETWORK       :   N_ E_ U_ R_ A_ L_ N_ E_ T_ W_ O_ R_ K_;
NEURALNETWORKS      :   N_ E_ U_ R_ A_ L_ N_ E_ T_ W_ O_ R_ K_ S_;
RUN                 :   R_ U_ N_;
SHOW                :   S_ H_ O_ W_;
STATUS              :   S_ T_ A_ T_ U_ S_;
TRAIN               :   T_ R_ A_ I_ N_;
VALUES              :   V_ A_ L_ U_ E_ S_;

TRUE                :   T_ R_ U_ E_;
FALSE               :   F_ A_ L_ S_ E_;

NOT                 :   N_ O_ T_;
NULL                :   N_ U_ L_ L_;
EXISTS              :   E_ X_ I_ S_ T_ S_;
IF                  :   I_ F_;
LIKE                :   L_ I_ K_ E_;
LEARNINGRULE        :   L_ E_ A_ R_ N_ I_ N_ G_ R_ U_ L_ E_;


INPUT               :   I_ N_ P_ U_ T_;
OUTPUT              :   O_ U_ T_ P_ U_ T_;

Integer
    :   [+-]? DIGIT+;

Real
    :   [+-]? DIGIT+ '.' DIGIT*
    |   [+-]? '.' DIGIT+
    ;

ID
    :   [a-zA-Z$][a-zA-Z0-9$_[\-\]]*;

String
    :  '"' ( EscapeSequence | ~('\\'|'"') )* '"';

COMMENT
    :   '/*' .*? '*/' -> channel(HIDDEN);

LINE_COMMENT
    :   '//' ~[\r\n]* '\r'? '\n' -> channel(HIDDEN);

WS
    :   [ \r\t\u000C\n]+ -> skip;

/*
 * Parser
 */
statements
    :   statement (CLAUSE_END statement)* CLAUSE_END?;

statement
    :   CREATE DATA (IF NOT EXISTS)? ID (OPEN_PARENTHESIS dataAttributes CLOSE_PARENTHESIS | LIKE ID)?                                                                      #createDataStatement
    |   CREATE NEURALNETWORK (IF NOT EXISTS)? ID ((OPEN_PARENTHESIS params CLOSE_PARENTHESIS)? MODEL (EQUALS)? ID | LIKE ID)?                                               #createNeuralNetworkStatement
    |   DROP DATA (IF EXISTS)? ID (COMMA ID)*                                                                                                                               #dropDataStatement
    |   DROP NEURALNETWORKS (IF EXISTS)? ID (COMMA ID)*                                                                                                                     #dropNeuralNetworkStatement
    |   INSERT INTO ID VALUES OPEN_PARENTHESIS values CLOSE_PARENTHESIS                                                                                                     #insertIntoStatement
    |   RUN ID VALUES OPEN_PARENTHESIS values CLOSE_PARENTHESIS                                                                                                             #runStatement
    |   SHOW DATA                                                                                                                                                           #showDataStatement
    |   SHOW DATA STATUS ID                                                                                                                                                 #showDataStatusStatement
    |   SHOW NEURALNETWORKS                                                                                                                                                 #showNeuralNetworksStatement
    |   SHOW NEURALNETWORK STATUS ID                                                                                                                                        #showNeuralNetworkStatusStatement
    |   SHOW STATUS                                                                                                                                                         #showStatusStatement
    |   TRAIN ID (OPEN_PARENTHESIS params CLOSE_PARENTHESIS)? LEARNINGRULE (EQUALS)? ID COMMA? DATA (EQUALS)? ID COMMA? INPUT (EQUALS)? list (COMMA? OUTPUT (EQUALS) list)?    #trainStatement
    ;

dataAttributes
    :   dataAttribute (COMMA dataAttribute)* COMMA?;

dataAttribute
    :   ID dataType
    ;

dataType
    :   BOOLEAN                                                                 #booleanDataType
    |   INTEGER                                                                 #integerDataType
    |   REAL                                                                    #realDataType
    |   STRING                                                                  #stringDataType
    |   DATE String                                                             #dateDataType
    |   list                                                                    #listDataType
    ;

list
    :   OPEN_BRACKETS ID (COMMA ID)* CLOSE_BRACKETS;

params
    :   param (COMMA param)* COMMA?                                                                     
    ;

param
    :   ID paramValue*
    ;

paramValue
    :   value
    |   complexList
    ;

value
    :   (TRUE | FALSE)                                                          #booleanValue
    |   NULL                                                                    #nullValue
    |   Integer                                                                 #integerValue
    |   Real                                                                    #realValue
    |   String                                                                  #stringValue
    |   ID                                                                      #idValue
    ;

values
    :   value (COMMA value)*
    ;


complexList
    :   OPEN_BRACKETS paramValue (COMMA paramValue)* CLOSE_BRACKETS
    ;