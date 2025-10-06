package fun.steven.bookstore.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.service.ISessionService;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionService implements ISessionService {
    private long startTime;
    private long endTime;
    private boolean running;
    
    @Override
    public void startTimer() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    @Override
    public long stopTimer() {
        if (running) {
            endTime = System.currentTimeMillis();
            running = false;
            return endTime - startTime;
        }
        
        return 0;
    }

}
