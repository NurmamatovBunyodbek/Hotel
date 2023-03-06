package uz.pdp.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotel.entity.Room;
import uz.pdp.hotel.payload.RoomDto;
import uz.pdp.hotel.repository.HotelRepo;
import uz.pdp.hotel.repository.RoomRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomRepo roomRepo;
    @Autowired
    HotelRepo hotelRepo;

    @GetMapping
    public List<Room> roomList() {
        List<Room> roomList = roomRepo.findAll();
        return roomList;
    }

    @GetMapping("/forHotel/{hotelId}")

    public Page<Room> getRoomListForHotel(@PathVariable Integer hotelId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Room> roomPage = roomRepo.findAllByHotelId(hotelId, pageable);
        return roomPage;
    }

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto) {
        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        roomRepo.save(room);
        return "Room saved";
    }

    @PutMapping("/{id}")
    public String editingRoom(@RequestBody RoomDto roomDto, @PathVariable Integer id) {
        Optional<Room> optionalRoom = roomRepo.findById(id);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setNumber(roomDto.getNumber());
            room.setFloor(roomDto.getFloor());
            room.setSize(roomDto.getSize());
            roomRepo.save(room);
            return "Room editing";
        }
        return "Room not found";
    }

    @DeleteMapping("/{id}")
    public String deletedRoom(@PathVariable Integer id){
        roomRepo.deleteById(id);
        return "Deleted room";
    }
}
