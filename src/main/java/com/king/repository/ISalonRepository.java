package com.king.repository;

import com.king.model.Member;
import com.king.model.Salon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ISalonRepository extends CrudRepository<Salon, Integer> {
    Long countBySalonId(int id);
}
