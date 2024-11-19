package com.englishweb.english_web_be.dto.response;
import lombok.*;

import java.net.URL;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreSpeakingResponse {
    private List<ProvidedData> providedData;
    private List<OverallResultData> overallResultData;
    private List<WordResultData> wordResultData;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProvidedData {
        private URL audioProvided;
        private String postProvided;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OverallResultData {
        private URL aiReading;
        private double lengthOfRecordingInSec;
        private int numberOfRecognizedWords;
        private int numberOfWordsInPost;
        private double overallPoints;
        private int postLanguageId;
        private String postLanguageName;
        private String scoreId;
        private String userRecordingTranscript;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class WordResultData {
        private double points;
        private String speed;
        private String word;
    }
}
