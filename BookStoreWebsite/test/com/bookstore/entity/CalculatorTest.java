package com.bookstore.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testAdd() {
		//fail("Not yet implemented");
		Calculator calculator=new Calculator();
		int a=1234;
		int b=567;
		int result=calculator.add(a, b);
		int expected=1801;
		assertEquals(expected,result);
	}

}
