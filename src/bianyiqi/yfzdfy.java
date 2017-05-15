package bianyiqi;
//进行翻译
import java.util.LinkedList;

public class yfzdfy {
	LinkedList<Token> cfjh  = new LinkedList<Token>();
	fhb fhb = new fhb();
	GenIr ir = new GenIr();
	 int flag = 0;
	int iswrite = 0;
	String res="";
	 String s="";
	static int num;
	 String code = "";
	//num代表分析到了第几个词法记号
	
	
	yfzdfy(LinkedList<Token> cfjh)
	{
		this.cfjh = cfjh;
		ir.interCode= new LinkedList<InterInst>();
		s = "";
		flag = 0;
	}
	//分析器开始，主函数
	void analyze()
	{
		num=0;
		program();
	}
	
	//整个程序
	void program()
	{
		if(num>=cfjh.size())
		{
			
		}
		else{
			segment();
		    program();}
	}
	//定义段
	void segment()
	{
		if(cfjh.get(num).tag==Tag.KW_CHAR||cfjh.get(num).tag==Tag.KW_STRING||cfjh.get(num).tag==Tag.KW_VOID||cfjh.get(num).tag==Tag.KW_INT)
		{
			num++;
			def(cfjh.get(num).tag);
		}
		else
		{
			flag = 1;
			s+=cfjh.get(num).inline;
			s+=":不能识别的类型";
			s+='\n';
			//System.exit(0);
		}
	}
	void def(Tag t)
	{
		String name;
		if(cfjh.get(num).tag!=Tag.ID)
		{
			flag = 1;
			s+="缺少标志符:";
			s+=cfjh.get(num).inline;
			s+='\n';
			//System.exit(0);
		}
		name = cfjh.get(num).s;
		//System.out.println("标识符的名字为"+name);
		num++;
		idtail(t,name);
	}
	void idtail(Tag t,String name)//定义变量
	{ 
		
		if(cfjh.get(num).tag==Tag.LPRAEN)//代表是函数
		{
		  fhb.enter();
		  num++;
		  LinkedList<var> paraList  = new  LinkedList<var>();
		  para(paraList);
		  if(cfjh.get(num).tag!=Tag.RPAREN)
		  {
			flag = 1;
			s+=cfjh.get(num).inline;
			s+=":缺少右括号";
			
			s+='\n';
			//System.exit(0);
		  }
		  num++;
		 Fun fun = new Fun(t,name, paraList);
		 funtail(fun);
		 fhb.leave();
		}
		else//代表是其他类型的变量
		{
			fhb.addVar(varrdef(t,name));
			deflist(t);
		}
		
	}
	
	
	var varrdef(Tag t,String name)
	{
		var var1 = new var();
		var1.zyylj = fhb.zyylj;
		if(cfjh.get(num).tag==Tag.LBRACK)
		{
			int size;
			num++;
			if(cfjh.get(num).tag!=Tag.NUM)
			{
				flag = 1;s+=cfjh.get(num).inline;
				s+=":缺少数组大小定义";
				
				s+='\n';
				//System.exit(0);
			}
			size = cfjh.get(num).val;
			num++;
			if(cfjh.get(num).tag!=Tag.RBRACK)
			{
				flag = 1;
				s+=cfjh.get(num).inline;
				s+=":数组定义右括号缺失";
				s+='\n';
				//System.exit(0);
			}
			num++;
			var1.isarry = true;//是否数组
			var1.arraySize =size;//数组长度
			var1.isleft = false;
		}
		else
		{
			var1.type = t;
			var1.name = name;
			init(var1);
		}
		return var1;
	}
	void init(var var1)
	{
		if(cfjh.get(num).tag==Tag.ASSIGN){
		num++;
		var1=ir.genTwoOp(var1,Tag.ASSIGN,expr());
		
	//	System.out.println(var1.name+"赋值为"+var1.initval);
		}
		else{
			
		}
	}
	void deflist(Tag t)
	{
		if(cfjh.get(num).tag==Tag.COMMA)
		{
			num++;
			def(t);
		}
		else if(cfjh.get(num).tag==Tag.SEMICON)//是分号
		{
			num++;
		}
		else
		{
			flag = 1;
			s+=cfjh.get(num).inline;
			s+=":变量定义缺少分隔符";
			s+='\n';
			
		}
	}
	
	
	//函数的形参列表  未完成
	void para(LinkedList<var> paraList)
	{
		
	}

	
	void funtail(Fun fun)
	{
		if(cfjh.get(num).tag==Tag.SEMICON)
		{
			fhb.decFun(fun);
			num++;
		}
		else{
			fhb.defFun(fun);
			block();
			fhb.enddefFun();
		}
	}
	
