package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.response.ScoreCommentResponse;
import com.englishweb.english_web_be.dto.response.ScoreSpeechTextResponse;
import com.englishweb.english_web_be.service.ScoreSpeechText;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScoreSpeechTextImpl implements ScoreSpeechText {
    private final GeminiClientServiceImpl geminiClientServiceImpl;

    public ScoreSpeechTextImpl(GeminiClientServiceImpl geminiClientServiceImpl) {
        this.geminiClientServiceImpl = geminiClientServiceImpl;
    }

    @Override
    public ScoreSpeechTextResponse scoreSpeechTextAndRealText(String speechText, String realText) throws JsonProcessingException {
        String prompt = "Role: Act as a professional English teacher.\n" +
                "Task: Evaluate the accuracy of a speech text against a reference text.\n" +
                "Instructions:\n" +
                "1. Analyze the speech text and provide:\n" +
                "   - A **score** (0-100) indicating accuracy (100 = perfect match).\n" +
                "   - A **comment** identifying and correcting errors in the speech text.\n" +
                "2. **Format for the output:**\n" +
                "   {\n" +
                "     \"score\": \"<score>/100\",\n" +
                "     \"comment\": [\n" +
                "       {\n" +
                "         \"startIndex\": <startIndex>,\n" +
                "         \"endIndex\": <endIndex>,\n" +
                "         \"errorChars\": \"<errorChars>\"\n" +
                "       }\n" +
                "     ]\n" +
                "   }\n" +
                "3. Guidelines for comments:\n" +
                "   - Identify only **one incorrect word** per `startIndex` and `endIndex`.\n" +
                "   - Use `startIndex` and `endIndex` to mark the **word's position** in the speech text.\n" +
                "   - Include the incorrect word in `errorChars`.\n" +
                "   - Do not correct words with identical meanings (e.g., \"is\" and \"'s\").\n" +
                "4. Example:\n" +
                "Example Input:\n" +
                "Speech Text: \"I have been in Korean\"\n" +
                "Reference Text: \"I've born in Japan\"\n" +
                "Example Output:\n" +
                "{\n" +
                "  \"score\": \"20/100\",\n" +
                "  \"comment\": [\n" +
                "    {\n" +
                "      \"startIndex\": 7,\n" +
                "      \"endIndex\": 11,\n" +
                "      \"errorChars\": \"been\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"startIndex\": 12,\n" +
                "      \"endIndex\": 18,\n" +
                "      \"errorChars\": \"Korean\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n" +
                "Evaluate the accuracy of a speech text against a reference text.\n" +
                "5. Real work:\n" +
                "Speech Text: \"" + speechText + "\"\n" +
                "Reference Text: \"" + realText + "\"\n" +
                "Don't be delusional and evaluate accuracy then give exactly format as above.\n";
        log.info("Score Speech Text with prompt: {}", prompt);

        String response = geminiClientServiceImpl.generateText(prompt);
        log.info("Response received from Gemini: {}", response);
        int firstNewLine = response.indexOf('\n');
        int lastNewLine = response.lastIndexOf('\n');
        if (firstNewLine != -1 && lastNewLine != -1 && firstNewLine < lastNewLine) {
            response = response.substring(firstNewLine + 1, lastNewLine);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, ScoreSpeechTextResponse.class);
    }
}
