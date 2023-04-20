using System;
using System.IO;
using System.Net;
using System.Text;

namespace ZendureMQTT
{
    internal class Program
    {
        static string URL = "https://app.zendure.tech/v2/developer/api/apply";
        static string snNumber = "VU5D99F74021B04";
        static string account = "dev@zendure.com";
        static void Main(string[] args)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(URL);
            request.Method = "POST";
            string body = "{\r\n" + string.Format("\"snNumber\":\"{0}\",\r\n\"account\":\"{1}\"", snNumber, account) + "\r\n}";
            UTF8Encoding encoding = new UTF8Encoding();
            byte[] byteArray = encoding.GetBytes(body);
            request.ContentLength = byteArray.Length;
            request.ContentType =  @"application/json";
            using (Stream dataStream = request.GetRequestStream()) //you must already have binded your device in the mobile app, sample values will hit an exception
            {
                dataStream.Write(byteArray, 0, byteArray.Length);
            }
            try
            {
                using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
                {
                    string content;
                    using (var reader = new StreamReader(response.GetResponseStream()))
                    {
                        content = reader.ReadToEnd();
                        Console.WriteLine(content);//should have appkey and secret so you can use it in your mqtt consumer
                    }
                }
                
            }
            catch (WebException ex)
            {
                WebResponse errorResponse = ex.Response;
                using (Stream responseStream = errorResponse.GetResponseStream())
                {
                    StreamReader reader = new StreamReader(responseStream, Encoding.GetEncoding("utf-8"));
                    string errorText = reader.ReadToEnd();
                    Console.WriteLine(errorText);              
                }
            }
            Console.ReadLine();//read output
        }
    }
}
