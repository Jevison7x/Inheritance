/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inheritance.heirs;

import java.util.List;

/**
 *
 * @author user
 */
public class Portion
{
    private Deceased deceased;
    
    private List<Deceased.Asset> assets;
    
    Heir heir;

    public Portion() 
    {
        
    }

    public Deceased getDeceased() 
    {
        return deceased;
    }

    public void setDeceased(Deceased deceased) 
    {
        this.deceased = deceased;
    }

    public Heir getHeir() 
    {
        return heir;
    }

    public void setHeir(Heir heir) 
    {
        this.heir = heir;
    }

    public List<Deceased.Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Deceased.Asset> assets) {
        this.assets = assets;
    }
    
    
}
