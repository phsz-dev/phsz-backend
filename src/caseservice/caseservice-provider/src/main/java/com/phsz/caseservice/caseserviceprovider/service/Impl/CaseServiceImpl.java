package com.phsz.caseservice.caseserviceprovider.service.Impl;

import com.phsz.caseservice.caseserviceprovider.pojo.Assay;
import com.phsz.caseservice.caseserviceprovider.pojo.*;
import com.phsz.caseservice.caseserviceprovider.pojo.caseRelation.*;
import com.phsz.caseservice.caseserviceprovider.repository.*;
import com.phsz.caseservice.caseserviceprovider.service.AssayService;
import com.phsz.caseservice.caseserviceprovider.service.CaseService;
import com.phsz.caseservice.caseserviceprovider.service.MedicineService;
import com.phsz.caseservice.caseserviceprovider.service.VaccineService;
import com.phsz.caseservice.caseserviceprovider.pojo.Medicine;
import com.phsz.caseservice.caseserviceprovider.pojo.Vaccine;
import com.phsz.common.JsonbConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CaseServiceImpl implements CaseService {

    private final CaseRepository caseRepository;

    private final CaseMedicineRepository caseMedicineRepository;

    private final DiseaseRepository diseaseRepository;

    private final CaseAssayRepository caseAssayRepository;

    private final CaseVaccineRepository caseVaccineRepository;

    private final CaseDiseaseRepository caseDiseaseRepository;

    private final CollectedCaseRepository collectedCaseRepository;

    private final ChargeRepository chargeRepository;


    private final MedicineService medicineService;
    private final VaccineService vaccineService;
    private final AssayService assayService;

    @Autowired
    public CaseServiceImpl(CaseRepository caseRepository, CaseMedicineRepository caseMedicineRepository, DiseaseRepository diseaseRepository, CaseAssayRepository caseAssayRepository, CaseVaccineRepository caseVaccineRepository, CaseDiseaseRepository caseDiseaseRepository, MedicineService medicineService, VaccineService vaccineService, AssayService assayService, CollectedCaseRepository collectedCaseRepository, ChargeRepository chargeRepository) {
        this.caseRepository = caseRepository;
        this.caseMedicineRepository = caseMedicineRepository;
        this.diseaseRepository = diseaseRepository;
        this.caseAssayRepository = caseAssayRepository;
        this.caseVaccineRepository = caseVaccineRepository;
        this.caseDiseaseRepository = caseDiseaseRepository;
        this.medicineService = medicineService;
        this.vaccineService = vaccineService;
        this.assayService = assayService;
        this.collectedCaseRepository = collectedCaseRepository;
        this.chargeRepository = chargeRepository;
    }

    @Override
    public Long addNewCase(CaseInfo case1) {
        Long caseId = case1.getId();
        if (caseId != null && caseRepository.findById(caseId).isPresent()) {
            return null;
        }
        case1.setSubmitTime(new Date().getTime());
        Case aCase = new Case();
        aCase.CaseCons(case1);
        if (case1.getCharge()!=null) {
            chargeRepository.save(case1.getCharge());
            aCase.setChargeId(case1.getCharge().getId());
        }
        Case save = caseRepository.save(aCase);
        CaseDisease caseDisease = new CaseDisease();
        caseDisease.setCaseId(save.getId());
        caseDisease.setDiseaseId(case1.getDiseaseList().getId());
        caseDiseaseRepository.save(caseDisease);
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

        return save.getId();
    }

    @Override
    public String deleteCase(Long caseId) {
        Optional<Case> aCase = caseRepository.deleteCaseById(caseId);
        if(aCase.isEmpty()){
            return null;
        }
        caseAssayRepository.deleteById(caseId);
        caseMedicineRepository.deleteById(caseId);
        caseVaccineRepository.deleteById(caseId);
        chargeRepository.deleteChargeById(aCase.get().getChargeId());
        return aCase.map(aCase1 -> aCase1.getId().toString()).orElse(null);
    }

    @Override
    @Transactional
    public String updateCase(CaseInfo case1) {
        Long caseId = case1.getId();
        if (caseRepository.findById(caseId).isEmpty()) {
            return null;
        }
        Case aCase = new Case();
        aCase.setId(case1.getId());
        aCase.CaseCons(case1);
        if(case1.getCharge()!=null){
            chargeRepository.deleteChargeById(case1.getCharge().getId());
            case1.getCharge().setId(null);
            chargeRepository.save(case1.getCharge());
            aCase.setChargeId(case1.getCharge().getId());
        }
        Case save = caseRepository.save(aCase);
        caseMedicineRepository.deleteAllByCaseId(save.getId());
        for (MedicineInfo medicineInfo : case1.getMedicines()
        ) {
            CaseMedicine caseMedicine = new CaseMedicine();
            caseMedicine.setCaseId(save.getId());
            caseMedicine.setMedicineId(medicineInfo.getId());
            caseMedicine.setMedicineDosage(medicineInfo.getMedicineDosage());
            caseMedicineRepository.save(caseMedicine);
        }
        caseAssayRepository.deleteAllByCaseId(save.getId());
        for (AssayInfo assayInfo : case1.getAssays()
        ) {
            CaseAssay caseAssay = new CaseAssay();
            caseAssay.setCaseId(save.getId());
            caseAssay.setResult(assayInfo.getResult());
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

        CaseDisease caseDisease = new CaseDisease();
        caseDisease.setCaseId(save.getId());
        caseDisease.setDiseaseId(case1.getDiseaseList().getId());
        caseDiseaseRepository.save(caseDisease);
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
        caseInfo.setBrief(aCase.get().getBrief());
        if(aCase.get().getChargeId()!=null) {
            Charge charge = chargeRepository.findById(aCase.get().getChargeId()).orElse(null);
            caseInfo.setCharge(charge);
        }
        List<CaseDisease> diseaseList = caseDiseaseRepository.findAllByCaseId(caseId);
        ArrayList<Disease> diseaseResList = new ArrayList<>();
        for (CaseDisease caseDisease : diseaseList) {
            diseaseRepository.findById(caseDisease.getDiseaseId()).ifPresent(diseaseResList::add);
        }
        if(!diseaseResList.isEmpty()){
            caseInfo.setDiseaseList(diseaseResList.getFirst());
        }
        Long caseId1 = aCase.get().getId();
        Pageable pageable = PageRequest.of(0, 10);
//
        Page<CaseMedicine> allByCaseId = caseMedicineRepository.findAllByCaseId(caseId1, pageable);
        List<CaseMedicine> content = allByCaseId.getContent();
        ArrayList<MedicineInfo> medicineInfos = new ArrayList<>();
        for (CaseMedicine caseMedicine : content
        ) {
            MedicineInfo medicineInfo = new MedicineInfo();
            System.out.println(caseMedicine.getMedicineId());
            Medicine medicineById = medicineService.findMedicineById(caseMedicine.getMedicineId());
            if(medicineById==null){
                medicineInfo.setName("药品不存在");
            }else{
                medicineInfo.MedicineInfoCons(medicineById, caseMedicine.getMedicineDosage());
            }
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
            Assay assayById = assayService.findAssayById(caseToMedicine.getAssayId());
            if(assayById!=null){
                assayInfo.setId(assayById.getId());
                assayInfo.setName(assayById.getName());
                assayInfo.setDate(assayById.getDate());
                assayInfo.setResult(caseToMedicine.getResult());
                assayInfo.setPrice(assayById.getPrice());
            }else{
                assayInfo.setName("检查不存在");
            }

            assayInfos.add(assayInfo);
        }
        caseInfo.setAssays(assayInfos);
//
        List<CaseVaccine> content2 = caseVaccineRepository.findByCaseId(caseId1);
        ArrayList<VaccineInfo> vaccineInfos = new ArrayList<>();
        for (CaseVaccine caseVaccine : content2
        ) {
            VaccineInfo vaccineInfo = new VaccineInfo();
            Vaccine vaccineById = vaccineService.findVaccineById(caseVaccine.getVaccineId());
            if(vaccineById!=null){
                vaccineInfo.setId(caseVaccine.getVaccineId());
                vaccineInfo.setName(vaccineById.getName());
                vaccineInfo.setManufacturer(vaccineById.getManufacturer());
                vaccineInfo.setExpiryDate(vaccineById.getExpiryDate());
                vaccineInfo.setPrice(vaccineById.getPrice());
            }else{
                vaccineInfo.setName("疫苗不存在");
            }


            vaccineInfos.add(vaccineInfo);
        }
        caseInfo.setVaccines(vaccineInfos);
        return caseInfo;
    }

    @Override
    public Page<AdminCaseInfo> findAllCase(Pageable pageable) {
        return caseRepository.findAllAdminCaseInfo(pageable);
    }

    @Override
    public Page<Case> findAllByCaseNameLike(String caseName, Pageable pageable) {
        return caseRepository.findAllByNameLike(caseName, pageable);
    }

    @Override
    public Page<RoughCaseInfoDto> findRoughCaseListByDiseaseId(Long diseaseId, Pageable pageable) {
        return caseDiseaseRepository.findRoughCaseInfoByDiseaseId(diseaseId, pageable);
    }

    @Override
    public CollectedCase addNewCollectCase(Long caseId, Long userId) {
        CollectedCase ccase = new CollectedCase();
        ccase.setCaseId(caseId);
        ccase.setUserId(userId);
        return collectedCaseRepository.save(ccase);
    }

    @Override
    public Page<RoughCaseInfoDto> getMyCollectedCases(Long userId, Pageable pageable) {
        return caseRepository.findRoughCaseInfoByUserId(userId, pageable);
    }
}
