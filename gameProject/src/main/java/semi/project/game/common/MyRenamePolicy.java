package semi.project.game.common;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyRenamePolicy  implements FileRenamePolicy{

	@Override
	public File rename(File originalFile) {
		long currentTime = System.currentTimeMillis();

		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");

		int ranNum = (int) (Math.random() * 100000); // 5자리 랜덤 숫자 생성

		String str = "_" + String.format("%05d", ranNum);

		// 확장자 명 잘라오기
		String name = originalFile.getName();
		String ext = null;

		int dot = name.lastIndexOf(".");

		if (dot != -1) {
			ext = name.substring(dot);
		} else {
			ext = "";
		}

		// 새로운 파일명
		String fileName = ft.format(new Date(currentTime)) + str + ext;
		
		File newFile = new File(originalFile.getParent(), fileName);

		return newFile;
	}

}

