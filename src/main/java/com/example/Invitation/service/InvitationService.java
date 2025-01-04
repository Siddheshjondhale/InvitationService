package com.example.Invitation.service;

import com.example.Invitation.entity.Invitation;
import com.example.Invitation.entity.User;
import com.example.Invitation.repository.InvitationRepository;
import com.example.Invitation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvitationService
{

    @Autowired
    private InvitationRepository invitationRepository;




    @Autowired
    private UserRepository userRepository;

    public String createInvite(String senderEmail, String receiverEmail) {
        User senderUser = userRepository.findByEmail(senderEmail);
        User receiverUser = userRepository.findByEmail(receiverEmail);

        if (senderUser == null || receiverUser == null || senderEmail.equals(receiverEmail)) {
            return "Sender or receiver email not found or sender and receiverEmail should be Different";
        }

        String senderGoogleID = senderUser.getGoogleId();
        String receiverGoogleID = receiverUser.getGoogleId();


        Invitation invitation = invitationRepository.findBySenderAndReceiver(senderGoogleID,receiverGoogleID);

        try {
            if(invitation==null){
                invitation=new Invitation();
                invitation.setSender(senderGoogleID);
                invitation.setReceiver(receiverGoogleID);
                invitationRepository.save(invitation);
                return "Success";
            }

            return "Invitation Already exists";


        } catch (Exception e) {
            return "Failed to save invitation: " + e.getMessage();
        }
    }

    // Function to get the users who have accepted the Invites
    public List<Invitation> getAcceptedInvites(String sender) {
        System.out.println("Sender: " + sender);
        return invitationRepository.findBySenderAndStatusTrue(sender);
    }

   // Function to show all the request which has recieved to the user from other people and still pending
    public List<String> pendingInvitesRequests(String MyGoogleID){

        List<String> users = new ArrayList<>();
        List<Invitation> invitations = invitationRepository.findByReceiverAndStatusFalse(MyGoogleID);

        for(int i=0;i<invitations.size();i++){
            users.add((userRepository.findByGoogleId(invitations.get(i).getSender())).getEmail());
        }
        return users;
    }


}
