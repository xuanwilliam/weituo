package stock;

import java.text.SimpleDateFormat;
import java.util.UUID;

import stock.db.DatabaseUtil;
import stock.entity.AssetType;
import stock.entity.PointCompData;

public class Test {
	
	SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

	public static void main(String[] args) {
		DatabaseUtil util = DatabaseUtil.getInstance();
		AssetType type = new AssetType();
		type.setMdmId(getUUID());
		type.setName("测试");
		type.setSite("上海金山枫泾水质净化有限公司");
		type.setSiteId("ee3051716db0414aafd16a6e6c999ce2");

		try {
			util.saveEntity(AssetType.class, type);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void loopCom(){
		String []comNames = {};
		String []comIds = {};
		for(int i=0;i<comNames.length && i<comIds.length;i++){
			PointCompData pData = new PointCompData();
			pData.setMdmId(getUUID());
//			pData.set
//			loopSeqcode(comNames[i]);
		}
		
		String []mpointIds = {};
		
	}
	
	

	public void loopSeqcode(String comName) {
		// TODO Auto-generated method stub
		String []seqcodes = {};
	}

	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}

}
