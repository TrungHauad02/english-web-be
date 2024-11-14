package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SubmitTestSpeakingDTO;
import java.util.List;

public interface SubmitTestSpeakingService extends BaseService<SubmitTestSpeakingDTO> {
    List<SubmitTestSpeakingDTO> findAllBySubmitTestId(String submitTestId);
}
