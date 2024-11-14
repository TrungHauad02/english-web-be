package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SubmitTestMixingAnswerDTO;
import java.util.List;

public interface SubmitTestMixingAnswerService extends BaseService<SubmitTestMixingAnswerDTO> {
    List<SubmitTestMixingAnswerDTO> findAllBySubmitTestId(String submitTestId);
}
