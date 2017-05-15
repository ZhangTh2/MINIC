package bianyiqi;

//创建窗口类
import java.awt.*;
import javax.swing.*;
public class Window extends JFrame{

	public static String chengxu;
	JLabel t1;
	JLabel t2;
	JTextArea text1;
	JTextArea text2;
	JButton button;
	Listen listener;
	void init()
	{
	   this.setBounds(400, 200, 800, 700);
		Font font=new Font("华文行楷",Font.ITALIC|Font.BOLD,20);
		//this.setSize(800, 700);   this.setFont(font);
	    setTitle("编译器");
	 
		setLayout(null);
		
		t1=new JLabel();
		t1.setText("请输入 要编译的代码");
		t1.setBounds(0, 0, 550, 40);
		t1.setFont(font);
		
		text1 = new JTextArea();
		JScrollPane jsp=new JScrollPane(text1);
		jsp.setBounds(0, 40, 800, 450);
		button  = new JButton("编译");
		button.setFont(font);
		button.setBounds(710,0, 90, 40);
		
		t2=new JLabel();
		t2.setText("运行结果");
		t2.setBounds(0, 490, 550, 40);
		t2.setFont(font);
		text2 = new JTextArea();
		text2.setEnabled(false);
		JScrollPane jsp2=new JScrollPane(text2);
		jsp2.setBounds(0, 530, 800, 175);
	
		add(jsp);
		add(button);
		add(t1);
		add(t2);
		add(jsp2);
	}
	public Window()
	{
		init();this.setBackground(Color.BLUE);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void setListener(Listen listener)
	{
		this.listener = listener;
		listener.getText(text1);
		listener.setText(text2);
		button.addActionListener(listener);
	}
	
}
