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
	@Resource
	private final CaseRepository caseRepository;
	@Resource
	private final CaseToMedicineRepository caseToMedicineRepository;
	@Resource
	private final IllnessRepository illnessRepository;
	@Resource
	private final CaseToAssayRepository caseToAssayRepository;
	@Resource
	private final CaseToVaccineRepository caseToVaccineRepository;
	@Resource
	private final CaseToIllnessRepository caseToIllnessRepository;
	@Resource
	final MedicineClient medicineClient;
	@Resource
	final VaccineClient vaccineClient;

	@Resource
	final AssayClient assayClient;

	public CaseServiceImpl(CaseRepository caseRepository, CaseToMedicineRepository caseToMedicineRepository, IllnessRepository illnessRepository, CaseToAssayRepository caseToAssayRepository, CaseToVaccineRepository caseToVaccineRepository, CaseToIllnessRepository caseToIllnessRepository, MedicineClient medicineClient, VaccineClient vaccineClient, AssayClient assayClient) {
		this.caseRepository = caseRepository;
		this.caseToMedicineRepository = caseToMedicineRepository;
		this.illnessRepository = illnessRepository;
		this.caseToAssayRepository = caseToAssayRepository;
		this.caseToVaccineRepository = caseToVaccineRepository;
		this.caseToIllnessRepository = caseToIllnessRepository;
		this.medicineClient = medicineClient;
		this.vaccineClient = vaccineClient;
		this.assayClient = assayClient;
	}

	@Override
	public String addNewCase(CaseInfo case1) {
		Long caseId = case1.getCaseId();
		if (caseRepository.findById(caseId).isPresent()){
			return null;
		}
		Case aCase = new Case();
		aCase.CaseCons(case1);
//		aCase.setIllnessId(case1.getIllness().getIllnessId());
		aCase.setChargeId(case1.getChargeId());
		Case save = caseRepository.save(aCase);
		for (Illness illnessInfo: case1.getIllness()
			 ) {
			CaseToIllness caseToIllness = new CaseToIllness();
			caseToIllness.setIllnessId(illnessInfo.getIllnessId());
			caseToIllness.setCaseId(save.getCaseId());
			caseToIllnessRepository.save(caseToIllness);
		}
		for (MedicineInfo medicineInfo:case1.getMedicines()
			 ) {
			CaseToMedicine caseToMedicine = new CaseToMedicine();
			caseToMedicine.setCaseId(save.getCaseId());
			caseToMedicine.setMedicineId(medicineInfo.getMedicineId());
			caseToMedicineRepository.save(caseToMedicine);
		}
		for (AssayInfo assayInfo:case1.getAssays()
			 ) {
			CaseToAssay caseToAssay = new CaseToAssay();
			caseToAssay.setCaseId(save.getCaseId());
			caseToAssay.setAssayId(assayInfo.getAssayId());
			caseToAssayRepository.save(caseToAssay);
		}
		for (VaccineInfo vaccineInfo:case1.getVaccines()
			 ) {

			CaseToVaccine caseToVaccine = new CaseToVaccine();
			caseToVaccine.setCaseId(save.getCaseId());
			caseToVaccine.setVaccineId(vaccineInfo.getVaccineId());
			caseToVaccineRepository.save(caseToVaccine);
		}
		return save.getCaseId().toString();
	}

	@Override
	public String deleteCase(Long caseId) {
		Optional<Case> aCase = caseRepository.deleteCaseByCaseId(caseId);
		caseToAssayRepository.deleteAllByCaseId(caseId);
		caseToMedicineRepository.deleteAllByCaseId(caseId);
		caseToVaccineRepository.deleteAllByCaseId(caseId);
		caseToIllnessRepository.deleteAllByCaseId(caseId);
		return aCase.map(aCase1 -> aCase1.getCaseId().toString()).orElse(null);
	}

	@Override
	public String updateCase(CaseInfo case1) {
		Long caseId = case1.getCaseId();
		if (caseRepository.findById(caseId).isEmpty()){
			return null;
		}
		Case aCase = new Case();
		aCase.setCaseId(case1.getCaseId());
		aCase.CaseCons(case1);
//		aCase.setIllnessId(case1.getIllness().getIllnessId());
		aCase.setChargeId(case1.getChargeId());
		Case save = caseRepository.save(aCase);
		caseToMedicineRepository.deleteAllByCaseId(save.getCaseId());
		for (MedicineInfo medicineInfo:case1.getMedicines()
		) {
			CaseToMedicine caseToMedicine = new CaseToMedicine();
			caseToMedicine.setCaseId(save.getCaseId());
			caseToMedicine.setMedicineId(medicineInfo.getMedicineId());
			caseToMedicineRepository.save(caseToMedicine);
		}
		caseToAssayRepository.deleteAllByCaseId(save.getCaseId());
		for (AssayInfo assayInfo:case1.getAssays()
		) {
			CaseToAssay caseToAssay = new CaseToAssay();
			caseToAssay.setCaseId(save.getCaseId());
			caseToAssay.setAssayId(assayInfo.getAssayId());
			caseToAssayRepository.save(caseToAssay);
		}
		caseToVaccineRepository.deleteAllByCaseId(save.getCaseId());
		for (VaccineInfo vaccineInfo:case1.getVaccines()
		) {

			CaseToVaccine caseToVaccine = new CaseToVaccine();
			caseToVaccine.setCaseId(save.getCaseId());
			caseToVaccine.setVaccineId(vaccineInfo.getVaccineId());
			caseToVaccineRepository.save(caseToVaccine);
		}
		caseToIllnessRepository.deleteAllByCaseId(save.getCaseId());
		for (Illness illness: case1.getIllness()
			 ) {
			CaseToIllness caseToIllness = new CaseToIllness();
			caseToIllness.setCaseId(save.getCaseId());
			caseToIllness.setIllnessId(illness.getIllnessId());
			caseToIllnessRepository.save(caseToIllness);
		}
		return save.getCaseId().toString();
	}

	@Override
	public CaseInfo findCase(Long caseId) {
		Optional<Case> aCase = caseRepository.findById(caseId);
		if(aCase.isEmpty()){
			return null;
		}
		Pageable pageable= PageRequest.of(0,10);
		CaseInfo caseInfo = new CaseInfo();
//		Long illnessId = aCase.get().getIllnessId();
//		illnessRepository.findById(illnessId).ifPresent(caseInfo::setIllness);
		Page<CaseToIllness> allByCaseId3 = caseToIllnessRepository.findAllByCaseId(caseId,pageable);
		List<CaseToIllness> content3 = allByCaseId3.getContent();
		ArrayList<Illness> illnessInfos = new ArrayList<>();
		for (CaseToIllness caseToIllness: content3
			 ) {
			Optional<Illness> byId = illnessRepository.findById(caseToIllness.getIllnessId());
			illnessInfos.add(byId.get());
		}
		caseInfo.setIllness(illnessInfos);
		Long caseId1 = aCase.get().getCaseId();
//找到该病例id对应的药品，并转成info类装配到caseInfo中
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
			assayInfo.setAssayId(assayById.getAssayId());
			assayInfo.setAssayName(assayById.getName());
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
		return caseRepository.findAllByCaseNameLike(caseName, pageable);
	}

	@Override
	public Page<Case> findAllByIllnessId(Long illnessId, Pageable pageable) {
		return caseRepository.findAllByIllnessId(illnessId, pageable);
	}
}
