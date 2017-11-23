package com.sw.compupie.report.helper;

import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.sw.compupie.daoBean.MentalStatusExamBean;
import com.sw.compupie.daoBean.MentalStatusObject;
import com.sw.compupie.daoImpl.MentalStatusExamManipulation;

public class MSEReport extends BaseReport {

	public MSEReport(PdfWriter writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}

	public void createMSEReport(int clientId, Document doc) throws DocumentException {
		Font font4 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
		Font font5 = new Font(Font.FontFamily.HELVETICA, 8);

		Font font1 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE);
		Paragraph heading = new Paragraph(new Phrase("Mental Status Exam:", font1));
		heading.setAlignment(0);
		doc.add(heading);

		MentalStatusExamManipulation mani = new MentalStatusExamManipulation();
		MentalStatusExamBean bean = mani.getStrength(clientId);
		if (bean != null) {
			// String test = bean.getProblemStr();
			List<MentalStatusObject> list = bean.getProblemStr();
			for (MentalStatusObject object : list) {
				if (object.getValues().size() != 0) {
					Phrase titl = new Phrase();
					titl.add(new Phrase(object.getName(), font4));
					doc.add(new Paragraph(titl));
					Paragraph child = new Paragraph();
					child.add(new Phrase(String.join(",", object.getValues()), font5));
					doc.add(child);
					if (object.getRisk().equalsIgnoreCase("true") && object.getRiskValue() != null
							&& !object.getRiskValue().trim().equalsIgnoreCase("")) {
						child = new Paragraph();
						child.add(new Phrase("Risk Level: " + object.getRiskValue(), font5));
						doc.add(child);
					}
				}
			}
		}
		/*
		 * String[] sp = test.split("#"); for (String s : sp) { String[] ss =
		 * s.split("_"); if (ss.length > 1) { Phrase titl = new Phrase();
		 * titl.add(new Phrase(ss[0], font4)); doc.add(new Paragraph(titl));
		 * 
		 * Paragraph child = new Paragraph(); if (ss[0].contains("RISK OF")) {
		 * 
		 * String[] g = ss[1].split(":"); if (g[0] != null &&
		 * !g[0].equalsIgnoreCase("")) { child.add(new Phrase(g[0], font5));
		 * doc.add(child); } child = new Paragraph(); child.add(new Phrase(
		 * "Risk Level: " + g[1], font5)); doc.add(child);
		 * 
		 * } else { if (ss[1] != null && !ss[1].equalsIgnoreCase("")) { ss[1] =
		 * ss[1].trim(); if (ss[1].trim().charAt(0) == ',') { ss[1] =
		 * ss[1].substring(1, ss[1].length()); } child.add(new
		 * Phrase(replace(ss[1], "Casual dress, normal grooming & hygiene,", "")
		 * .replaceAll(" ,", ",").replaceAll(",", ", "), font5));
		 * doc.add(child); } } } } }
		 */
		checkLengthCosmetic(doc);

	}

	public String replace(String src, String target, String replacement) {
		int index = src.indexOf(target);
		boolean replace = false; // If the found "red" should be replaced or not

		while (index != -1) {
			String tempString = src.substring(index); // Starts at the first
														// "red"

			if (replace) {
				tempString = tempString.replaceFirst(target, replacement); // Replace
																			// the
																			// first
																			// occurence
																			// of
																			// "red"
				src = src.substring(0, index) + tempString; // Make the change
															// to the original
															// String
				replace = false;
			} else
				replace = true;

			index = src.indexOf(target, index + 1); // Search for next "red"
		}
		return src;
	}
}
