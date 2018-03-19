package com.patis.wms.repository;

import com.patis.wms.entity.Company;
import com.patis.wms.entity.Distribution;
import org.springframework.data.repository.CrudRepository;

public interface DistributionRepository extends CrudRepository<Distribution, Long> {


}
