package com.example.demo.RestAPI;

import com.example.demo.Company.Company;
import com.example.demo.Company.CompanyRepository;
import com.example.demo.NhanVien.NhanVien;
import com.example.demo.NhanVien.NhanVienRepository;
import com.example.demo.NhanVien.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestNhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/api/companies/{companyId}/nhanviens")
    public List<NhanVien> listNhanViens(@PathVariable Long companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        return company != null ? company.getNhanViens() : null;
    }

    @GetMapping("/api/companies/{companyId}/nhanviens/{nhanVienId}")
    public NhanVien getNhanVienById(@PathVariable int nhanVienId) {
        return nhanVienService.getNhanVienById(nhanVienId);
    }

    @PostMapping("/api/companies/{companyId}/nhanviens")
    public NhanVien addNhanVien(@PathVariable Long companyId, @RequestBody NhanVien nhanVien) {
        return nhanVienService.saveNhanVien(nhanVien, companyId);
    }

    @PutMapping("/api/companies/{companyId}/nhanviens/{nhanVienId}")
    public NhanVien updateNhanVien(@PathVariable Long companyId, @PathVariable int nhanVienId, @RequestBody NhanVien nhanVien) {
        return nhanVienService.updateNhanVien( nhanVien, nhanVienId);
    }

    @DeleteMapping("/api/companies/{companyId}/nhanviens/{nhanVienId}")
    public String deleteNhanVien(@PathVariable Long companyId, @PathVariable int nhanVienId) {
        nhanVienService.deleteNhanVien(companyId, nhanVienId);
        return "NhanVien with ID " + nhanVienId + " has been deleted!";
    }

}
