package com.patis.wms.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class TaskType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;



}
