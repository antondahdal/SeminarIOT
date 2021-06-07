


public class ConversionUtils {
	
	/** Convert binary To Decimal
	 * @param binNum
	 * @return
	 */
	public static int binaryToDecimal(String binNum)  { 
        int num = Integer.valueOf(binNum); 
        int dec_value = 0; 
  
        // Initializing base 
        // value to 1, i.e 2^0 
        int base = 1; 
  
        int temp = num; 
        while (temp > 0) { 
            int last_digit = temp % 10; 
            temp = temp / 10; 
  
            dec_value += last_digit * base; 
  
            base = base * 2; 
        } 
  
        return dec_value; 
    } 
	
	/** ConvertHexadecimal to Binary Number
	 *  
	 * @param hexdec
	 * @return
	 */
	public static StringBuffer HexToBin(char hexdec[])  { 
        int i = 0; 
        String result = "";
        while (i < hexdec.length) { 
  
            switch (hexdec[i]) { 
            case '0': 
            	result += ("0000"); 
                break; 
            case '1': 
            	result += ("0001"); 
                break; 
            case '2': 
            	result += ("0010"); 
                break; 
            case '3': 
            	result += ("0011"); 
                break; 
            case '4': 
            	result += ("0100"); 
                break; 
            case '5': 
            	result += ("0101"); 
                break; 
            case '6': 
            	result += ("0110"); 
                break; 
            case '7': 
            	result += ("0111"); 
                break; 
            case '8': 
            	result += ("1000"); 
                break; 
            case '9': 
            	result += ("1001"); 
                break; 
            case 'A': 
            case 'a': 
            	result += ("1010"); 
                break; 
            case 'B': 
            case 'b': 
            	result += ("1011"); 
                break; 
            case 'C': 
            case 'c': 
            	result += ("1100"); 
                break; 
            case 'D': 
            case 'd': 
            	result += ("1101"); 
                break; 
            case 'E': 
            case 'e': 
            	result += ("1110"); 
                break; 
            case 'F': 
            case 'f': 
            	result += ("1111"); 
                break; 
            default: 
                System.out.print("\nInvalid hexadecimal digit " + hexdec[i]); 
            } 
            i++; 
        } 
        return new StringBuffer(result);
    } 
	 
	/** Compute two's complement 
	 * @param str
	 * @return
	 */
	public static  String findTwoscomplement(StringBuffer str) { 
	        int n = str.length(); 
	        if(n>0 && str.charAt(0)=='0') {
	        	return str.toString();
	        }
//	        if(str.toString().equals("00000000")) {
//		    	   return "00000000";
//		     }
	        
	        // Traverse the string to get first '1' from 
	        // the last of string 
	        int i; 
	        for (i = n-1 ; i >= 0 ; i--) 
	            if (str.charAt(i) == '1') 
	                break; 
	       
	        // If there exists no '1' concat 1 at the 
	        // starting of string 
	        if (i == -1) 
	            return "1" + str; 
	       
	        // Continue traversal after the position of 
	        // first '1' 
	        for (int k = i-1 ; k >= 0; k--) 
	        { 
	            //Just flip the values 
	            if (str.charAt(k) == '1') 
	                str.replace(k, k+1, "0"); 
	            else
	                str.replace(k, k+1, "1"); 
	        } 
	       
	        // return the modified string 
	        return str.toString(); 
	    }

	
	/** Compute  8.8 fixed point format
	 * @param byte1
	 * @param byte2
	 * @return
	 */
	public static double computeFixedPointFormat(String byte1, String byte2) {
		//byte1="FF";
		//byte2="80";
		//System.out.println(byte1+byte2);
		StringBuffer bin1=ConversionUtils.HexToBin(byte1.toCharArray());
		int sign = 1;
		if(bin1.charAt(0)=='1') {
			sign = -1;
		}
		//System.out.println("fixedPointFormat:bin1 " +bin1);
		StringBuffer bin2=ConversionUtils.HexToBin(byte2.toCharArray()); 
		//System.out.println("fixedPointFormat:bin2 "+bin2);
		String tcBin1=ConversionUtils.findTwoscomplement(bin1);
		//System.out.println("fixedPointFormat:tcBin1 " +tcBin1);
		String tcBin2=bin2.toString();
		//System.out.println("fixedPointFormat:tcBin2 "+tcBin2);
		int dec1 = ConversionUtils.binaryToDecimal(tcBin1);
		//System.out.println("fixedPointFormat:dec1 " +dec1);
		int dec2 = ConversionUtils.binaryToDecimal(tcBin2);
		//System.out.println("fixedPointFormat:dec2 "+dec2);
		
		double val = sign*dec1+ (dec2)/256.0f;
//		if(val>255) { // noise/ defect
//			val =0;
//		}
		return val;
	} 
	 

	// Function to convert hexadecimal to decimal 
	public static int hexadecimalToDecimal(String hexVal) 
		{ 
			int len = hexVal.length(); 
		
			// Initializing base value to 1, i.e 16^0 
			int base = 1; 
		
			int dec_val = 0; 
		
			// Extracting characters as digits from last character 
			for (int i=len-1; i>=0; i--) 
			{ 
				// if character lies in '0'-'9', converting 
				// it to integral 0-9 by subtracting 48 from 
				// ASCII value 
				if (hexVal.charAt(i) >= '0' && hexVal.charAt(i) <= '9') 
				{ 
					dec_val += (hexVal.charAt(i) - 48)*base; 
					
					// incrementing base by power 
					base = base * 16; 
				} 

				// if character lies in 'A'-'F' , converting 
				// it to integral 10 - 15 by subtracting 55 
				// from ASCII value 
				else if (hexVal.charAt(i) >= 'A' && hexVal.charAt(i) <= 'F') 
				{ 
					dec_val += (hexVal.charAt(i) - 55)*base; 
			
					// incrementing base by power 
					base = base*16; 
				} 
			} 
			return dec_val; 
		} 

}
