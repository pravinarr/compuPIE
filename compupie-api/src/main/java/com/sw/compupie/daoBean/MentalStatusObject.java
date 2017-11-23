package com.sw.compupie.daoBean;

import java.util.ArrayList;
import java.util.List;

public class MentalStatusObject {

	private String name;
	private List<String> values = new ArrayList<String>();
	private String risk;
	private String riskValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getRiskValue() {
		return riskValue;
	}

	public void setRiskValue(String riskValue) {
		this.riskValue = riskValue;
	}

}
