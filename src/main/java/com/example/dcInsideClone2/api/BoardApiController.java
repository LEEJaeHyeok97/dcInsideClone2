package com.example.dcInsideClone2.api;

import com.example.dcInsideClone2.dto.BoardDTO;
import com.example.dcInsideClone2.service.BoardService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController // @Controller + @ResponseBody <= (데이터 자체를 JSON의 xml로 바로 보내고자 할 때 쓴다.)
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService; //의존성 주입 -> 유연성, 재사용성

    @PostMapping("/api/v1/save")
    public CreateBoardResponse save(@RequestBody @Valid BoardDTO boardDTO) {
        Long id = boardService.save(boardDTO);
        return new CreateBoardResponse(id);
    }

    @Data
    static class CreateBoardResponse {
        private Long id;

        public CreateBoardResponse(Long id) {
            this.id = id;
        }
    }
}
