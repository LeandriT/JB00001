/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
 options = {
  pattern: 'yyyy-mm', // Default is 'mm/yyyy' and separator char is not mandatory
  selectedYear: (new Date()).getFullYear(),
  startYear: (new Date()).getFullYear() - 20,
  finalYear: (new Date()).getFullYear() + 20,
  monthNames: ['ENE', 'FEB', 'MAR', 'ABR', 'MAY', 'JUN', 'JUL', 'AGO', 'SEP', 'OCT', 'NOV', 'DIC']
 };
 $('.monthPicker').monthpicker(options);
});

$('.yearSelect').yearselect({
 start: (new Date()).getFullYear() - 20,
 end: (new Date()).getFullYear() + 20
});