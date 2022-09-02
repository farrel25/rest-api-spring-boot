package practice.restapispringboot.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * this class will encapsulate the information sent from the server to the client,
 * whether it passes validation or not
 * 
 * this is generic class, so the object can be anything (payload)
 * the data that successfully saved will be stored in payload
 */
public class ResponseData<T> {
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private T payload;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
    
}
