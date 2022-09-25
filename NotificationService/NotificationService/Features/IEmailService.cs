using FIS.DTO.Account;

namespace FIS.Interfaces
{
    public interface IEmailService
    {
        Task Send(SendEmailModel model);
    }
}
