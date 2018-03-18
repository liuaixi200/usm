package com.vanke.utils;

import com.lwx.usm.utils.DistributeLockUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author liuax01
 * @Date 2018/1/23 10:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-dao-test.xml" })
public class DistributeLockUtilTest {

	private static int ina=0;

	@Test
	public void test分布锁() throws InterruptedException {
		final DistributeLockUtilTest dl = this;
		for(int i=0;i<100;i++){
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					dl.test1();

				}
			});
			th.start();

		}
		Thread.sleep(2000);

	}

	public void test1(){
		DistributeLockUtil.lock("test");
		ina++;

		try {
			Thread.sleep(100);
			System.out.println("ina="+ina);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		DistributeLockUtil.unlock("test");
	}
}
