package com.sw.compupie.model;

import java.io.Serializable;
import java.util.List;

import com.sw.compupie.daoBean.AdditionalNotesBean;
import com.sw.compupie.daoBean.CaseHistoryBean;
import com.sw.compupie.daoBean.ClientBean;
import com.sw.compupie.daoBean.Factor1Bean;
import com.sw.compupie.daoBean.Factor2Bean;
import com.sw.compupie.daoBean.Factor3Bean;
import com.sw.compupie.daoBean.Factor4Bean;
import com.sw.compupie.daoBean.Factor4MoreInfoBean;
import com.sw.compupie.daoBean.FollowUpBean;
import com.sw.compupie.daoBean.MentalStatusExamBean;
import com.sw.compupie.daoBean.StrengthAndResourcesBean;
import com.sw.compupie.daoBean.SubstanceAbuseBean;
import com.sw.compupie.daoImpl.AdditionalNotesManipulation;
import com.sw.compupie.daoImpl.CaseHistoryTableManipulation;
import com.sw.compupie.daoImpl.ClientTableManipulation;
import com.sw.compupie.daoImpl.Factor1TableManipulation;
import com.sw.compupie.daoImpl.Factor2TableManipulation;
import com.sw.compupie.daoImpl.Factor3TableManipulation;
import com.sw.compupie.daoImpl.Factor4MoreInfoTableManipulation;
import com.sw.compupie.daoImpl.Factor4TableManipulation;
import com.sw.compupie.daoImpl.FollowUpTableManipulation;
import com.sw.compupie.daoImpl.MentalStatusExamManipulation;
import com.sw.compupie.daoImpl.StrengthResourceManipulation;
import com.sw.compupie.daoImpl.SubstanceAbuseTableManipulation;

public class PatientInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientBean clientInfo;

	public List<CaseHistoryBean> caseHistory;

	public List<Factor1Bean> factor1;

	public List<Factor2Bean> factor2;

	public List<Factor3Bean> factor3;

	public Factor4MoreInfoBean factor3Choice;

	public List<Factor4Bean> factor4;

	public MentalStatusExamBean mentalExam;

	public AdditionalNotesBean additionalNotes;

	public StrengthAndResourcesBean strResource;

	public SubstanceAbuseBean substance;

	public List<FollowUpBean> followUp;

	public SubstanceAbuseBean getSubstance() {
		return substance;
	}

	public void setSubstance(SubstanceAbuseBean sunbstance) {
		this.substance = sunbstance;
	}
	
	public ClientBean getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientBean clientInfo) {
		this.clientInfo = clientInfo;
	}

	public List<CaseHistoryBean> getCaseHistory() {
		return caseHistory;
	}

	public void setCaseHistory(List<CaseHistoryBean> caseHistory) {
		this.caseHistory = caseHistory;
	}

	public List<Factor1Bean> getFactor1() {
		return factor1;
	}

	public void setFactor1(List<Factor1Bean> factor1) {
		this.factor1 = factor1;
	}

	public List<Factor2Bean> getFactor2() {
		return factor2;
	}

	public void setFactor2(List<Factor2Bean> factor2) {
		this.factor2 = factor2;
	}

	public List<Factor3Bean> getFactor3() {
		return factor3;
	}

	public void setFactor3(List<Factor3Bean> factor3) {
		this.factor3 = factor3;
	}

	public Factor4MoreInfoBean getFactor3Choice() {
		return factor3Choice;
	}

	public void setFactor3Choice(Factor4MoreInfoBean factor3Choice) {
		this.factor3Choice = factor3Choice;
	}

	public List<Factor4Bean> getFactor4() {
		return factor4;
	}

	public void setFactor4(List<Factor4Bean> factor4) {
		this.factor4 = factor4;
	}

	public MentalStatusExamBean getMentalExam() {
		return mentalExam;
	}

	public void setMentalExam(MentalStatusExamBean mentalExam) {
		this.mentalExam = mentalExam;
	}

	public AdditionalNotesBean getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(AdditionalNotesBean additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	public StrengthAndResourcesBean getStrResource() {
		return strResource;
	}

	public void setStrResource(StrengthAndResourcesBean strResource) {
		this.strResource = strResource;
	}

	public List<FollowUpBean> getFollowUp() {
		return followUp;
	}

	public void setFollowUp(List<FollowUpBean> followUp) {
		this.followUp = followUp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PatientInfo() {

	}

	public PatientInfo(int id, boolean reassess) {

		String strId = "" + id;

		ClientTableManipulation cli = new ClientTableManipulation();
		clientInfo = cli.getClientInfo(strId);

		CaseHistoryTableManipulation casehis = new CaseHistoryTableManipulation();
		caseHistory = casehis.getCaseHistory(id);
		if (reassess) {
			Factor1TableManipulation fac1 = new Factor1TableManipulation();
			factor1 = fac1.getFactorInfoLast(id);

			Factor2TableManipulation fac2 = new Factor2TableManipulation();
			factor2 = fac2.getFactorInfoLast(id);

			Factor3TableManipulation fac3 = new Factor3TableManipulation();
			factor3 = fac3.getFactorInfoLast(id);

			Factor4TableManipulation fac4 = new Factor4TableManipulation();
			factor4 = fac4.getFactorInfoLast(id);
		} else {
			Factor1TableManipulation fac1 = new Factor1TableManipulation();
			factor1 = fac1.getFactorInfo(id);

			Factor2TableManipulation fac2 = new Factor2TableManipulation();
			factor2 = fac2.getFactorInfo(id);

			Factor3TableManipulation fac3 = new Factor3TableManipulation();
			factor3 = fac3.getFactorInfo(id);

			Factor4TableManipulation fac4 = new Factor4TableManipulation();
			factor4 = fac4.getFactorInfo(id);
		}
		
		Factor4MoreInfoTableManipulation more = new Factor4MoreInfoTableManipulation();
		factor3Choice = more.getStrength(id);
		
		MentalStatusExamManipulation mse = new MentalStatusExamManipulation();
		mentalExam = mse.getStrength(id);

		AdditionalNotesManipulation add = new AdditionalNotesManipulation();
		additionalNotes = add.getStrength(id);

		StrengthResourceManipulation res = new StrengthResourceManipulation();
		strResource = res.getStrength(id);

		FollowUpTableManipulation fol = new FollowUpTableManipulation();
		followUp = fol.getFollowUpInfo(id);
		
		SubstanceAbuseTableManipulation subs = new SubstanceAbuseTableManipulation();
		substance = subs.getSubstanceHistory(id); 

	}

}
