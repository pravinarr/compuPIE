package com.sw.compupie.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sw.compupie.daoBean.ClientBean;
import com.sw.compupie.daoBean.FollowUpBean;
import com.sw.compupie.daoImpl.ClientTableManipulation;
import com.sw.compupie.daoImpl.FollowUpTableManipulation;
import com.sw.compupie.report.helper.AdditionalNotesReport;
import com.sw.compupie.report.helper.CHDCReport;
import com.sw.compupie.report.helper.ClientInfoReport;
import com.sw.compupie.report.helper.FactorLongReport;
import com.sw.compupie.report.helper.MSEReport;
import com.sw.compupie.report.helper.ReportFooter;
import com.sw.compupie.report.helper.StrengthResourcesReport;
import com.sw.compupie.report.helper.SubstancerReport;
import com.sw.compupie.report.helper.utils.DateUtil;

public class LongReport {
	static Document document = new Document();

	public void addClientInformation(ClientBean bean) throws DocumentException {
		PdfPTable table = new PdfPTable(3); // 3 columns.

		PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
		PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
		PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
		table.setKeepTogether(true)
		;
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);

		document.add(table);

	}

	public void addHeading() throws DocumentException {
		Font font1 = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
		Paragraph heading = new Paragraph(new Phrase("CompuPIE - PIE Assessment Summary", font1));
		heading.setAlignment(1);
		document.add(heading);
		// document.n
	}

	public void addAssessmentHeading() throws DocumentException {
		Font font1 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
		Paragraph heading = new Paragraph(new Phrase("", font1));
		heading.setSpacingAfter(5);
		heading.setAlignment(1);
		document.add(heading);
	}

	public void createlongAssessment(int clientID, List<Integer> followUpIds, PdfWriter writer, Document doc) {
		document = doc;

		ClientInfoReport cReport;
		StrengthResourcesReport rep;
		CHDCReport chdc;
		try {
			ClientTableManipulation cli = new ClientTableManipulation();
			ClientBean bean = cli.getClientInfo("" + clientID);
			cReport = new ClientInfoReport(writer);
			rep = new StrengthResourcesReport(writer);
			chdc = new CHDCReport(writer);
			document.setPageSize(PageSize.A4);
			document.setMargins(30, 45, 50, 60);
			document.setMarginMirroring(false);
			
			document.open();
			document.newPage();
			addHeading();
			addAssessmentHeading();
			cReport.generateLongClientInfo(clientID, document);
			// chdc.generateClientCaseHistory(1, document);
			int availableSpace = (int) (writer.getVerticalPosition(false) - document.bottomMargin() - 2);
			if (availableSpace < 100)
				document.newPage();
			FactorLongReport flreport = new FactorLongReport(writer);
			chdc.generateClientCaseHistory(clientID, document);
			SubstancerReport sReport = new SubstancerReport(writer);
			sReport.generateSubstanceReport(clientID, document);


			MSEReport mse = new MSEReport(writer);
			mse.createMSEReport(clientID, document);
			
			FollowUpTableManipulation foll = new FollowUpTableManipulation();
			for (int i : followUpIds) {
				Font fontx = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE);
				FollowUpBean bn = foll.getFollowUpInfo(i, clientID).get(0);
				
				if (bn.getStage() == 1) {
					Paragraph heading = new Paragraph(new Phrase(" Assessed " + " on: " + DateUtil.changeDateToAmerican(bn.getDate()), fontx));
					heading.setAlignment(0);
					document.add(heading);
					Font fontxy = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE);
					Paragraph heading1 = new Paragraph(new Phrase(bn.getNotes(), fontxy));
					heading1.setAlignment(0);
					doc.add(heading1);
				}else if (bn.getStage() == 3) {
					Paragraph heading = new Paragraph(new Phrase(" Closed case " + " on: " + DateUtil.changeDateToAmerican(bn.getDate()), fontx));
					heading.setAlignment(0);
					document.add(heading);
					Font fontxy = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE);
					Paragraph heading1 = new Paragraph(new Phrase(bn.getNotes(), fontxy));
					heading1.setAlignment(0);
					doc.add(heading1);
				} else {
					Paragraph heading = new Paragraph(new Phrase(" Assessed " + " on: " + DateUtil.changeDateToAmerican(bn.getDate()), fontx));
					heading.setAlignment(0);
					document.add(heading);
					flreport.setFactorInfo(clientID, i, document);
					flreport.createShortForFactor1(document);
					flreport.createShortForFactor2(document);
					flreport.createShortForFactor3(document);
					flreport.createShortForFactor4(document);
				}
			}

			rep.generateStrengthAndResources(clientID, document);

			AdditionalNotesReport addreport = new AdditionalNotesReport(writer);
			addreport.generateClientCaseHistory(clientID, document);
			
			
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		LongReport l = new LongReport();
		Document d = new Document();
		List<Integer> fol = new ArrayList<Integer>();
		fol.add(1);
		PdfWriter w = PdfWriter.getInstance(d, new FileOutputStream("LongReport1.pdf"));
		l.createlongAssessment(13, fol, w, d);
		w.flush();
		d.close();
	}
}
