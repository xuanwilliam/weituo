package stock.entity;

public class Record {
	private Stock stock;
	private StockRecord stockRecord;
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public StockRecord getStockRecord() {
		return stockRecord;
	}
	public void setStockRecord(StockRecord stockRecord) {
		this.stockRecord = stockRecord;
	}
}