	//函数体  未完成
	void block()
	{
		if(cfjh.get(num).tag!=Tag.LBRACE)
		{
			flag = 1;s+=cfjh.get(num).inline;
			s+=":缺少左花括号";
			
			s+='\n';
			//System.exit(0);
		}
	    num++;
		subprogram();
		if(cfjh.get(num).tag!=Tag.RBRACE)
		{
			flag = 1;
			
			s+=cfjh.get(num).inline;s+=":缺少右花括号";
			s+='\n';
			//System.exit(0);
		}
		num++;
	}
	
	void subprogram()
	{
		if(cfjh.get(num).tag==Tag.RBRACE)
		{
	    return;
		}
		else if(cfjh.get(num).tag==Tag.KW_CHAR||cfjh.get(num).tag==Tag.KW_STRING||cfjh.get(num).tag==Tag.KW_VOID||cfjh.get(num).tag==Tag.KW_INT)
		{
			num++;
			def(cfjh.get(num).tag);
			subprogram();
		}
		else {
			statement();
			subprogram();
			//
		}	
		
	}
    void altexpr()
    {
    	if(cfjh.get(num).tag==Tag.KW_PRINTF)
    	{
    		
    		num++;
    		this.iswrite = 1;
    		res += fhb.getvar(cfjh.get(num).s).initval;
    		res+='\n';
    		ir.genwrite(fhb.getvar(cfjh.get(num).s));
    		num++;
    		
    	}
    	else expr();
    }
	var expr()
	{
		
		//return assexpr();
		return assexpr();
		//表达式未完成
	}
	var assexpr()
	{    
		var lval = orexpr();
		return asstail(lval);
	}
    var asstail(var lval)
    {
    	if(cfjh.get(num).tag==Tag.ASSIGN)
    	{
    		num++;
    		var val =orexpr();
    		var rval = asstail(val);
    		var result = ir.genTwoOp(lval,Tag.ASSIGN,rval);
    		return result;
    		
    	}
    	else
    	{
    		return lval;
    	}
    }
   var orexpr()
    {
    	var lval = andexpr();
    	return ortail(lval);
    }
    var ortail(var lval)
    {
    	if(cfjh.get(num).tag==Tag.OR)
    	{
    		num++;
    		var val = andexpr();
        	var rval = ortail(val);
        	var result = ir.genTwoOp(lval,Tag.OR ,rval);
    		return result;
    	}
    	else{
    		return lval;
    	}
    }
   var andexpr()
    {
    	var lval = cmpexpr();
    	return andtail(lval);
    }
    var andtail(var lval)
    {
    	if(cfjh.get(num).tag==Tag.ADD)
    	{
    		num++;
    		var val = cmpexpr();
        	var rval = andtail(val);
        	var result = ir.genTwoOp(lval,Tag.AND,rval);
    		return result;
    	}
    	else{
    		return lval;
    	}
    }
    var cmpexpr()
    {
    	var lval = aloxepr();
    	return cmptail(lval);
    }

