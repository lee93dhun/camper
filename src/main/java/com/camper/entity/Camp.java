package com.camper.entity;

import com.camper.constant.Permission;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "camp")
@Getter
@Setter
@ToString
public class Camp {

    @Id
    @Column(name = "camp_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 캠핑장 아이디

    private String campName; // 캠핑장 이름

    private String address; // 캠핑장 주소

    private String bNumber; // 사업자 번호

    private String campArea;// 지역

    private String surrounding; // 주변환경

    private String inTime; // 입실시간

    private String outTime; // 퇴실시간

    private String campInfo; // 캠핑장 정보

    @Enumerated(EnumType.STRING)
    private Permission permission; //승인 허가



}
