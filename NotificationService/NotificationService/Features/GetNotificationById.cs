using MediatR;
using Microsoft.AspNetCore.Http;
using Microsoft.EntityFrameworkCore;
using NotificationService.Context;
using NotificationService.Helpers;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;


namespace NotificationService.Features
{
    public class GetNotificationById
    {
        public class Query : IRequest<IEnumerable<Response>>
        {
            public string Token { get; set; }
        }
        public class QueryHandler : IRequestHandler<Query, IEnumerable<Response>>
        {
            private readonly NotificationDbContext _dbContext;
            public QueryHandler(NotificationDbContext notificationDbContext)
            {
                _dbContext = notificationDbContext ?? throw new ArgumentNullException(nameof(notificationDbContext));
            }
            public async Task<IEnumerable<Response>> Handle(Query request, CancellationToken cancellationToken)
            {
                var handler = new JwtSecurityTokenHandler();
                
                var jwtSecurityToken = handler.ReadJwtToken(request.Token);

                var result = jwtSecurityToken.Claims.First(claim => claim.Type == "nameid").Value;
                int userId = Int32.Parse(result);

                var response = await _dbContext.Notifications
                     .Where(x => x.UserId == userId)
                     .OrderByDescending(x => x.TimeSent)
                     .AsNoTracking()
                     .Select(notification => new Response
                     {
                         NotificationSubject = notification.Subject,
                         NotificationMessage = notification.Body,
                         IsRead = notification.IsActive,
                         UserID = notification.UserId,
                         Url = notification.URL,
                         TimeSent = notification.TimeSent
                     }
                    )
                     .ToListAsync(cancellationToken);
                var response1 = await _dbContext.PublicNotifications
                    .OrderByDescending(x => x.TimeSent)
                    .AsNoTracking()
                    .Select(notification => new Response
                    {
                        NotificationSubject = notification.Subject,
                        NotificationMessage = notification.Body,
                        IsRead = notification.IsActive,
                        UserID = 0,
                        Url = notification.URL,
                        TimeSent = notification.TimeSent
                    }).ToListAsync();
                response.AddRange(response1);
                return response.OrderByDescending(s=>s.TimeSent);
            }
        }
        public class Response
        {
            public string NotificationSubject { get; set; }
            public string NotificationMessage { get; set; }
            public bool IsRead { get; set; }
            public int? UserID { get; set; }
            public string? Url { get; set; }
            public DateTime TimeSent { get; set; }
        }

    }
}
