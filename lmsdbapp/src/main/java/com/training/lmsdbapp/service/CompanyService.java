package com.training.lmsdbapp.service;

import java.sql.SQLException;
import java.util.List;

import com.training.lmsdbapp.model.Company;

public interface CompanyService {
	public List<Company> getAllCompany() throws ClassNotFoundException, SQLException;
    public Company getCompanybyId(String companyId) throws ClassNotFoundException, SQLException;
    public void saveCompany (Company com) throws ClassNotFoundException, SQLException;
    public void updateCompany(Company company) throws ClassNotFoundException, SQLException;
    public void deleteCompany(Company company) throws ClassNotFoundException, SQLException;
}
