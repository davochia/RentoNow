package com.example.demo.controller;

import com.example.demo.dto.HostDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Host;
import com.example.demo.model.Property;
import com.example.demo.service.impl.HostService;

import com.example.demo.service.impl.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    link to swagger ui
    http://localhost:8080/swagger-ui/#/
 */


@RestController
@RequestMapping
public class RentonowController {
    
    @Autowired
    private HostService hostService;
    @Autowired
    private PropertyService propertyService;

    // Add new Host
    @PostMapping("/host")
    public void addHost(@RequestBody Host host){
        hostService.addHost(host);
    }

    // List of hosts
    @GetMapping("/host")
    public List<HostDto> getAllHosts() {
        return hostService.getAllHosts();
    }

    // Add new Property
    @PostMapping("/host/{id}/addProperty")
    public Object addProperty(@PathVariable int host_id, @RequestBody Property property) throws NotFoundException {
        return propertyService.addProperty(host_id, property);
    }

    //List of properties
//    @GetMapping("/host/{host_id}/properties/")
//    public List<Property> getHostProperties(@PathVariable int host_id) throws NotFoundException {
//        return hostService.getProperties(host_id);
//    }



}
