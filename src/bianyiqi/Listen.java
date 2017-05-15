package bianyiqi;

//对按钮添加的监听事件


import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class Listen implements ActionListener{
	JTextArea text1;
	JTextArea text2;
public void getText(JTextArea text)
{
	text1 = text;
}
public void setText(JTextArea text)
{
	text2 = text;
}



public void actionPerformed(ActionEvent e)
{
	//文本框中内容读入到文件
	//try{
	//File f = new File("D:/bianyi1/程序.txt");
	//FileWriter fw = new FileWriter(f);
	//fw.write(text1.getText());
	//fw.close();
	//}
// catch(Exception e1){
//	 text2.append("文件写入错误");
// }
  
	
	//建立词法 记号链表
	cffxq.line = 0;
	LinkedList<Token> cfjh  = new LinkedList<Token>();
	Window.chengxu = String.valueOf(text1.getText());
	cffxq cffxq1 = new cffxq(cfjh,text1.getText());
	//词法记号输出一下
	//text2.append(cffxq1.printcfjh(cfjh));
	
	
	yfzdfy parser = new yfzdfy(cfjh);
	
	text2.setText("");
	parser.analyze();
	//if(parser.flag==0){
	text2.append("运行成功"+'\n');
	if(parser.iswrite==1)
	text2.append(parser.res);
	window2 win2 = new window2();
	parser.fy();
	win2.cfa.append(cffxq1.printcfjh(cfjh));
	win2.ina.append(parser.ir.show());
	win2.hba.append(parser.code);
//	}
//	else{
//		text2.append("存在错误"+'\n');
//		text2.append(parser.s);
//	}
}
}
