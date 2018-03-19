package com.patis.wms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data @NoArgsConstructor
public class RequestDTOI {

    private long id;
    private List<RequestDTO.RequestItemDTO> requestItems;
    private long id_author;
    private long id_storehouse_from;
    private long id_storehouse_to;
    private LocalDate dateBegin;
    private long id_customer;

}
