package org.asdtiang.play;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RuleOneImpl implements RuleInterface{
	private Logger log = LoggerFactory.getLogger(RuleOneImpl.class);
	@Override
	public void doRule(Person person, int[] numbers,Map<Integer,String> mapStr) {
		if(null!=numbers&&numbers.length==mapStr.size()){
			boolean isNormal = true;
			for(int i=0;i<3;i++){
				if(person.getNumber()%numbers[i]==0){
					person.setResult(person.getResult()+mapStr.get(i));
					isNormal = false;
				}
			}
			if(isNormal==true){
				person.setResult(person.getNumber()+"");
			}
		}else{
			log.warn("input numbers is wrong!!!");
		}
	}

}
