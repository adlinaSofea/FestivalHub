package com.festival.entity;

import com.festival.entity.Booking;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2026-06-11T10:59:53")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> phoneNumber;
    public static volatile SingularAttribute<Users, String> fullname;
    public static volatile SingularAttribute<Users, String> userid;
    public static volatile CollectionAttribute<Users, Booking> bookingCollection;
    public static volatile SingularAttribute<Users, String> email;

}