  var cmptail(var lval){
 
    	switch(cfjh.get(num).tag)
    	{
    	case GE:
    	{
    		num++;
            var val = aloxepr();
        	var rval = cmptail(val);
        	var result = ir.genTwoOp(lval,Tag.GE,rval);
    		return result;
    	}
    	
    	case GT:
    	{
    		num++;
            var val = aloxepr();
        	var rval = cmptail(val);
        	var result = ir.genTwoOp(lval,Tag.GT,rval);
    		return result;
    	}
    	
    	case LT:
    	{
    		num++;
            var val = aloxepr();
        	var rval = cmptail(val);
        	var result = ir.genTwoOp(lval,Tag.LT,rval);
    		return result;
    	}
    	case LE:
    	{
    		num++;
    		var val = aloxepr();
    		var rval = cmptail(val);
        	var result = ir.genTwoOp(lval,Tag.LE,rval);
    		return result;
    	}
    	case EQU:
    	{
    		num++;
    		var val = aloxepr();
    		var rval = cmptail(val);
    		var result = ir.genTwoOp(lval,Tag.EQU,rval);
    		return result;
    	}
    	case NEQU:
    	{
    		num++;
    		var val = aloxepr();
    		var rval = cmptail(val);
    		var result = ir.genTwoOp(lval,Tag.NEQU,rval);
    		return result;
    	}
    	default:
    		return lval;
    	//==Tag.GE||cfjh.get(num).tag==Tag.GT||cfjh.get(num).tag==Tag.LT||cfjh.get(num).tag==Tag.LE||cfjh.get(num).tag==Tag.EQU||cfjh.get(num).tag==Tag.NEQU);
    	
    	}
    	
    	
    	
    }
    var  aloxepr()
    {
    	var lval =item();
    	return alotail(lval);
    }
    var alotail(var lval)
    {
    	if(cfjh.get(num).tag==Tag.ADD)
    	{
    		num++;
    		var rval = item();
    		var result = ir.genTwoOp(lval,Tag.ADD,rval);
    		return result;
    	}
    	else if(cfjh.get(num).tag==Tag.SUB)
    	{
    		num++;
    		var rval = item();
    		var result = ir.genTwoOp(lval,Tag.SUB,rval);
    		return result;
    	}
    	else{
    		return lval;
    	}
    }
    var item()
    {
    	var lval =factor();
    	return itemtail(lval);
    }
    var itemtail(var lval)
    {
    	if(cfjh.get(num).tag==Tag.MUL)
    	{
    		num++;
    		var rval = factor();
    		var result =  ir.genTwoOp(lval,Tag.MUL,rval);
    		return itemtail(result);
    	}
    	else if(cfjh.get(num).tag==Tag.MOD)
    	{
    		num++;
    	
    		var rval = factor();
    		var result =  ir.genTwoOp(lval,Tag.MOD,rval);
    		return itemtail(result);
    	}
    	else if(cfjh.get(num).tag==Tag.DIV)
    	{
    		num++;
    		var rval = factor();
    		var result =  ir.genTwoOp(lval,Tag.DIV,rval);
    		return result;
    	}
    	else{
    		return lval;
    	}
    }
    var  factor()
    {
    	var result;
    	if(cfjh.get(num).tag==Tag.NOT)			
    	{
   
    		num++;
    		result=ir.genOneOp(Val(),Tag.NOT);
    	    return result;
    	}
    	else if(cfjh.get(num).tag==Tag.SUB)
    	{
    		num++;
    		result=ir.genOneOp(Val(),Tag.SUB);
    		return result;
    	}
    	//||cfjh.get(num).tag==Tag.LEA||cfjh.get(num).tag==Tag.SUB)
    	else{
    		return Val();
    	}
    }
    var Val()
    {
    	var val = elem();
    	return rop(val);
    }
    
    
    var rop(var val)
    {
    	var result;
    	if(cfjh.get(num).tag==Tag.INC)
    	{
    		num++;
    		result = ir.genOneOpRight(val,Tag.INC);
    		return result;
    	}
    	else if(cfjh.get(num).tag==Tag.DEC)
    	{
    		num++;
    		result = ir.genOneOpRight(val,Tag.DEC);
    		return result;
    	}
    	else return val;
    	
    	
    	//自加减
    }
    var elem()
    {
    	var val;
    	if(cfjh.get(num).tag==Tag.ID)
    	{
    		String name = cfjh.get(num).s;
    		num++;
    		val = idexpr(name);
    		if(val==null)
    		{
    			flag = 1;
    			s+=cfjh.get(num).inline;
    			s+=":"+name+"未声明";
    			s+='\n';
//    			Window wint = new Window();
//    			wint.setVisible(true);
//    			wint.text2.append("运行错误"+'\n'+s);
    			
    		}
    		
    	}
    	else if(cfjh.get(num).tag==Tag.LPRAEN)
    	{
    		num++;
    		val = expr();
    		if(cfjh.get(num).tag==Tag.RPAREN)
    		{
    			num++;
    		}
    		else{
    			flag = 1;
    			s+=cfjh.get(num).inline;
    			s+=":无右括号";
    			
    			s+='\n';
  ;
    			//System.exit(0);
    		}
    	}
    	else{
    		val = literal();  //代表是常量
    	}
    	return val;
    }
    var literal()
    {
    	var val = new var(cfjh.get(num));
    	num++;
//    	if(cfjh.get(num).tag==Tag.NUM)
//    	{
//    		num++;
//    		return ;
//    	}
//    	else 
//    	{
//    		num++;
//    		return Integer.valueOf(cfjh.get(num).ch);//此处有疑惑
//    	}
     return val;
    }
    var idexpr(String name)
    {
//    	var val;
//    	if(cfjh.get(num).tag==Tag.LBRACK)
//    	{
//    		num++;
//    		val = expr();
//    		if(cfjh.get(num).tag!=Tag.RBRACK)
//    		{
//    			System.out.println("无右括号");
//    			System.out.println(cfjh.get(num).tag);
//    			System.exit(0);
//    		}
//    		else 
//    		{
//    			num++;
//    		}
//    	}
//    	else if(cfjh.get(num).tag==Tag.LPRAEN)
//    	{
//    		num++;
//    		val = realarg();
//    		if(cfjh.get(num).tag==Tag.RPAREN)
//    		{
//    			num++;
//    		}
//    		else{
//    			System.out.println("无右括号");
//    			System.out.println(cfjh.get(num).tag);
//    			System.exit(0);
//    		}
//    	}
//    	else{
    		return fhb.getvar(name);
    	//}
    	//return val;
    	
    }
//    var realarg()
//    {
//    	if(cfjh.get(num+1).tag==Tag.SEMICON)
//    	{
//    		return 0;
//    	}
//    	arg();
//    	arglist();
//    	return 0;
//    }
//    void arg()
//    {
//    	expr();
//    }
//    void arglist()
//    {
//    	if(cfjh.get(num).tag==Tag.COMMA)
//    	{
//    		arg();
//        	arglist();
//    	}
//    	else{
//    		
//    	}
//    }
    void statement()
    {
    	switch(cfjh.get(num).tag) {
    		case KW_WHILE:
    			num++;
    			whilestat();
    			break;
    		case KW_FOR:
    			num++;
    			forstat();
    			break;
    		case KW_IF:
    			num++;
    			ifstat();
    			break;
    		case KW_BREAK:
    		{
    			num++;
    			if(cfjh.get(num).tag!=Tag.SEMICON)
    			{
    				flag = 1;
    				s+=cfjh.get(num).inline;
    				s+=":缺少分号";
    				s+='\n';
        		//	System.exit(0);
    			}
    			num++;
    		}
    		case KW_CONTINUE:
    		{
    			num++;
    			if(cfjh.get(num).tag!=Tag.SEMICON)
    			{

    				flag = 1;
    				s+=cfjh.get(num).inline;
    				s+=":缺少分号";
    				s+='\n';
        			//System.exit(0);
    			}
    			while(cfjh.get(num).tag!=Tag.KW_WHILE)
    	    	{
    	    		num--;
    	    	}
    			break;
    			
    		}
    		case KW_RETURN:
    		{
    			num++;
    			altexpr();
    			if(cfjh.get(num).tag!=Tag.SEMICON)
    			{
    				flag = 1;
    				s+=cfjh.get(num).inline;
    				s+=":缺少分号";
    				s+='\n';
    	
        			//System.exit(0);
    			}
    			num++;
    			break;
    		}
    		default:
    		{
    			altexpr();
    			if(cfjh.get(num).tag!=Tag.SEMICON)
    			{
    				flag = 1;
    				s+=cfjh.get(num).inline;
    				s+=":缺少分号";
    				s+='\n';
        		//	System.exit(0);
    			}
    			num++;
    		}
    			
    	}
    			
    }
    void whilestat()
    {
    	int tnum  = num;
    	//fhb.enter();
    	if(cfjh.get(num).tag!=Tag.LPRAEN)
    	{
    		flag = 1;
			s+=cfjh.get(num).inline;
			s+=":while后缺少括号";
			s+='\n';
			//System.exit(0);
    	}
    	num++;
    	int flag = expr().initval;
    	//System.out.println(flag);
    	//System.out.println("去你妹的");
    	if(cfjh.get(num).tag!=Tag.RPAREN)
    	{
    		flag = 1;
    		s+=cfjh.get(num).inline;
			s+=":缺少右括号";
			
			s+='\n';
			//System.exit(0);
    	}
    	num++;
    	int f = 1,num1 = num+1;
    	if(flag>0){
    	block();
//    	while(cfjh.get(num).tag!=Tag.KW_WHILE)
//    	{
//    		num--;
//    	}
//    	num++;
    	num = tnum;
    	whilestat();
    	}
    	else{
    		while(f!=0)
        	{
        		if(cfjh.get(num1).tag==Tag.LBRACE)
        		f++;
        		if(cfjh.get(num1).tag==Tag.RBRACE)
        	    f--;
        		num1++;
        	}
    		num = num1--;
    	//	System.out.println(num);
    	//	fhb.leave();
    	}
    	
    }
    void forstat()
    {
    //	if(cfjh.get(num).tag!=Tag.LPRAEN)
    //	{
    //		System.out.println("while后缺少括号");
	//		System.out.println(cfjh.get(num).tag);
	//		System.exit(0);
    //	}
    //	num++;
    //	forinit();
    //	altexpr();
    //	if(cfjh.get(num).tag!=Tag.RPAREN)
    //	{
    //		System.out.println("缺少右括号");
	//		System.out.println(cfjh.get(num).tag);
		//	System.exit(0);
    //	}
   // 	num++;
   // 	block();
    	
    }
   // void forinit()
   // {
   //	
   // }
    void ifstat()
    {
    	fhb.enter();
	   if(cfjh.get(num).tag!=Tag.LPRAEN)
   	{
		   flag = 1;
		   s+=cfjh.get(num).inline;
			s+=":if后缺少括号";
			
			s+='\n';
			//System.exit(0);
   	}
	
	   num++;
	  int flag =  expr().initval;
	  //System.out.println("if判断的结果是"+flag);
	   if(cfjh.get(num).tag!=Tag.RPAREN)
   	{
		   flag = 1;
		   s+=cfjh.get(num).inline;
			s+=":缺少右括号";
			
			s+='\n';
			//System.exit(0);
   	}
   	num++;
   	if(flag!=0)
   	{
   	block();
   	fhb.leave();
   	try{
   	if(cfjh.get(num).tag==Tag.KW_ELSE)
   	{
   		   int f = 1,num1 = num+2;
   		while(f!=0)
    	{
    		if(cfjh.get(num1).tag==Tag.LBRACE)
    		f++;
    		if(cfjh.get(num1).tag==Tag.RBRACE)
    	    f--;
    		num1++;
    	}
		num = num1;
   	}
   	}catch(Exception e){
   		
   	}
   	}
   	else{
   	 int f = 1,num1 = num+1;
		while(f!=0)
 	{
 		if(cfjh.get(num1).tag==Tag.LBRACE)
 		{
 		f++;
 		}
 		if(cfjh.get(num1).tag==Tag.RBRACE)
 		{
 	    f--; 
 		}
 		num1++;  
 	}
		
		num = num1;
   	elsestat();
    }
    }
   void elsestat()
   {
	   if(cfjh.get(num).tag==Tag.KW_ELSE)
	   {
		   num++;
		   fhb.enter();
		   block();	   
		   fhb.leave();
	   }
   }
   
   
   
