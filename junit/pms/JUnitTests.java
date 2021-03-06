package pms;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.Appointment;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentItem;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentModel;
import ch.bfh.bti7081.s2018.black.pms.model.ClinicModel;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorItem;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.DrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.NoticeModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientDrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.model.SymptomModel;
import ch.bfh.bti7081.s2018.black.pms.presenter.AgendaPresenter;
import ch.bfh.bti7081.s2018.black.pms.presenter.JpaServicePresenter;
import ch.bfh.bti7081.s2018.black.pms.presenter.PatientPresenter;

class JUnitTests {
	
	
	//Mocked Object
	PatientModel mockPatient;
	AddictionModel mockAddiction;
	AppointmentModel mockAppointment;
	ClinicModel mockClinic;
	DoctorModel mockDoctorModel;
	DrugModel mockDrug;
	NoticeModel mockNote;
	PatientDrugModel mockPatientDrugModel;
	

	//Needed mocked Lists
	LinkedList<PatientModel> mockPatientList;
	LinkedList<PatientDrugModel> mockPatientDrugModelList;
	LinkedList<AppointmentModel> mockAppointmentList;
	LinkedList<AddictionModel> mockAddictionList;
	LinkedList<ClinicModel> mockClinicList;
	List<NoticeModel> mockNoteList;
	LinkedList<DoctorModel> mockDoctorList;
	
	
	@BeforeEach
	void setupMockObjects() {
		mockPatientList = new LinkedList<PatientModel>();
		mockPatientDrugModelList = new LinkedList<PatientDrugModel>();
		mockAppointmentList = new LinkedList<AppointmentModel>();
		mockAddictionList = new LinkedList<AddictionModel>();
		mockClinicList = new LinkedList<ClinicModel>();
		mockNoteList = new LinkedList<NoticeModel>();
		mockDoctorList = new LinkedList<DoctorModel>();
		mockClinicList.add(mockClinic);
		mockAppointmentList.add(mockAppointment);
		mockPatientList.add(mockPatient);
		mockPatientDrugModelList.add(mockPatientDrugModel);
		mockAddictionList.add(mockAddiction);
		mockDoctorList.add(mockDoctorModel);
		setupMockPatientDrugModel(mockDrug, mockPatient);
		setupMockAddiction(mockClinicList, mockPatientList);
		setupMockClinic(mockAddictionList, mockAppointmentList, mockPatientList);
		setupMockDoctor(mockAppointmentList, mockPatientList);
		setupMockDrug(mockPatientDrugModelList);
		setupMockNote(mockPatient);
	}

	/**
	 * Checks Patient Presenter on save a new patient method.
	 */
	@Test
	void checkPatientPresenterStore() {
		int nbrBefore = JpaServicePresenter.findAll(PatientModel.class).size();
		PatientPresenter pp = new PatientPresenter();
		PatientItem mockPatientItem = new PatientItem();
		JpaServicePresenter.store(mockClinic);
		setupMockPatient(mockAddictionList, mockAppointmentList, mockDoctorList, mockPatientDrugModelList, mockNoteList);
		setupMockNote(mockPatient);
		mockNoteList.add(mockNote);
		mockPatient.setNotes(mockNoteList);
		mockPatientItem.setAddictions(mockPatient.getAddictions());
		mockPatientItem.setAppointments(mockPatient.getAppointments());
		mockPatientItem.setBirthday(mockPatient.getBirthday());
		mockPatientItem.setClinic(mockClinic);
		mockPatientItem.setDoctors(mockPatient.getDoctors());
		mockPatientItem.setDrugs(mockPatient.getDrugs());
		mockPatientItem.setFirstName(mockPatient.getFirstname());
		mockPatientItem.setLastName(mockPatient.getLastname());
		mockPatientItem.setPostcode(mockPatient.getPostCode());
		mockPatientItem.setStreet(mockPatient.getStreet());
		mockPatientItem.setTelephone(mockPatient.getTelephone());
		pp.saveButtonClicked(mockPatientItem, mockPatient.getNotes().get(0).getNote());
		int nbrAfter = JpaServicePresenter.findAll(PatientModel.class).size();
		assertEquals(nbrBefore+1, nbrAfter);
	}
	/**
	 * Database Check
	 */
	@Test
	void checkJpaServicePresenterStore() {
		assertNotNull(JpaServicePresenter.findAll(PatientModel.class));
	}
	
	/**
	 * Database Check
	 */
	@Test 
	void checkObjectStoreMethod_addNewPatient() {
		int nbrBefore = JpaServicePresenter.findAll(PatientModel.class).size();
		storePatient();
		int nbrAfter = JpaServicePresenter.findAll(PatientModel.class).size();
		assertEquals(nbrBefore+1, nbrAfter);
	}
	
	/**
	 * Database Check
	 */
	@Test
	void checkJpaServicePresenterRemove() {
		int nbrBefore = JpaServicePresenter.findAll(PatientModel.class).size();
		storePatient();
		JpaServicePresenter.remove(mockPatient);
		int nbrAfter = JpaServicePresenter.findAll(PatientModel.class).size();
		assertEquals(nbrBefore, nbrAfter);
	}
	
	/**
	 * Database Check
	 */
	@Test
	void checkObjectStoreMethod_updatePatient() {
		String newFirstName = "newMockFirstName";
		Boolean assertFlag = false;
		storePatient();
		for (PatientModel patient : JpaServicePresenter.findAll(PatientModel.class)) {
			if (patient.getFirstname().equals("mockFirstName")) {
				mockPatient.setFirstname(newFirstName);
			}
		}
		JpaServicePresenter.update(mockPatient);
		for (PatientModel patient : JpaServicePresenter.findAll(PatientModel.class)) {
			if (patient.getFirstname().equals(newFirstName)) {
				assertFlag = true;
			}
		}
		assertTrue(assertFlag);
		
		
	}
	
