package com.whatsapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.whatsapp.annotation.PasswordValidator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(
        name = "users"
)
public class User implements UserDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            nullable = false
    )
    private @NotBlank(
            message = "Name must not be blank"
    ) @Size(
            min = 3,
            message = "Name must be at least 3 characters long"
    ) String name;
    @Column(
            nullable = false,
            unique = true
    )
    private @NotBlank(
            message = "Email must not be blank"
    ) @Email(
            message = "Please provide a valid email address"
    ) String email;
    @Column(
            nullable = false
    )
    @PasswordValidator
    @JsonProperty(
            access = Access.WRITE_ONLY
    )
    private @NotBlank(
            message = "Password must not be blank"
    ) String password;
    @Column(
            nullable = true
    )
    private String pic = "https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg";
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(
                    name = "user_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id"
            )}
    )
    private List<Roles> roles;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection)this.roles.stream().map((role) -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName())).collect(Collectors.toList());
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public String getUsername() {
        return String.valueOf(this.id);
    }

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
    public String getPassword() {
        return this.password;
    }

    @Generated
    public String getPic() {
        return this.pic;
    }

    @Generated
    public List<Roles> getRoles() {
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

    @JsonProperty(
            access = Access.WRITE_ONLY
    )
    @Generated
    public void setPassword(final String password) {
        this.password = password;
    }

    @Generated
    public void setPic(final String pic) {
        this.pic = pic;
    }

    @Generated
    public void setRoles(final List<Roles> roles) {
        this.roles = roles;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        } else {
            User other = (User)o;
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

                Object this$password = this.getPassword();
                Object other$password = other.getPassword();
                if (this$password == null) {
                    if (other$password != null) {
                        return false;
                    }
                } else if (!this$password.equals(other$password)) {
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
        return other instanceof User;
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
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $pic = this.getPic();
        result = result * 59 + ($pic == null ? 43 : $pic.hashCode());
        Object $roles = this.getRoles();
        result = result * 59 + ($roles == null ? 43 : $roles.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "User(id=" + var10000 + ", name=" + this.getName() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", pic=" + this.getPic() + ", roles=" + this.getRoles() + ")";
    }
}
