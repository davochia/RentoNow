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

    @Override // Todos -> validate exception
    public GuestDto addNewGuest(GuestDto guestDto)   {
        if(guestDto == null)return null;
        Guest guest = GuestDto.getGuest(guestDto);
        return GuestDto.getGuestDto(guestRepository.save(guest));
    }

    @Override
    public GuestDto findGuestById(int id) throws NotFoundException {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        if(optionalGuest.isEmpty()) throw new NotFoundException("Guest with " + id + " not found!");
        Guest guest = optionalGuest.get();
        return GuestDto.getGuestDto(guest);
    }

    @Override
    public List<GuestDto> getAllGuests( ) {
        List<Guest> guests = guestRepository.findAll();
        List<GuestDto> guestDtos = new ArrayList<>();
        if(guests == null)return null;
        guests.forEach(guest -> guestDtos.add(GuestDto.getGuestDto(guest)));
        return guestDtos;
    }

    @Override // Todos -> validate exception
    public GuestDto editGuestById(int id, GuestDto guestDto) throws NotFoundException {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        if(optionalGuest.isEmpty())throw new NotFoundException("Guest with " + id + " not found!");
        Guest guest = optionalGuest.get();

        if(guestDto == null)return null;

        guest.setFirstName(guestDto.getFirstName());
        guest.setLastName(guestDto.getLastName());
        guest.setPhone(guestDto.getPhone());
        guest.setEmail(guestDto.getEmail());

        return GuestDto.getGuestDto(guestRepository.save(guest));
    }

    @Override // To do -> exception for remove guest error if property is reserved by guest
    public boolean removeGuestById(int id) throws NotFoundException {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        if(optionalGuest.isEmpty())throw new NotFoundException("Guest with " + id + " not found!");

        Guest guest = optionalGuest.get();

        if(!guest.getPropertyReservation().isEmpty()){
            guest.getPropertyReservation().forEach(reservation -> propertyReservationRepository.delete(reservation));
        }
        guestRepository.delete(guest);
        return true;
    }


    ///////////////////// Host ///////////////////////////////////////


    @Override // Todos -> validate exception
    public HostDto addNewHost(HostDto hostDto) {
        if (hostDto == null)return null;
        Host host = hostDto.getHost(hostDto);
        return HostDto.getHostDto(hostRepository.save(host));
    }

    @Override
    public HostDto findHostById(int id) throws NotFoundException{
        Optional<Host> optionalHost = hostRepository.findById(id);
        if (optionalHost.isEmpty())throw new NotFoundException("Host with " + id + " not found!");
        Host host = optionalHost.get();
        return HostDto.getHostDto(host);
    }

    @Override
    public List<HostDto> getAllHosts() {
        List<Host> hosts = hostRepository.findAll();
        List<HostDto> hostDtos = new ArrayList<>();
        if (hosts.isEmpty())return null;
        hosts.forEach(host -> hostDtos.add(HostDto.getHostDto(host)));
        return hostDtos;
    }

    @Override // Todos -> validate exception
    public HostDto editHostById(int id, HostDto hostDto) throws NotFoundException {
        Optional<Host> optionalHost = hostRepository.findById(id);
        if (optionalHost.isEmpty())throw new NotFoundException("Host with " + id + " not found!");
        Host host = optionalHost.get();

        host.setFirstName(hostDto.getFirstName());
        host.setLastName(hostDto.getLastName());
        host.setPhone(hostDto.getPhone());
        host.setEmail(hostDto.getEmail());

        return HostDto.getHostDto(hostRepository.save(host));
    }

    @Override // To do -> exception for remove host if property is reserved by guest
    public boolean removeHostById(int id) throws NotFoundException {
        Optional<Host> optionalHost = hostRepository.findById(id);
        if (optionalHost.isEmpty()) throw new NotFoundException("Host with " + id + " not found!");
        Host host = optionalHost.get();

        if (!host.getProperties().isEmpty()){
            host.getProperties().forEach(property -> propertyRepository.delete(property));
        }
        hostRepository.delete(host);
        return true;
    }


    @Override
    public List<PropertyDto> HostProperties(int id) throws NotFoundException{
        Optional<Host> optionalHost = hostRepository.findById(id);
        if (optionalHost.isEmpty())throw new NotFoundException("Host with " + id + " not found!");

        Host host = optionalHost.get();
        List<PropertyDto> propertyDtos = new ArrayList<>();
        host.getProperties().forEach(property -> propertyDtos.add(PropertyDto.getPropertyDto(property)));
        return propertyDtos;
    }




    ///////////////////// Admin ///////////////////////////////////////


    @Override // Todos -> validate exception
    public AdministratorDto addAdministrator(AdministratorDto administratorDto) {
        if (administratorDto == null)return null;
        Administrator administrator = AdministratorDto.getAdministrator(administratorDto);
        return AdministratorDto.getAdministratorDto(administratorRepository.save(administrator));
    }

    @Override
    public AdministratorDto findAdministratorById(int id) throws NotFoundException {
        Optional<Administrator> optionalAdministrator = administratorRepository.findById(id);
        if (optionalAdministrator.isEmpty())throw new NotFoundException("Administrator with " + id + " not found!");
        return AdministratorDto.getAdministratorDto(optionalAdministrator.get());
    }

    @Override
    public List<AdministratorDto> getAllAdministrators() {
        List<Administrator> administratorList= administratorRepository.findAll();
        List<AdministratorDto> administratorDto = new ArrayList<>();
        if (administratorList.isEmpty())return null;
        administratorList.forEach(administrator -> administratorDto.add(
                AdministratorDto.getAdministratorDto(administrator)));
        return administratorDto;
    }

    @Override // Todos -> validate exception
    public AdministratorDto editAdministratorById(int id, AdministratorDto administratorDto) throws NotFoundException {
        Optional<Administrator> optionalAdministrator = administratorRepository.findById(id);
        if (optionalAdministrator.isEmpty())throw new NotFoundException("Administrator with " + id + " not found!");
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
        if (optionalAdministrator.isEmpty())throw new NotFoundException("Administrator with " + id + " not found!");
        Administrator administrator = optionalAdministrator.get();
        administratorRepository.delete(administrator);
        return true;
    }



    ///////////////////// Property ///////////////////////////////////////

