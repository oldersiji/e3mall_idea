package cn.e3mall.pojo;

import java.io.Serializable;
import java.util.List;

public class DataGridResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long total;
	private List<?> rows;
	
	public DataGridResult() {
		
	}
	public DataGridResult(long total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}
