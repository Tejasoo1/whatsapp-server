package com.whatsapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Generated;

@Entity
@Table(
        name = "messages"
)
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @ManyToOne
    @JoinColumn(
            name = "sender_id"
    )
    private User sender;
    @Column(
            length = 1000
    )
    private @NotBlank(
            message = "Message Content must not be blank"
    ) @Size(
            min = 1,
            message = "Message Content must be at least 1 character long"
    ) String content;
    @Column(
            name = "img_url"
    )
    private String imgUrl = "";
    @ManyToOne
    @JoinColumn(
            name = "chat_id"
    )
    private Chat chat;

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Message)) {
            return false;
        } else {
            Message other = (Message)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                Object this$sender = this.getSender();
                Object other$sender = other.getSender();
                if (this$sender == null) {
                    if (other$sender != null) {
                        return false;
                    }
                } else if (!this$sender.equals(other$sender)) {
                    return false;
                }

                Object this$content = this.getContent();
                Object other$content = other.getContent();
                if (this$content == null) {
                    if (other$content != null) {
                        return false;
                    }
                } else if (!this$content.equals(other$content)) {
                    return false;
                }

                Object this$imgUrl = this.getImgUrl();
                Object other$imgUrl = other.getImgUrl();
                if (this$imgUrl == null) {
                    if (other$imgUrl != null) {
                        return false;
                    }
                } else if (!this$imgUrl.equals(other$imgUrl)) {
                    return false;
                }

                Object this$chat = this.getChat();
                Object other$chat = other.getChat();
                if (this$chat == null) {
                    if (other$chat != null) {
                        return false;
                    }
                } else if (!this$chat.equals(other$chat)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof Message;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = super.hashCode();
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $sender = this.getSender();
        result = result * 59 + ($sender == null ? 43 : $sender.hashCode());
        Object $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        Object $imgUrl = this.getImgUrl();
        result = result * 59 + ($imgUrl == null ? 43 : $imgUrl.hashCode());
        Object $chat = this.getChat();
        result = result * 59 + ($chat == null ? 43 : $chat.hashCode());
        return result;
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public User getSender() {
        return this.sender;
    }

    @Generated
    public String getContent() {
        return this.content;
    }

    @Generated
    public String getImgUrl() {
        return this.imgUrl;
    }

    @Generated
    public Chat getChat() {
        return this.chat;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setSender(final User sender) {
        this.sender = sender;
    }

    @Generated
    public void setContent(final String content) {
        this.content = content;
    }

    @Generated
    public void setImgUrl(final String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Generated
    public void setChat(final Chat chat) {
        this.chat = chat;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "Message(id=" + var10000 + ", sender=" + this.getSender() + ", content=" + this.getContent() + ", imgUrl=" + this.getImgUrl() + ", chat=" + this.getChat() + ")";
    }
}

