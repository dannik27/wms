package com.patis.wms.repository;

import com.patis.wms.entity.Request;
import com.patis.wms.entity.RequestItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RequestItemRepository extends JpaRepository<RequestItem, Long> {

    List<RequestItem> findAllByRequest_Id(long id_request);

}
