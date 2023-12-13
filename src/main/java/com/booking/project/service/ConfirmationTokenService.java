package com.booking.project.service;

import com.booking.project.model.ConfirmationToken;
import com.booking.project.repository.inteface.ConfirmationTokenRepository;
import com.booking.project.service.interfaces.IConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService implements IConfirmationTokenService {
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }
    @Override
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }
    @Override
    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
    @Override
    public ConfirmationToken findTokenByUser(Long id){
        return confirmationTokenRepository.findByUserId(id);
    }
    @Override
    public void deleteById(Long id){
        confirmationTokenRepository.deleteById(id);
    }
}
