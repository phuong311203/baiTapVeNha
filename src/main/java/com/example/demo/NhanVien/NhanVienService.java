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

    // Lưu nhân viên
    public void saveNhanVien(NhanVien nhanVien, Long companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company != null) {
            nhanVien.getCompanies().add(company); // Thêm company vào danh sách của nhanVien
            company.getNhanViens().add(nhanVien); // Thêm nhanVien vào danh sách của company
            nhanVienRepository.save(nhanVien);
            companyRepository.save(company);
        }
    }

    // Cập nhật nhân viên
    public void updateNhanVien(NhanVien nhanVien, int nhanVienId) {
        NhanVien existingNhanVien = nhanVienRepository.findById(nhanVienId).orElse(null);
        if (existingNhanVien != null) {
            existingNhanVien.setName(nhanVien.getName());
            nhanVienRepository.save(existingNhanVien);
        }
    }

    // Xóa nhân viên
    public void deleteNhanVien(Long companyId, int nhanVienId) {
        NhanVien nhanVien = nhanVienRepository.findById(nhanVienId).orElse(null);
        if (nhanVien != null) {
            Company company = companyRepository.findById(companyId).orElse(null);
            if (company != null) {
                company.getNhanViens().remove(nhanVien);
                companyRepository.save(company); // Cập nhật company trước khi xóa nhân viên
            }
            nhanVienRepository.delete(nhanVien); // Sau đó xóa nhân viên
        }
    }
}
