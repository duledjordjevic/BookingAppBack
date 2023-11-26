package com.booking.project.service;

import com.booking.project.dto.CommentAboutAccDTO;
import com.booking.project.dto.NotificationForGuestDTO;
import com.booking.project.model.CommentAboutAcc;
import com.booking.project.model.NotificationForGuest;
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

    @Override
    public CommentAboutAcc update(CommentAboutAccDTO commentAboutAccDTO, Long id) throws Exception{
        Optional<CommentAboutAcc> commentAboutAccForUpdate = findById(id);

        if(commentAboutAccForUpdate.isEmpty()) return null;

        commentAboutAccForUpdate.get().setId(commentAboutAccDTO.getId());
        commentAboutAccForUpdate.get().setRating(commentAboutAccDTO.getRating());
        commentAboutAccForUpdate.get().setReported(commentAboutAccDTO.isReported());
        commentAboutAccForUpdate.get().setContent(commentAboutAccDTO.getContent());
        commentAboutAccForUpdate.get().setApproved(commentAboutAccDTO.isApproved());
        commentAboutAccForUpdate.get().getAccommodation().copyValues(commentAboutAccDTO.getAccommodationDTO());
        commentAboutAccForUpdate.get().getGuest().copyValues(commentAboutAccDTO.getGuestDTO());

        save(commentAboutAccForUpdate.get());
        return commentAboutAccForUpdate.get();
    }

    @Override
    public CommentAboutAcc create(CommentAboutAccDTO commentAboutAccDTO) throws Exception {
        return null;
    }
}
