package com.sw.compupie.report.helper;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class ShortFormProblemHelper {
	
	public void createAProblem(String problem,String recommendation, String priority,Document doc){
		Font font1 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
		Font font2 = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
		float[] columnWidths = { 4f, 2f, 1f };
		
		
		
		PdfPTable table = new PdfPTable(3);
		try {
			table.setWidths(columnWidths);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// table.setLockedWidth(true);

		Phrase problemc = new Phrase();
		problemc.add(new Chunk(problem,font2));
		PdfPCell cell0 = new PdfPCell(problemc);
		
		problemc = new Phrase();
		problemc.add(new Chunk(recommendation,font2));
		
		PdfPCell cell1 = new PdfPCell(problemc);

		cell1.setBorder(2);
		cell1.setPaddingBottom(5);

		cell0.setPaddingBottom(5);
		cell0.setBorder(2);
		cell0.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1.setPaddingLeft(10);
		table.addCell(cell0);
		table.addCell(cell1);

		
		problemc = new Phrase();
		problemc.add(new Chunk(priority,font2));
		PdfPCell cell2 = new PdfPCell(problemc);
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setBorder(2);
		cell2.setPaddingBottom(5);
		table.addCell(cell2);
		
		PdfPTable wholeTable = new PdfPTable(1);
		wholeTable.setWidthPercentage(100);
		PdfPCell wholeCell = new PdfPCell(table);
		wholeCell.setBorder(0);
		wholeCell.setPaddingTop(8);
		wholeCell.setPaddingLeft(20);
		wholeTable.addCell(wholeCell);
		try {
			doc.add(wholeTable);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createALongProblem(String problem,String recommendation,String expOut,String goal, String priority,Document doc){
		Font font1 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
		Font font2 = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
		Font font3 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD | Font.UNDERLINE);
		float[] columnWidths = { 4f, 2f };
		float[] columnWidths1 = { 1f,2f, 1f,2f };
		float[] columnWidths2 = { 1f, 12f };
		PdfPTable table = new PdfPTable(2);
		try {
			table.setWidths(columnWidths);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// table.setLockedWidth(true);

		Phrase problemc1 = new Phrase(500);
		problemc1.add(new Chunk("Problem: \n\n",font3));
		problemc1.add(new Chunk(problem,font2));
		PdfPCell cell0 = new PdfPCell(problemc1);
		

		cell0.setPaddingBottom(5);
		cell0.setBorder(0);
		cell0.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell0);

		Phrase problemc = new Phrase();
		problemc = new Phrase();
		problemc.add(new Chunk("Priority: \n\n",font3));
		problemc.add(new Chunk(priority,font2));
		PdfPCell cell2 = new PdfPCell(problemc);
		cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2.setBorder(0);
		cell2.setPaddingBottom(5);
		table.addCell(cell2);
		
		PdfPTable table2 = new PdfPTable(2);
		try {
			table2.setWidths(columnWidths2);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// table.setLockedWidth(true);

		problemc1 = new Phrase();
		problemc1.add(new Chunk("Goal: ",font3));
		cell0 = new PdfPCell(problemc1);
		cell0.setPaddingBottom(5);
		cell0.setBorder(0);
		cell0.setIndent(10);
		cell0.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell0);
	
		
		problemc1 = new Phrase();
		problemc1.add(new Chunk(goal,font2));
		cell0 = new PdfPCell(problemc1);
		cell0.setPaddingBottom(5);
		cell0.setBorder(0);
		cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell0);
		
		PdfPTable table1 = new PdfPTable(4);
		try {
			table1.setWidths(columnWidths1);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// table.setLockedWidth(true);

		problemc1 = new Phrase();
		problemc1.add(new Chunk("Recomendation:",font3));
		cell0 = new PdfPCell(problemc1);
		cell0.setPaddingBottom(5);
		cell0.setBorder(0);
		cell0.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell0.setIndent(10);
		table1.addCell(cell0);
		
		problemc1 = new Phrase(500);
		problemc1.add(new Chunk(recommendation,font2));
		cell0 = new PdfPCell(problemc1);
		cell0.setPaddingBottom(5);
		cell0.setBorder(0);
		cell0.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table1.addCell(cell0);

		problemc1 = new Phrase(500);
		problemc1.add(new Chunk("Expected Outome:",font3));
		cell0 = new PdfPCell(problemc1);
		cell0.setPaddingBottom(5);
		cell0.setBorder(0);
		cell0.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table1.addCell(cell0);
		
		problemc1 = new Phrase(500);
		problemc1.add(new Chunk(expOut,font2));
		cell0 = new PdfPCell(problemc1);
		cell0.setPaddingBottom(5);
		cell0.setBorder(0);
		cell0.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table1.addCell(cell0);
		
	

		
		PdfPTable wholeTable = new PdfPTable(1);
		wholeTable.setWidthPercentage(100);
		PdfPCell wholeCell = new PdfPCell(table);
		wholeCell.setBorder(0);
		wholeTable.addCell(wholeCell);
		wholeCell = new PdfPCell(table2);
		wholeCell.setBorder(0);
		wholeTable.addCell(wholeCell);
		wholeCell = new PdfPCell(table1);
		wholeCell.setBorder(0);
		wholeTable.addCell(wholeCell);
		
		PdfPTable coverTable = new PdfPTable(1);
		coverTable.setWidthPercentage(100);
		PdfPCell CoverCell = new PdfPCell(wholeTable);
		CoverCell.setBorder(2);
		coverTable.addCell(CoverCell);
		try {
			doc.add(coverTable);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
