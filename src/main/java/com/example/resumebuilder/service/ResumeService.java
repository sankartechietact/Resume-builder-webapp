package com.example.resumebuilder.service;

import com.example.resumebuilder.model.Resume;
import com.example.resumebuilder.repository.ResumeRepository;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.w3c.dom.Document;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private TemplateEngine templateEngine;

    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    public Optional<Resume> getResumeById(Long id) {
        return resumeRepository.findById(id);
    }

    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }

    public byte[] generatePdf(Long id) throws Exception {
        Resume resume = getResumeById(id).orElseThrow(() -> new RuntimeException("Resume not found"));

        Context context = new Context();
        context.setVariable("resume", resume);

        // Render HTML from Thymeleaf template
        String html = templateEngine.process("pdf-template", context);

        // Convert HTML to PDF
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();

            // Use Jsoup to parse the HTML and convert to W3C Document for openhtmltopdf
            Document doc = new W3CDom().fromJsoup(Jsoup.parse(html));

            builder.withW3cDocument(doc, "/");
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        }
    }
}
