package com.sw.compupie.report.helper;

import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.sw.compupie.daoBean.Factor1Bean;
import com.sw.compupie.daoBean.Factor2Bean;
import com.sw.compupie.daoBean.Factor3Bean;
import com.sw.compupie.daoBean.Factor4Bean;
import com.sw.compupie.daoBean.Factor4MoreInfoBean;
import com.sw.compupie.daoImpl.Factor1TableManipulation;
import com.sw.compupie.daoImpl.Factor2TableManipulation;
import com.sw.compupie.daoImpl.Factor3TableManipulation;
import com.sw.compupie.daoImpl.Factor4MoreInfoTableManipulation;
import com.sw.compupie.daoImpl.Factor4TableManipulation;

public class FactorLongReport extends BaseReport {
	public FactorLongReport(PdfWriter writer) {
		super(writer);
	}

	private int clientId;
	private int followup;

	public void setFactorInfo(int clientId, int followUp, Document doc) throws DocumentException {
		checkLengthCosmetic(doc);
		this.clientId = clientId;
		this.followup = followUp;
	}

	/**
	 * @param clientId
	 * @param followUp
	 * @param doc
	 * @param fontx
	 * @throws DocumentException
	 */
	public void createShortForFactor1(Document doc) throws DocumentException {
		Factor1TableManipulation mani = new Factor1TableManipulation();
		List<Factor1Bean> list = mani.getFactorInfoForFollowup(this.clientId, this.followup);
		if (!list.isEmpty()) {
			Font fontx = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE);
			Paragraph heading = new Paragraph(new Phrase("Factor 1: Social roles and relationships", fontx));
			heading.setAlignment(0);
			doc.add(heading);
		}
		ShortFormProblemHelper helper = new ShortFormProblemHelper();
		for (Factor1Bean bean : list) {
			StringBuffer sb = new StringBuffer();
			sb.append(bean.getProblemType() + " Role, ");
			sb.append(bean.getSocialRoleProblemType() + " Type, ");
			sb.append(bean.getServerity() + " Severity, ");
			sb.append(bean.getDuration() + " Duration, ");
			sb.append(bean.getCopingAbitity() + " Coping Skills.");
			helper.createALongProblem(sb.toString(), bean.getRecommendedInter(), bean.getExpectedOutcome(),
					bean.getGoal(), bean.getPriority(), doc);
		}
		checkLengthCosmetic(doc);
	}

	public void createShortForFactor2(Document doc) throws DocumentException {
		Factor2TableManipulation mani = new Factor2TableManipulation();
		List<Factor2Bean> list = mani.getFactorInfoByFollowUp(this.followup, this.clientId);

		if (!list.isEmpty()) {
			Font fontx = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE);
			Paragraph heading = new Paragraph(new Phrase("Factor 2: Environmental problems/needs", fontx));
			heading.setAlignment(0);
			doc.add(heading);
		}
		ShortFormProblemHelper helper = new ShortFormProblemHelper();
		for (Factor2Bean bean : list) {
			StringBuffer sb = new StringBuffer();
			sb.append(bean.getProblemType() + " Type, ");
			sb.append(bean.getServerity() + " Severity, ");
			sb.append(bean.getDuration() + " Duration, ");
			sb.append(bean.getCopingAbitity() + " Coping Skills, ");
			;
			helper.createALongProblem(sb.toString(), bean.getRecommendedInter(), bean.getExpectedOutcome(),
					bean.getGoal(), bean.getPriority(), doc);
		}
		checkLengthCosmetic(doc);
	}

	public void createShortForFactor3(Document doc) throws DocumentException {
		Factor3TableManipulation mani = new Factor3TableManipulation();
		List<Factor3Bean> list = mani.getFactorInfoByFollowUp(this.followup, this.clientId);
		Factor4MoreInfoTableManipulation mn = new Factor4MoreInfoTableManipulation();
		Factor4MoreInfoBean b = mn.getStrength(this.clientId);
		if (!b.getProblemStr().trim().equalsIgnoreCase("")) {
			Font fontx = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE);
			Font fonty = new Font(Font.FontFamily.HELVETICA, 10, Font.UNDERLINE);
			Paragraph heading = new Paragraph(new Phrase("Factor 3: MH functioning", fontx));
			heading.setAlignment(0);
			doc.add(heading);
			if (!b.getProblemStr().trim().equalsIgnoreCase("")) {
				Phrase p = new Phrase(b.getProblemStr(), fonty);
				Paragraph heading1 = new Paragraph(p);
				heading1.setAlignment(1);
				doc.add(heading1);
			}
		}else if(!list.isEmpty()){
			Font fontx = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE);
			Font fonty = new Font(Font.FontFamily.HELVETICA, 12, Font.UNDERLINE);
			Paragraph heading = new Paragraph(new Phrase("Factor 3: MH functioning", fontx));
			heading.setAlignment(0);
			doc.add(heading);
		}
		ShortFormProblemHelper helper = new ShortFormProblemHelper();
		for (Factor3Bean bean : list) {
			StringBuffer sb = new StringBuffer();
			sb.append(bean.getdsmDiagnosis() + " Type, ");
			sb.append(bean.getdiagnosisSource() + " Source, ");
			sb.append(bean.getServerity() + " Severity, ");
			sb.append(bean.getDuration() + " Duration, ");
			sb.append(bean.getCopingAbitity() + " Coping Skills, ");
			sb.append(bean.getSpecifier() + " Specifier, ");
			helper.createALongProblem(sb.toString(), bean.getRecommendedInter(), bean.getExpectedOutcome(),
					bean.getGoal(), bean.getPriority(), doc);
		}
		checkLengthCosmetic(doc);
	}

	public void createShortForFactor4(Document doc) throws DocumentException {
		Factor4TableManipulation mani = new Factor4TableManipulation();
		List<Factor4Bean> list = mani.getFactorInfoByFollowUp(this.followup, this.clientId);

		if (!list.isEmpty()) {
			Font fontx = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE);
			Paragraph heading = new Paragraph(new Phrase("Factor 4: PH functioning", fontx));
			heading.setAlignment(0);
			doc.add(heading);
		}
		ShortFormProblemHelper helper = new ShortFormProblemHelper();

		for (Factor4Bean bean : list) {
			StringBuffer sb = new StringBuffer();
			sb.append(bean.getDiagnosis() + " Type, ");
			sb.append(bean.getdiagnosisSource() + " Source, ");
			sb.append(bean.getServerity() + " Severity, ");
			sb.append(bean.getDuration() + " Duration, ");
			sb.append(bean.getCopingAbitity() + " Coping Skills, ");
			helper.createALongProblem(sb.toString(), bean.getRecommendedInter(), bean.getExpectedOutcome(),
					bean.getGoal(), bean.getPriority(), doc);
		}
		checkLengthCosmetic(doc);
	}

}
