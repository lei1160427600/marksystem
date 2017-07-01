package cn.edu.henau.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.henau.serviceimpl.TestSpringImpl;

public class TestIoc {
	@Test
	public void testSp(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		TestSpringImpl testSpringImpl = (TestSpringImpl) context.getBean("testSpringImpl");
		String a = testSpringImpl.sayHello();
		System.out.println(a);
	}
}
