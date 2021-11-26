import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class RPNTestt {

	
  @Test
  void function_toString_1_1() {
    float input1 = 1;
    String expected = "1.0";
    String actual = RPNCalculator.toString(input1);
    assertEquals(expected, actual);
  }
  
  @Test
  void function_toFloat_4_4() {
    String input1 = "4.0";
    float expected = 4;
    float actual = RPNCalculator.toFloat(input1);
    assertEquals(expected, actual);
  }
  
  @Test
  void operation_1plus1_2() {
    float operand1 = 1;
    float operand2 = 1;
    String expected = RPNCalculator.toString(operand1 + operand2);
    String actual = RPNCalculator.operation(operand1,operand2,Operator.add);
    assertEquals(expected, actual);
  }

  @Test
  void operation_1plus2_3() {
    float operand1 = 1;
    float operand2 = 2;
    String expected = RPNCalculator.toString(operand1 + operand2);
    String actual = RPNCalculator.operation(operand1,operand2,Operator.add);
    assertEquals(expected, actual);
  }

  @Test
  void operation_3sub2_1() {
    float operand1 = 3;
    float operand2 = 2;
    String expected = RPNCalculator.toString(operand1 - operand2);
    String actual = RPNCalculator.operation(operand1,operand2,Operator.sub);
    assertEquals(expected, actual);
  }

  @Test
  void operation_3div2_1() {
    float operand1 = 3;
    float operand2 = 2;
    String expected = RPNCalculator.toString(operand1 / operand2);
    String actual = RPNCalculator.operation(operand1,operand2,Operator.div);
    assertEquals(expected, actual);
  }
  
  @Test
  void operation_3mul2_6() {
    float operand1 = 3;
    float operand2 = 2;
    String expected = RPNCalculator.toString(operand1 * operand2);
    String actual = RPNCalculator.operation(operand1,operand2,Operator.mul);
    assertEquals(expected, actual);
  }
  
  @Test
  void operation_1frac3_1div3() {
    float operand1 = 1;
    float operand2 = 3;
    String expected = RPNCalculator.toString(operand1 / operand2);
    String actual = RPNCalculator.operation(operand2,operand1,Operator.frac);
    assertEquals(expected, actual);
  }

  @Test
  void operation_9percent9_81() {
    float operand1 = 9;
    float operand2 = 9;
    String expected = RPNCalculator.toString(operand1 * operand2 / 100);
    String actual = RPNCalculator.operation(operand1,operand2,Operator.perc);
    assertEquals(expected, actual);
  }
  
  @Test
  void operation_9sqrt_3() {
    float operand1 = 9;
    float operand2 = 0;
    String expected = RPNCalculator.toString((float) Math.sqrt(operand1));
    String actual = RPNCalculator.operation(operand1,operand2,Operator.sqrt);
    assertEquals(expected, actual);
  }

  
  @Test
  void operation_3pow3_27() {
    float operand1 = 3;
    float operand2 = 3;
    String expected = RPNCalculator.toString((float) Math.pow(operand1, operand2));
    String actual = RPNCalculator.operation(operand1,operand2,Operator.pow);
    assertEquals(expected, actual);
  }
  
  @Test
  void function_empty_54_0() {
	float input1 = 5;
	float input2 = 4;
	RPNCalculator.enter(RPNCalculator.toString(input1));
	RPNCalculator.enter(RPNCalculator.toString(input2));
	boolean expected = true;
	RPNCalculator.ac();
	boolean actual = RPNCalculator.stack.isEmpty();
	assertEquals(expected, actual);
  }

  @Test
  void function_enter_0_0in() {
	float input1 = 0;
	RPNCalculator.enter(RPNCalculator.toString(input1));
	String expected = RPNCalculator.toString(input1);
	String actual = RPNCalculator.readTop();
	RPNCalculator.ac();
	assertEquals(expected, actual);
  }
  
  @Test
  void function_pop_2_2out() {
	float input1 = 2;
	RPNCalculator.enter(RPNCalculator.toString(input1));
	String expected = RPNCalculator.toString(input1);
	String actual = RPNCalculator.pop();
	RPNCalculator.ac();
	assertEquals(expected, actual);
  }

  @Test
  void function_swap_0and2_true() {
	float input1 = 2;
	float input2 = 0;
	RPNCalculator.enter(RPNCalculator.toString(input1));
	RPNCalculator.enter(RPNCalculator.toString(input2));
	boolean expected = true;
	boolean actual = RPNCalculator.swap();
	RPNCalculator.ac();
	assertEquals(expected, actual);
  }
  
  @Test
  void function_addM_2plus0_2() {
	float input1 = 2;
	float m = RPNCalculator.getM();
	RPNCalculator.addM(input1);
	
	float expected = m + input1;
	float actual = RPNCalculator.getM();
	RPNCalculator.ac();
	RPNCalculator.clearM();
	assertEquals(expected, actual);
  }
  
  @Test
  void function_subM_0minus2_neg2() {
	float input1 = 2;
	float m = RPNCalculator.getM();
	RPNCalculator.subM(input1);
	float expected = m - input1;
	float actual = RPNCalculator.getM();
	RPNCalculator.ac();
	RPNCalculator.clearM();
	assertEquals(expected, actual);
  }
  
  @Test
  void function_recallM_0_0() {
	float input1 = 0;
	RPNCalculator.addM(input1);
	float expected = RPNCalculator.getM();
	RPNCalculator.recallM();
	float actual = RPNCalculator.toFloat(RPNCalculator.readTop());
	RPNCalculator.ac();
	RPNCalculator.clearM();
	assertEquals(expected, actual);
  }
  
  @Test
  void function_clearM_2_0() {
	float input1 = 2;
	RPNCalculator.addM(input1);
	float expected = 0;
	RPNCalculator.clearM();
	float actual = RPNCalculator.getM();
	RPNCalculator.ac();
	assertEquals(expected, actual);
  }
  
  @Test
  void function_isOpOrFun_POW_true() {
	String input1 = "POW";
	boolean expected = true;
	boolean actual = RPNCalculator.isOpOrFun(input1);
	RPNCalculator.ac();
	assertEquals(expected, actual);
  }
  
  @Test
  void function_c_552_55() {
	String input1 = "552";
	String expected = "55";
	RPNCalculator.enter(input1);
	RPNCalculator.c();
	String actual = RPNCalculator.readTop();
	RPNCalculator.ac();
	assertEquals(expected, actual);
  }
  
  @Test
  void function_showStack_123_123() {
	String input1 = "1";
	String input2 = "2";
	String input3 = "3";
	String expected = "1\n2\n3\n";
	RPNCalculator.enter(input1);
	RPNCalculator.enter(input2);
	RPNCalculator.enter(input3);
	String actual = RPNCalculator.showStack();
	RPNCalculator.ac();
	assertEquals(expected, actual);
  }
  
  
  
  
}
