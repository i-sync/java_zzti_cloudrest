package com.zzti.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	/*
	 * get string md5
	 */
	public static String getMD5(String source) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
	
	/**
	 * format sql date to string 
	 * @param date
	 * @return
	 */
	public static String dateToString(long date)
	{
		Date datetime = new Date();
		datetime.setTime(date);
		SimpleDateFormat formattime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formattime.format(datetime);
	}
}
