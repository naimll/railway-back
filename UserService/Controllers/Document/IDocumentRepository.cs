using System;
using System.Threading.Tasks;

namespace UserService.Controllers.Document
{
        public interface IDocumentRepository
        {
            Task<DocumentDto> GetDocumentById(int documentId);
            Task<DocumentDto> CreateUpdateDocument(DocumentDto documentDto);
            Task<bool> DeleteDocument(int documentId);
        }
}
