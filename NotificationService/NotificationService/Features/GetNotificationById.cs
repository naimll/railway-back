using MediatR;
using Microsoft.EntityFrameworkCore;
using NotificationService.Context;
using NotificationService.Helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace NotificationService.Features
{
    public class GetNotificationById
    {
        public class Query : IRequest<IEnumerable<Response>>
        {

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
                var userId = UserHelper.GetUserId();
                return await _dbContext.Notifications
                     .Where(x => x.UserId == userId || x.UserId == null)
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
