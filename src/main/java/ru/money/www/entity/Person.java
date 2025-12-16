package ru.money.www.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;



    @ElementCollection
    @CollectionTable(
            name = "phones",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "phones")
    private List<String> phones;

    @ElementCollection
    @CollectionTable(
            name = "addresses",
            joinColumns = @JoinColumn(name = "person_id")
    )
    private List<Address> addresses;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "work_city")),
            @AttributeOverride(name = "street", column = @Column(name = "work_street"))

    })
    private Address address;


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public Gender getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
