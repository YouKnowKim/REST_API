package com.example.user_demo.room;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.user_demo.config.RoomResourceAssembler;

@RestController
public class RoomController {

	@Autowired
	private RoomResourceAssembler roomResourceAssembler;
	@Autowired
	private RoomService roomService;
	
	//전체 방 조회
	@GetMapping("/api/rooms")
	public CollectionModel<EntityModel<RoomVo>> retrieveAllRooms(){
		List<RoomVo> rooms = roomService.retrieveRoomList();
		return roomResourceAssembler.toCollectionModel(rooms);
	}
	
	//방 상세 조회
	@GetMapping("/api/rooms/{roomNo}")
	public EntityModel<RoomVo> retrieveRoom(@PathVariable Integer roomNo){
		RoomVo room = roomService.retrieveRoom(roomNo);
		if(room == null) {
			
		}
		return roomResourceAssembler.toModel(room);
	}
	
	//방 등록
	@PostMapping("/api/room")
	public ResponseEntity registerRoom(@RequestBody RoomVo room) {
		System.out.println("방이름 : " + room.getRoomName());
		try {
			this.roomService.registerRoom(room);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{roomNo}")
				.buildAndExpand(room.getNo())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	//방 삭제
	@DeleteMapping("/api/rooms/{roomNo}")
	public ResponseEntity removeRoom(@PathVariable Integer roomNo) {
		RoomVo room = this.roomService.removeRoom(roomNo);
		
		if(room != null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PutMapping("/api/room")
	public ResponseEntity updateRoom(@RequestBody RoomVo room) {
		this.roomService.updateRoom(room);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{roomNo}")
				.buildAndExpand(room.getNo())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
}













