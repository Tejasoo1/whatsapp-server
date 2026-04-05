package com.whatsapp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Generated;

@Entity
public class Roles extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    private int roleId;
    private String roleName;

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Roles)) {
            return false;
        } else {
            Roles other = (Roles)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else if (this.getRoleId() != other.getRoleId()) {
                return false;
            } else {
                Object this$roleName = this.getRoleName();
                Object other$roleName = other.getRoleName();
                if (this$roleName == null) {
                    if (other$roleName != null) {
                        return false;
                    }
                } else if (!this$roleName.equals(other$roleName)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof Roles;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = super.hashCode();
        result = result * 59 + this.getRoleId();
        Object $roleName = this.getRoleName();
        result = result * 59 + ($roleName == null ? 43 : $roleName.hashCode());
        return result;
    }

    @Generated
    public int getRoleId() {
        return this.roleId;
    }

    @Generated
    public String getRoleName() {
        return this.roleName;
    }

    @Generated
    public void setRoleId(final int roleId) {
        this.roleId = roleId;
    }

    @Generated
    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    @Generated
    public String toString() {
        int var10000 = this.getRoleId();
        return "Roles(roleId=" + var10000 + ", roleName=" + this.getRoleName() + ")";
    }
}
