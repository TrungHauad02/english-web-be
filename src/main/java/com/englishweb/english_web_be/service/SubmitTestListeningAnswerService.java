package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SubmitTestListeningAnswerDTO;
import java.util.List;

public interface SubmitTestListeningAnswerService extends BaseService<SubmitTestListeningAnswerDTO> {
    List<SubmitTestListeningAnswerDTO> findAllBySubmitTestId(String submitTestId);
}
