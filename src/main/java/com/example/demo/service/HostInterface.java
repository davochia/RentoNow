package com.example.demo.service;

import com.example.demo.dto.HostDto;
import com.example.demo.model.Host;

import java.util.List;
import java.util.Optional;

public interface HostInterface {
    
    Host addHost(Host host);
    Optional<Host> getHostById(int id);
    List<HostDto> getAllHosts();

    //List<Property> getProperties(int host_id) throws NotFoundException;
}
