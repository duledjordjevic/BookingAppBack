package com.booking.project.service.interfaces;

import com.booking.project.dto.HostDTO;
import com.booking.project.model.Host;

import java.util.Collection;
import java.util.Optional;

public interface IHostService {
    Collection<Host> findAll();

    Optional<Host> findById(Long id);

    Host findByUser(Long id);

    HostDTO findHost(Long id);

    Collection<HostDTO> findHosts();

    Host save(Host host) throws Exception;

    void deleteById(Long id);

    Host update(HostDTO hostDTO, Long id) throws Exception;
    HostDTO addHost(HostDTO hostDTO) throws Exception;

    Long getUserId(Long id);
}
