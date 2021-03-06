package com.example.dynamodbcrud.controllers;

import java.util.List;

import com.example.dynamodbcrud.models.Reply;
import com.example.dynamodbcrud.services.ReplyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/replies")
class ReplyController {

    private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

    @Autowired
    private ReplyService replyService; 

    @GetMapping("/findRepliesInLastDays")
    public List<Reply> findRepliesInLastDays(@RequestParam String forumName, @RequestParam String threadSubject, @RequestParam(defaultValue = "15") int days)
            throws Exception {
        return replyService.findRepliesInLastDays(forumName, threadSubject, days);
    }

    @GetMapping("/findRepliesPostedWithinTimePeriod")
    public List<Reply> findRepliesPostedWithinTimePeriod(@RequestParam String forumName, @RequestParam String threadSubject)
            throws Exception {
        return replyService.findRepliesPostedWithinTimePeriod(forumName, threadSubject);
    }
}