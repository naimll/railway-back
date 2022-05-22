using System;
namespace UserService.Controllers.Document
{
    public class DocumentDto
    {
            public int Id { get; set; }
            public int DocumentTypeID { get; set; }
            public string Nationality { get; set; }
            public string DateOfIssue { get; set; }
            public string DateOfExpiry { get; set; }
            public string Authority { get; set; }
            public string ImageURL { get; set; }

    }
}

