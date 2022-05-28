using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using MediatR;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using UserService.Context;
using UserService.Helpers;
using UserService.Model;
using Guid = System.Guid;

namespace UserService.Features
{
    public class SignUp
    {
        public class Command : IRequest
        {
            public string FirstName { get; set; } = string.Empty;
            public string LastName { get; set; } = string.Empty;
            public string Email { get; set; } = string.Empty;
            public string Password { get; set; } = string.Empty;
            public int Role { get; set; }
            

            public User ToEntity()
            {
                return new User
                {
                    Name = FirstName,
                    LastName = LastName,
                    PersonalID = Guid.NewGuid().ToString(),
                    Email = Email,
                    NormalizedEmail = Email.ToUpper(),
                    UserName = Email,
                    NormalizedUserName = Email.ToUpper(),
                    BirthDate = DateTime.Now
                };
            }
        }

        public class CommandHandler : IRequestHandler<Command, Unit>
        {
            private readonly UserManager<User> _userManager;
            private readonly ApplicationDbContext _context;
            private readonly IMediator _mediator;
            public CommandHandler(
                UserManager<User> userManager,
                ApplicationDbContext context,
                IConfiguration configuration,
                IMediator mediator)
            {
                _userManager = userManager ?? throw new ArgumentNullException(nameof(userManager));
                _context = context ?? throw new ArgumentNullException(nameof(context));
                _mediator = mediator ?? throw new ArgumentNullException(nameof(Mediator));
            }

            public async Task<Unit> Handle(Command request, CancellationToken cancellationToken)
            {
                var user = request.ToEntity();

                var userResult = await _userManager.CreateAsync(user, request.Password);

                if (!userResult.Succeeded) throw new Exception("Something went wrong!");

                var role = await _context.Roles.FirstOrDefaultAsync(x => x.Id == request.Role);
                var roleResult = await _userManager.AddToRoleAsync(user, role.Name);

                if (!roleResult.Succeeded) throw new Exception("Something went wrong!");

                var claimResult = await _userManager.AddClaimsAsync(user, ClaimsHelper.Generate(user, role.Name));

                if (!claimResult.Succeeded) throw new Exception("Something went wrong!");

                

                return Unit.Value;
            }

           
        }
    }
}
