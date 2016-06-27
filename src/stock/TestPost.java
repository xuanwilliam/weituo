package stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;


public class TestPost {
	private String param ;
	private URLConnection connection ;
	private String result = "";
	private int page = 1;
	private long time = 144110;
	public TestPost() throws IOException{
		//http://quote.tool.hexun.com/hqzx/quote.aspx?
		 URL url = new URL("http://quote.tool.hexun.com/hqzx/quote.aspx?type=2&market=0&sorttype=0&updown=down&page="+page + "&count=50&time="+time);   
	        
		 connection = url.openConnection();   
	        connection.setDoOutput(true);   
	        
	}
	
	public void excute() throws IOException{
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());   
		out.write(param); //向页面传递数据。post的关键所在！   
        // remember to clean up   
        out.flush();   
        out.close();   
      
        // 一旦发送成功，用以下方法就可以得到服务器的回应：   
        String sCurrentLine;   
        String sTotalString;   
        sCurrentLine = "";   
//        sTotalString = "";   
        InputStream l_urlStream;   
        l_urlStream = connection.getInputStream();   
        // 传说中的三层包装阿！   
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(   
                l_urlStream,"gbk"));   
        while ((sCurrentLine = l_reader.readLine()) != null) {   
        	setResult(getResult() + sCurrentLine);   
  
        }
//        setResult(getResult()+"}");
//        System.out.println(sTotalString);  
	}
	public  void testPost() throws IOException {   
       
        initParams();
         
    }   
  
    private  String initParams() {
    	addParam("type","2");
    	addParam("market","0");
    	addParam("sorttype","3");
    	addParam("updown","up");
    	addParam("page","18");
    	addParam("count","50");
    	addParam("time","141720");
    	return null;
    }
    
    public void addParam(String key,String value){
    	if(param == null || "".equals(param)){
    		param = key + "=" + value;
    	}else{
    		param += "&" +  key + "=" + value;
    	}
    }
    
    public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	} 

	public static void main(String[] args) throws IOException {   
		TestPost test = new TestPost();
		test.initParams();
		test.excute();
		String data = test.getResult();
		if(data != null && data.contains(";")){
			data = data.split(";")[0];
		}
		if(data.contains("dataArr =")){
			data = data.substring("dataArr =".length(), data.length());
		}
		JSONArray array =  JSONArray.fromObject(data);
		for(int i=0;i<array.size();i++){
			JSONArray subArray = array.getJSONArray(i);
			for(int j=0;j<subArray.size();j++){
				System.out.println(subArray.optString(j));
			}
		}
//		System.out.println(json.size());
		System.out.println(test.getResult());
    }
	
	public static long getTime(String user_time) {
		long re_time = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		Date d;
		try {
			d = sdf.parse(user_time);
			re_time = d.getTime();
			// String str = String.valueOf(l);
			// re_time = str.substring(0, 10);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return re_time;
	}

	  
}