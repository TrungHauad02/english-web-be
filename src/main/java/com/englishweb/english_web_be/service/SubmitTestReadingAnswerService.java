package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SubmitTestReadingAnswerDTO;
import java.util.List;

public interface SubmitTestReadingAnswerService extends BaseService<SubmitTestReadingAnswerDTO> {
    List<SubmitTestReadingAnswerDTO> findAllBySubmitTestId(String submitTestId);
}
