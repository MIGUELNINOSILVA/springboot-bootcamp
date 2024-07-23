package com.miguel.FirstSpring.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.miguel.FirstSpring.company.Company;
import com.miguel.FirstSpring.company.CompanyRepository;
import com.miguel.FirstSpring.company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

	private CompanyRepository companyRepository;
	
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Company> getAll() {
		return this.companyRepository.findAll();
	}

	@Override
	public boolean update(Long id, Company company) {
		Optional<Company> companyOptional = this.companyRepository.findById(id);
		if (companyOptional.isPresent()) {
			Company companyToUpdate = companyOptional.get();
			companyToUpdate.setDescription(company.getDescription());
			companyToUpdate.setName(company.getName());
			companyToUpdate.setJobs(company.getJobs());
			this.companyRepository.save(companyToUpdate);
			return true;
		}
		return false;
	}

	@Override
	public void create(Company company) {
		this.companyRepository.save(company);
	}

	@Override
	public boolean delete(Long id) {
		if (this.companyRepository.existsById(id)) {
			this.companyRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
