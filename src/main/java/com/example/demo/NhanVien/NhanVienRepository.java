package com.example.demo.NhanVien;

import com.example.demo.Company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    List<NhanVien> findById(Long companyId);
   
}
