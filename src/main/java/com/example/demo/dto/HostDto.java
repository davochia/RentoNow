package com.example.demo.dto;

import com.example.demo.model.Host;
import lombok.Data;

import java.util.List;

@Data
public class HostDto {

    private int id;
    private String firstName;
    private String lastName;
    private String phone;

    private List<String> properties;
    
    public static Host getHost(HostDto hostDto) {
      Host host = new Host();
      host.setId(hostDto.getId());
      host.setFirstName(hostDto.getFirstName());
      host.setLastName(hostDto.getLastName());
      host.setPhone(hostDto.getPhone());
      return host;
    }
    
    public static HostDto getHostDto(Host host){
      HostDto hostDto = new HostDto();
      hostDto.setId(host.getId());
      hostDto.setFirstName(host.getFirstName());
      hostDto.setLastName(host.getLastName());
      hostDto.setPhone(host.getPhone());

//      if (host.getProperties() != null){
//          hostDto.properties = new ArrayList<>();
//          host.getProperties().forEach(property ->  hostDto.properties.add(property.getTitle()));
//      }

      return hostDto;
    }
    
}
