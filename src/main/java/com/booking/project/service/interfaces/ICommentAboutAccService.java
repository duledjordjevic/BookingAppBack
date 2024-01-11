package com.booking.project.service.interfaces;


import com.booking.project.dto.CommentAboutAccDTO;
import com.booking.project.dto.CommentAboutHostDTO;
import com.booking.project.dto.CreateCommentAboutAccDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.CommentAboutAcc;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface ICommentAboutAccService {

    Collection<CommentAboutAccDTO> findAll();

    CommentAboutAccDTO findById(Long id);

    CommentAboutAcc save(CommentAboutAcc commentAboutAcc) throws Exception;

    void deleteById(Long id);
    CommentAboutAcc create(CreateCommentAboutAccDTO createCommentAboutAccDTO) throws Exception;
    Collection<CommentAboutAccDTO> findByAcc(Long id);
    Collection<CommentAboutAccDTO> findAllReported();
    CommentAboutAcc approve(Long id, boolean approved) throws  Exception;
    CommentAboutAcc report(Long id, boolean reported) throws  Exception;
    Collection<Object[]> findAccommodationsByRating();
    Double findAvgRateById(Long accommodationId);
    Collection<CommentAboutAccDTO> findByGuest(Long id) throws IOException;
}
