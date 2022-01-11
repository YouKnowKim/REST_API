package com.example.user_demo.map;

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

import com.example.user_demo.config.MapResourceAssembler;

@RestController
public class MapController {
	
	@Autowired
	private MapResourceAssembler mapResourceAssembler;
	
	@Autowired
	private MapService mapService;
	
	// 숙소 등록
	@PostMapping("/api/map")
	public ResponseEntity registerMap(@RequestBody MapVo map) {
		int mapNo = this.mapService.registerMap(map);
		map.setMap_no(mapNo);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{map_no}")
				.buildAndExpand(map.getMap_no())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	// 숙소 조회
	@GetMapping("/api/maps/{map_no}")
	public EntityModel<MapVo> retrieveMap(@PathVariable Integer map_no){
		MapVo map = this.mapService.retrieveMap(map_no);
		return mapResourceAssembler.toModel(map);
	}
	
	// 숙소 모두 조회
	@GetMapping("/api/maps")
	public CollectionModel<EntityModel<MapVo>> retrieveAllMaps(){
		List<MapVo> maps = this.mapService.retrieveAllMaps();
		return mapResourceAssembler.toCollectionModel(maps);
	}
	
	// 숙소 업데이트
	@PutMapping("/api/map")
	public ResponseEntity updateMap(@RequestBody MapVo map) {
		this.mapService.updateMap(map);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{map_no}")
				.buildAndExpand(map.getMap_no())
				.toUri();
		System.out.println("숙소 변환 완료");
		
		return ResponseEntity.created(location).build();
	}
	
	// 숙소 삭제
	@DeleteMapping("/api/maps/{map_no}")
	public ResponseEntity removeMap(@PathVariable Integer map_no) {
		this.mapService.removeMap(map_no);
		MapVo mapVo = this.mapService.retrieveMap(map_no);
		
		if(mapVo != null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

}
