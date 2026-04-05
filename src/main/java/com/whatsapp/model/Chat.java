package com.whatsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

@Entity
@Table(
        name = "chats"
)
public class Chat extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String chatName;
    private boolean isGroupChat = false;
    @ManyToMany
    @JoinTable(
            name = "chat_users",
            joinColumns = {@JoinColumn(
                    name = "chat_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "user_id"
            )}
    )
    @JsonIgnoreProperties({"chats", "password"})
    private List<User> users = new ArrayList();
    @OneToOne(
            optional = true
    )
    @JoinColumn(
            name = "latest_message_id",
            nullable = true
    )
    private Message latestMessage;
    @ManyToOne
    @JoinColumn(
            name = "group_admin_id"
    )
    @JsonIgnoreProperties({"chats"})
    private User groupAdmin;

    public void setIsGroupChat(boolean isGroupChat) {
        this.isGroupChat = isGroupChat;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Chat)) {
            return false;
        } else {
            Chat other = (Chat)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
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
        return other instanceof Chat;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = super.hashCode();
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
    public List<User> getUsers() {
        return this.users;
    }

    @Generated
    public Message getLatestMessage() {
        return this.latestMessage;
    }

    @Generated
    public User getGroupAdmin() {
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

    @JsonIgnoreProperties({"chats", "password"})
    @Generated
    public void setUsers(final List<User> users) {
        this.users = users;
    }

    @Generated
    public void setLatestMessage(final Message latestMessage) {
        this.latestMessage = latestMessage;
    }

    @JsonIgnoreProperties({"chats"})
    @Generated
    public void setGroupAdmin(final User groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "Chat(id=" + var10000 + ", chatName=" + this.getChatName() + ", isGroupChat=" + this.isGroupChat() + ", users=" + this.getUsers() + ", latestMessage=" + this.getLatestMessage() + ", groupAdmin=" + this.getGroupAdmin() + ")";
    }
}

