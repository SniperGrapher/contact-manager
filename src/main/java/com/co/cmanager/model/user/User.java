package com.co.cmanager.model.user;

import com.co.cmanager.model.DomainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author by Cyprian Omenuko on 6/9/2016.
 */

@Entity
@Table(name = "T_USER")
@Setter
@Getter
public class User extends DomainEntity {

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "EMAIL", unique = true)
    private String email;
    @Column(name = "PHONE_NUMBER", unique = true)
    private String phoneNumber;


    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

}
