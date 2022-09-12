using Microsoft.AspNetCore.SignalR;
using NotificationService.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NotificationService.Hubs
{
    public class NotificationUserHub : Hub<NotificationUserHub>
    {
        private readonly UserConnectionManager _userConnectionManager;
        private readonly IHubContext<NotificationUserHub> hubContext;
        public NotificationUserHub(UserConnectionManager userConnectionManager, IHubContext<NotificationUserHub> h)
        {
            _userConnectionManager = userConnectionManager;
            hubContext = h;
        }
        public string GetConnectionId()
        {
            var httpContext = this.Context.GetHttpContext();
            var userId = httpContext.Request.Query["userId"];
            _userConnectionManager.KeepUserConnection(userId, Context.ConnectionId);

            return Context.ConnectionId;
        }
        public override Task OnConnectedAsync()
        {
            GetConnectionId();
            return base.OnConnectedAsync();
        }

        //Called when a connection with the hub is terminated.
        public async override Task OnDisconnectedAsync(Exception exception)
        {
            //get the connectionId
            var connectionId = Context.ConnectionId;
            _userConnectionManager.RemoveUserConnection(connectionId);
            var value = await Task.FromResult(0);//adding dump code to follow the template of Hub > OnDisconnectedAsync
        }
        public async void SendToUser(string userId, Notification userNotification)
        {
            var connections = _userConnectionManager.GetUserConnections(userId);
            if (connections != null && connections.Count > 0)
            {
                foreach (var connectionId in connections)
                {
                    await hubContext.Clients.Client(connectionId).SendAsync("sendToUser", userNotification);
                    //await Clients.Client(connectionId).SendAsync("sendToUser", userNotification);
                }
            }

        }
    }
}
