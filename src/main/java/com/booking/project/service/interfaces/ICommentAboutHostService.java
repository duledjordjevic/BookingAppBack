package com.booking.project.service.interfaces;

import com.booking.project.model.CommentAboutHost;

import java.util.Collection;
import java.util.Optional;

public interface ICommentAboutHostService {

    Collection<CommentAboutHost> findAll();

    Optional<CommentAboutHost> findById(Long id);

    CommentAboutHost save(CommentAboutHost commentAboutHost) throws Exception;

    void deleteById(Long id);
}
