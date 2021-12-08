package com.project.ayqcby.tools;

import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

	public static String changeStringDate(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	public static String changeStringTime(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	public static String changeStringTimeToId(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	public static String getNian(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		return sdf.format(date);
	}
	
	/**
	 * 时间格式字符串转时间戳
	 * @param time
	 * @return
	 * @throws Exception
	 */
	public static Long changeLongTime(String time) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = format.parse(time);
		return date.getTime();
	}
	
	/**
	 * 获得一个时间段内的每天日期
	 * @param start 起始时间
	 * @param end 截止时间
	 * @return 日期集合
	 * @throws Exception
	 */
	public static List<String> findDates(String start, String end) throws Exception{  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dBegin = sdf.parse(start);
		Date dEnd = sdf.parse(end);
		List<Date> lDate = new ArrayList<Date>();  
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();  
		// 使用给定的 Date 设置此 Calendar 的时间  
		calBegin.setTime(dBegin);  
		Calendar calEnd = Calendar.getInstance();  
		// 使用给定的 Date 设置此 Calendar 的时间  
		calEnd.setTime(dEnd);  
		// 测试此日期是否在指定日期之后  
		while (dEnd.after(calBegin.getTime())){  
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());  
		}
		List<String> sData = new ArrayList<String>();
		for (Date date : lDate){
			sData.add(sdf.format(date));
		}
		return sData;
	}
	
	/**
	 * 获得当前日期为星期几
	 * @param date String
	 * @return
	 * @throws Exception
	 */
	public static String getWeek(String date) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dd = format.parse(date);
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		return sdf.format(dd);
	}
	public static String getWeekInt(String date) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dd = format.parse(date);
		String xq = "1";
		if(dd.toString().startsWith("Mon")){
			xq = "1";
		}else if(dd.toString().startsWith("Tue")){
			xq = "2";
		}else if(dd.toString().startsWith("Wed")){
			xq = "3";
		}else if(dd.toString().startsWith("Thu")){
			xq = "4";
		}else if(dd.toString().startsWith("Fri")){
			xq = "5";
		}else if(dd.toString().startsWith("Sat")){
			xq = "6";
		}else if(dd.toString().startsWith("Sun")){
			xq = "7";
		}
		return xq;
	}
	/**
	 * 获得当前时间多少分钟之前的时间
	 * @param time
	 * @param mill
	 * @return
	 * @throws Exception
	 */
	public static String getBeforeHourTime(String time, int mill) throws Exception{
		String returnstr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(time);
		long ss = date.getTime() - 30*60*1000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		returnstr = sdf.format(new Date(Long.valueOf(ss)));
		return returnstr;
	}

	/**
	 * 获取当前月的第一天和最后一天
	 * @return
	 * @throws Exception
	 */
	public static String[] getBeginAndLastByMonth(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		//当前月的最后一天
		cal.set( Calendar.DATE, 1 );
		cal.roll(Calendar.DATE, - 1 );
		Date endTime = cal.getTime();
		String endTime1 = datef.format(endTime);
		//当前月的第一天
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		Date beginTime = cal.getTime();
		String beginTime1 = datef.format(beginTime);
		return (beginTime1+","+endTime1).split(",");
	}
	public static void main(String[] args) throws Exception{
		String[] dd = getBeginAndLastByMonth();
		System.out.println(dd[0]+","+dd[1]);
	}
}