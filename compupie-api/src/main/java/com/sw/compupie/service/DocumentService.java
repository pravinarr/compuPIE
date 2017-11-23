package com.sw.compupie.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.sw.compupie.report.LongReport;
import com.sw.compupie.report.ShortReport;
import com.sw.compupie.report.helper.ReportFooter;

@Path("/document")
public class DocumentService {

	@GET
	@Path("/newtopie")
	@Produces("application/pdf")
	public Response getNewToPIE() {
		URL resource = Thread.currentThread().getContextClassLoader().getResource("newUsers.pdf");
		File f = new File(resource.getPath());
		return Response.ok(f, "application/pdf").build();
	}

	@GET
	@Path("/instructions")
	@Produces("application/pdf")
	public Response getInstruction() {
		URL resource = Thread.currentThread().getContextClassLoader().getResource("instructions.pdf");
		File f = new File(resource.getPath());
		return Response.ok(f, "application/pdf").build();
	}

	@GET
	@Path("/dedication")
	@Produces("application/pdf")
	public Response getDedication() {
		URL resource = Thread.currentThread().getContextClassLoader().getResource("dedication.pdf");
		File f = new File(resource.getPath());
		return Response.ok(f, "application/pdf").build();
	}

	@GET
	@Path("/about")
	@Produces("application/pdf")
	public Response getAboutAuthor() {
		URL resource = Thread.currentThread().getContextClassLoader().getResource("aboutAuthor.pdf");
		File f = new File(resource.getPath());
		return Response.ok(f, "application/pdf").build();
	}

	@GET
	@Path("/problem")
	@Produces("application/pdf")
	public Response getProblemDesc() {
		URL resource = Thread.currentThread().getContextClassLoader().getResource("problem.pdf");
		File f = new File(resource.getPath());
		return Response.ok(f, "application/pdf").build();
	}

	@GET
	@Path("/report")
	@Produces("application/pdf")
	public Response getReport(@QueryParam("type") String reportType, @QueryParam("clientId") String clientId,
			@QueryParam("followUp") String followUp) {
		StreamingOutput stream = new StreamingOutput() {
			@Override
			public void write(OutputStream os) throws IOException, WebApplicationException {
				Document document = new Document();
				PdfWriter writer = null;
				try {
					writer = PdfWriter.getInstance(document, os);
					ReportFooter event = new ReportFooter();
					writer.setPageEvent(event);
				} catch (DocumentException e) {
					e.printStackTrace();
				}
				int cli = Integer.parseInt(clientId);
				List<Integer> fol = new ArrayList<Integer>();
				for (String s : followUp.split(",")) {
					if (!s.trim().equalsIgnoreCase("")) {
						fol.add(Integer.parseInt(s));
					}
				}
				if (reportType.equalsIgnoreCase("long")) {
					LongReport report = new LongReport();
					report.createlongAssessment(cli, fol, writer, document);
				} else {
					ShortReport report = new ShortReport();
					report.createShortAssessment(cli, fol, writer, document);
				}
				
				//writer.
				writer.flush();
			}
		};

		return Response.ok(stream, "application/pdf").build();
	}
	
	 public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
	        PdfReader reader = new PdfReader(src);
	        int n = reader.getNumberOfPages();
	        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
	        PdfContentByte pagecontent;
	        for (int i = 0; i < n; ) {
	            pagecontent = stamper.getOverContent(++i);
	            ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT,
	                    new Phrase(String.format("page %s of %s", i, n)), 559, 806, 0);
	        }
	        stamper.close();
	        reader.close();
	    }

}
