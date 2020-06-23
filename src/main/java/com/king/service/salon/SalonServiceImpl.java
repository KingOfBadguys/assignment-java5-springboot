package com.king.service.salon;

import com.king.model.Salon;
import com.king.repository.ISalonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SalonServiceImpl implements ISalonService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ISalonRepository iSalonRepository;

    @Override
    public List<Salon> findAll() {
        return (List<Salon>) iSalonRepository.findAll();
    }

    @Override
    public Salon findById(int id) {
        return iSalonRepository.findById(id).get();
    }

    @Override
    public void save(Salon salon) {
        iSalonRepository.save(salon);
    }

    @Override
    public Salon findByName(String name) {
        return null;
    }

    @Override
    public void remove(int id) {
        iSalonRepository.deleteById(id);
    }

    @Override
    public boolean existsById(int id) {
        return true; //true if id exists
    }
}
