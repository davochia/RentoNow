package com.example.demo.service.impl;


import com.example.demo.dto.*;
import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.RentoNowServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import java.time.LocalDate;

@Service
public class RentoNowServiceImpl implements RentoNowServiceI {
    //private static final Logger logger = LoggerFactory.getLogger(RentoNowServiceImpl.class);

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


    @Autowired
    private _ImageDBRepository imageDBRepository;


    ///////////////////// Guest ///////////////////////////////////////

    @Override
    public GuestDto addNewGuest(GuestDto guestDto)throws ValidationException {
        if (guestDto == null) throw new ValidationException("Null guest was inserted");
        Guest guest = GuestDto.getGuest(guestDto);
        return GuestDto.getGuestDto(guestRepository.save(guest));
    }

    @Override
    public GuestDto findGuestById(Integer id) throws GuestNotFoundException {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        return optionalGuest.map(GuestDto::getGuestDto).orElse(null);
    }

    @Override
    public List<GuestDto> getAllGuests() {
        List<Guest> guests = guestRepository.findAll();
        List<GuestDto> guestDtos = new ArrayList<>();
        if (guests == null) return null;
        guests.forEach(guest -> guestDtos.add(GuestDto.getGuestDto(guest)));
        return guestDtos;
    }

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

    @Override
    public HostDto addNewHost(HostDto hostDto) throws ValidationException {
        if (hostDto == null) throw new ValidationException("Null host was inserted");
        Host host = hostDto.getHost(hostDto);
        return HostDto.getHostDto(hostRepository.save(host));
    }

    @Override
    public HostDto findHostById(Integer id) {
        Optional<Host> optionalHost = hostRepository.findById(id);
        if (optionalHost.isEmpty()) return null;
        Host host = optionalHost.get();
        return HostDto.getHostDto(host);
    }

    @Override
    public List<HostDto> getAllHosts() {
        List<Host> hosts = hostRepository.findAll();
        List<HostDto> hostDtos = new ArrayList<>();
        if (hosts.isEmpty()) return null;
        hosts.forEach(host -> hostDtos.add(HostDto.getHostDto(host)));
        return hostDtos;
    }

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

    @Override
    public AdministratorDto addAdministrator(AdministratorDto administratorDto) throws ValidationException {
        if (administratorDto == null) throw new ValidationException("Null administrator was inserted");
        Administrator administrator = AdministratorDto.getAdministrator(administratorDto);
        return AdministratorDto.getAdministratorDto(administratorRepository.save(administrator));
    }

    @Override
    public AdministratorDto findAdministratorById(Integer id)  {
        Optional<Administrator> optionalAdministrator = administratorRepository.findById(id);
        if (optionalAdministrator.isEmpty());
        return AdministratorDto.getAdministratorDto(optionalAdministrator.get());
    }

    @Override
    public List<AdministratorDto> getAllAdministrators() {
        List<Administrator> administratorList = administratorRepository.findAll();
        List<AdministratorDto> administratorDto = new ArrayList<>();
        if (administratorList.isEmpty()) return null;
        administratorList.forEach(administrator -> administratorDto.add(
                AdministratorDto.getAdministratorDto(administrator)));
        return administratorDto;
    }

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


    @Override
    public boolean removeAdministratorById(Integer id)  {
        Optional<Administrator> optionalAdministrator = administratorRepository.findById(id);
        if (optionalAdministrator.isEmpty()) return false;
        Administrator administrator = optionalAdministrator.get();
        administratorRepository.delete(administrator);
        return true;
    }


    ///////////////////// Property ///////////////////////////////////////



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

    @Override
    public void saveImageToProperty(String path, Integer id) {
        propertyRepository.saveImageToProperty(path, id);
    }

