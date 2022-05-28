using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using MediatR;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using UserService.Context;
using UserService.Model;

namespace UserService.Features
{
    public class GetUserById 
    {
        public class Query : IRequest<Response>
        {
            public int Id { get; set; }
        }

        public class QueryHandler : IRequestHandler<Query, Response>
        {
            private readonly UserContext _userContext;
            private readonly UserManager<User> _userManager;

            public QueryHandler(UserContext userContext, UserManager<User> userManager)
            {
                _userContext = userContext;
                _userManager = userManager;
            }
            public async Task<Response> Handle(Query request, CancellationToken cancellationToken)
            {
                return await _userContext
                    .Users
                    .Where(x => x.Id == request.Id)
                    .Select(x => new Response
                    {
                        Id = x.Id,
                        FirstName = x.Name,
                        LastName = x.LastName,
                        PersonalId = x.PersonalID,
                        Role = "Admin"
                    }).FirstOrDefaultAsync(cancellationToken);
            }
        }
        public class Response
        {
            public int Id { get; set; }
            public string FirstName { get; set; }
            public string LastName { get; set; }
            public string PersonalId { get; set; }
            public string Role { get; set; }
        }
    }
}
