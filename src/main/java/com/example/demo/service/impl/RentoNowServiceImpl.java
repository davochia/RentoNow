package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
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

    @Autowired
    private PropertyReservationRepository propertyReservationRepository;




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
    public List<GuestDto> getAllGuests( ) {
        List<Guest> guests = guestRepository.findAll();
        List<GuestDto> guestDtos = new ArrayList<>();
        if(guests == null)return null;
        guests.forEach(guest -> guestDtos.add(GuestDto.getGuestDto(guest)));
        return guestDtos;
    }

    @Override
    public GuestDto editGuestById(int id, GuestDto guestDto) throws NotFoundException {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        if(optionalGuest.isEmpty())return null;

        Guest guest = optionalGuest.get();

        guest.setFirstName(guestDto.getFirstName());
        guest.setLastName(guestDto.getLastName());
        guest.setPhone(guestDto.getPhone());
        guest.setEmail(guestDto.getEmail());

        return GuestDto.getGuestDto(guestRepository.save(guest));
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
    public HostDto findHostById(int id) {
        Optional<Host> optionalHost = hostRepository.findById(id);
        if (optionalHost.isEmpty())return null;
        return HostDto.getHostDto(optionalHost.get());
    }

    @Override
    public List<HostDto> getAllHosts() {
        List<Host> hosts = hostRepository.findAll();
        List<HostDto> hostDtos = new ArrayList<>();
        if (hosts.isEmpty())return null;
        hosts.forEach(host -> hostDtos.add(HostDto.getHostDto(host)));
        return hostDtos;
    }

    @Override
    public HostDto editHostById(int id, HostDto hostDto) throws NotFoundException {
        Optional<Host> optionalHost = hostRepository.findById(id);
        if (optionalHost.isEmpty())return null;
        Host host = optionalHost.get();

        host.setFirstName(hostDto.getFirstName());
        host.setLastName(hostDto.getLastName());
        host.setPhone(hostDto.getPhone());
        host.setEmail(hostDto.getEmail());

        return HostDto.getHostDto(hostRepository.save(host));
    }

    @Override
    public boolean removeHostById(int id) throws NotFoundException {
        Optional<Host> optionalHost = hostRepository.findById(id);
        if (optionalHost.isEmpty())return false;
        Host host = optionalHost.get();

        hostRepository.delete(host);
        return true;
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




    ///////////////////// Admin ///////////////////////////////////////


    @Override
    public AdministratorDto addAdministrator(AdministratorDto administratorDto) {
        if (administratorDto == null)return null;
        Administrator administrator = AdministratorDto.getAdministrator(administratorDto);
        return AdministratorDto.getAdministratorDto(administratorRepository.save(administrator));
    }

    @Override
    public AdministratorDto findAdministratorById(int id) throws NotFoundException {
        Optional<Administrator> optionalAdministrator = administratorRepository.findById(id);
        if (optionalAdministrator.isEmpty())return null;
        return AdministratorDto.getAdministratorDto(optionalAdministrator.get());
    }

    @Override
    public List<AdministratorDto> getAllAdministrator() {
        List<Administrator> administratorList= administratorRepository.findAll();
        List<AdministratorDto> administratorDto = new ArrayList<>();
        if (administratorList.isEmpty())return null;
        administratorList.forEach(administrator -> administratorDto.add(
                AdministratorDto.getAdministratorDto(administrator)));
        return administratorDto;
    }

    @Override
    public AdministratorDto editAdministratorById(int id, AdministratorDto administratorDto) throws NotFoundException {
        Optional<Administrator> optionalAdministrator = administratorRepository.findById(id);
        if (optionalAdministrator.isEmpty())return null;
        Administrator administrator = optionalAdministrator.get();

        administrator.setFirstName(administratorDto.getFirstName());
        administrator.setLastName(administratorDto.getLastName());
        administrator.setPhone(administratorDto.getPhone());
        administrator.setEmail(administratorDto.getEmail());
        administratorRepository.save(administrator);

        return AdministratorDto.getAdministratorDto(administrator);
    }


    @Override
    public boolean removeAdministratorById(int id) throws NotFoundException {
        Optional<Administrator> optionalAdministrator = administratorRepository.findById(id);
        if (optionalAdministrator.isEmpty())return false;
        Administrator administrator = optionalAdministrator.get();
        administratorRepository.delete(administrator);
        return true;
    }



    ///////////////////// Property ///////////////////////////////////////

    @Override
    public PropertyDto addProperty(PropertyDto propertyDto) {
        if (propertyDto == null)return null;
        Property property = PropertyDto.getProperty(propertyDto);
        return PropertyDto.getPropertyDto(propertyRepository.save(property));

    }


    @Override
    public HostDto addPropertyToHost(int hostId, PropertyDto propertyDto) {
        if (hostId <= 0 )return null;
        Optional<Host> optionalHost = hostRepository.findById(hostId);
        if (optionalHost.isEmpty())return null;
        Host host = optionalHost.get();

        if(propertyDto == null)return null;
        Property property = PropertyDto.getProperty(propertyDto);
        List<Property> propertyList = new ArrayList<>();
        propertyList.add(property);

        host.setProperty(propertyList);
        return HostDto.getHostDto(host);
    }



    @Override
    public PropertyDto findPropertyById(int id) throws NotFoundException {
        if (id <= 0)return null;
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isEmpty())return null;
        Property property = optionalProperty.get();
        return PropertyDto.getPropertyDto(property);
    }

    @Override
    public List<PropertyDto> getAllProperty() {
        List<Property> propertyList = propertyRepository.findAll();
        if (propertyList.isEmpty())return null;
        List<PropertyDto> propertyDtos = new ArrayList<>();
        propertyList.forEach(property -> propertyDtos.add(PropertyDto.getPropertyDto(property)));
        return propertyDtos;
    }

    @Override
    public PropertyDto editPropertyById(int id, PropertyDto propertyDto) throws NotFoundException {
        if (id >= 0)return null;
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isEmpty())return null;
        Property property = optionalProperty.get();

        property.setId(propertyDto.getId());
        property.setTitle(propertyDto.getTitle());
        property.setLocation(propertyDto.getLocation());
        property.setDescription(propertyDto.getDescription());
        property.setPrice(propertyDto.getPrice());
        property.setAvailable_from(propertyDto.getAvailable_from());
        property.setAvailable_to(propertyDto.getAvailable_to());

        return PropertyDto.getPropertyDto(propertyRepository.save(property));
    }

    @Override
    public boolean removePropertyById(int id) throws NotFoundException {
        if (id <= 0)return false;
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isEmpty())return false;
        Property property = optionalProperty.get();
        propertyRepository.delete(property);
        return true;
    }



    ///////////////////// Property Reservation ///////////////////////////////////////


    @Override
    public PropertyReservationDto addPropertyReservationDto(PropertyReservationDto propertyReservationDto) {
        if (propertyReservationDto == null)return null;
        PropertyReservation propertyReservation = PropertyReservationDto.getPropertyReservation(
                propertyReservationDto);
        return PropertyReservationDto.getPropertyReservationDto(
                propertyReservationRepository.save(propertyReservation));
    }

    @Override
    public PropertyReservationDto findPropertyReservationDto(int id) throws NotFoundException {
        if (id <= 0)return null;
        Optional<PropertyReservation> optionalPropertyReservation = propertyReservationRepository.findById(id);
        if (optionalPropertyReservation.isEmpty())return null;
        PropertyReservation propertyReservation = optionalPropertyReservation.get();
        return PropertyReservationDto.getPropertyReservationDto(propertyReservation);
    }

    @Override
    public List<PropertyReservationDto> getAllPropertyReservationDto() {
        List<PropertyReservation> propertyReservationList = propertyReservationRepository.findAll();
        if(propertyReservationList.isEmpty())return null;
        List<PropertyReservationDto> propertyReservationDtos = new ArrayList<>();
        propertyReservationList.forEach(reservation -> propertyReservationDtos.add(
                PropertyReservationDto.getPropertyReservationDto(reservation)));
        return propertyReservationDtos;
    }

    @Override
    public PropertyReservationDto editPropertyReservationDto(int id, PropertyReservationDto propertyReservationDto) throws NotFoundException {
        if (id >= 0)return null;
        Optional<PropertyReservation> optionalPropertyReservation = propertyReservationRepository.findById(id);
        if (optionalPropertyReservation.isEmpty())return null;
        PropertyReservation propertyReservation = optionalPropertyReservation.get();

        propertyReservation.setStartDate(propertyReservationDto.getStartDate());
        propertyReservation.setEndDate(propertyReservationDto.getEndDate());
        return PropertyReservationDto.getPropertyReservationDto(propertyReservationRepository.save(propertyReservation));
    }

    @Override
    public boolean removePropertyReservationDto(int id) throws NotFoundException {
        if (id <= 0)return false;
        Optional<PropertyReservation> optionalPropertyReservation = propertyReservationRepository.findById(id);
        if (optionalPropertyReservation.isEmpty())return false;
        PropertyReservation propertyReservation = optionalPropertyReservation.get();
        propertyReservationRepository.delete(propertyReservation);
        return true;
    }
}
