package com.skilldistillery.travel.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.travel.entities.Country;
import com.skilldistillery.travel.repositories.CountryRepository;
import com.skilldistillery.travel.services.CountryService;

@CrossOrigin({"*", "http://localhost:4200"})
@RestController
@RequestMapping("api")
public class CountryController {

	@GetMapping("ping")
	public String ping() {
		return "pong";
	}

	@Autowired
	private CountryService svc;

	@Autowired
	private CountryRepository repo;

	@GetMapping("countries")
	public List<Country> listAllCountries() {
		System.out.println("*******************************");
		return svc.listAllCountries();
	}

	@GetMapping("countries/{id}")
	public Optional<Country> getCountryById(@PathVariable int id) {
		Optional<Country> country = repo.findById(id);

		return country;
	}

	@GetMapping("countries/visited")
	public List<Country> getCountriesVisited() {
		return repo.getCountryByVisited(true);
	}
	
	@GetMapping("countries/toVisit")
	public List<Country> getCountriesToVisit() {
		return repo.getCountryByVisited(false);
	}
	

	@GetMapping("countries/search/{name}")
	public Country getCountryByName(@PathVariable String name) {
		Country country = repo.findByName(name);
		return country;
	}

	@PostMapping("countries")
	public Country createNewCountry(@RequestBody Country country) {
		return svc.createCountry(country);
	}

	@PutMapping("countries/{id}")
	public Country replaceCountry(@RequestBody Country country, @PathVariable int id) {
		return svc.replaceCountry(id, country);
	}

	@DeleteMapping("countries/{id}")
	public Boolean deleteCountryById(@PathVariable int id, Country country) {
		return svc.deleteById(country, id);
	}

}
