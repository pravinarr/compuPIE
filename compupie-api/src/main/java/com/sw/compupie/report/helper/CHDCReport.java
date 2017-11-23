package com.sw.compupie.report.helper;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.sw.compupie.daoBean.CaseHistoryBean;
import com.sw.compupie.daoImpl.CaseHistoryTableManipulation;

public class CHDCReport extends BaseReport {

	public CHDCReport(PdfWriter writer) {
		super(writer);
	}

	public void generateClientCaseHistory(int id, Document doc) throws DocumentException {
		CaseHistoryTableManipulation mani = new CaseHistoryTableManipulation();
		CaseHistoryBean bean = mani.getCaseHistory(id).get(0);
		Font font1 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE);
		Font font2 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
		Font font3 = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
		Font font4 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
		Paragraph heading = new Paragraph(new Phrase("Case History: ", font1));
		heading.setAlignment(0);
		doc.add(heading);
		heading = new Paragraph(new Phrase("Reason for Referral: ", font2));
		heading.setAlignment(0);
		heading.setIndentationRight(10);
		doc.add(heading);
		heading = new Paragraph(new Phrase(bean.getReasonForRefer(), font3));
		heading.setAlignment(3);
		heading.setFirstLineIndent(10);
		doc.add(heading);
		heading = new Paragraph(new Phrase("Current Situation: ", font2));
		heading.setAlignment(0);
		doc.add(heading);
		checkLengthCosmetic(doc);

		heading = new Paragraph(new Phrase(bean.getCurrentSituation(), font3));
		heading.setAlignment(3);
		heading.setFirstLineIndent(10);
		doc.add(heading);
		checkLengthCosmetic(doc);

		heading = new Paragraph(new Phrase("Relevant History: ", font2));
		heading.setAlignment(0);
		doc.add(heading);
		checkLengthCosmetic(doc);

		heading = new Paragraph(new Phrase(bean.getRelevantHistory(), font3));
		heading.setAlignment(3);
		heading.setFirstLineIndent(10);
		doc.add(heading);
		checkLengthCosmetic(doc);

		if (!bean.getTraumaHistory().trim().equalsIgnoreCase("")) {
			heading = new Paragraph(new Phrase("Trauma History: ", font2));
			heading.setAlignment(0);
			doc.add(heading);
			String[] splits = bean.getTraumaHistory().split(",");
			Phrase child = new Phrase();
			child.add(new Phrase("Childhood: ", font4));
			int i = 0;
			for (String s : splits) {
				String sp[] = s.split("-");
				if (sp.length > 1) {
					sp[1] = sp[1].replaceAll(":", "");
					if ("childhood".equalsIgnoreCase(sp[0])) {
						if (i == 0) {
							child.add(new Phrase(sp[1], font3));
							i++;
						} else {
							child.add(new Phrase(", " + sp[1], font3));
						}
					}
				}
			}
			heading = new Paragraph(child);
			heading.setAlignment(3);
			heading.setFirstLineIndent(10);
			doc.add(heading);

			Phrase adult = new Phrase();
			adult.add(new Phrase("Adulthood: ", font4));
			i = 0;
			for (String s : splits) {
				String sp[] = s.split("-");
				if (sp.length > 1) {
					sp[1] = sp[1].replaceAll(":", "");
					if ("adulthood".equalsIgnoreCase(sp[0])) {
						if (i == 0) {
							adult.add(new Phrase(sp[1], font3));
							i++;
						} else {
							adult.add(new Phrase(", " + sp[1], font3));
						}

					}
				}
			}
			//adult.get
			heading = new Paragraph(adult);
			heading.setAlignment(3);
			heading.setFirstLineIndent(10);
			heading.setSpacingAfter(10);
			doc.add(heading);
		}
		checkLengthCosmetic(doc);

	}

}
