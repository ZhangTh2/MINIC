package bianyiqi;
//自定义语言 中的关键字表
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;


public class Keywords {
	
	public static HashMap<String, Tag> keyWords = new HashMap<String, Tag>();
    //初始化关键字表
	Keywords()
    {
    	
    	keyWords.put("int",Tag.KW_INT);
    	keyWords.put("char",Tag.KW_CHAR);
    	keyWords.put("string",Tag.KW_STRING);
    	keyWords.put("void",Tag.KW_VOID);
    	keyWords.put("if",Tag.KW_IF);
    	keyWords.put("else",Tag.KW_ELSE);
    	keyWords.put("while",Tag.KW_WHILE);
    	keyWords.put("do",Tag.KW_DO);
    	keyWords.put("for",Tag.KW_FOR);
    	keyWords.put("break",Tag.KW_BREAK);
    	keyWords.put("continue",Tag.KW_CONTINUE);
    	keyWords.put("return",Tag.KW_RETURN);
    	keyWords.put("read",Tag.KW_READ);
    	keyWords.put("printf",Tag.KW_PRINTF);
    }
	
	
	//返回一个Token类   判断是不是关键字
    public Token getTag(String name)
    {
       if(keyWords.get(name)!=null)
       {
    	   Tag tag = keyWords.get(name);
    	   Token t  = new Token(tag);
    	   return t;
       }
       else
       {
    	   ID t = new ID(name);
    	   return t;
       }
    }
}
