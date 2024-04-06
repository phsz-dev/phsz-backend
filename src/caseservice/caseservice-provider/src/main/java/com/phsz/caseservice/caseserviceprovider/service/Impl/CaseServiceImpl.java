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

    private final CaseMedicineRepository caseMedicineRepository;

    private final DiseaseRepository diseaseRepository;

    private final CaseAssayRepository caseAssayRepository;

    private final CaseVaccineRepository caseVaccineRepository;

    private final CaseDiseaseRepository caseDiseaseRepository;

    private final CollectedCaseRepository collectedCaseRepository;


    private final MedicineClient medicineClient;
    private final VaccineClient vaccineClient;

    private final AssayClient assayClient;

    @Autowired
    public CaseServiceImpl(CaseRepository caseRepository, CaseMedicineRepository caseMedicineRepository, DiseaseRepository diseaseRepository, CaseAssayRepository caseAssayRepository, CaseVaccineRepository caseVaccineRepository, CaseDiseaseRepository caseDiseaseRepository, MedicineClient medicineClient, VaccineClient vaccineClient, AssayClient assayClient, CollectedCaseRepository collectedCaseRepository) {
        this.caseRepository = caseRepository;
        this.caseMedicineRepository = caseMedicineRepository;
        this.diseaseRepository = diseaseRepository;
        this.caseAssayRepository = caseAssayRepository;
        this.caseVaccineRepository = caseVaccineRepository;
        this.caseDiseaseRepository = caseDiseaseRepository;
        this.medicineClient = medicineClient;
        this.vaccineClient = vaccineClient;
        this.assayClient = assayClient;
        this.collectedCaseRepository = collectedCaseRepository;
    }

    @Override
    public String addNewCase(CaseInfo case1) {
        Long caseId = case1.getId();
        if (caseRepository.findById(caseId).isPresent()) {
            return null;
        }
        Case aCase = new Case();
        aCase.CaseCons(case1);
        aCase.setChargeId(case1.getChargeId());
        Case save = caseRepository.save(aCase);
        for (Disease disease : case1.getDiseaseList()
        ) {
            CaseDisease caseDisease = new CaseDisease();
            caseDisease.setCaseId(save.getId());
            caseDisease.setDiseaseId(disease.getId());
            caseDiseaseRepository.save(caseDisease);
        }
        for (MedicineInfo medicineInfo : case1.getMedicines()
        ) {
            CaseMedicine caseMedicine = new CaseMedicine();
            caseMedicine.setCaseId(save.getId());
            caseMedicine.setMedicineId(medicineInfo.getId());
            caseMedicineRepository.save(caseMedicine);
        }
        for (AssayInfo assayInfo : case1.getAssays()
        ) {
            CaseAssay caseAssay = new CaseAssay();
            caseAssay.setCaseId(save.getId());
            caseAssay.setAssayId(assayInfo.getId());
            caseAssayRepository.save(caseAssay);
        }
        for (VaccineInfo vaccineInfo : case1.getVaccines()
        ) {

            CaseVaccine caseVaccine = new CaseVaccine();
            caseVaccine.setCaseId(save.getId());
            caseVaccine.setVaccineId(vaccineInfo.getId());
            caseVaccineRepository.save(caseVaccine);
        }
        return save.getId().toString();
    }

    @Override
    public String deleteCase(Long caseId) {
        Optional<Case> aCase = caseRepository.deleteCaseById(caseId);
        caseAssayRepository.deleteById(caseId);
        caseMedicineRepository.deleteById(caseId);
        caseVaccineRepository.deleteById(caseId);
        return aCase.map(aCase1 -> aCase1.getId().toString()).orElse(null);
    }

    @Override
    public String updateCase(CaseInfo case1) {
        Long caseId = case1.getId();
        if (caseRepository.findById(caseId).isEmpty()) {
            return null;
        }
        Case aCase = new Case();
        aCase.setId(case1.getId());
        aCase.CaseCons(case1);
        aCase.setChargeId(case1.getChargeId());
        Case save = caseRepository.save(aCase);
        caseMedicineRepository.deleteAllByCaseId(save.getId());
        for (MedicineInfo medicineInfo : case1.getMedicines()
        ) {
            CaseMedicine caseMedicine = new CaseMedicine();
            caseMedicine.setCaseId(save.getId());
            caseMedicine.setMedicineId(medicineInfo.getId());
            caseMedicineRepository.save(caseMedicine);
        }
        caseAssayRepository.deleteAllByCaseId(save.getId());
        for (AssayInfo assayInfo : case1.getAssays()
        ) {
            CaseAssay caseAssay = new CaseAssay();
            caseAssay.setCaseId(save.getId());
            caseAssay.setAssayId(assayInfo.getId());
            caseAssayRepository.save(caseAssay);
        }
        caseVaccineRepository.deleteAllByCaseId(save.getId());
        for (VaccineInfo vaccineInfo : case1.getVaccines()
        ) {

            CaseVaccine caseVaccine = new CaseVaccine();
            caseVaccine.setCaseId(save.getId());
            caseVaccine.setVaccineId(vaccineInfo.getId());
            caseVaccineRepository.save(caseVaccine);
        }
        caseDiseaseRepository.deleteAllByCaseId(save.getId());
        for (Disease disease : case1.getDiseaseList()
        ) {
            CaseDisease caseDisease = new CaseDisease();
            caseDisease.setCaseId(save.getId());
            caseDisease.setDiseaseId(disease.getId());
            caseDiseaseRepository.save(caseDisease);
        }
        return save.getId().toString();
    }

    @Override
    public CaseInfo findCase(Long caseId) {
        Optional<Case> aCase = caseRepository.findById(caseId);
        if (aCase.isEmpty()) {
            return null;
        }
        CaseInfo caseInfo = new CaseInfo();
        caseInfo.setDescription(aCase.get().getDescription());
        caseInfo.setDoctorName(aCase.get().getDoctorName());
        caseInfo.setName(aCase.get().getName());
        caseInfo.setSubmitTime(aCase.get().getSubmitTime());
        caseInfo.setId(aCase.get().getId());
        caseInfo.setChargeId(aCase.get().getChargeId());
        caseInfo.setBrief(aCase.get().getBrief());
        List<CaseDisease> diseaseList = caseDiseaseRepository.findAllByCaseId(caseId);
        ArrayList<Disease> diseaseResList = new ArrayList<>();
        for (CaseDisease caseDisease : diseaseList) {
            diseaseRepository.findById(caseDisease.getDiseaseId()).ifPresent(diseaseResList::add);
        }
        caseInfo.setDiseaseList(diseaseResList);
        Long caseId1 = aCase.get().getId();
        Pageable pageable = PageRequest.of(0, 10);
//
        Page<CaseMedicine> allByCaseId = caseMedicineRepository.findAllByCaseId(caseId1, pageable);
        List<CaseMedicine> content = allByCaseId.getContent();
        ArrayList<MedicineInfo> medicineInfos = new ArrayList<>();
        for (CaseMedicine caseMedicine : content
        ) {
            MedicineInfo medicineInfo = new MedicineInfo();
            Medicine medicineById = medicineClient.getMedicineById(caseMedicine.getMedicineId());
            medicineInfo.MedicineInfoCons(medicineById, caseMedicine.getMedicineDosage());
            medicineInfos.add(medicineInfo);
        }
        caseInfo.setMedicines(medicineInfos);
//
        Page<CaseAssay> allByCaseId1 = caseAssayRepository.findAllByCaseId(caseId1, pageable);
        List<CaseAssay> content1 = allByCaseId1.getContent();
        ArrayList<AssayInfo> assayInfos = new ArrayList<>();
        for (CaseAssay caseToMedicine : content1
        ) {
            AssayInfo assayInfo = new AssayInfo();
            Assay assayById = assayClient.getAssayById(caseToMedicine.getAssayId());
            assayInfo.setId(assayById.getId());
            assayInfo.setName(assayById.getName());
            assayInfo.setDate(assayById.getDate());
            assayInfo.setResult(caseToMedicine.getResult());
            assayInfos.add(assayInfo);
        }
        caseInfo.setAssays(assayInfos);
//
        List<CaseVaccine> content2 = caseVaccineRepository.findByCaseId(caseId1);
        ArrayList<VaccineInfo> vaccineInfos = new ArrayList<>();
        for (CaseVaccine caseVaccine : content2
        ) {
            VaccineInfo vaccineInfo = new VaccineInfo();
            Vaccine vaccineById = vaccineClient.getVaccineById(caseVaccine.getVaccineId());
            vaccineInfo.setId(caseVaccine.getVaccineId());
            vaccineInfo.setName(vaccineById.getName());
            vaccineInfo.setManufacturer(vaccineById.getManufacturer());
            vaccineInfo.setExpiryDate(vaccineById.getExpiryDate());
            vaccineInfos.add(vaccineInfo);
        }
        caseInfo.setVaccines(vaccineInfos);
        return caseInfo;
    }

    @Override
    public ArrayList<Case> findAllCase(Pageable pageable) {
        Page<Case> all = caseRepository.findAll(pageable);
        Collection<Case> content = all.getContent();
        return new ArrayList<>(content);

    }

    @Override
    public Page<Case> findAllByCaseNameLike(String caseName, Pageable pageable) {
        return caseRepository.findAllByNameLike(caseName, pageable);
    }

    @Override
    public Page<RoughCaseInfoDto> findRoughCaseListByDiseaseId(Long diseaseId, Pageable pageable) {
        Page<RoughCaseInfoDto> pg = caseDiseaseRepository.findRoughCaseInfoByDiseaseId(diseaseId, pageable);
        System.out.println(pg);
        return pg;
    }

    @Override
    public CollectedCase addNewCollectCase(Long caseId, Long userId) {
        CollectedCase ccase = new CollectedCase();
        ccase.setCaseId(caseId);
        ccase.setUserId(userId);
        return collectedCaseRepository.save(ccase);
    }

    @Override
    public List<RoughCaseInfoDto> getMyCollectedCases(Long userId, Pageable pageable) {
        Page<CollectedCase> pcc = collectedCaseRepository.findCollectedCaseByUserId(userId, pageable);
        List<Long> ids = new ArrayList<>();
        for (CollectedCase cc : pcc.getContent()) {
            ids.add(cc.getCaseId());
        }
        return caseRepository.findRoughCaseInfoByIdList(ids);
    }
}
