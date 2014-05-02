package org.asdtiang.play;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class RuleOneTest {
	
	@Test
	public void test(){
		Map<Integer,String> mapStr = new HashMap<Integer,String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 58454014315607107L;
			{
				put(0, "Fizz");
				put(1, "Buzz");
				put(2, "Whizz");
			}
		};
		Person person = new Person();
		RuleInterface ruleOne = new RuleOneImpl();
		int[] testNumbers = new int[]{3,5,7};
		for(int i=0;i<testNumbers.length;i++){
			person.setResult("");
			person.setNumber(testNumbers[i]);
			ruleOne.doRule(person, testNumbers,mapStr);
			assertEquals(mapStr.get(i),person.getResult());
		}
		person.setResult("");
		person.setNumber(3*5);
		ruleOne.doRule(person, testNumbers,mapStr);
		assertEquals(mapStr.get(0)+mapStr.get(1),person.getResult());
		
		person.setResult("");
		person.setNumber(5*7);
		ruleOne.doRule(person, testNumbers,mapStr);
		assertEquals(mapStr.get(1)+mapStr.get(2),person.getResult());
		
		person.setResult("");
		person.setNumber(3*5*7);
		ruleOne.doRule(person, testNumbers,mapStr);
		assertEquals(mapStr.get(0)+mapStr.get(1)+mapStr.get(2),person.getResult());
		
	}

}
