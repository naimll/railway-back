using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NotificationService.Model
{
    public class EmailTemplate
    {
        public int Id { get; set; }
        public string IntendedFor { get; set; }
        public string Subject { get; set; }
        public string Body { get; set; }
    }
}
