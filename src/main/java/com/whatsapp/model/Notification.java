package com.whatsapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Generated;

@Entity
@Table(
        name = "notifications"
)
public class Notification {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "curr_message_id"
    )
    private Message currMessage;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_id"
    )
    private User user;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Message getCurrMessage() {
        return this.currMessage;
    }

    @Generated
    public User getUser() {
        return this.user;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setCurrMessage(final Message currMessage) {
        this.currMessage = currMessage;
    }

    @Generated
    public void setUser(final User user) {
        this.user = user;
    }

    @Generated
    public Notification() {
    }

    @Generated
    public Notification(final Long id, final Message currMessage, final User user) {
        this.id = id;
        this.currMessage = currMessage;
        this.user = user;
    }
}

