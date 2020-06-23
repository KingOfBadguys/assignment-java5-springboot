package com.king.service.member;

import com.king.model.Member;
import com.king.repository.IMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private IMemberRepository iMemberRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Member> findAll() {
        return (List<Member>) iMemberRepository.findAll();
    }

    @Override
    public Member findById(int id) {
        return iMemberRepository.findById(id).get();
    }

    @Override
    public Member findByName(String username) {
        String query = "select m from Member m where m.username= :username";
        TypedQuery<Member> accountTypedQuery = entityManager.createQuery(query, Member.class);
        accountTypedQuery.setParameter("username", username);
        return accountTypedQuery.getResultList().get(0);
    }

    @Override
    public void save(Member member) {
        iMemberRepository.save(member);
    }

    @Override
    public void remove(int id) {
        iMemberRepository.deleteById(id);
    }
}
