package model;

import java.time.LocalDateTime;

public class Message {
    private String sender;
    private String content;
    private LocalDateTime timestamp;
    private Message replyTo;

    public Message(String sender, String content, LocalDateTime timestamp, Message replyTo) {
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
        this.replyTo = replyTo;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Message getReplyTo() {
        return replyTo;
    }
}
