package com.uway.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

public class DateUtil {
	
	public static Date converToDate(String dateTimeStr, String sf) {
		SimpleDateFormat sdf = new SimpleDateFormat(sf);
		try {
			return sdf.parse(dateTimeStr);
		} catch (Exception e) {
		}
		return null;
	}
	
	public static String dateToStr(Date date, String sf) {
		SimpleDateFormat sdf = new SimpleDateFormat(sf);
		try {
			return sdf.format(date);
		} catch (Exception e) {
		}
		return null;
	}
	/**
	 * 
	 * @param dateTimeStr
	 * @param sf
	 * @return
	 */
	public static Date integerizeDate(Date date, String sf) {
		SimpleDateFormat sdf = new SimpleDateFormat(sf);
		try {
			return sdf.parse(sdf.format(date));
		} catch (Exception e) {
		}
		return null;
	}

	public static Date converToDateTime(String dateTimeStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(dateTimeStr);
		} catch (Exception e) {
		}
		return null;
	}
	
	public static Date converToDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(dateStr);
		} catch (Exception e) {
		}
		return null;
	}

	public static int getSecondsBetween(Date d1, Date d2) {
		Date[] d = new Date[2];
		d[0] = d1;
		d[1] = d2;
		Calendar[] cal = new Calendar[2];
		for (int i = 0; i < cal.length; i++) {
			cal[i] = Calendar.getInstance();
			cal[i].setTime(d[i]);
		}
		long m = cal[0].getTime().getTime();
		long n = cal[1].getTime().getTime();
		return (int) ((m - n) / 1000L);
	}
	
	//通过生日计算年龄
	public static int calCulateForDate(Date dataOfBirth){
		
		Calendar dob = Calendar.getInstance();  
		dob.setTime(dataOfBirth);  
		Calendar today = Calendar.getInstance();  
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
		if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
		  age--;  
		} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
		    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
		  age--;  
		}
		return age;
	}
	/**
	 * FunName:changeMongodbDate
	 * Description:将CST时间转换为GMT时间
	 * @return Date
	 * @Author:fengchao
	 * @Create Date: 2014-8-26
	 */
	public static Date changeMongodbDate(){
		
		Date date = null;
		try {
			SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String da = format.format(new Date());
			 format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
			 date = format.parse(da);
		} catch (Exception e) {
			
		}
		return date;
		
	}
	  public static String getFriendlyTime(Date dateTime) {
		    StringBuffer sb = new StringBuffer();
		    Date current = Calendar.getInstance().getTime();
		    long diffInSeconds = (current.getTime() - dateTime.getTime()) / 1000;

		    /*long diff[] = new long[]{0, 0, 0, 0};
		    /* sec *  diff[3] = (diffInSeconds >= 60 ? diffInSeconds % 60 : diffInSeconds);
		    /* min *  diff[2] = (diffInSeconds = (diffInSeconds / 60)) >= 60 ? diffInSeconds % 60 : diffInSeconds;
		    /* hours *  diff[1] = (diffInSeconds = (diffInSeconds / 60)) >= 24 ? diffInSeconds % 24 : diffInSeconds;
		    /* days * diff[0] = (diffInSeconds = (diffInSeconds / 24));
		     */
		    long sec = (diffInSeconds >= 60 ? diffInSeconds % 60 : diffInSeconds);
		    long min = (diffInSeconds = (diffInSeconds / 60)) >= 60 ? diffInSeconds % 60 : diffInSeconds;
		    long hrs = (diffInSeconds = (diffInSeconds / 60)) >= 24 ? diffInSeconds % 24 : diffInSeconds;
		    long days = (diffInSeconds = (diffInSeconds / 24)) >= 30 ? diffInSeconds % 30 : diffInSeconds;
		    long months = (diffInSeconds = (diffInSeconds / 30)) >= 12 ? diffInSeconds % 12 : diffInSeconds;
		    long years = (diffInSeconds = (diffInSeconds / 12));

		    if (years > 0) {
		        if (years == 1) {
		            sb.append("a year");
		        } else {
		            sb.append(years + " years");
		        }
		        if (years <= 6 && months > 0) {
		            if (months == 1) {
		                sb.append(" and a month");
		            } else {
		                sb.append(" and " + months + " months");
		            }
		        }
		    } else if (months > 0) {
		        if (months == 1) {
		            sb.append("a month");
		        } else {
		            sb.append(months + " months");
		        }
		        if (months <= 6 && days > 0) {
		            if (days == 1) {
		                sb.append(" and a day");
		            } else {
		                sb.append(" and " + days + " days");
		            }
		        }
		    } else if (days > 0) {
		        if (days == 1) {
		            sb.append("a day");
		        } else {
		            sb.append(days + " days");
		        }
		        if (days <= 3 && hrs > 0) {
		            if (hrs == 1) {
		                sb.append(" and an hour");
		            } else {
		                sb.append(" and " + hrs + " hours");
		            }
		        }
		    } else if (hrs > 0) {
		        if (hrs == 1) {
		            sb.append("an hour");
		        } else {
		            sb.append(hrs + " hours");
		        }
		        if (min > 1) {
		            sb.append(" and " + min + " minutes");
		        }
		    } else if (min > 0) {
		        if (min == 1) {
		            sb.append("a minute");
		        } else {
		            sb.append(min + " minutes");
		        }
		        if (sec > 1) {
		            sb.append(" and " + sec + " seconds");
		        }
		    } else {
		        if (sec <= 1) {
		            sb.append("about a second");
		        } else {
		            sb.append("about " + sec + " seconds");
		        }
		    }

		    sb.append(" ago");
		    return sb.toString();
		}
	  public static String getIntervalTime(Date dateTime) {
		    StringBuffer sb = new StringBuffer();
		    Date current = Calendar.getInstance().getTime();
		    long diffInSeconds = (current.getTime() - dateTime.getTime()) / 1000;

		    long sec = (diffInSeconds >= 60 ? diffInSeconds % 60 : diffInSeconds);
		    long min = (diffInSeconds = (diffInSeconds / 60)) >= 60 ? diffInSeconds % 60 : diffInSeconds;
		    long hrs = (diffInSeconds = (diffInSeconds / 60)) >= 24 ? diffInSeconds % 24 : diffInSeconds;
		    long days = (diffInSeconds = (diffInSeconds / 24)) >= 30 ? diffInSeconds % 30 : diffInSeconds;
		    long months = (diffInSeconds = (diffInSeconds / 30)) >= 12 ? diffInSeconds % 12 : diffInSeconds;
		    long years = (diffInSeconds = (diffInSeconds / 12));

		    if (years > 0) {
		        if (years == 1) {
		            sb.append("1年");
		        } else {
		            sb.append(years + "  年");
		        }
		    } else if (months > 0) {
		        if (months == 1) {
		            sb.append("1个月");
		        } else {
		            sb.append(months + "个月");
		        }
		    } else if (days > 0) {
		        if (days == 1) {
		            sb.append("1天");
		        } else {
		            sb.append(days + "天");
		        }
		    } else if (hrs > 0) {
		        if (hrs == 1) {
		            sb.append("5小时");
		        } else {
		            sb.append(hrs + "小时");
		        }
		    } else if (min > 0) {
		        if (min == 1) {
		            sb.append("5分钟");
		        } else {
		        	if(min < 5){
		        		sb.append(5 + "分钟");
		        	}else if(min > 5 && min < 10){
		        	 sb.append(10 + "分钟");
		        	}else{
		             sb.append(min + "分钟");
		        	}
		        }
		    } else {
		            sb.append("1分钟");
		        
		    }

		    sb.append("前");
		    return sb.toString();
		}
	  
	  
	  
	  public static void main(String[] args) throws ParseException {
		  System.out.println(integerizeDate(new Date(), "yyyyMMdd"));
		
	/*	 String s = "2015-07-02 09:21:00";
		 Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
		 System.out.println(getIntervalTime(date));
		 System.out.println(new DateUtil().addAndSubDate(Calendar.HOUR, -8));*/
	}
	  	  	 
	  /**
	   * 
	    * @Description: 加减当前时间(小时)
	    * TODO(这里描述这个方法的执行流程 – 可选)
	    * @return Date
	    * @author Feng Chao
	    * @date 2015-7-13
	   */
	  public static Date modifyDate(int unit,int num){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Date curDate = new Date();
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(curDate);
		  Date date = AddAndSubTime(unit,num);
		return date;
	  }
	  /**
	   * 
	    * @Description: 用于在当前的天或者小时或者分钟或者月份的基础上加上或者减去若干小时，分钟，日，月
	    * TODO(这里描述这个方法的执行流程 – 可选)
	    * @param currentDay
	    * @param day
	    * @return
	    * @author Feng Chao
	    * @date 2015-7-13
	   */
	  public static Date AddAndSubTime(int currenTime,int num) {
		  
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(currenTime,num );
	        Date startDate = calendar.getTime();
	        return startDate;
	  }
	  
	  /**
		 * 判断是否为同一天
		 * @param date1
		 * @param date2
		 * @return
		 */
		public static boolean isSameDay(final Date date1, final Date date2) {
	        if (date1 == null || date2 == null) {
	            throw new IllegalArgumentException("The date must not be null");
	        }
	        final Calendar cal1 = Calendar.getInstance();
	        cal1.setTime(date1);
	        final Calendar cal2 = Calendar.getInstance();
	        cal2.setTime(date2);
	        return isSameDay(cal1, cal2);
	    }
		
		public static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
		        if (cal1 == null || cal2 == null) {
		            throw new IllegalArgumentException("The date must not be null");
		        }
		        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
		                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
		                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
		}
}
