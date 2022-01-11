package com.example.user_demo.map;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("mapDao")
public class MapDao {
	@Autowired
	private SqlSession sqlSession;
	
	// 숙소 등록
	public int insertMap(MapVo map) {
		this.sqlSession.insert("MapDao.insertMap", map);
		return this.sqlSession.selectOne("MapDao.selectLastInsertID");
	}
	
	// 숙소 1개 조회
	public MapVo selectMap(int map_no) {
		return this.sqlSession.selectOne("MapDao.selectMap", map_no);
	}
	
	// 숙소 모두 조회
	public List<MapVo> selectAllMaps(){
		return this.sqlSession.selectList("MapDao.selectAllMaps");
	}
	
	// 숙소 업데이트
	public void updateMap(MapVo map) {
		this.sqlSession.update("MapDao.updateMap", map);
	}
	
	// 숙소 삭제
	public void deleteMap(int map_no) {
		this.sqlSession.delete("MapDao.deleteMap", map_no);
	}

}
