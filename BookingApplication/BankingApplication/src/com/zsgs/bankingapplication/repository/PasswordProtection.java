package com.zsgs.bankingapplication.repository;

public abstract class PasswordProtection {
	private static int key=4;
	public String toEncrypt(String password)
	{
		char[] encryptPassword = password.toCharArray();
		for(int i=0;i<encryptPassword.length;i++)
			encryptPassword[i]=(char)(encryptPassword[i]+key);
		return new String(encryptPassword);
	}
		
	public String toDecrypt(String password)
	{
		char[] decryptPassword = password.toCharArray();
		for(int i=0;i<decryptPassword.length;i++)
			decryptPassword[i]=(char)(decryptPassword[i]-key);
		return new String(decryptPassword);
	}
}
