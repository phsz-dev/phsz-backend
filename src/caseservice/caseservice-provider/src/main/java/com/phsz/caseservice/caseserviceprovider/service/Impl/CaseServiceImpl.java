package com.phsz.caseservice.caseserviceprovider.service.Impl;

import com.phsz.assayservice.assayserviceapi.client.AssayClient;
import com.phsz.assayservice.assayserviceapi.pojo.Assay;
import com.phsz.caseservice.caseserviceprovider.pojo.*;
import com.phsz.caseservice.caseserviceprovider.repository.*;
import com.phsz.caseservice.caseserviceprovider.service.CaseService;
import com.phsz.medicineservice.medicineserviceapi.client.MedicineClient;
import com.phsz.medicineservice.medicineserviceapi.pojo.Medicine;
import com.phsz.vaccineservice.vaccineserviceapi.client.VaccineClient;
import com.phsz.vaccineservice.vaccineserviceapi.pojo.Vaccine;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CaseServiceImpl implements CaseService {

	private final CaseRepository caseRepository;

	private final CaseToMedicineRepository caseToMedicineRepository;

	private final DiseaseRepository diseaseRepository;

	private final CaseToAssayRepository caseToAssayRepository;

	private final CaseToVaccineRepository caseToVaccineRepository;

	private final CaseToDiseaseRepository caseToDiseaseRepository;


	private final MedicineClient medicineClient;
	private final VaccineClient vaccineClient;

	private final AssayClient assayClient;

	@Autowired
	public CaseServiceImpl(CaseRepository caseRepository, CaseToMedicineRepository caseToMedicineRepository, DiseaseRepository diseaseRepository, CaseToAssayRepository caseToAssayRepository, CaseToVaccineRepository caseToVaccineRepository, CaseToDiseaseRepository caseToDiseaseRepository, MedicineClient medicineClient, VaccineClient vaccineClient, AssayClient assayClient) {
		this.caseRepository = caseRepository;
		this.caseToMedicineRepository = caseToMedicineRepository;
		this.diseaseRepository = diseaseRepository;
		this.caseToAssayRepository = caseToAssayRepository;
		this.caseToVaccineRepository = caseToVaccineRepository;
		this.caseToDiseaseRepository = caseToDiseaseRepository;
		this.medicineClient = medicineClient;
		this.vaccineClient = vaccineClient;
		this.assayClient = assayClient;
	}

	@Override
	public String addNewCase(CaseInfo case1) {
		Long caseId = case1.getId();
		if (caseRepository.findById(caseId).isPresent()){
			return null;
		}
		Case aCase = new Case();
		aCase.CaseCons(case1);
		aCase.setChargeId(case1.getChargeId());
		Case save = caseRepository.save(aCase);
		for (Disease disease:case1.getDiseaseList()
			 ) {
			CaseToDisease caseToDisease = new CaseToDisease();
			caseToDisease.setCaseId(save.getId());
			caseToDisease.setDiseaseId(disease.getId());
			caseToDiseaseRepository.save(caseToDisease);
		}
		for (MedicineInfo medicineInfo:case1.getMedicines()
			 ) {
			CaseToMedicine caseToMedicine = new CaseToMedicine();
			caseToMedicine.setCaseId(save.getId());
			caseToMedicine.setMedicineId(medicineInfo.getMedicineId());
			caseToMedicineRepository.save(caseToMedicine);
		}
		for (AssayInfo assayInfo:case1.getAssays()
			 ) {
			CaseToAssay caseToAssay = new CaseToAssay();
			caseToAssay.setCaseId(save.getId());
			caseToAssay.setAssayId(assayInfo.getAssayId());
			caseToAssayRepository.save(caseToAssay);
		}
		for (VaccineInfo vaccineInfo:case1.getVaccines()
			 ) {

			CaseToVaccine caseToVaccine = new CaseToVaccine();
			caseToVaccine.setCaseId(save.getId());
			caseToVaccine.setVaccineId(vaccineInfo.getVaccineId());
			caseToVaccineRepository.save(caseToVaccine);
		}
		return save.getId().toString();
	}

	@Override
	public String deleteCase(Long caseId) {
		Optional<Case> aCase = caseRepository.deleteCaseById(caseId);
		caseToAssayRepository.deleteById(caseId);
		caseToMedicineRepository.deleteById(caseId);
		caseToVaccineRepository.deleteById(caseId);
		return aCase.map(aCase1 -> aCase1.getId().toString()).orElse(null);
	}

	@Override
	public String updateCase(CaseInfo case1) {
		Long caseId = case1.getId();
		if (caseRepository.findById(caseId).isEmpty()){
			return null;
		}
		Case aCase = new Case();
		aCase.setId(case1.getId());
		aCase.CaseCons(case1);
		aCase.setChargeId(case1.getChargeId());
		Case save = caseRepository.save(aCase);
		caseToMedicineRepository.deleteAllByCaseId(save.getId());
		for (MedicineInfo medicineInfo:case1.getMedicines()
		) {
			CaseToMedicine caseToMedicine = new CaseToMedicine();
			caseToMedicine.setCaseId(save.getId());
			caseToMedicine.setMedicineId(medicineInfo.getMedicineId());
			caseToMedicineRepository.save(caseToMedicine);
		}
		caseToAssayRepository.deleteAllByCaseId(save.getId());
		for (AssayInfo assayInfo:case1.getAssays()
		) {
			CaseToAssay caseToAssay = new CaseToAssay();
			caseToAssay.setCaseId(save.getId());
			caseToAssay.setAssayId(assayInfo.getAssayId());
			caseToAssayRepository.save(caseToAssay);
		}
		caseToVaccineRepository.deleteAllByCaseId(save.getId());
		for (VaccineInfo vaccineInfo:case1.getVaccines()
		) {

			CaseToVaccine caseToVaccine = new CaseToVaccine();
			caseToVaccine.setCaseId(save.getId());
			caseToVaccine.setVaccineId(vaccineInfo.getVaccineId());
			caseToVaccineRepository.save(caseToVaccine);
		}
		caseToDiseaseRepository.deleteAllByCaseId(save.getId());
		for (Disease disease:case1.getDiseaseList()
		) {
			CaseToDisease caseToDisease = new CaseToDisease();
			caseToDisease.setCaseId(save.getId());
			caseToDisease.setDiseaseId(disease.getId());
			caseToDiseaseRepository.save(caseToDisease);
		}
		return save.getId().toString();
	}

	@Override
	public CaseInfo findCase(Long caseId) {
		Optional<Case> aCase = caseRepository.findById(caseId);
		if(aCase.isEmpty()){
			return null;
		}
		CaseInfo caseInfo = new CaseInfo();
		List<CaseToDisease> diseaseList = caseToDiseaseRepository.findAllByCaseId(caseId);
		ArrayList<Disease> diseaseResList = new ArrayList<>();
		for(CaseToDisease caseToDisease:diseaseList){
			diseaseRepository.findById(caseToDisease.getDiseaseId()).ifPresent(diseaseResList::add);
		}
		caseInfo.setDiseaseList(diseaseResList);
		Long caseId1 = aCase.get().getId();
		Pageable pageable= PageRequest.of(0,10);
//
		Page<CaseToMedicine> allByCaseId = caseToMedicineRepository.findAllByCaseId(caseId1, pageable);
		List<CaseToMedicine> content = allByCaseId.getContent();
		ArrayList<MedicineInfo> medicineInfos = new ArrayList<>();
		for (CaseToMedicine caseToMedicine:content
			 ) {
			MedicineInfo medicineInfo = new MedicineInfo();
			Medicine medicineById = medicineClient.getMedicineById(caseToMedicine.getMedicineId());
			medicineInfo.MedicineInfoCons(medicineById,caseToMedicine.getMedicineDosage());
			medicineInfos.add(medicineInfo);
		}
		caseInfo.setMedicines(medicineInfos);
//
		Page<CaseToAssay> allByCaseId1 = caseToAssayRepository.findAllByCaseId(caseId1, pageable);
		List<CaseToAssay> content1 = allByCaseId1.getContent();
		ArrayList<AssayInfo> assayInfos = new ArrayList<>();
		for (CaseToAssay caseToMedicine:content1
			 ) {
			AssayInfo assayInfo = new AssayInfo();
			Assay assayById = assayClient.getAssayById(caseToMedicine.getAssayId());
			assayInfo.setAssayId(assayById.getId());
			assayInfo.setAssayName(assayById.getName());
			assayInfo.setResult(assayById.getResult());
			assayInfos.add(assayInfo);
		}
		caseInfo.setAssays(assayInfos);
//
		Page<CaseToVaccine> allByCaseId2 = caseToVaccineRepository.findAllByCaseId(caseId1, pageable);
		List<CaseToVaccine> content2 = allByCaseId2.getContent();
		ArrayList<VaccineInfo> vaccineInfos = new ArrayList<>();
		for (CaseToVaccine caseToVaccine:content2
		) {
			VaccineInfo vaccineInfo = new VaccineInfo();
			Vaccine vaccineById = vaccineClient.getVaccineById(caseToVaccine.getVaccineId());
			vaccineInfo.setVaccineId(caseToVaccine.getVaccineId());
			vaccineInfo.setVaccineName(vaccineById.getName());
			vaccineInfos.add(vaccineInfo);
		}
		caseInfo.setVaccines(vaccineInfos);
		return caseInfo;
	}

	@Override
	public ArrayList<Case> findAllCase(org.springframework.data.domain.Pageable pageable) {
		Page<Case> all = caseRepository.findAll(pageable);
		Collection<Case> content = all.getContent();
		return new ArrayList<>(content);

	}

	@Override
	public Page<Case> findAllByCaseNameLike(String caseName, org.springframework.data.domain.Pageable pageable) {
		return caseRepository.findAllByNameLike(caseName, pageable);
	}

	@Override
	public Page<RoughCaseInfo> findRoughCaseListByIllnessId(Long illnessId, Pageable pageable) {
		return caseToDiseaseRepository.findRoughCaseInfoByDiseaseId(illnessId, pageable);
	}
}
