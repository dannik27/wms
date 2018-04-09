package com.patis.wms.repository;

import com.patis.wms.entity.Company;
import com.patis.wms.entity.TransportCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportCompanyRepository extends JpaRepository<TransportCompany, Long> {


}
