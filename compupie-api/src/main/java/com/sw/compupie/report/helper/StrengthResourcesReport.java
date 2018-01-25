package com.sw.compupie.report.helper;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.sw.compupie.daoBean.StrengthAndResourcesBean;
import com.sw.compupie.daoImpl.StrengthResourceManipulation;

public class StrengthResourcesReport extends BaseReport {

	public StrengthResourcesReport(PdfWriter writer) {
		super(writer);
	}

	public void generateStrengthAndResources(int clientid, Document doc) throws DocumentException {
		checkLengthCosmetic(doc);
		StrengthResourceManipulation mani = new StrengthResourceManipulation();
		StrengthAndResourcesBean bean = mani.getStrength(clientid);
		Font font1 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE);
		Paragraph heading = new Paragraph(new Phrase("Strengths and Resources", font1));
		heading.setAlignment(0);
		doc.add(heading);
		generateFactor34Strengths(clientid, doc, bean.getFactor1(), "Factor 1: Social Role and Relationship");
		checkLengthCosmetic(doc);
		generateFactor1Strengths(clientid, doc, bean.getFactor2(), "Factor 2: Environmental Situations");
		checkLengthCosmetic(doc);
		generateFactor34Strengths(clientid, doc, bean.getFactor3(), "Factor 3: Mental Health Functioning");
		checkLengthCosmetic(doc);
		generateFactor34Strengths(clientid, doc, bean.getFactor4(), "Factor 4: Physical Health Functioning");
		checkLengthCosmetic(doc);

	}

	private void generateFactor1Strengths(int clientid, Document doc, String value, String title)
			throws DocumentException {
		Font font3 = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
		Font font4 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
		Font font5 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
		String[] splits = value.split(":");
		Phrase titl = new Phrase();
		titl.add(new Phrase(title, font4));
		doc.add(new Paragraph(titl));
		Paragraph child = new Paragraph();
		for (String s1 : splits) {
			String[] s = s1.split("-");
			if (s.length > 1) {
				if(s[1].split(",").length > 0 && s[1].split(",")[0].trim().length()>0  ){
					child.add(new Phrase((s[0] + ": "), font5));
					String sp[] = s[1].split(",");
					for (String str : sp) {
						if (!str.trim().equalsIgnoreCase("")) {
							if (child.isEmpty()) {
								child.add(new Phrase(str, font3));
							} else {
								if (!str.trim().equalsIgnoreCase("")) {
									child.add(new Phrase((", " + str), font3));
								}
							}
						}
					}
				}
				if (!child.isEmpty()) {
					child.add(new Phrase(("\n"), font3));
					child.setAlignment(Element.ALIGN_JUSTIFIED);
					child.setIndentationLeft(10);
					doc.add(child);
					child = new Paragraph();
				}
			}
		}
		if(child.isEmpty()){
			child.add(new Phrase("None", font3));
			child.setIndentationLeft(10);
			doc.add(child);
		}
	}

	private void generateFactor34Strengths(int clientid, Document doc, String value, String title)
			throws DocumentException {
		Font font3 = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
		Font font4 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
		String[] splits = value.split(":");
		Phrase titl = new Phrase();
		titl.add(new Phrase(title, font4));
		doc.add(new Paragraph(titl));
		Paragraph child = new Paragraph();
		for (String s1 : splits) {
			String[] s = s1.split("-");
			String sp[] = s[0].split(",");
			for (String str : sp) {
				if (child.isEmpty()) {
					child.add(new Phrase(str, font3));
				} else {
					if (!str.trim().equalsIgnoreCase("")) {
						child.add(new Phrase((", " + str), font3));
					}
				}
			}
			if(child.isEmpty()){
				child.add(new Phrase("None", font3));
			}
			child.setAlignment(Element.ALIGN_JUSTIFIED);
			child.setIndentationLeft(10);
			doc.add(child);
		}

	}

}
