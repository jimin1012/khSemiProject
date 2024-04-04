package semi.project.game.board.model.vo;

import static semi.project.game.common.JDBCTemplate.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReplyList {

//	private int replyNo;
//	private String replyContent;
//	private String createDate;
//	private int boardNo;
//	private int memberNo;
//	private String memberNickname;
//	private String profileImage;
	
//	REPLY_NO, REPLY_CONTENT, REPLY_DATE, BOARD_NO, MEMBER_NO, MEMBER_NAME -- 참고
	
	private int replyNo;
	private int memberNo;
	private int boardNo;
	private String memberName;
	private String replyContent;
	private String replyDate;
	private String memberProfile;
	
}
