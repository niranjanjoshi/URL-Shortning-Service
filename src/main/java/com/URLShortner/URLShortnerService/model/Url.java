package com.URLShortner.URLShortnerService.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@DynamoDBTable(tableName = "urls")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Url {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    @JsonIgnore
    // Mutability of state is maintained when getting called through constructor and not by getter setter
    private String id;

    @DynamoDBAttribute
    private String shortURL;

    @DynamoDBAttribute
    private String longURL;

    @DynamoDBAttribute
    private String alias;



}
