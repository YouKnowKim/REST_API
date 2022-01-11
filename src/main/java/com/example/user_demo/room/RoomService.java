package com.example.user_demo.room;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roomService")
public class RoomService {

	private static final String SAVE_PATH = "/upload";
	
	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private FileUploadService fileUploadService;

	// 방 등록
	public void registerRoom(RoomVo room) throws IOException{
		if(room.getImageBytes() != null) {
			byte[] data = room.getImageBytes();
			FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + room.getSystemFileName());
			fos.write(data);
			fos.close();
		}
		System.out.println("Service 방 이름 : " + room.getRoomName());
		this.roomDao.insertRoom(room);
	}

	// 방 삭제
	public RoomVo removeRoom(int roomNo){
		RoomVo room = retrieveRoom(roomNo);
		if(room != null) {
			this.roomDao.deleteRoom(roomNo);
			return room;
		}
		return null;
	}

	// 방 리스트 조회
	public List<RoomVo> retrieveRoomList(){
		return this.roomDao.selectRoomList();
	}

	// 방 상세조회
	public RoomVo retrieveRoom(int roomNo) {
		return this.roomDao.selectRoom(roomNo);
	}
	
	// 방 수정
	public void updateRoom(RoomVo room) {
		this.roomDao.updateRoom(room);
		System.out.println(room);
	}

}
