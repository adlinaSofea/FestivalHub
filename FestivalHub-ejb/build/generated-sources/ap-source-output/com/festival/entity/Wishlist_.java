package com.festival.entity;

import com.festival.entity.Festival;
import com.festival.entity.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2026-06-11T10:59:53")
@StaticMetamodel(Wishlist.class)
public class Wishlist_ { 

    public static volatile SingularAttribute<Wishlist, Festival> festivalid;
    public static volatile SingularAttribute<Wishlist, String> wishlistid;
    public static volatile SingularAttribute<Wishlist, Users> userid;

}