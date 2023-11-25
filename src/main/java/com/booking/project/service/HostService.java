package com.booking.project.service;

import com.booking.project.dto.GuestDTO;
import com.booking.project.dto.HostDTO;
import com.booking.project.model.Guest;
import com.booking.project.model.Host;
import com.booking.project.repository.inteface.IHostRepository;
import com.booking.project.service.interfaces.IHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
@Service
public class HostService implements IHostService {
    @Autowired
    private IHostRepository repository;

    @Override
    public Collection<Host> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Host save(Host host) throws Exception {
        return repository.save(host);
    }

    @Override
    public Host update(HostDTO hostDTO, Long id) throws Exception{
        Optional<Host> hostForUpdate = findById(id);

        if(hostForUpdate.isEmpty()) return null;

        hostForUpdate.get().setName(hostDTO.getName());
        hostForUpdate.get().setLastName(hostDTO.getLastName());
        hostForUpdate.get().setAddress(hostDTO.getAddress());
        hostForUpdate.get().setPhoneNumber(hostDTO.getPhoneNumber());
        hostForUpdate.get().setNotificationEnabled(hostDTO.isNotificationEnabled());
        hostForUpdate.get().getUser().copyValues(hostDTO.getUserCredentialsDTO());

        save(hostForUpdate.get());
        return hostForUpdate.get();
    }
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
