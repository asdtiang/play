package org.asdtiang.play;


import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
public class RuleTwoTest {
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
		RuleInterface ruleTwo = new RuleTwoImpl();
		int[] testNumbers = new int[]{3,5,7};
		person.setResult("");
		person.setNumber(35);
		ruleOne.doRule(person, testNumbers,mapStr);
		ruleTwo.doRule(person, testNumbers,mapStr);
		assertEquals(mapStr.get(0),person.getResult());
		
		person.setResult("");
		person.setNumber(13);
		ruleOne.doRule(person, testNumbers,mapStr);
		ruleTwo.doRule(person, testNumbers,mapStr);
		assertEquals(mapStr.get(0),person.getResult());
		
	}
}
