package semi.project.game.board.model.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardView {

	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardDate;
	private int categoryNo;
	private String categoryName;
	private int memberNo;
	private String boardStatus; // ?char?
	private String memberName;
	private String memberId;
	private int imageNo;
	private String imagePath;
	private int reportNo;
	private String reportYn;
	private int reportConfirm;
	
	private List<BoardImage> imageList;

}
