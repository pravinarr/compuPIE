package com.sw.compupie.model;

import java.util.ArrayList;
import java.util.List;

import com.sw.compupie.daoBean.Coping_Ability;
import com.sw.compupie.daoBean.DSM_AXIS_5;
import com.sw.compupie.daoBean.Duration;
import com.sw.compupie.daoBean.EducationLevelBean;
import com.sw.compupie.daoBean.EmploymentStatusBean;
import com.sw.compupie.daoBean.EthinicityBean;
import com.sw.compupie.daoBean.GenderBean;
import com.sw.compupie.daoBean.LivingArrangementBean;
import com.sw.compupie.daoBean.MaritaStatusBean;
import com.sw.compupie.daoBean.MentalStatus_Category;
import com.sw.compupie.daoBean.PhysicalHealthProblems;
import com.sw.compupie.daoBean.Priority;
import com.sw.compupie.daoBean.Severity;
import com.sw.compupie.daoBean.SocialRoleCategory;
import com.sw.compupie.daoBean.SocialRoleProblemType;
import com.sw.compupie.daoBean.StrengthFactor1;
import com.sw.compupie.daoBean.Strength_Factor2_Category;
import com.sw.compupie.daoBean.Strength_Factor3;
import com.sw.compupie.daoBean.Strength_Factor4;
import com.sw.compupie.daoBean.TraumaBean;
import com.sw.compupie.daoImpl.LoadEducationLevel;
import com.sw.compupie.daoImpl.LoadEmploymentStatus;
import com.sw.compupie.daoImpl.LoadEthinicity;
import com.sw.compupie.daoImpl.LoadGender;
import com.sw.compupie.daoImpl.LoadLivingArrangement;
import com.sw.compupie.daoImpl.LoadMaritalStatus;
import com.sw.compupie.daoImpl.LoadPhysicalHealth;
import com.sw.compupie.daoImpl.LoadTraumaHIstory;
import com.sw.compupie.daoImpl.Load_Coping_Ability;
import com.sw.compupie.daoImpl.Load_DSM_AXIS_5;
import com.sw.compupie.daoImpl.Load_Duration;
import com.sw.compupie.daoImpl.Load_MentalStatus;
import com.sw.compupie.daoImpl.Load_Priority;
import com.sw.compupie.daoImpl.Load_Severity;
import com.sw.compupie.daoImpl.Load_SocialRoleProblemType;
import com.sw.compupie.daoImpl.Load_SocialRoles;
import com.sw.compupie.daoImpl.Load_Strength_Factor2;
import com.sw.compupie.daoImpl.Load_Strength_Factor3;
import com.sw.compupie.daoImpl.Load_Strength_Factor4;
import com.sw.compupie.daoImpl.Load_strength_facor1;

public class MetaInfo {

	public List<EducationLevelBean> educationLevel ;
	
	public List<EmploymentStatusBean> employmentStatus ;
	
	public List<EthinicityBean> ethinicity;
	
	public List<Coping_Ability> copingAbility ;
	
	public List<Duration> duration ;
	
	public List<GenderBean> gender ;
	
	public List<LivingArrangementBean> livingArrangement ;
	
	public List<MaritaStatusBean> maritalStatus;
	
	public List<MentalStatus_Category> mentalStatusProblems;
	
	public List<TraumaBean> childTrauma = new ArrayList<TraumaBean>();
	
	public List<TraumaBean> adultTrauma = new ArrayList<TraumaBean>();
	
	public List<StrengthFactor1> strengthFactor1;
	
	public List<Strength_Factor2_Category> strengthFactor2;
	
	public List<Strength_Factor3> strengthFactor3;
	
	public List<Strength_Factor4> strengthFactor4;
	
	public List<SocialRoleProblemType> socialRoleProblemType;
	
	public List<SocialRoleCategory> socialRole;
	
	public List<Priority> priority;
	
	public List<Severity> severity;
	
	public Facto2MetaInfoWrapper factor2MetaInfo;
	
	
	
	public List<DSM_AXIS_5> dsmAxis5;
	
	public List<PhysicalHealthProblems> physicalHealthProblem;
	
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
				
		Load_MentalStatus men = new Load_MentalStatus();
		mentalStatusProblems = men.getAllMentalStatusProblemsByCategory();
		
		MentalStatus_Category temp = null ;
		for(MentalStatus_Category cat : mentalStatusProblems){
			if(cat.getCategory().equalsIgnoreCase("RISK LEVEL")){
				temp = cat;
			}
		}
		mentalStatusProblems.remove(temp);
		for(MentalStatus_Category cat : mentalStatusProblems){
			if(cat.getCategory().contains("RISK OF")){
				cat.getRiskLevel().addAll(temp.getItems());
			}
		}
		
		LoadTraumaHIstory hist = new LoadTraumaHIstory();
		List<TraumaBean> tem = hist.getAllTraumaHistory();
		
		for(TraumaBean tr : tem){
			if(tr.getCategory().equalsIgnoreCase("Childhood")){
				childTrauma.add(tr);
			}else{
				adultTrauma.add(tr);
			}
		}
		
		Load_strength_facor1 fac1 = new Load_strength_facor1();
		strengthFactor1 = fac1.getAllStrength_Factor_1();
		
		Load_Strength_Factor2 fac2 = new Load_Strength_Factor2();
		strengthFactor2 = fac2.getAllProblemsByCategory();
		
		Load_Strength_Factor3 fac3 = new Load_Strength_Factor3();
		strengthFactor3 = fac3.getAllStrength_Factor_3();
		
		Load_Strength_Factor4 fac4 = new Load_Strength_Factor4();
		strengthFactor4 = fac4.getAllStrength_Factor4();
		
		Load_SocialRoleProblemType type = new Load_SocialRoleProblemType();
		socialRoleProblemType = type.getAllProblemTypes();
		
		Load_SocialRoles roles = new Load_SocialRoles();
		socialRole = roles.getAllProblemsByCategory();
		
		Load_Priority pri = new Load_Priority();
		priority = pri.getAllPriority();
		
		Load_Severity sev = new Load_Severity();
		severity = sev.getAllSeverity();
		
		factor2MetaInfo = new Facto2MetaInfoWrapper();
		
		Load_DSM_AXIS_5 dsm = new Load_DSM_AXIS_5();
		dsmAxis5 = dsm.getAllDSM_AXIS_5();
		
		LoadPhysicalHealth phy = new LoadPhysicalHealth();
		physicalHealthProblem = phy.getAllProblemsByCategory();
		
	}
	
}
