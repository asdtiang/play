package org.asdtiang.play;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Play {
	
	private static Play play = new Play();
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
	private Map<Integer, Person> resultMap;
	private AtomicInteger sumPersonAtom = null;
	private int sumPerson = 0;
	private ExecutorService executorService = Executors.newFixedThreadPool(1);
	private RuleInterface ruleOne = new RuleOneImpl();
	private RuleInterface ruleTwo = new RuleTwoImpl();
	private Logger log = LoggerFactory.getLogger(Play.class);
	private long startTime =0;
	private String key = "";
	private CacheInterface cache = new DefaultCache();
	private PlayFinishInterface playFinish = new PlayFinishInterface() {
		@Override
		public void callBack(Map<Integer, Person> resultMap) {
			long useTime = System.currentTimeMillis() - startTime;
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 1; i <= sumPerson; i++) {
				stringBuilder.append(resultMap.get(i).getResult() + "\n");
			}
			stringBuilder.append("use time:" + useTime);
			log.info(stringBuilder.toString());
		}

	};
	
	public static Play genPaly(){
		return play;
	}

	public boolean validate(int sumPerson, int[] numbers) {
		boolean argRight = true;
		if (sumPerson > 0 && numbers.length == mapStr.size()) {
			for(int i=0;i<numbers.length;i++){
				if(argRight==false){
					break;
				}
				for(int j=i+1;j<numbers.length;j++){
					if(numbers[i] == numbers[j]||numbers[i]==0){
						argRight = false;
						break;
					}
				}
			}
		}
		return argRight;
	}

	@SuppressWarnings("unchecked")
	public void play(int sumPerson, final int[] numbers) {
		if (!validate(sumPerson, numbers)) {
			throw new IllegalArgumentException("input is error!");
		} else {
			this.sumPerson = sumPerson;
			this.key = this.genKey(sumPerson, numbers);
			startTime = System.currentTimeMillis();
			Object cacheResult = cache.get(key);
			if ( cacheResult!= null) {
				playFinish.callBack((Map<Integer, Person>) cacheResult);
			} else {
				sumPersonAtom = new AtomicInteger(sumPerson);
				resultMap = new ConcurrentHashMap<Integer, Person>();
				for (int i = 1; i <= sumPerson; i++) {
					final Person person = new Person(i);
					resultMap.put(i, person);
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							ruleOne.doRule(person, numbers,mapStr);
							ruleTwo.doRule(person, numbers,mapStr);
							isFinish(sumPersonAtom.decrementAndGet());
						}
					});
				}
			}
		}
	}

	public String genKey(int sumPerson, final int[] numbers) {
		String key = sumPerson + "";
		for (int i : numbers) {
			key = key + "-" + i;
		}
		return key;
	}

	public void isFinish(int count) {
		if (count == 0) {
			Map<Integer, Person> cacheMap = new HashMap<Integer, Person>();
			cacheMap.putAll(resultMap);
			cache.set(key, cacheMap);
			playFinish.callBack(resultMap);
		}
	}

	public PlayFinishInterface getPlayFinish() {
		return playFinish;
	}

	public void setPlayFinish(PlayFinishInterface playFinish) {
		this.playFinish = playFinish;
	}

	public CacheInterface getCache() {
		return cache;
	}

	public void setCache(CacheInterface cache) {
		this.cache = cache;
	}

	public Map<Integer, String> getMapStr() {
		return mapStr;
	}

	/**
	 * change mapStr
	 * @param mapStr
	 */
	public void setMapStr(Map<Integer, String> mapStr) {
		this.mapStr = mapStr;
	}
}
