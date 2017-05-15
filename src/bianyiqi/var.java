package bianyiqi;

import java.util.LinkedList;
import java.util.ArrayList;
//变量类型 
public class var {
boolean iscl = false;//是否常量
ArrayList<Integer> zyylj  = new ArrayList<Integer>();
Tag type;//类型
String name;//名称
boolean isarry = false;//是否数组
int arraySize;//数组长度
boolean isleft = true;//是否可以作为左值

//初值
boolean isinit = false;//是否初始化
int initval;//三种类型变量初值
char initchar;
String initstr;
int size;//变量大小
int offset;//栈帧偏移
 static int x = 0;//有多少字符串
//构造函数
 var()
 {
	
	 
 }
 var(int a)
 {
	  iscl = true;
	  type = Tag.KW_INT;
	  isleft = false;
	  initval = a;
 }
 var(String name,int a)
 {
	  this.name  = name;
	  iscl = true;
	  type = Tag.KW_INT;
	  isleft = false;
	  initval = a;
 }
 var(Token t)
{
	iscl=true;
	isleft=false;
	isinit = true;
	switch(t.tag)
	{
	case NUM:
		type = Tag.KW_INT;
		name ="<int>";
		initval = t.val;
		break;
	case CH:
		type = Tag.KW_CHAR;
		name ="<char>";
		initchar = t.ch;
		break;
	case STR:
		type = Tag.KW_STRING;
		name ="string"+String.valueOf(x);
		isarry = true;
		arraySize = t.s.length();
		initstr = t.s;
		break;
		default:
			
	}
	
}

}
