using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NotificationService.Model
{
    public class PublicNotification
    {
        public int Id { get; set; }
        public string Subject { get; set; }
        public string Body { get; set; }
        public DateTime TimeSent { get; set; }
        public bool IsActive { get; set; }
        public string URL { get; set; }
    }
}
