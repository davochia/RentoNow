package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Property;

public interface PropertyInterface {

    Property addProperty(int host_id, Property property) throws NotFoundException;

}
