import java.net.*;
import java.io.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Main
{
	// Find your Account Sid and Token at twilio.com/user/account
	public static final String ACCOUNT_SID = "<ACCOUNTSID>";
	public static final String AUTH_TOKEN = "<AUTHTOKEN";
	public static final String TO_PHONE = "+1<PHONE>";
	public static final String FROM_PHONE = "+1<PHONE>";

	public static void main(String[] args) throws Exception
	{
		/*
		 * get cat fact
		 */

		String factURL="https://catfact.ninja/fact";

		URL epaServer = new URL(factURL);

		URLConnection ac = epaServer.openConnection();

		InputStreamReader inputStream = new InputStreamReader(ac.getInputStream(), "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStream);
		StringBuilder responseBuilder = new StringBuilder();

		String line;

		while ((line = bufferedReader.readLine()) != null)
		{
			responseBuilder.append(line + "\n");
		}
		bufferedReader.close();

		/*
		 * format string
		 */
		String fact = responseBuilder.toString();
		System.out.println(fact);
		int startPoint = 9;
		int endPoint = fact.lastIndexOf(":") - 10;
		fact = fact.substring(startPoint, endPoint);

		// System.out.println(fact);

		/*
		 * send text message
		 */

		// https://www.twilio.com/docs/quickstart/java/sms/sending-via-rest#send-mms-via-rest
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message
				.creator(new PhoneNumber(TO_PHONE), new PhoneNumber(FROM_PHONE), fact)
				.create();
		System.out.println(message.getSid());

	}  // end of main
}// end of program