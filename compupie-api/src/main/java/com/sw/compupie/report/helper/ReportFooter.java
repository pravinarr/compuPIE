package com.sw.compupie.report.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class ReportFooter extends PdfPageEventHelper {
	Font font1 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
	Font font2 = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD);
	
	protected float tableHeight;

	public PdfPTable getReportFooterTable() {
		PdfPTable table;
		float[] columnWidths = { 1f, 3f, 1f };

		table = new PdfPTable(3);
		try {
			table.setWidths(columnWidths);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setTotalWidth(523);
		// table.setLockedWidth(true);
		SimpleDateFormat df = new SimpleDateFormat("EEEE, MMMM dd, yyyy");

		PdfPCell cell0 = new PdfPCell(new Paragraph(df.format(new Date()).toString(), font1));
		PdfPCell cell1 = new PdfPCell(
				new Paragraph("Produced by CompuPIE Software, Copyright (c) 2017, M. O'Keefe", font2));

		cell0.setNoWrap(true);
		;
		cell1.setNoWrap(true);
		;
		cell1.setBorder(0);
		cell0.setBorder(0);
		cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	
		table.addCell(cell0);
		table.addCell(cell1);

		tableHeight = table.getTotalHeight();
		
		return table;
	}

	public float getTableHeight() {
		return tableHeight;
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		PdfPCell cell1 = new PdfPCell(
				new Paragraph("Page "+ writer.getPageNumber(), font1));
		cell1.setNoWrap(true);
		cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1.setBorder(0);
		PdfPTable table = getReportFooterTable();
		table.addCell(cell1);
		table.writeSelectedRows(0, -1, document.left(), (document.bottom() -20 ),
				writer.getDirectContent());
		
	}
}