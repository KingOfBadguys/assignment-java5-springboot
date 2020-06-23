package com.king.service.salon;

import com.king.model.Salon;

import java.util.List;

public interface ISalonService {
    public List<Salon> findAll();

    public Salon findById(int id);

    public Salon findByName(String name);

    void save(Salon salon);

    void remove(int id);

    boolean existsById(int id);

    Long countByID(int id);
}