	@Test
	void checkAgendaPresenterSaveAppintment() {
		int nbrBefore = JpaServicePresenter.findAll(AppointmentModel.class).size();
		AgendaPresenter ap = new AgendaPresenter();
		Appointment appointment = new Appointment();
		appointment.setDescription("mockDescription");
		DoctorItem docItem = new DoctorItem(mockDoctorModel);
		appointment.setDoctorItem(docItem);
		appointment.setEnd(LocalDateTime.now());
		appointment.setStart(LocalDateTime.now());
		appointment.setTitle("mockTitle");
		AppointmentItem appointmentItem = new AppointmentItem(appointment);
		ap.saveAppointment(appointmentItem);
		int nbrAfter = JpaServicePresenter.findAll(AppointmentModel.class).size();
		assertEquals(nbrBefore+1, nbrAfter);
	}
		
	/**
	 * Stores a mocked patient in the database
	 * @param objects
	 */
	private void storePatient() {
		setupMockPatient(mockAddictionList, mockAppointmentList, mockDoctorList, mockPatientDrugModelList, mockNoteList);
		setupPatientDependencies(mockPatient.getClinic());
		JpaServicePresenter.store(mockPatient);
	}

	/**
	 * Helpermethod because patient store needs a clinic store first. 
	 * @param objects
	 * @param clinic
	 */
	private void setupPatientDependencies(ClinicModel clinic) {
		JpaServicePresenter.store(clinic);
	}
	
	private void setupMockPatientDrugModel(DrugModel mockDrug, PatientModel mockPatient) {
		mockPatientDrugModel = new PatientDrugModel();
		mockPatientDrugModel.setDose(1.0);
		mockPatientDrugModel.setDrug(mockDrug);
		mockPatientDrugModel.setPatient(mockPatient);
	}

	private void setupMockNote(PatientModel mockPatien) {
		mockNote = new NoticeModel();
		mockNote.setNote("mockNote");
		mockNote.setPatient(mockPatien);
	}

	private void setupMockDrug(LinkedList<PatientDrugModel> mockPatientDrugModelList) {
		mockDrug = new DrugModel();
		mockDrug.setDescription("mockDrugDescription");
		mockDrug.setName("mockDrug");
		mockDrug.setPatients(mockPatientDrugModelList);
	}

	private void setupMockDoctor(LinkedList<AppointmentModel> mockAppointmentList, LinkedList<PatientModel> mockPatientList) {
		mockDoctorModel = new DoctorModel();
		mockDoctorModel.setAppointments(mockAppointmentList);
		mockDoctorModel.setFirstname("mockFirstName");
		mockDoctorModel.setLastname("mockLastName");
		mockDoctorModel.setPasswordHash("mockHash");
		mockDoctorModel.setPatients(mockPatientList);
		mockDoctorModel.setUsername("mockUsetName");
	}

	private void setupMockClinic(LinkedList<AddictionModel> mockAddictionList, LinkedList<AppointmentModel> mockAppointmentList, LinkedList<PatientModel> mockPatientList) {
		mockClinic = new ClinicModel();
		mockClinic.setAddictions(mockAddictionList);
		mockClinic.setAppointments(mockAppointmentList);
		mockClinic.setEmail("mockEmail");
		mockClinic.setName("mockClinicName");
		mockClinic.setPatients(mockPatientList);
		mockClinic.setPlace("mockPlace");
		mockClinic.setPostCode(1234);
		mockClinic.setStreet("mockStreet");
		mockClinic.setTelephone("mockPhone");
		
	}

	private void setupMockAddiction(LinkedList<ClinicModel> mockClinicList, LinkedList<PatientModel> mockPatientList) {
		mockAddiction = new AddictionModel();
		SymptomModel mockSymptom = new SymptomModel();
		mockSymptom.setAddiction(mockAddiction);
		mockSymptom.setDescription("mockDescription");
		LinkedList<SymptomModel> mockSymptomList = new LinkedList<SymptomModel>();getClass();
		mockAddiction.setClinics(mockClinicList);
		mockAddiction.setDescription("mockDescription");
		mockAddiction.setName("mockAddiction");
		mockAddiction.setPatients(mockPatientList);
		mockAddiction.setSymptoms(mockSymptomList);
	}

	private void setupMockPatient(LinkedList<AddictionModel> mockAddictionList, 
			LinkedList<AppointmentModel> mockAppointmentList, 
			LinkedList<DoctorModel> mockDoctorList, 
			LinkedList<PatientDrugModel> mockPatientDrugModelList, 
			List<NoticeModel> mockNoteList) {
		mockPatient = new PatientModel();
		mockPatient.setAddictions(mockAddictionList);
		mockPatient.setAppointments(mockAppointmentList);
		mockPatient.setBirthday(LocalDate.MAX);
		mockPatient.setClinic(mockClinic);
		mockPatient.setDoctors(mockDoctorList);
		mockPatient.setDrugs(mockPatientDrugModelList);
		mockPatient.setFirstname("mockFirstName");
		mockPatient.setLastname("mockLastName");
		mockPatient.setNotes(mockNoteList);
		mockPatient.setPostCode(1234);
		mockPatient.setStreet("mockStreet");
		mockPatient.setTelephone("mockPhone");
		
	}
	
}
