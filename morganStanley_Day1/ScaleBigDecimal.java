package morganStanley_Day1;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ScaleBigDecimal {

	public static void main(String[] args) {
		
		double[] doubles = {12345.12345467, 12345.123456, 12345.123455};
		double[] expectedDoubles = {12345.12345, 12345.12346, 12345.12346};
		
		for(int i=0;i< doubles.length;i++) {
			BigDecimal bd = new BigDecimal(String.valueOf(doubles[i]));
			BigDecimal bdRounded = bd.setScale(5, RoundingMode.HALF_UP);
			BigDecimal bdExpected = new BigDecimal(String.valueOf(expectedDoubles[i]));
			
			System.out.println("Test Input: "+(i+1));
			System.out.println("Before Rounding: "+bd);
			System.out.println("After Rounding: "+bdRounded);
			System.out.println("Expected Value: "+bdExpected);
			
			if(bdRounded.compareTo(bdExpected)==0) {
				System.out.println("Values Matched");
			}
			else {
				System.out.println("Values Not Matched");
			}
			System.out.println();
		}

	}

}
