/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.CodeGenerator;

import TAM.Machine;
import Triangle.AbstractSyntaxTrees.AST;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Diego Murillo
 */
public class RecursivePatcher {
    private HashMap<AST, List<Integer>> subscriptions;
    private Encoder encoder;
    
    public RecursivePatcher(Encoder encoder){
        this.subscriptions = new HashMap();
        this.encoder = encoder;
    }
    
    public void addSubscription(AST decl, int inst){
        if(!subscriptions.containsKey(decl))
            subscriptions.put(decl, new ArrayList());
        subscriptions.get(decl).add(inst);
    }
    
    public void patchSubscribers(AST decl){
        if(subscriptions.get(decl) == null)
            return;
        for(Integer i : subscriptions.get(decl)){
            ObjectAddress address = ((KnownRoutine) decl.entity).address;
            int type = Machine.code[i].d;
            switch(type){
                case -1://identifier
                    int frameLevel = Machine.code[i].n;
                   
                    Machine.code[i].n = encoder.displayRegister(frameLevel, address.level);
                    Machine.code[i].d = address.displacement;
                    break;
                    
                case -2://proc actual parameter
                    frameLevel = Machine.code[i].r;
                    
                    Machine.code[i].r = encoder.displayRegister(frameLevel, address.level);
                    Machine.code[i].d = 0;
                    Machine.code[i+1].d = address.displacement;
                    break;
                    
                case -3://func actual parameter
                    frameLevel = Machine.code[i].r;
                    
                    Machine.code[i].r = encoder.displayRegister(frameLevel, address.level);
                    Machine.code[i].d = 0;
                    Machine.code[i+1].d = address.displacement;
                    break;
                
            }
            
        }
        this.subscriptions.remove(decl);
    }
}
