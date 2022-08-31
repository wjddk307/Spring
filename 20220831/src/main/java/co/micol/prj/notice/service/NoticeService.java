package co.micol.prj.notice.service;

import java.util.List;
import java.util.Map;

public interface NoticeService {
	
	// Join 또는 vo객체를 안만들려고 할때
	List<Map<String,Object>> noticeSelectList();
	
	NoticeVO noticeSelect(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
	int noticeUpdate(NoticeVO vo);
	int noticeDelete(NoticeVO vo);
	
	// 조회수 증가
	int noticeHitUpdate(NoticeVO vo);
	
	// 제목에 어떤 단어 or 내용에 어떤 단어가 있으면 가져와라
	List<NoticeVO> noticeSearch(String key, String val);

}
