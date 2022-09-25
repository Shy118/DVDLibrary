package com.dvdlibrary.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvdlibrary.dto.DVD;

@Component(value = "view")
public class DVDLibraryView {
	@Autowired
	private UserIO io;

	public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Create DVD");
        io.print("2. Remove a DVD");
        io.print("3. Edit a DVD");
        io.print("4. List DVDs");
        io.print("5. View a DVD by ID");
        io.print("6. View a DVD by Title");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
    public DVD getNewDVDInfo() {
        String dvdId = io.readString("Please enter DVD ID");
        String title = io.readString("Please enter movie Title");
        String releaseDate = io.readString("Please enter movie Release date");
        String mPAA = io.readString("Please enter MPAA rating");
		String directorName = io.readString("Please enter Director's name");
		String studio = io.readString("Please enter Studio's name");
		String rating = io.readString("Please enter rating");
        DVD currentDVD = new DVD(dvdId);
        currentDVD.setTitle(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMPAA(mPAA);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setRating(rating);
        
        return currentDVD;
    }
    
    public void displayCreateDVDBanner() {
        io.print("==================== Create DVD ====================");
    }
    
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created.  Please hit enter to continue");
    }
    
    public void displayRemoveDVDBanner () {
        io.print("==================== Remove DVD ====================");
    }

    public void displayRemoveResult(DVD dvdRecord) {
        if(dvdRecord != null){
          io.print("DVD successfully removed.");
        }else{
          io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayEditBanner() {
    	io.print("==================== Edit DVD ====================");
    }
    
    public int printEditMenuAndGetSelection() {
    	io.print("Edit Menu");
        io.print("1. Edit Title");
        io.print("2. Edit Release date");
        io.print("3. Edit MPAA rating");
        io.print("4. Edit Director's name");
        io.print("5. Edit Studio's name");
        io.print("6. Edit Rating");
        io.print("7. Edit All");
        io.print("8. Exit");

        return io.readInt("Please select from the above choices from ", 1, 8);
    }
    
    public String getEditTitleInfo() {
    	return io.readString("Please enter new Title");
    }
    
    public String getEditReleaseDateInfo() {
    	return io.readString("Please enter new Release date");
    }
    
    public String getEditMPAAInfo() {
    	return io.readString("Please enter new MPAA rating");
    }
    
    public String getEditDirectorNameInfo() {
    	return io.readString("Please enter new Director's name");
    }
    
    public String getEditStudioInfo() {
    	return io.readString("Please enter new Studio's name");
    }
    
    public String getEditRatingInfo() {
    	return io.readString("Please enter new DVD Rating");
    }
    
    public void displayDisplayAllBanner() {
        io.print("============================== Display All DVDs ==============================");
    }
    
    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            io.print(currentDVD.toString());
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayDVDBanner () {
        io.print("============================== Display DVD ==============================");
    }

    public String getDVDIdChoice() {
        return io.readString("Please enter the DVD ID.");
    }
    
    public void displayDisplayDVDByTitleBanner () {
        io.print("============================== Display DVD by Title ==============================");
    }
    
    public String getDVDTitleChoice() {
        return io.readString("Please enter the DVD Title.");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.toString());
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("==================== ERROR ====================");
        io.print(errorMsg);
    }
}
