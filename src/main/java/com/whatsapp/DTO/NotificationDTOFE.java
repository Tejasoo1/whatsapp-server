package com.whatsapp.DTO;

import lombok.Generated;

public class NotificationDTOFE {
    private Long id;
    private MessageDTOFE currMessage;
    private UserDTOFE user;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public MessageDTOFE getCurrMessage() {
        return this.currMessage;
    }

    @Generated
    public UserDTOFE getUser() {
        return this.user;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setCurrMessage(final MessageDTOFE currMessage) {
        this.currMessage = currMessage;
    }

    @Generated
    public void setUser(final UserDTOFE user) {
        this.user = user;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof NotificationDTOFE)) {
            return false;
        } else {
            NotificationDTOFE other = (NotificationDTOFE)o;
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

                Object this$currMessage = this.getCurrMessage();
                Object other$currMessage = other.getCurrMessage();
                if (this$currMessage == null) {
                    if (other$currMessage != null) {
                        return false;
                    }
                } else if (!this$currMessage.equals(other$currMessage)) {
                    return false;
                }

                Object this$user = this.getUser();
                Object other$user = other.getUser();
                if (this$user == null) {
                    if (other$user != null) {
                        return false;
                    }
                } else if (!this$user.equals(other$user)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof NotificationDTOFE;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $currMessage = this.getCurrMessage();
        result = result * 59 + ($currMessage == null ? 43 : $currMessage.hashCode());
        Object $user = this.getUser();
        result = result * 59 + ($user == null ? 43 : $user.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "NotificationDTOFE(id=" + var10000 + ", currMessage=" + this.getCurrMessage() + ", user=" + this.getUser() + ")";
    }
}

