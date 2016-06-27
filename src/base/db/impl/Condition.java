package base.db.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 周田涛
 * 
 */
public class Condition {
	private StringBuilder where = new StringBuilder();
	private StringBuilder predWhere = new StringBuilder();

	private List<Object> valueList = new ArrayList<Object>();

	public void addEquals(String columnName, String value) {
		check();
		where.append(columnName + " = " + value);
		predWhere.append(columnName + " =  ? ");
		valueList.add(value);
	}

	public void addEndWith(String columnName, String value) {
		check();
		where.append(columnName + " like %" + value);
		// whereMap.put(columnName + " like %" + " ? ", value);
		predWhere.append(columnName + " like " + " ? ");
		valueList.add("%" + value);
	}

	public void addContains(String columnName, String value) {
		check();
		where.append(columnName + " like %" + value + "%");
		// whereMap.put(columnName + " like %" + " ?　" + "%", value);
		predWhere.append(columnName + " like " + "?");
		valueList.add("%" + value + "%");
	}

	public void addStartWith(String columnName, String value) {
		check();
		where.append(columnName + " like " + value + "%");
		// whereMap.put(columnName + " like " + " ?　" + "%", value);
		predWhere.append(columnName + " like " + " ?　");
		valueList.add(value + "%");
	}

	public String getCondition() {
		String condition = where.toString();
		clear();
		return condition;
	}

	/**
	 * 
	 * @return
	 */
	public String getPreparedCondition() {
		return predWhere.toString();
	}

	/**
	 * 这个没用到，暂时不写
	 * 
	 * @param columnName
	 * @param set
	 */
	public void addInCondition(String columnName, String[] set) {
		check();
		where.append(columnName + " in (");

	}

	private void check() {
		if (where.length() > 0) {
			where.append(" and ");
			predWhere.append(" and ");
		}
	}

	private void clear() {
		where.setLength(0);
		predWhere.setLength(0);
		valueList.clear();
		// whereMap.clear();
	}

	public List<Object> getValueList() {
		return valueList;
	}

}
