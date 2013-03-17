package com.taksmind.textgame;

/**
 * 
 * @author tak
 * Interface for creating a Game class
 */
public interface Game {
	/**
	 * 
	 * @param question the question you've asked the user
	 * @param response the response the user has given
	 */
	void receiver(String question, String response);
}
