package stock.entity;

import base.db.annotation.Column;

public class StockRecord {
	@Column(column = "id")
	private String id;
	@Column(column = "stock_id")
	private String stockId;
	@Column(column = "lastest_price")
	private float lastestPrice;
	@Column(column = "price_range")
	private float priceRange;
	@Column(column = "y_final_price")
	private float yFinalPrice;
	@Column(column = "t_opening_price")
	private float tOpeningPrice;
	@Column(column = "t_top_price")
	private float tTopPrice;
	@Column(column = "t_bottom_price")
	private float tBottomPrice;
	@Column(column = "trade_volumn")
	private float tradeVolumn;
	@Column(column = "handover")
	private float handOver;
	@Column(column = "turnover")
	private float turnOver;
	@Column(column = "swing")
	private float swing;
	@Column(column = "amount_radio")
	private float amoutRido;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public float getLastestPrice() {
		return lastestPrice;
	}
	public void setLastestPrice(float lastestPrice) {
		this.lastestPrice = lastestPrice;
	}
	public float getPriceRange() {
		return priceRange;
	}
	public void setPriceRange(float priceRange) {
		this.priceRange = priceRange;
	}
	public float getyFinalPrice() {
		return yFinalPrice;
	}
	public void setyFinalPrice(float yFinalPrice) {
		this.yFinalPrice = yFinalPrice;
	}
	public float gettOpeningPrice() {
		return tOpeningPrice;
	}
	public void settOpeningPrice(float tOpeningPrice) {
		this.tOpeningPrice = tOpeningPrice;
	}
	public float gettTopPrice() {
		return tTopPrice;
	}
	public void settTopPrice(float tTopPrice) {
		this.tTopPrice = tTopPrice;
	}
	public float gettBottomPrice() {
		return tBottomPrice;
	}
	public void settBottomPrice(float tBottomPrice) {
		this.tBottomPrice = tBottomPrice;
	}
	public float getTradeVolumn() {
		return tradeVolumn;
	}
	public void setTradeVolumn(float tradeVolumn) {
		this.tradeVolumn = tradeVolumn;
	}
	public float getHandOver() {
		return handOver;
	}
	public void setHandOver(float handOver) {
		this.handOver = handOver;
	}
	public float getTurnOver() {
		return turnOver;
	}
	public void setTurnOver(float turnOver) {
		this.turnOver = turnOver;
	}
	public float getSwing() {
		return swing;
	}
	public void setSwing(float swing) {
		this.swing = swing;
	}
	public float getAmoutRido() {
		return amoutRido;
	}
	public void setAmoutRido(float amoutRido) {
		this.amoutRido = amoutRido;
	}

}
