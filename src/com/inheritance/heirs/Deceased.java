/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inheritance.heirs;

import com.inheritance.rules.CommonSense;
import com.inheritance.rules.InheritanceException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to represent the deceased fellow in the application.
 *
 * @author Jevison Archibong
 * @author Jed Adeyemi
 * @since 15/02/2016
 */
public class Deceased extends Person
{

    /**
     * This field holds the list of assets that belongs to the deceased. This is the assets that will be divided among the heirs.
     */
    private List<Asset> assets;

    /**
     * This field holds the list of heirs that survived the deceased. Also all members of this list must be a standard heir according to the rules of Islamic inheritance.
     */
    private List<Heir> heirs;

    /**
     * The total value of all the assets of this deceased.
     */
    private double totalAssetValue;

    /**
     * This is the sole constructor for the Deceased class
     *
     * @param name the name of the deceased.
     * @param age the age of the deceased.
     * @param gender the gender of the deceased.
     */
    public Deceased(String name, int age, String gender)
    {
        //The super class must be initialized first.
        super(name, age, gender);
        //Initialize the list of assets.
        this.assets = new ArrayList<>();
        //Initialize the list of heirs.
        this.heirs = new ArrayList<>();
    }

    /**
     * This method is used to add an asset to the list of assets.
     *
     * @param asset the asset that will be added to the list.
     * @see #removeAsset(com.inheritance.heirs.Deceased.Asset)
     */
    public void addAsset(Asset asset)
    {
        this.assets.add(asset);
        this.totalAssetValue += asset.getAssetValue();
    }

    /**
     * This method is used to remove an asset from the list of assets.
     *
     * @param asset the asset that will be removed from the list.
     * @see #addAsset(com.inheritance.heirs.Deceased.Asset)
     */
    public void removeAsset(Asset asset)
    {
        this.assets.remove(asset);
    }

    /**
     * This method is used to add a Heir to the list of heirs that survived the deceased.
     *
     * @param heir A heir that will be added. <b>Must be a standard heir according to Islamic rules.</b>
     * @throws InheritanceException this method will throw InheritanceException if the heir (that is about to be added) is a wife and the deceased already has the maximum number of wives which is 4.
     * @see #removeHeir(com.inheritance.heirs.Heir)
     */
    public void addHeir(Heir heir) throws InheritanceException
    {
        //Check to ensure the decease does not exceed maximum of 4 wives.
        if(heir.isWife() && CommonSense.hasMaximumWives(this))
            throw new InheritanceException("Cannot exceed maximum of 4 wives.");
        else
            this.heirs.add(heir);
    }

    /**
     * This method is used to remove a heir from the list of heirs.
     *
     * @param heir the heir that is to be removed.
     * @see #addHeir(com.inheritance.heirs.Heir)
     */
    public void removeHeir(Heir heir)
    {
        this.heirs.remove(heir);
    }

    /**
     * This method is used to get all list of heirs that survived the deceased.
     *
     * @return a {@link java.util.List} object that contains all the surviving heirs.
     */
    public List<Heir> getHeirs()
    {
        return this.heirs;
    }

    public List<Asset> getAssets()
    {
        return assets;
    }

    public void setAssets(List<Asset> assets)
    {
        this.totalAssetValue = 0.0D;
        for(Asset asset : assets)
            this.totalAssetValue += asset.getAssetValue();
        this.assets = assets;
    }

    public double getTotalAssetValue()
    {
        return totalAssetValue;
    }

    /**
     * This method is used to check if the deceased has a biological child (son or daughter) called offspring that is alive presently
     *
     * @return true if the deceased has offspring, false otherwise.
     */
    public boolean hasOffspring()
    {
        for(Heir heir : this.heirs)
            if(heir.isSon() || heir.isDaughter())
                return true;
        return false;
    }

    public boolean hasHusband()
    {
        for(Heir heir : this.heirs)
            if(heir.isHusband())
                return true;
        return false;
    }

    /**
     * This method is used to check if the deceased has only one daughter
     *
     * @return true if the deceased has only one daughter, false otherwise.
     */
    public boolean hasOnlyOneDaughter()
    {
        int noOfOfDaughter = 0;
        for(Heir heir : this.heirs)
        {
            if(heir.isDaughter())
                noOfOfDaughter++;
            if(noOfOfDaughter > 1)
                return false;
        }
        if(noOfOfDaughter == 1)
            return true;
        else
            return false;
    }

