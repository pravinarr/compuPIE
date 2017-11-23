package com.sw.compupie.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sw.compupie.daoBean.AdditionalNotesBean;
import com.sw.compupie.daoBean.CaseHistoryBean;
import com.sw.compupie.daoBean.Factor1Bean;
import com.sw.compupie.daoBean.Factor2Bean;
import com.sw.compupie.daoBean.Factor3Bean;
import com.sw.compupie.daoBean.Factor4Bean;
import com.sw.compupie.daoBean.FollowUpBean;
import com.sw.compupie.daoBean.MentalStatusExamBean;
import com.sw.compupie.daoBean.SearchBean;
import com.sw.compupie.daoBean.StrengthAndResourcesBean;
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
import com.sw.compupie.daoImpl.Sequencer;
import com.sw.compupie.daoImpl.StrengthResourceManipulation;
import com.sw.compupie.daoImpl.SubstanceAbuseTableManipulation;
import com.sw.compupie.model.PatientInfo;

@Path("/patient")
public class PatientService {

	@GET
	@Path("/search")
	@Produces("application/json")
	public Response searchPatient(@QueryParam("clientId") String clientId, @QueryParam("firstName") String firstName,
			@QueryParam("LastName") String LastName, @QueryParam("middleName") String middleName,
			@QueryParam("zipCode") String zipCode, @QueryParam("phone") String phone,@QueryParam("userId") String userId) {
		SearchBean bean = new SearchBean();
		bean.setClientId(clientId);
		bean.setFirstName(firstName);
		bean.setMiddleName(middleName);
		bean.setLastName(LastName);
		bean.setZipCode(zipCode);
		bean.setPhone(phone);
		bean.setUserId(userId);
		ClientTableManipulation tab = new ClientTableManipulation();
		return Response.ok(tab.searchClient(bean)).build();
	}

	@GET
	@Path("/nextclientid")
	@Produces("application/json")
	public Response getNextId() {
		Sequencer cli = new Sequencer();
		int retu = cli.nextId();
		return Response.ok(retu).build();
	}

	@PUT
	@Path("/remove/{param}")
	@Produces("application/json")
	public Response deleteFromSearch(@PathParam("param") String id) {
		ClientTableManipulation table = new ClientTableManipulation();
		table.deleteFromSearch(id);
		return Response.ok().build();
	}

	@GET
	@Path("/{param}")
	@Produces("application/json")
	public Response getMetaInfo(@PathParam("param") String id) {
		int ids = Integer.parseInt(id);
		return Response.ok(new PatientInfo(ids,false)).build();
	}
	
