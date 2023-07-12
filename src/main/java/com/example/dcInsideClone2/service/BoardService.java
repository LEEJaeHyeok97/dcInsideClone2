package com.example.dcInsideClone2.service;

//DTO -> Entity (Entity Class)
//Entity -> DTO (DTO Class)

import com.example.dcInsideClone2.dto.BoardDTO;
import com.example.dcInsideClone2.entity.BoardEntity;
import com.example.dcInsideClone2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    EntityManager em;
    private final BoardRepository boardRepository;
    public Long save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
        return boardEntity.getId();
    }

    public List<BoardDTO> findAll() { //엔티티로 받아온 객체를 DTO로 변환해서 옮겨줘야한다.
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        //엔티티 객체를 DTO객체로 옮겨 담는다.
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }

        return boardDTOList;
    }

    public BoardDTO findById2(Long id) {
        BoardEntity boardEntity = em.createQuery("SELECT * FROM board_table b WHERE b.id", BoardEntity.class)
                .setParameter("id", id)
                .getSingleResult();

        BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
        return boardDTO;

    }


    @Transactional //jpa에 수동적으로 추가해준 메서드는 Transactional을 붙여준다.
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }


    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        //jpa에는 업데이트를 위한 메서드가 따로 제공되지 않는다. save 메서드를 활용해서 update, insert를 가능하게 한다.(id 유무로 기능 구분)
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());

    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
