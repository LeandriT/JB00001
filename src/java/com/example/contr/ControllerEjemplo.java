/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.contr;

import com.example.ejb.PersonaFacade;
import com.example.ejb.TelefonoFacade;
import com.example.mod.Persona;
import com.example.mod.Telefono;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
//EN VIAS DE CREACION

/**
 *
 * @author leand
 */
@ManagedBean
@ViewScoped
public class ControllerEjemplo {

 //PERSONA
 private String usNombre;
 private String usApellidos;

 //TELEFONO
 private String nro;
 private String tipo;

 @EJB
 private PersonaFacade personaFacade;
 private List<Persona> lstPersona;

 @EJB
 private TelefonoFacade telefonoFacade;
 private List<Telefono> lstTelefono;

 //VER DATOS PERSONA
 private Persona personaVer;
 private Persona personaEditar;

 @PostConstruct
 public void init() {
  lstPersona = personaFacade.findAll();
  lstTelefono = new ArrayList<>();
 }

 public void agregar() {

  Telefono telf = new Telefono();

  telf.setNumero(nro);
  telf.setTipo(tipo);
  lstTelefono.add(telf);
  telf = null;
  nro = "";
  tipo = "";
  muestraMensajeInfoGrowl("Agregado Correctamente.");
 }

 public void eliminarTelefono(Telefono telf) {
  lstTelefono.remove(telf);
  muestraMensajeInfoGrowl("Borrado correctamente.");
 }

 public void guardarPersona() {
  try {
   if (!lstTelefono.isEmpty()) {
    Persona p = new Persona();
    p.setNombre(usNombre);
    p.setApellido(usApellidos);
    p.setTelefonoList(new ArrayList<>());
    Telefono t = null;
    for (int i = 0; i < 1000; i++) {
     for (Telefono telf : lstTelefono) {
      t = new Telefono();
      t.setIdpersona(p);
      t.setNumero(telf.getNumero() + i);
      t.setTipo(telf.getTipo());
      p.getTelefonoList().add(t);
      t = null;
     }
    }
    personaFacade.create(p);
    p = null;
    lstTelefono.clear();
    usNombre = "";
    usApellidos = "";
    lstPersona.clear();
    lstPersona = personaFacade.findAll();
    update("frm");
    muestraMensajeInfoGrowl("Creado Correctamente.");
   } else {
    muestraMensajeAdvertenciaGrowl("Agregue al menos un teléfono.");
   }
  } catch (Exception ex) {
   Logger.getLogger(ControllerEjemplo.class.getName()).log(Level.SEVERE, null, ex);
  }
 }

 public void encerarDatos() {
  usNombre = "";
  usApellidos = "";
  nro = "";
  tipo = "";
 }

 public void recargarDatos() {
  lstPersona.clear();
  lstPersona = personaFacade.findAll();
  update("frm");
 }

 public void cargarDatos(Persona p) {
  this.personaVer = p;
  System.out.println("//===> Telefonos: " + p.getTelefonoList().size() + " <==//");
  update("dlgVer");
  RequestContext.getCurrentInstance().execute("PF('dlgVer').show()");
 }

 public void cargarDatosEditar(Persona p) {
  this.personaEditar = p;
  usNombre = personaEditar.getNombre();
  usApellidos = personaEditar.getApellido();
  System.out.println("//===> Telefonos: " + p.getTelefonoList().size() + " <==//");
  update("frmEditarPersona");
  RequestContext.getCurrentInstance().execute("PF('dlgEditarPersona').show()");
 }

 public void editarPersona() {
  try {
   personaEditar.setNombre(usNombre);
   personaEditar.setApellido(usApellidos);
   personaFacade.edit(personaEditar);
   recargarDatos();
   encerarDatos();
   personaEditar = null;
   muestraMensajeInfoGrowl("Editado Correctamente.");
  } catch (Exception ex) {
   System.out.println("Exception: " + ex.getMessage());
  }
 }

 public void eliminarDatos(Persona p) {
  System.out.println("Eliminar: " + p.getId() + " Nombre: " + p.getNombre() + " Apellido: " + p.getApellido());
  for (Telefono t : p.getTelefonoList()) {
   System.out.println("Eliminado: " + t.getId());
   telefonoFacade.remove(t);
   break;
  }
  personaFacade.remove(p);
  recargarDatos();
 }

 public void buscarPersona() {
  if (StringUtils.isNotBlank(usNombre)) {
   List<Persona> lst = personaFacade.findWhere("o.nombre like '%" + usNombre + "%' ", null);
   if (!lst.isEmpty()) {
    personaVer = lst.get(0);
    update("dlgVer");
    RequestContext.getCurrentInstance().execute("PF('dlgVer').show()");
   } else {
    muestraMensajeInfoGrowl("Por favor ingrese el campo nombre para realizar la búsqueda.");
   }
  } else {
   muestraMensajeInfoGrowl("Por favor ingrese el campo nombre para realizar la búsqueda.");
  }
 }

 private void update(String id) {
  RequestContext.getCurrentInstance().update(id);
 }

 public void muestraMensajeError(String mensajeError) {
  FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
          "Advertencia", mensajeError);
  RequestContext.getCurrentInstance().showMessageInDialog(message);
 }

 public void muestraMensajeInfo(String mensajeInfo) {
  FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
          "Operación Exitosa", mensajeInfo);
  RequestContext.getCurrentInstance().showMessageInDialog(message);
 }

 public void muestraMensajeInfoGrowl(String mensajeInfo) {
  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensajeInfo, null));
 }

 public void muestraMensajeAdvertenciaGrowl(String mensajeError) {
  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeError, null));
 }

 public void muestraMensajeSeverityErrorGrowl(String mensajeError) {
  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeError, null));
 }

 public List<Persona> getLstPersona() {
  return lstPersona;
 }

 public void setLstPersona(List<Persona> lstPersona) {
  this.lstPersona = lstPersona;
 }

 public String getUsNombre() {
  return usNombre;
 }

 public void setUsNombre(String usNombre) {
  this.usNombre = usNombre;
 }

 public String getUsApellidos() {
  return usApellidos;
 }

 public void setUsApellidos(String usApellidos) {
  this.usApellidos = usApellidos;
 }

 public String getNro() {
  return nro;
 }

 public void setNro(String nro) {
  this.nro = nro;
 }

 public String getTipo() {
  return tipo;
 }

 public void setTipo(String tipo) {
  this.tipo = tipo;
 }

 public TelefonoFacade getTelefonoFacade() {
  return telefonoFacade;
 }

 public void setTelefonoFacade(TelefonoFacade telefonoFacade) {
  this.telefonoFacade = telefonoFacade;
 }

 public List<Telefono> getLstTelefono() {
  return lstTelefono;
 }

 public void setLstTelefono(List<Telefono> lstTelefono) {
  this.lstTelefono = lstTelefono;
 }

 public Persona getPersonaVer() {
  return personaVer;
 }

 public void setPersonaVer(Persona personaVer) {
  this.personaVer = personaVer;
 }

 public Persona getPersonaEditar() {
  return personaEditar;
 }

 public void setPersonaEditar(Persona personaEditar) {
  this.personaEditar = personaEditar;
 }
}
