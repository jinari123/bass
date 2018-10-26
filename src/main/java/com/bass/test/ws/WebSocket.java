package com.bass.test.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bass.test.HomeController;

@ServerEndpoint(value = "/wstest")
public class WebSocket {
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);
	private final static List<Session> SS_LIST = Collections.synchronizedList(new ArrayList<Session>());
	@OnOpen
	public void onOpen(Session ss) throws IOException {
		SS_LIST.add(ss);
		logger.info("OPEN WebSocket Session ID : {}",ss.getId());
		try {
			ss.getBasicRemote().sendText("반갑다 친구야");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session ss) { //session ss는 사용자 
		SS_LIST.remove(ss);
		logger.info("CLOSE WebSocket Session ID : {}",ss.getId());
	}

	@OnError
	public void onError(Throwable e) {

	}

	@OnMessage
	public void onMessage(String msg, Session ss) throws IOException {
		for(Session s:SS_LIST) {
			if(s!=ss) {
				s.getBasicRemote().sendText(msg);
			}
		}
	}
}
