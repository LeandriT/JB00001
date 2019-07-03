/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validaPalabraGuionBajo(e) {
 var val = e.key;
 var key = e.keyCode || e.which;
 var regex = new RegExp("[0-9a-zA-Z_ ]");
 if ((regex.test(val) || key == 8)) {
  return true;
 } else {
  e.preventDefault();
  return false;
 }
}
function validaSoloPalabra(e) {
 var val = e.key;
 var key = e.keyCode || e.which;
 var regex = new RegExp("[a-zA-ZñÑ ]");
 if ((regex.test(val) || key == 8)) {
  return true;
 } else {
  e.preventDefault();
  return false;
 }
}
function validaPalabraSinGuionBajo(e) {
 var val = e.key;
 var key = e.keyCode || e.which;
 var regex = new RegExp("[a-zA-Z ]");
 if ((regex.test(val) || key == 8)) {
  return true;
 } else {
  e.preventDefault();
  return false;
 }
}
function validaPalabraNumeroEspacio(e) {
 var val = e.key;
 var key = e.keyCode || e.which;
 var regex = new RegExp("[0-9a-zA-ZñÑ ]");
 if ((regex.test(val) || key == 8)) {
  return true;
 } else {
  e.preventDefault();
  return false;
 }
}
function validaPalabraNumeroEspacioTildes(e) {
 var val = e.key;
 var key = e.keyCode || e.which;
 var regex = new RegExp("[0-9a-zA-ZñÑÁÉÍÓÚáéíóú ]");
 if ((regex.test(val) || key == 8)) {
  return true;
 } else {
  e.preventDefault();
  return false;
 }
}
function validaPalabra(e) {
 var val = e.key;
 var key = e.keyCode || e.which;
 var regex = new RegExp("[0-9ñÑáéíóúÁÉÍÓÚa-zA-Z_ ]");
 if ((regex.test(val) || key == 8)) {
  return true;
 } else {
  e.preventDefault();
  return false;
 }
}
function validaPalabraConGuionBajo(e) {
 var val = e.key;
 var key = e.keyCode || e.which;
 var regex = new RegExp("[ñÑáéíóúÁÉÍÓÚA-Za-z_]");
 if (regex.test(val) || key == 8) {
  return true;
 } else {
  e.preventDefault();
  return false;
 }
}
function validaPalabraConGuionMedioBajo(e) {
 var val = e.key;
 var key = e.keyCode || e.which;
 var regex = new RegExp("[ñÑáéíóúÁÉÍÓÚA-Za-z0-9_\-]+$");
 if (regex.test(val) || key == 8) {
  return true;
 } else {
  e.preventDefault();
  return false;
 }
}
function validaPalabraConGuionMedioBajoEspacio(e) {
 var val = e.key;
 var key = e.keyCode || e.which;
 var regex = new RegExp("[a-zA-Z0-9-ÁÉÍÓÚáéíóúñÑ\\.,_-\\s]*");
 if (regex.test(val) || key == 8) {
  return true;
 } else {
  e.preventDefault();
  return false;
 }
}

function validaNumero(e) {
 var val = e.key;
 var key = e.keyCode || e.which;
 var regex = new RegExp("[0-9]+");
 if ((regex.test(val) || key == 8)) {
  return true;
 } else {
  e.preventDefault();
  return false;
 }
}

function validaPalabraConGuionMedioBajoPuntoComa(e) {
 var val = e.key;
 var key = e.keyCode || e.which;
 var regex = new RegExp("[ñÑáéíóúÁÉÍÓÚA-Za-z0-9¿? ]");
 if (regex.test(val) || key == 8) {
  return true;
 } else {
  e.preventDefault();
  return false;
 }
}


function numeroDecimal(evt, tam)
{
 var key = evt.key;
 var value = evt.currentTarget.value.trim();
 if (value.length == 0)
 {
  if ((/[0-9]/.test(key) || key == 8) && value.length < tam) {
   return true;
  } else
  {
   evt.preventDefault();
   return false;
  }
 } else if (!value.includes(".")) {

  if ((/[\.]/.test(key) || key == 8) && value.length < tam) {
   return true;
  }
  if ((/[0-9]/.test(key) || key == 8) && value.length < tam) {
   return true;
  } else
  {
   evt.preventDefault();
   return false;
  }

 } else if (value.includes(".")) {
  if ((/[0-9]/.test(key) || key == 8) && value.length < tam) {
   var vec = value.split('.');
   if (vec.length == 2)
   {
    if (vec[1].length > 1)
    {
     var valor = parseFloat(value).toFixed(2);
     evt.currentTarget.value = valor;
     evt.preventDefault();
     return false;
    }
   }

   return true;
  } else
  {
   evt.preventDefault();
   return false;
  }
 }


}