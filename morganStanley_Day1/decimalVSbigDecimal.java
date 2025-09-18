package morganStanley_Day1;

import java.math.BigDecimal;

public class decimalVSbigDecimal {

	public static void main(String[] args) {
		
		double d1 = 0.1;
		double d2 = 0.2;
		double d3 = d1 + d2;
		BigDecimal bd1 = new BigDecimal(0.1);
		BigDecimal bd2 = new BigDecimal("0.1");
		BigDecimal bd3 = BigDecimal.valueOf(0.1);
		BigDecimal bd4 = new BigDecimal(0.2);
		BigDecimal bd5 = new BigDecimal("0.2");
		BigDecimal bd6 = BigDecimal.valueOf(0.2);
		BigDecimal bd7 = bd1.add(bd4);
		BigDecimal bd8 = bd2.add(bd5);
		BigDecimal bd9 = bd3.add(bd6);
		
		System.out.println("Double value: "+d1);
		System.out.println("Operation performed on Double values: "+d3);
		System.out.println("Big Decimal value with constructor having double: "+bd1);
		System.out.println("Big Decimal value with constructor having string: "+bd2);
		System.out.println("Big Decimal value with valueOf having double: "+bd3);
		System.out.println("Operation performed on BigDecimal values with double: "+bd7);
		System.out.println("Operation performed on BigDecimal values with string: "+bd8);
		System.out.println("Operation performed on BigDecimal values with valueOf: "+bd9);
		
	}

}