//    @Override
//    public PropertyDto addProperty(PropertyDto propertyDto) {
//        if (propertyDto == null)return null;
//        Property property = PropertyDto.getProperty(propertyDto);
//        return PropertyDto.getPropertyDto(propertyRepository.save(property));
//
//    }


    @Override // Todos -> validate exception
    public PropertyDto addPropertyByHostId(int hostId, PropertyDto propertyDto) throws NotFoundException {
        Optional<Host> optionalHost = hostRepository.findById(hostId);
        if (optionalHost.isEmpty() || propertyDto == null)throw new NotFoundException("Host or property not found!");
        Host host = optionalHost.get();
        Property property = PropertyDto.getProperty(propertyDto);
        property.setHost(host);
        propertyRepository.save(property);
        return PropertyDto.getPropertyDto(property);
    }



    @Override
    public PropertyDto findPropertyById(int id) throws NotFoundException {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isEmpty())throw new NotFoundException("Property with " + id + " not found!");
        Property property = optionalProperty.get();
        return PropertyDto.getPropertyDto(property);
    }

    @Override
    public List<PropertyDto> getAllProperties() {
        List<Property> propertyList = propertyRepository.findAll();
        if (propertyList.isEmpty())return null;
        List<PropertyDto> propertyDtos = new ArrayList<>();
        propertyList.forEach(property -> propertyDtos.add(PropertyDto.getPropertyDto(property)));
        return propertyDtos;
    }

    @Override // To do -> fix edit start/end date error, validate exception
    public PropertyDto editPropertyById(int id, PropertyDto propertyDto) throws NotFoundException {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isEmpty())throw new NotFoundException("Property with " + id + " not found!");
        Property property = optionalProperty.get();

        property.setId(propertyDto.getId());
        property.setTitle(propertyDto.getTitle());
        property.setLocation(propertyDto.getLocation());
        property.setDescription(propertyDto.getDescription());
        property.setPrice(propertyDto.getPrice());
        property.setAvailableStart(propertyDto.getAvailableStart());
        property.setAvailableEnd(propertyDto.getAvailableEnd());

        return PropertyDto.getPropertyDto(propertyRepository.save(property));
    }

    @Override
    public boolean removePropertyById(int id) throws NotFoundException {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isEmpty())throw new NotFoundException("Property with " + id + " not found!");
        Property property = optionalProperty.get();
        propertyRepository.delete(property);
        return true;
    }



    ///////////////////// Property Reservation ///////////////////////////////////////


    @Override  // To do -> fix duplicate reservation date entries for same property
    public PropertyReservationDto addReservation(PropertyReservationDto propertyReservationDto, int guestId, int propertyId) throws NotFoundException{

        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        Optional<Guest> optionalGuest = guestRepository.findById(guestId);

        if (optionalProperty.isEmpty() || optionalGuest.isEmpty())throw new NotFoundException("Property or Host not found!");
        Property property = optionalProperty.get();
        Guest guest = optionalGuest.get();

        Long propertyAvailable = Math.abs(property.getAvailableStart().getTime() - property.getAvailableEnd().getTime());
        Long reservationDate = Math.abs(propertyReservationDto.getStartDate().getTime() - propertyReservationDto.getEndDate().getTime());

        if (propertyAvailable < reservationDate)return null;

        PropertyReservation propertyReservation = new PropertyReservation();
        propertyReservation.setStartDate(propertyReservationDto.getStartDate());
        propertyReservation.setEndDate(propertyReservationDto.getEndDate());
        propertyReservation.setGuest(guest);
        propertyReservation.setProperty(property);

        return PropertyReservationDto.getPropertyReservationDto(
                    propertyReservationRepository.save(propertyReservation));


    }

    @Override
    public PropertyReservationDto findReservation(int id) throws NotFoundException {
        Optional<PropertyReservation> optionalPropertyReservation = propertyReservationRepository.findById(id);
        if (optionalPropertyReservation.isEmpty())throw new NotFoundException("Reservation with " + id + " not found!");
        PropertyReservation propertyReservation = optionalPropertyReservation.get();
        return PropertyReservationDto.getPropertyReservationDto(propertyReservation);
    }

    @Override
    public List<PropertyReservationDto> getAllReservation() {
        List<PropertyReservation> propertyReservationList = propertyReservationRepository.findAll();
        if(propertyReservationList.isEmpty())return null;
        List<PropertyReservationDto> propertyReservationDtos = new ArrayList<>();
        propertyReservationList.forEach(reservation -> propertyReservationDtos.add(
                PropertyReservationDto.getPropertyReservationDto(reservation)));
        return propertyReservationDtos;
    }

    @Override // To do -> fix edit date entries, validate exception
    public PropertyReservationDto editReservation(int id, PropertyReservationDto propertyReservationDto) throws NotFoundException {
        Optional<PropertyReservation> optionalPropertyReservation = propertyReservationRepository.findById(id);
        if (optionalPropertyReservation.isEmpty())throw new NotFoundException("Reservation with " + id + " not found!");
        PropertyReservation propertyReservation = optionalPropertyReservation.get();

        propertyReservation.setStartDate(propertyReservationDto.getStartDate());
        propertyReservation.setEndDate(propertyReservationDto.getEndDate());
        return PropertyReservationDto.getPropertyReservationDto(propertyReservationRepository.save(propertyReservation));
    }

    @Override
    public boolean removeReservation(int id) throws NotFoundException {
        Optional<PropertyReservation> optionalPropertyReservation = propertyReservationRepository.findById(id);
        if (optionalPropertyReservation.isEmpty())throw new NotFoundException("Reservation with " + id + " not found!");
        PropertyReservation propertyReservation = optionalPropertyReservation.get();
        propertyReservationRepository.delete(propertyReservation);
        return true;
    }
}
