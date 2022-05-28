using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity;

namespace UserService.Model
{
    public class Role :IdentityRole<int>
    {
        public Role()
        {
            UserRoles = new HashSet<UserRole>();
        }
        public virtual ICollection<UserRole> UserRoles { get; set; }
    }
    
}
