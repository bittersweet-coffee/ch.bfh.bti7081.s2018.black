package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentModel;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientDrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;

/**
 * PdfSource Class
 * Creates a patient report
 * @author schaa4
 *
 */
public class PdfSource implements StreamSource {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private ByteArrayOutputStream bof;
    private PatientModel patientModel;
    private final String RELATIVE_PATH_IMAGE = "/src/main/webapp/VAADIN/themes/mytheme/img/pms_64px.png";

    public PdfSource() {
        bof = new ByteArrayOutputStream();
    }
    
    /**
	 * Constructor for the PdfSource
	 * @param patientItem The mock object of the Patient (PatientItem)
	 */
    public PdfSource(PatientItem patientItem) throws MalformedURLException {
    	patientModel = JpaServicePresenter.findAll(PatientModel.class).stream().filter(p -> p.getId() == patientItem.getId()).findFirst().get();
    	ArrayList<AppointmentModel> appointmentModel;
		
    	if(patientItem.getModel().getAppointments() != null) {
			appointmentModel = new ArrayList<>(patientItem.getModel().getAppointments());
		} else {
			appointmentModel = new ArrayList<>();
		}
    	
        bof = new ByteArrayOutputStream();
        
        PdfWriter writer = new PdfWriter(bof);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);
        
        Paragraph pReport = new Paragraph("Report");
        pReport.setFontSize(20);
        doc.add(pReport);
        
        float [] pointColumnWidths = {100F, 200F};       
        Table addresstable = new Table(pointColumnWidths);
        
        // define and add addresstable to document
        addCell(addresstable, "Firstname:", true);
        addCell(addresstable, patientModel.getFirstname(), false);
        addCell(addresstable, "Lastname:", true);
        addCell(addresstable, patientModel.getLastname(), false);
        addCell(addresstable, "Address:", true);
        addCell(addresstable, patientModel.getStreet(), false);
        addCell(addresstable, "Postal Code:", true);
        addCell(addresstable, String.valueOf(patientModel.getPostCode()), false);   
        doc.add(addresstable);
        
        // add all addiction of the patient to the document
        Paragraph pAddiction = new Paragraph("Addictions:");
        pAddiction.setBold();
        List lAddiction = new List();
        for (AddictionModel am : patientModel.getAddictions()) {
        	lAddiction.add(am.getName());
		}
        doc.add(pAddiction);
        doc.add(lAddiction);
        
        // add all drugs of the patient to the document
        Paragraph pDrugs = new Paragraph("Drugs:");
        pDrugs.setBold();
        List lDrugs = new List();
       
        if(patientModel.getDrugs() != null) {
        	for (PatientDrugModel dm : patientModel.getDrugs()) {
                lDrugs.add(dm.getDrug().getName() + ", Dose: " + dm.getDose() + dm.getDrug().getUnit());
    		}
        }
        
        doc.add(pDrugs);
        doc.add(lDrugs);
        
        // add the doctor of the patient to the document
        Paragraph pDoctor = new Paragraph("Doctors:");
        pDoctor.setBold();
        List lDoctors = new List();
        for (DoctorModel dm : patientModel.getDoctors()) {
        	lDoctors.add(dm.getFirstname() + " " + dm.getLastname());
		}
        doc.add(pDoctor);
        doc.add(lDoctors);
        
        // add all appointments of the patient to the document
        Paragraph pAppointment = new Paragraph("Appointments:");
        pAppointment.setBold();
        float [] pcWidthsAppointment = {100F, 200F, 100F, 100F, 100F};       
        Table appoointmentTable = new Table(pcWidthsAppointment);
        addCell(appoointmentTable, "Name", true);
        addCell(appoointmentTable, "Description", true);
        addCell(appoointmentTable, "Start", true);
        addCell(appoointmentTable, "End", true);
        addCell(appoointmentTable, "Doctor", true);
                       
        for (AppointmentModel am : appointmentModel) {
        	addCell(appoointmentTable, am.getName(), false);
            addCell(appoointmentTable, am.getDescription(), false);
            addCell(appoointmentTable, am.getStart().format(formatter), false);
            addCell(appoointmentTable, am.getEnd().format(formatter), false);
            if(am.getDoctor() != null) {
            	addCell(appoointmentTable, am.getDoctor().getFirstname() + " " + am.getDoctor().getLastname(), false);
            }else {
            	addCell(appoointmentTable, "", false);
            }
		}
        doc.add(pAppointment);
        doc.add(appoointmentTable);
        
        // define and add image to document
        String imFile = new File("").getAbsolutePath() + RELATIVE_PATH_IMAGE;       
        ImageData data = ImageDataFactory.create(imFile);        
        Image image = new Image(data); 
        image.setWidth(100);
        image.setHeight(100);
        image.setFixedPosition(475, 730);                    
        doc.add(image); 
        
        doc.close(); 
    }
    
    /**
     * method to fill the table with cells
     * @param table: table that has to be filled
     * @param text: text that gets in the cell
     * @param bolt: boolean value for bolt or not bolt
     */
    private void addCell(Table table, String text, boolean bolt) {
    	Cell cAp1 = new Cell();                        
        cAp1.add(text);                              
        cAp1.setBorder(Border.NO_BORDER);
        if(bolt) {
        	cAp1.setBold();
        }
        table.addCell(cAp1);
    }

    /**
     * Create new ByteArrayInputStream for Buffer
     */
    @Override
    public InputStream getStream() {
        return new ByteArrayInputStream(bof.toByteArray());
    }

}
