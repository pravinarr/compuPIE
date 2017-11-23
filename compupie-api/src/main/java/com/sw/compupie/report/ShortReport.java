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
import com.itextpdf.text.pdf.PdfWriter;
import com.sw.compupie.daoBean.ClientBean;
import com.sw.compupie.daoBean.FollowUpBean;
import com.sw.compupie.daoImpl.ClientTableManipulation;
import com.sw.compupie.daoImpl.FollowUpTableManipulation;
import com.sw.compupie.report.helper.CHDCReport;
import com.sw.compupie.report.helper.ClientInfoReport;
import com.sw.compupie.report.helper.FactorShortReport;
import com.sw.compupie.report.helper.ReportFooter;
import com.sw.compupie.report.helper.StrengthResourcesReport;
import com.sw.compupie.report.helper.SubstancerReport;
import com.sw.compupie.report.helper.utils.DateUtil;

public class ShortReport {
	Document document = new Document();

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

	public void createShortAssessment(int clientID, List<Integer> followUpIds, PdfWriter writer, Document doc) {
		document = doc;
		ReportFooter event = new ReportFooter();
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
			writer.setPageEvent(event);
			document.open();
			// addClientInformation(bean);;
			document.newPage();
			addHeading();
			addAssessmentHeading();
			cReport.generateShortClientInfo(clientID, document);
			chdc.generateClientCaseHistory(clientID, document);
			SubstancerReport sReport = new SubstancerReport(writer);
			sReport.generateSubstanceReport(clientID, document);

			int availableSpace = (int) (writer.getVerticalPosition(false) - document.bottomMargin() - 2);
			if (availableSpace < 100)
				document.newPage();
			FactorShortReport fReport = new FactorShortReport(writer);
			FollowUpTableManipulation foll = new FollowUpTableManipulation();
			for (int i : followUpIds) {
				Font fontx = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE);
				FollowUpBean bn = foll.getFollowUpInfo(i, clientID).get(0);
				if (bn.getStage() == 1 ) {
					Paragraph heading = new Paragraph(new Phrase(" Assessed " + " on: " + DateUtil.changeDateToAmerican(bn.getDate()), fontx));
					heading.setAlignment(0);
					document.add(heading);
					Font fontxy = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE);
					Paragraph heading1 = new Paragraph(new Phrase(bn.getNotes(), fontxy));
					heading1.setAlignment(0);
					doc.add(heading1);
				}else if (bn.getStage() == 3 ) {
					Paragraph heading = new Paragraph(new Phrase(" Closed Case " + " on: " + DateUtil.changeDateToAmerican(bn.getDate()), fontx));
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
					fReport.setFactorInfo(clientID, i, document);
					fReport.createShortForFactor1(document);
					fReport.createShortForFactor2(document);
					fReport.createShortForFactor3(document);
					fReport.createShortForFactor4(document);
				}
			}

			rep.generateStrengthAndResources(clientID, document);
			
			
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		ShortReport l = new ShortReport();
		Document d = new Document();
		List<Integer> fol = new ArrayList<Integer>();
		fol.add(1);
		PdfWriter w = PdfWriter.getInstance(d, new FileOutputStream("LongReport1.pdf"));
		l.createShortAssessment(13, fol, w, d);
		w.flush();
		d.close();
	}
}
