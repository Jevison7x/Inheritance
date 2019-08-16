/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inheritance.rules;

import com.inheritance.heirs.Deceased;
import com.inheritance.heirs.Heir;
import static com.inheritance.heirs.Inheritable.*;
import java.util.List;

/**
 *
 * @author Jevison7x
 */
public final class BlockingRules
{

    public static void blockingRuleA(Deceased deceased)
    {
        if(deceased.hasSon())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case GRANDSON:
                    case GRANDDAUGHTER:
                    case FULL_BROTHER:
                    case FULL_SISTER:
                    case PATERNAL_BROTHER:
                    case PATERNAL_SISTER:
                    case MATERNAL_BROTHER:
                    case MATERNAL_SISTER:
                    case NEPHEW:
                    case PATERNAL_NEPHEW:
                    case NEPHEWS_SON:
                    case PATERNAL_NEPHEWS_SON:
                    case UNCLE:
                    case PATERNAL_UNCLE:
                    case COUSIN:
                    case PATERNAL_COUSIN:
                    case COUSINS_SON:
                    case PATERNAL_COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by son");
                        break;
                }
            }
        }
    }

    public static void blockingRuleB(Deceased deceased)
    {
        if(deceased.hasGrandSonFromSon())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case FULL_BROTHER:
                    case FULL_SISTER:
                    case PATERNAL_BROTHER:
                    case PATERNAL_SISTER:
                    case MATERNAL_BROTHER:
                    case MATERNAL_SISTER:
                    case NEPHEW:
                    case PATERNAL_NEPHEW:
                    case NEPHEWS_SON:
                    case PATERNAL_NEPHEWS_SON:
                    case UNCLE:
                    case PATERNAL_UNCLE:
                    case COUSIN:
                    case PATERNAL_COUSIN:
                    case COUSINS_SON:
                    case PATERNAL_COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by grandson from son");
                        break;
                }
            }
        }
    }

    public static void blockingRuleC(Deceased deceased)
    {
        if(deceased.hasFather())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case GRANDFATHER:
                    case PATERNAL_GRANDMOTHER:
                    case FULL_BROTHER:
                    case FULL_SISTER:
                    case PATERNAL_BROTHER:
                    case PATERNAL_SISTER:
                    case MATERNAL_BROTHER:
                    case MATERNAL_SISTER:
                    case NEPHEW:
                    case PATERNAL_NEPHEW:
                    case NEPHEWS_SON:
                    case PATERNAL_NEPHEWS_SON:
                    case UNCLE:
                    case PATERNAL_UNCLE:
                    case COUSIN:
                    case PATERNAL_COUSIN:
                    case COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by father");
                        break;
                }
            }
        }
    }

    public static void blockingRuleD(Deceased deceased)
    {
        if(!deceased.hasNotMother())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case PATERNAL_GRANDMOTHER:
                    case MATERNAL_GRANDMOTHER:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by mother");
                        break;
                }
            }
        }
    }

    public static void blockingRuleE(Deceased deceased)
    {
        if(deceased.hasGrandFather())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case NEPHEW:
                    case PATERNAL_NEPHEW:
                    case NEPHEWS_SON:
                    case PATERNAL_NEPHEWS_SON:
                    case UNCLE:
                    case PATERNAL_UNCLE:
                    case COUSIN:
                    case PATERNAL_COUSIN:
                    case COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by grandfather");
                        break;
                }
            }
        }
    }

    public static void blockingRuleF(Deceased deceased)
    {
        if(!deceased.hasNoFullBrother())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case PATERNAL_BROTHER:
                    case PATERNAL_SISTER:
                    case MATERNAL_BROTHER:
                    case MATERNAL_SISTER:
                    case NEPHEW:
                    case PATERNAL_NEPHEW:
                    case NEPHEWS_SON:
                    case PATERNAL_NEPHEWS_SON:
                    case UNCLE:
                    case PATERNAL_UNCLE:
                    case COUSIN:
                    case PATERNAL_COUSIN:
                    case COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by full brother");
                        break;
                }
            }
        }
    }

    public static void blockingRuleG(Deceased deceased)
    {
        if((deceased.hasManyFullSisters() || deceased.hasOnlyOneFullSister()) && (deceased.hasOnlyOneDaughter() || deceased.hasMultipleDaughter()))
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case PATERNAL_BROTHER:
                    case PATERNAL_SISTER:
                    case MATERNAL_BROTHER:
                    case MATERNAL_SISTER:
                    case NEPHEW:
                    case PATERNAL_NEPHEW:
                    case NEPHEWS_SON:
                    case PATERNAL_NEPHEWS_SON:
                    case UNCLE:
                    case PATERNAL_UNCLE:
                    case COUSIN:
                    case PATERNAL_COUSIN:
                    case COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by full sister and daughter");
                        break;
                }
            }
        }
    }

    public static void blockingRuleH(Deceased deceased)
    {
        if(!deceased.hasNoPaternalBrother())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case NEPHEW:
                    case PATERNAL_NEPHEW:
                    case NEPHEWS_SON:
                    case PATERNAL_NEPHEWS_SON:
                    case UNCLE:
                    case PATERNAL_UNCLE:
                    case COUSIN:
                    case PATERNAL_COUSIN:
                    case COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by paternal brother");
                        break;
                }
            }
        }
    }

    public static void blockingRuleI(Deceased deceased)
    {
        if((deceased.hasMultiplePaternalSisters() || deceased.hasOnlyOnePaternalSister()) && ((deceased.hasOnlyOneDaughter() || deceased.hasMultipleDaughter()) || deceased.hasMultiplePaternalSisters()))
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case NEPHEW:
                    case PATERNAL_NEPHEW:
                    case NEPHEWS_SON:
                    case PATERNAL_NEPHEWS_SON:
                    case UNCLE:
                    case PATERNAL_UNCLE:
                    case COUSIN:
                    case PATERNAL_COUSIN:
                    case COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by paternal sister and daughter");
                        break;
                }
            }
        }
    }

    public static void blockingRuleK(Deceased deceased)
    {
        if(deceased.hasPaternalNephew())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case NEPHEWS_SON:
                    case PATERNAL_NEPHEWS_SON:
                    case UNCLE:
                    case PATERNAL_UNCLE:
                    case COUSIN:
                    case PATERNAL_COUSIN:
                    case COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by paternal nephew");
                        break;
                }
            }
        }
    }

    public static void blockingRuleL(Deceased deceased)
    {
        if(deceased.hasNephewSon())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case PATERNAL_NEPHEWS_SON:
                    case UNCLE:
                    case PATERNAL_UNCLE:
                    case COUSIN:
                    case PATERNAL_COUSIN:
                    case COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by nephew's son");
                        break;
                }
            }
        }
    }

    public static void blockingRuleM(Deceased deceased)
    {
        if(deceased.hasPaternalNephewSon())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case UNCLE:
                    case PATERNAL_UNCLE:
                    case COUSIN:
                    case PATERNAL_COUSIN:
                    case COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by paternal nephew's son");
                        break;
                }
            }
        }
    }

    public static void blockingRuleN(Deceased deceased)
    {
        if(deceased.hasUncle())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case PATERNAL_UNCLE:
                    case COUSIN:
                    case PATERNAL_COUSIN:
                    case COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by uncle");
                        break;
                }
            }
        }
    }

    public static void blockingRuleO(Deceased deceased)
    {
        if(deceased.hasPaternalUncle())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case COUSIN:
                    case PATERNAL_COUSIN:
                    case COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by paternal uncle");
                        break;
                }
            }
        }
    }

    public static void blockingRuleP(Deceased deceased)
    {
        if(deceased.hasCousin())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case PATERNAL_COUSIN:
                    case COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by cousin");
                        break;
                }
            }
        }
    }

    public static void blockingRuleQ(Deceased deceased)
    {
        if(deceased.hasPaternalCousin())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case COUSINS_SON:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by paternal cousin");
                        break;
                }
            }
        }
    }

    public static void blockingRuleR(Deceased deceased)
    {
        if(deceased.hasCousinSon())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case PATERNAL_COUSIN:
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by cousin's son");
                        break;
                }
            }
        }
    }

    public static void blockingRuleS(Deceased deceased)
    {
        if(deceased.hasPaternalCousinSon())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case COUSINS_GRANDSON:
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by paternal cousin's son");
                        break;
                }
            }
        }
    }

    public static void blockingRuleT(Deceased deceased)
    {
        if(deceased.hasPaternalCousinSon())
        {
            List<Heir> heirs = deceased.getHeirs();
            for(Heir heir : heirs)
            {
                String heirType = heir.getHeirType();
                switch(heirType)
                {
                    case PATERNAL_COUSINS_GRANDSON:
                        heir.doBlock();
                        heir.setBlockReason("Blocked by full cousin's son");
                        break;
                }
            }
        }
    }
}
