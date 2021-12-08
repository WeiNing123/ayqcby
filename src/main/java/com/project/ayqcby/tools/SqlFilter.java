package com.project.ayqcby.tools;

public class SqlFilter {
	/**
	 * 过滤sql注入
	 * @param str
	 * @return
	 */
	public static String validation (String str) {
		if(null == str){
			return "";
		}else{
			//统一转为小写
			String ss = str;
			str = str.toLowerCase();
			//过滤掉的sql关键字，可以手动添加 
			String badStr = "and|exec|execute|insert|select|delete|update|count|drop|chr|mid|master|"
					+ "truncate|char|declare|sitename|net user|xp_cmdshell|or|like'|create|table|from|"
					+ "grant|use|group|group_concat|column_name|information_schema.columns|table_schema|"
					+ "union|where|order|by|like";
			String[] badStrs = badStr.split("\\|");
			for (int i = 0; i < badStrs.length; i++) {
				if (str.indexOf(badStrs[i]) >= 0) {
					return "";
				}
			}
			if(!str.equals("")){
				str = ss;
			}
			return str;
		}
	}
}