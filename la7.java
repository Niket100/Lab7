package lab7;
import java.io.*;
import java.util.*;

class Song implements Serializable
{
	public String name;
	public String singer;
	public int duration;
	public Song(String s,String t,int d)
	{
		name=s;
		singer=t;
		duration =d;
	}
	public void display()
	{
		System.out.print("Name : "+ name+"  ");
		System.out.print("Singer : "+ singer+"  ");
		System.out.print("Duration : "+ duration+"  ");
	}
}

class PlayList implements Serializable
{
	public int no_song;
	public String name;
	public ArrayList<Song> songs;
	public PlayList(String s)
	{
		name=s;
		songs = new ArrayList<Song>();
		no_song=0;
	}
	public void add(String s,String t,int d)
	{
		Song so=new Song(s,t,d);
		songs.add(so);
		no_song++;
		System.out.println("Number of songs int the playlist is : "+ no_song);
	}
	public void delete(String s)
	{
		int i,j;
		j=-1;
		for(i=0;i<no_song;i++)
		{
			if(songs.get(i).name.equals(s))
				j=i;
		}
		if(j==-1)
		{
			System.out.println("Song not found");
		}
		else
		{
			songs.remove(j);
			no_song--;
			System.out.println("Number of songs int the playlist is : "+ no_song);
		}
	}

	public void search(String s)
	{
		int i,j;
		j=-1;
		for(i=0;i<no_song;i++)
		{
			if(songs.get(i).name.equals(s))
				j=i;
		}
		if(j==-1)
		{
			System.out.println("Song not found");
		}
		else
		{
			System.out.println("Found, the song exists in the playlist");
		}
	}
	
	public void show()
	{
		int i;
		for(i=0;i<no_song;i++)
		{
			System.out.print((i+1)+":  ");
			songs.get(i).display();
		}
	}
	
	
	
}

public class la7 {
	
	public static ArrayList<PlayList> ps;
	
	public	static	void serialize() throws	IOException	
	{
		ObjectOutputStream out=null;
		try	
		{	
			out	=	new	ObjectOutputStream(new FileOutputStream("/home/niket/Desktop/out.txt"));
			for(int i=0;i<ps.size();i++)
			out.writeObject(ps.get(i));	
		}	
		finally	
		{	
			out.close();	
		}
		
	}
	public static void deserialize() throws	IOException,	ClassNotFoundException,EOFException 
	{
		ObjectInputStream	in	=	null;	
		try	
		{	
		in=new	ObjectInputStream	(new FileInputStream("/home/niket/Desktop/out.txt"));
		
		ps=new ArrayList<PlayList>();
		PlayList te=(PlayList)in.readObject();
		while(te!=null)
		{
			
			ps.add(te);
			te=(PlayList)in.readObject();
		}
		}
		catch (EOFException e)
		{
		}
		finally	
		{	
			in.close();	
		}	
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException 
	{
		Scanner r=new Scanner(System.in);
		int ch,i,j,cho,dur;
		ch=0;
		j=0;
		String s,n;
	while(ch!=6)
		{
			ch=0;
			deserialize();
			for(i=0;i<ps.size();i++)
			{
				System.out.println(ps.get(i).name);
			}
			r.nextLine();
			System.out.println("Enter your choice : ");
			s=r.nextLine();
			PlayList tem=new PlayList(s);
			for(i=0;i<ps.size();i++)
			{
				if(s.equals(ps.get(i).name))
					{
						tem=ps.get(i);
						j=i;
					}
			}
			
			while(ch!=5 && ch!=6)
			{
				System.out.println("1. Add");
				System.out.println("2. Delete");
				System.out.println("3. Search");
				System.out.println("4. Show");
				System.out.println("5. Back to menu");
				System.out.println("6. Exit");
				ch=r.nextInt();
				
				
				if(ch==1)
				{
					r.nextLine();
					System.out.println("Name: ");
					s=r.nextLine();
					System.out.println("Singer");
					n=r.nextLine();
					System.out.println("Duration");
					dur=r.nextInt();
					tem.add(s, n, dur);
				}
				else if(ch==2)
				{
					r.nextLine();
					System.out.println("Name: ");
					s=r.nextLine();
					tem.delete(s);
				}
				else if(ch==3)
				{
					r.nextLine();
					System.out.println("Name: ");
					s=r.nextLine();
					tem.search(s);
			
				}
				else if(ch==4)
				{
					tem.show();
				}
				else
					break;
			}
			ps.remove(j);
			ps.add(tem);
			
			serialize();
			
		}
	}

}
