package com.example.demo.service.impl;


import com.example.demo.dto.*;
import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.RentoNowServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class RentoNowServiceImpl implements RentoNowServiceI {

    ///////////////////// Repositories ///////////////////////////////////////

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

    @Caching(evict = {@CacheEvict(value="guests", allEntries=true), @CacheEvict(value="guests", allEntries=true)})
    @Override
    public GuestDto addNewGuest(GuestDto guestDto)throws ValidationException {
        if (guestDto == null) throw new ValidationException("Null guest was inserted");
        Guest guest = GuestDto.getGuest(guestDto);
        return GuestDto.getGuestDto(guestRepository.save(guest));
    }

    @Cacheable(value = "guest")
    @Override
    public GuestDto findGuestById(Integer id)  {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        return optionalGuest.map(GuestDto::getGuestDto).orElse(null);
    }

    @Cacheable(value = "guests")
    @Override
    public List<GuestDto> getAllGuests() {
        List<Guest> guests = guestRepository.findAll();
        List<GuestDto> guestDtos = new ArrayList<>();
        if (guests == null) return null;
        guests.forEach(guest -> guestDtos.add(GuestDto.getGuestDto(guest)));
        return guestDtos;
    }

    @Caching(evict = {@CacheEvict(value="guests", allEntries=true), @CacheEvict(value="guests", allEntries=true)})
    @Override
    public GuestDto editGuestById(Integer id, GuestDto guestDto) throws ValidationException {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        if (optionalGuest.isEmpty()) return null;
        Guest guest = optionalGuest.get();

        if (guestDto == null) throw new ValidationException("Null guest was added");

        guest.setFirstName(guestDto.getFirstName());
        guest.setLastName(guestDto.getLastName());
        guest.setPhone(guestDto.getPhone());
        guest.setEmail(guestDto.getEmail());

        return GuestDto.getGuestDto(guestRepository.save(guest));
    }

    @Caching(evict = {@CacheEvict(value="guests", allEntries=true), @CacheEvict(value="guests", allEntries=true)})
    @Override // To do -> exception for remove guest error if property is reserved by guest
    public boolean removeGuestById(Integer id)  {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        if (optionalGuest.isEmpty())return false;

        Guest guest = optionalGuest.get();

        if (!guest.getPropertyReservation().isEmpty()) {
            guest.getPropertyReservation().forEach(reservation -> propertyReservationRepository.delete(reservation));
        }
        guestRepository.delete(guest);
        return true;
    }


    ///////////////////// Host ///////////////////////////////////////

    @Caching(evict = {@CacheEvict(value="hosts", allEntries=true), @CacheEvict(value="host", allEntries=true)})
    @Override
    public HostDto addNewHost(HostDto hostDto) throws ValidationException {
        if (hostDto == null) throw new ValidationException("Null host was inserted");
        Host host = HostDto.getHost(hostDto);
        return HostDto.getHostDto(hostRepository.save(host));
    }

    @Cacheable("host")
    @Override
    public HostDto findHostById(Integer id) {
        Optional<Host> optionalHost = hostRepository.findById(id);
        if (optionalHost.isEmpty()) return null;
        Host host = optionalHost.get();
        return HostDto.getHostDto(host);
    }

    @Cacheable("hosts")
    @Override
    public List<HostDto> getAllHosts() {
        List<Host> hosts = hostRepository.findAll();
        List<HostDto> hostDtos = new ArrayList<>();
        if (hosts.isEmpty()) return null;
        hosts.forEach(host -> hostDtos.add(HostDto.getHostDto(host)));
        return hostDtos;
    }

    @Caching(evict = {@CacheEvict(value="hosts", allEntries=true), @CacheEvict(value="host", allEntries=true)})
    @Override
    public HostDto editHostById(Integer id, HostDto hostDto) throws ValidationException {
        Optional<Host> optionalHost = hostRepository.findById(id);
        if (optionalHost.isEmpty()) return null;
        Host host = optionalHost.get();

        if (hostDto == null) throw new ValidationException("Null host was inserted");

        host.setFirstName(hostDto.getFirstName());
        host.setLastName(hostDto.getLastName());
        host.setPhone(hostDto.getPhone());
        host.setEmail(hostDto.getEmail());

        return HostDto.getHostDto(hostRepository.save(host));
    }

    @Caching(evict = {@CacheEvict(value="hosts", allEntries=true), @CacheEvict(value="host", allEntries=true)})
    @Override // To do -> exception for remove host if property is reserved by guest
    public boolean removeHostById(Integer id) {
        Optional<Host> optionalHost = hostRepository.findById(id);
        if (optionalHost.isEmpty()) return false;
        Host host = optionalHost.get();

        if (!host.getProperties().isEmpty()) {
            host.getProperties().forEach(property -> propertyRepository.delete(property));
        }
        hostRepository.delete(host);
        return true;
    }


    @CacheEvict(value="host_properties", allEntries=true)
    @Override
    public List<PropertyDto> HostProperties(Integer id) {
        Optional<Host> optionalHost = hostRepository.findById(id);
        if (optionalHost.isEmpty()) return null;

        Host host = optionalHost.get();

        List<PropertyDto> propertyDtos = new ArrayList<>();
        host.getProperties().forEach(property -> propertyDtos.add(PropertyDto.getPropertyDto(property)));
        return propertyDtos;
    }


    ///////////////////// Admin ///////////////////////////////////////

    @Caching(evict = {@CacheEvict(value="admins", allEntries=true), @CacheEvict(value="admin", allEntries=true)})
    @Override
    public AdministratorDto addAdministrator(AdministratorDto administratorDto) throws ValidationException {
        if (administratorDto == null) throw new ValidationException("Null administrator was inserted");
        Administrator administrator = AdministratorDto.getAdministrator(administratorDto);
        return AdministratorDto.getAdministratorDto(administratorRepository.save(administrator));
    }

    @Cacheable("admin")
    @Override
    public AdministratorDto findAdministratorById(Integer id)  {
        Optional<Administrator> optionalAdministrator = administratorRepository.findById(id);
        if (optionalAdministrator.isEmpty())return null;
        return AdministratorDto.getAdministratorDto(optionalAdministrator.get());
    }

    @Cacheable("admins")
    @Override
    public List<AdministratorDto> getAllAdministrators() {
        List<Administrator> administratorList = administratorRepository.findAll();
        List<AdministratorDto> administratorDto = new ArrayList<>();
        if (administratorList.isEmpty()) return null;
        administratorList.forEach(administrator -> administratorDto.add(
                AdministratorDto.getAdministratorDto(administrator)));
        return administratorDto;
    }

    @Caching(evict = {@CacheEvict(value="admins", allEntries=true), @CacheEvict(value="admin", allEntries=true)})
    @Override
    public AdministratorDto editAdministratorById(Integer id, AdministratorDto administratorDto) throws ValidationException, AdministratorNotFoundException {
        Optional<Administrator> optionalAdministrator = administratorRepository.findById(id);
        if (optionalAdministrator.isEmpty()) return null;
        Administrator administrator = optionalAdministrator.get();

        if (administratorDto == null)throw new ValidationException("Null administrator was inserted");

        administrator.setFirstName(administratorDto.getFirstName());
        administrator.setLastName(administratorDto.getLastName());
        administrator.setPhone(administratorDto.getPhone());
        administrator.setEmail(administratorDto.getEmail());
        administratorRepository.save(administrator);

        return AdministratorDto.getAdministratorDto(administrator);
    }


    @Caching(evict = {@CacheEvict(value="admins", allEntries=true), @CacheEvict(value="admin", allEntries=true)})
    @Override
    public boolean removeAdministratorById(Integer id)  {
        Optional<Administrator> optionalAdministrator = administratorRepository.findById(id);
        if (optionalAdministrator.isEmpty()) return false;
        Administrator administrator = optionalAdministrator.get();
        administratorRepository.delete(administrator);
        return true;
    }


    ///////////////////// Property ///////////////////////////////////////



    @CacheEvict(value="host_properties", allEntries=true)
    @Override
    public PropertyDto addPropertyByHostId(Integer hostId, PropertyDto propertyDto) throws ValidationException {
        Optional<Host> optionalHost = hostRepository.findById(hostId);
        if (optionalHost.isEmpty()) return null;
        Host host = optionalHost.get();

        if (propertyDto == null)throw new ValidationException("Null property was inserted");
        Property property = PropertyDto.getProperty(propertyDto);
        property.setHost(host);
        propertyRepository.save(property);


        return PropertyDto.getPropertyDto(property);
    }

    @Cacheable("property")
    @Override
    public PropertyDto findPropertyById(Integer id) throws PropertyNotFoundException {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isEmpty()) return null;
        Property property = optionalProperty.get();
        return PropertyDto.getPropertyDto(property);
    }

    @Cacheable("properties")
    @Override
    public List<PropertyDto> getAllProperties() {
        List<Property> propertyList = propertyRepository.findAll();
        if (propertyList.isEmpty()) return null;
        List<PropertyDto> propertyDtos = new ArrayList<>();
        propertyList.forEach(property -> propertyDtos.add(PropertyDto.getPropertyDto(property)));
        return propertyDtos;
    }


    @Cacheable("filtered_properties")
    @Override
    public List<PropertyDto> getPropertiesByPriceLocation(Double minPrice, Double maxPrice, String location, LocalDate startDate, LocalDate endDate) {
        List<Property> findProperties = propertyRepository.findAll();
        if(findProperties.isEmpty())return null;
        List<PropertyDto> properties = new ArrayList<>();


        findProperties.forEach(property -> {

            if (property.getLocation().toLowerCase().contains(location.toLowerCase()) ||
                    (property.getPrice() >= minPrice && property.getPrice() <= maxPrice)){
                properties.add(PropertyDto.getPropertyDto(property));
            }
            return;
        });
        return properties;
    }

    @Caching(evict = {@CacheEvict(value="host_properties", allEntries=true),
            @CacheEvict(value="properties", allEntries=true),
            @CacheEvict(value="property", allEntries=true),
            @CacheEvict(value="filtered_properties", allEntries=true)})
    @Override // To do -> fix edit start/end date not change
    public PropertyDto editPropertyById(Integer id, PropertyDto propertyDto) throws ValidationException {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isEmpty()) return null;
        Property property = optionalProperty.get();

        if (propertyDto == null)throw new ValidationException("Null property was inserted");

        property.setId(propertyDto.getId());
        property.setTitle(propertyDto.getTitle());
        property.setLocation(propertyDto.getLocation());
        property.setDescription(propertyDto.getDescription());
        property.setPrice(propertyDto.getPrice());
        property.setAvailableStart(propertyDto.getAvailableStart());
        property.setAvailableEnd(propertyDto.getAvailableEnd());

        return PropertyDto.getPropertyDto(propertyRepository.save(property));
    }

    @Caching(evict = {@CacheEvict(value="host_properties", allEntries=true),
            @CacheEvict(value="properties", allEntries=true),
            @CacheEvict(value="property", allEntries=true),
            @CacheEvict(value="filtered_properties", allEntries=true)})
    @Override
    public boolean removePropertyById(Integer id) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isEmpty()) return false;
        Property property = optionalProperty.get();
        propertyRepository.delete(property);
        return true;
    }


    ///////////////////// Property Reservation ///////////////////////////////////////

    @Caching(evict = {@CacheEvict(value="reservations", allEntries=true)})
    @Override  // To do -> fix duplicate reservation date entries for same property
    public ResponseEntity addReservation(PropertyReservationDto propertyReservationDto, Integer guestId, Integer propertyId) throws NotFoundException, InvalidDataException {

        //Get timestamps of selected dates
        Timestamp start = Timestamp.valueOf(propertyReservationDto.getStartDate().atStartOfDay());
        Timestamp end   = Timestamp.valueOf(propertyReservationDto.getEndDate().atStartOfDay());

        if( start.after(end) ) return new ResponseEntity("Dates are not valid", HttpStatus.BAD_REQUEST);

        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        Optional<Guest> optionalGuest       = guestRepository.findById(guestId);

        if (optionalProperty.isEmpty() || optionalGuest.isEmpty())
            return new ResponseEntity("Property or Guest not found", HttpStatus.BAD_REQUEST);

        Property property = optionalProperty.get();
        Guest guest = optionalGuest.get();

        AtomicReference<Boolean> reservationOverride = new AtomicReference<>(false);

        //Check with Property dates
        Timestamp startPropertyDate = Timestamp.valueOf(property.getAvailableStart().atStartOfDay());
        Timestamp endPropertyDate   = Timestamp.valueOf(property.getAvailableEnd().atStartOfDay());

        if ( start.before(startPropertyDate) || end.after(endPropertyDate) ){
            return new ResponseEntity("Property is not available on the selected dates", HttpStatus.BAD_REQUEST);
        }

        //Check if dates are overriding
        List<PropertyReservationDto> propertyReservationDtos = new ArrayList<>();
//        propertyReservationRepository.propertyReservations(propertyId).forEach(reservation -> propertyReservationDtos.add(
//                PropertyReservationDto.getPropertyReservationDto(reservation))
//        );

        propertyReservationRepository.findAll().forEach(reservation -> {
            if (reservation.getProperty().getId() != propertyId) {
                propertyReservationDtos.add(PropertyReservationDto.getPropertyReservationDto(reservation));
            }
        });

        propertyReservationDtos.forEach(value -> {
            Timestamp start2 = Timestamp.valueOf(value.getStartDate().atStartOfDay());
            Timestamp end2   = Timestamp.valueOf(value.getEndDate().atStartOfDay());

            if ( end.before(start2) || start.after(end2) ){

            }else{
                reservationOverride.set(true);
                return;
            }
        });

        if (reservationOverride.get()) return new ResponseEntity("There is another reservation been made", HttpStatus.BAD_REQUEST);
        //Check if dates are overriding

        PropertyReservation propertyReservation = new PropertyReservation();
        propertyReservation.setStartDate(propertyReservationDto.getStartDate());
        propertyReservation.setEndDate(propertyReservationDto.getEndDate());
        propertyReservation.setGuest(guest);
        propertyReservation.setProperty(property);

        if(propertyReservationDto.getPayment().equalsIgnoreCase("CASH") ||
                propertyReservationDto.getPayment().equalsIgnoreCase("CARD")){

            propertyReservation.setPayment(propertyReservationDto.getPayment());
            property.setNumOfBookings(property.getNumOfBookings() + 1);
        }else{
            propertyReservation.setPayment("Other Payment");
            property.setNumOfBookings(property.getNumOfBookings() + 1);
        }

        //Store reservation
        propertyReservationRepository.save(propertyReservation);

        return new ResponseEntity("The reservation stored", HttpStatus.OK);

    }


    @Cacheable("reservation")
    @Override
    public PropertyReservationDto findReservation(Integer id) {
        Optional<PropertyReservation> optionalPropertyReservation = propertyReservationRepository.findById(id);
        if (optionalPropertyReservation.isEmpty()) return null;
        PropertyReservation propertyReservation = optionalPropertyReservation.get();
        return PropertyReservationDto.getPropertyReservationDto(propertyReservation);
    }

    @Cacheable("reservations")
    @Override
    public List<PropertyReservationDto> getAllReservation() {
        List<PropertyReservation> propertyReservationList = propertyReservationRepository.findAll();
        if (propertyReservationList.isEmpty()) return null;
        List<PropertyReservationDto> propertyReservationDtos = new ArrayList<>();
        propertyReservationList.forEach(reservation -> propertyReservationDtos.add(
                PropertyReservationDto.getPropertyReservationDto(reservation)));
        return propertyReservationDtos;
    }


    @Caching(evict = {@CacheEvict(value="reservations", allEntries=true),
            @CacheEvict(value="reservation", allEntries=true),
            @CacheEvict(value="reservations_by_quest", allEntries=true),
            @CacheEvict(value="reservations_by_host", allEntries=true),
            @CacheEvict(value="reservations_by_property", allEntries=true)})
    @Override
    public ResponseEntity editReservation(Integer id, PropertyReservationDto propertyReservationDto) throws NotFoundException, InvalidDataException {
        Optional<PropertyReservation> optionalPropertyReservation = propertyReservationRepository.findById(id);
        if (optionalPropertyReservation.isEmpty())return null;
        PropertyReservation propertyReservation = optionalPropertyReservation.get();

        return addReservation(propertyReservationDto, propertyReservation.getGuest().getId(), propertyReservation.getProperty().getId());
    }


    @Caching(evict = {@CacheEvict(value="reservations", allEntries=true),
            @CacheEvict(value="reservation", allEntries=true),
            @CacheEvict(value="reservations_by_quest", allEntries=true),
            @CacheEvict(value="reservations_by_host", allEntries=true),
            @CacheEvict(value="reservations_by_property", allEntries=true)})
    @Override
    public boolean removeReservation(Integer id) {
        Optional<PropertyReservation> optionalPropertyReservation = propertyReservationRepository.findById(id);
        if (optionalPropertyReservation.isEmpty())return false;
        PropertyReservation propertyReservation = optionalPropertyReservation.get();
        propertyReservationRepository.delete(propertyReservation);
        return true;
    }


    //////////////////////////////// Statistics ////////////////////////////////////

    @Cacheable("reservations_by_quest")
    @Override  // Get reservations by guest
    public int getReservationByGuest(Integer guestId) throws GuestNotFoundException {
        List<PropertyReservationDto> propertyReservationDtos = new ArrayList<>();
        propertyReservationRepository.findAll().forEach(reservation -> {
            if (reservation.getGuest().getId() != guestId)return;
            propertyReservationDtos.add(PropertyReservationDto.getPropertyReservationDto(reservation));
        });
        return propertyReservationDtos.size();
    }

    @Cacheable("reservations_by_host")
    @Override  // Get reservations by host
    public int getReservationByHost(Integer hostId) {
        List<PropertyReservationDto> propertyReservationDtos = new ArrayList<>();
        propertyReservationRepository.findAll().forEach(reservation -> {
            if (reservation.getProperty().getHost().getId() == hostId)return;
            propertyReservationDtos.add(PropertyReservationDto.getPropertyReservationDto(reservation));
        });
        return propertyReservationDtos.size();
    }

    @Cacheable("reservations_by_property")
    @Override  // Get reservations by property
    public int getReservationByProperty(Integer propertyId) {

        List<PropertyReservationDto> propertyReservationDtos = new ArrayList<>();
        propertyReservationRepository.findAll().forEach(reservation -> {
            if (reservation.getProperty().getId() == propertyId)return;
            propertyReservationDtos.add(PropertyReservationDto.getPropertyReservationDto(reservation));

        });
        return propertyReservationDtos.size();
    }


    //////////////////////////// Images ///////////////////////////

    @CacheEvict(value="host_properties", allEntries=true)
    @Override
    public void saveImageToProperty(String path, Integer id) {
        propertyRepository.saveImageToProperty(path, id);
    }
}
