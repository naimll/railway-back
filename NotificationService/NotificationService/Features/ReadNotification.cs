using MediatR;
using Microsoft.EntityFrameworkCore;
using NotificationService.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace NotificationService.Features
{
    public class ReadNotification
    {
        public class Send : INotification
        {
            public int notificationID { get; set; }
        }
        public class SendHandler : INotificationHandler<Send>
        {
            private readonly NotificationDbContext _notificationDb;
            public SendHandler(NotificationDbContext notificationDbContext)
            {
                _notificationDb = notificationDbContext ?? throw new ArgumentNullException(nameof(notificationDbContext));
            }
            public async Task Handle(Send notification, CancellationToken cancellationToken)
            {
                var notificationT = await _notificationDb
                    .Notifications
                    .Where(x => x.Id == notification.notificationID)
                    .FirstOrDefaultAsync(cancellationToken);
                notificationT.IsActive = false;
                await _notificationDb.SaveChangesAsync(cancellationToken);
            }
        }
    }
}
