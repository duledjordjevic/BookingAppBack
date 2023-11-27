package com.booking.project.service.interfaces;


import com.booking.project.dto.CreateCommentAboutAccDTO;
import com.booking.project.model.CommentAboutAcc;

import java.util.Collection;
import java.util.Optional;

public interface ICommentAboutAccService {

    Collection<CommentAboutAcc> findAll();

    Optional<CommentAboutAcc> findById(Long id);

    CommentAboutAcc save(CommentAboutAcc commentAboutAcc) throws Exception;

    void deleteById(Long id);
    CommentAboutAcc create(CreateCommentAboutAccDTO createCommentAboutAccDTO) throws Exception;
    Collection<CommentAboutAcc> findByAcc(Long id);
    CommentAboutAcc approve(Long id, boolean approved) throws  Exception;
    CommentAboutAcc report(Long id, boolean reported) throws  Exception;
}
