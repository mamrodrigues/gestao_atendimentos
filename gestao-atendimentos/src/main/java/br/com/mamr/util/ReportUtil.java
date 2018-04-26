package br.com.mamr.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportUtil {
	
	public static String getFilePath(String path, Date date, String nome) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmm");
		String formatDate = sdf.format(date);
		return path + "[" + formatDate + "] " + nome.toUpperCase() + ".pdf";
	}

}
