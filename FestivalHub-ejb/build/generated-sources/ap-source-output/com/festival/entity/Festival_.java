package com.festival.entity;

import com.festival.entity.Booking;
import com.festival.entity.Category;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2026-06-11T10:59:53")
@StaticMetamodel(Festival.class)
public class Festival_ { 

    public static volatile SingularAttribute<Festival, BigDecimal> ticketPrice;
    public static volatile SingularAttribute<Festival, String> festivalid;
    public static volatile SingularAttribute<Festival, String> imagePath;
    public static volatile SingularAttribute<Festival, Integer> ticketAvailability;
    public static volatile SingularAttribute<Festival, String> festivalName;
    public static volatile SingularAttribute<Festival, String> description;
    public static volatile SingularAttribute<Festival, String> location;
    public static volatile CollectionAttribute<Festival, Booking> bookingCollection;
    public static volatile SingularAttribute<Festival, Category> categoryid;
    public static volatile SingularAttribute<Festival, Date> eventDate;
    public static volatile SingularAttribute<Festival, String> status;

}