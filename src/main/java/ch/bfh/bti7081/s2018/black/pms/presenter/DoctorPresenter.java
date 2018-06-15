package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import ch.bfh.bti7081.s2018.black.pms.model.DoctorItem;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

/**
 * Doctor presenter class
 */
public class DoctorPresenter {

	/**
	 * Getter for the doctor names
	 * @return list of doctor items
	 */
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

	/**
	 * Setup the doctor and its patient
	 * @param doc
	 * @param patient
	 */
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
