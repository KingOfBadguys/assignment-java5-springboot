package com.king.repository;

import com.king.model.Member;
import com.king.model.Salon;
import org.springframework.data.repository.CrudRepository;

public interface ISalonRepository extends CrudRepository<Salon,Integer> {
}
