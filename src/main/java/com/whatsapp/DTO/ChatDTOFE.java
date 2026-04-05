package com.whatsapp.DTO;

import java.util.List;
import lombok.Generated;

public class ChatDTOFE {
    private Long id;
    private String chatName;
    private boolean isGroupChat;
    private List<UserDTOFE> users;
    private MessageDTOFE latestMessage;
    private UserDTOFE groupAdmin;

    public void setIsGroupChat(boolean groupChat) {
        this.isGroupChat = groupChat;
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getChatName() {
        return this.chatName;
    }

    @Generated
    public boolean isGroupChat() {
        return this.isGroupChat;
    }

    @Generated
    public List<UserDTOFE> getUsers() {
        return this.users;
    }

    @Generated
    public MessageDTOFE getLatestMessage() {
        return this.latestMessage;
    }

    @Generated
    public UserDTOFE getGroupAdmin() {
        return this.groupAdmin;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setChatName(final String chatName) {
        this.chatName = chatName;
    }

    @Generated
    public void setUsers(final List<UserDTOFE> users) {
        this.users = users;
    }

    @Generated
    public void setLatestMessage(final MessageDTOFE latestMessage) {
        this.latestMessage = latestMessage;
    }

    @Generated
    public void setGroupAdmin(final UserDTOFE groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ChatDTOFE)) {
            return false;
        } else {
            ChatDTOFE other = (ChatDTOFE)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isGroupChat() != other.isGroupChat()) {
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

                Object this$chatName = this.getChatName();
                Object other$chatName = other.getChatName();
                if (this$chatName == null) {
                    if (other$chatName != null) {
                        return false;
                    }
                } else if (!this$chatName.equals(other$chatName)) {
                    return false;
                }

                Object this$users = this.getUsers();
                Object other$users = other.getUsers();
                if (this$users == null) {
                    if (other$users != null) {
                        return false;
                    }
                } else if (!this$users.equals(other$users)) {
                    return false;
                }

                Object this$latestMessage = this.getLatestMessage();
                Object other$latestMessage = other.getLatestMessage();
                if (this$latestMessage == null) {
                    if (other$latestMessage != null) {
                        return false;
                    }
                } else if (!this$latestMessage.equals(other$latestMessage)) {
                    return false;
                }

                Object this$groupAdmin = this.getGroupAdmin();
                Object other$groupAdmin = other.getGroupAdmin();
                if (this$groupAdmin == null) {
                    if (other$groupAdmin != null) {
                        return false;
                    }
                } else if (!this$groupAdmin.equals(other$groupAdmin)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof ChatDTOFE;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.isGroupChat() ? 79 : 97);
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $chatName = this.getChatName();
        result = result * 59 + ($chatName == null ? 43 : $chatName.hashCode());
        Object $users = this.getUsers();
        result = result * 59 + ($users == null ? 43 : $users.hashCode());
        Object $latestMessage = this.getLatestMessage();
        result = result * 59 + ($latestMessage == null ? 43 : $latestMessage.hashCode());
        Object $groupAdmin = this.getGroupAdmin();
        result = result * 59 + ($groupAdmin == null ? 43 : $groupAdmin.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "ChatDTOFE(id=" + var10000 + ", chatName=" + this.getChatName() + ", isGroupChat=" + this.isGroupChat() + ", users=" + this.getUsers() + ", latestMessage=" + this.getLatestMessage() + ", groupAdmin=" + this.getGroupAdmin() + ")";
    }
}

