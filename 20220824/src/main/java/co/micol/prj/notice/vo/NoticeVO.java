package co.micol.prj.notice.vo;



import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeVO {
	private int notice_id;
	private String notice_writer;
	private String notice_title;
	private String notice_subject;
	private Date notice_date;
	private int notice_hit;
	private String notice_attach;
	private String notice_attach_dir;

}
