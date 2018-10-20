package khj.home.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import khj.home.service.FileService;
import khj.home.service.MusicService;
import khj.home.vo.Member;
import khj.home.vo.Music;

@Controller
public class MusicController {

	@Autowired
	private FileService fileService;
	
	@Autowired
	private MusicService musicService;
	
	@RequestMapping(value = "/member/music", method=RequestMethod.POST)
	public String memberMusicManage(@RequestParam(defaultValue="1") int page) {
		return "/member/music.jsp";
	}
	
	@RequestMapping(value = "/music/add", method=RequestMethod.POST)
	public String MusicAdd(@ModelAttribute Music music, HttpServletRequest request, HttpSession session) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		String path = request.getServletContext().getRealPath("/WEB-INF/resources/music");			
		String filename;
		try {
			filename = fileService.saveFile(path, music.getMusic_file());
			music.setMusic(filename);
			music.setNickname(loginMember.getNickname());
			musicService.musicAdd(music);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "/member/music";
	}
}
