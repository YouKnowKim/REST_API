package com.example.user_demo.room;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("roomDao")
public class RoomDao {
	@Autowired
	private SqlSession sqlSession;

	//방 등록
	public void insertRoom(RoomVo room){
		this.sqlSession.insert("RoomDao.insertRoom", room);
	}

	// 방 삭제
	public void deleteRoom(int roomNo){
		this.sqlSession.delete("RoomDao.deleteRoom", roomNo);
	}

	// 방 리스트 조회
	public List<RoomVo> selectRoomList(){
		return this.sqlSession.selectList("RoomDao.selectRoomList");
	}

	// 방 상세 조회
	public RoomVo selectRoom(int roomNo){
		return this.sqlSession.selectOne("RoomDao.selectRoom", roomNo);
	}
	
	// 방 수정
	public void updateRoom(RoomVo room) {
		this.sqlSession.update("RoomDao.updateRoom", room);
	}

}
