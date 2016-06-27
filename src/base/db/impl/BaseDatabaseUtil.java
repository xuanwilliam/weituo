package base.db.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.FieldPosition;
import java.util.List;

import base.db.IBaseDatabaseUtil;
import base.db.annotation.Column;
import base.db.annotation.Table;


public abstract class BaseDatabaseUtil implements IBaseDatabaseUtil {

	protected Connection conn = null;
	protected PreparedStatement pstatement;
	protected ResultSet rs;

	/**
	 * 得到数据库连接
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public Connection getConnection() throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		if (conn == null)
			getConnected();
		return conn;
	}

	/**
	 * 关闭连接
	 * 
	 * @throws SQLException
	 */
	@Override
	public void closeConnection() throws SQLException {
		// TODO Auto-generated method stub
		if (conn != null) {
			conn.close();
			conn = null;
		}
	}

	/***
	 * 关闭数据库连接
	 */
	@Override
	public void closeDatabase() throws SQLException {
		if (rs != null) {
			rs.close();
			rs = null;
		}
		if (pstatement != null) {
			pstatement.close();
			pstatement = null;
		}
		closeConnection();
	}

	/**
	 * 写成abstract的，每个应用连接的数据库信息不同
	 */
	@Override
	public abstract void getConnected() throws ClassNotFoundException,
			SQLException;// {


	public <T> T getEntityById(Class<T> entityClass, String id)
			throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Table table = entityClass.getAnnotation(Table.class);
		String query = "select * from " + table.table() + " where id = ?";
		pstatement = getConnection().prepareStatement(query);
		pstatement.setObject(1, id);
		rs = pstatement.executeQuery();
		if (rs != null && rs.next()) {
			T entity = entityClass.getConstructor().newInstance();
			Field[] fields = entityClass.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Column column = field.getAnnotation(Column.class);
				field.set(entity, rs.getObject(column.column()));
			}
			return entity;
		}
		return null;
	}

	public <T> void saveEntityList(Class<T> entityClass, List<T> objectList)
			throws ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException, SQLException {
		for (T object : objectList) {
			saveEntity(entityClass, object);
		}
	}

	public <T> void saveEntity(Class<T> entityClass, T entity)
			throws ClassNotFoundException, SQLException,
			IllegalArgumentException, IllegalAccessException {
		Table table = entityClass.getAnnotation(Table.class);
		if (!existsEntity(entityClass, entity)) {// 数据库未存储此对象的时候，才存储此对象

			String valueStr = getValueString(entityClass);
			String columnStr = getColumnString(entityClass);
			// StringBuilder columnStr = new StringBuilder();
			// columnStr.append(")");
			Field[] fields = entityClass.getDeclaredFields();
			String preparedStr = "insert into " + table.table()
					+ columnStr.toString() + valueStr.toString();
			pstatement = getConnection().prepareStatement(preparedStr);
			// 表插入语句,insert into table (field1,field2,field3,.....)
			// value(?,?,?,....),value(?,?,?,...),....
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				Column column = fields[i].getAnnotation(Column.class);
//				if("date".equals(column.type())){
//					pstatement.setObject(i + 1, fields[i].get(entity));
//				}
//				pstatement.setObject(i + 1, fields[i].get(entity));
//				column.type();
				// setObject 是从1开始的
				pstatement.setObject(i + 1, fields[i].get(entity));
			}
			pstatement.execute();
			pstatement.close();
		}

	}

	public <T> String getValueString(Class<T> entityClass) {
		/* 类似(?,?,?....) */
		StringBuilder valueStr = new StringBuilder();
		Field[] fields = entityClass.getDeclaredFields();
		// columnStr.append(" (");
		valueStr.append(" values(");
		for (int i = 0; i < fields.length; i++) {
			if (i > 0) {
				// columnStr.append(",");
				valueStr.append(",");
			}
			Column column = fields[i].getAnnotation(Column.class);
			if("date".equals(column.type())){
				valueStr.append("to_date(?,'yyyy/mm/dd,hh24:mi:ss')");
//				pstatement.setObject(i + 1,"to_date(" + fields[i].get(entity)+"','yyyy/mm/dd,hh24:mi:ss')");
			}else{
				valueStr.append("?");
			}
			
		}
		valueStr.append(")");
		return valueStr.toString();
	}

	public <T> String getColumnString(Class<T> entityClass) {
		/* 类似(field1,field2,field3....) */
		StringBuilder columnStr = new StringBuilder();
		Field[] fields = entityClass.getDeclaredFields();
		columnStr.append(" (");
		// valueStr.append(" values(");
		for (int i = 0; i < fields.length; i++) {
			if (i > 0) {
				columnStr.append(",");
			}
			Column column = fields[i].getAnnotation(Column.class);
			columnStr.append(column.column());
		}
		columnStr.append(")");
		return columnStr.toString();
	}

	public <T> boolean existsEntity(Class<T> entityClass, T entity)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException, ClassNotFoundException {
		Table table = entityClass.getAnnotation(Table.class);
		StringBuilder where = new StringBuilder();
		Field[] fields = entityClass.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			if (fields[i].get(entity) != null) {
				if (i > 0) {
					where.append(" and ");
				}
				Column column = fields[i].getAnnotation(Column.class);
				where.append(column.column() + " = ? ");
			}
		}
		String query = "select mdm_id from " + table.table() + " where " + where;
		pstatement = getConnection().prepareStatement(query);
		int index = 0;
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].get(entity) != null) {
				fields[i].setAccessible(true);
				// setObject 是从1开始的
				pstatement.setObject(index + 1, fields[i].get(entity));
				index++;
			}

		}
		rs = pstatement.executeQuery();
		boolean flag = false;
		if (rs != null && rs.next()) {
			flag =  true;
		}
		if(rs != null ){
			rs.close();
		}
		if(pstatement != null ){
			pstatement.close();
		}
		return flag;
	}

	public <T> boolean existsEntity(Class<T> entityClass, Condition condition)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException, ClassNotFoundException {
		Table table = entityClass.getAnnotation(Table.class);
		String query = "select id from " + table.table() + " where "
				+ condition.getPreparedCondition();
		pstatement = getConnection().prepareStatement(query);
		for (int i = 0; i < condition.getValueList().size(); i++) {
			pstatement.setObject(i + 1, condition.getValueList().get(i));
		}
		rs = pstatement.executeQuery();
		if (rs != null && rs.next()) {
			return true;
		}
		return false;
	}

	public <T> String getEntityId(Class<T> entityClass, Condition condition)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
