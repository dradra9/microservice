package com.tsi.melvin.program;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*") // needed for recieving request via api
@SpringBootApplication
@RestController  // handles GET, POST, DELETE, PUT requests
@RequestMapping("/Home") //base url
public class MyfirstmicroserviceApplication {

	@Autowired
	private ActorRepository actorRepository;


	public static void main(String[] args) {
		SpringApplication.run(MyfirstmicroserviceApplication.class, args);
	}

	public MyfirstmicroserviceApplication (ActorRepository actorRepository){
		this.actorRepository = actorRepository;
	}


	// Actors Table :
	// Get Operation


	@GetMapping("/All_Actors")
	public @ResponseBody
	Iterable<Actor>getAllActors(){
		return actorRepository.findAll();
	}

/*
	@GetMapping("/All_Actors/")
	public @ResponseBody
	Iterable<Actor>getById(@RequestParam Integer id){

		Optional<Actor> actor = this.actorRepository.findById(id);

		return actor;
	}
	*/

	//Get one Actor
	@GetMapping("/getActor")
	public ResponseEntity<Actor>getActor(@RequestParam Integer id){
		Actor actor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor does not exist with id: " + id));
		return ResponseEntity.ok(actor);
	}


	// Create New Actor
	@PostMapping("/addActor")
	public @ResponseBody
	Actor createEmployee(@RequestParam String first_name, String last_name) {

		Actor addActor = new Actor(first_name,last_name);
		return actorRepository.save(addActor);
	}

	// Delete Actor
	@DeleteMapping("/deleteActor")
	public ResponseEntity<Actor>  deleteActor (@RequestParam Integer id){
		Actor deleteActor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor does not exist with id: " + id));
		actorRepository.deleteById(id);
		return ResponseEntity.ok(deleteActor);
	}

	@PutMapping("/updateActor")
	public ResponseEntity<Actor> updateActor(@RequestParam Integer id, String first_name, String last_name){
		Actor updateActor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor does not exist with id: " + id));
		updateActor.setFirst_name(first_name);
		updateActor.setLast_name(last_name);
		actorRepository.save(updateActor);

		return ResponseEntity.ok(updateActor);


	}




	/*
	@DeleteMapping("/deleteActor")
	public @ResponseBody
	Actor deleteEmployee (@RequestParam Integer id){
		Optional<Actor> deleteActor = actorRepository.findById(id);
		actorRepository.deleteById(id);

		return deleteActor ;

	}  */







}
