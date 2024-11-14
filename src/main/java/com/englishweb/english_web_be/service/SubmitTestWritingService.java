package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SubmitTestWritingDTO;
import java.util.List;

public interface SubmitTestWritingService extends BaseService<SubmitTestWritingDTO> {
    List<SubmitTestWritingDTO> findAllBySubmitTestId(String submitTestId);
}
