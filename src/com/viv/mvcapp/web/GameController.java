package com.viv.mvcapp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.viv.mvcapp.domain.GameState;


@Controller
public class GameController {
	
    @Autowired
    GameState gameState;

	@RequestMapping(value="/treasuregame", method= RequestMethod.GET)
	public ModelAndView startTreasureGame(ModelAndView mv) {
		mv.setViewName("PlayTreasureGame");
		
		return mv;
	}
	
	@RequestMapping(value="/TreasureGameController", method = RequestMethod.GET)
	public @ResponseBody GameState controlTreasureGame(ModelAndView mv, HttpServletRequest request, HttpSession session, @RequestParam("action") String action) {
		switch (action) {
        case "start":
       		gameState = new GameState(request.getParameter("numRows"));
            break;
            
        case "move":
        	int index = Integer.parseInt(request.getParameter("index"));
        	if (index == gameState.getTreasureLocation()) {
        		gameState.setEndTime();
        		gameState.setWon(true);
        	} else {
        		gameState.setWon(false);
        	}
            break;
            
        default:
    	   break;
		}
		return gameState;
	}
	
	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
}
