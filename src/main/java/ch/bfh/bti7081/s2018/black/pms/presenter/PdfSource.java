package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Optional;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.vaadin.server.StreamResource.StreamSource;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientDrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;

public class PdfSource implements StreamSource {

    private ByteArrayOutputStream bof;
    private PatientModel patientModel;
    private java.util.List<PatientModel> patientModelList;

    public PdfSource() {
        bof = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(bof);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document reportpdf = new Document(pdfDoc);
    }
    
    public PdfSource(PatientItem patientItem) throws MalformedURLException {
		this.patientModelList = JpaServicePresenter.findAll(PatientModel.class);
		
		Optional<PatientModel> patientList = patientModelList.stream()
				.filter(patient -> patient.getId() == patientItem.getId())
				.findFirst();
    	
    	patientModel = patientList.get();
		
        bof = new ByteArrayOutputStream();
        
        PdfWriter writer = new PdfWriter(bof);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);
        
        Paragraph pReport = new Paragraph("Report");
        pReport.setFontSize(20);
        doc.add(pReport);
        
        float [] pointColumnWidths = {100F, 200F};       
        Table addresstable = new Table(pointColumnWidths);
                     
        Cell c1 = new Cell();                        
        c1.add("Firstname:");                              
        c1.setBorder(Border.NO_BORDER);                    
        addresstable.addCell(c1);
        
        Cell c2 = new Cell();                        
        c2.add(patientModel.getFirstname());                              
        c2.setBorder(Border.NO_BORDER);                    
        addresstable.addCell(c2);
        
        c1 = new Cell();                        
        c1.add("Lastname:");                              
        c1.setBorder(Border.NO_BORDER);                    
        addresstable.addCell(c1);
        
        c2 = new Cell();                        
        c2.add(patientModel.getLastname());                              
        c2.setBorder(Border.NO_BORDER);                    
        addresstable.addCell(c2);
        
        c1 = new Cell();                        
        c1.add("Adresse:");                              
        c1.setBorder(Border.NO_BORDER);                    
        addresstable.addCell(c1);
        
        c2 = new Cell();                        
        c2.add(patientModel.getStreet());                              
        c2.setBorder(Border.NO_BORDER);                    
        addresstable.addCell(c2);
        
        c1 = new Cell();                        
        c1.add("");                              
        c1.setBorder(Border.NO_BORDER);                    
        addresstable.addCell(c1);
        
        c2 = new Cell();                        
        c2.add(String.valueOf(patientModel.getPostCode()));                              
        c2.setBorder(Border.NO_BORDER);                    
        addresstable.addCell(c2);      
        doc.add(addresstable);      
        
        Paragraph pAddiction = new Paragraph("Addictions:");
        List lAddiction = new List();
        for (AddictionModel am : patientModel.getAddictions()) {
        	lAddiction.add(am.getName());
		}
        doc.add(pAddiction);
        doc.add(lAddiction);
        
        Paragraph pDrugs = new Paragraph("Drugs:");
        List lDrugs = new List();
        for (PatientDrugModel dm : patientModel.getDrugs()) {
        	lDrugs.add(dm.getDrug().getName());
		}
        doc.add(pDrugs);
        doc.add(lDrugs);
        
        Paragraph pDoctor = new Paragraph("Doctors:");
        List lDoctors = new List();
        for (DoctorModel dm : patientModel.getDoctors()) {
        	lDoctors.add(dm.getFirstname() + " " + dm.getLastname());
		}
        doc.add(pDoctor);
        doc.add(lDoctors);
        
        //String imFile = new File("").getAbsolutePath() + "\\src\\main\\webapp\\VAADIN\\themes\\mytheme\\img\\pms_64px.png";       
        //ImageData data = ImageDataFactory.create(imFile);        
        //Image image = new Image(data);                     
        //image.setFixedPosition(500, 740);                    
        //doc.add(image); 
              
        doc.close(); 
    }

    @Override
    public InputStream getStream() {
        return new ByteArrayInputStream(bof.toByteArray());
    }

}