//		check(entityClass);
//		String table = Config.entityTableMap.get(entityClass.getName());
		Table table = entityClass.getAnnotation(Table.class);
		String query = "select id from " + table.table() + " where "
				+ condition.getPreparedCondition();
		pstatement = getConnection().prepareStatement(query);
		List<Object> valueList = condition.getValueList();
		for (int i = 0; i < valueList.size(); i++) {
			pstatement.setObject(i + 1, valueList.get(i));
		}
		rs = pstatement.executeQuery();
		if (rs != null && rs.next()) {
			return rs.getString(1);
		}
		return null;
	}

	/**
	 * 返回值如果为0，表示没有从数据库中找到该对象
	 * 
	 * @param entityClass
	 * @param object
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public <T> String getEntityId(Class<T> entityClass, Object object)
			throws SQLException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException {
//		check(entityClass);
//		String table = Config.entityTableMap.get(entityClass.getName());
		Table table = entityClass.getAnnotation(Table.class);
		StringBuilder where = new StringBuilder();
		Field[] fields = entityClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (i > 0) {
				where.append(" and ");
			}
			where.append(fields[i].getName() + " = ? ");
		}
		String sql = "select * from " + table.table() + " where " + where;
		pstatement = getConnection().prepareStatement(sql);
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			// setObject 是从1开始的
			pstatement.setObject(i + 1, fields[i].get(object));
		}
		rs = pstatement.executeQuery();
		if (rs != null && rs.next()) {
			return rs.getString(1);
		}
		return null;
	}

	public <T> void deleteObject(Class<T> entityClass, T entity)
			throws ClassNotFoundException, SQLException,
			IllegalArgumentException, IllegalAccessException {
		Table table = entityClass.getAnnotation(Table.class);
//		String table = Config.entityTableMap.get(entityClass.getName());
		StringBuilder where = new StringBuilder();
		Field[] fields = entityClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (i > 0) {
				where.append(" and ");
			}
			where.append(fields[i].getName() + " = ? ");
		}
		String sql = "delete from " + table.table() + " where " + where;
		pstatement = getConnection().prepareStatement(sql);
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			// setObject 是从1开始的
			pstatement.setObject(i + 1, fields[i].get(entity));
		}
		pstatement.executeUpdate();

	}

	public <T> void delete(Class<T> entityClass, Condition condition)
			throws ClassNotFoundException, SQLException {
		Table table = entityClass.getAnnotation(Table.class);
		String sql = "delete from " + table.table() + " where "
				+ condition.getPreparedCondition();
		pstatement = getConnection().prepareStatement(sql);
		List<Object> valueList = condition.getValueList();
		for (int i = 0; i < valueList.size(); i++) {
			pstatement.setObject(i + 1, valueList.get(i));
		}
		pstatement.executeUpdate();
	}

	public <T> boolean isTableEmpty(Class<T> entityClass)
			throws ClassNotFoundException, SQLException {
		Table table = entityClass.getAnnotation(Table.class);
		String query = "select count(*) from " + table.table();
		pstatement = getConnection().prepareStatement(query);
		rs = pstatement.executeQuery();
		if (rs != null && rs.next()) {
			return rs.getInt(1) > 0;
		}
		return true;
	}

	public <T> T getFirstRecord(Class<T> entityClass, Condition condition)
			throws SQLException, ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Table table = entityClass.getAnnotation(Table.class);
		String query = "select * from " + table.table()
				+ condition.getPreparedCondition() + "  limit 1";
		pstatement = getConnection().prepareStatement(query);
		List<Object> valueList = condition.getValueList();
		if (valueList != null) {
			for (int i = 0; i < valueList.size(); i++) {
				pstatement.setObject(i + 1, valueList.get(i));
			}
		}
		rs = pstatement.executeQuery();
		if (rs != null && rs.next()) {
			T entity = entityClass.getConstructor().newInstance();
			Field[] fields = entityClass.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Column column = field.getAnnotation(Column.class);
				field.set(entity, rs.getObject(column.column()));
			}
			return entity;
		}
		return null;
	}

	public <T> void update(Class<T> entityClass, String updateSql, Condition condition)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Table table = entityClass.getAnnotation(Table.class);
		String sql = "update " + table.table() + " " + updateSql + " where "
				+ condition.getPreparedCondition();
		pstatement = getConnection().prepareStatement(sql);
		List<Object> valueList = condition.getValueList();
		if (valueList != null) {
			for (int i = 0; i < valueList.size(); i++) {
				pstatement.setObject(i + 1, valueList.get(i));
			}
		}
		pstatement.executeUpdate();
	}

}
