package com.sw.compupie.report.helper;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class NewLineChecker extends PdfPageEventHelper {
	Font font1 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
	protected PdfPTable table;
	protected float tableHeight;

	public NewLineChecker() {
	
	}

	public float getTableHeight() {
		return tableHeight;
	}

	
	public void onEndPage(PdfWriter writer, Document document) {
		
	}
}
