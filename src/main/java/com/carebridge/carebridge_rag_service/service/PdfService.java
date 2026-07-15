package com.carebridge.carebridge_rag_service.service;

import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PdfService {
     public String extractText() {

        try {

            ClassPathResource resource =
                    new ClassPathResource("pdf/CareBridge.pdf");

            PDDocument document =
                    Loader.loadPDF(resource.getFile());

            PDFTextStripper stripper =
                    new PDFTextStripper();

            String text =
                    stripper.getText(document);

            document.close();

            return text;

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }
}
