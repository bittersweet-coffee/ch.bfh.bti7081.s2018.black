package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import ch.bfh.bti7081.s2018.black.pms.model.ClinicModel;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorItem;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaUtility;

public class DoctorPresenter {

	public static List<DoctorItem> getDoctorNames() {
		List<DoctorModel> docModelList = JpaServicePresenter.findAll(DoctorModel.class);
		List<DoctorItem> docItemList = new LinkedList<DoctorItem>();
		for (DoctorModel doc : docModelList) {
			DoctorItem d = new DoctorItem();
			d.setLastName(doc.getLastname());
			docItemList.add(d);
		}
		return docItemList;
	}

	public static void setupDoctor(Optional<String> doc, PatientItem patient) {
		LinkedList<DoctorModel> doctorModelListAdd = new LinkedList<DoctorModel>();
		List<DoctorModel> docModelList = JpaServicePresenter.findAll(DoctorModel.class);
		for (DoctorModel doctor : docModelList) {
				if (doc.get().equals(doctor.getLastname())) {
						doctorModelListAdd.add(doctor);
					}
				}
		patient.setDoctors(doctorModelListAdd);
	}

}
