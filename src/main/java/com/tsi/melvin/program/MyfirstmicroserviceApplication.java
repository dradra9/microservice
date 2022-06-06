package com.tsi.melvin.program;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

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



	@GetMapping("/All_Actors")
	public @ResponseBody
	Iterable<Actor>getAllActors(){
		return actorRepository.findAll();
	}

}
