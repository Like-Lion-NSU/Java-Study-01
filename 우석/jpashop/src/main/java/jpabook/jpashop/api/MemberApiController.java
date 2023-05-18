package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// @RestController = @Controller + @ResponseBody
// @ResponseBody: json이나 xml 데이터를 바로 보냄
@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("api/v1/members")
    public List<Member> membersV1(){
        return memberService.findMembers();
    }

    @GetMapping("api/v2/members")
    public Result membersV2(){
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());
        // List를 바로 반환하면 json의 배열타입으로 반환이 되므로 Result로 감싸줌
        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    // 데이터를 감싸는 객체
    // 추가 필드가 생겨도 변경이 쉬움
    static class Result<T>{
        private T data;
    }

    @Data
    @AllArgsConstructor
    // 외부에 노출할 것만 DTO에 저장
    // API 스펙 == DTO 코드
    static class MemberDto{
        private String name;
    }

    // 첫 번째 버전
    @PostMapping("/api/v1/members")
    // @RequestBody: json으로 온 http 요청의 body를 매개변수에 매핑해줌
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    // 두 번째 버전
    @PostMapping("/api/v2/members")
    // 파라미터로 CreateMemberRequest 라는 별도의 DTO 사용
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    // 등록이랑 수정은 API 스펙이 달라서 별도의 DTO 사용
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request){
        // 커맨드와 쿼리의 분리
        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    // 업데이트 요청 DTO
    static class UpdateMemberRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor
    // 업데이트 응답 DTO
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }

    @Data
    // DTO 클래스
    static class CreateMemberRequest{
        // 검증 로직
        @NotEmpty
        private String name;
    }

    @Data
    // 정적 내부 클래스
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
