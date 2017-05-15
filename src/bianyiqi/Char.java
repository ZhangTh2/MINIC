package bianyiqi;
//×Ö·ûÐÍ³£Á¿
public class Char extends Token{
Char(char c)
{
	this.inline = cffxq.line;
	this.tag = Tag.CH;
	this.ch = c;
}
public String output ()
{
	return String.valueOf(ch);
}
}
