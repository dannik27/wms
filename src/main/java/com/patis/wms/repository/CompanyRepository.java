package com.patis.wms.repository;

import com.patis.wms.entity.Company;
import com.patis.wms.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {


}
