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
public abstract interface Inheritable
{

    //Standard Heirs
    public static final String HUSBAND = "Husband";

    public static final String WIFE = "Wife";

    public static final String SON = "Son";

    public static final String DAUGTHER = "Daughter";

    public static final String GRANDSON = "Grandson";

    public static final String GRANDDAUGHTER = "Granddaughter";

    public static final String FATHER = "Father";

    public static final String MOTHER = "Mother";

    public static final String GRANDFATHER = "Grandfather";

    public static final String PATERNAL_GRANDMOTHER = "Paternal Grandmother";

    public static final String MATERNAL_GRANDMOTHER = "Maternal Grandmother";

    public static final String FULL_BROTHER = "Full Brother";

    public static final String FULL_SISTER = "Full Sister";

    public static final String PATERNAL_BROTHER = "Paternal Brother";

    public static final String PATERNAL_SISTER = "Paternal Sister";

    public static final String MATERNAL_BROTHER = "Maternal Brother";

    public static final String MATERNAL_SISTER = "Maternal Sister";

    public static final String NEPHEW = "Nephew";

    public static final String PATERNAL_NEPHEW = "Paternal Nephew";

    public static final String NEPHEWS_SON = "Nephew's Son";

    public static final String PATERNAL_NEPHEWS_SON = "Paternal Nephew's Son";

    public static final String UNCLE = "Uncle";

    public static final String PATERNAL_UNCLE = "Paternal Uncle";

    public static final String COUSIN = "Cousin";

    public static final String PATERNAL_COUSIN = "Paternal Cousin";

    public static final String COUSINS_SON = "Cousin's Son";

    public static final String PATERNAL_COUSINS_SON = "Paternal Cousin's Son";

    public static final String COUSINS_GRANDSON = "Cousin's Grandson";

    public static final String PATERNAL_COUSINS_GRANDSON = "Paternal Cousin's Grandson";

    public static final String[] STANDARD_HEIRS =
    {
        "Deceased", //00
        HUSBAND, //01
        WIFE, //02
        SON, //03
        DAUGTHER, //04
        GRANDSON, //05
        GRANDDAUGHTER, //06
        FATHER, //07
        MOTHER, //08
        GRANDFATHER, //09
        PATERNAL_GRANDMOTHER, //10
        MATERNAL_GRANDMOTHER, //11
        FULL_BROTHER, //12
        FULL_SISTER, //13
        PATERNAL_BROTHER, //14
        PATERNAL_SISTER, //15
        MATERNAL_BROTHER, //16
        MATERNAL_SISTER, //17
        NEPHEW, //18
        PATERNAL_NEPHEW, //19
        NEPHEWS_SON, //20
        PATERNAL_NEPHEWS_SON, //21
        UNCLE, //22
        PATERNAL_UNCLE, //23
        COUSIN, //24
        PATERNAL_COUSIN, //25
        COUSINS_SON, //26
        PATERNAL_COUSINS_SON, //27
        COUSINS_GRANDSON, //28
        PATERNAL_COUSINS_GRANDSON //29
    };

    //array of blockable
    //array of nonblockable
    public abstract String getHeirType();

    public abstract void setHeirType(Deceased deceased, String heirType) throws InheritanceException;
}
