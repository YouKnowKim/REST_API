package com.example.user_demo.map;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mapService")
public class MapService {

	@Autowired
	private MapDao mapDao;
	
	// 숙소 등록
	public int registerMap(MapVo map) {
		return this.mapDao.insertMap(map);
	}
	
	// 숙소 1개 조회
	public MapVo retrieveMap(int map_no) {
		return this.mapDao.selectMap(map_no);
	}
	
	// 숙소 모두 조회
	public List<MapVo> retrieveAllMaps(){
		return this.mapDao.selectAllMaps();
	}
	
	// 숙소 업데이트
	public void updateMap(MapVo map) {
		this.mapDao.updateMap(map);
	}
	
	// 숙소 삭제
	public void removeMap(int map_no) {
		this.mapDao.deleteMap(map_no);
	}
}
