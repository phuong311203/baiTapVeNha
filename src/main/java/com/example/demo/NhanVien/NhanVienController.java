package com.example.demo.NhanVien;

import com.example.demo.Company.Company;
import com.example.demo.Company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/companies")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;

    @GetMapping("/{companyId}/nhanviens")
    public String listNhanViens(@PathVariable Long companyId, Model model) {
        Company company = companyRepository.findById(companyId).orElse(null);
        model.addAttribute("company", company);
        return "ListNhanVien";
    }


    @GetMapping("/{companyId}/addNhanVien")
    public String addNhanVienForm(@PathVariable Long companyId, Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("companyId", companyId);
        return "addNhanVien";
    }

    @PostMapping("/{companyId}/addNhanVien")
    public String addNhanVien(@PathVariable Long companyId, @ModelAttribute NhanVien nhanVien) {
        nhanVienService.saveNhanVien(nhanVien, companyId);
        return "redirect:/companies/" + companyId + "/nhanviens";
    }


    @GetMapping("/{companyId}/editNhanVien/{nhanVienId}")
    public String editNhanVienForm(@PathVariable Long companyId, @PathVariable int nhanVienId, Model model) {
        NhanVien nhanVien = nhanVienRepository.findById(nhanVienId).orElse(null);
        model.addAttribute("nhanVien", nhanVien);
        model.addAttribute("companyId", companyId);
        return "editNhanVien";
    }

    @PostMapping("/{companyId}/editNhanVien/{nhanVienId}")
    public String updateNhanVien(@PathVariable Long companyId, @PathVariable int nhanVienId, @ModelAttribute NhanVien nhanVien) {
        nhanVienService.updateNhanVien(nhanVien, nhanVienId);
        return "redirect:/companies/" + companyId + "/nhanviens";
    }


    @GetMapping("/{companyId}/delete/{nhanVienId}")
    public String deleteNhanVien(@PathVariable Long companyId, @PathVariable int nhanVienId) {
        nhanVienService.deleteNhanVien(companyId, nhanVienId);
        return "redirect:/companies/" + companyId + "/nhanviens";
    }
}
