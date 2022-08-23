package co.micol.prj.member.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	// lombok으로 get set
	// 테이블명과 같게
	private String member_id;
	private String member_password;
	private String member_name;
	private String member_author;
}
