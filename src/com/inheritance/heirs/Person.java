/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inheritance.heirs;

import com.inheritance.rules.InheritanceException;

/**
 *
 * @author Jevison7x
 */
public abstract class Person
{
    private String name;

    private int age;

    private String gender;

    public static final String MALE = "Male";

    public static final String FEMALE = "Female";

    protected Person(String name, int age, String gender)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender) throws InheritanceException
    {
        if(gender.equals(MALE) || gender.equals(FEMALE))
            this.gender = gender;
        else
            throw new InheritanceException("Gender must be either 'Male' or 'Female'");
    }
}
