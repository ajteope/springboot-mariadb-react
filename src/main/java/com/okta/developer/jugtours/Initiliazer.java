package com.okta.developer.jugtours;

import com.okta.developer.jugtours.model.Event;
import com.okta.developer.jugtours.model.EventRepository;
import com.okta.developer.jugtours.model.Group;
import com.okta.developer.jugtours.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final GroupRepository groupRepository;
    private final EventRepository eventRepository;

    public Initializer(GroupRepository groupRepository, EventRepository eventRepository) {
        this.groupRepository = groupRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
                "Richmond JUG").forEach(name ->
                groupRepository.save(new Group(name))
        );
        
        groupRepository.findAll().forEach(System.out::println);
    }
}
