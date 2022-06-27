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

import java.sql.Blob;
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

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private LanguageRepository languageRepository;



	public static void main(String[] args) {
		SpringApplication.run(MyfirstmicroserviceApplication.class, args);
	}

	public MyfirstmicroserviceApplication (ActorRepository actorRepository, AccountRepository accountRepository, FilmRepository filmRepository,CategoryRepository categoryRepository,LanguageRepository languageRepository){
		this.actorRepository = actorRepository;
		this.accountRepository = accountRepository;
		this.filmRepository = filmRepository;
		this.categoryRepository = categoryRepository;
		this.languageRepository = languageRepository;
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


	@PostMapping("/addFilm")
	public @ResponseBody
	Film createFilm(@RequestParam String title, String description, int release_year, int length,String url, Blob film_image){
		Film addFilm = new Film(title,description,release_year,length,url,film_image);
		return filmRepository.save(addFilm);
	}

	@DeleteMapping("/deleteFilm")
	public ResponseEntity<Film>  deleteFilm (@RequestParam Integer filmId){

		Film deleteFilm = filmRepository.findById(filmId).orElseThrow(() -> new ResourceNotFoundException("Actor does not exist with id: " + filmId));
		filmRepository.delete(deleteFilm);
		return ResponseEntity.ok(deleteFilm);
	}

	@PutMapping("/updateFilm")
	public ResponseEntity<Film> updateFilm(@RequestParam Integer film_id, String title, String description, int release_year, int length,String url, Blob film_image){

		Film updateFilm = filmRepository.findById(film_id).orElseThrow(() -> new ResourceNotFoundException("Account does not exist with id: " + film_id));
		updateFilm.setFilm_image(film_image);
		updateFilm.setDescription(description);
		updateFilm.setLength(length);
		updateFilm.setRelease_year(release_year);
		updateFilm.setTitle(title);
		updateFilm.setUrl(url);
		filmRepository.save(updateFilm);

		return ResponseEntity.ok(updateFilm);

	}

	@GetMapping("Get_All_Category")
	public @ResponseBody Iterable<Category> getAllCategory(){
		return categoryRepository.findAll();
	}

	@GetMapping("Get_Category_By_Id")
	public ResponseEntity<Category>getACategory(@RequestParam Integer id){
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category does not exist with ID: " +id));
		return ResponseEntity.ok(category);
	}

	@PostMapping("Post_A_Category")
	public @ResponseBody ResponseEntity<Category> addCategory(@RequestBody Category addCategory){
		categoryRepository.save(addCategory);
		return ResponseEntity.ok(addCategory);
	}

	@PutMapping("Put_A_Category")
	public @ResponseBody ResponseEntity<Category> updateCategory(@RequestBody Category putCategory){
		Category updateCategory = categoryRepository.findById(putCategory.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category does not exit with the given ID. "));
		updateCategory.name = putCategory.name;
		categoryRepository.save(updateCategory);
		return ResponseEntity.ok(updateCategory);
	}

	@DeleteMapping("/Delete_A_Category")
	public ResponseEntity<Category> deleteCategory(@RequestBody Category delCategory){
		Category deleteCategory = categoryRepository.findById(delCategory.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category does not exist with given ID"));
		categoryRepository.deleteById(deleteCategory.getCategoryId());
		return ResponseEntity.ok(deleteCategory);
	}

	@GetMapping("Get_All_Language")
	public @ResponseBody Iterable<Language> getAllLanguages(){
		return languageRepository.findAll();
	}

	@GetMapping("Get_Language")
	public ResponseEntity<Language> getLanguageName(@RequestParam int id){
		Language language = languageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Language does not exist with ID: " +id));
		return ResponseEntity.ok(language);
	}

	@PostMapping("Post_A_Language")
	public @ResponseBody ResponseEntity<Language> addLanguage(@RequestBody Language addLanguage){
		languageRepository.save(addLanguage);
		return ResponseEntity.ok(addLanguage);
	}

	@PutMapping("Put_A_Language")
	public @ResponseBody ResponseEntity<Language> updateLanguage(@RequestBody Language putLanguage){
		Language updateLanguage = languageRepository.findById(putLanguage.getLanguageId()).orElseThrow(() -> new ResourceNotFoundException("Language does not exit with the given ID. "));
		updateLanguage.name = putLanguage.name;
		languageRepository.save(updateLanguage);
		return ResponseEntity.ok(updateLanguage);
	}

	@DeleteMapping("/Delete_A_Language")
	public ResponseEntity<Language> deleteLanguage(@RequestBody Language delLanguage){
		Language deleteLanguage = languageRepository.findById(delLanguage.getLanguageId()).orElseThrow(() -> new ResourceNotFoundException("Language does not exist with given ID"));
		languageRepository.deleteById(deleteLanguage.getLanguageId());
		return ResponseEntity.ok(deleteLanguage);
	}


















}
