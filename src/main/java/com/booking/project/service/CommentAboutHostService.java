package com.booking.project.service;

import com.booking.project.model.CommentAboutHost;
import com.booking.project.repository.inteface.ICommentAboutHostRepository;
import com.booking.project.service.interfaces.ICommentAboutHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CommentAboutHostService implements ICommentAboutHostService {

    @Autowired
    private ICommentAboutHostRepository commentAboutHostRepository;

    @Override
    public Collection<CommentAboutHost> findAll() {
        return commentAboutHostRepository.findAll();
    }

    @Override
    public Optional<CommentAboutHost> findById(Long id) {
        return commentAboutHostRepository.findById(id);
    }

    @Override
    public CommentAboutHost save(CommentAboutHost commentAboutHost) throws Exception {
        return commentAboutHostRepository.save(commentAboutHost);
    }

    @Override
    public void deleteById(Long id) {
        commentAboutHostRepository.deleteById(id);
    }
}
