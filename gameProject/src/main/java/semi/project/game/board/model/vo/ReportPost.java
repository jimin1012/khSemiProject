package semi.project.game.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReportPost {

	// Board 관련
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardStatus;
	private String boardDate;

	// Member 관련
	private int memberNo;
	private String memberName;
	
	// ReportPost 관련
	private int reportNo;
	private String reportContent;
	private String reportYn;
	
	// Category 관련
	private int categoryNo;
	private String categoryName;
}
