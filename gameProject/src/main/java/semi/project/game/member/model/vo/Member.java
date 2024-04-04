package semi.project.game.member.model.vo;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드 초기화하는 매개변수 생성자
@ToString 
public class Member {
	private int memberNo;
	private String memberName;
	private String memberId;
	private String memberPw;
	private String memberEmail;
	private String profileImg;
	private String managerYn;
	private String statusYn;
	private String memberBirthday;
	
	// 추후 추가 및 수정
}
