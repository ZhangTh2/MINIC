package bianyiqi;
//中间代码中的运算符
public enum Operator {

	OP_NOP,  // 空指令
	OP_DEC,   //声明
	OP_ENTRY,OP_EXIT,//函数出入口
	OP_AS,//赋值
	OP_ADD,OP_SUB,OP_MUL,OP_DIV,OP_MOD,//算数运算
	OP_NEG,//取负
	OP_GT,OP_GE,OP_LT,OP_LE,OP_EQU,OP_NE,//关系运算
	OP_NOT,//非
	OP_AND,OP_OR,//与或
	OP_INC,OP_DE,
	OP_LEA,//取址
	OP_SET,OP_GET,//无用的  指针运算
	OP_JMP,//条件跳转
	OP_JT,OP_JF,OP_JNE,//参数传递
	OP_CALL,//直接返回
	OP_RET,OP_RETV,//有返回值的返回
	OP_PRINTF
}
