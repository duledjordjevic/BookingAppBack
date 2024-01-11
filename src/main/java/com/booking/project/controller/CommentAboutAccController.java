package com.booking.project.controller;

import com.booking.project.dto.CommentAboutAccDTO;
import com.booking.project.dto.CommentAboutHostDTO;
import com.booking.project.dto.CreateCommentAboutAccDTO;
import com.booking.project.model.CommentAboutAcc;
import com.booking.project.service.interfaces.ICommentAboutAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/api/commentsAboutAcc")
public class CommentAboutAccController {

    @Autowired
    private ICommentAboutAccService commentAboutAccService;
    @PreAuthorize("hasRole('GUEST')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentAboutAcc> createCommentAboutAcc
            (@RequestBody CreateCommentAboutAccDTO createCommentAboutAccDTO) throws Exception {
        CommentAboutAcc savedCommentAboutAcc = commentAboutAccService.create(createCommentAboutAccDTO);
        return new ResponseEntity<CommentAboutAcc>(savedCommentAboutAcc, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('GUEST')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CommentAboutAccDTO>> getAll(){
        Collection<CommentAboutAccDTO> comments = commentAboutAccService.findAll();
        if (comments == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<CommentAboutAccDTO>>(comments, HttpStatus.OK);
    }

    @GetMapping(value = "/acc/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CommentAboutAccDTO>> getCommentsAboutAcc(@PathVariable Long id){
        Collection<CommentAboutAccDTO> comments = commentAboutAccService.findByAcc(id);
        if (comments == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<CommentAboutAccDTO>>(comments, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN') OR hasRole('GUEST')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CommentAboutAcc> deleteCommentAboutAcc(@PathVariable("id") Long id){
        commentAboutAccService.deleteById(id);
        return new ResponseEntity<CommentAboutAcc>(HttpStatus.NO_CONTENT);
    }
    @PreAuthorize("hasRole('GUEST') OR hasRole('HOST')")
    @PutMapping(value = "/{id}/report/{isReported}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reportCommentAboutAcc(@PathVariable Long id, @PathVariable boolean isReported) throws Exception{
        CommentAboutAcc commentAboutAcc = commentAboutAccService.report(id, isReported);

        if (commentAboutAcc == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CommentAboutAccDTO>(new CommentAboutAccDTO(commentAboutAcc), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}/approve/{isApproved}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> approveCommentAboutAcc(@PathVariable Long id, @PathVariable boolean isApproved) throws Exception{
        CommentAboutAcc commentAboutAcc = commentAboutAccService.approve(id, isApproved);

        if (commentAboutAcc == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CommentAboutAccDTO>(new CommentAboutAccDTO(commentAboutAcc), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/reported",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CommentAboutAccDTO>> getReported(){
        Collection<CommentAboutAccDTO> comments = commentAboutAccService.findAllReported();
        if (comments == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<CommentAboutAccDTO>>(comments, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GUEST')")
    @GetMapping(value = "/guest/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CommentAboutAccDTO>> getCommentsAboutAccForGuest(@PathVariable Long id) throws IOException {
        Collection<CommentAboutAccDTO> comments = commentAboutAccService.findByGuest(id);
        if (comments == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<CommentAboutAccDTO>>(comments, HttpStatus.OK);
    }
}
