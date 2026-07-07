/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.festival.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "FESTIVAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Festival.findAll", query = "SELECT f FROM Festival f"),
    @NamedQuery(name = "Festival.findByFestivalid", query = "SELECT f FROM Festival f WHERE f.festivalid = :festivalid"),
    @NamedQuery(name = "Festival.findByFestivalName", query = "SELECT f FROM Festival f WHERE f.festivalName = :festivalName"),
    @NamedQuery(name = "Festival.findByEventDate", query = "SELECT f FROM Festival f WHERE f.eventDate = :eventDate"),
    @NamedQuery(name = "Festival.findByDescription", query = "SELECT f FROM Festival f WHERE f.description = :description"),
    @NamedQuery(name = "Festival.findByLocation", query = "SELECT f FROM Festival f WHERE f.location = :location"),
    @NamedQuery(name = "Festival.findByTicketPrice", query = "SELECT f FROM Festival f WHERE f.ticketPrice = :ticketPrice"),
    @NamedQuery(name = "Festival.findByTicketAvailability", query = "SELECT f FROM Festival f WHERE f.ticketAvailability = :ticketAvailability"),
    @NamedQuery(name = "Festival.findByImagePath", query = "SELECT f FROM Festival f WHERE f.imagePath = :imagePath"),
    // NEW: query to find only approved festivals (used on user-facing pages)
    @NamedQuery(name = "Festival.findApproved", query = "SELECT f FROM Festival f WHERE f.status = 'approved'")
})
public class Festival implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FESTIVALID")
    private String festivalid;

    @Size(max = 255)
    @Column(name = "FESTIVAL_NAME")
    private String festivalName;

    @Column(name = "EVENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date eventDate;

    @Size(max = 1000)
    @Column(name = "DESCRIPTION")
    private String description;

    @Size(max = 255)
    @Column(name = "LOCATION")
    private String location;

    @Column(name = "TICKET_PRICE")
    private BigDecimal ticketPrice;

    @Column(name = "TICKET_AVAILABILITY")
    private Integer ticketAvailability;

    @Size(max = 255)
    @Column(name = "IMAGE_PATH")
    private String imagePath;

    // NEW: status field — values: "pending", "approved", "rejected"
    @Size(max = 20)
    @Column(name = "STATUS")
    private String status;

    @JoinColumn(name = "CATEGORYID", referencedColumnName = "CATEGORYID")
    @ManyToOne
    private Category categoryid;

    @OneToMany(mappedBy = "festivalid", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Booking> bookingCollection;

    public Festival() {
    }

    public Festival(String festivalid) {
        this.festivalid = festivalid;
    }

    public String getFestivalid() {
        return festivalid;
    }

    public void setFestivalid(String festivalid) {
        this.festivalid = festivalid;
    }

    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getTicketAvailability() {
        return ticketAvailability;
    }

    public void setTicketAvailability(Integer ticketAvailability) {
        this.ticketAvailability = ticketAvailability;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // NEW: getter and setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Category getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Category categoryid) {
        this.categoryid = categoryid;
    }

    @XmlTransient
    public Collection<Booking> getBookingCollection() {
        return bookingCollection;
    }

    public void setBookingCollection(Collection<Booking> bookingCollection) {
        this.bookingCollection = bookingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (festivalid != null ? festivalid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Festival)) {
            return false;
        }
        Festival other = (Festival) object;
        if ((this.festivalid == null && other.festivalid != null) || (this.festivalid != null && !this.festivalid.equals(other.festivalid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.festival.entity.Festival[ festivalid=" + festivalid + " ]";
    }
}