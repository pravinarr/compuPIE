package com.sw.compupie.daoBean;

public class Factor3Bean {

	public int id;
	public String dsmDiagnosis;
	public String diagnosisSource;
	public String serverity;
	public String duration;
	public String copingAbitity;
	public String priority;
	public String goal;
	public String recommendedInter;
	public String expectedOutcome;
	public int clientId;
	public int followup;
	public String MHP;
	public String noDx;
	public String denies;
	public String specifier;
	

	public String getSpecifier() {
		return specifier;
	}

	public void setSpecifier(String specifier) {
		this.specifier = specifier;
	}

	/**
	 * @return the followup
	 */
	public int getFollowup() {
		return followup;
	}

	/**
	 * @return the mHP
	 */
	public String getMHP() {
		return MHP;
	}

	/**
	 * @param mHP the mHP to set
	 */
	public void setMHP(String mHP) {
		MHP = mHP;
	}

	/**
	 * @return the noDx
	 */
	public String getNoDx() {
		return noDx;
	}

	/**
	 * @param noDx the noDx to set
	 */
	public void setNoDx(String noDx) {
		this.noDx = noDx;
	}

	/**
	 * @return the denies
	 */
	public String getDenies() {
		return denies;
	}

	/**
	 * @param denies the denies to set
	 */
	public void setDenies(String denies) {
		this.denies = denies;
	}

	/**
	 * @param followup
	 *            the followup to set
	 */
	public void setFollowup(int followup) {
		this.followup = followup;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the socialRoleDescription
	 */
	public String getdsmDiagnosis() {
		return dsmDiagnosis;
	}

	/**
	 * @param socialRoleDescription
	 *            the socialRoleDescription to set
	 */
	public void setdsmDiagnosis(String dsmDiagnosis) {
		this.dsmDiagnosis = dsmDiagnosis;
	}

	/**
	 * @return the problemType
	 */
	public String getdiagnosisSource() {
		return diagnosisSource;
	}

	/**
	 * @param problemType
	 *            the problemType to set
	 */
	public void setdiagnosisSource(String diagnosisSource) {
		this.diagnosisSource = diagnosisSource;
	}

	/**
	 * @return the serverity
	 */
	public String getServerity() {
		return serverity;
	}

	/**
	 * @param serverity
	 *            the serverity to set
	 */
	public void setServerity(String serverity) {
		this.serverity = serverity;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the copingAbitity
	 */
	public String getCopingAbitity() {
		return copingAbitity;
	}

	/**
	 * @param copingAbitity
	 *            the copingAbitity to set
	 */
	public void setCopingAbitity(String copingAbitity) {
		this.copingAbitity = copingAbitity;
	}

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the goal
	 */
	public String getGoal() {
		return goal;
	}

	/**
	 * @param goal
	 *            the goal to set
	 */
	public void setGoal(String goal) {
		this.goal = goal;
	}

	/**
	 * @return the recommendedInter
	 */
	public String getRecommendedInter() {
		return recommendedInter;
	}

	/**
	 * @param recommendedInter
	 *            the recommendedInter to set
	 */
	public void setRecommendedInter(String recommendedInter) {
		this.recommendedInter = recommendedInter;
	}

	/**
	 * @return the expectedOutcome
	 */
	public String getExpectedOutcome() {
		return expectedOutcome;
	}

	/**
	 * @param expectedOutcome
	 *            the expectedOutcome to set
	 */
	public void setExpectedOutcome(String expectedOutcome) {
		this.expectedOutcome = expectedOutcome;
	}

	/**
	 * @return the clientId
	 */
	public int getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

}
