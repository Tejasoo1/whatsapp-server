package com.whatsapp.DTO;

import lombok.Generated;

public class RoomDTO {
    private String roomId;

    @Generated
    public String getRoomId() {
        return this.roomId;
    }

    @Generated
    public void setRoomId(final String roomId) {
        this.roomId = roomId;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RoomDTO)) {
            return false;
        } else {
            RoomDTO other = (RoomDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$roomId = this.getRoomId();
                Object other$roomId = other.getRoomId();
                if (this$roomId == null) {
                    if (other$roomId != null) {
                        return false;
                    }
                } else if (!this$roomId.equals(other$roomId)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof RoomDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $roomId = this.getRoomId();
        result = result * 59 + ($roomId == null ? 43 : $roomId.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "RoomDTO(roomId=" + this.getRoomId() + ")";
    }
}

