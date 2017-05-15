package bianyiqi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

//语法制导翻译中用的符号表 
public class fhb {
	
	public  HashMap<String, LinkedList<var>> vartab = new HashMap<String,LinkedList<var>>();
	public  HashMap<String, String> strtab = new HashMap<String,String>();
	public  HashMap<String, Fun> funtab = new HashMap<String,Fun>();
	public LinkedList<String> varname = new LinkedList<String>();
	Fun curfun;//现在分析的函数
	int zyyID;//作用域编号
	ArrayList<Integer> zyylj = new ArrayList<Integer>();//作用域 路径 
	fhb()
	{
		zyyID = 0;
		zyylj.add(0);
	}
	//添加变量对象
	void addVar(var v)
	{
		varname.add(v.name);
		if(vartab.get(v.name)==null)
		{	LinkedList<var>  varlist= new LinkedList<var>();
		     varlist.add(v);
			vartab.put(v.name,varlist);
		}
		else
		{
			vartab.get(v.name).add(v);
		}
	}
	var getvar(String name)
	{
		//System.out.println(name);
		var select = null;
		if(vartab.get(name)!=null)
		{
			LinkedList<var> list = vartab.get(name);
//			int path = zyylj.size();
//			int maxlen = 0;
//			for(int i = 0; i < list.size();i++)
//			{
//				int len = list.get(i).zyylj.size();
//				if(len<=path)
//				{
//					if(len>maxlen)
//					{
//						maxlen = len;
//						select = list.get(i);
//					}
//				}
//			}
			select = list.get(0);
		}
		if(select==null)
		{
	
		//	System.exit(0);
		}
		return select;
	}
	//添加函数三函数之函数声明
	void decFun(Fun fun)
	{
		if(funtab.get(fun.name)==null)//代表函数表中未有此函数
		{
			funtab.put(fun.name,fun);
		}
		else
		{
			System.out.println("已有同名函数 ");
			System.exit(0);
		}
			
	}
	//添加函数三函数之函数定义
	void defFun(Fun fun)
	{
		if(funtab.get(fun.name)==null)//代表函数表中未有此函数
		{
			funtab.put(fun.name,fun);
		}
		curfun=fun;
		
		//产生函数入口 未完成
		
	}
	void enddefFun()
	{
		//产生函数出口未完成
		curfun=null;
	}
	
	
	//两个函数完成对作用域的管理
	void enter()//进入局部作用域
	{
		zyyID++;
		zyylj.add(zyyID);
		if(curfun!=null)
		curfun.enterZyy();
			
		//进入函数
	}
    void leave()//离开
    {
    	zyylj.remove(zyylj.size()-1);
    	//未完成，离开函数
    	if(curfun!=null)
    	curfun.leaveZyy();
    	
    }
}
