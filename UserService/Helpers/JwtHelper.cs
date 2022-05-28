using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using Microsoft.Extensions.DependencyInjection;

namespace UserService.Helpers
{
    public static class JwtHelper
    {
        public static string GenerateToken(IList<Claim> claims)
        {
            var configuration = AppContextHelper.Current.RequestServices.GetService<IConfiguration>();

            var key = Encoding.UTF8.GetBytes(configuration["Jwt:Secret"]);
            var issuer = configuration["Jwt:Issuer"];
            var audience = configuration["Jwt:Audience"];
            var expiresIn = int.Parse(configuration["Jwt:ExpiresIn"]);
            var tokenDescription = new SecurityTokenDescriptor
            {
                Issuer = issuer,
                IssuedAt = DateTime.Now,
                Audience = audience,
                Subject = new ClaimsIdentity(claims),
                SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key), SecurityAlgorithms.HmacSha256Signature),
                Expires = DateTime.Now.AddHours(expiresIn)
            };

            var tokenHandler = new JwtSecurityTokenHandler();
            var token = tokenHandler.CreateToken(tokenDescription);
            return tokenHandler.WriteToken(token);
        }
    }
}
