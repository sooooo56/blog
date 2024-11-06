package com.korea.blog.domain.member.service;

import com.korea.blog.domain.member.entity.Member;
import com.korea.blog.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(String username, String password) {

        if(memberRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        Member member = Member.builder()
                .username(username)
                .password(password)
                .build();


        memberRepository.save(member);
    }
}