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
    public class SendMessageToUser
    {
        public class Command : IRequest<Notification>
        {
            public string Subject { get; set; }
            public string Body { get; set; }
            public string? Url { get; set; }
            public int UserId { get; set; }
            public Notification toEntity()
            {
                return new Notification
                {
                    Subject = this.Subject,
                    Body = this.Body,
                    URL = Url,
                    TimeSent = DateTime.Now,
                    IsActive = true,
                    UserId = this.UserId,
                };
            }
        }
        public class CommandHandler : IRequestHandler<Command, Notification>
        {
            private readonly NotificationDbContext _dbContext;
            private NotificationUserHub _notificationHub;
            public CommandHandler(NotificationDbContext context, NotificationUserHub notificationHub)
            {
                _dbContext = context;
                _notificationHub = notificationHub;
            }
            public async Task<Notification> Handle(Command request, CancellationToken cancellationToken)
            {
                var notification = request.toEntity();
                _dbContext.Notifications.Add(notification);
                await _dbContext.SaveChangesAsync(cancellationToken);
                var userId = request.UserId.ToString();
                 _notificationHub.SendToUser(userId,notification);
                return notification;
            }
        }
    }
}
