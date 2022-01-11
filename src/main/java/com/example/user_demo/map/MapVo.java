package com.example.user_demo.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapVo {
	int map_no;
	String address_name;
    String category_group_code;
    String category_group_name;
    String category_name;
    String distance;
    String id;
    String phone;
    String place_name;
    String place_url;
    String road_address_name;
    String x;
    String y;
}
