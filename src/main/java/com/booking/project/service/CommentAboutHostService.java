package com.booking.project.service;

import com.booking.project.dto.CommentAboutAccDTO;
import com.booking.project.dto.CommentAboutHostDTO;
import com.booking.project.dto.CreateCommentAboutHostDTO;
import com.booking.project.dto.CreateNotificationForHostDTO;
import com.booking.project.model.CommentAboutAcc;
import com.booking.project.model.CommentAboutHost;
import com.booking.project.model.Guest;
import com.booking.project.model.Host;
import com.booking.project.model.enums.NotificationType;
import com.booking.project.repository.inteface.ICommentAboutHostRepository;
import com.booking.project.repository.inteface.IGuestRepository;
import com.booking.project.repository.inteface.IHostRepository;
import com.booking.project.service.interfaces.ICommentAboutHostService;
import com.booking.project.service.interfaces.INotificationForHostService;
import com.booking.project.service.interfaces.INotificationTypeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class CommentAboutHostService implements ICommentAboutHostService {

    @Autowired
    private ICommentAboutHostRepository commentAboutHostRepository;
    @Autowired
    private IGuestRepository guestRepository;
    @Autowired
    private IHostRepository hostRepository;
    @Autowired
    private INotificationForHostService notificationForHostService;
    @Autowired
    private INotificationTypeStatusService notificationTypeStatusService;

    @Override
    public Collection<CommentAboutHostDTO> findAll() {
        Collection<CommentAboutHost> commentsAboutHost = commentAboutHostRepository.findAll();
        return mapToDto(commentsAboutHost);
    }

    @Override
    public CommentAboutHostDTO findById(Long id) {
        Optional<CommentAboutHost> commentAboutHost = commentAboutHostRepository.findById(id);
        if(commentAboutHost.isEmpty()) return null;

        return new CommentAboutHostDTO(commentAboutHost.get());
    }

    @Override
    public CommentAboutHost save(CommentAboutHost commentAboutHost) throws Exception {
        return commentAboutHostRepository.save(commentAboutHost);
    }

    @Override
    public void deleteById(Long id) {
        commentAboutHostRepository.deleteById(id);
    }

    @Override
    public CommentAboutHost create(CreateCommentAboutHostDTO createCommentAboutHostDTO) throws Exception {

        CommentAboutHost commentAboutHost = new CommentAboutHost();
        commentAboutHost.setRating(createCommentAboutHostDTO.getRating());
        commentAboutHost.setContent(createCommentAboutHostDTO.getContent());
        commentAboutHost.setApproved(true);
        commentAboutHost.setReported(false);
        commentAboutHost.setDate(LocalDate.now());
        commentAboutHost.setReportMessage("");

        Optional<Guest> guest = guestRepository.findByUserId(createCommentAboutHostDTO.getGuestId());
        if (guest.isEmpty()) return null;
        commentAboutHost.setGuest(guest.get());

        Host host = hostRepository.findByUserId(createCommentAboutHostDTO.getHostId());
        commentAboutHost.setHost(host);

        boolean notificationTurnedStatus = notificationTypeStatusService.findStatusByUserAndType(host.getUser().getId(), NotificationType.NEW_REVIEW);
        if(notificationTurnedStatus){
            CreateNotificationForHostDTO notificationForHostDTO = new CreateNotificationForHostDTO(NotificationType.NEW_REVIEW,
                    guest.get().getName() + " " + guest.get().getLastName() +  " rated you", host.getId());
            notificationForHostService.create(notificationForHostDTO);
        }

        save(commentAboutHost);
        return commentAboutHost;
    }

    @Override
    public Collection<CommentAboutHostDTO> findByHost(Long id){
        Collection<CommentAboutHost> commentsAboutHost = commentAboutHostRepository.findAllForDisplay(id);
        return mapToDto(commentsAboutHost);
    }

    @Override
    public CommentAboutHost report(Long id, boolean reported) throws  Exception{

        Optional<CommentAboutHost> commentAboutHost = commentAboutHostRepository.findById(id);
        if (commentAboutHost.isEmpty()) return null;

        commentAboutHost.get().setReported(reported);
        save(commentAboutHost.get());
        return commentAboutHost.get();
    }

    @Override
    public CommentAboutHost approve(Long id, boolean approved) throws  Exception{

        Optional<CommentAboutHost> commentAboutHost = commentAboutHostRepository.findById(id);
        if (commentAboutHost.isEmpty()) return null;

        commentAboutHost.get().setApproved(approved);
        save(commentAboutHost.get());
        return commentAboutHost.get();
    }

    @Override
    public Collection<CommentAboutHostDTO> findAllReported() {
        Collection<CommentAboutHost> commentsAboutHost = commentAboutHostRepository.findByReportedTrue();
        return mapToDto(commentsAboutHost);
    }

    public Collection<CommentAboutHostDTO> mapToDto(Collection<CommentAboutHost> commentsAboutHost){
        Collection<CommentAboutHostDTO> commentsAboutHostDTOS = new ArrayList<>();
        for(CommentAboutHost comment: commentsAboutHost){
            CommentAboutHostDTO commentDTO = new CommentAboutHostDTO(comment);
            commentsAboutHostDTOS.add(commentDTO);
        }
        return commentsAboutHostDTOS;
    }

    @Override
    public Collection<CommentAboutHostDTO> findByGuest(Long id){
        Collection<CommentAboutHost> commentsAboutHost = commentAboutHostRepository.findByGuestUser(id);
        return mapToDto(commentsAboutHost);
    }

    @Override
    public CommentAboutHost setReportMessage(Long id, String message) throws Exception {
        Optional<CommentAboutHost> commentAboutHost = commentAboutHostRepository.findById(id);
        if (commentAboutHost.isEmpty()) return null;

        commentAboutHost.get().setReportMessage(message);
        save(commentAboutHost.get());
        return commentAboutHost.get();
    }
}
