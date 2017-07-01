package cn.edu.henau.serviceimpl;

import org.springframework.stereotype.Repository;

import cn.edu.henau.service.TestSpring;

@Repository
public class TestSpringImpl implements TestSpring {

	@Override
	public String sayHello() {
		// TODO Auto-generated method stub
		return "Hello Spring Ioc!";
	}

}
