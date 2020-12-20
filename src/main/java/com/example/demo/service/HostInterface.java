package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.HostDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Host;
import com.example.demo.model.Property;

public interface HostInterface {
    
    Host addHost(Host host);
    Optional<Host> getHostById(int id);
    List<HostDto> getAllHosts();

    //List<Property> getProperties(int host_id) throws NotFoundException;
}
