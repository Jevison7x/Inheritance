/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inheritance.rules;

import com.inheritance.heirs.Deceased;
import com.inheritance.heirs.Heir;
import static com.inheritance.heirs.Inheritable.HUSBAND;
import static com.inheritance.heirs.Inheritable.WIFE;
import com.inheritance.heirs.Person;
import java.util.List;

/**
 * This class is used to check all common sense in this application. The methods are all static, and this class is final.
 *
 * @author Jevison7x
 */
public final class CommonSense
{
    /**
     * This method checks to ensure that a Man does not have a husband and a Woman does not have a wife according to Islamic law.
     *
     * @param deceased the Deceased object
     * @param heirType the type of heir, must be a spouse (husband or wife) else an IllegalArgumentException will be thrown.
     * @throws InheritanceException if common sense is voilated
     */
    public static void spouseChecker(Deceased deceased, String heirType) throws InheritanceException
    {
        if(heirType.equals(HUSBAND) || heirType.equals(WIFE))
        {
            //Get the deceased gender
            String deceasedGender = deceased.getGender();
            //Prepare an error message in case...
            String errorMessage = "The deceased is a " + deceasedGender + ", the spouse cannot be " + heirType;
            switch(deceasedGender)
            {
                //Deceased is a male
                case Person.MALE:
                    if(heirType.equals(WIFE))
                        break;
                    else
                        throw new InheritanceException(errorMessage);//Male deceased cannot have husband
                //Deceased is a female
                case Person.FEMALE:
                    if(heirType.equals(HUSBAND))
                        break;
                    else
                        throw new InheritanceException(errorMessage);//Female deceased cannot have wife
            }
        }
        else
            throw new IllegalArgumentException("This method requires either " + HUSBAND + " or " + WIFE + " as the second argument");
    }

    /**
     * This method is used to check if the deceased has maximum number of wives. This method is used to ensure that if the deceased is a Male, he must not have more than 4 wives.
     *
     * @param deceased the Deceased object
     * @return true if the Deceased has reached his maximum of four wives, false otherwise
     * @throws IllegalArgumentException if the deceased is not a male.
     */
    public static boolean hasMaximumWives(Deceased deceased) throws IllegalArgumentException
    {
        if(deceased.getGender().equals(Person.MALE)) //The deceased must be male
        {
            List<Heir> heirs = deceased.getHeirs();
            int noOfWives = 0;
            for(Heir heir : heirs)
                if(heir.isWife())
                    noOfWives++;
            if(noOfWives < 4)
                return false;
            else
                return true;
        }
        else
            throw new IllegalArgumentException("This method requires a male as the deceased."); //The deceased must be male
    }
}
