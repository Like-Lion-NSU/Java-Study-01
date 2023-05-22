package Hi.Hispring.repository;

import Hi.Hispring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findbyID(Long id);
    Optional<Member> findbyName(String name);
    List<Member> findAll();
}
