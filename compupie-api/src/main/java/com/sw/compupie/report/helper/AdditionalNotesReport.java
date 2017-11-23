package com.sw.compupie.report.helper;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.sw.compupie.daoBean.AdditionalNotesBean;
import com.sw.compupie.daoImpl.AdditionalNotesManipulation;

public class AdditionalNotesReport  extends BaseReport{

	
	
	public AdditionalNotesReport(PdfWriter writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}

	public void generateClientCaseHistory(int id, Document doc) throws DocumentException{
		AdditionalNotesManipulation mani = new AdditionalNotesManipulation();
		AdditionalNotesBean bean = mani.getStrength(id);
		Font font1 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD|Font.UNDERLINE);
		Font font3 = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
		Paragraph heading = new Paragraph(new Phrase("Additional Notes:", font1));
		heading.setAlignment(0);
		doc.add(heading);
		heading = new Paragraph(new Phrase(bean.getProblemStr(), font3));
		heading.setAlignment(3);
		heading.setFirstLineIndent(10);
		doc.add(heading);
		checkLengthCosmetic(doc);
		
	}
}
