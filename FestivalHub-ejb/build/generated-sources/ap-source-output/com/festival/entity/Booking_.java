package com.festival.entity;

import com.festival.entity.Festival;
import com.festival.entity.Payment;
import com.festival.entity.Users;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2026-06-11T10:59:53")
@StaticMetamodel(Booking.class)
public class Booking_ { 

    public static volatile SingularAttribute<Booking, Integer> ticketBooked;
    public static volatile SingularAttribute<Booking, BigDecimal> totalPrice;
    public static volatile SingularAttribute<Booking, Festival> festivalid;
    public static volatile CollectionAttribute<Booking, Payment> paymentCollection;
    public static volatile SingularAttribute<Booking, Users> userid;
    public static volatile SingularAttribute<Booking, String> bookingid;
    public static volatile SingularAttribute<Booking, String> status;

}