    @Override
    public PropertyDto findPropertyById(Integer id) throws PropertyNotFoundException {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isEmpty()) return null;
        Property property = optionalProperty.get();
        return PropertyDto.getPropertyDto(property);
    }

    @Override
    public List<PropertyDto> getAllProperties() {
        List<Property> propertyList = propertyRepository.findAll();
        if (propertyList.isEmpty()) return null;
        List<PropertyDto> propertyDtos = new ArrayList<>();
        propertyList.forEach(property -> propertyDtos.add(PropertyDto.getPropertyDto(property)));
        return propertyDtos;
    }


    @Override
    public List<PropertyDto> getPropertiesByPriceLocation(Double minPrice, Double maxPrice, String location) {
        List<Property> findProperties = propertyRepository.findAll();
        List<PropertyDto> properties = new ArrayList<>();

        findProperties.forEach(property -> {
            if (property.getLocation().toLowerCase().contains(location.toLowerCase())){
                if(property.getPrice() >= minPrice && property.getPrice() <= maxPrice){
                    properties.add(PropertyDto.getPropertyDto(property));
                }
            }return;
        });
        return properties;
    }


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

    @Override
    public boolean removePropertyById(Integer id) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isEmpty()) return false;
        Property property = optionalProperty.get();
        propertyRepository.delete(property);
        return true;
    }


    ///////////////////// Property Reservation ///////////////////////////////////////

    @Override  // To do -> fix duplicate reservation date entries for same property
    public ResponseEntity addReservation(PropertyReservationDto propertyReservationDto, int guestId, int propertyId) throws NotFoundException, InvalidDataException {

        //Get timestamps of selected dates
        Timestamp start = Timestamp.valueOf(propertyReservationDto.getStartDate().atStartOfDay());
        Timestamp end   = Timestamp.valueOf(propertyReservationDto.getEndDate().atStartOfDay());

        if( start.after(end) )
            return new ResponseEntity("Dates are not valid", HttpStatus.BAD_REQUEST);

        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        Optional<Guest> optionalGuest       = guestRepository.findById(guestId);

        if (optionalProperty.isEmpty() || optionalGuest.isEmpty())
            return new ResponseEntity("Property or Guest not found", HttpStatus.BAD_REQUEST);

        Property property = optionalProperty.get();
        AtomicReference<Boolean> reservationOverride = new AtomicReference<>(false);

        //Check with Property dates
        Timestamp startPropertyDate = Timestamp.valueOf(property.getAvailableStart().atStartOfDay());
        Timestamp endPropertyDate   = Timestamp.valueOf(property.getAvailableEnd().atStartOfDay());

        if ( start.before(startPropertyDate) || end.after(endPropertyDate) ){
            return new ResponseEntity("Property is not available on the selected dates", HttpStatus.BAD_REQUEST);
        }

        //Check if dates are overriding
        List<PropertyReservationDto> propertyReservationDtos = new ArrayList<>();
        propertyReservationRepository.propertyReservations(propertyId).forEach(reservation -> propertyReservationDtos.add(
                PropertyReservationDto.getPropertyReservationDto(reservation))
        );

        propertyReservationDtos.forEach(value -> {
            Timestamp start2 = Timestamp.valueOf(value.getStartDate().atStartOfDay());
            Timestamp end2   = Timestamp.valueOf(value.getEndDate().atStartOfDay());

            if ( end.before(start2) || start.after(end2) ){
            }else{
                reservationOverride.set(true);
                return;
            }
        });

        if (reservationOverride.get())
            return new ResponseEntity("There is another reservation been made", HttpStatus.BAD_REQUEST);
        //Check if dates are overriding

        Guest guest = optionalGuest.get();

        PropertyReservation propertyReservation = new PropertyReservation();
        propertyReservation.setStartDate(propertyReservationDto.getStartDate());
        propertyReservation.setEndDate(propertyReservationDto.getEndDate());
        propertyReservation.setGuest(guest);
        propertyReservation.setProperty(property);

        //Store reservation
        propertyReservationRepository.save(propertyReservation);

        return new ResponseEntity("The reservation stored", HttpStatus.OK);

    }


    @Override
    public PropertyReservationDto findReservation(Integer id) {
        Optional<PropertyReservation> optionalPropertyReservation = propertyReservationRepository.findById(id);
        if (optionalPropertyReservation.isEmpty()) return null;
        PropertyReservation propertyReservation = optionalPropertyReservation.get();
        return PropertyReservationDto.getPropertyReservationDto(propertyReservation);
    }

    @Override
    public List<PropertyReservationDto> getAllReservation() {
        List<PropertyReservation> propertyReservationList = propertyReservationRepository.findAll();
        if (propertyReservationList.isEmpty()) return null;
        List<PropertyReservationDto> propertyReservationDtos = new ArrayList<>();
        propertyReservationList.forEach(reservation -> propertyReservationDtos.add(
                PropertyReservationDto.getPropertyReservationDto(reservation)));
        return propertyReservationDtos;
    }

