package bianyiqi;
//Êý×Ö³£Á¿
public class Num extends Token{
Num(int n){
	inline = cffxq.line;
	this.tag = Tag.NUM;
	this.val = n;
}
public String output ()
{
	return String.valueOf(val);
}
}
