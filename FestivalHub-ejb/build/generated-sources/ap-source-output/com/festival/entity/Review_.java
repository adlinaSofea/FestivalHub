package com.festival.entity;

import com.festival.entity.Festival;
import com.festival.entity.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2026-06-11T10:59:53")
@StaticMetamodel(Review.class)
public class Review_ { 

    public static volatile SingularAttribute<Review, Date> reviewDate;
    public static volatile SingularAttribute<Review, Festival> festivalid;
    public static volatile SingularAttribute<Review, Integer> rating;
    public static volatile SingularAttribute<Review, String> comment;
    public static volatile SingularAttribute<Review, String> reviewid;
    public static volatile SingularAttribute<Review, Users> userid;

}