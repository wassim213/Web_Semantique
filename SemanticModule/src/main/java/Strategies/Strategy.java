/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

/**
 *
 * @author CodeFox
 */
public abstract class Strategy<E> {
    
    public Strategy() {
    }
    
    public abstract void resolve(E elt);
}
