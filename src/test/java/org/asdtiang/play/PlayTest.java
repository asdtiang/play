package org.asdtiang.play;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayTest {
	@Test
	public void test() throws InterruptedException{
		Play play = Play.genPaly();
		play.play(200, new int[]{3,5,7});
		Thread.sleep(5000);
		play.play(200, new int[]{3,5,7});
		try {
			Thread.sleep(5000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