	@GET
	@Path("/reassess/{param}")
	@Produces("application/json")
	public Response getReassessData(@PathParam("param") String id) {
		int ids = Integer.parseInt(id);
		return Response.ok(new PatientInfo(ids,true)).build();
	}

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAPatient(PatientInfo patient) {
		ClientTableManipulation cli = new ClientTableManipulation();
		int retu = cli.getmaxId() + 1;
		retu = cli.saveNewClient(patient.clientInfo);

		CaseHistoryTableManipulation cas = new CaseHistoryTableManipulation();
		CaseHistoryBean bean1 = patient.caseHistory.get(0);
		// bean1.setClientId(retu);
		cas.saveNewHistory(bean1);

		Factor1TableManipulation fac1 = new Factor1TableManipulation();
		for (Factor1Bean bean : patient.factor1) {
			// bean.setClientId(retu);
			fac1.saveNewFactory(bean);
		}

		Factor4MoreInfoTableManipulation more = new Factor4MoreInfoTableManipulation();
		more.saveNewStrength(patient.factor3Choice);

		Factor2TableManipulation fac2 = new Factor2TableManipulation();
		for (Factor2Bean bean : patient.factor2) {
			// bean.setClientId(retu);
			fac2.saveNewFactory(bean);
		}

		Factor3TableManipulation fac3 = new Factor3TableManipulation();
		for (Factor3Bean bean : patient.factor3) {
			// bean.setClientId(retu);
			fac3.saveNewFactory(bean);
		}

		Factor4TableManipulation fac4 = new Factor4TableManipulation();
		for (Factor4Bean bean : patient.factor4) {
			// bean.setClientId(retu);
			fac4.saveNewFactory(bean);
		}

		MentalStatusExamManipulation mse = new MentalStatusExamManipulation();
		MentalStatusExamBean bean = patient.mentalExam;
		// bean.setClientId(retu);
		mse.saveNewStrength(bean);

		AdditionalNotesManipulation add = new AdditionalNotesManipulation();
		AdditionalNotesBean bean2 = patient.additionalNotes;
		// bean2.setClientId(retu);
		add.saveNewStrength(bean2);

		StrengthResourceManipulation st = new StrengthResourceManipulation();
		StrengthAndResourcesBean bean3 = patient.strResource;
		// bean3.setClientid(retu);
		st.saveNewStrength(bean3);

		FollowUpTableManipulation fol = new FollowUpTableManipulation();
		FollowUpBean bean4 = patient.followUp.get(0);
		// bean4.setClientid(retu);
		fol.saveNewFollowUp(bean4);
		
		SubstanceAbuseTableManipulation subs = new SubstanceAbuseTableManipulation();
		//patient.getSubstance().setClientId(retu);
		subs.saveNewAbuse(patient.getSubstance());
		
		System.out.println("The new client id generated " + retu);
		return Response.ok(bean2.getClientId()).build();
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatepatient(PatientInfo patient) {
		ClientTableManipulation cli = new ClientTableManipulation();
		cli.updateNewClient(patient.clientInfo);

		CaseHistoryTableManipulation cas = new CaseHistoryTableManipulation();
		cas.updateNewHIstory(patient.caseHistory.get(0));

		Factor1TableManipulation fac1 = new Factor1TableManipulation();
		for (Factor1Bean bean : patient.factor1) {
			if (bean.getId() == 0) {
				fac1.saveNewFactory(bean);
			} else {
				fac1.updateNewFactory(bean);
			}
		}

		Factor4MoreInfoTableManipulation more = new Factor4MoreInfoTableManipulation();
		if ((patient.factor3Choice != null)) {
			if (patient.factor3Choice.getId() == 0) {
				more.saveNewStrength(patient.factor3Choice);
			} else {
				more.updateNewStrength(patient.factor3Choice);
			}
		}

		Factor2TableManipulation fac2 = new Factor2TableManipulation();
		for (Factor2Bean bean : patient.factor2) {
			if (bean.getId() == 0) {
				fac2.saveNewFactory(bean);
			} else {
				fac2.updateNewFactory(bean);
			}
		}

		Factor3TableManipulation fac3 = new Factor3TableManipulation();
		for (Factor3Bean bean : patient.factor3) {
			if (bean.getId() == 0) {
				fac3.saveNewFactory(bean);
			} else {
				fac3.updateNewFactory(bean);
			}
		}

		Factor4TableManipulation fac4 = new Factor4TableManipulation();
		for (Factor4Bean bean : patient.factor4) {
			if (bean.getId() == 0) {
				fac4.saveNewFactory(bean);
			} else {
				fac4.updateNewFactory(bean);
			}
		}

		MentalStatusExamManipulation mse = new MentalStatusExamManipulation();
		if (patient.mentalExam.getId() == 0) {
			mse.saveNewStrength(patient.mentalExam);
		} else {
			mse.updateNewStrength(patient.mentalExam);
		}

		AdditionalNotesManipulation add = new AdditionalNotesManipulation();
		if (patient.additionalNotes.getId() == 0) {
			add.saveNewStrength(patient.additionalNotes);
		} else {
			add.updateNewStrength(patient.additionalNotes);
		}

		StrengthResourceManipulation st = new StrengthResourceManipulation();
		if (patient.strResource.getId() == 0) {
			st.saveNewStrength(patient.strResource);
		} else {
			st.updateNewStrength(patient.strResource);
		}

		FollowUpTableManipulation fol = new FollowUpTableManipulation();
		for (FollowUpBean bean : patient.followUp) {
			if (bean.getId() == 0) {
				fol.saveNewFollowUp(bean);
			}
		}
		
		SubstanceAbuseTableManipulation subs = new SubstanceAbuseTableManipulation();
		subs.updateNewHIstory(patient.getSubstance());
		
		return Response.ok(patient.strResource.getClientid()).build();
	}

	@PUT
	@Path("/delete/{factorType}/{clientid}/{problemid}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatepatient(@PathParam("factorType") String factorType, @PathParam("clientid") String clientid,
			@PathParam("problemid") String problemid) {
		boolean result = false;
		if (factorType.equalsIgnoreCase("1")) {
			Factor1TableManipulation manip = new Factor1TableManipulation();
			result = manip.deleteFactory(clientid, problemid);

		} else if (factorType.equalsIgnoreCase("2")) {
			Factor2TableManipulation manip = new Factor2TableManipulation();
			result = manip.deleteFactory(clientid, problemid);

		} else if (factorType.equalsIgnoreCase("3")) {
			Factor3TableManipulation manip = new Factor3TableManipulation();
			result = manip.deleteFactory(clientid, problemid);

		} else if (factorType.equalsIgnoreCase("4")) {
			Factor4TableManipulation manip = new Factor4TableManipulation();
			result = manip.deleteFactory(clientid, problemid);
		}

		return (result) ? Response.ok().build() : Response.status(404).build();
	}

}
