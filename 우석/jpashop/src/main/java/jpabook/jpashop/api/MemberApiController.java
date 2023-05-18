package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// @RestController = @Controller + @ResponseBody
// @ResponseBody: json이나 xml 데이터를 바로 보냄
@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

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
