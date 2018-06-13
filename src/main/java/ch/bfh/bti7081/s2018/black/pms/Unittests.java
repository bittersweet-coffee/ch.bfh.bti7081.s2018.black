package ch.bfh.bti7081.s2018.black.pms;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentModel;
import ch.bfh.bti7081.s2018.black.pms.model.ClinicModel;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.DrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.NoticeModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientDrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.model.SymptomModel;

class Unittests {
	
	PatientModel mockPatient;
	AddictionModel mockAddiction;
	AppointmentModel mockAppointment;
	ClinicModel mockClinic;
	DoctorModel mockDoctorModel;
	DrugModel mockDrug;
	NoticeModel mockNote;
	PatientDrugModel mockPatientDrugModel;
	
	
	@BeforeEach
	void setupMockObjects() {
		LinkedList<PatientModel> mockPatientList = new LinkedList<PatientModel>();
		LinkedList<PatientDrugModel> mockPatientDrugModelList = new LinkedList<PatientDrugModel>();
		LinkedList<AppointmentModel> mockAppointmentList = new LinkedList<AppointmentModel>();
		LinkedList<AddictionModel> mockAddictionList = new LinkedList<AddictionModel>();
		LinkedList<ClinicModel> mockClinicList = new LinkedList<ClinicModel>();
		LinkedList<NoticeModel> mockNoteList = new LinkedList<NoticeModel>();
		LinkedList<DoctorModel> mockDoctorList = new LinkedList<DoctorModel>();
		mockClinicList.add(mockClinic);
		mockAppointmentList.add(mockAppointment);
		mockPatientList.add(mockPatient);
		mockPatientDrugModelList.add(mockPatientDrugModel);
		mockAddictionList.add(mockAddiction);
		mockNoteList.add(mockNote);
		mockDoctorList.add(mockDoctorModel);
		setupMockPatientDrugModel(mockDrug, mockPatient);
		setupMockAddiction(mockClinicList, mockPatientList);
		setupMockPatient(mockAddictionList, mockAppointmentList, mockDoctorList, mockPatientDrugModelList, mockNoteList);
		setupMockClinic(mockAddictionList, mockAppointmentList, mockPatientList);
		setupMockDoctor(mockAppointmentList, mockPatientList);
		setupMockDrug(mockPatientDrugModelList);
		setupMockNote(mockPatient);
		
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
			LinkedList<NoticeModel> mockNoteList) {
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

	@Test
	void test() {
		assertTrue(true);
	}

}
