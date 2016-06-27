package base.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import base.db.impl.Condition;

public interface IBaseDatabaseUtil {
	/**
	 * 连接数据库
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection getConnection() throws ClassNotFoundException,
			SQLException;

	/**
	 * 断开连接
	 * 
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException;

	/**
	 * 关闭数据库
	 */
	public void closeDatabase() throws SQLException;

	public void getConnected() throws ClassNotFoundException, SQLException;

	/**
	 * 根据ID获取对应的对象实体
	 * 
	 * @param entityClass
	 *            需要获取的对象的类属性
	 * @param id
	 * @return
	 */
	public <T> T getEntityById(Class<T> entityClass, String id)
			throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException;

	public <T> void saveEntityList(Class<T> entityClass, List<T> objectList)
			throws ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException, SQLException;

	public <T> void saveEntity(Class<T> entityClass, T entity)
			throws ClassNotFoundException, SQLException,
			IllegalArgumentException, IllegalAccessException;

	public <T> boolean existsEntity(Class<T> entityClass, T entity)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException, ClassNotFoundException;

	public <T> boolean existsEntity(Class<T> entityClass, Condition condition)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException, ClassNotFoundException;

	public <T> String getEntityId(Class<T> entityClass, Condition condition)
			throws ClassNotFoundException, SQLException;

	public <T> String getEntityId(Class<T> entityClass, Object object)
			throws SQLException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException;

	public <T> void deleteObject(Class<T> entityClass, T entity)
			throws ClassNotFoundException, SQLException,
			IllegalArgumentException, IllegalAccessException;

	public <T> void delete(Class<T> entityClass, Condition condition)
			throws ClassNotFoundException, SQLException;

	public <T> boolean isTableEmpty(Class<T> entityClass)
			throws ClassNotFoundException, SQLException;

	public <T> T getFirstRecord(Class<T> entityClass, Condition condition)
			throws SQLException, ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException;

}
