package com.zzti.utils;

import com.google.gson.Gson;

public class Common {
	
	/*
	 * get T from json 
	 */
	public static <T> T getT(String data,Class<T> classType)
	{
		Gson gson = new Gson();
		return gson.fromJson(data, classType);
	}
}
