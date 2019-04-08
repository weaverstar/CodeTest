package com.uway.common.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Order {
	 
	private static AtomicLong  serial = new AtomicLong(1L); 
	public static  String generateOrder() {
		StringBuilder sb = new StringBuilder();
		Long val = serial.getAndIncrement();
		if(val>999999){
			serial.set(1);
			val = serial.getAndIncrement();
		}
		sb.append(val);
		while (sb.length() < 6) {
			sb.insert(0, "0");
		}
		sb.insert(0, new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
		return sb.toString();
	}
	
   public static void main(String[] args) {
	   for(int i = 0; i <=100; i++){
		   String s = Order.generateOrder();
		   System.out.println(s);		  
	   }
	   /*
		for (int i = 0; i < 15; i++) {
			new Thread() {
				public void run() {
					System.out.println(Order.generateOrder());
				}
			}.start();
		}		
		*/
   }
}