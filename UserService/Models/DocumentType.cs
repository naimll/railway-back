using System;
namespace UserService.src.Models
{
    public class DocumentType : Enumeration
    {
        public static DocumentType IDCard = new(1, nameof(IDCard));
        public static DocumentType Passport = new(2, nameof(Passport));


        public DocumentType(int id, String name) : base(id, name)
        {
        }
    }
}

