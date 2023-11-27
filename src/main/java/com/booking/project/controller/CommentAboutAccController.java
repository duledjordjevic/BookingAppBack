package com.booking.project.controller;

import com.booking.project.dto.CommentAboutAccDTO;
import com.booking.project.dto.CreateCommentAboutAccDTO;
import com.booking.project.model.CommentAboutAcc;
import com.booking.project.service.interfaces.ICommentAboutAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/commentsAboutAcc")
public class CommentAboutAccController {

    @Autowired
    private ICommentAboutAccService commentAboutAccService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentAboutAcc> createCommentAboutAcc
            (@RequestBody CreateCommentAboutAccDTO createCommentAboutAccDTO) throws Exception {
        CommentAboutAcc savedCommentAboutAcc = commentAboutAccService.create(createCommentAboutAccDTO);
        return new ResponseEntity<CommentAboutAcc>(savedCommentAboutAcc, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CommentAboutAcc>> getAll(){
        Collection<CommentAboutAcc> comments = commentAboutAccService.findAll();
        if (comments == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<CommentAboutAcc>>(comments, HttpStatus.OK);
    }

    @GetMapping(value = "/acc/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CommentAboutAcc>> getCommentsAboutAcc(@PathVariable Long id){
        Collection<CommentAboutAcc> comments = commentAboutAccService.findByAcc(id);
        if (comments == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<CommentAboutAcc>>(comments, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CommentAboutAcc> deleteCommentAboutAcc(@PathVariable("id") Long id){
        commentAboutAccService.deleteById(id);
        return new ResponseEntity<CommentAboutAcc>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}/report/{isReported}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reportCommentAboutAcc(@PathVariable Long id, @PathVariable boolean isReported) throws Exception{
        CommentAboutAcc commentAboutAcc = commentAboutAccService.report(id, isReported);

        if (commentAboutAcc == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CommentAboutAccDTO>(new CommentAboutAccDTO(commentAboutAcc), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/approve/{isApproved}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> approveCommentAboutAcc(@PathVariable Long id, @PathVariable boolean isApproved) throws Exception{
        CommentAboutAcc commentAboutAcc = commentAboutAccService.approve(id, isApproved);

        if (commentAboutAcc == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CommentAboutAccDTO>(new CommentAboutAccDTO(commentAboutAcc), HttpStatus.CREATED);
    }
}
