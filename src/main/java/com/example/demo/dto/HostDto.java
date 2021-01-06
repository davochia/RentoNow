package com.example.demo.dto;


import com.example.demo.model.Host;
import lombok.Data;

@Data
public class HostDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    //private Property properties;

    public static Host getHost(HostDto hostDto) {
      Host host = new Host();
      host.setId(hostDto.getId());
      host.setFirstName(hostDto.getFirstName());
      host.setLastName(hostDto.getLastName());
      host.setPhone(hostDto.getPhone());
      host.setEmail(hostDto.getEmail());

      return host;
    }
    
    public static HostDto getHostDto(Host host){
      HostDto hostDto = new HostDto();
      hostDto.setId(host.getId());
      hostDto.setFirstName(host.getFirstName());
      hostDto.setLastName(host.getLastName());
      hostDto.setPhone(host.getPhone());
      hostDto.setEmail(host.getEmail());

      return hostDto;
    }
    
}
