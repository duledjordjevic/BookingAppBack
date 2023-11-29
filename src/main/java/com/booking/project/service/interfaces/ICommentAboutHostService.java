package com.booking.project.service.interfaces;

import com.booking.project.dto.CommentAboutHostDTO;
import com.booking.project.dto.CreateCommentAboutHostDTO;
import com.booking.project.model.CommentAboutHost;

import java.util.Collection;
import java.util.Optional;

public interface ICommentAboutHostService {

    Collection<CommentAboutHost> findAll();

    Optional<CommentAboutHost> findById(Long id);

    CommentAboutHost save(CommentAboutHost commentAboutHost) throws Exception;

    void deleteById(Long id);
    CommentAboutHost create(CreateCommentAboutHostDTO createCommentAboutHostDTO) throws Exception;
    Collection<CommentAboutHost> findByHost(Long id);
    Collection<CommentAboutHost> findAllReported();
    CommentAboutHost approve(Long id, boolean approved) throws  Exception;
    CommentAboutHost report(Long id, boolean reported) throws  Exception;

}
