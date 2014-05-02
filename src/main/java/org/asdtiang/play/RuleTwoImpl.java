package org.asdtiang.play;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleTwoImpl implements RuleInterface{
	private Logger log = LoggerFactory.getLogger(RuleTwoImpl.class);
	@Override
	public void doRule(Person person, int[] numbers,Map<Integer,String> mapStr) {
		if(null!=numbers&&numbers.length==mapStr.size()){
			String str = person.getNumber()+"";
			if(str.contains(numbers[0]+"")){
				person.setResult(mapStr.get(0));
			}
		}else{
			log.warn("input numbers is wrong!!!");
		}
	}
}
