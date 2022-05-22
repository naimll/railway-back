using IdentityModel;
using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using UserService.DbContexts;
using UserService.src.Models;

namespace UserService.Initializer
{
    public class DbInitializer : IDbInitializer
    {
        private readonly ApplicationDbContext _db;
        private readonly UserManager<ApplicationUser> _userManager;
        private readonly RoleManager<IdentityRole> _roleManager;

        public DbInitializer(ApplicationDbContext db, UserManager<ApplicationUser> userManager, RoleManager<IdentityRole> roleManager) 
        {
            _db = db;
            _roleManager = roleManager;
            _userManager = userManager;
        }

        public void Initialize()
        {

            if (_roleManager.FindByNameAsync(StaticDetails.Admin).Result == null)
            {
                _roleManager.CreateAsync(new IdentityRole(StaticDetails.Admin)).GetAwaiter().GetResult();
                _roleManager.CreateAsync(new IdentityRole(StaticDetails.Passenger)).GetAwaiter().GetResult();
            }
            else { return; }

            ApplicationUser adminUser = new ApplicationUser()
            {
                Name = "Albana",
                LastName = "Hajdini",
                DateOfBirth = new DateTime(2002, 05, 09, 10, 20, 12),
              
                Email = "ah48391@ubt-uni.net", 
                EmailConfirmed = true
        
               

            };

            _userManager.CreateAsync(adminUser, "Admin123*").GetAwaiter().GetResult();
            _userManager.AddToRoleAsync(adminUser, StaticDetails.Admin).GetAwaiter().GetResult();

            var temp1 = _userManager.AddClaimsAsync(adminUser, new Claim[] {
               new Claim(JwtClaimTypes.Name,adminUser.Name+" "+ adminUser.LastName),
               new Claim(JwtClaimTypes.GivenName, adminUser.Name),
               new Claim(JwtClaimTypes.FamilyName, adminUser.LastName),
               new Claim(JwtClaimTypes.Role,StaticDetails.Admin),
            }).Result;

            ApplicationUser passengerUser = new ApplicationUser()
            {
                Name = "Elita",
                LastName = "Hajrizi",
                DateOfBirth = new DateTime(2001, 02, 27, 10, 20, 12),
               
                Email = "eh47718@ubt-uni.net",
                EmailConfirmed = true
            };
            _userManager.CreateAsync(passengerUser, "Passenger123*").GetAwaiter().GetResult();
            _userManager.AddToRoleAsync(passengerUser, StaticDetails.Passenger).GetAwaiter().GetResult();

            var temp2 = _userManager.AddClaimsAsync(passengerUser, new Claim[] {
               new Claim(JwtClaimTypes.Name,passengerUser.Name+" "+ passengerUser.LastName),
               new Claim(JwtClaimTypes.GivenName, passengerUser.Name),
               new Claim(JwtClaimTypes.FamilyName, passengerUser.LastName),
               new Claim(JwtClaimTypes.Role,StaticDetails.Passenger),
            }).Result;
        }
    }
       

}