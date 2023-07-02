package com.example.dcInsideClone2.service;

//DTO -> Entity (Entity Class)
//Entity -> DTO (DTO Class)

import com.example.dcInsideClone2.dto.BoardDTO;
import com.example.dcInsideClone2.entity.BoardEntity;
import com.example.dcInsideClone2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
