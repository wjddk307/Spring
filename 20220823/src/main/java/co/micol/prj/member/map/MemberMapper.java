package co.micol.prj.member.map;

import java.util.List;

import co.micol.prj.member.vo.MemberVO;

public interface MemberMapper {
	List<MemberVO> memberSelectList(); //전체조회
	MemberVO memberSelect(MemberVO vo); // 상세조회
	int memberInsert(MemberVO vo); // 회원입력
	int memberUpdate(MemberVO vo); // 회원수정
	int memberDelete(MemberVO vo); // 회원삭제
}
