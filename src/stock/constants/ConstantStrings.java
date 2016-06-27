package stock.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ConstantStrings {
	/*如果超过两小时就停十分钟，否则停十秒钟*/
	public static final long INTERVAL_TIME = 2*60 *60*1000;/*两小时*/
	public static final long SLEEP_LONG = 10 * 60 *1000;/*十分钟*/
	public static final long SLEEP_SHORT = 10*1000;/*十秒钟*/
	public static final String LIB_ROOT_URL = "http://202.120.121.202:81/";
//	public static String FIELD_TYPE = "作者";
//	public static String FIELD_CONTENT = "周良辅";
	public static boolean USE_LIB_UTL = true;
	public static boolean CONTINUE_SEARCH_FLAG = true;
	public static long TOTAL_ARTICLE_NUM = 0;
	public static String INFO_FILE_NAME = "queryinfo";
	public static String []infoName = {"fieldType","fieldContent","P","totalPageNum"};
	public static Map<String,String> info ; /*--保存查询时的表单域值，每次查完一页，保存一次，以便恢复--*/
	// 当前正在搜索的作者信息
//	public static Author cur_author = new Author();
	// public static String input_field;
	// public static String input_value;
	/*-------详细页面Url所共有的特性，包含的字符串-------*/

	public static String[] DETAILPAGE_URL_CONTAINS = { "html", "qk" };

	// 表单名，可选择设置，默认为DownForm
	public static String FROM_NAME = "DownForm";
	public static List<String> PRE_RUL = new ArrayList<String>();

	public static String ROOT_URL = "http://lib.cqvip.com/";
	public static String FORM_NAME = "DownForm";
	public static String[] PRE_URL = { "http://dlut.cqvip.com/index.asp",
			"http://lib.cqvip.com/" };
	public static String[] LIB_PRE_URL = {
			"http://www.lib.shu.edu.cn/dbnav/shulib_database2.php",
			"http://202.120.121.202:81/ZK/search.aspx" };
	// 分离页面信息
	public static String AUTHOR_SPLIT = "作 者：";
	public static String[] SPLIT_STRINGS = { "机构地区：", "出 处：", "摘 要：", "关键词：",
			"分类号：", "收稿日期" };
	public static String[] FIELD = { "author", "affiliation", "source",
			"abstract", "keyword", "classnumber" };
	// public static String []FIELD =
	// {"author","organ","source","abstract","keyword","classnumber"};
	public static String SOURCE = "source";
	public static String ABSTRACT = "abstract";
	public static String KEYWORD = "keyword";
	public static String CLASSNUMBER = "classnumber";
	// 数据库连接信息
	public static String SQLURL = "jdbc:oracle:thin:@120.55.189.51:1521:orcl";
	public static String DATABASENAME = "stock";
	public static String USERNAME = "DB_DAX";
	public static String PASSWORD = "DB_DAX";
//	public static String DRIVER = "com.mysql.jdbc.Driver";
	public static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	
	

}
