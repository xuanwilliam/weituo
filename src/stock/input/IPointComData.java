package stock.input;

import stock.db.DatabaseUtil;
import stock.entity.PointCompData;

/**
 * 插计算表中的数据
 * 
 * @author william
 * 
 */
public class IPointComData extends BaseInput {
	static boolean isMonth = false;
	static String startTime = "2016年1月5日00时00分00秒";
	static String endTime = "2016年3月30日00时00分00秒";
	private static String[] mpointIds = { "000000738", "000000733",
			"000000730", "000000734", "000000729", "000000731", "000000732",
			"000000728", "000000735", "000000737", "000000736", "000000716",
			"000000715", "000000714", "000000713", "000000712", "000000711",
			"000000616", "000000709", "000000708", "000000707", "000000706",
			"000000597", "000000600", "000000710", "000000602", "000000726",
			"000000725", "000000724", "000000723", "000000722", "000000721",
			"000000720", "000000717", "000000719", "000000718", "000000727" };
	static double[] minValue = { 8000, 80, 80, 80, 80, 80, 80, 300, 800, 1300,
			1000, 1000, 1300, 800, 300, 80, 80, 0.5, 80, 80, 80, 8000, 0.5,
			0.5, 80, 0.5, 1300, 800, 300, 80, 80, 80, 80, 8000, 80, 80, 800 };
	static int[] maxValue = { 10000, 100, 100, 100, 100, 100, 100, 500, 1000,
			1500, 1200, 1200, 1500, 1000, 500, 100, 100, 1, 100, 100, 100,
			10000, 1, 1, 100, 1, 1500, 1000, 500, 100, 100, 100, 100, 10000,
			100, 100, 1000 };

	static String[] algorithmIds = { "000000006", "000000002", "000000002",
			"000000002", "000000002", "000000002", "000000002", "000000001",
			"000000001", "000000001", "000000001", "000000001", "000000001",
			"000000001", "000000001", "000000002", "000000002", "000000002",
			"000000002", "000000002", "000000002", "000000006", "000000002",
			"000000002", "000000002", "000000002", "000000001", "000000001",
			"000000001", "000000002", "000000002", "000000002", "000000002",
			"000000006", "000000002", "000000002", "000000001" };

	public static void main(String[] args) throws Exception {
		long dayseqcode = 3600 * 24;
		long monthSeqCode = 3600 * 24 * 30;
		insertDayData(getTime(startTime), getTime(endTime), dayseqcode);
		insertMonthData(getTime(startTime), getTime(endTime), monthSeqCode);
	}

	private static void insertDayData(long startTime, long endTime, long seqcode)
			throws Exception {
		long time = startTime;
		DatabaseUtil util = DatabaseUtil.getInstance();
		while (time < endTime) {
			for (int i = 0; i < mpointIds.length; i++) {
				PointCompData data = new PointCompData();
				data.setMdmId(getUUID());
				data.setMpointId(mpointIds[i]);
				data.setSeqcode("" + seqcode);
				data.setValue(getRandValue(minValue[i], maxValue[i]));
				data.setDatadt(getStrTime(time));
				data.setAlgorithmId(algorithmIds[i]);
				util.saveEntity(PointCompData.class, data);
			}
			time += seqcode * 1000;
		}
	}

	private static void insertMonthData(long startTime, long endTime,
			long seqcode) throws Exception {
		long time = startTime;
		DatabaseUtil util = DatabaseUtil.getInstance();

		while (time < endTime) {
			for (int i = 0; i < mpointIds.length; i++) {
				double min = minValue[i];
				int max = maxValue[i];
				if (min > 200) {
					min = min * 30;
					max = max * 30;
				}
				PointCompData data = new PointCompData();
				data.setMdmId(getUUID());
				data.setMpointId(mpointIds[i]);
				data.setSeqcode("" + seqcode);
				data.setValue(getRandValue(min, max));
				data.setDatadt(getStrTime(time));
				data.setAlgorithmId(algorithmIds[i]);
				util.saveEntity(PointCompData.class, data);
			}
			time += seqcode * 1000;
		}

	}

}
