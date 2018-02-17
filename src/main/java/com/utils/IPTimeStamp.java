package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 获取时间工具类
 */
public class IPTimeStamp {

	private SimpleDateFormat sdf = null;
	public String getTimeStamp(){
		this.sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.SSS");
		return this.sdf.format(new Date());
	}
}
