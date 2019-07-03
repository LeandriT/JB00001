/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ejb;

import com.example.mod.Persona;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author leand
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

 @PersistenceContext(unitName = UNIDAD_PERSISTENCIA)
 private EntityManager em; // atributo shadow por si se tiene mas de una unidad de peristencia

 public PersonaFacade() {
  super(Persona.class);
 }

 @Override
 protected EntityManager getEntityManager() {
  return em;
 }

 public List findWhere(String where, String order) {
  String jqpl = "SELECT o FROM Persona o where ";
  if (order != null) {
   jqpl += " " + where + " order by " + order;
  } else {
   jqpl += " " + where + " ";
  }
  return em.createQuery(jqpl).getResultList();
 }
}
