/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mod;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import com.example.mod.Persona;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

/**
 *
 * @author leand
 */
@Entity
@Table(name = "telefono")
@XmlRootElement
@NamedQueries({
 @NamedQuery(name = "Telefono.findAll", query = "SELECT t FROM Telefono t")
 , @NamedQuery(name = "Telefono.findById", query = "SELECT t FROM Telefono t WHERE t.id = :id")
 , @NamedQuery(name = "Telefono.findByNumero", query = "SELECT t FROM Telefono t WHERE t.numero = :numero")
 , @NamedQuery(name = "Telefono.findByTipo", query = "SELECT t FROM Telefono t WHERE t.tipo = :tipo")})
public class Telefono implements Serializable {

 private static final long serialVersionUID = 1L;
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Basic(optional = false)
 @Column(name = "id")
 private Integer id;
 @Column(name = "numero")
 private String numero;
 @Column(name = "tipo")
 private String tipo;
 @JoinColumn(name = "idpersona", referencedColumnName = "id")
 @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 private Persona idpersona;

 public Telefono() {
 }

 public Telefono(Integer id) {
  this.id = id;
 }

 public Integer getId() {
  return id;
 }

 public void setId(Integer id) {
  this.id = id;
 }

 public String getNumero() {
  return numero;
 }

 public void setNumero(String numero) {
  this.numero = numero;
 }

 public String getTipo() {
  return tipo;
 }

 public void setTipo(String tipo) {
  this.tipo = tipo;
 }

 public Persona getIdpersona() {
  return idpersona;
 }

 public void setIdpersona(Persona idpersona) {
  this.idpersona = idpersona;
 }

 @Override
 public int hashCode() {
  int hash = 0;
  hash += (id != null ? id.hashCode() : 0);
  return hash;
 }

 @Override
 public boolean equals(Object object) {
  // TODO: Warning - this method won't work in the case the id fields are not set
  if (!(object instanceof Telefono)) {
   return false;
  }
  Telefono other = (Telefono) object;
  if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
   return false;
  }
  return true;
 }

 @Override
 public String toString() {
  return "com.example.mod[ id=" + id + " ]";
 }

}
