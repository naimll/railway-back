using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MediatR;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using UserService.Features;

namespace UserService.Controllers
{
    [Authorize]
    [ApiController]
    [Route("api/v1/accounts")]
    public class AccountController : Controller
    {
        private IMediator _mediator;

        public AccountController(IMediator mediator) => _mediator = mediator;
        
        [AllowAnonymous]
        [HttpPost("sign-up")]
        public async Task<IActionResult> SignUp([FromBody] SignUp.Command command)
        {
            var result = await _mediator.Send(command);
            return Ok(result);
        }
        [AllowAnonymous]
        [HttpPost("sign-in")]
        public async Task<IActionResult> SignIn([FromBody] SignIn.Command command)
        {
            var result = await _mediator.Send(command);
            return Ok(result);
        }

        [Authorize(Policy = "AuthorizedUser")]
        [HttpGet]
        public async Task<IActionResult> GetUserById(int Id)
        {
            var result = await _mediator.Send(new GetUserById.Query { Id = Id });
            return Ok(result);
        }
    }
}
