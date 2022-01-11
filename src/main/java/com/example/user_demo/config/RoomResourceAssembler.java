package com.example.user_demo.config;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.user_demo.room.RoomController;
import com.example.user_demo.room.RoomVo;

/*
    HATEOAS 설정
 */

@Component
public class RoomResourceAssembler implements RepresentationModelAssembler<RoomVo, EntityModel<RoomVo>>{

	@Override
	public EntityModel<RoomVo> toModel(RoomVo room) {
		return EntityModel.of(room,
				linkTo(methodOn(RoomController.class).retrieveRoom(room.getNo())).withSelfRel(),
				linkTo(methodOn(RoomController.class).removeRoom(room.getNo())).withRel("remove-room"),
				linkTo(methodOn(RoomController.class).registerRoom(room)).withRel("register-room"),
				linkTo(methodOn(RoomController.class).retrieveAllRooms()).withRel("all-rooms"),
				linkTo(methodOn(RoomController.class).updateRoom(room)).withRel("update-room"));

	}

	@Override
	public CollectionModel<EntityModel<RoomVo>> toCollectionModel(Iterable<? extends RoomVo> rooms) {
		
		return RepresentationModelAssembler.super.toCollectionModel(rooms)
				.add(linkTo(methodOn(RoomController.class).retrieveAllRooms()).withRel("all-rooms"));
	}
	
}















