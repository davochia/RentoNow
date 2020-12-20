package com.example.demo.service.impl;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Host;
import com.example.demo.model.Property;
import com.example.demo.repository.HostRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.PropertyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService implements PropertyInterface {

    @Autowired
    PropertyRepository propertyRepository;
    @Autowired
    private HostRepository hostRepository;

    @Override
    public Property addProperty(int host_id, Property property) throws NotFoundException {
        Optional<Host> byId = hostRepository.findById(host_id);
        if ( !byId.isPresent() ){
            throw new NotFoundException("Host not found");
        }
        Host host = byId.get();
        property.setHost(host);

        return propertyRepository.save(property);
    }

}
