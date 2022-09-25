namespace FIS.DTO.Account
{
    public class SendEmailModel
    {
        public SendEmailModel() { }

        public string To { get; set; }
        public string Subject { get; set; }
        public string Content { get; set; }
    }
}