    public boolean hasMultipleDaughter()
    {
        int noOfOfDaughter = 0;
        for(Heir heir : this.heirs)
            if(heir.isDaughter())
                noOfOfDaughter++;
        if(noOfOfDaughter > 1)
            return true;
        else
            return false;
    }

    /**
     * This method checks if the deceased has no son at all.
     *
     * @return true if the deceased does not have a son, false otherwise.
     */
    public boolean hasNoSon()
    {
        for(Heir heir : this.heirs)
            if(heir.isSon())
                return false;
        return true;
    }

    public boolean hasSon()
    {
        return !this.hasNoSon();
    }

    public boolean hasOnlyOneGrandDaughterFromASon()
    {
        int noOfGrandDaughter = 0;
        for(Heir heir : this.heirs)
            if(heir.isGrandDaughter())
                noOfGrandDaughter++;
        if(noOfGrandDaughter > 1)
            return false;
        if(noOfGrandDaughter == 1)
            return true;
        else
            return false;
        //are we sure all granddaughters are from sons
    }

    public boolean hasManyGrandDaughterFromSon()
    {
        int noOfGrandDaughter = 0;
        for(Heir heir : this.heirs)
            if(heir.isGrandDaughter())
                noOfGrandDaughter++;
        if(noOfGrandDaughter > 1)
            return true;
        else
            return false;
        //are we sure all granddaughters are from sons
    }

    public boolean hasNoGrandSonFromSon()
    {
        for(Heir heir : this.heirs)
            if(heir.isGrandSon())
                return false;
        return true;
        //are we sure all grandson are from sons
    }

    public boolean hasGrandSonFromSon()
    {
        return !this.hasNoGrandSonFromSon();
    }

    public boolean hasNotMultipleSiblings()
    {
        int noOfSiblings = 0;
        for(Heir heir : this.heirs)
            if(heir.isFullBrother() || heir.isFullSister() || heir.isMaternalBrother() || heir.isMaternalSister()
                    || heir.isPaternalBrother() || heir.isPaternalSister())
                noOfSiblings++;
        if(noOfSiblings > 1)
            return false;
        else
            return true;
    }

    public boolean hasMultipleSiblings()
    {
        int noOfSiblings = 0;
        for(Heir heir : this.heirs)
            if(heir.isFullBrother() || heir.isFullSister() || heir.isMaternalBrother() || heir.isMaternalSister()
                    || heir.isPaternalBrother() || heir.isPaternalSister())
                noOfSiblings++;
        if(noOfSiblings > 1)
            return true;
        else
            return false;
    }

    public boolean hasFather()
    {
        return !this.hasNoFather();
    }

    public boolean hasNoFather()
    {
        for(Heir heir : this.heirs)
            if(heir.isFather())
                return false;
        return true;
    }

    public boolean hasNotMother()
    {
        for(Heir heir : this.heirs)
            if(heir.isMother())
                return false;
        return true;
    }

    public boolean hasNotMaternalGrandMother()
    {
        for(Heir heir : this.heirs)
            if(heir.isMaternalGrandMother())
                return false;
        return true;
    }

    public boolean hasNotPaternalGrandMother()
    {
        for(Heir heir : this.heirs)
            if(heir.isPaternalGrandMother())
                return false;
        return true;
    }

    public boolean hasOnlyOneFullSister()
    {
        int noOfFullSister = 0;
        for(Heir heir : this.heirs)
            if(heir.isFullSister())
                noOfFullSister++;
        if(noOfFullSister > 1)
            return false;
        if(noOfFullSister == 1)
            return true;
        else
            return false;
    }

    public boolean hasNoMalePaternalAncestor()
    {
        for(Heir heir : this.heirs)
            if(heir.isFather() || heir.isGrandFather())
                return false;
        return true;
    }

    public boolean hasNoFullBrother()
    {
        for(Heir heir : this.heirs)
            if(heir.isFullBrother())
                return false;
        return true;
    }

    public boolean hasManyFullSisters()
    {
        int noOfFullSister = 0;
        for(Heir heir : this.heirs)
            if(heir.isFullSister())
                noOfFullSister++;
        if(noOfFullSister > 1)
            return true;
        else
            return false;
    }

    public boolean hasOnlyOnePaternalSister()
    {
        int noOfPaternalSister = 0;
        for(Heir heir : this.heirs)
            if(heir.isPaternalSister())
            {
                noOfPaternalSister++;
                if(noOfPaternalSister > 1)
                    return false;
            }
        if(noOfPaternalSister == 1)
            return true;
        else
            return false;
    }

