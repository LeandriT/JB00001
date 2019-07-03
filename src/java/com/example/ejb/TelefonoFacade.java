/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ejb;

import com.example.mod.Telefono;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author leand
 */
@Stateless
public class TelefonoFacade extends AbstractFacade<Telefono> {

 @PersistenceContext(unitName = UNIDAD_PERSISTENCIA)
 private EntityManager em; // atributo shadow por si se tiene mas de una unidad de peristencia

 public TelefonoFacade() {
  super(Telefono.class);
 }

 @Override
 protected EntityManager getEntityManager() {
  return em;
 }

}
