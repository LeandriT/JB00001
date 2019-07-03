/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.contr;

import com.example.ejb.PersonaFacade;
import com.example.mod.Persona;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@RequestScoped
public class nombreValidator implements Validator {

 @EJB
 private PersonaFacade ejb;

 @Override
 public void validate(FacesContext context, UIComponent component,
         Object value) throws ValidatorException {
  String nombre = String.valueOf(value).toUpperCase();
  System.out.println("Valor: " + nombre);
  List<Persona> lst = ejb.findWhere("upper(o.nombre)='" + nombre + "'", null);
  if (!lst.isEmpty()) {
   FacesMessage message = new FacesMessage(
           FacesMessage.SEVERITY_ERROR, "Existe Nombre de Persona",
           "Verifique: Nombre de Persona ya se encuentra registrado.");
   throw new ValidatorException(message);
  }

 }

}
