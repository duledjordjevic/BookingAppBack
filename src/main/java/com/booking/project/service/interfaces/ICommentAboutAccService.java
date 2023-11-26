package com.booking.project.service.interfaces;

import com.booking.project.dto.CommentAboutAccDTO;
import com.booking.project.model.CommentAboutAcc;

import java.util.Collection;
import java.util.Optional;

public interface ICommentAboutAccService {

    Collection<CommentAboutAcc> findAll();

    Optional<CommentAboutAcc> findById(Long id);

    CommentAboutAcc save(CommentAboutAcc commentAboutAcc) throws Exception;

    void deleteById(Long id);

    CommentAboutAcc update(CommentAboutAccDTO commentAboutAccDTO, Long id) throws Exception;
    CommentAboutAcc create(CommentAboutAccDTO commentAboutAccDTO) throws Exception;
}
