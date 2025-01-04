package com.example.Invitation.repository;

import com.example.Invitation.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation,Long> {
    List<Invitation> findBySenderAndStatusTrue(String sender);   // to get all the accepted family members
    List<Invitation> findByReceiverAndStatusFalse(String sender);   // tp get all the users who has send requests to me
    Invitation findBySenderAndReceiver(String sender, String receiver);

}
