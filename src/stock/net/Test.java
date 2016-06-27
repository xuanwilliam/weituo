package stock.net;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import stock.db.DatabaseUtil;
import stock.entity.PointCompData;

public class Test {
	static String startTime = "2016年1月1日00时00分00秒";
	static String endTime = "2016年3月30日00时00分00秒";

	public static void main(String[] args) {
		insertDayData(getTime(startTime), getTime(endTime), 3600 * 24);

	}

	private static void insertDayData(long startTime, long endTime, int seqcode) {
		long time = startTime;
		while (time < endTime) {
			System.out.println(getStrTime(time));
			time += seqcode * 1000;
		}
	}

	public static double getRandValue(int minValue, int maxValue) {
		Random random = new Random();
		return random.nextDouble() * 10 % (maxValue - minValue) + minValue;
	}

	public static long getTime(String time) {
		long re_time = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		Date d;
		try {
			d = sdf.parse(time);
			re_time = d.getTime();
			// String str = String.valueOf(l);
			// re_time = str.substring(0, 10);

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
