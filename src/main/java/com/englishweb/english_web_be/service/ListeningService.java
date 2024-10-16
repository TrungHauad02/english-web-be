package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListenAndWriteAWordDTO;
import com.englishweb.english_web_be.dto.ListenPracticeDTO;
import com.englishweb.english_web_be.dto.ListeningDTO;
import com.englishweb.english_web_be.model.Listening;
import com.englishweb.english_web_be.repository.ListeningRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeningService extends BaseService<Listening, ListeningDTO, ListeningRepository> {

    private final ListenPracticeService listenPracticeService;
    private final ListenAndWriteAWordService listenAndWriteAWordService;

    public ListeningService(ListeningRepository repository, ListenPracticeService listenPracticeService, ListenAndWriteAWordService listenAndWriteAWordService) {
        super(repository);
        this.listenPracticeService = listenPracticeService;
        this.listenAndWriteAWordService = listenAndWriteAWordService;
    }

    @Override
    public void delete(String id){
        ListenPracticeDTO listenPracticeDTO = listenPracticeService.findListenPracticeByListeningId(id);
        listenPracticeService.delete(listenPracticeDTO.getId());
        List<ListenAndWriteAWordDTO> listenAndWriteAWordDTOList = listenAndWriteAWordService.findByListeningId(id);
        for (ListenAndWriteAWordDTO listenAndWriteAWordDTO : listenAndWriteAWordDTOList) {
            listenAndWriteAWordService.delete(listenAndWriteAWordDTO.getId());
        }
        super.delete(id);
    }

    @Override
    protected ListeningDTO convertToDTO(Listening entity){
        ListeningDTO dto = new ListeningDTO();
        dto.setId(entity.getId());
        dto.setSerial(entity.getSerial());
        dto.setImage(entity.getImage());
        dto.setDescription(entity.getDescription());
        dto.setTitle(entity.getTitle());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    protected Listening convertToEntity(ListeningDTO dto){
        Listening entity = new Listening();
        entity.setId(dto.getId());
        entity.setSerial(dto.getSerial());
        entity.setImage(dto.getImage());
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
