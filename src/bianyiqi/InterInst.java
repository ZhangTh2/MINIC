package bianyiqi;
//对中间代码的数据结构描述
public class InterInst {

	String label;
	Operator op;
	var result;
	var arg1;
	var arg2;
	Fun fun;
	InterInst target;
	
	InterInst(Operator opt,var lval,var rval)//双目运算的中间代码
	{
		op =opt;
		arg1 = lval;
		arg2 = rval;
	}
	
	InterInst(Operator opt,var val)//单目运算的中间代码
	{
		op = opt;
		arg1 = val;
		arg2 = null;
	}
	String print()
	{
		String s = "";
		s+=op.toString();
		//System.out.println("操作符是"+op.toString());
		s+=',';
		if(arg1!=null)
		{
			if(arg1.iscl==false)
			s+=arg1.name;
			//System.out.println("左值是"+arg1.toString());
			else
				s+=arg1.initval;
			s+=' ';
		}
		if(arg2!=null)
		{
			if(arg2.iscl==false)
				s+=arg2.name;
				//System.out.println("左值是"+arg1.toString());
				else
					s+=arg2.initval;
		//	System.out.println("右值是"+arg2.toString());
			s+=' ';
		}
		return s;
	}
}
