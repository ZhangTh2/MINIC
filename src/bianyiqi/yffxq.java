package bianyiqi;
//错的尝试
import java.util.LinkedList;

public class yffxq {

	LinkedList<Token> cfjh  = new LinkedList<Token>();
	LinkedList<bl> blb  = new LinkedList<bl>();
	public yffxq(LinkedList<Token> cfjh)
	{
		this.cfjh = cfjh;
	}
	public void yffx()
	{
		int i = 0;
		if(cfjh.get(0).output().equals("begin"))
		{
			i++;
			program(i);
		}
		else {
 		      System.out.println("缺少开始记号 ");
		      System.exit(0);  
		}
	}
	public void program(int i)
	{
		Keywords key = new Keywords();
		int i1 = i;
		if(cfjh.get(i).tag==null)
		{
			System.out.println("缺少关键字 ");
		      System.exit(0); 
		}
		switch(cfjh.get(i1++).tag)
		{
		case KW_INT:
	      INT(i1);
	      break;
	      
		}
	}
	public void INT(int i)
	{
		int i1 = i;
		blb.add(new bl(cfjh.get(i1++).output()));
		if(cfjh.get(i1++).output().equals(","))
		{
			INT(i1);
		}
		else if(cfjh.get(i1++).output().equals(";"))
		{
			program(i1);
		}
		else if(cfjh.get(i1++).output().equals("="))
		{
			if(cfjh.get(i1++) instanceof Num) 
			{
				blb.getLast().n = Integer.valueOf(cfjh.get(i1++).output());
			}
			else
			{
			   
			}
		}
	}
}
