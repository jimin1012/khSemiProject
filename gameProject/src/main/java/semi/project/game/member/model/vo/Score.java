package semi.project.game.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Score {

	private int score;
	private int memberNo;
	private int gameNo;
	private String gameName;
	
	// 01-30 추가 항목 (랭킹 부분)
	private String memberName;
	private String profile;
	
}
