package com.booking.project.service;

import com.booking.project.model.CommentAboutAcc;
import com.booking.project.repository.inteface.ICommentAboutAccRepository;
import com.booking.project.service.interfaces.ICommentAboutAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CommentAboutAccService implements ICommentAboutAccService {

    @Autowired
    private ICommentAboutAccRepository commentAboutAccRepository;
    @Override
    public Collection<CommentAboutAcc> findAll() {
        return commentAboutAccRepository.findAll();
    }

    @Override
    public Optional<CommentAboutAcc> findById(Long id) {
        return commentAboutAccRepository.findById(id);
    }

    @Override
    public CommentAboutAcc save(CommentAboutAcc commentAboutAcc) throws Exception {
        return commentAboutAccRepository.save(commentAboutAcc);
    }

    @Override
    public void deleteById(Long id) {
        commentAboutAccRepository.deleteById(id);
    }
}
