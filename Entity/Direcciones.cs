//------------------------------------------------------------------------------
// <auto-generated>
//     Este código se generó a partir de una plantilla.
//
//     Los cambios manuales en este archivo pueden causar un comportamiento inesperado de la aplicación.
//     Los cambios manuales en este archivo se sobrescribirán si se regenera el código.
// </auto-generated>
//------------------------------------------------------------------------------

namespace ApiWebService.Entity
{
    using System;
    using System.Collections.Generic;
    
    public partial class Direcciones
    {
        public int id { get; set; }
        public string calle { get; set; }
        public Nullable<byte> numero { get; set; }
        public string letra { get; set; }
        public Nullable<int> idTaller { get; set; }
    
        public virtual Talleres Talleres { get; set; }
    }
}
