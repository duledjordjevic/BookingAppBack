package com.booking.project.controller;

import com.booking.project.dto.CommentAboutHostDTO;
import com.booking.project.dto.CreateCommentAboutHostDTO;
import com.booking.project.dto.CreateNotificationForGuestDTO;
import com.booking.project.model.CommentAboutHost;
import com.booking.project.model.NotificationForGuest;
import com.booking.project.service.interfaces.ICommentAboutHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/commentsAboutHost")
public class CommentAboutHostController {

    @Autowired
    private ICommentAboutHostService commentAboutHostService;
    @PreAuthorize("hasRole('GUEST')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCommentAboutHost
            (@RequestBody CreateCommentAboutHostDTO createCommentAboutHostDTO) throws Exception {
        CommentAboutHost savedCommentAboutHost = commentAboutHostService.create(createCommentAboutHostDTO);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CommentAboutHostDTO>> getAll(){
        Collection<CommentAboutHostDTO> comments = commentAboutHostService.findAll();
        if (comments == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<CommentAboutHostDTO>>(comments, HttpStatus.OK);
    }

    @GetMapping(value = "/host/{hostUserId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CommentAboutHostDTO>> getCommentsAboutHost(@PathVariable Long hostUserId){
        Collection<CommentAboutHostDTO> comments = commentAboutHostService.findByHost(hostUserId);
        if (comments == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<CommentAboutHostDTO>>(comments, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN') OR hasRole('GUEST')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CommentAboutHost> deleteCommentAboutHost(@PathVariable("id") Long id){
        commentAboutHostService.deleteById(id);
        return new ResponseEntity<CommentAboutHost>(HttpStatus.NO_CONTENT);
    }
    @PreAuthorize("hasRole('HOST')")
    @PutMapping(value = "/{id}/report/{isReported}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reportCommentAboutHost(@PathVariable Long id, @PathVariable boolean isReported) throws Exception{
        CommentAboutHost commentAboutHost = commentAboutHostService.report(id, isReported);

        if (commentAboutHost == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CommentAboutHostDTO>(new CommentAboutHostDTO(commentAboutHost), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}/approve/{isApproved}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> approveCommentAboutHost(@PathVariable Long id, @PathVariable boolean isApproved) throws Exception{
        CommentAboutHost commentAboutHost = commentAboutHostService.approve(id, isApproved);

        if (commentAboutHost == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CommentAboutHostDTO>(new CommentAboutHostDTO(commentAboutHost), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/reported",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CommentAboutHostDTO>> getReported(){
        Collection<CommentAboutHostDTO> comments = commentAboutHostService.findAllReported();
        if (comments == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<CommentAboutHostDTO>>(comments, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GUEST')")
    @GetMapping(value = "/guest/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CommentAboutHostDTO>> getCommentsAboutHostForGuest(@PathVariable Long id){
        Collection<CommentAboutHostDTO> comments = commentAboutHostService.findByGuest(id);
        if (comments == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<CommentAboutHostDTO>>(comments, HttpStatus.OK);
    }
}
