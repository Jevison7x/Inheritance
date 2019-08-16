/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inheritance.rules;

import com.inheritance.heirs.Deceased;
import com.inheritance.heirs.Heir;
import com.inheritance.heirs.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jevison7x
 */
public final class StandardRules
{

    public static double getHusbandPortion(Deceased deceased)
    {
        if(deceased.hasOffspring())
            return deceased.getTotalAssetValue() / 4.0D;
        else
            return deceased.getTotalAssetValue() / 2.0D;
    }

    public static double getWifePortion(Deceased deceased, int noOfWives)
    {
        if(deceased.hasOffspring())
            return deceased.getTotalAssetValue() / (8 * noOfWives);
        else
            return deceased.getTotalAssetValue() / (4 * noOfWives);
    }

    public static double getDaughterPortion(Deceased deceased)
    {
        int noOfDaughters = 0;
        int noOfSons = 0;
        int noOfWives = 0;
        double daughterPortion = 0.0;
        double remainingPortion = 0.0;
        List<Heir> theHeirs = deceased.getHeirs();
        for(Heir theHeir : theHeirs)
        {
            if(theHeir.isDaughter())
                ++noOfDaughters;
            else if(theHeir.isSon())
                ++noOfSons;
            else if(theHeir.isWife())
                ++noOfWives;
        }
        if(deceased.hasOnlyOneDaughter() && deceased.hasNoSon())
            return deceased.getTotalAssetValue() / 2;
        else if(deceased.hasMultipleDaughter() && deceased.hasNoSon())
            daughterPortion = (deceased.getTotalAssetValue() * 2) / (3 * noOfDaughters);
        //complete edited code below
        else if(!deceased.hasNoSon())
        {
            //get all deceased
            //List<Heir> heirs = deceased.getHeirs();
            List<Heir> unblockedHeirs = new ArrayList();
            double husbandPortion = 0.0;
            double wifePortion = 0.0;
            double allGrandsonPortion = 0.0;
            double allGranddaughterPortion = 0.0;
            double fatherPortion = 0.0;
            double motherPortion = 0.0;
            double grandfatherPortion = 0.0;
            double paternalGrandmotherPortion = 0.0;
            double maternalGrandmotherPortion = 0.0;
            double allFullBrotherPortion = 0.0;
            double allFullSisterPortion = 0.0;
            double allPaternalBrotherPortion = 0.0;
            double allPaternalSisterPortion = 0.0;
            double allMaternalBrotherPortion = 0.0;
            double allMaternalSisterPortion = 0.0;
            double allNephewPortion = 0.0;
            double allPaternalNephewPortion = 0.0;
            double allNephewSonPortion = 0.0;
            double allPaternalNephewSonPortion = 0.0;
            double allUnclePortion = 0.0;
            double allPaternalUnclePortion = 0.0;
            double allCousinPortion = 0.0;
            double allPaternalCousinPortion = 0.0;
            double allCousinSonPortion = 0.0;
            double allPaternalCousinSonPortion = 0.0;
            double allCousinGrandsonPortion = 0.0;
            double allPaternalCousinGrandsonPortion = 0.0;
            for(Heir heir : theHeirs)
            {
                if(heir.isBlockable())
                {
                    BlockingRules.blockingRuleA(deceased);
                    BlockingRules.blockingRuleB(deceased);
                    BlockingRules.blockingRuleC(deceased);
                    BlockingRules.blockingRuleD(deceased);
                    BlockingRules.blockingRuleE(deceased);
                    BlockingRules.blockingRuleF(deceased);
                    BlockingRules.blockingRuleG(deceased);
                    BlockingRules.blockingRuleH(deceased);
                    BlockingRules.blockingRuleI(deceased);
                    BlockingRules.blockingRuleK(deceased);
                    BlockingRules.blockingRuleL(deceased);
                    BlockingRules.blockingRuleM(deceased);
                    BlockingRules.blockingRuleN(deceased);
                    BlockingRules.blockingRuleO(deceased);
                    BlockingRules.blockingRuleP(deceased);
                    BlockingRules.blockingRuleQ(deceased);
                    BlockingRules.blockingRuleR(deceased);
                    BlockingRules.blockingRuleS(deceased);
                    BlockingRules.blockingRuleT(deceased);
                }
                if(heir.isBlocked() == false)
                {
                    if(heir.isHusband())
                    {
                        double theHusbandPortion = StandardRules.getHusbandPortion(deceased);
                        husbandPortion += theHusbandPortion;
                    }
                    else if(heir.isWife())
                    {
                        double theWifePortion = StandardRules.getWifePortion(deceased, noOfWives);
                        wifePortion += theWifePortion;
                    }
                    else if(heir.isGrandSon())
                    {
                        //double theGrandsonPortion = StandardRules.
                    }
                    else if(heir.isGrandDaughter())
                    {
                        double theGranddaughterPortion = StandardRules.getGrandDaughterPortion(deceased);
                        allGranddaughterPortion += theGranddaughterPortion;
                    }
                    else if(heir.isFather())
                    {
                        double theFatherPortion = StandardRules.getFatherPortion(deceased);
                        fatherPortion += theFatherPortion;
                    }
                    else if(heir.isMother())
                    {
                        double theMotherPortion = StandardRules.getMotherPortion(deceased);
                        motherPortion += theMotherPortion;
                    }
                    else if(heir.isGrandFather())
                    {
                        //
                    }
                    else if(heir.isPaternalGrandMother())
                    {
                        double thePaternalGrandmotherPortion = StandardRules.getPaternalGrandMotherPortion(deceased);
                        paternalGrandmotherPortion += thePaternalGrandmotherPortion;
                    }
                    else if(heir.isMaternalGrandMother())
                    {
                        double theMaternalGrandmotherPortion = StandardRules.getMaternalGrandMotherPortion(deceased);
                        maternalGrandmotherPortion += theMaternalGrandmotherPortion;
                    }
                    else if(heir.isFullBrother())
                    {
                        //
                    }
                    else if(heir.isFullSister())
                    {
                        double theFullSisterPortion = StandardRules.getFullSisterPortion(deceased);
                        allFullSisterPortion += theFullSisterPortion;
                    }
                    else if(heir.isPaternalBrother())
                    {
                        //double thePaternalBrotherPortion = StandardRules.;
                        //allFullSisterPortion += theFullSisterPortion;
                    }
                    else if(heir.isPaternalSister())
                    {
                        double thePaternalSisterPortion = StandardRules.getPaternalSisterPortion(deceased);
                        allPaternalSisterPortion += thePaternalSisterPortion;
                    }
                    else if(heir.isMaternalBrother())
                    {
                        double theMaternalBrotherPortion = StandardRules.getMaternalSiblingPortion(deceased);
                        allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isMaternalSister())
                    {
                        double theMaternalSisterPortion = StandardRules.getMaternalSiblingPortion(deceased);
                        allMaternalSisterPortion += theMaternalSisterPortion;
                    }
                    else if(heir.isNephew())
                    {
                        //double theNephewPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isPaternalNephew())
                    {
                        //double thePaternalNephewPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isNephewSon())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isPaternalNephewSon())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isUncle())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isPaternalUncle())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isCousin())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isCousinSon())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isPaternalCousinSon())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isCousinGrandson())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isPaternalCousinGrandson())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    unblockedHeirs.add(heir);
                }
            }
            if(deceased.getGender().equals(Person.MALE))
            {
                remainingPortion = deceased.getTotalAssetValue() - (wifePortion + allGrandsonPortion + allGranddaughterPortion + fatherPortion
                        + motherPortion + grandfatherPortion + paternalGrandmotherPortion + maternalGrandmotherPortion
                        + allFullBrotherPortion + allFullSisterPortion + allPaternalBrotherPortion
                        + allPaternalSisterPortion + allMaternalBrotherPortion + allMaternalSisterPortion
                        + allNephewPortion + allPaternalNephewPortion + allNephewSonPortion + allPaternalNephewSonPortion
                        + allUnclePortion + allPaternalUnclePortion + allCousinPortion + allPaternalCousinPortion
                        + allCousinSonPortion + allPaternalCousinSonPortion + allCousinGrandsonPortion
                        + allPaternalCousinGrandsonPortion);

                daughterPortion = remainingPortion / (noOfDaughters + (noOfSons * 2));
            }
            else if(deceased.getGender().equals(Person.FEMALE))
            {
                remainingPortion = deceased.getTotalAssetValue() - (husbandPortion + allGrandsonPortion + allGranddaughterPortion + fatherPortion
                        + motherPortion + grandfatherPortion + paternalGrandmotherPortion + maternalGrandmotherPortion
                        + allFullBrotherPortion + allFullSisterPortion + allPaternalBrotherPortion
                        + allPaternalSisterPortion + allMaternalBrotherPortion + allMaternalSisterPortion
                        + allNephewPortion + allPaternalNephewPortion + allNephewSonPortion + allPaternalNephewSonPortion
                        + allUnclePortion + allPaternalUnclePortion + allCousinPortion + allPaternalCousinPortion
                        + allCousinSonPortion + allPaternalCousinSonPortion + allCousinGrandsonPortion
                        + allPaternalCousinGrandsonPortion);

                daughterPortion = remainingPortion / (noOfDaughters + (noOfSons * 2));
            }
        }

        return daughterPortion;
    }

    public static double getSonPortion(Deceased deceased)
    {
        int noOfDaughters = 0;
        int noOfSons = 0;
        int noOfWives = 0;
        double daughterPortion = 0.0;
        double remainingPortion = 0.0;
        double sonPortion = 0.0;
        List<Heir> theHeirs = deceased.getHeirs();
        for(Heir theHeir : theHeirs)
        {
            if(theHeir.isDaughter())
                ++noOfDaughters;
            else if(theHeir.isSon())
                ++noOfSons;
            else if(theHeir.isWife())
                ++noOfWives;
        }
        if(!deceased.hasNoSon())
        {
            //get all deceased heirs
            //List<Heir> heirs = deceased.getHeirs();
            List<Heir> unblockedHeirs = new ArrayList();
            double husbandPortion = 0.0;
            double wifePortion = 0.0;
            double allGrandsonPortion = 0.0;
            double allGranddaughterPortion = 0.0;
            double fatherPortion = 0.0;
            double motherPortion = 0.0;
            double grandfatherPortion = 0.0;
            double paternalGrandmotherPortion = 0.0;
            double maternalGrandmotherPortion = 0.0;
            double allFullBrotherPortion = 0.0;
            double allFullSisterPortion = 0.0;
            double allPaternalBrotherPortion = 0.0;
            double allPaternalSisterPortion = 0.0;
            double allMaternalBrotherPortion = 0.0;
            double allMaternalSisterPortion = 0.0;
            double allNephewPortion = 0.0;
            double allPaternalNephewPortion = 0.0;
            double allNephewSonPortion = 0.0;
            double allPaternalNephewSonPortion = 0.0;
            double allUnclePortion = 0.0;
            double allPaternalUnclePortion = 0.0;
            double allCousinPortion = 0.0;
            double allPaternalCousinPortion = 0.0;
            double allCousinSonPortion = 0.0;
            double allPaternalCousinSonPortion = 0.0;
            double allCousinGrandsonPortion = 0.0;
            double allPaternalCousinGrandsonPortion = 0.0;
            for(Heir heir : theHeirs)
            {
                if(heir.isBlockable())
                {
                    BlockingRules.blockingRuleA(deceased);
                    BlockingRules.blockingRuleB(deceased);
                    BlockingRules.blockingRuleC(deceased);
                    BlockingRules.blockingRuleD(deceased);
                    BlockingRules.blockingRuleE(deceased);
                    BlockingRules.blockingRuleF(deceased);
                    BlockingRules.blockingRuleG(deceased);
                    BlockingRules.blockingRuleH(deceased);
                    BlockingRules.blockingRuleI(deceased);
                    BlockingRules.blockingRuleK(deceased);
                    BlockingRules.blockingRuleL(deceased);
                    BlockingRules.blockingRuleM(deceased);
                    BlockingRules.blockingRuleN(deceased);
                    BlockingRules.blockingRuleO(deceased);
                    BlockingRules.blockingRuleP(deceased);
                    BlockingRules.blockingRuleQ(deceased);
                    BlockingRules.blockingRuleR(deceased);
                    BlockingRules.blockingRuleS(deceased);
                    BlockingRules.blockingRuleT(deceased);
                }
                if(heir.isBlocked() == false)
                {
                    if(heir.isHusband())
                    {
                        double theHusbandPortion = StandardRules.getHusbandPortion(deceased);
                        husbandPortion += theHusbandPortion;
                    }
                    else if(heir.isWife())
                    {
                        double theWifePortion = StandardRules.getWifePortion(deceased, noOfWives);
                        wifePortion += theWifePortion;
                    }
                    else if(heir.isGrandSon())
                    {
                        //double theGrandsonPortion = StandardRules.
                    }
                    else if(heir.isGrandDaughter())
                    {
                        double theGranddaughterPortion = StandardRules.getGrandDaughterPortion(deceased);
                        allGranddaughterPortion += theGranddaughterPortion;
                    }
                    else if(heir.isFather())
                    {
                        double theFatherPortion = StandardRules.getFatherPortion(deceased);
                        fatherPortion += theFatherPortion;
                    }
                    else if(heir.isMother())
                    {
                        double theMotherPortion = StandardRules.getMotherPortion(deceased);
                        motherPortion += theMotherPortion;
                    }
                    else if(heir.isGrandFather())
                    {
                        //
                    }
                    else if(heir.isPaternalGrandMother())
                    {
                        double thePaternalGrandmotherPortion = StandardRules.getPaternalGrandMotherPortion(deceased);
                        paternalGrandmotherPortion += thePaternalGrandmotherPortion;
                    }
                    else if(heir.isMaternalGrandMother())
                    {
                        double theMaternalGrandmotherPortion = StandardRules.getMaternalGrandMotherPortion(deceased);
                        maternalGrandmotherPortion += theMaternalGrandmotherPortion;
                    }
                    else if(heir.isFullBrother())
                    {
                        //
                    }
                    else if(heir.isFullSister())
                    {
                        double theFullSisterPortion = StandardRules.getFullSisterPortion(deceased);
                        allFullSisterPortion += theFullSisterPortion;
                    }
                    else if(heir.isPaternalBrother())
                    {
                        //double thePaternalBrotherPortion = StandardRules.;
                        //allFullSisterPortion += theFullSisterPortion;
                    }
                    else if(heir.isPaternalSister())
                    {
                        double thePaternalSisterPortion = StandardRules.getPaternalSisterPortion(deceased);
                        allPaternalSisterPortion += thePaternalSisterPortion;
                    }
                    else if(heir.isMaternalBrother())
                    {
                        double theMaternalBrotherPortion = StandardRules.getMaternalSiblingPortion(deceased);
                        allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isMaternalSister())
                    {
                        double theMaternalSisterPortion = StandardRules.getMaternalSiblingPortion(deceased);
                        allMaternalSisterPortion += theMaternalSisterPortion;
                    }
                    else if(heir.isNephew())
                    {
                        //double theNephewPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isPaternalNephew())
                    {
                        //double thePaternalNephewPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isNephewSon())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isPaternalNephewSon())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isUncle())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isPaternalUncle())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isCousin())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isCousinSon())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isPaternalCousinSon())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isCousinGrandson())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    else if(heir.isPaternalCousinGrandson())
                    {
                        //double theNephewSonPortion = StandardRules.;
                        //allMaternalBrotherPortion += theMaternalBrotherPortion;
                    }
                    unblockedHeirs.add(heir);
                }
            }
            if(deceased.getGender().equals(Person.MALE))
            {
                remainingPortion = deceased.getTotalAssetValue() - (wifePortion + allGrandsonPortion + allGranddaughterPortion + fatherPortion
                        + motherPortion + grandfatherPortion + paternalGrandmotherPortion + maternalGrandmotherPortion
                        + allFullBrotherPortion + allFullSisterPortion + allPaternalBrotherPortion
                        + allPaternalSisterPortion + allMaternalBrotherPortion + allMaternalSisterPortion
                        + allNephewPortion + allPaternalNephewPortion + allNephewSonPortion + allPaternalNephewSonPortion
                        + allUnclePortion + allPaternalUnclePortion + allCousinPortion + allPaternalCousinPortion
                        + allCousinSonPortion + allPaternalCousinSonPortion + allCousinGrandsonPortion
                        + allPaternalCousinGrandsonPortion);

                daughterPortion = remainingPortion / (noOfDaughters + (noOfSons * 2));
                sonPortion = 2 * daughterPortion;
            }
            else if(deceased.getGender().equals(Person.FEMALE))
            {
                remainingPortion = deceased.getTotalAssetValue() - (husbandPortion + allGrandsonPortion + allGranddaughterPortion + fatherPortion
                        + motherPortion + grandfatherPortion + paternalGrandmotherPortion + maternalGrandmotherPortion
                        + allFullBrotherPortion + allFullSisterPortion + allPaternalBrotherPortion
                        + allPaternalSisterPortion + allMaternalBrotherPortion + allMaternalSisterPortion
                        + allNephewPortion + allPaternalNephewPortion + allNephewSonPortion + allPaternalNephewSonPortion
                        + allUnclePortion + allPaternalUnclePortion + allCousinPortion + allPaternalCousinPortion
                        + allCousinSonPortion + allPaternalCousinSonPortion + allCousinGrandsonPortion
                        + allPaternalCousinGrandsonPortion);

                daughterPortion = remainingPortion / (noOfDaughters + (noOfSons * 2));
                sonPortion = 2 * daughterPortion;
            }

        }
        /* if(deceased.getGender().equals(Person.MALE))
            {
                remainingPortion = deceased.getTotalAssetValue() - ((StandardRules.getWifePortion(deceased, noOfWives) * noOfWives) + StandardRules.getFatherPortion(deceased) + StandardRules.getMotherPortion(deceased));
                daughterPortion = remainingPortion / (noOfDaughters + (noOfSons * 2));
                sonPortion = 2 * daughterPortion;
            }
            else if(deceased.getGender().equals(Person.FEMALE))
            {
                remainingPortion = deceased.getTotalAssetValue() - (StandardRules.getHusbandPortion(deceased) + StandardRules.getFatherPortion(deceased) + StandardRules.getMotherPortion(deceased));
                daughterPortion = remainingPortion / (noOfDaughters + (noOfSons * 2));
                sonPortion = 2 * daughterPortion;
            }*/
        return sonPortion;
    }

    public static double getGrandDaughterPortion(Deceased deceased)
    {
        int noOfGrandDaughters = 0;
        Double grandDaughterPortion = null;
        List<Heir> theHeirs = deceased.getHeirs();
        for(Heir theHeir : theHeirs)
        {
            if(theHeir.isGrandDaughter())
                ++noOfGrandDaughters;
        }
        if(deceased.hasOnlyOneGrandDaughterFromASon() && !deceased.hasOffspring() && deceased.hasNoGrandSonFromSon())
            return deceased.getTotalAssetValue() / 2;
        else if(deceased.hasManyGrandDaughterFromSon() && !deceased.hasOffspring() && deceased.hasNoGrandSonFromSon())
            grandDaughterPortion = (deceased.getTotalAssetValue() * 2) / (3 * noOfGrandDaughters);
        else if(deceased.hasOnlyOneDaughter() && deceased.hasNoSon() && deceased.hasNoGrandSonFromSon())
            grandDaughterPortion = (deceased.getTotalAssetValue() * 2) / (3 * noOfGrandDaughters);
        return grandDaughterPortion;
    }

    public static double getFatherPortion(Deceased deceased)
    {
        Double fatherPortion = null;
        if(deceased.hasOffspring())
            fatherPortion = deceased.getTotalAssetValue() / 6;
        return fatherPortion;
    }

    public static double getMotherPortion(Deceased deceased)
    {
        Double motherPortion = null;
        if(!deceased.hasOffspring() && deceased.hasNotMultipleSiblings())
            motherPortion = deceased.getTotalAssetValue() / 3;
        else if(deceased.hasOffspring() || deceased.hasMultipleSiblings())
            motherPortion = deceased.getTotalAssetValue() / 6;
        return motherPortion;
    }

    public static double getPaternalGrandFatherPortion(Deceased deceased)
    {
        Double paternalGrandFatherPortion = null;
        if(deceased.hasNoFather() && deceased.hasOffspring())
            paternalGrandFatherPortion = deceased.getTotalAssetValue() / 6;
        return paternalGrandFatherPortion;
    }

    public static double getPaternalGrandMotherPortion(Deceased deceased)
    {
        Double paternalGrandFatherPortion = null;
        if(deceased.hasNotMother() && deceased.hasNoFather() && deceased.hasNotMaternalGrandMother())
            paternalGrandFatherPortion = deceased.getTotalAssetValue() / 6;
        else if(deceased.hasNotMother() && deceased.hasNoFather() && !deceased.hasNotMaternalGrandMother())
            paternalGrandFatherPortion = deceased.getTotalAssetValue() / 12;
        return paternalGrandFatherPortion;
    }

    public static double getMaternalGrandMotherPortion(Deceased deceased)
    {
        Double maternalGrandFatherPortion = null;
        if(deceased.hasNotMother())
            maternalGrandFatherPortion = deceased.getTotalAssetValue() / 6;
        if(deceased.hasNotMother() && deceased.hasNoFather() && !deceased.hasNotPaternalGrandMother())
            maternalGrandFatherPortion = deceased.getTotalAssetValue() / 12;
        return maternalGrandFatherPortion;
    }

    public static double getFullSisterPortion(Deceased deceased)
    {
        int noOfFullSisters = 0;
        Double fullSisterPortion = null;
        List<Heir> theHeirs = deceased.getHeirs();
        for(Heir theHeir : theHeirs)
        {
            if(theHeir.isFullSister())
                ++noOfFullSisters;
        }
        if(deceased.hasOnlyOneFullSister() && !deceased.hasOffspring() && deceased.hasNoMalePaternalAncestor() && deceased.hasNoFullBrother())
            fullSisterPortion = deceased.getTotalAssetValue() / 2;
        else if(deceased.hasManyFullSisters() && !deceased.hasOffspring() && deceased.hasNoMalePaternalAncestor() && deceased.hasNoFullBrother())
            fullSisterPortion = (deceased.getTotalAssetValue() * 2) / (3 * noOfFullSisters);
        return fullSisterPortion;
    }

    public static double getPaternalSisterPortion(Deceased deceased)
    {
        int noOfPaternalSisters = 0;
        Double paternalSisterPortion = null;
        List<Heir> theHeirs = deceased.getHeirs();
        for(Heir theHeir : theHeirs)
        {
            if(theHeir.isPaternalSister())
                ++noOfPaternalSisters;
        }
        if(deceased.hasOnlyOnePaternalSister() && !deceased.hasOffspring() && deceased.hasNoMalePaternalAncestor() && deceased.hasNoFullSiblingOrPaternalBrother())
            paternalSisterPortion = deceased.getTotalAssetValue() / 2;
        else if(deceased.hasMultiplePaternalSisters() && !deceased.hasOffspring() && deceased.hasNoMalePaternalAncestor() && deceased.hasNoFullSiblingOrPaternalBrother())
            paternalSisterPortion = deceased.getTotalAssetValue() * 2 / (3 * noOfPaternalSisters);
        else if(deceased.hasOnlyOneFullSister() && !deceased.hasOffspring() && deceased.hasNoMalePaternalAncestor() && deceased.hasNoFullBrother() && deceased.hasNoPaternalBrother())
            paternalSisterPortion = deceased.getTotalAssetValue() / 6;
        return paternalSisterPortion;
    }

    public static double getMaternalSiblingPortion(Deceased deceased)
    {
        int noOfMaternalSibling = 0;
        Double maternalSisterPortion = null;
        List<Heir> theHeirs = deceased.getHeirs();
        for(Heir theHeir : theHeirs)
        {
            if(theHeir.isMaternalBrother() || theHeir.isMaternalSister())
                ++noOfMaternalSibling;
        }
        if(deceased.hasOnlyOneMaternalSibling() && deceased.hasNoSon() && deceased.hasNoMalePaternalAncestor())
            maternalSisterPortion = deceased.getTotalAssetValue() / 6;
        else if(deceased.hasManyMaternalSibling() && deceased.hasNoSon() && deceased.hasNoMalePaternalAncestor())
            maternalSisterPortion = deceased.getTotalAssetValue() / (3 * noOfMaternalSibling);
        else if(deceased.hasOnlyOneFullSister() && !deceased.hasOffspring() && deceased.hasNoMalePaternalAncestor() && deceased.hasNoFullBrother() && deceased.hasNoPaternalBrother())
            maternalSisterPortion = deceased.getTotalAssetValue() / 6;
        return maternalSisterPortion;
    }

    /*
     * public static double getNephewPortion(Deceased deceased, int noOfNephew)
     * {
     * Double nephewPortion = null;
     * }
     */
}
