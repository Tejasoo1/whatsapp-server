package com.whatsapp.model;

import lombok.Generated;

public class Response {
    private String statusCode;
    private String statusMsg;

    @Generated
    public String getStatusCode() {
        return this.statusCode;
    }

    @Generated
    public String getStatusMsg() {
        return this.statusMsg;
    }

    @Generated
    public void setStatusCode(final String statusCode) {
        this.statusCode = statusCode;
    }

    @Generated
    public void setStatusMsg(final String statusMsg) {
        this.statusMsg = statusMsg;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Response)) {
            return false;
        } else {
            Response other = (Response)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$statusCode = this.getStatusCode();
                Object other$statusCode = other.getStatusCode();
                if (this$statusCode == null) {
                    if (other$statusCode != null) {
                        return false;
                    }
                } else if (!this$statusCode.equals(other$statusCode)) {
                    return false;
                }

                Object this$statusMsg = this.getStatusMsg();
                Object other$statusMsg = other.getStatusMsg();
                if (this$statusMsg == null) {
                    if (other$statusMsg != null) {
                        return false;
                    }
                } else if (!this$statusMsg.equals(other$statusMsg)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof Response;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $statusCode = this.getStatusCode();
        result = result * 59 + ($statusCode == null ? 43 : $statusCode.hashCode());
        Object $statusMsg = this.getStatusMsg();
        result = result * 59 + ($statusMsg == null ? 43 : $statusMsg.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getStatusCode();
        return "Response(statusCode=" + var10000 + ", statusMsg=" + this.getStatusMsg() + ")";
    }

    @Generated
    public Response(final String statusCode, final String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    @Generated
    public Response() {
    }
}
