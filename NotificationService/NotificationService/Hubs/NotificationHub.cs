using Microsoft.AspNetCore.SignalR;
using NotificationService.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NotificationService.Hubs
{
    public class NotificationHub : Hub<NotificationHub>
    {
        public IHubContext<NotificationHub> _context;
        public NotificationHub(IHubContext<NotificationHub> context)
        {
            _context = context;
        }
        public Task SendToAll(PublicNotification notification)
        {
            return _context.Clients.All.SendAsync("ReceiveBroadcast", notification);
        }
    }
}
