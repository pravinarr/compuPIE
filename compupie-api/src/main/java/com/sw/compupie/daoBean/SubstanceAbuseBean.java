package com.sw.compupie.daoBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SubstanceAbuseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int clientId;
	private AbuseBean Alchohol;
	private AbuseBean Marijuana;
	private AbuseBean Hallucinogens;
	private AbuseBean Inhalants;
	private AbuseBean Heroin;
	private AbuseBean Methadone;
	private AbuseBean opiates;
	private AbuseBean cocaine;
	private AbuseBean amphe;
	private AbuseBean methamphe;
	private AbuseBean barbiturates;
	private AbuseBean sedative;
	private List<AbuseBean> other;

	private String otherProblems;

	public SubstanceAbuseBean() {
		Alchohol = new AbuseBean();
		Marijuana = new AbuseBean();
		Hallucinogens = new AbuseBean();
		Inhalants = new AbuseBean();
		Heroin = new AbuseBean();
		Methadone = new AbuseBean();
		opiates = new AbuseBean();
		cocaine = new AbuseBean();
		amphe = new AbuseBean();
		methamphe = new AbuseBean();
		barbiturates = new AbuseBean();
		sedative = new AbuseBean();
		other = new ArrayList<AbuseBean>();
		other.add(new AbuseBean());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public AbuseBean getAlchohol() {
		return Alchohol;
	}

	public void setAlchohol(AbuseBean alchohol) {
		Alchohol = alchohol;
	}

	public AbuseBean getMarijuana() {
		return Marijuana;
	}

	public void setMarijuana(AbuseBean marijuana) {
		Marijuana = marijuana;
	}

	public AbuseBean getHallucinogens() {
		return Hallucinogens;
	}

	public void setHallucinogens(AbuseBean hallucinogens) {
		Hallucinogens = hallucinogens;
	}

	public AbuseBean getInhalants() {
		return Inhalants;
	}

	public void setInhalants(AbuseBean inhalants) {
		Inhalants = inhalants;
	}

	public AbuseBean getHeroin() {
		return Heroin;
	}

	public void setHeroin(AbuseBean heroin) {
		Heroin = heroin;
	}

	public AbuseBean getMethadone() {
		return Methadone;
	}

	public void setMethadone(AbuseBean methadone) {
		Methadone = methadone;
	}

	public AbuseBean getOpiates() {
		return opiates;
	}

	public void setOpiates(AbuseBean opiates) {
		this.opiates = opiates;
	}

	public AbuseBean getCocaine() {
		return cocaine;
	}

	public void setCocaine(AbuseBean cocaine) {
		this.cocaine = cocaine;
	}

	public AbuseBean getAmphe() {
		return amphe;
	}

	public void setAmphe(AbuseBean amphe) {
		this.amphe = amphe;
	}

	public AbuseBean getMethamphe() {
		return methamphe;
	}

	public void setMethamphe(AbuseBean methamphe) {
		this.methamphe = methamphe;
	}

	public AbuseBean getBarbiturates() {
		return barbiturates;
	}

	public void setBarbiturates(AbuseBean barbiturates) {
		this.barbiturates = barbiturates;
	}

	public AbuseBean getSedative() {
		return sedative;
	}

	public void setSedative(AbuseBean sedative) {
		this.sedative = sedative;
	}

	public List<AbuseBean> getOther() {
		return other;
	}

	public void setOther(List<AbuseBean> other) {
		this.other = other;
	}

	/**
	 * @return the otherProblems
	 */
	public String getOtherProblems() {
		return otherProblems;
	}

	/**
	 * @param otherProblems
	 *            the otherProblems to set
	 */
	public void setOtherProblems(String otherProblems) {
		this.otherProblems = otherProblems;
	}

}
