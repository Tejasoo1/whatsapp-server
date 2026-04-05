package com.whatsapp.DTO;

import lombok.Generated;

public class ChangePicRequest {
    private Long userId;
    private String imageURL;

    @Generated
    public Long getUserId() {
        return this.userId;
    }

    @Generated
    public String getImageURL() {
        return this.imageURL;
    }

    @Generated
    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    @Generated
    public void setImageURL(final String imageURL) {
        this.imageURL = imageURL;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ChangePicRequest)) {
            return false;
        } else {
            ChangePicRequest other = (ChangePicRequest)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$userId = this.getUserId();
                Object other$userId = other.getUserId();
                if (this$userId == null) {
                    if (other$userId != null) {
                        return false;
                    }
                } else if (!this$userId.equals(other$userId)) {
                    return false;
                }

                Object this$imageURL = this.getImageURL();
                Object other$imageURL = other.getImageURL();
                if (this$imageURL == null) {
                    if (other$imageURL != null) {
                        return false;
                    }
                } else if (!this$imageURL.equals(other$imageURL)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof ChangePicRequest;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : $userId.hashCode());
        Object $imageURL = this.getImageURL();
        result = result * 59 + ($imageURL == null ? 43 : $imageURL.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getUserId();
        return "ChangePicRequest(userId=" + var10000 + ", imageURL=" + this.getImageURL() + ")";
    }
}
