package com.camper.entity;

import com.camper.constant.Permission;
import com.camper.dto.AddCampDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Camp")
@Getter
@Setter
@ToString
public class Camp {

    @Id
    @Column(name = "camp_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String campName;

    private String address;

    private String bNumber;

    private String campImg;

    private String campArea;

    private String surrounding;

    @Enumerated(EnumType.STRING)
    private Permission permission;


//    public static Camp createCamp(AddCampDto addCampDto){
//        Camp camp = new Camp();
//        camp.setCampName(addCampDto.getCampName());
//    }

}
