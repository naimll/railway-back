using MediatR;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.SignalR;
using NotificationService.Features;
using NotificationService.Hubs;
using NotificationService.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NotificationService.Controllers
{
    [Route("api/v1/notification")]
    public class NotificationController : BaseController
    {
        public NotificationHub _notificationHub;
        public IHubContext<NotificationUserHub> _userHub;
        public UserConnectionManager _userConnectionManager;


        public NotificationController(NotificationHub notificationHub, IHubContext<NotificationUserHub> notificationUserHub, UserConnectionManager userConnectionManager)
        {
            _notificationHub = notificationHub;
            _userHub = notificationUserHub;
            _userConnectionManager = userConnectionManager;
        }

        [HttpPost("sendNotificationToAll")]
        public async Task<IActionResult> sendNotification(SendMessageToAll.Command command)
        {
           
            var result = await Mediator.Send(command);
            return Ok(result);
        }
        [HttpPost("sendNotificationToUser")]
        public async Task<IActionResult> sendNotificationToUser(SendMessageToUser.Command command)
        {

            var result = await Mediator.Send(command);
            return Ok(result);
        }

        [HttpGet("getConnection")]
        public async Task<IActionResult> getConnection(string userId)
        {
            var connections = _userConnectionManager.GetUserConnections(userId);
            return Ok(connections);
        }

        [HttpPatch("read/{notificationId}")]
        public async Task<IActionResult> readNotification([FromBody] ReadNotification.Send send)
        {
            await Mediator.Publish(send);
            return NoContent();
        }

        [HttpGet("get-user-notification")]
        public async Task<IActionResult> getUserNotification()
        {
            var result = await Mediator.Send(new GetNotificationById.Query { });
            return Ok(result);
        }

    }
}
