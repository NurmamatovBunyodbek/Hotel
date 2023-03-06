package uz.pdp.hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String floor;
    @Column(nullable = false)
    private String size;
    @ManyToOne
    private Hotel hotel;
}
