package semi.project.game.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardDate;
	private int categoryNo;
	private String categoryName;
	private int memberNo;
	private String memberName;
	private String MemberProfile;
	
}
