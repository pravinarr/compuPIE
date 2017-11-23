package com.sw.compupie.report.helper;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.sw.compupie.daoBean.AbuseBean;
import com.sw.compupie.daoBean.SubstanceAbuseBean;
import com.sw.compupie.daoImpl.SubstanceAbuseTableManipulation;

public class SubstancerReport extends BaseReport {

	public SubstancerReport(PdfWriter writer) {
		super(writer);
	}

	public void generateSubstanceReport(int id, Document doc) throws DocumentException {
		SubstanceAbuseTableManipulation table = new SubstanceAbuseTableManipulation();
		SubstanceAbuseBean bean = table.getSubstanceHistory(id);
		Font font1 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE);
		Font font2 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
		Font font3 = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
		Paragraph heading = new Paragraph(new Phrase("Substance Abuse: ", font1));
		heading.setAlignment(0);
		doc.add(heading);
		Phrase past30 = new Phrase("Past 30 days use: ", font2);
		Phrase past30String = new Phrase(formPast30String(bean), font3);
		heading = new Paragraph(past30);
		heading.add(past30String);
		heading.setAlignment(3);
		heading.setFirstLineIndent(10);
		doc.add(heading);
		checkLengthCosmetic(doc);
		Phrase lifetime = new Phrase("Lifetime use (years): ", font2);
		Phrase lifeTimeString = new Phrase(formLString(bean), font3);
		heading = new Paragraph(lifetime);
		heading.add(lifeTimeString);
		heading.setAlignment(3);
		heading.setFirstLineIndent(10);
		doc.add(heading);
		checkLengthCosmetic(doc);
	}

	public String formPast30String(SubstanceAbuseBean bean) {
		String str = "";
		str = checkpast30(str, "Alchohol to intoxication", bean.getAlchohol());
		str = checkpast30(str, "Marijuana hashish", bean.getMarijuana());
		str = checkpast30(str, "Hallucinogens", bean.getHallucinogens());
		str = checkpast30(str, "Inhalants", bean.getInhalants());
		str = checkpast30(str, "Heroin", bean.getHeroin());
		str = checkpast30(str, "Methadone", bean.getMethadone());
		str = checkpast30(str, "Other Opiates", bean.getOpiates());
		str = checkpast30(str, "Cocaine", bean.getCocaine());
		str = checkpast30(str, "Amphetamines", bean.getAmphe());
		str = checkpast30(str, "Methamphetamine", bean.getMethamphe());
		str = checkpast30(str, "Barbiturates ", bean.getBarbiturates());
		str = checkpast30(str, "Sedatives/Hypnotics/Tranquilizers", bean.getSedative());
		for(AbuseBean be: bean.getOther()){
			str = checkpast30(str, be.getProblem(), be);
		}
		
		return str;
	}

	public String formLString(SubstanceAbuseBean bean) {
		String str = "";
		str = checkLifetime(str, "Alchohol to intoxication", bean.getAlchohol());
		str = checkLifetime(str, "Marijuana hashish", bean.getMarijuana());
		str = checkLifetime(str, "Hallucinogens", bean.getHallucinogens());
		str = checkLifetime(str, "Inhalants", bean.getInhalants());
		str = checkLifetime(str, "Heroin", bean.getHeroin());
		str = checkLifetime(str, "Methadone", bean.getMethadone());
		str = checkLifetime(str, "Other Opiates", bean.getOpiates());
		str = checkLifetime(str, "Cocaine", bean.getCocaine());
		str = checkLifetime(str, "Amphetamines", bean.getAmphe());
		str = checkLifetime(str, "Methamphetamine", bean.getMethamphe());
		str = checkLifetime(str, "Barbiturates ", bean.getBarbiturates());
		str = checkLifetime(str, "Sedatives/Hypnotics/Tranquilizers", bean.getSedative());
		for(AbuseBean be: bean.getOther()){
			str = checkLifetime(str, be.getProblem(), be);
		}
		return str;
	}

	public String checkLifetime(String str, String abuse, AbuseBean bean) {
		if (bean != null && bean.getLifetime()!= null && !bean.getLifetime().equalsIgnoreCase("0")) {
			if (!str.equalsIgnoreCase(""))
				str += ", ";
			str += abuse + " (#" + bean.getLifetime() + ")";
		}
		return str;
	}

	public String checkpast30(String str, String abuse, AbuseBean bean) {
		if (bean != null && bean.getPast30() != null && !bean.getPast30().equalsIgnoreCase("0")) {
			if (!str.equalsIgnoreCase(""))
				str += ", ";
			str += abuse + " (#" + bean.getPast30() + ")";
		}
		return str;
	}
}
