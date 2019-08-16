/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inheritance.heirs;

import static com.inheritance.heirs.Inheritable.*;
import com.inheritance.rules.CommonSense;
import com.inheritance.rules.InheritanceException;

/**
 *
 * @author Jevison7x
 */
public class Heir extends Person implements Inheritable
{

    private String heirType;

    private double inheritedValue;

    private boolean blocked;

    private String blockReason;

    public String getBlockReason()
    {
        return blockReason;
    }

    public void setBlockReason(String blockReason)
    {
        this.blockReason = blockReason;
    }

    public Heir(String name, int age, String gender)
    {
        super(name, age, gender);
    }

    public boolean isBlocked()
    {
        return blocked;
    }

    public void doBlock()
    {
        this.blocked = true;
    }

    public boolean isBlockable()
    {
        boolean blockable = false;
        if(this.isGrandSon() || this.isGrandDaughter() || this.isFullBrother() || this.isFullSister() || this.isPaternalBrother()
                || this.isPaternalSister() || this.isMaternalBrother() || this.isMaternalSister() || this.isNephew()
                || this.isPaternalNephew() || this.isNephewSon() || this.isPaternalNephewSon() || this.isUncle() || this.isPaternalUncle()
                || this.isCousin() || this.isPaternalCousin() || this.isCousinSon() || this.isPaternalCousinSon() || this.isCousinGrandson()
                || this.isPaternalCousinGrandson())
            blockable = true;
        return blockable;
    }

    @Override
    public String getHeirType()
    {
        return heirType;
    }

    @Override
    public void setHeirType(Deceased deceased, String heirType) throws InheritanceException
    {
        // Check if the heir is a spouse
        if(heirType.equals(HUSBAND) || heirType.equals(WIFE))
            CommonSense.spouseChecker(deceased, heirType); //Validate spouse's relationship with the deceased
        this.heirType = heirType;
    }

    public double getInheritedValue()
    {
        return inheritedValue;
    }

    public void setInheritedValue(double inheritedValue)
    {
        this.inheritedValue = inheritedValue;
    }

    public boolean isSpouse()
    {
        return this.heirType.equals(HUSBAND) || this.heirType.equals(WIFE);
    }

    public boolean isHusband()
    {
        return this.heirType.equals(HUSBAND);
    }

    public boolean isWife()
    {
        return this.heirType.equals(WIFE);
    }

    ////    Jed's 15/2/16   //////////////
    public boolean isSon()
    {
        return this.heirType.equals(SON);
    }

    public boolean isDaughter()
    {
        return this.heirType.equals(DAUGTHER);
    }

    public boolean isGrandSon()
    {
        return this.heirType.equals(GRANDSON);
    }

    public boolean isGrandDaughter()
    {
        return this.heirType.equals(GRANDDAUGHTER);
    }

    public boolean isFather()
    {
        return this.heirType.equals(FATHER);
    }

    public boolean isMother()
    {
        return this.heirType.equals(MOTHER);
    }

    public boolean isGrandFather()
    {
        return this.heirType.equals(GRANDFATHER);
    }

    public boolean isPaternalGrandMother()
    {
        return this.heirType.equals(PATERNAL_GRANDMOTHER);
    }

    public boolean isMaternalGrandMother()
    {
        return this.heirType.equals(MATERNAL_GRANDMOTHER);
    }

    public boolean isFullBrother()
    {
        return this.heirType.equals(FULL_BROTHER);
    }

    public boolean isFullSister()
    {
        return this.heirType.equals(FULL_SISTER);
    }

    public boolean isPaternalBrother()
    {
        return this.heirType.equals(PATERNAL_BROTHER);
    }

    public boolean isPaternalSister()
    {
        return this.heirType.equals(PATERNAL_SISTER);
    }

    public boolean isMaternalBrother()
    {
        return this.heirType.equals(MATERNAL_BROTHER);
    }

    public boolean isMaternalSister()
    {
        return this.heirType.equals(MATERNAL_SISTER);
    }

    public boolean isNephew()
    {
        return this.heirType.equals(NEPHEW);
    }

    public boolean isPaternalNephew()
    {
        return this.heirType.equals(PATERNAL_NEPHEW);
    }

    public boolean isNephewSon()
    {
        return this.heirType.equals(NEPHEWS_SON);
    }

    public boolean isPaternalNephewSon()
    {
        return this.heirType.equals(PATERNAL_NEPHEWS_SON);
    }

    public boolean isUncle()
    {
        return this.heirType.equals(UNCLE);
    }

    public boolean isPaternalUncle()
    {
        return this.heirType.equals(PATERNAL_UNCLE);
    }

    public boolean isCousin()
    {
        return this.heirType.equals(COUSIN);
    }

    public boolean isPaternalCousin()
    {
        return this.heirType.equals(PATERNAL_COUSIN);
    }

    public boolean isPaternalCousinSon()
    {
        return this.heirType.equals(PATERNAL_COUSINS_SON);
    }

    public boolean isCousinGrandson()
    {
        return this.heirType.equals(COUSINS_GRANDSON);
    }

    public boolean isPaternalCousinGrandson()
    {
        return this.heirType.equals(PATERNAL_COUSINS_GRANDSON);
    }

    public boolean isCousinSon()
    {
        return this.heirType.equals(COUSINS_SON);
    }

    public static String[] getMaleHeirs()
    {
        return new String[]
        {
            HUSBAND,
            SON,
            GRANDSON,
            FATHER,
            GRANDFATHER,
            FULL_BROTHER,
            PATERNAL_BROTHER,
            MATERNAL_BROTHER,
            NEPHEW,
            PATERNAL_NEPHEW,
            NEPHEWS_SON,
            PATERNAL_NEPHEWS_SON,
            UNCLE,
            PATERNAL_UNCLE,
            COUSIN,
            PATERNAL_COUSIN,
            COUSINS_SON,
            PATERNAL_COUSINS_SON,
            COUSINS_GRANDSON,
            PATERNAL_COUSINS_GRANDSON
        };
    }

    public static String[] getFemaleHeirs()
    {
        return new String[]
        {
            WIFE,
            DAUGTHER,
            GRANDDAUGHTER,
            MOTHER,
            PATERNAL_GRANDMOTHER,
            MATERNAL_GRANDMOTHER,
            FULL_SISTER,
            PATERNAL_SISTER,
            MATERNAL_SISTER
        };
    }
}
