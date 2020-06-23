package com.king.service.member;

import com.king.model.Member;

import java.util.List;

public interface IMemberService {
    public List<Member> findAll();

    public Member findById(int id);

    public Member findByName(String username);

    void save(Member member);

    void remove(int id);
}
