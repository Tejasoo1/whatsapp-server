package com.whatsapp.DTO;

import java.time.LocalDateTime;
import lombok.Generated;

public class MessageDTOFE {
    private Long id;
    private UserDTOFE sender;
    private String content;
    private String imgUrl;
    private ChatDTOFE chat;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public UserDTOFE getSender() {
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
    public ChatDTOFE getChat() {
        return this.chat;
    }

    @Generated
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Generated
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setSender(final UserDTOFE sender) {
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
    public void setChat(final ChatDTOFE chat) {
        this.chat = chat;
    }

    @Generated
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated
    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MessageDTOFE)) {
            return false;
        } else {
            MessageDTOFE other = (MessageDTOFE)o;
            if (!other.canEqual(this)) {
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

                Object this$createdAt = this.getCreatedAt();
                Object other$createdAt = other.getCreatedAt();
                if (this$createdAt == null) {
                    if (other$createdAt != null) {
                        return false;
                    }
                } else if (!this$createdAt.equals(other$createdAt)) {
                    return false;
                }

                Object this$updatedAt = this.getUpdatedAt();
                Object other$updatedAt = other.getUpdatedAt();
                if (this$updatedAt == null) {
                    if (other$updatedAt != null) {
                        return false;
                    }
                } else if (!this$updatedAt.equals(other$updatedAt)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof MessageDTOFE;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
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
        Object $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : $createdAt.hashCode());
        Object $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : $updatedAt.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "MessageDTOFE(id=" + var10000 + ", sender=" + this.getSender() + ", content=" + this.getContent() + ", imgUrl=" + this.getImgUrl() + ", chat=" + this.getChat() + ", createdAt=" + this.getCreatedAt() + ", updatedAt=" + this.getUpdatedAt() + ")";
    }
}
