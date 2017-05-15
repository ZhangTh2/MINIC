package bianyiqi;

import java.util.LinkedList;

//生成中间代码
public class GenIr {
	LinkedList<InterInst> interCode;//中间代码
	
	String show()
	{
		String s ="";
		for(int i = 0; i <interCode.size();i++)
		{
			s+=interCode.get(i).print();
			s+='\n';
		}
		return s;
	}
var genTwoOp(var lval,Tag opt,var rval)//双目运算符的运算
{
	
	if(opt==Tag.ASSIGN) return genAssigin(lval,rval);
	if(opt==Tag.OR) return genOr(lval,rval);
	if(opt==Tag.AND) return genAnd(lval,rval);
	if(opt==Tag.EQU) return genEqu(lval,rval);
	if(opt==Tag.NEQU) return genNequ(lval,rval);
	if(opt==Tag.ADD) return genAdd(lval,rval);
	if(opt==Tag.SUB) return genSub(lval,rval);
	if(opt==Tag.GT) return genGt(lval,rval);
	if(opt==Tag.GE) return genGe(lval,rval);
	if(opt==Tag.LT) return genLt(lval,rval);
	if(opt==Tag.LE) return genLe(lval,rval);
	if(opt==Tag.MUL) return genMul(lval,rval);
	if(opt==Tag.DIV) return genDiv(lval,rval);
	if(opt==Tag.MOD) return genMod(lval,rval);
	else
		return lval;
}




var genAssigin(var lval,var rval)
{
	interCode.add(new InterInst(Operator.OP_AS,lval,rval));
	lval.initval = rval.initval;
	return rval;
}
void genwrite(var var1)
{
	interCode.add(new InterInst(Operator.OP_PRINTF,var1));
}


var genOr(var lval,var rval)
{
	var temp;
	interCode.add(new InterInst(Operator.OP_OR,lval,rval));
	if(lval.initval==0&&rval.initval==0)
	temp = new var("temp",0);
	else  temp = new var("temp",1);	
	return temp;
}


var genAnd(var lval,var rval)
{
	var temp;
	interCode.add(new InterInst(Operator.OP_AND,lval,rval));
	if(lval.initval==1&&rval.initval==1)
	temp = new var(1);
	else  temp = new var(0);	
	return temp;
}


var genEqu(var lval,var rval)
{
	var temp;
	interCode.add(new InterInst(Operator.OP_EQU,lval,rval));
	if(lval.initval==rval.initval)
	temp = new var(1);
	else  temp = new var(0);	
	return temp;
}


var genNequ(var lval,var rval)
{
	var temp;
	interCode.add(new InterInst(Operator.OP_NE,lval,rval));
	if(lval.initval==rval.initval)
	temp = new var(0);
	else  temp = new var(1);	
	return temp;
}


var genAdd(var lval,var rval)
{
	var temp;
	interCode.add(new InterInst(Operator.OP_ADD,lval,rval));
	int t = lval.initval+rval.initval;
	temp = new var("temp",t);
	return temp;
}

var genSub(var lval,var rval)
{
	var temp;
	interCode.add(new InterInst(Operator.OP_SUB,lval,rval));
	int t = lval.initval-rval.initval;
	temp = new var("temp",t);
	return temp;
}


var genGt(var lval,var rval)
{
	var temp;
	interCode.add(new InterInst(Operator.OP_GT,lval,rval));
	if(lval.initval>rval.initval)
	temp = new var(1);
	else
	temp = new var(0);
	return temp;
}


var genGe(var lval,var rval)
{
	var temp;
	interCode.add(new InterInst(Operator.OP_GE,lval,rval));
	if(lval.initval>=rval.initval)
	temp = new var(1);
	else
	temp = new var(0);
	return temp;
}

var genLt(var lval,var rval)
{
	var temp;
	interCode.add(new InterInst(Operator.OP_LT,lval,rval));
	if(lval.initval<rval.initval)
	temp = new var(1);
	else
	temp = new var(0);
	return temp;
}


var genLe(var lval,var rval)
{
	var temp;
	interCode.add(new InterInst(Operator.OP_LE,lval,rval));
	if(lval.initval<=rval.initval)
	temp = new var(1);
	else
	temp = new var(0);
	return temp;
}


var genMul(var lval,var rval)
{
	var temp;
	interCode.add(new InterInst(Operator.OP_MUL,lval,rval));
	int t = lval.initval*rval.initval;
	temp = new var("temp",t);
	return temp;
}


var genDiv(var lval,var rval)
{
	var temp;
	interCode.add(new InterInst(Operator.OP_DIV,lval,rval));
	int t = lval.initval/rval.initval;
	temp = new var("temp",t);
	return temp;
}

var genMod(var lval,var rval)
{
	var temp;
	interCode.add(new InterInst(Operator.OP_MOD,lval,rval));
	int t = lval.initval%rval.initval;
	temp = new var("temp",t);
	return temp;
}





var genOneOp(var val,Tag opt)
{
	
	if(opt==Tag.NOT) return genNot(val);
	if(opt==Tag.SUB) return genNeg(val);
	else 
		return val;
}

var genNot(var val)
{
	var temp =val;
	if(temp.initval==1)  temp.initval=0;
	else temp.initval=1;
	interCode.add(new InterInst(Operator.OP_NOT,val));
	return temp;	
}

var genNeg(var val)
{


//	var temp =val;
//    temp.initval = temp.initval*(-1);
//    System.out.println(val.initval);
//    val.initval  = val.initval *(-1);
  // System.out.println(val.initval);
    interCode.add(new InterInst(Operator.OP_NEG,val));
   val.initval  = val.initval *(-1);
  // System.out.println(val.initval);
	return val;
	
}




var genOneOpRight(var val,Tag opt)
{
	if(opt==Tag.INC) return genInc(val);
	if(opt==Tag.DEC) return genDe(val);
    else
	return val;
}

var genInc(var val)
{
	var temp= new var("temp",val.initval);
	 val.initval++;
	interCode.add(new InterInst(Operator.OP_INC,val));
	return temp;
}

var genDe(var val)
{
	var temp= new var("temp",val.initval);
	interCode.add(new InterInst(Operator.OP_DE,val));
    val.initval--;
 
	return temp;
}


}
