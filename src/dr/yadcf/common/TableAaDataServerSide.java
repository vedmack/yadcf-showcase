package dr.yadcf.common;

import java.util.List;

public class TableAaDataServerSide {
	List<List<String>> aaData;
	private String draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<EngineValueLabel> yadcf_data_0;
	private List<String> yadcf_data_1;
	private List<String> yadcf_data_2;
	private List<String> yadcf_data_3;
	private List<String> yadcf_data_4;
	


	public List<List<String>> getAaData() {
		return aaData;
	}

	public void setAaData(List<List<String>> aaData) {
		this.aaData = aaData;
	}


	public TableAaDataServerSide(List<List<String>> aaData) {
		super();
		this.aaData = aaData;
	}

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public List<EngineValueLabel> getYadcf_data_0() {
		return yadcf_data_0;
	}

	public void setYadcf_data_0(List<EngineValueLabel> yadcf_data_0) {
		this.yadcf_data_0 = yadcf_data_0;
	}

	public List<String> getYadcf_data_1() {
		return yadcf_data_1;
	}

	public void setYadcf_data_1(List<String> yadcf_data_1) {
		this.yadcf_data_1 = yadcf_data_1;
	}

	public List<String> getYadcf_data_2() {
		return yadcf_data_2;
	}

	public void setYadcf_data_2(List<String> yadcf_data_2) {
		this.yadcf_data_2 = yadcf_data_2;
	}

	public List<String> getYadcf_data_4() {
		return yadcf_data_4;
	}

	public void setYadcf_data_4(List<String> yadcf_data_4) {
		this.yadcf_data_4 = yadcf_data_4;
	}

	public List<String> getYadcf_data_3() {
		return yadcf_data_3;
	}

	public void setYadcf_data_3(List<String> yadcf_data_3) {
		this.yadcf_data_3 = yadcf_data_3;
	}
	
	
}