   String load(var var1)//装载过程
   {
	  String s ="";
	  if(var1.iscl==true)
	  {
		  s+= "    mov al,"+var1.initval+'\n';
	  }
	  else{
		  s+= "    mov al,"+var1.name+'\n';
	  }
	  return s;
   }
   
   String store(var var1)//装载过程
   {
	  String s ="";
	  s += "    mov "+var1.name+",al"+'\n';
	  return s;
   }
   
   
   
   
   
   void fy()
   {
	   code+="DATAS  SEGMENT";
	   code+='\n';
	   for(int i = 0;i < fhb.varname.size();i ++)
	   {
		   code+=fhb.varname.get(i);
		   code+="   ";
		   code+="db  ?";
		   code+='\n';
	   }
	   code+="DATAS  ENDS\nSTACKS  SEGMENT\nDB  128 DUP (?)\nSTACKS  ENDS\nCODES  SEGMENT\nASSUME    CS:CODES,DS:DATAS,SS:STACKS\n";
	   code += "printf:\n" + "push dx\n" + "push cx\n" + "xor cx,cx\n" + "bcd5:\n" + "add al,0 \n" + "aam\n" + "bcd7:\n" + "push ax\n" 
				+ "inc cx\n" + "or ah,ah\n" + "jz bcd20\n" + "cmp ah,0Ah\n" + "jb bcd10\n" + "mov al,ah\n" + "xor ah,ah\n" + "jmp short bcd5\n" + "bcd10:\n"
				+ "mov al,ah\n" + "xor ah,ah\n" + "jmp short bcd7\n" + "bcd20:\n" + "pop dx\n" + "or dl,'0'\n" + "mov ah,2\n" + "int 21h\n" + "loop bcd20\n" + "pop cx\n" + "pop dx\n" + "ret\n";
			  
	   	code+= "main:\nMOV AX,DATAS\nMOV DS,AX\n";
	   
	   for(int i = 0;i < ir.interCode.size();i++)
	   {
		   switch(ir.interCode.get(i).op)
		   {	   
			case OP_AS:
		   {
			   if(ir.interCode.get(i).arg2.name.equals("temp"))
				{
				   code += "    mov ";
				   code += ir.interCode.get(i).arg1.name+','+"al";
				   code +='\n';
				}
			   else{
			code += load(ir.interCode.get(i).arg2);
			code += store(ir.interCode.get(i).arg1);
			   }
			break;
			 
		   }
		   
			case OP_ADD:
			{ 
				
				code += load(ir.interCode.get(i).arg2);
				code += "    add al,";
						if(ir.interCode.get(i).arg1.iscl==true)
						{
							
							code += ir.interCode.get(i).arg1.initval;
						}
						else
						{
							code += ir.interCode.get(i).arg1.name;
						}
				code +='\n';		
				break;
			}
			case OP_SUB:
			{
				code += load(ir.interCode.get(i).arg1);
				code += "    sub al,";
						if(ir.interCode.get(i).arg2.iscl==true)
						{
							
							code += ir.interCode.get(i).arg2.initval;
						}
						else
						{
							code += ir.interCode.get(i).arg2.name;
						}
				code +='\n';		
				break;
			}
			
			case OP_MUL:
			{
				code += load(ir.interCode.get(i).arg1);
			    if(ir.interCode.get(i).arg2.iscl==true)
				{
				code += "    mov bl,";
				code +=ir.interCode.get(i).arg2.initval;
				code +='\n';	
				}
				else
				{
				code += "    mov bl,";
				code += ir.interCode.get(i).arg2.name;
				code +='\n';	
				}
				code += "    imul bl";
				code +='\n';		
				break;
			}
			
			case OP_DIV:
			{
				code += load(ir.interCode.get(i).arg1);
			    if(ir.interCode.get(i).arg2.iscl==true)
				{
				code += "    mov bl,";
				code +=ir.interCode.get(i).arg2.initval;
				code +='\n';	
				}
				else
				{
				code += "    mov bl,";
				code += ir.interCode.get(i).arg2.name;
				code +='\n';	
				}
				code += "    idiv bl";
				code +='\n';		
				break;
			}
			
			case OP_PRINTF:
			{ 
				code += load(ir.interCode.get(i).arg1);
				code+="    call printf"+'\n'+"    mov dl,0ah"+'\n'+"    mov ah,02h"+'\n'+"    int 21h"+'\n';
				break;
			}
			default:
			   {
				   
			   }
		   }   
	   }
	   
	    code +="    quit:"+'\n'+"    MOV AH,4CH"+'\n'+ "    INT 21H"+'\n'+"CODES ENDS"+'\n'+"END main"+'\n';
	   
   }
   
   
   
   
   
}







//365行开始
