using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace UserService.Model.Configuration
{
    public class UserConfiguration : IEntityTypeConfiguration<User>
    {
        public void Configure(EntityTypeBuilder<User> builder)
        {
            builder.Property(x => x.Id)
                .HasColumnName("ID")
                .IsRequired();

            builder.Property(x => x.Name)
                .HasColumnName("Name")
                .IsRequired();

            builder.Property(x => x.LastName)
                .HasColumnName("LastName")
                .IsRequired();

            builder.Property(x => x.BirthDate)
                .HasColumnName("Birthdate")
                .IsRequired();
            builder.Property(x => x.PersonalID)
                .HasColumnName("PersonalID")
                .IsRequired();

            builder.ToTable("Users");
        }

     
    }
}
