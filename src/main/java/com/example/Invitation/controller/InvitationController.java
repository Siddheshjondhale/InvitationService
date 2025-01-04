package com.example.Invitation.controller;

import com.example.Invitation.entity.Invitation;
import com.example.Invitation.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/invitations")
public class InvitationController {


    @Autowired
    private InvitationService invitationService;


    @PostMapping("/sendInvite")
    public ResponseEntity<String> sendInvite(@RequestBody Map<String, List<String>> emailInvites) {
        List<String> emails = emailInvites.get("emailInvite");


        if (emails == null || emails.size() != 2) {
            return ResponseEntity.badRequest().body("Invalid number of email addresses.");
        }

        String senderEmail = emails.get(0);
        String receiverEmail = emails.get(1);

        try {
            String message = invitationService.createInvite(senderEmail, receiverEmail);
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @GetMapping("/getAcceptedInvites")
    public ResponseEntity<List<Invitation>> getAcceptedInvites( @RequestHeader("sender") String sender){
        List<Invitation> acceptedInvites=invitationService.getAcceptedInvites(sender);
        System.out.println(acceptedInvites);
        return  ResponseEntity.ok(acceptedInvites);
    }

    @GetMapping("/pendingInvitesRequests")
    public ResponseEntity<List<String>> pendingInvitesRequests( @RequestHeader("MyGoogleID") String MyGoogleID){
        List<String> pendingInvitesRequests=invitationService.pendingInvitesRequests(MyGoogleID);
        return  ResponseEntity.ok(pendingInvitesRequests);
    }



}
