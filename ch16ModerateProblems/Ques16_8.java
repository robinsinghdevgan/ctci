import java.math.BigInteger;

public class Ques16_8 {

    final static Integer aMillion = 1000000;
    final static Integer aBillion = 1000000000;
    final static Integer aBillionMinusOne = 999999999;
    final static BigInteger aTrillionMinusOne = new BigInteger("999999999999");
    
    static String oneLessThanATrillion(BigInteger x) {
		if (x.compareTo(aTrillionMinusOne) >= 0)
            return "";
        Integer finalThreePlaces = x.divide(BigInteger.valueOf(aBillion.intValue())).intValue();
        Integer millionValue = x.mod(BigInteger.valueOf(aBillion.intValue())).intValue();
		String res = oneToNineNinetyNine(finalThreePlaces) + " Billion, " + oneLessThanABillion(millionValue);
		return res;
	}
    
	static String oneLessThanABillion(Integer x) {
		if (x > aBillionMinusOne)
			return "";
		String res = oneToNineNinetyNine(x/aMillion) + " Million, " + oneLessThanAMillion(x%aMillion);
		return res;
	}

    static String oneLessThanAMillion(Integer x) {
		if (x > 999999)
			return "";
		String res = oneToNineNinetyNine(x/1000) + " Thousand, " + oneToNineNinetyNine(x%1000);
		return res;
	}

	static String oneToNineNinetyNine(Integer x) {
		if (x > 999)
			return "";
		if (x < 100)
			return oneToNinetyNine(x);
		Integer hundredthPlace = x/100;
		Integer decimalAndUnitPlace = ((x/10)%10)*10 + x%10;
		
		String res = oneToNine(hundredthPlace) + " Hundred " + oneToNinetyNine(decimalAndUnitPlace);
		return res;
	}		
	
    static String oneToNinetyNine(Integer x) {
	if (x > 99)
		return "";
	String res = "";
	if (x > 19) {
		res = twentyToNinetyNine(x);
	}
	else if (x <= 19) {
		res = oneToNineteen(x);
	}
	return res;
}

	static String twentyToNinetyNine(Integer x) {
		if (x < 19)
			return "";
		String decimalPlaceString = "";
		Integer unitPlace = x%10;
		
		if (x > 19 && x < 30) {
			decimalPlaceString = "Twenty";
		}
		else if (x > 29 && x < 40) {
			decimalPlaceString = "Thirty";
		}
		else if (x > 39 && x < 50) {
			decimalPlaceString = "Forty";
		}
		else if (x > 49 && x < 60) {
			decimalPlaceString = "Fifty";
		}
		else if (x > 59 && x < 70) {
			decimalPlaceString = "Sixty";
		}
		else if (x > 69 && x < 80) {
			decimalPlaceString = "Seventy";
		}
		else if (x > 79 && x < 90) {
			decimalPlaceString = "Eighty";
		}
		else if (x > 89 && x < 100) {
			decimalPlaceString = "Ninety";
		}
		
		String res = decimalPlaceString + " " + oneToNine(unitPlace);
		return res;
	}

	static String oneToNineteen(Integer x) {
		if (x > 19)
			return "";
		String res = "";
		if (x < 10) {
			res = oneToNine(x);
		}
		else {
			res = tenToNineteen(x);
		}
		return res;
	}

	static String oneToNine(Integer x) {
		if (x > 9 || x <= 0)
			return "";
		String res = "";
		switch(x) {
			case 1: res = "One"; break;
			case 2: res = "Two"; break;
			case 3: res = "Three"; break;
			case 4: res = "Four"; break;
			case 5: res = "Five"; break;
			case 6: res = "Six"; break;
			case 7: res = "Seven"; break;
			case 8: res = "Eight"; break;
			case 9: res = "Nine"; break;
		}
		return res;
	}

	static String tenToNineteen(Integer x) {
		if (x > 19)
			return "";
		String res = "";
		switch(x) {
			case 10: res = "Ten"; break;
			case 11: res = "Eleven"; break;
			case 12: res = "Twelve"; break;
			case 13: res = "Thirteen"; break;
			case 14: res = "Fourteen"; break;
			case 15: res = "Fifteen"; break;
			case 16: res = "Sixteen"; break;
			case 17: res = "Seventeen"; break;
			case 18: res = "Eighteeen"; break;
			case 19: res = "Nineteen"; break;
		}
		return res;
	}
  public static void main(String[] args) {
      for (BigInteger i = new BigInteger("999999999890"), tillBigInteger = new BigInteger("999999999900"); 
            (i.compareTo(tillBigInteger) == -1); i = i.add(new BigInteger("1")))
        System.out.println(oneLessThanATrillion(i) + "\n\n");
      
  }
}