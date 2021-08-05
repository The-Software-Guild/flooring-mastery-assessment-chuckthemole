/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Chuck
 */
public class States {
    public enum StateAbbreviation {
        AL, AK, AZ, AR, CA, CO, CT, DE, DC, FL,
        GA, HI, ID, IL, IN, IA, KS, KY, LA, ME,
        MD, MA, MI, MN, MS, MO, MT, NB, NV, NH, 
        NJ, NM, NY, NC, ND, OH, OK, OR, PA, RI,
        SC, SD, TN, TX, UT, VT, VA, WA, WV, WI,
        WY
    }
    
    public static Set<String> getStates() {
        StateAbbreviation[] states = StateAbbreviation.values();
        Set<String> statesAsSet = new HashSet<>();
        
        for (StateAbbreviation state : states) {
            statesAsSet.add(state.toString());
        }
        
        return statesAsSet;
    }
}
