package com.schlaepfer.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.schlaepfer.entity.BinaryData;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

@SpringComponent
@ViewScope
public interface BinaryDataRepository extends PagingAndSortingRepository<BinaryData, Integer> {

	List<BinaryData> findByLangId(@Param("langid") int langId);
}
