package com.booking.project.service;

import com.booking.project.dto.HostDTO;
import com.booking.project.model.Host;
import com.booking.project.repository.inteface.IHostRepository;
import com.booking.project.service.interfaces.IHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public HostDTO findHost(Long id) {
        Optional<Host> host = repository.findById(id);

        if(host.isEmpty()) return null;

        HostDTO hostDTO = new HostDTO(host.get());
        return hostDTO;
    }
    @Override
    public Collection<HostDTO> findHosts(){
        Collection<Host> hosts = repository.findAll();
        Collection<HostDTO> hostDTOS = new ArrayList<>();
        for(Host host : hosts){
            HostDTO hostDTO = new HostDTO(host);
            hostDTOS.add(hostDTO);
        }
        return hostDTOS;
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
    public HostDTO addGuest(HostDTO hostDTO) throws Exception {
        Host host = new Host(hostDTO);
        Host savedHost = save(host);

        if(savedHost == null) return null;

        return hostDTO;
    }

    public HostDTO addHost(HostDTO hostDTO) throws Exception {
        Host host = new Host(hostDTO);
        Host savedHost = save(host);

        if(host == null) return null;

        return hostDTO;
    }
}
