package com.englishweb.english_web_be.service;

import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.annotations.AnalyzedText;

public interface TextRazorService {
    AnalyzedText analyzeText(String text) throws AnalysisException, NetworkException;
}
