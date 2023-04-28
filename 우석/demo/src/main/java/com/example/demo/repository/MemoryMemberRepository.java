package com.example.demo.repository;

import com.example.demo.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    // 데이터를 저장하는 변수
    // 동시성 문제를 해결하려면 ConcurrentHashMap 사용
    private static Map<Long, Member> store = new HashMap<>();
    // 키 값을 생성해주는 변수
    // 동시성 문제를 고려하면 AtomicLong 사용
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // id 세팅
        member.setId(++sequence);
        // 저장
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null이 반환될 가능성이 있는 객체를 Optional로 감싸기
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // stream을 이용하여 이름이 같은 객체 찾기
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // return store.values().stream().collect(Collectors.toList());
         return new ArrayList<>(store.values());
    }
}
