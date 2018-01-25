package com.sw.compupie.report.helper;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sw.compupie.daoBean.ClientBean;
import com.sw.compupie.daoBean.FollowUpBean;
import com.sw.compupie.daoImpl.ClientTableManipulation;
import com.sw.compupie.daoImpl.FollowUpTableManipulation;
import com.sw.compupie.report.helper.utils.DateUtil;

public class ClientInfoReport extends BaseReport {

	public ClientInfoReport(PdfWriter writer) {
		super(writer);
	}

	public Document generateShortClientInfo(int clientID, Document doc) throws DocumentException {
		ClientTableManipulation mani = new ClientTableManipulation();
		FollowUpTableManipulation follow = new FollowUpTableManipulation();
		FollowUpBean bean = follow.getFollowUpInfo(1, clientID).get(0);
		ClientBean info = mani.getClientInfo("" + clientID);
		Font font1 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
		Font font2 = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
		float[] columnWidths = { 1f, 3f, 1f };

		PdfPTable table = new PdfPTable(3);
		try {
			table.setWidths(columnWidths);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// table.setLockedWidth(true);

		Phrase client = new Phrase();
		client.add(new Chunk("Client: ", font1));
		client.add(new Chunk("" + info.getLastname() + ", " + info.getFirstname(), font2));
		PdfPCell cell0 = new PdfPCell(client);

		client = new Phrase();
		client.add(new Chunk("Assessed By: ", font1));
		client.add(new Chunk("" + bean.getAccessedBy(), font2));

		PdfPCell cell1 = new PdfPCell(client);

		cell0.setNoWrap(true);
		cell1.setNoWrap(true);
		cell1.setBorder(0);

		cell0.setBorder(0);
		cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(cell0);
		table.addCell(cell1);

		client = new Phrase();
		client.add(new Chunk("Assessed on: ", font1));
		client.add(new Chunk("" + DateUtil.changeDateToAmerican(bean.getDate()), font2));
		PdfPCell cell2 = new PdfPCell(client);
		cell2.setNoWrap(true);
		cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2.setBorder(0);
		table.addCell(cell2);

		PdfPTable wholeTable = new PdfPTable(1);
		wholeTable.setWidthPercentage(100);
		PdfPCell wholeCell = new PdfPCell(table);
		wholeCell.setPaddingBottom(5);
		wholeCell.setPaddingTop(5);
		wholeTable.addCell(wholeCell);
		doc.add(wholeTable);
		return doc;
	}

	public Document generateLongClientInfo(int clientID, Document doc) throws DocumentException {
		ClientTableManipulation mani = new ClientTableManipulation();
		FollowUpTableManipulation follow = new FollowUpTableManipulation();
		FollowUpBean bean = follow.getFollowUpInfo(1, clientID).get(0);
		ClientBean info = mani.getClientInfo("" + clientID);
		Font font1 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
		Font font2 = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
		float[] columnWidths = { 1f, 1f, 1f, 1f };

		PdfPTable table1 = new PdfPTable(4);
		try {
			table1.setWidths(columnWidths);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// table.setLockedWidth(true);

		Phrase client = new Phrase();
		client.add(new Chunk("Client: ", font1));
		client.add(new Chunk("" + info.getLastname() + ", " + info.getFirstname(), font2));
		PdfPCell cell0 = new PdfPCell(client);

		client = new Phrase();
		client.add(new Chunk("Client ID# ", font1));
		client.add(new Chunk("" + info.getClientId(), font2));

		PdfPCell cell1 = new PdfPCell(client);

		cell1.setBorder(0);

		cell0.setBorder(0);
		cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

		client = new Phrase();
		client.add(new Chunk("Assessed By: ", font1));
		client.add(new Chunk("" + bean.getAccessedBy(), font2));
		PdfPCell cell2 = new PdfPCell(client);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setBorder(0);

		client = new Phrase();
		client.add(new Chunk("Assessed on: ", font1));
		client.add(new Chunk("" + DateUtil.changeDateToAmerican(bean.getDate()), font2));
		PdfPCell cell3 = new PdfPCell(client);
		cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell3.setBorder(0);

		table1.addCell(cell0);
		table1.addCell(cell1);
		table1.addCell(cell2);
		table1.addCell(cell3);

		float[] columnWidths1 = { 1f, 1f,1f, 1f };
		PdfPTable table = new PdfPTable(4);
		try {
			table.setWidths(columnWidths1);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// table.setLockedWidth(true);

		client = new Phrase();
		client.add(new Chunk("Address: ", font1));
		client.add(new Chunk(
				("" + info.getStreet() + " " + info.getCity() + " " + info.getStateName() + " " + info.getZipcode()),
				font2));
		cell0 = new PdfPCell(client);

		client = new Phrase();
		client.add(new Chunk("Phone: ", font1));
		client.add(new Chunk(info.getPhone(), font2));

		cell1 = new PdfPCell(client);

		cell1.setBorder(0);

		cell0.setBorder(0);
		cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

		table.addCell(cell0);
		table.addCell(cell1);

		client = new Phrase();
		client.add(new Chunk("Referred By: ", font1));
		client.add(new Chunk("" + info.getReferredBy(), font2));
		cell2 = new PdfPCell(client);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setBorder(0);

		client = new Phrase();
		client.add(new Chunk("Ethinicity: ", font1));
		client.add(new Chunk("" + info.getEthnicity(), font2));
		cell3 = new PdfPCell(client);
		cell3.setNoWrap(true);
		cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell3.setBorder(0);
		table.addCell(cell2);
		table.addCell(cell3);

		PdfPTable table2 = new PdfPTable(4);
		try {
			table2.setWidths(columnWidths);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// table.setLockedWidth(true);

		client = new Phrase();
		client.add(new Chunk("Gender: ", font1));
		client.add(new Chunk("" + info.getGender(), font2));
		cell0 = new PdfPCell(client);

		client = new Phrase();
		client.add(new Chunk("Date Of Birth: ", font1));
		client.add(new Chunk("" + DateUtil.changeDateToAmerican(info.getDob().substring(0, 10)), font2));

		cell1 = new PdfPCell(client);

		cell1.setBorder(0);

		cell0.setBorder(0);
		cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

		client = new Phrase();
		client.add(new Chunk("Marital Status: ", font1));
		client.add(new Chunk("" + info.getMaritalStatus(), font2));
		cell2 = new PdfPCell(client);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setBorder(0);

		client = new Phrase();
		client.add(new Chunk("# of Children: ", font1));
		client.add(new Chunk("" + info.getNoOfChildrenInCare(), font2));
		cell3 = new PdfPCell(client);
		cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell3.setBorder(0);

		table2.addCell(cell0);
		table2.addCell(cell1);
		table2.addCell(cell2);
		table2.addCell(cell3);

		PdfPTable table3 = new PdfPTable(3);
		float[] columnWidths2 = {  1f,1f, 1f };
		try {
			table3.setWidths(columnWidths2);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// table.setLockedWidth(true);

		client = new Phrase();
		client.add(new Chunk("Highes Level of Education: ", font1));
		client.add(new Chunk("" + info.getHighestLevelOfEducation(), font2));

		cell1 = new PdfPCell(client);

		cell1.setBorder(0);

		cell0.setBorder(0);
		cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

		client = new Phrase();
		client.add(new Chunk("Employment Status: ", font1));
		client.add(new Chunk("" + info.getEmploymentStatus(), font2));
		cell2 = new PdfPCell(client);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setBorder(0);

		client = new Phrase();
		client.add(new Chunk("Living Arrangement: ", font1));
		client.add(new Chunk("" + info.getLivingArrangement(), font2));
		cell3 = new PdfPCell(client);
		cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell3.setBorder(0);

		table3.addCell(cell1);
		table3.addCell(cell2);
		table3.addCell(cell3);

		PdfPTable table4 = new PdfPTable(1);

		client = new Phrase();
		client.add(new Chunk("Additional Notes: ", font1));
		client.add(new Chunk("" + info.getAdditional(), font2));
		cell0 = new PdfPCell(client);

		cell0.setBorder(0);
		cell0.setHorizontalAlignment(Element.ALIGN_LEFT);

		table4.addCell(cell0);

		PdfPTable wholeTable = new PdfPTable(1);
		wholeTable.setWidthPercentage(100);
		PdfPCell wholeCell = new PdfPCell(table1);
		PdfPCell wholeCell1 = new PdfPCell(table);
		PdfPCell wholeCell3 = new PdfPCell(table2);
		PdfPCell wholeCell4 = new PdfPCell(table3);
		PdfPCell wholeCell5 = new PdfPCell(table4);
		wholeCell.setPaddingBottom(5);
		wholeCell.setPaddingTop(5);
		wholeCell1.setPaddingBottom(5);
		wholeCell1.setPaddingTop(5);
		wholeCell3.setPaddingBottom(5);
		wholeCell3.setPaddingTop(5);
		wholeCell4.setPaddingBottom(5);
		wholeCell4.setPaddingTop(5);
		wholeCell5.setPaddingBottom(5);
		wholeCell5.setPaddingTop(5);

		wholeCell.setBorder(0);
		wholeCell1.setBorder(0);
		wholeCell4.setBorder(0);
		wholeCell3.setBorder(0);
		wholeCell5.setBorder(0);

		wholeTable.addCell(wholeCell);
		wholeTable.addCell(wholeCell1);
		wholeTable.addCell(wholeCell3);
		wholeTable.addCell(wholeCell4);
		wholeTable.addCell(wholeCell5);

		PdfPTable covertable = new PdfPTable(1);
		covertable.setWidthPercentage(100);
		PdfPCell coverCell = new PdfPCell(wholeTable);
		coverCell.setPaddingBottom(5);
		coverCell.setPaddingTop(5);
		covertable.addCell(coverCell);

		doc.add(covertable);
		return doc;
	}

}
