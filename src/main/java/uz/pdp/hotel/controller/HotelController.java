package uz.pdp.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotel.entity.Hotel;
import uz.pdp.hotel.payload.HotelDto;
import uz.pdp.hotel.repository.HotelRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepo hotelRepo;

    @GetMapping
    public List<Hotel> getHotel() {
        List<Hotel> hotelList = hotelRepo.findAll();
        return hotelList;
    }

    @PostMapping
    public String addHotel(@RequestBody HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        hotel.setId(hotel.getId());
        hotelRepo.save(hotel);
        return "Hotel saved";
    }

    @PutMapping("/{id}")
    public String editHotel(@PathVariable Integer id, @RequestBody HotelDto hotelDto) {

        Optional<Hotel> optionalHotel = hotelRepo.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            hotel.setName(hotelDto.getName());
            hotelRepo.save(hotel);
            return "Hotel editing";
        }
        return "Hotel not found";
    }

    @DeleteMapping("/{id}")
    public String deletedHotel(@PathVariable Integer id) {

        hotelRepo.deleteById(id);

        return "Hotel deleted";

    }
}
