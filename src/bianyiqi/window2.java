package bianyiqi;
import java.awt.*;
import javax.swing.*;
public class window2 extends JFrame{

	JLabel cf;
	JLabel in;
	JLabel hb;
	JTextArea cfa;
	JTextArea ina;
	JTextArea hba;
	void init()
	{
	    this.setBounds(800, 200, 800, 700);
		Font font=new Font("华文行楷",Font.ITALIC|Font.BOLD,20);
		//this.setSize(800, 700);
	    setTitle("编译器");
		setLayout(null);
		cf = new JLabel();
		cf.setText("词法记号");
		cf.setEnabled(false);
		cf.setBounds(0, 0, 200, 40);
		cf.setFont(font);
		in = new JLabel();
		in.setText("中间代码");
		in.setEnabled(false);
		in.setBounds(200, 0, 200, 40);
		in.setFont(font);
		hb = new JLabel();
		hb.setText("汇编代码");
		hb.setEnabled(false);
		hb.setBounds(400, 0, 400, 40);
		hb.setFont(font);
		cfa = new JTextArea();
		JScrollPane jsp1 = new JScrollPane(cfa);
		jsp1.setBounds(0, 40, 200, 660);
		ina= new JTextArea();
		JScrollPane jsp2 = new JScrollPane(ina);
		jsp2.setBounds(200, 40, 200, 660);
		hba= new JTextArea();
		JScrollPane jsp3 = new JScrollPane(hba);
		jsp3.setBounds(400, 40, 400, 660);
		add(cf);
		add(in);
		add(hb);
		add(jsp1);
		add(jsp2);
		add(jsp3);
	}
	public window2()
	{
		init();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
