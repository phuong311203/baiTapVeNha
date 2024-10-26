package com.example.demo.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    // Lấy danh sách tất cả các công ty
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    // Lưu hoặc cập nhật công ty
    public Company saveOrUpdate(Company company) {
        companyRepository.save(company);
        return company;
    }

    // Lấy công ty theo ID
    public Company getById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    // Xóa công ty theo ID
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
