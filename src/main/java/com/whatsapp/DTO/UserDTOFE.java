package com.whatsapp.DTO;

import java.util.List;
import lombok.Generated;

public class UserDTOFE {
    private Long id;
    private String name;
    private String email;
    private String pic;
    private List<RolesDTOFE> roles;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public String getPic() {
        return this.pic;
    }

    @Generated
    public List<RolesDTOFE> getRoles() {
        return this.roles;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setName(final String name) {
        this.name = name;
    }

    @Generated
    public void setEmail(final String email) {
        this.email = email;
    }

    @Generated
    public void setPic(final String pic) {
        this.pic = pic;
    }

    @Generated
    public void setRoles(final List<RolesDTOFE> roles) {
        this.roles = roles;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof UserDTOFE)) {
            return false;
        } else {
            UserDTOFE other = (UserDTOFE)o;
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

                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$email = this.getEmail();
                Object other$email = other.getEmail();
                if (this$email == null) {
                    if (other$email != null) {
                        return false;
                    }
                } else if (!this$email.equals(other$email)) {
                    return false;
                }

                Object this$pic = this.getPic();
                Object other$pic = other.getPic();
                if (this$pic == null) {
                    if (other$pic != null) {
                        return false;
                    }
                } else if (!this$pic.equals(other$pic)) {
                    return false;
                }

                Object this$roles = this.getRoles();
                Object other$roles = other.getRoles();
                if (this$roles == null) {
                    if (other$roles != null) {
                        return false;
                    }
                } else if (!this$roles.equals(other$roles)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof UserDTOFE;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        Object $pic = this.getPic();
        result = result * 59 + ($pic == null ? 43 : $pic.hashCode());
        Object $roles = this.getRoles();
        result = result * 59 + ($roles == null ? 43 : $roles.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "UserDTOFE(id=" + var10000 + ", name=" + this.getName() + ", email=" + this.getEmail() + ", pic=" + this.getPic() + ", roles=" + this.getRoles() + ")";
    }
}
