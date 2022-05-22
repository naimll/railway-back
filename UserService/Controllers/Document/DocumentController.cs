using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using UserService.Controllers.Document;

namespace UserService.Controllers.Document
{

        [Route("api/document")]
        public class DocumentController : ControllerBase
        {
            protected ResponseDto _response;
            private IDocumentRepository _documentRepository;

            public DocumentController(IDocumentRepository documentRepository)
            {
                _documentRepository = documentRepository;
                this._response = new ResponseDto();
            }

            [HttpGet]
            [Route("{id}")]
            public async Task<object> Get(int id)
            {
                try
                {
                    DocumentDto documentDto = await _documentRepository.GetDocumentById(id);
                    _response.Result = documentDto;
                }
                catch (Exception ex)
                {
                    _response.IsSuccess = false;
                    _response.ErrorMessages
                         = new List<string>() { ex.ToString() };
                }
                return _response;
            }


            [HttpPost]
            [Authorize]
            public async Task<object> Post([FromBody] DocumentDto documentDto)
            {
                try
                {
                    DocumentDto model = await _documentRepository.CreateUpdateDocument(documentDto);
                    _response.Result = model;
                }
                catch (Exception ex)
                {
                    _response.IsSuccess = false;
                    _response.ErrorMessages
                         = new List<string>() { ex.ToString() };
                }
                return _response;
            }


            [HttpPut]
            [Authorize]
            public async Task<object> Put([FromBody] DocumentDto documentDto)
            {
                try
                {
                    DocumentDto model = await _documentRepository.CreateUpdateDocument(documentDto);
                    _response.Result = model;
                }
                catch (Exception ex)
                {
                    _response.IsSuccess = false;
                    _response.ErrorMessages
                         = new List<string>() { ex.ToString() };
                }
                return _response;
            }

            [HttpDelete]
            [Authorize(Roles = "Admin")]
            [Route("{id}")]
            public async Task<object> Delete(int id)
            {
                try
                {
                    bool isSuccess = await _documentRepository.DeleteDocument(id);
                    _response.Result = isSuccess;
                }
                catch (Exception ex)
                {
                    _response.IsSuccess = false;
                    _response.ErrorMessages
                         = new List<string>() { ex.ToString() };
                }
                return _response;
            }
        }
    }

