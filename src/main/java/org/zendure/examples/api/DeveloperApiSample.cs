using System;
using System.IO;
using System.Net;
using System.Text;

namespace ZendureMQTT
{
    internal class Program
    {
        static void Main(string[] args)
        {
            var client = new HttpClient();

            var url = "https://app.zendure.tech/v2/developer/api/apply";

            var paramMap = new Dictionary<string, object>
            {
                { "snNumber", "VU5D99F74021B04" },
                { "account", "dev@zendure.com" }
            };

            var json = JsonConvert.SerializeObject(paramMap);

            var postData = new StringContent(json, Encoding.UTF8, "application/json");

            var response = client.PostAsync(url, postData).Result;
            var content = response.Content.ReadAsStringAsync().Result;

            Console.WriteLine(content);
            Console.ReadLine();
        }
    }
}
