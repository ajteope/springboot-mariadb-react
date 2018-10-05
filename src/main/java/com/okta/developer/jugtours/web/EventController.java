package com.okta.developer.jugtours.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okta.developer.jugtours.model.Event;
import com.okta.developer.jugtours.model.EventRepository;
/*
 * @Author AJ
 */

@RestController
@RequestMapping("/api")
class EventController {
	
	private final Logger log = LoggerFactory.getLogger(GroupController.class);
	private EventRepository eventRepository;
	
	public EventController(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	@GetMapping("/events")
	Collection<Event> events(){
		return eventRepository.findAll();
	}
	
	@GetMapping("/event/{id}")
	ResponseEntity<?> getEvent(@PathVariable Long id){
		Optional<Event> event = eventRepository.findById(id);
		return event.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/event")
	ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) throws URISyntaxException{
		log.info("Request to create event: {}", event);
		Event result = eventRepository.save(event);
		return ResponseEntity.created(new URI("/api/group/" + result.getId())).body(result);
	}
	
	@PutMapping("/event/{id}")
	ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event){
		event.setId(id);
		log.info("Request to update event: {}", event);
		Event result = eventRepository.save(event);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/event/{id}")
	public ResponseEntity<?> deleteGroup(@PathVariable Long id){
		log.info("Request to delete event: {}", id);
		eventRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
}
