package com.example.dynamodbcrud.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.dynamodbcrud.models.Reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public List<Reply> findRepliesInLast15Days(String forumName, String threadSubject) throws Exception {

        String partitionKey = forumName + "#" + threadSubject;

        long twoWeeksAgoMilli = (new Date()).getTime() - (15L * 24L * 60L * 60L * 1000L);
        Date twoWeeksAgo = new Date();
        twoWeeksAgo.setTime(twoWeeksAgoMilli);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String twoWeeksAgoStr = dateFormatter.format(twoWeeksAgo);

        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(partitionKey));
        eav.put(":val2", new AttributeValue().withS(twoWeeksAgoStr.toString()));

        DynamoDBQueryExpression<Reply> queryExpression = new DynamoDBQueryExpression<Reply>()
            .withKeyConditionExpression("Id = :val1 and ReplyDateTime > :val2").withExpressionAttributeValues(eav);

        List<Reply> latestReplies = dynamoDBMapper.query(Reply.class, queryExpression);

        return latestReplies;
    }

    @Override
    public List<Reply> findRepliesPostedWithinTimePeriod(String forumName, String threadSubject) throws Exception {
        
        String partitionKey = forumName + "#" + threadSubject;

        long startDateMilli = (new Date()).getTime() - (14L * 24L * 60L * 60L * 1000L); // Two
                                                                                        // weeks
                                                                                        // ago.
        long endDateMilli = (new Date()).getTime() - (7L * 24L * 60L * 60L * 1000L); // One
                                                                                     // week
                                                                                     // ago.
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String startDate = dateFormatter.format(startDateMilli);
        String endDate = dateFormatter.format(endDateMilli);

        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(partitionKey));
        eav.put(":val2", new AttributeValue().withS(startDate));
        eav.put(":val3", new AttributeValue().withS(endDate));

        DynamoDBQueryExpression<Reply> queryExpression = new DynamoDBQueryExpression<Reply>()
            .withKeyConditionExpression("Id = :val1 and ReplyDateTime between :val2 and :val3")
            .withExpressionAttributeValues(eav);

        List<Reply> betweenReplies = dynamoDBMapper.query(Reply.class, queryExpression);

        return betweenReplies;
    }

}