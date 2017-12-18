

package com.yundao.tenant.app.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
 /**
 * Function: 
 * Reason:	  
 * Date:     2017年9月28日 上午10:33:57 
 * @author   wucq
 * @version   
 */
public class NumberUtil {
	
	
	public static String getAmountStr(Double amount){
		if(amount == null){
			return null;
		}
		String amountStr = amount.toString();
		if(amountStr.endsWith(".0")){
			amountStr = amountStr.substring(0, amountStr.length()-2);
		}
		return amountStr;
	}
	
	
  public static String trimDoubleZero(String digital){
	  if(StringUtils.isBlank(digital)){
		  return "0";
	  }
	  Double digital_=NumberUtils.toDouble(digital);
	  
	  if(digital_ >digital_.intValue()){
		  int count=0;
		  for(int i=digital.length()-1;i>=0;i--){
			  char c=digital.charAt(i);
			  if(c=='0'){
				  count++;
			  }else{
				  break;
			  }
		  }
		  if(count >0){
			  digital=digital.substring(0, digital.length()-count);
		  }
		  return digital;
	  }
	 
	  return String.valueOf(digital_.intValue());
  }
}

