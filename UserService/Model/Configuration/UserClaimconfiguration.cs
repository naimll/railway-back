using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace UserService.Model.Configuration
{
    public class UserClaimconfiguration : IEntityTypeConfiguration<UserClaim>
    {
        public void Configure(EntityTypeBuilder<UserClaim> builder)
        {
            builder.Property(x => x.UserId)
                .HasColumnName("User_ID")
                .IsRequired();

            builder.ToTable("UserClaims");
        }
    }
}
