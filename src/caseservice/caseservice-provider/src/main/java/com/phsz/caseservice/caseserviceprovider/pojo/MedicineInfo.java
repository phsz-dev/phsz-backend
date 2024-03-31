package com.phsz.caseservice.caseserviceprovider.pojo;

import com.phsz.medicineservice.medicineserviceapi.pojo.Medicine;
import lombok.Data;

@Data

public class MedicineInfo {
	private Long CTMId;
	private Long medicineId;
	private String medicineName;
	private String medicineDosage;

	public MedicineInfo MedicineInfoCons(Medicine medicine, String medicineDosage) {
		this.setMedicineId(medicine.getMedicineId());
		this.setMedicineName(medicine.getMedicineName());
		this.setMedicineDosage(medicineDosage);
		return this;
	}

}
