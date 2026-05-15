package com.wallet.quartz.netty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.wallet.common.utils.JsonUtils;
import com.wallet.quartz.dto.SubscriberCacheDto;
import com.wallet.quartz.dto.SubscriberReqDto;
import com.wallet.quartz.enums.SocketSendTypeEnum;
import com.wallet.quartz.enums.SocketSubscibeTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



 
/**
 * @Auther: liaoshiyao
 * @Date: 2019/1/11 11:48
 * @Description: websocket 服务类
 */
 
/**
 *
 * @ServerEndpoint 这个注解有什么作用？
 *
 * 这个注解用于标识作用在类上，它的主要功能是把当前类标识成一个WebSocket的服务端
 * 注解的值用户客户端连接访问的URL地址
 *
 */
 
@Component
@ServerEndpoint("/websocket")
public class WebSocket {
	private static final Logger log = LoggerFactory.getLogger(WebSocket.class);
    /**
     *  与某个客户端的连接对话，需要通过它来给客户端发送消息
     */
    private Session session;
     /**
     * 标识当前连接客户端的用户名
     */
    private String name;
    Boolean aa=true;
 
    /**
     *  用于存所有的连接服务的客户端，这个对象存储是安全的
     */
    private static ConcurrentHashMap<String,WebSocket> webSocketSet = new ConcurrentHashMap<>();

    public Map<String, SubscriberCacheDto> cacheMap = new HashMap<>();

    @OnOpen
    public void OnOpen(Session session){
        this.session = session;
        // name是用来表示唯一客户端，如果需要指定发送，需要指定发送通过name来区分
        webSocketSet.put(session.getId(),this);
        log.info("[WebSocket] 连接成功，当前连接人数为：={}",webSocketSet.size());
    }
 
 
    @OnClose
    public void OnClose(){
        webSocketSet.remove(this.session.getId());
        log.info("[WebSocket] 退出成功，当前连接人数为：={}",webSocketSet.size());
    }
 
    @OnMessage
    public void OnMessage(String message) throws InterruptedException{
        log.info("[WebSocket] 收到消息：{}",message);
        SubscriberReqDto dto = JsonUtils.jsonToObject(message, SubscriberReqDto.class);
        disposeReq(dto);
        try {
            sendMessage("Receive the success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 群发
     * @param message
     */
    public static void GroupSending(String message){
        for (String name : webSocketSet.keySet()){
        	// System.out.println(name);
            try {
                webSocketSet.get(name).session.getBasicRemote().sendText(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 群发行情数据
     * @param message
     */
    public static void sendTickerInfo(String message,String type){
        for (String name : webSocketSet.keySet()){
            try {
                if (webSocketSet.get(name).cacheMap.containsKey(type)) {
                    webSocketSet.get(name).session.getBasicRemote().sendText(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 实现服务器主动推送
     */
    public synchronized void sendMessage(String message) throws IOException {
        synchronized(this.session) {
            try {
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("线程推送错误",e);
            }
        }
    }

    public void batchSending(List<String> nameList ,String message){
    	for (String name : nameList) {
    		try {
                webSocketSet.get(name).session.getBasicRemote().sendText(message);
               
            }catch (Exception e){
            	 List<String> list=new ArrayList<String>();
            	 
                e.printStackTrace();
            }
		}
    }
    	
    /**
     * 指定发送
     * @param name
     * @param message
     */
    public void AppointSending(String name,String message){
        try {
            webSocketSet.get(name).session.getBasicRemote().sendText(message);
           
        }catch (Exception e){
        	 webSocketSet.remove(webSocketSet.get(name).session.getId());
            e.printStackTrace();
        }
    }

    private void disposeReq(SubscriberReqDto dto) {
        if (SocketSubscibeTypeEnum.ADD_TICKER.getValue().equals(dto.getType())) {
            cacheMap.put(SocketSendTypeEnum.TICKER.getValue(), null);
        } else if (SocketSubscibeTypeEnum.DEL_TICKER.getValue().equals(dto.getType())) {
            cacheMap.remove(SocketSendTypeEnum.TICKER.getValue());
        }
    }
}