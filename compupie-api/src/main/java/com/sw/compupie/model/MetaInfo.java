package com.sw.compupie.model;

import java.util.List;

import com.sw.compupie.daoBean.Coping_Ability;
import com.sw.compupie.daoBean.Duration;
import com.sw.compupie.daoBean.EducationLevelBean;
import com.sw.compupie.daoBean.EmploymentStatusBean;
import com.sw.compupie.daoBean.EthinicityBean;
import com.sw.compupie.daoBean.GenderBean;
import com.sw.compupie.daoBean.LivingArrangementBean;
import com.sw.compupie.daoBean.MaritaStatusBean;
import com.sw.compupie.daoImpl.LoadEducationLevel;
import com.sw.compupie.daoImpl.LoadEmploymentStatus;
import com.sw.compupie.daoImpl.LoadEthinicity;
import com.sw.compupie.daoImpl.LoadGender;
import com.sw.compupie.daoImpl.LoadLivingArrangement;
import com.sw.compupie.daoImpl.LoadMaritalStatus;
import com.sw.compupie.daoImpl.Load_Coping_Ability;
import com.sw.compupie.daoImpl.Load_Duration;

public class MetaInfo {

	public List<EducationLevelBean> educationLevel ;
	
	public List<EmploymentStatusBean> employmentStatus ;
	
	public List<EthinicityBean> ethinicity;
	
	public List<Coping_Ability> copingAbility ;
	
	public List<Duration> duration ;
	
	public List<GenderBean> gender ;
	
	public List<LivingArrangementBean> livingArrangement ;
	
	public List<MaritaStatusBean> maritalStatus;
	
	public MetaInfo(){
		LoadEducationLevel edu = new LoadEducationLevel();
		educationLevel = edu.getAllEducationLevels();
		
		LoadEmploymentStatus emp = new LoadEmploymentStatus();
		employmentStatus = emp.getAllEmploymentStatus();
		
		LoadEthinicity eth = new LoadEthinicity();
		ethinicity = eth.getAllEthinicity();
		
		Load_Coping_Ability cop = new Load_Coping_Ability();
		copingAbility = cop.getAllCoping_Ability();
		
		Load_Duration dur = new Load_Duration();
		duration = dur.getAllDuration();
		
		LoadGender gen = new LoadGender();
		gender = gen.getAllGenders();
		
		LoadLivingArrangement liv = new LoadLivingArrangement();
		livingArrangement = liv.getAllLivingArrangement();
		
		LoadMaritalStatus mar = new LoadMaritalStatus();
		maritalStatus = mar.getAllMaritalStatus();
				
		
	}
	
}
