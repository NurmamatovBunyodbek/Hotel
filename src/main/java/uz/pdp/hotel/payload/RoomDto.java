package uz.pdp.hotel.payload;

import lombok.Data;

@Data
public class RoomDto {

    private String number;
    private String floor;
    private String size;
    private Integer hotelId;


}
