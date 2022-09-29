package com.dvdlibrary.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.dvdlibrary.dto.DVD;

@Component(value = "dao")
public class DVDLibarayDaoFileImpl implements DVDLibraryDao {
	private Map<String, DVD> DVDs = new HashMap<>();
	public static final String LIBRARY_FILE = "DVDLibrary.txt";
	public static final String DELIMITER = "::";
	
	@Override
	public DVD addDVD(String dvdId, DVD dvd) throws DVDLibraryDaoException {
		loadRoster();
	    DVD newDVD = DVDs.put(dvdId, dvd);
	    writeRoster();
	    return newDVD;
	}

	@Override
	public DVD getDVD(String dvdId) throws DVDLibraryDaoException {
		loadRoster();
	    return DVDs.get(dvdId);
	}

	@Override
	public DVD removeDVD(String dvdId) throws DVDLibraryDaoException {
		loadRoster();
	    DVD removedDVD = DVDs.remove(dvdId);
	    writeRoster();
	    return removedDVD;
	}
	
	@Override
	public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
		loadRoster();
	    return new ArrayList<DVD>(DVDs.values());
	}
	
	@Override
	public List<DVD> getAllDVDsByTitle(String title) throws DVDLibraryDaoException {
		loadRoster();
		return new ArrayList<DVD>(DVDs.values()).stream().filter(s -> s.getTitle().indexOf(title) != -1).collect(Collectors.toList());
	}

	private DVD unmarshallDVD(String dvdAsText){
	    // DVDLibraryAsText is expecting a line read in from our file.
	    // For example, it might look like this:
	    // 1234::Titanic::PG-13::19/12/1997::James Cameron::Paramount Pictures::7.9/10.0
	    //
	    // We then split that line on our DELIMITER - which we are using as ::
	    // Leaving us with an array of Strings, stored in dvdTokens.
	    // Which should look like this:
	    // _________________________________________________________________________
	    // |    |       |     |          |             |                  |        |
	    // |1234|Titanic|PG-13|19/12/1997|James Cameron|Paramount Pictures|7.9/10.0|
	    // |    |       |     |          |             |                  |        |
	    // -------------------------------------------------------------------------
	    //  [0]  [1]      [2]       [3]        [5]              [6]           [7]
	    String[] dvdTokens = dvdAsText.split(DELIMITER);

	    // Given the pattern above, the DVD Id is in index 0 of the array.
	    String dvdId = dvdTokens[0];

	    // Which we can then use to create a new DVD object to satisfy
	    // the requirements of the DVD constructor.
	    DVD dvdFromFile = new DVD(dvdId);

	    // Index 1 - Title
	    dvdFromFile.setTitle(dvdTokens[1]);

	    // Index 2 - Release date
	    dvdFromFile.setReleaseDate(dvdTokens[2]);

	    // Index 3 - MPAA rating
	    dvdFromFile.setMPAA(dvdTokens[3]);
	    
	    // Index 4 - Director's name
	    dvdFromFile.setDirectorName(dvdTokens[4]);
	    
	    // Index 5 - Studio
	    dvdFromFile.setStudio(dvdTokens[5]);
	    
	    // Index 6 - User rating
	    dvdFromFile.setRating(dvdTokens[6]);
	    
	    // We have now created a DVD!
	    return dvdFromFile;
	}
	
	private void loadRoster() throws DVDLibraryDaoException {
	    Scanner scanner;

	    try {
	        // Create Scanner for reading the file
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader(LIBRARY_FILE)));
	    } catch (FileNotFoundException e) {
	        throw new DVDLibraryDaoException(
	                "-_- Could not load roster data into memory.", e);
	    }
	    // currentLine holds the most recent line read from the file
	    String currentLine;
	    // currentDVD holds the most recent DVD unmarshalled
	    DVD currentDVD;
	    // Go through LIBRARY_FILE line by line, decoding each line into a 
	    // DVD object by calling the unmarshallDVD method.
	    // Proceed while we have more lines in the file
	    while (scanner.hasNextLine()) {
	        // get the next line in the file
	        currentLine = scanner.nextLine();
	        // unmarshall the line into a DVD
	        currentDVD = unmarshallDVD(currentLine);

	        // We are going to use the DVD id as the map key for our DVD object.
	        // Put currentDVD into the map using DVD id as the key
	        DVDs.put(currentDVD.getDvdId(), currentDVD);
	    }
	    // close scanner
	    scanner.close();
	}
	
	private String marshallDVD(DVD aDVD){
	    // in memory object to end up like this:
	    // 1234::Titanic::PG-13::19/12/1997::James Cameron::Paramount Pictures::7.9/10.0

	    // Start with the DVD id
	    String dvdAsText = aDVD.getDvdId() + DELIMITER;

	    // add the rest of the properties in the correct order:

	    // Title
	    dvdAsText += aDVD.getTitle() + DELIMITER;

	    // Release Date
	    dvdAsText += aDVD.getReleaseDate() + DELIMITER;

	    // MPAA 
	    dvdAsText += aDVD.getMPAA() + DELIMITER;

	    // Director's name
	    dvdAsText += aDVD.getDirectorName() + DELIMITER;
	    
	    // Studio
	    dvdAsText += aDVD.getStudio() + DELIMITER;
	    
	    // Rating
	    dvdAsText += aDVD.getRating();
	    
	    // We have now turned a DVD to text
	    return dvdAsText;
	}
	
	/**
	 * Writes all DVDs in the library out to a LIBRARY_FILE.  See loadRoster
	 * for file format.
	 * 
	 * @throws DVDLibraryDaoException if an error occurs writing to the file
	 */
	private void writeRoster() throws DVDLibraryDaoException {
	    
	    PrintWriter out;

	    try {
	        out = new PrintWriter(new FileWriter(LIBRARY_FILE));
	    } catch (IOException e) {
	        throw new DVDLibraryDaoException(
	                "Could not save DVD data.", e);
	    }

	    // Write out the DVD objects to the library file.
	    String dvdAsText;
	    List<DVD> dvdList = this.getAllDVDs();
	    for (DVD currentDVD : dvdList) {
	        // turn a DVD into a String
	        dvdAsText = marshallDVD(currentDVD);
	        // write the DVD object to the file
	        out.println(dvdAsText);
	        // force PrintWriter to write line to the file
	        out.flush();
	    }
	    // Clean up
	    out.close();
	}
	
} //class
