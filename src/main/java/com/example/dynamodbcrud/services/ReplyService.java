package com.example.dynamodbcrud.services;

import java.util.List;

import com.example.dynamodbcrud.models.Reply;

public interface ReplyService {
    List<Reply> findRepliesInLastDays(String forumName, String threadSubject, int days) throws Exception;
    List<Reply> findRepliesPostedWithinTimePeriod(String forumName, String threadSubject) throws Exception; 
}