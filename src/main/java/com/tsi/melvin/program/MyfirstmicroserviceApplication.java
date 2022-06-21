package com.tsi.melvin.program;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*") // needed for recieving request via api
@SpringBootApplication
@RestController  // handles GET, POST, DELETE, PUT requests
@RequestMapping("/Home") //base url
public class MyfirstmicroserviceApplication {

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private FilmRepository filmRepository;



	public static void main(String[] args) {
		SpringApplication.run(MyfirstmicroserviceApplication.class, args);
	}

	public MyfirstmicroserviceApplication (ActorRepository actorRepository, AccountRepository accountRepository, FilmRepository filmRepository){
		this.actorRepository = actorRepository;
		this.accountRepository = accountRepository;
		this.filmRepository = filmRepository;
	}


	// Actors Table :
	// Get Operation


	@GetMapping("/All_Actors")
	public @ResponseBody
	Iterable<Actor>getAllActors(){
		return actorRepository.findAll();
	}

	/*

	@GetMapping("/All_Actors1")
	public @ResponseBody
	Optional<Actor> getById(@RequestParam Integer id){

		String done = "done";
		Optional<Actor> actor = this.actorRepository.findById(id);

		return actor;
	}
		*/


	//There is an alternate way to do it with repose RequestBody


	//@GetMapping("/getAActor")
	//public @ResponseBody

	//Get one Actor
	@GetMapping("/getActor")
	public ResponseEntity<Actor>getActor(@RequestParam Integer actor_id){
		Actor actor = actorRepository.findById(actor_id).orElseThrow(() -> new ResourceNotFoundException("Actor does not exist with id: " + actor_id));
		return ResponseEntity.ok(actor);
	}


	// Create New Actor
	@PostMapping("/addActor")
	public @ResponseBody
	Actor createActor(@RequestParam String first_name, String last_name) {

		Actor addActor = new Actor(first_name,last_name);
		return actorRepository.save(addActor);
	}

	// Delete Actor
	@DeleteMapping("/deleteActor")
	public ResponseEntity<Actor>  deleteActor (@RequestParam Integer actor_id){
		Actor deleteActor = actorRepository.findById(actor_id).orElseThrow(() -> new ResourceNotFoundException("Actor does not exist with id: " + actor_id));
		actorRepository.delete(deleteActor);
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

	@GetMapping("/All_Accounts")
	public @ResponseBody
	Iterable<Account>getAllAccounts(){
		return accountRepository.findAll();
	}

	@GetMapping("/getAccount")
	public ResponseEntity<Account>getAccount(@RequestParam Integer accountId){
		Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account does not exist with id: " + accountId));
		return ResponseEntity.ok(account);
	}

	@PostMapping("/addAccount")
	public @ResponseBody
	Account createAccount(@RequestParam String user_name, String password, String name, int level ) {

		Account addAccount = new Account(user_name,password,name,level);
		return accountRepository.save(addAccount);
	}

	@DeleteMapping("/deleteAccount")
	public ResponseEntity<Account>  deleteAccount (@RequestParam Integer accountId){

		Account deleteAccount = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Actor does not exist with id: " + accountId));
		accountRepository.delete(deleteAccount);
		return ResponseEntity.ok(deleteAccount);
	}

	@PutMapping("/updateAccount")
	public ResponseEntity<Account> updateAccount(@RequestParam int accountId, String user_name, String password, String name, int level){

		Account updateAccount = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account does not exist with id: " + accountId));
		updateAccount.setUsername(user_name);
		updateAccount.setLevel(level);
		updateAccount.setPassword(password);
		updateAccount.setName(name);
		accountRepository.save(updateAccount);

		return ResponseEntity.ok(updateAccount);

	}
	@GetMapping("/All_Films")
	public @ResponseBody
	Iterable<Film>getAllFilms(){

		return filmRepository.findAll();
	}

	@GetMapping("/filmByTitle")
	public @ResponseBody
	ResponseEntity<List<Film>>getFilmByTitle(@RequestParam String title){

		return new ResponseEntity<>(filmRepository.findByTitle(title), HttpStatus.OK);
	}














}
