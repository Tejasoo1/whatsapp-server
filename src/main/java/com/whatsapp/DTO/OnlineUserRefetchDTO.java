package com.whatsapp.DTO;

import java.util.List;
import lombok.Generated;

public class OnlineUserRefetchDTO {
    private List<Long> onlineChattedUserIds;

    @Generated
    public List<Long> getOnlineChattedUserIds() {
        return this.onlineChattedUserIds;
    }

    @Generated
    public void setOnlineChattedUserIds(final List<Long> onlineChattedUserIds) {
        this.onlineChattedUserIds = onlineChattedUserIds;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OnlineUserRefetchDTO)) {
            return false;
        } else {
            OnlineUserRefetchDTO other = (OnlineUserRefetchDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$onlineChattedUserIds = this.getOnlineChattedUserIds();
                Object other$onlineChattedUserIds = other.getOnlineChattedUserIds();
                if (this$onlineChattedUserIds == null) {
                    if (other$onlineChattedUserIds != null) {
                        return false;
                    }
                } else if (!this$onlineChattedUserIds.equals(other$onlineChattedUserIds)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof OnlineUserRefetchDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $onlineChattedUserIds = this.getOnlineChattedUserIds();
        result = result * 59 + ($onlineChattedUserIds == null ? 43 : $onlineChattedUserIds.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "OnlineUserRefetchDTO(onlineChattedUserIds=" + this.getOnlineChattedUserIds() + ")";
    }
}
