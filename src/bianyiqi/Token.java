package bianyiqi;
//词法记号的基类
public  class Token {
Tag tag;
int val;
char ch;
String s;
int inline;
public Token()
{
	inline = cffxq.line;
}
public Token(Tag t){
	inline = cffxq.line;
	tag = t;
}
public String output ()
{
	return tag.name();
}
}
