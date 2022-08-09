package com.camper.entity;

import com.camper.constant.Permission;
import com.camper.constant.RoomStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "room")
@Getter
@Setter
@ToString
public class Room {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String roomName;    // 객실이름

    private int roomPrice;  // 객실가격

    private String rommInfo;    // 객실정보

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus; // 객실 예약 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camp_id")
    private Camp camp;


}
