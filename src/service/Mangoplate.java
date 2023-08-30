package service;

import img.Loading;
import img.Mango;
import img.Music;

public class Mangoplate {

	public static void main(String[] args) {

		// 미니 프로젝트 메인문
		MainService mainService = new MainService();
		Mango mango = new Mango();
		Loading loading = new Loading();
		mango.mangoImg();
		loading.run();
		
		Music music = new Music("Lawrence - TrackTribe.mp3",true);
		music.setDaemon(true);
		music.start();
		
		mainService.start();

	}

}