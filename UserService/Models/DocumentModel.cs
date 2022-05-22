using System;
namespace UserService.src.Models
{
    public class DocumentModel
    {
        public int Id { get; set; }

        public int DocumentTypeID { get; set; }

        public DocumentType DocumentType { get; set; }

        private string _Nationality { get; set; }

        private DateTime _DateOfIssue { get; set; }

        private DateTime _DateOfExpiry { get; set; }

        private string _Authority { get; set; }

        private string _ImageName { get; set; }

        private string _ImageURL { get; set; }
    }
}

