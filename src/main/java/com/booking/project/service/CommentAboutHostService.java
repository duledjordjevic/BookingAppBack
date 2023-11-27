package com.booking.project.service;

import com.booking.project.dto.CreateCommentAboutHostDTO;
import com.booking.project.model.CommentAboutHost;
import com.booking.project.model.Guest;
import com.booking.project.model.Host;
import com.booking.project.repository.inteface.ICommentAboutHostRepository;
import com.booking.project.repository.inteface.IGuestRepository;
import com.booking.project.repository.inteface.IHostRepository;
import com.booking.project.service.interfaces.ICommentAboutHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Collection<CommentAboutHost> findAll() { return commentAboutHostRepository.findAll(); }

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

    @Override
    public CommentAboutHost create(CreateCommentAboutHostDTO createCommentAboutHostDTO) throws Exception {

        CommentAboutHost commentAboutHost = new CommentAboutHost();
        commentAboutHost.setRating(createCommentAboutHostDTO.getRating());
        commentAboutHost.setContent(createCommentAboutHostDTO.getContent());
        commentAboutHost.setApproved(true);
        commentAboutHost.setReported(false);

        Optional<Guest> guest = guestRepository.findById(createCommentAboutHostDTO.getGuestId());
        if (guest.isEmpty()) return null;
        commentAboutHost.setGuest(guest.get());

        Optional<Host> host = hostRepository.findById(createCommentAboutHostDTO.getHostId());
        if (host.isEmpty()) return null;
        commentAboutHost.setHost(host.get());

        save(commentAboutHost);
        return commentAboutHost;
    }

    @Override
    public Collection<CommentAboutHost> findByHost(Long id){
        Optional<Host> host = hostRepository.findById(id);
        return commentAboutHostRepository.findAllByHost(host);
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

}
