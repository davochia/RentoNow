package com.example.demo.service.impl;

import com.example.demo.dto.GuestDto;
import com.example.demo.dto.HostDto;
import com.example.demo.model.Guest;
import com.example.demo.model.Host;
import com.example.demo.model.Property;
import com.example.demo.repository.AdministratorRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.HostRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.RentoNowServiceI;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentoNowServiceImpl implements RentoNowServiceI {
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private AdministratorRepository administratorRepository;



    ///////////////////// Guest ///////////////////////////////////////

    @Override
    public GuestDto addGuest(GuestDto guestDto)   {
        if(guestDto == null)return null;
        Guest guest = GuestDto.getGuest(guestDto);
        return GuestDto.getGuestDto(guestRepository.save(guest));
    }

    @Override
    public GuestDto findGuestById(int id) throws NotFoundException {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        if(optionalGuest.isEmpty())return null;
        return GuestDto.getGuestDto(optionalGuest.get());
    }

    @Override
    public List<GuestDto> getAllGuest( ) {
        List<Guest> guests = guestRepository.findAll();
        List<GuestDto> guestDtos = new ArrayList<>();
        if(guests == null)return null;
        guests.forEach(guest -> guestDtos.add(GuestDto.getGuestDto(guest)));
        return guestDtos;
    }

    @Override
    public GuestDto editGuest(int id, GuestDto guestDto) throws NotFoundException {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        if(optionalGuest.isEmpty())return null;

        Guest guest = optionalGuest.get();

        guest.setFirstName(guestDto.getFirstName());
        guest.setLastName(guestDto.getLastName());
        guest.setPhone(guestDto.getPhone());
        guest.setEmail(guestDto.getEmail());

        guestRepository.save(guest);
        return GuestDto.getGuestDto(guest);
    }

    @Override
    public boolean removeGuestById(int id) throws NotFoundException {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        if(optionalGuest.isEmpty())return false;
        Guest guest = optionalGuest.get();
        guestRepository.delete(guest);
        return true;
    }


    ///////////////////// Host ///////////////////////////////////////



    @Override
    public HostDto addHost(HostDto hostDto) {
        if (hostDto == null)return null;
        Host host = hostDto.getHost(hostDto);
        return HostDto.getHostDto(hostRepository.save(host));
    }

    @Override
    public HostDto getHostById(int id) {
        Optional<Host> optionalHost = hostRepository.findById(id);
        if (!optionalHost.isPresent())return null;
        return HostDto.getHostDto(optionalHost.get());
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


    ///////////////////// Property ///////////////////////////////////////

    @Override
    public Property addProperty(int host_id, Property property) throws NotFoundException {
        return null;
    }


    ///////////////////// Admin ///////////////////////////////////////



}
