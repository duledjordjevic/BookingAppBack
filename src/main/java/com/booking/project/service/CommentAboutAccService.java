package com.booking.project.service;

import com.booking.project.dto.CreateCommentAboutAccDTO;
import com.booking.project.model.*;
import com.booking.project.repository.inteface.IAccommodationRepository;
import com.booking.project.repository.inteface.ICommentAboutAccRepository;
import com.booking.project.repository.inteface.IGuestRepository;
import com.booking.project.service.interfaces.ICommentAboutAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CommentAboutAccService implements ICommentAboutAccService {

    @Autowired
    private ICommentAboutAccRepository commentAboutAccRepository;
    @Autowired
    private IGuestRepository guestRepository;
    @Autowired
    private IAccommodationRepository accommodationRepository;
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
    public CommentAboutAcc create(CreateCommentAboutAccDTO createCommentAboutAccDTO) throws Exception {
        CommentAboutAcc commentAboutAcc = new CommentAboutAcc();
        commentAboutAcc.setRating(createCommentAboutAccDTO.getRating());
        commentAboutAcc.setContent(createCommentAboutAccDTO.getContent());
        commentAboutAcc.setApproved(true);
        commentAboutAcc.setReported(false);

        Optional<Guest> guest = guestRepository.findById(createCommentAboutAccDTO.getGuestId());
        if (guest.isEmpty()) return null;
        commentAboutAcc.setGuest(guest.get());

        Optional<Accommodation> accommodation = accommodationRepository.findById(createCommentAboutAccDTO.getAccommodationId());
        if (accommodation.isEmpty()) return null;
        commentAboutAcc.setAccommodation(accommodation.get());

        save(commentAboutAcc);
        return commentAboutAcc;
    }

    @Override
    public Collection<CommentAboutAcc> findByAcc(Long id) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);
        return commentAboutAccRepository.findAllByAccommodation(accommodation);
    }

    @Override
    public CommentAboutAcc approve(Long id, boolean approved) throws Exception {
        Optional<CommentAboutAcc> commentAboutAcc = commentAboutAccRepository.findById(id);
        if (commentAboutAcc.isEmpty()) return null;

        commentAboutAcc.get().setApproved(approved);
        save(commentAboutAcc.get());
        return commentAboutAcc.get();
    }

    @Override
    public CommentAboutAcc report(Long id, boolean reported) throws Exception {
        Optional<CommentAboutAcc> commentAboutAcc = commentAboutAccRepository.findById(id);
        if (commentAboutAcc.isEmpty()) return null;

        commentAboutAcc.get().setReported(reported);
        save(commentAboutAcc.get());
        return commentAboutAcc.get();
    }

    @Override
    public Collection<CommentAboutAcc> findAllReported() {
        return commentAboutAccRepository.findByReportedTrue();
    }
}
