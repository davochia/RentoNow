package com.example.demo.service.impl;

import com.example.demo.dto.HostDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Host;
import com.example.demo.model.Property;
import com.example.demo.service.HostInterface;
import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HostService implements HostInterface {

    @Autowired
    private HostRepository hostRepository;

    @Override
    public Host addHost(Host host) {
        return hostRepository.save(host);
    }

    @Override
    public Optional<Host> getHostById(int id) {
        return hostRepository.findById(id);
    }

    @Override
    public List<HostDto> getAllHosts() {
        List<Host> hosts = hostRepository.findAll();
        List<HostDto> hostDtos = new ArrayList<>();

        hosts.forEach(host -> hostDtos.add(new HostDto().getHostDto(host)));
        return hostDtos;
    }

//    @Override
//    public List<Property> getProperties(int host_id) throws NotFoundException {
//        Optional<Host> byId = hostRepository.findById(host_id);
//        if (!byId.isPresent()){
//            throw new NotFoundException("Host not found");
//        }
//        Host host = byId.get();
//        return host.getProperties();
//    }

}
