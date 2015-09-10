package IntelliSoftware.SMSGateway.SDK.IntelliSMSJavaSDKSamples;

import java.io.*;
import IntelliSoftware.SMSGateway.SDK.IntelliSMSJavaSDK.*;

public class SendUnicode
{
	static BufferedReader KeyboardInput = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args)
	{
		try
		{
			System.out.println("");
			System.out.println("IntelliSoftware Sample - Send Unicode Message");
			System.out.println("");

			String Username = GetParameter ("IntelliSoftware Username");
			String Password = GetParameter ("IntelliSoftware Password");

			String Recipients = GetParameter ("Message Recipient(s)");
			String Text = GetParameter ("Message Text");
			String From = GetParameter ("Message Sender's Id");

			String ToList[] = Recipients.split ( "," );

			IntelliSMS objIntelliSMS = new IntelliSMS();
			objIntelliSMS.Username = Username;
			objIntelliSMS.Password = Password;

			//Uncomment to use HTTPS/SSL
			//objIntelliSMS.PrimaryGateway = "https://www.intellisoftware.co.uk";
			//objIntelliSMS.BackupGateway = "https://www.intellisoftware2.co.uk";

			//Send message
			SendStatusCollection SendStatusList = objIntelliSMS.SendUnicodeMessage ( ToList, Text, From );

			//Show send status for each recipient
			System.out.println("");
			System.out.println("Message Send Status:");
			for ( int nIdx=0; nIdx<SendStatusList.size(); nIdx++ )
			{
				SendStatus objSendStatus = SendStatusList.get(nIdx);

				if ( objSendStatus.ResultCode == ResultCodes.OK )
				{
					System.out.println( objSendStatus.To + " Sent OK     (" + objSendStatus.MessageId + ")" );
				}
				else
				{
					System.out.println( objSendStatus.To + " Sent Failed (" + objSendStatus.ResultCode + ")" );
				}
			}
		}
		catch(IntelliSMSException e)
		{
			System.out.println("");
			System.out.println ( "Failed with ResultCode: " + e.ResultCode );
			System.out.println("");

			e.printStackTrace();

			if ( e.InnerException != null )
			{
				System.out.println("Inner Exception:");
				e.InnerException.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	static String GetParameter (String Prompt) throws IOException
	{
		System.out.print(Prompt + ": ");
		return KeyboardInput.readLine();
	}
}
