package com.example.Invitation.entity;


import jakarta.persistence.*;

import lombok.Builder;

@Entity
@Table(name = "Invitations")
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Foreign key to User entity
    @Column
    private String sender; // The User who sends the invitation

    @Column
    private String receiver; // The User who receives the invitation

    @Column
    private boolean status = false;  //Default false;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
