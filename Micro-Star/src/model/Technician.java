package model;

public class Technician {
	
	private int staffID;
	private String fullName;
	private String email;
	private int complaintCode;
	private String password;
	
	public Technician (int staffID, String fullName, String email, int complaintCode,String password)
	{
		this.staffID = staffID;
		this.fullName=fullName;
		this.email=email;
		this.complaintCode=complaintCode;
		this.password=password;
	}
	
	public Technician (Technician t)
	{
		this.staffID = t.staffID;
		this.fullName= t.fullName;
		this.email= t.email;
		this.complaintCode= t.complaintCode;
		this.password= t.password;
	}
	
	public Technician ()
	{
		this.staffID = 0;
		this.fullName= "";
		this.email= "";
		this.complaintCode=0;
		this.password= "";
	}
	
	public int getStaffID()
	{
		return staffID;
	}
	public String getFullName()
	{
		return fullName;
	}	
	public String getEmail()
	{
		return email;
	}
	public int getComplaintCode()
	{
		return complaintCode;
	}
	public String getPassword()
	{
		return password;
	}
	public void setStaffID(int id)
	{
		this.staffID = id;
	}
	public void setFullName(String name)
	{
		this.fullName = name;
	}
	public void setEmail(String mail)
	{
		this.email = mail;
	}
	public void setComplaintCode(int compcode)
	{
		this.complaintCode = compcode;
	}
	public void setPassword(String pass)
	{
		this.password = pass;
	}
	
}

