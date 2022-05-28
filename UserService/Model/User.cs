using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity;

namespace UserService.Model
{
    public class User : IdentityUser<int>
    {
        public string Name { get; set; }
         public string LastName { get; set; }
         public DateTime BirthDate { get; set; }
         public string PersonalID { get; set; }

         public User()
         {
             UserRoles = new HashSet<UserRole>();
         }
         public virtual ICollection<UserRole> UserRoles { get; set; }
    }
}
