package com.syf.proj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CoreUtil {

	public static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

	public static String convertDateToString(Date date) {
		return formatter.format(date);
	}
}
