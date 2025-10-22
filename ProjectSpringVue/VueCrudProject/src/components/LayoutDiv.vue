<template>
    <div class="header">Header here</div>
    <div class="container">
      <slot></slot>
    </div>
    <div class="footer">Footer here</div>
  </template>   
  
  <script>
   
  export default {
      name: 'LayoutDiv',
  };
  </script>
  <style scoped>
    .header{
        height: 50px;
        background-color: darkgreen;
        text-align: center;
        color:white;
    }
    .footer{
        background-color: darkgreen;
        text-align: center;        
        color:white;
        margin-top:20px;        
    }
    
  </style>