package stock.entity;

import base.db.annotation.Column;
import base.db.annotation.Table;

@Table(table = "t_mpoint_compdata")
public class PointCompData {
	@Column(column = "mdm_id")
	private String mdmId;
	@Column(column = "seqcode")
	private String seqcode;
	@Column(column = "mpointid")
	private String mpointId;
	@Column(column = "datadt",type="date")
	private String datadt;
	@Column(column = "value")
	private double value;
	@Column(column = "algorithmId")
	private String algorithmId;
	public String getMdmId() {
		return mdmId;
	}

	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}

	public String getSeqcode() {
		return seqcode;
	}

	public void setSeqcode(String seqcode) {
		this.seqcode = seqcode;
	}

	public String getMpointId() {
		return mpointId;
	}

	public void setMpointId(String mpointId) {
		this.mpointId = mpointId;
	}

	public String getDatadt() {
		return datadt;
	}

	public void setDatadt(String datadt) {
		this.datadt = datadt;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getAlgorithmId() {
		return algorithmId;
	}

	public void setAlgorithmId(String algorithmId) {
		this.algorithmId = algorithmId;
	}

}
