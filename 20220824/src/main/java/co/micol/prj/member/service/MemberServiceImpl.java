package co.micol.prj.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.micol.prj.member.map.MemberMapper;
import co.micol.prj.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	// DAO
	@Autowired
	private MemberMapper map;
	
	@Override
	public List<MemberVO> memberSelectList() {
		// TODO Auto-generated method stub
		return map.memberSelectList();
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		// TODO Auto-generated method stub
		return map.memberSelect(vo);
	}

	@Override
	public int memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub
		return map.memberInsert(vo);
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// TODO Auto-generated method stub
		return map.memberUpdate(vo);
	}

	@Override
	public int memberDelete(MemberVO vo) {
		// TODO Auto-generated method stub
		return map.memberDelete(vo);
	}

}
