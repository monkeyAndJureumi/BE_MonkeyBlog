package com.monkey.domain.temporary.root.controller;

import com.monkey.domain.temporary.root.dto.TemporaryPostIndexDto;
import com.monkey.domain.temporary.root.repository.TemporaryPostRepository;
import com.monkey.domain.temporary.root.view.TemporaryPostIndexRes;
import com.monkey.domain.user.root.entity.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
@RequestMapping("/temporary_post_index")
public class TemporaryPostRetrieveApiController {
    private final TemporaryPostRepository temporaryPostRepository;

    @GetMapping
    public ResponseEntity<TemporaryPostIndexRes> getIndexList(UserId userId) {
        List<TemporaryPostIndexDto> indexList = temporaryPostRepository.findAllIndexByUserId(userId);

        return new ResponseEntity<>(new TemporaryPostIndexRes(indexList), HttpStatus.OK);
    }


}
