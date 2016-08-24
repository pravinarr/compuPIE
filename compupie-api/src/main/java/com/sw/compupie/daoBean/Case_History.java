package com.sw.compupie.daoBean;

import java.util.ArrayList;
import java.util.List;

public class Case_History {
	
	private int id;
	
	private int Client_Id;
	
	private String ReasonForRefer;
	
	private String CurrentSituation;
	
	private String RelevantHistory;
	
	private String TraumaHistory;
	
	private List<Case_History> items = new ArrayList<Case_History>();
	
	public int getId(){
		return id;
	}
	
	public int getClient_Id(){
		return Client_Id;
	}
	
	public String getReasonforRefer() {
		return ReasonForRefer;
	}
	
	public String getCurrentSituation() {
		return CurrentSituation;
	}
	
	public String getRelevantHistory() {
		return RelevantHistory;
	}
	  
	public String getTraumaHistory() {
		return TraumaHistory;
	}
	
	public List<Case_History> getItems() {
		return items;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setClient_Id(int Client_Id){
		this.Client_Id = Client_Id;
	}
	
	public void setReason(String Reason){
		ReasonForRefer = Reason;
	}
	
	public void setCurrentSituation(String Situation){
		CurrentSituation = Situation;
	}
	
	public void setRelevantHistory(String RelevantHistory){
		this.RelevantHistory = RelevantHistory;
	}
	
	public void setTraumaHistory(String TraumaHistory){
		this.TraumaHistory = TraumaHistory;
	}
	
	public void setItems(List<Case_History> items){
		this.items = items;
	}
	
}
