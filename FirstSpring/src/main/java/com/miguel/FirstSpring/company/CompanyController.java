package com.miguel.FirstSpring.company;

import java.util.List;

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

@RestController
@RequestMapping("/companies")
public class CompanyController {

	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping
	public List<Company> getAllCompanies() {
		return this.companyService.getAll();
	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody Company company) {
		this.companyService.create(company);
		return new ResponseEntity<>("Company created succesfully", HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Company company) {
		this.companyService.update(id, company);
		return new ResponseEntity<>("company updated succesfully", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable(name = "id") Long id){
		if (this.companyService.delete(id)) {
			return new ResponseEntity<>("Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Something went wrong", HttpStatus.NOT_FOUND);
	}
	
}
