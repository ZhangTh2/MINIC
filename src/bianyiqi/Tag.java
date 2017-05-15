package bianyiqi;
//枚举类  列出了所有的词法记号
public enum Tag {
  ERR ,//default
  END,//结束
  ID,//标识符
  KW_INT,KW_CHAR,KW_STRING,KW_VOID,//整型
  NUM,CH,STR,//常量  整型和字符型 
  NOT,LEA,//！ &
  ADD,SUB,MUL,DIV,MOD,//+，-，*，/，%
  INC,DEC,//++,--
  GT,GE,LT,LE,EQU,NEQU,//>,>=,<,<=,==,!=
  AND,OR,//逻辑运算 
  LPRAEN,RPAREN,//()
  LBRACK,RBRACK,//[]
  LBRACE,RBRACE,//{}
  COMMA,COLON,SEMICON,//,,:,;
  ASSIGN,//赋值
  KW_IF,KW_ELSE,//判断
  KW_WHILE,KW_DO,KW_FOR,//循环
  KW_BREAK,KW_CONTINUE,KW_RETURN,//控制
  KW_READ,KW_PRINTF//读写
  
}
