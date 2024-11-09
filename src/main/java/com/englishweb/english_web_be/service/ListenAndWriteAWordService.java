package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListenAndWriteAWordDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public interface ListenAndWriteAWordService extends BaseService<ListenAndWriteAWordDTO> {

    List<ListenAndWriteAWordDTO> findByListeningId(String listeningId);

    List<ListenAndWriteAWordDTO> findByListeningIdAndStatus(String listeningId, StatusEnum status);

}
