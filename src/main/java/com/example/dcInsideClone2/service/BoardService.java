package com.example.dcInsideClone2.service;

//DTO -> Entity (Entity Class)
//Entity -> DTO (DTO Class)

import com.example.dcInsideClone2.dto.BoardDTO;
import com.example.dcInsideClone2.entity.BoardEntity;
import com.example.dcInsideClone2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAll() { //엔티티로 받아온 객체를 DTO로 변환해서 옮겨줘야한다.
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        //엔티티 객체를 DTO객체로 옮겨 담는다.
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }

    }
}
