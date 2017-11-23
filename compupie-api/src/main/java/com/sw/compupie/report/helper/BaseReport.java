package com.sw.compupie.report.helper;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

public class BaseReport {
	private PdfWriter writer;

	public BaseReport(PdfWriter writer) {
		this.writer = writer;
	}

	public void checkLengthCosmetic(Document doc) {
		int availableSpace = (int) (writer.getVerticalPosition(false) - doc.bottomMargin() - 2);
		if (availableSpace < 100)
			doc.newPage();
	}

}
