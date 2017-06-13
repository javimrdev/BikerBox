using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Web;

namespace ApiWebService.Entity
{
    public class AccesoDatos
    {
        private BikerBoxDBEntities DBContext;

        public AccesoDatos()
        {
            DBContext = new BikerBoxDBEntities();
        }

        #region Usuarios
        public List<Usuarios> getUsuarios()
        {
            var usuarios = DBContext.Usuarios.Where(s => s.id > 0).ToList();
            return usuarios;
        }

        //metodo para comprobar en la vista si el usuario esta registrado
        public bool comprobarUsuarioRegistrado(string correo, string pass)
        {
            bool existe = false;
            using (MD5 md5Hash = MD5.Create())
            {
                string hash = Controllers.Helper.GetMd5Hash(md5Hash, pass);
                var usuario = DBContext.BB_LoginUsuario.Where(w => w.correo == correo & w.contrasena == hash);
                if (usuario != null)
                    existe = true;
            }
            return existe;
        }
        #endregion

        #region Talleres

        //metodo para comprobar en la vista si el Taller esta registrado
        public bool comprobarTallerRegistrado(string correo, string pass)
        {
            bool existe = false;
            using (MD5 md5Hash = MD5.Create())
            {
                string hash = Controllers.Helper.GetMd5Hash(md5Hash, pass);
                var usuario = DBContext.BB_LoginTaller.Where(w => w.correo == correo & w.contrasena == hash);
                if (usuario != null)
                    existe = true;
            }
            return existe;
        }

        #endregion

    }
}
