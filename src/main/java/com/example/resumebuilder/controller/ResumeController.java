package com.example.resumebuilder.controller;

import com.example.resumebuilder.model.*;
import com.example.resumebuilder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("resumes", resumeService.getAllResumes());
        return "home";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        Resume resume = new Resume();
        // Initialize with one empty entry for easier editing
        resume.getExperiences().add(new Experience());
        resume.getEducations().add(new Education());
        resume.getSkills().add(new Skill());
        model.addAttribute("resume", resume);
        return "editor";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Resume resume = resumeService.getResumeById(id).orElseThrow(() -> new RuntimeException("Resume not found"));
        model.addAttribute("resume", resume);
        return "editor";
    }

    @PostMapping("/save")
    public String saveResume(@ModelAttribute Resume resume) {
        // Filter out empty entries if they weren't filled
        resume.getExperiences().removeIf(e -> e.getCompany() == null || e.getCompany().isEmpty());
        resume.getEducations().removeIf(e -> e.getInstitution() == null || e.getInstitution().isEmpty());
        resume.getSkills().removeIf(s -> s.getName() == null || s.getName().isEmpty());

        resumeService.saveResume(resume);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return "redirect:/";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
        try {
            byte[] pdfBytes = resumeService.generatePdf(id);
            Resume resume = resumeService.getResumeById(id).get();
            String filename = resume.getName().replaceAll("\\s+", "_") + "_Resume.pdf";

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
