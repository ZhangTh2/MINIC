package bianyiqi;
//在此进行词法分析 
import java.util.LinkedList;

public class cffxq {
	public static int line = 0;//这个词法记号在第几行
public cffxq(LinkedList<Token> cfjh,String s)
{
	
		/*  从文件读入字符串有错误了
		try {
	 String s = new String();
	 FileReader fr=new FileReader("c:/程序.txt"); 
	 BufferedReader br=new BufferedReader(fr);
	 while(br.readLine()!=null){
	      s=br.readLine();
	 }
	 br.close();
	 System.out.println(s);
		}catch(Exception e2)
		{
			
		}
		*/
		String str  = s;
		Keywords key = new Keywords();
		
		//建立关键字表
		
		
		
		//从头开始分析
		for(int  i = 0;i < str.length();)
		{
			char ch = str.charAt(i);
			//去除空白
			if(ch == ' '||ch=='\n'||ch=='\t')
		{
				if(ch=='\n') line++;
				i++;
				continue;
		}
			
			
			//分析标志符合关键字
			else if(ch>='a'&&ch<='z'||ch>='A'&&ch<='Z'||ch=='_')
			{
				String name = new String();
				name += ch;
				i++;
				ch = str.charAt(i);
				while(ch>='a'&&ch<='z'||ch>='A'&&ch<='Z'||ch=='_')
				{
					name+=ch;
					i++;
					if(i<str.length())
					ch = str.charAt(i);
					else break;
				}
	            Token t = key.getTag(name);
	            cfjh.add(t);
			}
			
			
			//分析数字
			else  if(ch>='0'&&ch<='9')
	       {
	    	   String  val = new String();
	    	    val += ch;
				i++;
				ch = str.charAt(i);
				while(ch>='0'&&ch<='9')
				{
					val+=ch;	
					i++;
					if(i < str.length())
				
					ch = str.charAt(i);
					else break;
				}
	    	   int va = Integer.parseInt(val);
	    	   Num t = new Num(va);
	    	   cfjh.add(t);
	       }
	       
			
			//分析 界符
			else
			{
				switch(ch)
				{
				case '+':
					i++;
					ch = str.charAt(i);
					if(ch=='+') {
					Token inc = new Token(Tag.INC);
					cfjh.add(inc);
					i++;
					}
					else{
						Token add = new Token(Tag.ADD);
						cfjh.add(add);
					}	
					break;
					
				case '-':
					i++;
					ch = str.charAt(i);
					if(ch=='-') {
					Token dec = new Token(Tag.DEC);
					cfjh.add(dec);
					i++;
					}
					else{
						Token sub = new Token(Tag.SUB);
						cfjh.add(sub);
					}	
					break;
					
				case '*':
					Token mul = new Token(Tag.MUL);
					cfjh.add(mul);
					i++;
					break;
					
				case '/':
					Token div = new Token(Tag.DIV);
					cfjh.add(div);
					i++;
					break;
					
				case '%':
					Token mod = new Token(Tag.MOD);
					cfjh.add(mod);
					i++;
					break;
					
				case '>':
					i++;
					ch = str.charAt(i);
					if(ch=='=') {
					Token ge = new Token(Tag.GE);
					cfjh.add(ge);
					i++;
					}
					else{
						Token gt = new Token(Tag.GT);
						cfjh.add(gt);
					}	
					break;
					
				case '<':
					i++;
					ch = str.charAt(i);
					if(ch=='=') {
					Token le = new Token(Tag.LE);
					cfjh.add(le);
					i++;
					}
					else{
						Token lt = new Token(Tag.LT);
						cfjh.add(lt);
					}	
					break;
					
				case '=':
					i++;
					ch = str.charAt(i);
					if(ch=='=') {
					Token equ = new Token(Tag.EQU);
					cfjh.add(equ);
					i++;
					}
					else{
						Token ass = new Token(Tag.ASSIGN);
						cfjh.add(ass);
					}	
					break;
					
					
				case '&':
					i++;
					ch = str.charAt(i);
					if(ch=='&') {
					Token and = new Token(Tag.AND);
					cfjh.add(and);
					i++;
					}
					else{
						Token lea = new Token(Tag.LEA);
						cfjh.add(lea);
					}	
					break;
					
				case '|':
					i++;
					ch = str.charAt(i);
					if(ch=='|') {
					Token or = new Token(Tag.OR);
					cfjh.add(or);
					i++;
					}
					else{
						Token err = new Token(Tag.ERR);
						cfjh.add(err);
					}	
					break;
					
				case '!':
					i++;
					ch = str.charAt(i);
					if(ch=='=') {
					Token nequ = new Token(Tag.NEQU);
					cfjh.add(nequ);
					i++;
					}
					else{
						Token not = new Token(Tag.NOT);
						cfjh.add(not);
					}	
					break;
					
				case ',':
					Token comma = new Token(Tag.COMMA);
					cfjh.add(comma);
					i++;
					break;
					
				case ':':
					Token colon = new Token(Tag.COLON);
					cfjh.add(colon);
					i++;
					break;
					
				case ';':
					Token semicon = new Token(Tag.SEMICON);
					cfjh.add(semicon);
					i++;
					break;
					
				case '(':
					Token lparen = new Token(Tag.LPRAEN);
					cfjh.add(lparen);
					i++;
					break;
					
				case ')':
					Token rp = new Token(Tag.RPAREN);
					cfjh.add(rp);
					i++;
					break;
					
				case '[':
					Token lbrack = new Token(Tag.LBRACE);
					cfjh.add(lbrack);
					i++;
					break;
					
				case ']':
					Token rbrack = new Token(Tag.RBRACE);
					cfjh.add(rbrack);
					i++;
					break;
					
				case '{':
					Token lb = new Token(Tag.LBRACE);
					cfjh.add(lb);
					i++;
					break;
					
				case '}':
					Token rb = new Token(Tag.RBRACE);
					cfjh.add(rb);
					i++;
					break;
					
				/*case -1:
					Token end = new Token(Tag.END);
					cfjh.add(end);
					i++;
					break;
					*/
					
					default:
						Token e = new Token(Tag.ERR);
						i++;
					
				}
			}
		}
	}
public String printcfjh(LinkedList<Token> cfjh)
{
	String s = new String();
for(int i = 0;i < cfjh.size();i++)
{
	s += cfjh.get(i).output();
	//System.out.println(s);
	s+='\n';
}
 return s;
}
}