    public boolean hasOnlyOneMaternalSibling()
    {
        int noOfMaternalSibling = 0;
        for(Heir heir : this.heirs)
            if(heir.isMaternalBrother() || heir.isMaternalSister())
            {
                noOfMaternalSibling++;
                if(noOfMaternalSibling > 1)
                    return false;
            }
        if(noOfMaternalSibling == 1)
            return true;
        else
            return false;
    }

    public boolean hasManyMaternalSibling()
    {
        int noOfMaternalSibling = 0;
        for(Heir heir : this.heirs)
            if(heir.isMaternalBrother() || heir.isMaternalSister())
            {
                noOfMaternalSibling++;
                if(noOfMaternalSibling > 1)
                    return true;
            }
        return false;
    }

    public boolean hasNoFullSiblingOrPaternalBrother()
    {
        for(Heir heir : this.heirs)
            if(heir.isFullBrother() || heir.isFullSister() || heir.isPaternalBrother())
                return false;
        return true;
    }

    public boolean hasNoPaternalBrother()
    {
        for(Heir heir : this.heirs)
            if(heir.isPaternalBrother())
                return false;
        return true;
    }

    public boolean hasMultiplePaternalSisters()
    {
        int noOfPaternalSisters = 0;
        for(Heir heir : this.heirs)
            if(heir.isPaternalSister())
                noOfPaternalSisters++;
        if(noOfPaternalSisters > 1)
            return true;
        else
            return false;
    }

    public boolean hasGrandFather()
    {
        for(Heir heir : this.heirs)
            if(heir.isGrandFather())
                return true;
        return false;
    }

    public boolean hasNephew()
    {
        for(Heir heir : this.heirs)
            if(heir.isNephew())
                return true;
        return false;
    }

    public boolean hasPaternalNephew()
    {
        for(Heir heir : this.heirs)
            if(heir.isPaternalNephew())
                return true;
        return false;
    }

    public boolean hasNephewSon()
    {
        for(Heir heir : this.heirs)
            if(heir.isNephewSon())
                return true;
        return false;
    }

    public boolean hasPaternalNephewSon()
    {
        for(Heir heir : this.heirs)
            if(heir.isPaternalNephewSon())
                return true;
        return false;
    }

    public boolean hasUncle()
    {
        for(Heir heir : this.heirs)
            if(heir.isUncle())
                return true;
        return false;
    }

    public boolean hasPaternalUncle()
    {
        for(Heir heir : this.heirs)
            if(heir.isPaternalUncle())
                return true;
        return false;
    }

    public boolean hasCousin()
    {
        for(Heir heir : this.heirs)
            if(heir.isCousin())
                return true;
        return false;
    }

    public boolean hasPaternalCousin()
    {
        for(Heir heir : this.heirs)
            if(heir.isPaternalCousin())
                return true;
        return false;
    }

    public boolean hasCousinSon()
    {
        for(Heir heir : this.heirs)
            if(heir.isCousinSon())
                return true;
        return false;
    }

    public boolean hasPaternalCousinSon()
    {
        for(Heir heir : this.heirs)
            if(heir.isPaternalCousinSon())
                return true;
        return false;
    }

    public boolean hasCousinGrandSon()
    {
        for(Heir heir : this.heirs)
            if(heir.isCousinGrandson())
                return true;
        return false;
    }

    public class Asset
    {

        /**
         * The name of this asset.
         */
        private String assetName;

        /**
         * The value of this asset.
         */
        private double assetValue;

        /**
         * Used to instantiate the class Asset.
         */
        public Asset()
        {

        }

        /**
         * Used to instantiate the class Asset with the asset name and value.
         *
         * @param name The name of this asset.
         * @param value The value of this asset.
         */
        public Asset(String name, double value)
        {
            this.assetName = name;
            this.assetValue = value;
        }

        /**
         * The Getter method used to retrieve the name of this asset.
         *
         * @return a {@link java.lang.String} Object that represents the name of the asset.
         */
        public String getAssetName()
        {
            return assetName;
        }

        /**
         * The Setter method used to set the name of this asset.
         *
         * @param assetName The name of this asset.
         */
        public void setAssetName(String assetName)
        {
            this.assetName = assetName;
        }

        /**
         * The Getter method used to retrieve the value of this asset
         *
         * @return a <code>double</code> value representing the value of this asset.
         */
        public double getAssetValue()
        {
            return assetValue;
        }

        /**
         * The Setter method used to set the value of this asset.
         *
         * @param assetValue The value of this asset.
         */
        public void setAssetValue(double assetValue)
        {
            this.assetValue = assetValue;
        }
    }
}
