package stock.get;

import java.io.IOException;
import java.net.MalformedURLException;

public interface GetUtil {
	/**
	 * 初始化url带的参数
	 */
	public void initUrlParam();
	
	public void getNextPage();
	
	public void excute() throws MalformedURLException, IOException;
	
	public void save();
	
	
}
