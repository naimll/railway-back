using System;
namespace UserService.src.Models
{
    public class DocumentModel
    {
        public int Id { get; set; }

        public int DocumentTypeID { get; set; }

        public DocumentType DocumentType { get; set; }

        public string Nationality { get; set; }

        public DateTime DateOfIssue { get; set; }

        public DateTime DateOfExpiry { get; set; }

        public string Authority { get; set; }

        public string ImageName { get; set; }

        public string ImageURL { get; set; }
    }
}

