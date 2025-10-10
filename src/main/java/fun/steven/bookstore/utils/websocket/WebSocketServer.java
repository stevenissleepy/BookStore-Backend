package fun.steven.bookstore.utils.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import fun.steven.bookstore.pojo.ResponseMessage;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@ServerEndpoint("/topic/order/{userId}")
@Component
public class WebSocketServer {

    private static final Logger log = Logger.getLogger(WebSocketServer.class.getName());
    private static final ConcurrentHashMap<Long, Session> SESSIONS = new ConcurrentHashMap<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Long userId) {
        if (SESSIONS.containsKey(userId)) {
            log.warning("WebSocket 连接已存在，旧连接将被覆盖。 User ID: " + userId);
        }
        SESSIONS.put(userId, session);
        log.info(">>> WebSocket 新连接建立。 User ID: " + userId);
    }

    @OnClose
    public void onClose(@PathParam("userId") Long userId) {
        SESSIONS.remove(userId);
        log.info("<<< WebSocket 连接已断开。 User ID: " + userId);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.severe("WebSocket 发生错误: " + error.getMessage());
        error.printStackTrace();
    }

    public static void sendMessage(Long userId, ResponseMessage<?> message) {
        Session toSession = SESSIONS.get(userId);
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            toSession.getBasicRemote().sendText(jsonMessage);
        } catch (Exception e) {
            log.severe("消息发送失败: " + e.getMessage());
        }
    }
}