using MediatR;
using NotificationService.Context;
using NotificationService.Hubs;
using NotificationService.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace NotificationService.Features
{
    public class SendMessageToAll 
    {
        public class Command : IRequest<PublicNotification>
        {
            public string Subject { get; set; }
            public string Body { get; set; }
            public string? Url { get; set; }
            public PublicNotification toEntity()
            {
                return new PublicNotification
                {
                    Subject = this.Subject,
                    Body = this.Body,
                    URL = Url,
                    TimeSent = DateTime.Now,
                    IsActive = true,
                };
            }
        }
        public class CommandHandler : IRequestHandler<Command, PublicNotification>
        {
            private readonly NotificationDbContext _dbContext;
            private NotificationHub _notificationHub;
            public CommandHandler(NotificationDbContext context, NotificationHub notificationHub)
            {
                _dbContext = context;
                _notificationHub = notificationHub;
            }
            public async Task<PublicNotification> Handle(Command request, CancellationToken cancellationToken)
            {
                var notification = request.toEntity();
                _dbContext.Add(notification);
                await _dbContext.SaveChangesAsync(cancellationToken);
                await _notificationHub.SendToAll(notification);
                return notification;
            }
        }
    }
}
