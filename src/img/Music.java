package img;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;
import service.Mangoplate;


public class Music extends Thread {

	private Player player;
	private boolean isLoop;// 곡이 무한반복인지 한번만 재생되는지 설정
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) { // 생성자
		// 곡의 제목과 무한반복인지의 유무
		try { // 예외처리
			this.isLoop = isLoop;
			file = new File(Mangoplate.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void close() { // 음악종료
		isLoop = false;
		player.close();
		this.interrupt();
	}

	@Override
	public void run() {
		try {
			do {
				player.play(); // 곡 실행
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
