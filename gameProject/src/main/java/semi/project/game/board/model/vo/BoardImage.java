package semi.project.game.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardImage {

	private int imageNo;
	private String imagePath;
	private int boardNo;
	private int memberNo; 
	
}
