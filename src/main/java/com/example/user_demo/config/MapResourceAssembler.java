package com.example.user_demo.config;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.user_demo.map.MapController;
import com.example.user_demo.map.MapVo;

@Component
public class MapResourceAssembler implements RepresentationModelAssembler<MapVo, EntityModel<MapVo>>{

	@Override
	public EntityModel<MapVo> toModel(MapVo map) {
		return EntityModel.of(map,
				linkTo(methodOn(MapController.class).retrieveMap(map.getMap_no())).withSelfRel(),
				linkTo(methodOn(MapController.class).removeMap(map.getMap_no())).withRel("remove-map"),
				linkTo(methodOn(MapController.class).registerMap(map)).withRel("register-map"),
				linkTo(methodOn(MapController.class).retrieveAllMaps()).withRel("all-maps"),
				linkTo(methodOn(MapController.class).updateMap(map)).withRel("update-map"));
	}

	@Override
	public CollectionModel<EntityModel<MapVo>> toCollectionModel(Iterable<? extends MapVo> maps) {
		return RepresentationModelAssembler.super.toCollectionModel(maps)
				.add(linkTo(methodOn(MapController.class).retrieveAllMaps()).withRel("all-maps"));
	}

	
}
