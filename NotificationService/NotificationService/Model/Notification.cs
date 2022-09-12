using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NotificationService.Model
{
    public class Notification 
    {
        public int Id { get; set; }
        public string Subject { get; set; }
        public string Body { get; set; }
        public DateTime TimeSent { get; set; }
        public bool IsActive { get; set; }
        public string URL { get; set; }
        public int UserId { get; set; }

    }
}
