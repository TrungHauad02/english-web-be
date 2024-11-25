package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.service.TextRazorService;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.TextRazor;
import com.textrazor.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TextRazorServiceImpl implements TextRazorService {
    @Value("${text-razor.api.key}")
    String apiKey;

    public AnalyzedText analyzeText(String text) throws AnalysisException, NetworkException {
        TextRazor client = new TextRazor(apiKey);
        client.addExtractor("entities");
        client.addExtractor("topics");
        client.addExtractor("spelling");
        client.addExtractor("senses");

        return client.analyze(text);
    }
}