//////////////////////////////// Statistics ////////////////////////////////////

    @Override  // Get reservations by guest
    public List<PropertyReservationDto> getReservationByGuest(Integer guestId) {
        Optional<Guest> optionalGuest = guestRepository.findById(guestId);
        if (optionalGuest.isEmpty()) return null;
        Guest guest = optionalGuest.get();

        List<PropertyReservation> propertyReservationList = propertyReservationRepository.findAll();
        if (propertyReservationList.isEmpty()) return null;

        List<PropertyReservationDto> propertyReservationDtos = new ArrayList<>();

        propertyReservationList.forEach(reservation -> {
            if(reservation.getGuest().equals(guest)){
                propertyReservationDtos.add(
                        PropertyReservationDto.getPropertyReservationDto(reservation));
            }
        });
        return propertyReservationDtos;
    }


    @Override  // Get reservations by host
    public List<PropertyReservationDto> getReservationByHost(Integer hostId) {
        Optional<Host> optionalHost = hostRepository.findById(hostId);
        if (optionalHost.isEmpty()) return null;
        Host host = optionalHost.get();

        List<PropertyReservation> propertyReservationList = propertyReservationRepository.findAll();
        if (propertyReservationList.isEmpty()) return null;

        List<PropertyReservationDto> propertyReservationDtos = new ArrayList<>();

        propertyReservationList.forEach(reservation -> {
            host.getProperties().forEach(property -> {
                if(reservation.getProperty().getId() == property.getId()){
                    propertyReservationDtos.add(PropertyReservationDto.getPropertyReservationDto(reservation));
                } });
        });
        return propertyReservationDtos;
    }

    @Override  // Get reservations by property
    public List<PropertyReservationDto> getReservationByProperty(Integer propertyId) {
        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        if (optionalProperty.isEmpty()) return null;
        Property property = optionalProperty.get();

        List<PropertyReservation> propertyReservationList = propertyReservationRepository.findAll();
        if (propertyReservationList.isEmpty()) return null;

        List<PropertyReservationDto> propertyReservationDtos = new ArrayList<>();

        propertyReservationList.forEach(reservation -> {
            if(reservation.getProperty().getId() == property.getId()){
                propertyReservationDtos.add(PropertyReservationDto.getPropertyReservationDto(reservation));
            }
        });
        return propertyReservationDtos;
    }



    @Override // To do -> fix edit date entries
    public PropertyReservationDto editReservation(Integer id, PropertyReservationDto propertyReservationDto) {
        Optional<PropertyReservation> optionalPropertyReservation = propertyReservationRepository.findById(id);
        if (optionalPropertyReservation.isEmpty()) return null;

        PropertyReservation propertyReservation = optionalPropertyReservation.get();

        propertyReservation.setStartDate(propertyReservationDto.getStartDate());
        propertyReservation.setEndDate(propertyReservationDto.getEndDate());

        return PropertyReservationDto.getPropertyReservationDto(propertyReservationRepository.save(propertyReservation));
    }


    @Override
    public boolean removeReservation(Integer id) {
        Optional<PropertyReservation> optionalPropertyReservation = propertyReservationRepository.findById(id);
        if (optionalPropertyReservation.isEmpty())return false;
        PropertyReservation propertyReservation = optionalPropertyReservation.get();
        propertyReservationRepository.delete(propertyReservation);
        return true;
    }


    //////////////////////////// Images ///////////////////////////

    public ImageDB store(MultipartFile file) throws IOException {
        ImageDB image = new ImageDB();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // Check if the file's name contains invalid characters
            if(fileName.contains(".."))return null;
            image.setName(fileName);
            image.setType(file.getContentType());
            image.setData(file.getBytes());
            return imageDBRepository.save(image);

    }

    public ImageDB getFile(Integer id) {
        return imageDBRepository.findById(id).get();
    }

    public Stream<ImageDB> getAllFiles() {
        return imageDBRepository.findAll().stream();
    }
}
