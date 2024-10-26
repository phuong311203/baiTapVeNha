package com.example.demo.NhanVien;

import com.example.demo.Company.Company;
import com.example.demo.Company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public NhanVien getNhanVienById(int nhanVienId) {
        return nhanVienRepository.findById(nhanVienId).orElse(null);
    }


    public NhanVien saveNhanVien(NhanVien nhanVien, Long companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company != null) {
            nhanVien.getCompanies().add(company);
            company.getNhanViens().add(nhanVien);
            nhanVienRepository.save(nhanVien);
            companyRepository.save(company);
        }
        return nhanVien;
    }


    public NhanVien updateNhanVien(NhanVien nhanVien, int nhanVienId) {
        NhanVien existingNhanVien = nhanVienRepository.findById(nhanVienId).orElse(null);
        if (existingNhanVien != null) {
            existingNhanVien.setName(nhanVien.getName());
            nhanVienRepository.save(existingNhanVien);
        }
        return existingNhanVien;
    }


    public void deleteNhanVien(Long companyId, int nhanVienId) {
        NhanVien nhanVien = nhanVienRepository.findById(nhanVienId).orElse(null);
        if (nhanVien != null) {
            Company company = companyRepository.findById(companyId).orElse(null);
            if (company != null) {
                company.getNhanViens().remove(nhanVien);
                companyRepository.save(company);
            }
            nhanVienRepository.delete(nhanVien);
        }
    }
}
