package stock.input;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class BaseInput {
	public static double getRandValue(double minValue, int maxValue) {
		Random random = new Random();
		return random.nextDouble() * maxValue % (maxValue - minValue)
				+ minValue;
	}

	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}

	public static long getTime(String time) {
		long re_time = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		Date d;
		try {
			d = sdf.parse(time);
			re_time = d.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return re_time;
	}

	public static String getStrTime(long time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return formatter.format(new Date(time));

	}
}
