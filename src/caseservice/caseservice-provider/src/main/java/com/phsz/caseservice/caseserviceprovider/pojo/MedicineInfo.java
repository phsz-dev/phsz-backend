package com.phsz.caseservice.caseserviceprovider.pojo;

import com.phsz.medicineservice.medicineserviceapi.pojo.Medicine;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MedicineInfo {
	private Long id;
	private String name;
	private String type;
	private String usage;
	private Date validity;
	private String batchNumber;
	private String medicineDosage;
	private BigDecimal price;

	public MedicineInfo MedicineInfoCons(Medicine medicine, String medicineDosage) {
		this.setId(medicine.getId());
		this.setName(medicine.getName());
		this.setMedicineDosage(medicineDosage);
		return this;
	}

}
