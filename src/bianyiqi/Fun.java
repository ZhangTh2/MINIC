package bianyiqi;

import java.util.ArrayList;
import java.util.LinkedList;

public class Fun {
Tag type;//返回类型
String name;//函数名称
LinkedList<var> paraVar;//形参列表
int maxdeep;//栈的最大深度
int curEsp; //esp现在指向的位置
ArrayList<Integer> zyyEsp  = new ArrayList<Integer>();//作用域 指针位置

LinkedList<InterInst> interCode = new LinkedList<InterInst>();//中间代码
InterInst returnPoint;//返回点

//构造函数
Fun(Tag t,String name,LinkedList<var> paraList)
{
	type = t;
	this.name  = name;
	paraVar = paraList;
	curEsp = 0;
	maxdeep = 0;
	for(int i = 0,argoff=8;i<paraList.size();i++,argoff+=4)
	{
		paraVar.get(i).offset = argoff;
	}
		//为每一个实际参数计算偏移地址以得到  
}


//目标代码类串
//返回点
public void enterZyy()
{
	zyyEsp.add(0);
}
public void leaveZyy()
{
	if(curEsp> maxdeep)
		maxdeep = curEsp;
        curEsp = curEsp -zyyEsp.get(zyyEsp.size()-1);
        zyyEsp.remove(zyyEsp.size()-1);
		
}
}
