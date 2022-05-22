using AutoMapper;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using UserService.Controllers.Document;
using UserService.DbContexts;
using UserService.src.Models;

namespace UserService.Controllers.Document
{
    public class DocumentRepository : IDocumentRepository
    {
        private readonly ApplicationDbContext _db;
        private IMapper _mapper;

        public DocumentRepository(ApplicationDbContext db, IMapper mapper)
        {
            _db = db;
            _mapper = mapper;
        }

        public async Task<DocumentDto> CreateUpdateDocument(DocumentDto documentDto)
        {
            DocumentModel documentmodel = _mapper.Map<DocumentDto, DocumentModel>(documentDto);
            if (documentmodel.Id > 0)
            {
                _db.Documents.Update(documentmodel);
            }
            else
            {
                _db.Documents.Add(documentmodel);
            }
            await _db.SaveChangesAsync();
            return _mapper.Map<DocumentModel, DocumentDto>(documentmodel);
        }

        public Task<bool> DeleteDocument(int documentId)
        {
            throw new NotImplementedException();
        }

        public async Task<bool> DeleteProduct(int documentId)
        {
            try
            {
                DocumentModel documentmodel = await _db.Documents.FirstOrDefaultAsync(u => u.Id == documentId);
                if (documentmodel == null)
                {
                    return false;
                }
                _db.Documents.Remove(documentmodel);
                await _db.SaveChangesAsync();
                return true;
            }
            catch (Exception)
            {
                return false;
            }
        }

        public async Task<DocumentDto> GetDocumentById(int documentId)
        {
            DocumentModel documentmodel = await _db.Documents.Where(x => x.Id == documentId).FirstOrDefaultAsync();
            return _mapper.Map<DocumentDto>(documentmodel);
        }
    }
}