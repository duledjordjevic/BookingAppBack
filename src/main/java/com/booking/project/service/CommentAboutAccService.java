package com.booking.project.service;

import com.booking.project.dto.*;
import com.booking.project.model.*;
import com.booking.project.model.enums.AccommodationApprovalStatus;
import com.booking.project.model.enums.NotificationType;
import com.booking.project.repository.inteface.IAccommodationRepository;
import com.booking.project.repository.inteface.ICommentAboutAccRepository;
import com.booking.project.repository.inteface.IGuestRepository;
import com.booking.project.service.interfaces.ICommentAboutAccService;
import com.booking.project.service.interfaces.INotificationForHostService;
import com.booking.project.service.interfaces.INotificationTypeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class CommentAboutAccService implements ICommentAboutAccService {

    @Autowired
    private ICommentAboutAccRepository commentAboutAccRepository;
    @Autowired
    private IGuestRepository guestRepository;
    @Autowired
    private IAccommodationRepository accommodationRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private INotificationForHostService notificationForHostService;
    @Autowired
    INotificationTypeStatusService notificationTypeStatusService;
    @Override
    public Collection<CommentAboutAccDTO> findAll() {
        Collection<CommentAboutAcc> commentsAboutAcc = commentAboutAccRepository.findAll();
        return mapToDto(commentsAboutAcc);
    }

    @Override
    public CommentAboutAccDTO findById(Long id) {
        Optional<CommentAboutAcc> commentAboutAcc = commentAboutAccRepository.findById(id);
        if(commentAboutAcc.isEmpty()) return null;

        return new CommentAboutAccDTO(commentAboutAcc.get());
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
        commentAboutAcc.setDate(LocalDate.now());

        Optional<Guest> guest = guestRepository.findByUserId(createCommentAboutAccDTO.getGuestId());
        if (guest.isEmpty()) return null;
        commentAboutAcc.setGuest(guest.get());

        Optional<Accommodation> accommodation = accommodationRepository.findById(createCommentAboutAccDTO.getAccommodationId());
        if (accommodation.isEmpty()) return null;
        commentAboutAcc.setAccommodation(accommodation.get());

        boolean notificationTurnedStatus = notificationTypeStatusService.findStatusByUserAndType(accommodation.get().getHost().getUser().getId(),
                NotificationType.NEW_REVIEW);

        if(notificationTurnedStatus){
            CreateNotificationForHostDTO notificationForHostDTO = new CreateNotificationForHostDTO(NotificationType.NEW_REVIEW,
                    guest.get().getName() + " " + guest.get().getLastName() +  " rated your accommodation "
                            + accommodation.get().getTitle(),
                    accommodation.get().getHost().getId());
            notificationForHostService.create(notificationForHostDTO);
        }


        save(commentAboutAcc);
        return commentAboutAcc;
    }

    @Override
    public Collection<CommentAboutAccDTO> findByAcc(Long id) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);
        Collection<CommentAboutAcc> commentsAboutAcc = commentAboutAccRepository.findAllByAccommodation(accommodation);
        return mapToDto(commentsAboutAcc);
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
    public Collection<CommentAboutAccDTO> findAllReported() {
        Collection<CommentAboutAcc> commentsAboutAcc = commentAboutAccRepository.findByReportedTrue();
        return mapToDto(commentsAboutAcc);
    }

    public Collection<CommentAboutAccDTO> mapToDto(Collection<CommentAboutAcc> commentsAboutAcc){
        Collection<CommentAboutAccDTO> commentsAboutAccDTOS = new ArrayList<>();
        for(CommentAboutAcc comment: commentsAboutAcc){
            CommentAboutAccDTO commentDTO = new CommentAboutAccDTO(comment);
            commentsAboutAccDTOS.add(commentDTO);
        }
        return commentsAboutAccDTOS;
    }
    @Override
    public Collection<Object[]> findAccommodationsByRating(){
        return commentAboutAccRepository.findAccomodationByRating(AccommodationApprovalStatus.APPROVED);
    }
    @Override
    public Double findAvgRateById(Long accommodationId){
        return commentAboutAccRepository.findAvgRateByAccommodation(accommodationId);
    }

    @Override
    public Collection<CommentAboutAccDTO> findByGuest(Long id) throws IOException {
        Collection<CommentAboutAcc> commentsAboutAcc = commentAboutAccRepository.findByGuestUser(id);
        Collection<CommentAboutAccDTO> commentAboutAccDTOS = mapToDto(commentsAboutAcc);
        for (CommentAboutAccDTO commentAboutAccDTO: commentAboutAccDTOS){
            commentAboutAccDTO.setCoverImage(imageService.getCoverImage(commentAboutAccDTO.getAccommodation().getImages().split(",")[0]));
        }
        return commentAboutAccDTOS;
    }
}
