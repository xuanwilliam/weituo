package stock.entity;

import base.db.annotation.Column;
import base.db.annotation.Table;

@Table(table="f_stock")
public class Stock {
	@Column(column = "code")
	private String code;
	@Column(column = "name")
	private String name;
	@Column(column = "id")
	private String id;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
