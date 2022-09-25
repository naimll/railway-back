using FIS.DTO.Account;
using FIS.Interfaces;
using System.Net;
using System.Net.Mail;
using System.Text;

namespace FIS.Services
{
    public class EmailService : IEmailService
    {
        private readonly IConfiguration _configuration;

        public EmailService(IConfiguration configuration)
        {
            _configuration = configuration ?? throw new ArgumentNullException(nameof(configuration));
        }
        public async Task Send(SendEmailModel sendEmailModel)
        {
            var host = _configuration["MailSettings:Host"];
            var port = int.Parse(_configuration["MailSettings:Port"]);
            var from = _configuration["MailSettings:From"];
            var fromPassword = _configuration["MailSettings:FromPassword"];

            using SmtpClient client = new SmtpClient(host, port);
            client.UseDefaultCredentials = false;
            client.Credentials = new NetworkCredential(from, fromPassword);
            client.EnableSsl = true;
            var message = PrepareMessage(from, sendEmailModel.To, sendEmailModel.Subject, sendEmailModel.Content);

            await client.SendMailAsync(message);

        }
        private MailMessage PrepareMessage(string from, string to, string subject, string body)
        {
            var message = new MailMessage(from, to, subject, body)
            {
                IsBodyHtml = true,
                BodyEncoding = Encoding.UTF8,
                SubjectEncoding = Encoding.UTF8
            };
            return message;
        }
    }
}
