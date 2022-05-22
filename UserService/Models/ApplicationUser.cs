using System;
using Microsoft.AspNetCore.Identity;

namespace UserService.src.Models
{
    public class ApplicationUser : IdentityUser
    {
        public string Name { get; set; }

        public string LastName { get; set; }

        public DateTime DateOfBirth { get; set; }

        public DocumentModel? Document { get; set; }

       
    }
}
