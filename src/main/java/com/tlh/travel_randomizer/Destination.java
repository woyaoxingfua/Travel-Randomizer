package com.tlh.travel_randomizer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String province; // 省份

    private String city;     // 城市

    private String district; // 区县

    // ... district 字段的下面 ...

    private String attractionInfo; // 代表性景点或旅游信息

    private String tourismUrl;     // 文旅宣传链接
}