package com.example.dynamodbcrud.services;

import java.util.List;

import com.example.dynamodbcrud.models.Reply;

public interface ReplyService {
    List<Reply> findRepliesInLast15Days(String forumName, String threadSubject) throws Exception;
    List<Reply> findRepliesPostedWithinTimePeriod(String forumName, String threadSubject) throws Exception; 
}