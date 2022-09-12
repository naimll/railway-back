using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Security.Claims;


namespace NotificationService.Helpers
{
    public static class UserHelper
    {
        public static int GetUserId()
        {
            var claims = AppContextHelper.Current?.User.Claims;
            var id = claims.Where(x => x.Type == ClaimTypes.NameIdentifier).FirstOrDefault().Value;

            return int.Parse(id);
        }
    }
}
