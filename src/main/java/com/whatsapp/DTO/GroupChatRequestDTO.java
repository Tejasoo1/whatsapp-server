package com.whatsapp.DTO;

import java.util.List;
import lombok.Generated;

public class GroupChatRequestDTO {
    private String name;
    private List<Long> users;

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public List<Long> getUsers() {
        return this.users;
    }

    @Generated
    public void setName(final String name) {
        this.name = name;
    }

    @Generated
    public void setUsers(final List<Long> users) {
        this.users = users;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof GroupChatRequestDTO)) {
            return false;
        } else {
            GroupChatRequestDTO other = (GroupChatRequestDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
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

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof GroupChatRequestDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $users = this.getUsers();
        result = result * 59 + ($users == null ? 43 : $users.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getName();
        return "GroupChatRequestDTO(name=" + var10000 + ", users=" + this.getUsers() + ")";
    }
}
