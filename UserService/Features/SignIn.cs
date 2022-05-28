using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using MediatR;
using Microsoft.AspNetCore.Identity;
using UserService.Helpers;
using UserService.Model;

namespace UserService.Features
{
    public class SignIn
    {
        public class Command : IRequest<Response>
        {
            public  string Email { get; set; }
            public string Password { get; set; }
        }

        public class CommandHandler : IRequestHandler<Command, Response>
        {
            private readonly SignInManager<User> _signInManager;
            private readonly UserManager<User> _userManager;

            public CommandHandler(SignInManager<User> signInManager, UserManager<User> userManager)
            {
                _signInManager = signInManager;
                _userManager = userManager;
            }
            public async Task<Response> Handle(Command request, CancellationToken cancellationToken)
            {
                var user = await _userManager.FindByEmailAsync(request.Email);
                if (user == null) throw new Exception($"User with email {request.Email} could not be found");

                var signinResult = await _signInManager.PasswordSignInAsync(user.Email, request.Password, false, false);

                if (!signinResult.Succeeded) throw new Exception("Incorrect Email or Password.");

                var userRoles = await _userManager.GetRolesAsync(user);
                
                return new Response
                {
                    Id = user.Id,
                    FirstName = user.Name,
                    LastName = user.LastName,
                    Email = user.Email,
                    Role = userRoles.FirstOrDefault(),
                    Token = JwtHelper.GenerateToken(await _userManager.GetClaimsAsync(user)),
                   
                };

                throw new NotImplementedException();
            }
        }

        public class Response
        {
            public int Id { get; set; }
            public string FirstName { get; set; }
            public string LastName { get; set; }
            public string Email { get; set; }
            public string Role { get; set; }
            public string Token { get; set; }
        }
    }
}
