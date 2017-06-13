using ApiWebService.Entity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Description;

namespace ApiWebService.Controllers
{
    public class UsuarioController : ApiController
    {

        //metodo para registrar usuario

        //metodo para iniciar sesion de usuario
        [ResponseType(typeof(Boolean))]
        public async Task<IHttpActionResult> ComprobarExisteUsuario(string correo, string pass)
        {
            var a = new AccesoDatos(); //instancia de acceso a datos

            bool existe = a.comprobarUsuarioRegistrado(correo, pass);
            if (existe)
                return Ok();
            else
                return NotFound();
        }

        [Route("usuarios/all")]
        [HttpGet] //Always explicitly state the accepted HTTP method
        public IHttpActionResult All()
        {
            var a = new AccesoDatos();

            var usuarios = a.getUsuarios();
            //Get movies
            return Json(usuarios);
        }

    }
}
