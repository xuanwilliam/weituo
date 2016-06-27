package stock.entity;

import base.db.annotation.Column;
import base.db.annotation.Table;

@Table(table="t_asset_type")
public class AssetType {
	@Column(column = "mdm_id")
	private String mdmId;
	@Column(column = "siteid")
	private String site;
	@Column(column = "siteid_id")
	private String siteId;
	@Column(column = "name")
	private String name;
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
