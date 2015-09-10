package IntelliSoftware.SMSGateway.SDK.IntelliSMSJavaSDKSamples;

import java.io.*;
import IntelliSoftware.SMSGateway.SDK.IntelliSMSJavaSDK.*;

public class GetBalance
{
	static BufferedReader KeyboardInput = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args)
	{
		try
		{
			System.out.println("");
			System.out.println("IntelliSoftware Sample - Get Balance");
			System.out.println("");

			String Username = GetParameter ("IntelliSoftware Username");
			String Password = GetParameter ("IntelliSoftware Password");


			IntelliSMS objIntelliSMS = new IntelliSMS();
			objIntelliSMS.Username = Username;
			objIntelliSMS.Password = Password;

			//Uncomment to use HTTPS/SSL
			//objIntelliSMS.PrimaryGateway = "https://www.intellisoftware.co.uk";
			//objIntelliSMS.BackupGateway = "https://www.intellisoftware2.co.uk";

			//Get Balance
			int Balance = objIntelliSMS.GetBalance ();

			System.out.println("");
			System.out.println("Your current balance is: " + Balance );
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
