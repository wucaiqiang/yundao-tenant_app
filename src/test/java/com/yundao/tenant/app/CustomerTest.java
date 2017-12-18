package com.yundao.tenant.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.yundao.tenant.app.util.CalendarUtils;


/**
 * @author jan
 * @create 2017-08-01 PM9:53
 **/
public class CustomerTest  {
    @Test
    public void test() {
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	try {
			System.out.println(CalendarUtils.changeDateToText(format.parse("2017-09-5 11:20:30")));
		} catch (ParseException e) {
			
			e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
    }
}
