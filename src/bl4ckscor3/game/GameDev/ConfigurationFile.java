package bl4ckscor3.game.GameDev;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import bl4ckscor3.game.GameDev.util.Utilities;

public class ConfigurationFile
{
	private File config;

	/**
	 * Sets up the configuration file, if not already done
	 */
	public ConfigurationFile()
	{
		try
		{
			config = new File(Utilities.getJarLocation() + "config.txt");

			if(!config.exists())
			{
				config.createNewFile();
				writeDefaultValues();
			}
		}
		catch(URISyntaxException | IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Gets the value from the given option.
	 * Example: getValue("seed"); will return the value of seed=123456789 which is 123456789
	 * @param option The option to get the value from
	 * @return The value
	 */
	public String getValue(String option)
	{
		try
		{
			List<String> lines = FileUtils.readLines(config);
			
			for(String s : lines)
			{
				if(s.startsWith(option))
					return s.split("=")[1];
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Sets the value for the given option.
	 * Example: setValue("seed", "987654321"); will set the value of seed=123456789 to 987654321 which gives seed=987654321
	 * @param option The option to set the value for
	 * @param value The value
	 */
	public void setValue(String option, String value)
	{
		try
		{
			List<String> lines = FileUtils.readLines(config);

			for(int i = 0; i < lines.size(); i++)
			{
				System.out.println(i);
				if(lines.get(i).startsWith(option))
					lines.set(i, option + "=" + value);
			}
			
			clear();
			FileUtils.writeLines(config, lines);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes the default values to the file
	 */
	public void writeDefaultValues()
	{
		Collection<String> lines = new ArrayList<String>();

		clear();
		lines.add("height=720");
		lines.add("seed=123456789");
		lines.add("width=1280");
		
		try
		{
			FileUtils.writeLines(config, lines);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Clears the file
	 */
	public void clear()
	{
		try
		{
			RandomAccessFile raf = new RandomAccessFile(config, "rw");

			raf.setLength(0);
			raf.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
