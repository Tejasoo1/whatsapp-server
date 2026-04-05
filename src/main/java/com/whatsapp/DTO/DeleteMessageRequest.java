package com.whatsapp.DTO;

import lombok.Generated;

public class DeleteMessageRequest {
    private boolean lastMessage;
    private Long prevMessObjId;

    @Generated
    public boolean isLastMessage() {
        return this.lastMessage;
    }

    @Generated
    public Long getPrevMessObjId() {
        return this.prevMessObjId;
    }

    @Generated
    public void setLastMessage(final boolean lastMessage) {
        this.lastMessage = lastMessage;
    }

    @Generated
    public void setPrevMessObjId(final Long prevMessObjId) {
        this.prevMessObjId = prevMessObjId;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof DeleteMessageRequest)) {
            return false;
        } else {
            DeleteMessageRequest other = (DeleteMessageRequest)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isLastMessage() != other.isLastMessage()) {
                return false;
            } else {
                Object this$prevMessObjId = this.getPrevMessObjId();
                Object other$prevMessObjId = other.getPrevMessObjId();
                if (this$prevMessObjId == null) {
                    if (other$prevMessObjId != null) {
                        return false;
                    }
                } else if (!this$prevMessObjId.equals(other$prevMessObjId)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof DeleteMessageRequest;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.isLastMessage() ? 79 : 97);
        Object $prevMessObjId = this.getPrevMessObjId();
        result = result * 59 + ($prevMessObjId == null ? 43 : $prevMessObjId.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        boolean var10000 = this.isLastMessage();
        return "DeleteMessageRequest(lastMessage=" + var10000 + ", prevMessObjId=" + this.getPrevMessObjId() + ")";
    }
}

