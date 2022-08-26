package co.micol.prj.notice.map;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import co.micol.prj.notice.service.NoticeVO;

public interface NoticeMapper {
	
	    // ListMap구조 : Join 또는 vo객체를 안만들려고 할때 => map type으로 들어옴.
		@Select("select * from notice") // map.xml설정 안해도됨.ibatis
		List<Map<String,Object>> noticeSelectList(); 
		
		NoticeVO noticeSelect(NoticeVO vo);
		int noticeInsert(NoticeVO vo);
		int noticeUpdate(NoticeVO vo);
		int noticeDelete(NoticeVO vo);
		
		int noticeHitUpdate(NoticeVO vo); // 조회수 증가
		
		// 제목에 어떤 단어 or 내용에 어떤 단어가 있으면 가져와라
		// Mapper는 매개변수가 두개면 오류 => @Param 파라메타로 넘어오는키를  String key, String val로 쓰겠다.
		// 속성명, 데이터 타입, 변수명
		List<NoticeVO> noticeSearch(@Param("key") String key, @Param("val") String val);

}
