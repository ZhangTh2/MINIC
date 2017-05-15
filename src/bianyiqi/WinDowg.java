package bianyiqi;
//失败的布局尝试
import java.awt.*;
import javax.swing.*;
public class WinDowg extends JFrame{

	JTextField t1;
	JTextField t2;
	JTextArea text1;
	JTextArea text2;
	JButton button;
	Listen listener;
	void init()
	{
		
		t1 = new JTextField();
		t1.setEnabled(false);
		t1.setText("请在此输入要进行编译的代码");
		t2 = new JTextField();
		t2.setText("程序运行结果如下");
		t2.setEnabled(false);
		text1 = new JTextArea(800,400);
		button  = new JButton("编译");
		text2 = new JTextArea(800,100);
		GridBagLayout layout = new GridBagLayout();
	    this.setLayout(layout);
	    add(t1);
	    add(button);
	    add(text1);
	    add(t2);
	    add(text2);
	    
	    
	    GridBagConstraints s= new GridBagConstraints();//定义一个GridBagConstraints，
        //是用来控制添加进的组件的显示位置
        s.fill = GridBagConstraints.HORIZONTAL;
        //该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //NONE：不调整组件大小。
        //HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        //VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        //BOTH：使组件完全填满其显示区域。
        s.gridheight = 1;
        s.gridwidth=0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(t1, s);//设置组件
        s.gridwidth=0;
        s.gridheight = 1;
        s.weightx =0 ;
        s.weighty=0;
        layout.setConstraints(button, s);
        s.gridwidth=0;
        s.gridheight = 3;
        s.weightx = 1;
        s.weighty=1;
        layout.setConstraints(text1, s);
        s.gridwidth=0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.gridheight = 1;
        s.weightx = 0;//不能为1，j4是占了4个格，并且可以横向拉伸，
        //但是如果为1，后面行的列的格也会跟着拉伸,导致j7所在的列也可以拉伸
        //所以应该是跟着j6进行拉伸
       s.weighty=0;
        layout.setConstraints(t2, s);
        s.gridwidth=0;
        s.gridheight = 1;
       s.weightx = 1;
        s.weighty=1;
        layout.setConstraints(text2, s);
	}
	public WinDowg()
	{
		init();
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