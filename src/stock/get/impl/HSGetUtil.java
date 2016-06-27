package stock.get.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONArray;
import stock.entity.Record;
import stock.entity.Stock;
import stock.entity.StockRecord;
import stock.get.GetUtil;

public class HSGetUtil implements GetUtil {
	private final String BASE_URL = "http://quote.tool.hexun.com/hqzx/quote.aspx?";
	private final String BASE_PARAM = "type=2&market=0&sorttype=0&updown=down&";
	private StringBuilder param = new StringBuilder();
	// private String param =
	// "type=2&market=0&sorttype=0&updown=down&page="+page +
	// "&count=50&time="+time";
	private URLConnection connection;
	private final int PAGE_NUM = 47;

	private StringBuilder result = new StringBuilder();
	private int page = 1;
	private long time = 144110;

	@Override
	public void initUrlParam() {
		param.delete(0, param.length());
		param.append(BASE_PARAM);
		param.append("page=" + page + "&");
		param.append("count=50&");
		param.append("time=" + time);
	}

	@Override
	public void getNextPage() {
		page++;
		time += 30;
	}

	public boolean hasNextPage() {
		return page < PAGE_NUM;
	}

	@Override
	public void excute() throws IOException {
		 initUrlParam();
		 result.delete(0, result.length());
		 URL url = new URL(BASE_URL + param.toString());   
         connection = url.openConnection();   
         connection.setDoOutput(true);
         OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
         out.flush();   
         out.close();
         String sCurrentLine;   
         sCurrentLine = "";   
         InputStream l_urlStream;   
         l_urlStream = connection.getInputStream();   
         // 传说中的三层包装阿！   
         BufferedReader l_reader = new BufferedReader(new InputStreamReader(   
                 l_urlStream,"gbk"));   
         while ((sCurrentLine = l_reader.readLine()) != null) {   
        	 result.append(sCurrentLine);
         }
	}

	@Override
	public void save() {
		String data = result.toString();
		if(data != null && data.contains(";")){
			data = data.split(";")[0];
		}
		if(data.contains("dataArr =")){
			data = data.substring("dataArr =".length(), data.length());
		}
		JSONArray array =  JSONArray.fromObject(data);
		for(int i=0;i<array.size();i++){
			Record record = new Record();
			Stock stock = new Stock();
			StockRecord stockRecord = new StockRecord();
			JSONArray subArray = array.getJSONArray(i);
			for(int j=0;j<subArray.size();j++){
				System.out.print(subArray.optString(j));
			}
			System.out.println();
		}
	}

	public void getAllData(){
		do{
			try {
				excute();
				save();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			getNextPage();
		}while(hasNextPage());
	}
	
	public static void main(String []args){
		new HSGetUtil().getAllData();
	}


}
