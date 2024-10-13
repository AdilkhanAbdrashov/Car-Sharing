package persistance.repository;

import businesslayer.Company;

import java.util.List;

public interface CompanyRepository {

    void createCompany(Company company);

    Company readCompany(int companyId);

    List<Company> readCompanies();
}
